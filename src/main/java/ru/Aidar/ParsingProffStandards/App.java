package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;


public class App {


    public static void main(String[] args) throws InterruptedException {
        String number1 = "06.021";
        String number2 = "06.022";

        String number4 = "06.024";
        String number5 = "06.025";
        String number6 = "06.026";
        String number7 = "06.027";
        String number8 = "06.028";
        String number9 = "06.029";
        String number10 = "06.010";
        String number11 = "06.011";
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();
        GetAndParseProffStandard.getAndParseStandard(number2, dirToSave);
     /*   getAndParseStandard(number2, dirToSave);
        getAndParseStandard(number4, dirToSave);
        getAndParseStandard(number5, dirToSave);
        getAndParseStandard(number6, dirToSave);
        getAndParseStandard(number7, dirToSave);

        getAndParseStandard(number8, dirToSave);
        getAndParseStandard(number9, dirToSave);
        getAndParseStandard(number10, dirToSave);
        getAndParseStandard(number11, dirToSave);

      */

    }


}
