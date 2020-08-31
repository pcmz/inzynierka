package pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl;

import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Dokument;
import pl.agh.student.pcmz.pracainzynierska.integrations.invoice.fakturaxl.model.Podmiot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    static final String makeHttpCall(String uri, String method, String parameters) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(HOSTNAME + uri).openConnection();
        con.setRequestMethod(method);
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
        return content.toString();
    }

    static final String makeHttpCallForInvoicePDF(String uri, HttpServletRequest request, HttpServletResponse response) {
        // TODO: fix if needed
        StringBuilder content = new StringBuilder("");
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(HOSTNAME + uri).openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);

            response.setHeader("Pragma", "public");
            response.setHeader("responseType", "blob");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "pdfFilename" + "\"");
            InputStream is = con.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            DataOutputStream dataOutputStream = new DataOutputStream(response.getOutputStream());
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                dataOutputStream.writeBytes(inputLine);
                content.append(inputLine);
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            is.close();
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    public static String getRandomImieINazwiskoWystawcy() {
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

    public static void enrichDokument(Dokument dokument) {
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
}
