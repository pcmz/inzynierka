package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

import static pl.agh.student.pcmz.pracainzynierska.util.StringSanitizer.sanitizeDiacritics;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Podmiot {

    private String nazwa;
    private String nip;
    @JsonProperty("ulica_i_numer")
    private String ulicaINumer;
    private String kodPocztowy;
    private String miejscowosc;
    private String krajId;
    private String kraj;
    private String email;
    private String telefon;
    private String fax;
    private String www;
    private String nrKontaBankowego;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = sanitizeDiacritics(nazwa);
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = sanitizeDiacritics(nip);
    }

    public String getUlicaINumer() {
        return ulicaINumer;
    }

    public void setUlicaINumer(String ulicaINumer) {
        this.ulicaINumer = sanitizeDiacritics(ulicaINumer);
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = sanitizeDiacritics(kodPocztowy);
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = sanitizeDiacritics(miejscowosc);
    }

    public String getKrajId() {
        return krajId;
    }

    public void setKrajId(String krajId) {
        this.krajId = sanitizeDiacritics(krajId);
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = sanitizeDiacritics(kraj);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = sanitizeDiacritics(email);
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = sanitizeDiacritics(telefon);
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = sanitizeDiacritics(fax);
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = sanitizeDiacritics(www);
    }

    public String getNrKontaBankowego() {
        return nrKontaBankowego;
    }

    public void setNrKontaBankowego(String nrKontaBankowego) {
        this.nrKontaBankowego = sanitizeDiacritics(nrKontaBankowego);
    }

    @Override
    public String toString() {
        return "Podmiot{" +
                "nazwa='" + nazwa + '\'' +
                ", nip='" + nip + '\'' +
                ", ulicaINumer='" + ulicaINumer + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", krajId='" + krajId + '\'' +
                ", kraj='" + kraj + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", fax='" + fax + '\'' +
                ", www='" + www + '\'' +
                ", nrKontaBankowego='" + nrKontaBankowego + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podmiot podmiot = (Podmiot) o;
        return Objects.equals(nazwa, podmiot.nazwa) &&
                Objects.equals(nip, podmiot.nip) &&
                Objects.equals(ulicaINumer, podmiot.ulicaINumer) &&
                Objects.equals(kodPocztowy, podmiot.kodPocztowy) &&
                Objects.equals(miejscowosc, podmiot.miejscowosc) &&
                Objects.equals(krajId, podmiot.krajId) &&
                Objects.equals(kraj, podmiot.kraj) &&
                Objects.equals(email, podmiot.email) &&
                Objects.equals(telefon, podmiot.telefon) &&
                Objects.equals(fax, podmiot.fax) &&
                Objects.equals(www, podmiot.www) &&
                Objects.equals(nrKontaBankowego, podmiot.nrKontaBankowego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nazwa, nip, ulicaINumer, kodPocztowy, miejscowosc, krajId, kraj, email, telefon, fax, www, nrKontaBankowego);
    }
}
