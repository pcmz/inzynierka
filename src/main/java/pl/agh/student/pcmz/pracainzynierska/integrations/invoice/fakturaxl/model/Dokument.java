package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;
import java.util.Objects;

import static pl.agh.student.pcmz.pracainzynierska.util.StringSanitizer.sanitizeDiacritics;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dokument {

    private String kod;
    private String dokumentId;
    private String dokumentNr;
    private String dokumnetNr;
    private String apiToken;
    private String typFaktury;
    private String numerFaktury;
    private String dataWystawienia;
    private String dataSprzedazy;
    private String terminPlatnosciData;
    private String status;
    private String unikatowyKod;
    private String dataOplacenia;
    private String kwotaOplacona;
    private String uwagi;
    private String waluta;
    private String kurs;
    private String rodzajPlatnosci;
    private String jezyk;
    private String imieNazwiskoWystawcy;
    private String imieNazwiskoOdbiorcy;
    private String nrZamowienia;
    private String dodatkoweUwagi;
    private String wartoscNetto;
    private String wartoscVat;
    private String wartoscBrutto;
    private String idDzialyFirmy;
    private String wyslijDokumentDoKlientaEmailem;
    private String magazynId;
    private Podmiot sprzedawca;
    private Podmiot nabywca;
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("faktura_pozycje")
    private List<FakturaPozycje> fakturaPozycjeList;

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = sanitizeDiacritics(kod);
    }

    public String getDokumentId() {
        return dokumentId;
    }

    public void setDokumentId(String dokumentId) {
        this.dokumentId = sanitizeDiacritics(dokumentId);
    }

    public String getDokumentNr() {
        return dokumentNr;
    }

    public void setDokumentNr(String dokumentNr) {
        this.dokumentNr = sanitizeDiacritics(dokumentNr);
    }

    public String getDokumnetNr() {
        return dokumnetNr;
    }

    public void setDokumnetNr(String dokumnetNr) {
        this.dokumnetNr = sanitizeDiacritics(dokumnetNr);
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getTypFaktury() {
        return typFaktury;
    }

    public void setTypFaktury(String typFaktury) {
        this.typFaktury = sanitizeDiacritics(typFaktury);
    }

    public String getNumerFaktury() {
        return numerFaktury;
    }

    public void setNumerFaktury(String numerFaktury) {
        this.numerFaktury = sanitizeDiacritics(numerFaktury);
    }

    public String getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(String dataWystawienia) {
        this.dataWystawienia = sanitizeDiacritics(dataWystawienia);
    }

    public String getDataSprzedazy() {
        return dataSprzedazy;
    }

    public void setDataSprzedazy(String dataSprzedazy) {
        this.dataSprzedazy = sanitizeDiacritics(dataSprzedazy);
    }

    public String getTerminPlatnosciData() {
        return terminPlatnosciData;
    }

    public void setTerminPlatnosciData(String terminPlatnosciData) {
        this.terminPlatnosciData = sanitizeDiacritics(terminPlatnosciData);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = sanitizeDiacritics(status);
    }

    public String getUnikatowyKod() {
        return unikatowyKod;
    }

    public void setUnikatowyKod(String unikatowyKod) {
        this.unikatowyKod = sanitizeDiacritics(unikatowyKod);
    }

    public String getDataOplacenia() {
        return dataOplacenia;
    }

    public void setDataOplacenia(String dataOplacenia) {
        this.dataOplacenia = sanitizeDiacritics(dataOplacenia);
    }

    public String getKwotaOplacona() {
        return kwotaOplacona;
    }

    public void setKwotaOplacona(String kwotaOplacona) {
        this.kwotaOplacona = sanitizeDiacritics(kwotaOplacona);
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = sanitizeDiacritics(uwagi);
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = sanitizeDiacritics(waluta);
    }

    public String getKurs() {
        return kurs;
    }

    public void setKurs(String kurs) {
        this.kurs = sanitizeDiacritics(kurs);
    }

    public String getRodzajPlatnosci() {
        return rodzajPlatnosci;
    }

    public void setRodzajPlatnosci(String rodzajPlatnosci) {
        this.rodzajPlatnosci = sanitizeDiacritics(rodzajPlatnosci);
    }

    public String getJezyk() {
        return jezyk;
    }

    public void setJezyk(String jezyk) {
        this.jezyk = sanitizeDiacritics(jezyk);
    }

    public String getImieNazwiskoWystawcy() {
        return imieNazwiskoWystawcy;
    }

    public void setImieNazwiskoWystawcy(String imieNazwiskoWystawcy) {
        this.imieNazwiskoWystawcy = sanitizeDiacritics(imieNazwiskoWystawcy);
    }

    public String getImieNazwiskoOdbiorcy() {
        return imieNazwiskoOdbiorcy;
    }

    public void setImieNazwiskoOdbiorcy(String imieNazwiskoOdbiorcy) {
        this.imieNazwiskoOdbiorcy = sanitizeDiacritics(imieNazwiskoOdbiorcy);
    }

    public String getNrZamowienia() {
        return nrZamowienia;
    }

    public void setNrZamowienia(String nrZamowienia) {
        this.nrZamowienia = sanitizeDiacritics(nrZamowienia);
    }

    public String getDodatkoweUwagi() {
        return dodatkoweUwagi;
    }

    public void setDodatkoweUwagi(String dodatkoweUwagi) {
        this.dodatkoweUwagi = sanitizeDiacritics(dodatkoweUwagi);
    }

    public String getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(String wartoscNetto) {
        this.wartoscNetto = sanitizeDiacritics(wartoscNetto);
    }

    public String getWartoscVat() {
        return wartoscVat;
    }

    public void setWartoscVat(String wartoscVat) {
        this.wartoscVat = sanitizeDiacritics(wartoscVat);
    }

    public String getWartoscBrutto() {
        return wartoscBrutto;
    }

    public void setWartoscBrutto(String wartoscBrutto) {
        this.wartoscBrutto = sanitizeDiacritics(wartoscBrutto);
    }

    public String getIdDzialyFirmy() {
        return idDzialyFirmy;
    }

    public void setIdDzialyFirmy(String idDzialyFirmy) {
        this.idDzialyFirmy = sanitizeDiacritics(idDzialyFirmy);
    }

    public String getWyslijDokumentDoKlientaEmailem() {
        return wyslijDokumentDoKlientaEmailem;
    }

    public void setWyslijDokumentDoKlientaEmailem(String wyslijDokumentDoKlientaEmailem) {
        this.wyslijDokumentDoKlientaEmailem = sanitizeDiacritics(wyslijDokumentDoKlientaEmailem);
    }

    public String getMagazynId() {
        return magazynId;
    }

    public void setMagazynId(String magazynId) {
        this.magazynId = sanitizeDiacritics(magazynId);
    }

    public Podmiot getSprzedawca() {
        return sprzedawca;
    }

    public void setSprzedawca(Podmiot sprzedawca) {
        this.sprzedawca = sprzedawca;
    }

    public Podmiot getNabywca() {
        return nabywca;
    }

    public void setNabywca(Podmiot nabywca) {
        this.nabywca = nabywca;
    }

    public List<FakturaPozycje> getFakturaPozycjeList() {
        return fakturaPozycjeList;
    }

    public void setFakturaPozycjeList(List<FakturaPozycje> fakturaPozycjeList) {
        this.fakturaPozycjeList = fakturaPozycjeList;
    }

    @Override
    public String toString() {
        return "Dokument{" +
                "kod='" + kod + '\'' +
                ", dokumentId='" + dokumentId + '\'' +
                ", dokumentNr='" + dokumentNr + '\'' +
                ", dokumnetNr='" + dokumnetNr + '\'' +
                ", apiToken='" + apiToken + '\'' +
                ", typFaktury='" + typFaktury + '\'' +
                ", numerFaktury='" + numerFaktury + '\'' +
                ", dataWystawienia='" + dataWystawienia + '\'' +
                ", dataSprzedazy='" + dataSprzedazy + '\'' +
                ", terminPlatnosciData='" + terminPlatnosciData + '\'' +
                ", status='" + status + '\'' +
                ", unikatowyKod='" + unikatowyKod + '\'' +
                ", dataOplacenia='" + dataOplacenia + '\'' +
                ", kwotaOplacona='" + kwotaOplacona + '\'' +
                ", uwagi='" + uwagi + '\'' +
                ", waluta='" + waluta + '\'' +
                ", kurs='" + kurs + '\'' +
                ", rodzajPlatnosci='" + rodzajPlatnosci + '\'' +
                ", jezyk='" + jezyk + '\'' +
                ", imieNazwiskoWystawcy='" + imieNazwiskoWystawcy + '\'' +
                ", imieNazwiskoOdbiorcy='" + imieNazwiskoOdbiorcy + '\'' +
                ", nrZamowienia='" + nrZamowienia + '\'' +
                ", dodatkoweUwagi='" + dodatkoweUwagi + '\'' +
                ", wartoscNetto='" + wartoscNetto + '\'' +
                ", wartoscVat='" + wartoscVat + '\'' +
                ", wartoscBrutto='" + wartoscBrutto + '\'' +
                ", idDzialyFirmy='" + idDzialyFirmy + '\'' +
                ", wyslijDokumentDoKlientaEmailem='" + wyslijDokumentDoKlientaEmailem + '\'' +
                ", magazynId='" + magazynId + '\'' +
                ", sprzedawca=" + sprzedawca +
                ", nabywca=" + nabywca +
                ", fakturaPozycjeList=" + fakturaPozycjeList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dokument)) return false;
        Dokument dokument = (Dokument) o;
        return Objects.equals(dokumentId, dokument.dokumentId) &&
                Objects.equals(dokumentNr, dokument.dokumentNr) &&
                Objects.equals(dokumnetNr, dokument.dokumnetNr) &&
                Objects.equals(apiToken, dokument.apiToken) &&
                Objects.equals(typFaktury, dokument.typFaktury) &&
                Objects.equals(numerFaktury, dokument.numerFaktury) &&
                Objects.equals(dataWystawienia, dokument.dataWystawienia) &&
                Objects.equals(dataSprzedazy, dokument.dataSprzedazy) &&
                Objects.equals(terminPlatnosciData, dokument.terminPlatnosciData) &&
                Objects.equals(status, dokument.status) &&
                Objects.equals(unikatowyKod, dokument.unikatowyKod) &&
                Objects.equals(dataOplacenia, dokument.dataOplacenia) &&
                Objects.equals(kwotaOplacona, dokument.kwotaOplacona) &&
                Objects.equals(uwagi, dokument.uwagi) &&
                Objects.equals(waluta, dokument.waluta) &&
                Objects.equals(kurs, dokument.kurs) &&
                Objects.equals(rodzajPlatnosci, dokument.rodzajPlatnosci) &&
                Objects.equals(jezyk, dokument.jezyk) &&
                Objects.equals(imieNazwiskoWystawcy, dokument.imieNazwiskoWystawcy) &&
                Objects.equals(imieNazwiskoOdbiorcy, dokument.imieNazwiskoOdbiorcy) &&
                Objects.equals(nrZamowienia, dokument.nrZamowienia) &&
                Objects.equals(dodatkoweUwagi, dokument.dodatkoweUwagi) &&
                Objects.equals(wartoscNetto, dokument.wartoscNetto) &&
                Objects.equals(wartoscVat, dokument.wartoscVat) &&
                Objects.equals(wartoscBrutto, dokument.wartoscBrutto) &&
                Objects.equals(idDzialyFirmy, dokument.idDzialyFirmy) &&
                Objects.equals(wyslijDokumentDoKlientaEmailem, dokument.wyslijDokumentDoKlientaEmailem) &&
                Objects.equals(magazynId, dokument.magazynId) &&
                Objects.equals(sprzedawca, dokument.sprzedawca) &&
                Objects.equals(nabywca, dokument.nabywca) &&
                Objects.equals(fakturaPozycjeList, dokument.fakturaPozycjeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dokumentId, dokumentNr, dokumnetNr, apiToken, typFaktury, numerFaktury, dataWystawienia, dataSprzedazy, terminPlatnosciData, status, unikatowyKod, dataOplacenia, kwotaOplacona, uwagi, waluta, kurs, rodzajPlatnosci, jezyk, imieNazwiskoWystawcy, imieNazwiskoOdbiorcy, nrZamowienia, dodatkoweUwagi, wartoscNetto, wartoscVat, wartoscBrutto, idDzialyFirmy, wyslijDokumentDoKlientaEmailem, magazynId, sprzedawca, nabywca, fakturaPozycjeList);
    }
}
