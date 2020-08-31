package pl.agh.student.pcmz.pracainzynierska.controllers;

import org.springframework.web.bind.annotation.*;
import pl.agh.student.pcmz.pracainzynierska.models.Invoice;
import pl.agh.student.pcmz.pracainzynierska.models.Order;
import pl.agh.student.pcmz.pracainzynierska.services.InvoiceService;
import pl.agh.student.pcmz.pracainzynierska.util.InvoiceAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoice")
    public Invoice createProformaInvoice(@Valid @RequestBody Order order) throws IOException {
        return invoiceService.createProformaInvoice(order);
    }

    @GetMapping("/promote_invoice/{orderId}")
    public Invoice promoteProformaInvoiceIntoVat(@PathVariable Long orderId) throws IOException {
        return invoiceService.promoteProformaInvoiceIntoVat(orderId);
    }

    @GetMapping("/invoice_pdf/{orderId}")
    public void getInvoiceAsPDF(@PathVariable Long orderId,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        invoiceService.getInvoiceAsPDF(orderId, request, response);
    }

    @GetMapping("/proforma_invoice_pdf_address/{orderId}")
    public InvoiceAddress getInvoicePdfAddress(@PathVariable Long orderId) {
        return invoiceService.getProformaInvoicePdfAddress(orderId);
    }

    @GetMapping("/vat_invoice_pdf_address/{orderId}")
    public InvoiceAddress getVatPdfAddress(@PathVariable Long orderId) {
        return invoiceService.getVatInvoicePdfAddress(orderId);
    }

    @GetMapping("/invoice_by_order_id/{orderId}")
    public Invoice getInvoiceByOrderId(@PathVariable Long orderId) {
        return invoiceService.getInvoiceByOrderId(orderId);
    }

    @GetMapping("/invoice/all")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
