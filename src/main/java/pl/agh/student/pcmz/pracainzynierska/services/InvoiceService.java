package pl.agh.student.pcmz.pracainzynierska.services;

import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlCrd;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.FakturaPozycje;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Podmiot;
import pl.agh.student.pcmz.pracainzynierska.models.*;
import pl.agh.student.pcmz.pracainzynierska.repositories.CartRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.InvoiceRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderRepository;
import pl.agh.student.pcmz.pracainzynierska.util.InvoiceAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlUtils.*;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public String getInvoiceAsPDF(Long orderId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Invoice invoice = invoiceRepository.getByOrder(orderRepository.findById(orderId));
        String fakturaXlId = getNewestFakturaXlId(invoice);
        return FakturaXlCrd.getInvoiceAsPDF(fakturaXlId, request, response);
    }

    public Invoice createInvoiceByOrderId(Long orderId) throws IOException {
        return createProformaInvoice(orderRepository.findById(orderId).get());
    }

    public Invoice createProformaInvoice(Order order) throws IOException {
        Dokument invoiceBasedOnCart = createInvoiceBasedOnCart(order);
        enrichDokument(invoiceBasedOnCart);
        Dokument invoiceDokument = FakturaXlCrd.createProformaInvoice(invoiceBasedOnCart);

        Invoice invoiceDAO = new Invoice();
        invoiceDAO.setOrder(order);
        invoiceDAO.setFakturaXlIdProforma(invoiceDokument.getDokumentId());
        invoiceDAO.setInvoiceNameProforma(invoiceDokument.getDokumnetNr());
        return invoiceRepository.save(invoiceDAO);
    }

    private Dokument createInvoiceBasedOnCart(Order order) {
        Dokument dokument = new Dokument();

        List<Cart> carts = cartRepository.findAll();
        double wartoscNetto = 0.0;
        double wartoscBrutto = 0.0;

        dokument.setFakturaPozycjeList(new LinkedList<>());
        for (Cart cart : carts) {
            FakturaPozycje fakturaPozycje = new FakturaPozycje();
            Product product = cart.getProduct();
            fakturaPozycje.setNazwa(product.getProductName());
            Short quantity = cart.getQuantity();
            fakturaPozycje.setIlosc(String.valueOf(quantity));
            fakturaPozycje.setJm(product.getUnit());
            Float unitPrice = product.getUnitPrice();
            Integer vat = product.getVat();
            double brutto = unitPrice;
            double netto = unitPrice / (1 + (Double.valueOf(vat) / 100));
            fakturaPozycje.setNetto(String.valueOf(netto));
            fakturaPozycje.setBrutto(String.valueOf(brutto).replace(',', '.'));
            fakturaPozycje.setVat(String.valueOf(vat).replace(',', '.'));
            double wNetto = quantity * netto;
            fakturaPozycje.setWartoscNetto(String.valueOf(wNetto).replace(',', '.'));
            double wBrutto = quantity * brutto;
            fakturaPozycje.setWartoscBrutto(String.valueOf(wBrutto).replace(',', '.'));
            dokument.getFakturaPozycjeList().add(fakturaPozycje);
            wartoscNetto += wNetto;
            wartoscBrutto += wBrutto;
        }
        dokument.setWartoscNetto(String.format("%.2f", wartoscNetto).replace(',', '.'));
        dokument.setWartoscVat(String.format("%.2f", wartoscBrutto - wartoscNetto).replace(',', '.'));
        dokument.setWartoscBrutto(String.format("%.2f", wartoscBrutto).replace(',', '.'));

        Customer customer = order.getCustomer();
        Address address = customer.getAddress();
        Podmiot nabywca = new Podmiot();
        nabywca.setNazwa(customer.getName());
        nabywca.setNip(customer.getNip());
        nabywca.setUlicaINumer(address.getStreet() + " " + address.getHouse_no());
        nabywca.setKodPocztowy(address.getCode());
        nabywca.setMiejscowosc(address.getCity());
        nabywca.setKraj(KRAJ);
        dokument.setNabywca(nabywca);
        return dokument;
    }

    public Invoice getInvoiceByOrderId(Long orderId) {
        return invoiceRepository.getByOrder(orderRepository.findById(orderId));
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public InvoiceAddress getProformaInvoicePdfAddress(Long orderId) {
        return getInvoicePdfAddress(invoiceRepository.getByOrder(orderRepository.findById(orderId)).getFakturaXlIdProforma());
    }

    public InvoiceAddress getVatInvoicePdfAddress(Long orderId) {
        return getInvoicePdfAddress(invoiceRepository.getByOrder(orderRepository.findById(orderId)).getFakturaXlIdVat());
    }

    public InvoiceAddress getInvoicePdfAddress(String fakturaXlId) {
        return new InvoiceAddress(HOSTNAME + "/dokument_export.php?api=" + API_TOKEN + "&dokument_id=" + fakturaXlId + "&pdf=2");
    }

    public Invoice promoteProformaInvoiceIntoVat(Long orderId) throws IOException {
        Invoice oldInvoice = invoiceRepository.getByOrder(orderRepository.findById(orderId));
        Dokument proformaInvoice = FakturaXlCrd.getInvoice(oldInvoice.getFakturaXlIdProforma());
        Dokument vatInvoice = FakturaXlCrd.createVatInvoice(proformaInvoice);
        oldInvoice.setFakturaXlIdVat(vatInvoice.getDokumentId());
        oldInvoice.setInvoiceNameVat(vatInvoice.getDokumnetNr());
        Invoice save = invoiceRepository.save(oldInvoice);
        return save;
    }

    private String getNewestFakturaXlId(Invoice invoice) {
        String fakturaXlIdVat = invoice.getFakturaXlIdVat();
        return fakturaXlIdVat == null ? invoice.getFakturaXlIdProforma() : fakturaXlIdVat;
    }
}
