package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;

public class Thread2 extends Thread {
    @Override
    public void run() {
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();

        parse("12.", 12, dirToSave);
        parse("13.", 23, dirToSave);

        parse("14.", 14, dirToSave);
        parse("15.", 18, dirToSave);
        parse("16.", 153, dirToSave);
        parse("17.", 121, dirToSave);
        parse("18.", 8, dirToSave);
        parse("19.", 71, dirToSave);
        parse("20.", 47, dirToSave);
        parse("21.", 3, dirToSave);

        parse("22.", 9, dirToSave);
    }

    public static void parse(String standardCategory, int endNumber, String dirToSave) {
        for (int i = 1; i <= endNumber; i++) { //
            String numberToParse = standardCategory + String.format("%03d", i);
            GetAndParseProfStandard.getAndParseStandard(numberToParse, dirToSave);
        }
    }
}
