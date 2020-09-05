package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl;

import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlUtils.*;

public class FakturaXlCrud {

    public static final Dokument getInvoice(String id) throws IOException {
        Dokument dokument = new Dokument();
        dokument.setDokumentId(id);
        dokument.setApiToken(API_TOKEN);
        return makeHttpCall("/api/dokument_odczytaj.php", dokument);
    }

    public static final Dokument createProformaInvoice(Dokument dokument) throws IOException {
        enrichDokument(dokument);
        dokument.setTypFaktury(TYP_FAKTURY_PROFORMA);
        dokument.setStatus(STATUS_NIEOPLACONA);
        return createInvoice(dokument);
    }

    public static final Dokument createVatInvoice(Dokument dokument) throws IOException {
        dokument.setTypFaktury(TYP_FAKTURY_VAT);
        dokument.setStatus(STATUS_OPLACONA);
        dokument.setUwagi("Faktura VAT do faktury Proforma nr " + dokument.getNumerFaktury());
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dokument.setDataWystawienia(today);
        dokument.setDataOplacenia(today);
        dokument.setTerminPlatnosciData(today);
        dokument.setKwotaOplacona(dokument.getWartoscBrutto());
        dokument.setKod(null);
        dokument.setNumerFaktury(null);
        dokument.setIdDzialyFirmy(ID_DZIALY_FIRMY);
        dokument.getNabywca().setKraj(KRAJ);
        dokument.setRodzajPlatnosci(RODZAJ_PLATNOSCI);
        return createInvoice(dokument);
    }

    public static final Dokument promoteProformaInvoiceIntoVat(String proformaInvoiceId) throws IOException {
        return createVatInvoice(getInvoice(proformaInvoiceId));
    }

    public static final Dokument createInvoice(Dokument dokument) throws IOException {
        dokument.setApiToken(API_TOKEN);
        return makeHttpCall("/api/dokument_dodaj.php", dokument);
    }
}
