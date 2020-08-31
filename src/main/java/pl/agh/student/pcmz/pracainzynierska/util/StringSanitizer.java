package pl.agh.student.pcmz.pracainzynierska.util;

public class StringSanitizer {

    public static final String diacriticsa = "ą";
    public static final String simplifieda = "a";
    public static final String diacriticsA = "Ą";
    public static final String simplifiedA = "A";
    public static final String diacriticsc = "ć";
    public static final String simplifiedc = "c";
    public static final String diacriticsC = "Ć";
    public static final String simplifiedC = "C";
    public static final String diacriticse = "ę";
    public static final String simplifiede = "e";
    public static final String diacriticsE = "Ę";
    public static final String simplifiedE = "E";
    public static final String diacriticsl = "ł";
    public static final String simplifiedl = "l";
    public static final String diacriticsL = "Ł";
    public static final String simplifiedL = "L";
    public static final String diacriticsn = "ń";
    public static final String simplifiedn = "n";
    public static final String diacriticsN = "Ń";
    public static final String simplifiedN = "N";
    public static final String diacriticso = "ó";
    public static final String simplifiedo = "o";
    public static final String diacriticsO = "Ó";
    public static final String simplifiedO = "O";
    public static final String diacriticss = "ś";
    public static final String simplifieds = "s";
    public static final String diacriticsS = "Ś";
    public static final String simplifiedS = "S";
    public static final String diacriticszi = "ź";
    public static final String simplifiedzi = "z";
    public static final String diacriticsZi = "Ź";
    public static final String simplifiedZi = "Z";
    public static final String diacriticsz = "ż";
    public static final String simplifiedz = "z";
    public static final String diacriticsZ = "Ż";
    public static final String simplifiedZ = "Z";

    public static final String sanitizeDiacritics(String original) {
        return original == null ? null : original.replaceAll(diacriticsa, simplifieda)
                .replaceAll(diacriticsA, simplifiedA)
                .replaceAll(diacriticsc, simplifiedc)
                .replaceAll(diacriticsC, simplifiedC)
                .replaceAll(diacriticse, simplifiede)
                .replaceAll(diacriticsE, simplifiedE)
                .replaceAll(diacriticsl, simplifiedl)
                .replaceAll(diacriticsL, simplifiedL)
                .replaceAll(diacriticsn, simplifiedn)
                .replaceAll(diacriticsN, simplifiedN)
                .replaceAll(diacriticso, simplifiedo)
                .replaceAll(diacriticsO, simplifiedO)
                .replaceAll(diacriticss, simplifieds)
                .replaceAll(diacriticsS, simplifiedS)
                .replaceAll(diacriticszi, simplifiedzi)
                .replaceAll(diacriticsZi, simplifiedZi)
                .replaceAll(diacriticsz, simplifiedz)
                .replaceAll(diacriticsZ, simplifiedZ);
    }
}
