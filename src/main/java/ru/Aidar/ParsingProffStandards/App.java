package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;

public class App {

    public static void main(String[] args) throws InterruptedException {
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();
        String numberToParse = "01." + String.format("%03d", 1);
        GetAndParseProfStandard.getAndParseStandard(numberToParse, dirToSave);
       /*
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        Thread thread3 = new Thread1();

        thread1.start();
        thread2.start();
        thread3.start();

         */
    }


}
