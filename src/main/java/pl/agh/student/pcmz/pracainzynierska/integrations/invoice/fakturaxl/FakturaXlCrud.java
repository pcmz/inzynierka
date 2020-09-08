package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl;

import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.InvoiceIntegrationController;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.InvoiceIntegrationDocument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlUtils.*;

public class FakturaXlCrud implements InvoiceIntegrationController {

    @Override
    public final InvoiceIntegrationDocument getInvoice(String id) {
        Dokument document = new Dokument();
        document.setDokumentId(id);
        document.setApiToken(API_TOKEN);
        return makeHttpCall("/api/dokument_odczytaj.php", document);
    }

    @Override
    public final Dokument createProformaInvoice(InvoiceIntegrationDocument document) {
        enrichDokument((Dokument) document);
        document.setTypFaktury(TYP_FAKTURY_PROFORMA);
        document.setStatus(STATUS_NIEOPLACONA);
        return createInvoice(document);
    }

    public final Dokument createVatInvoice(InvoiceIntegrationDocument document) {
        document.setTypFaktury(TYP_FAKTURY_VAT);
        document.setStatus(STATUS_OPLACONA);
        document.setUwagi("Faktura VAT do faktury Proforma nr " + document.getNumerFaktury());
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        document.setDataWystawienia(today);
        document.setDataOplacenia(today);
        document.setTerminPlatnosciData(today);
        document.setKwotaOplacona(document.getWartoscBrutto());
        document.setKod(null);
        document.setNumerFaktury(null);
        document.setIdDzialyFirmy(ID_DZIALY_FIRMY);
        document.getNabywca().setKraj(KRAJ);
        document.setRodzajPlatnosci(RODZAJ_PLATNOSCI);
        return createInvoice(document);
    }

    @Override
    public final InvoiceIntegrationDocument promoteProformaInvoiceIntoVat(String proformaInvoiceId) {
        return createVatInvoice(getInvoice(proformaInvoiceId));
    }

    public final Dokument createInvoice(InvoiceIntegrationDocument document) {
        document.setApiToken(API_TOKEN);
        return makeHttpCall("/api/dokument_dodaj.php", (Dokument) document);
    }
}
