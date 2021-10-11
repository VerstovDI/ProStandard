package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;


public class Thread1 extends Thread {
    @Override
    public void run() {
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();
        parse("01.", 11, dirToSave);
        parse("02.", 87, dirToSave);
        parse("03.", 15, dirToSave);

        parse("04.", 15, dirToSave);
        parse("05.", 14, dirToSave);
        parse("06.", 46, dirToSave);

        parse("07.", 13, dirToSave);
        parse("09.", 3, dirToSave);
        parse("10.", 15, dirToSave);

        parse("11.", 17, dirToSave);
    }

    public static void parse(String standardCategory, int endNumber, String dirToSave) {
        for (int i = 1; i <= endNumber; i++) { //
            String numberToParse = standardCategory + String.format("%03d", i);
            GetAndParseProffStandard.getAndParseStandard(numberToParse, dirToSave);
        }
    }

}
