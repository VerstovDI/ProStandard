package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;

public class Thread3 extends Thread {
    public static void parse(String standardCategory, int endNumber, String dirToSave) {
        for (int i = 1; i <= endNumber; i++) { //
            String numberToParse = standardCategory + String.format("%03d", i);
            Main.getAndParseStandard(numberToParse, dirToSave);
        }
    }

    @Override
    public void run() {
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();

        parse("23.", 59, dirToSave);
        parse("24.", 110, dirToSave);
        parse("25.", 60, dirToSave);
        parse("26.", 33, dirToSave);
        parse("27.", 107, dirToSave);
        parse("28.", 11, dirToSave);
        parse("29.", 17, dirToSave);

        parse("30.", 30, dirToSave);
        parse("31.", 21, dirToSave);

        parse("32.", 16, dirToSave);
        parse("33.", 23, dirToSave);
        parse("40.", 226, dirToSave);
    }
}
