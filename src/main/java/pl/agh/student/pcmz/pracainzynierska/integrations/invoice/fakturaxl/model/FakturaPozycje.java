package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FakturaPozycje {
    private String nazwa;
    private String pkwiu;
    private String rabat;
    private String ilosc;
    private String jm;
    private String netto;
    private String brutto;
    private String vat;
    private String wartoscNetto;
    private String wartoscBrutto;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getPkwiu() {
        return pkwiu;
    }

    public void setPkwiu(String pkwiu) {
        this.pkwiu = pkwiu;
    }

    public String getRabat() {
        return rabat;
    }

    public void setRabat(String rabat) {
        this.rabat = rabat;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getJm() {
        return jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    public String getNetto() {
        return netto;
    }

    public void setNetto(String netto) {
        this.netto = netto;
    }

    public String getBrutto() {
        return brutto;
    }

    public void setBrutto(String brutto) {
        this.brutto = brutto;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getWartoscNetto() {
        return wartoscNetto;
    }

    public void setWartoscNetto(String wartoscNetto) {
        this.wartoscNetto = wartoscNetto;
    }

    public String getWartoscBrutto() {
        return wartoscBrutto;
    }

    public void setWartoscBrutto(String wartoscBrutto) {
        this.wartoscBrutto = wartoscBrutto;
    }

    @Override
    public String toString() {
        return "FakturaPozycje{" +
                "nazwa='" + nazwa + '\'' +
                ", pkwiu='" + pkwiu + '\'' +
                ", rabat='" + rabat + '\'' +
                ", ilosc='" + ilosc + '\'' +
                ", jm='" + jm + '\'' +
                ", netto='" + netto + '\'' +
                ", brutto='" + brutto + '\'' +
                ", vat='" + vat + '\'' +
                ", wartoscNetto='" + wartoscNetto + '\'' +
                ", wartoscBrutto='" + wartoscBrutto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FakturaPozycje that = (FakturaPozycje) o;
        return Objects.equals(nazwa, that.nazwa) &&
                Objects.equals(pkwiu, that.pkwiu) &&
                Objects.equals(rabat, that.rabat) &&
                Objects.equals(ilosc, that.ilosc) &&
                Objects.equals(jm, that.jm) &&
                Objects.equals(netto, that.netto) &&
                Objects.equals(brutto, that.brutto) &&
                Objects.equals(vat, that.vat) &&
                Objects.equals(wartoscNetto, that.wartoscNetto) &&
                Objects.equals(wartoscBrutto, that.wartoscBrutto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, pkwiu, rabat, ilosc, jm, netto, brutto, vat, wartoscNetto, wartoscBrutto);
    }
}
