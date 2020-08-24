package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.FakturaXlUtils.*;

public class FakturaXlCrd {

    private static final XmlMapper mapper = new XmlMapper();

    public static final Dokument getInvoice(String id) throws IOException {
        Dokument dokument = new Dokument();
        dokument.setDokumentId(id);
        dokument.setApiToken(API_TOKEN);
        String reguestParams = mapper.writeValueAsString(dokument);
        String invoiceAsString = makeHttpCall("/api/dokument_odczytaj.php", "GET", reguestParams);
        return mapper.readValue(invoiceAsString, Dokument.class);
    }

    public static final Dokument createInvoice(Dokument dokument) throws IOException {
        dokument.setApiToken(API_TOKEN);
        String reguestParams = mapper.writeValueAsString(dokument);
        String invoiceAsString = makeHttpCall("/api/dokument_dodaj.php", "GET", reguestParams);
        return mapper.readValue(invoiceAsString, Dokument.class);
    }

    public static final String getInvoiceAsPDF(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Dokument dokument = new Dokument();
        dokument.setDokumentId(id);
        dokument.setApiToken(API_TOKEN);
        return makeHttpCallForInvoicePDF("/dokument_export.php?api=" + API_TOKEN + "&dokument_id=" + id + "&pdf=1", request, response);
    }
}
