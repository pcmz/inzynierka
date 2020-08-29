package pl.agh.student.pcmz.pracainzynierska.services;

import lombok.extern.java.Log;
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

@Log
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
        String fakturaXlId = invoice.getFakturaXlId();
        return FakturaXlCrd.getInvoiceAsPDF(fakturaXlId, request, response);
    }

    public Invoice createInvoiceByOrderId(Long orderId) throws IOException {
        return createInvoice(orderRepository.findById(orderId).get());
    }

    public Invoice createInvoice(Order order) throws IOException {
        log.warning("InvoiceService#createInvoice");
        Dokument invoiceBasedOnCart = createInvoiceBasedOnCart(order);
        enrichDokument(invoiceBasedOnCart);
        Dokument invoiceDokument = FakturaXlCrd.createInvoice(invoiceBasedOnCart);

        Invoice invoiceDAO = new Invoice();
        invoiceDAO.setOrder(order);
        invoiceDAO.setFakturaXlId(invoiceDokument.getDokumentId());
        invoiceDAO.setInvoiceName(invoiceDokument.getDokumnetNr());
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
            fakturaPozycje.setNetto(String.valueOf(unitPrice));
            Integer vat = product.getVat();
            double brutto = unitPrice * (1 + (Double.valueOf(vat) / 100));
            fakturaPozycje.setBrutto(String.valueOf(brutto).replace(',', '.'));
            fakturaPozycje.setVat(String.valueOf(vat).replace(',', '.'));
            float wNetto = quantity * unitPrice;
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
//        String shippingAddress = customer.getShippingAddress();
        Podmiot nabywca = new Podmiot();
//        nabywca.setNazwa(customer.getCompanyName());
        nabywca.setNip(customer.getNip());
//        nabywca.setUlicaINumer(shippingAddress.substring(0, shippingAddress.lastIndexOf(',')));
//        nabywca.setKodPocztowy(shippingAddress.substring(shippingAddress.length() - 6));
//        nabywca.setMiejscowosc(shippingAddress.substring(shippingAddress.lastIndexOf(',') + 2, shippingAddress.lastIndexOf(' ')));
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

    public InvoiceAddress getInvoicePdfAddress(Long orderId) {
        return new InvoiceAddress(HOSTNAME + "/dokument_export.php?api=" + API_TOKEN + "&dokument_id=" + invoiceRepository.getByOrder(orderRepository.findById(orderId)).getFakturaXlId() + "&pdf=1");
    }
}
