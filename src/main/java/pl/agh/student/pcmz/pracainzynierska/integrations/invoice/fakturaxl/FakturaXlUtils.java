package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Podmiot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakturaXlUtils {

    public static final String API_TOKEN = "5hkOiYF3f1MfCEVwZ2s6hEjQdkrXNDgw2qj7QZyZiLL4Y7RJUD8UYqxEL8peq5yjA6SHx5i6QjdHVG9k";
    public static final String HOSTNAME = "https://program.fakturaxl.pl";
    public static final String INVOICE_AS_PDF = HOSTNAME + "/dokument_export.php?api=" + API_TOKEN + "&dokument_id=";
    public static final String DOWNLOAD_PDF = "&pdf=1";
    public static final String OPEN_PDF = "&pdf=2";
    public static final String TYP_FAKTURY_VAT = "0";
    public static final String TYP_FAKTURY_PROFORMA = "1";
    public static final String STATUS_NIEOPLACONA = "0";
    public static final String STATUS_OPLACONA = "2";
    public static final String COMPANY_NAME = "przyszli inzynierowie";
    public static final String COMPANY_NIP = "675-00-01-923";
    public static final String COMPANY_STREET = "ul. Krakowska 987";
    public static final String COMPANY_POST_CODE = "30-383";
    public static final String COMPANY_CITY = "Krakow";
    public static final String COMPANY_BANK_ACCOUNT = "00-0000-0000-0000-0000-0000-0000-0000";
    public static final String ID_DZIALY_FIRMY = "41360";
    public static final String KRAJ = "PL";
    public static final String RODZAJ_PLATNOSCI = "Przelew";
    public static final String PLN = "PLN";
    public static final String JEZYK = "0";
    public static final List<String> IMIONA_I_NAZWISKA = Arrays.asList("Dominik Z", "Sylwester M", "Bartosz C");

    private static final XmlMapper mapper = new XmlMapper();

    static final Dokument makeHttpCall(String uri, Dokument dokument) throws IOException {
        String parameters = mapper.writeValueAsString(dokument);
        HttpURLConnection con = (HttpURLConnection) new URL(HOSTNAME + uri).openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes("xmlRequest=" + parameters);
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return mapper.readValue(content.toString(), Dokument.class);
    }

    static void enrichDokument(Dokument dokument) {
        String dateOfIssueAndSale = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dokument.setDataWystawienia(dateOfIssueAndSale);
        dokument.setDataSprzedazy(dateOfIssueAndSale);
        dokument.setTerminPlatnosciData(LocalDateTime.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dokument.setWaluta(PLN);
        dokument.setJezyk(JEZYK);
        dokument.setIdDzialyFirmy(ID_DZIALY_FIRMY);
        dokument.setRodzajPlatnosci(RODZAJ_PLATNOSCI);
        dokument.setImieNazwiskoWystawcy(FakturaXlUtils.getRandomImieINazwiskoWystawcy());

        Podmiot sprzedawca = new Podmiot();
        sprzedawca.setNazwa(COMPANY_NAME);
        sprzedawca.setNip(COMPANY_NIP);
        sprzedawca.setUlicaINumer(COMPANY_STREET);
        sprzedawca.setKodPocztowy(COMPANY_POST_CODE);
        sprzedawca.setMiejscowosc(COMPANY_CITY);
        sprzedawca.setNrKontaBankowego(COMPANY_BANK_ACCOUNT);
        sprzedawca.setKraj(KRAJ);
        dokument.setSprzedawca(sprzedawca);
    }

    private static String getRandomImieINazwiskoWystawcy() {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        if (randomDouble < 0.3) {
            return IMIONA_I_NAZWISKA.get(0);
        } else if (randomDouble < 0.6) {
            return IMIONA_I_NAZWISKA.get(1);
        } else {
            return IMIONA_I_NAZWISKA.get(2);
        }
    }
}
