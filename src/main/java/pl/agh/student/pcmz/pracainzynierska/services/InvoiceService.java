package pl.agh.student.pcmz.pracainzynierska.services;

import org.springframework.stereotype.Service;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.InvoiceIntegrationController;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.InvoiceIntegrationDocument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlCrud;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.FakturaPozycje;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Podmiot;
import pl.agh.student.pcmz.pracainzynierska.models.*;
import pl.agh.student.pcmz.pracainzynierska.repositories.CartRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.InvoiceRepository;
import pl.agh.student.pcmz.pracainzynierska.repositories.OrderRepository;
import pl.agh.student.pcmz.pracainzynierska.util.InvoiceAddress;

import java.util.LinkedList;
import java.util.List;

import static pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlUtils.*;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    private final InvoiceIntegrationController invoiceIntegrationController = new FakturaXlCrud();

    public InvoiceService(InvoiceRepository invoiceRepository, OrderRepository orderRepository, CartRepository cartRepository) {
        this.invoiceRepository = invoiceRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    public Invoice createProformaInvoice(Order order) {
        InvoiceIntegrationDocument invoiceBasedOnCart = createInvoiceBasedOnCart(order);
        InvoiceIntegrationDocument invoiceDokument = invoiceIntegrationController.createProformaInvoice(invoiceBasedOnCart);
        Invoice invoiceDAO = new Invoice();
        invoiceDAO.setOrder(order);
        invoiceDAO.setFakturaXlIdProforma(invoiceDokument.getDokumentId());
        invoiceDAO.setInvoiceNameProforma(invoiceDokument.getDokumnetNr());
        return invoiceRepository.save(invoiceDAO);
    }

    public Invoice getInvoiceByOrderId(Long orderId) {
        return invoiceRepository.getByOrder(orderRepository.findById(orderId));
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public InvoiceAddress getProformaInvoicePdfAddress(Long orderId) {
        return getInvoicePdfAddress(getInvoiceByOrderId(orderId).getFakturaXlIdProforma());
    }

    public InvoiceAddress getVatInvoicePdfAddress(Long orderId) {
        return getInvoicePdfAddress(getInvoiceByOrderId(orderId).getFakturaXlIdVat());
    }

    public Invoice promoteProformaInvoiceIntoVat(Long orderId) {
        Invoice oldInvoice = invoiceRepository.getByOrder(orderRepository.findById(orderId));
        InvoiceIntegrationDocument vatInvoice = invoiceIntegrationController.promoteProformaInvoiceIntoVat(oldInvoice.getFakturaXlIdProforma());
        oldInvoice.setFakturaXlIdVat(vatInvoice.getDokumentId());
        oldInvoice.setInvoiceNameVat(vatInvoice.getDokumnetNr());
        return invoiceRepository.save(oldInvoice);
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
            fakturaPozycje.setBrutto(getStringValue(brutto));
            fakturaPozycje.setVat(String.valueOf(vat));
            double wNetto = quantity * netto;
            fakturaPozycje.setWartoscNetto(getStringValue(wNetto));
            double wBrutto = quantity * brutto;
            fakturaPozycje.setWartoscBrutto(getStringValue(wBrutto));
            dokument.getFakturaPozycjeList().add(fakturaPozycje);
            wartoscNetto += wNetto;
            wartoscBrutto += wBrutto;
        }
        dokument.setWartoscNetto(getStringValue(wartoscNetto));
        dokument.setWartoscVat(getStringValue(wartoscBrutto - wartoscNetto));
        dokument.setWartoscBrutto(getStringValue(wartoscBrutto));

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

    private String getStringValue(double value) {
        return String.format("%.2f", value).replace(',', '.');
    }

    private InvoiceAddress getInvoicePdfAddress(String fakturaXlId) {
        return new InvoiceAddress(INVOICE_AS_PDF + fakturaXlId + OPEN_PDF);
    }
}
