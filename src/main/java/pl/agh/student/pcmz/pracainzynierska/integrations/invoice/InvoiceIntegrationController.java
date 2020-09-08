package pl.agh.student.pcmz.pracainzynierska.integrations.invoice;

public interface InvoiceIntegrationController {

    InvoiceIntegrationDocument getInvoice(String id);

    InvoiceIntegrationDocument createProformaInvoice(InvoiceIntegrationDocument document);

    InvoiceIntegrationDocument createVatInvoice(InvoiceIntegrationDocument document);

    InvoiceIntegrationDocument promoteProformaInvoiceIntoVat(String proformaInvoiceId);

    InvoiceIntegrationDocument createInvoice(InvoiceIntegrationDocument document);
}
