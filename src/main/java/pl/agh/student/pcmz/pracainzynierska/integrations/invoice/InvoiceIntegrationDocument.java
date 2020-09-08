package pl.agh.student.pcmz.pracainzynierska.integrations.invoice;

import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Podmiot;

public interface InvoiceIntegrationDocument {
    String getDokumentId();

    String getDokumnetNr();

    void setTypFaktury(String typFakturyProforma);

    void setStatus(String status);

    void setApiToken(String apiToken);

    String getNumerFaktury();

    void setUwagi(String uwagi);

    void setDataWystawienia(String dataWystawienia);

    void setDataOplacenia(String dataOplacenia);

    void setTerminPlatnosciData(String terminPlatnosciData);

    String getWartoscBrutto();

    void setKwotaOplacona(String kwotaOplacona);

    void setKod(String kod);

    void setNumerFaktury(String numerFaktury);

    void setIdDzialyFirmy(String idDzialyFirmy);

    Podmiot getNabywca();

    void setRodzajPlatnosci(String rodzajPlatnosci);

    void setDokumentId(String id);
}
