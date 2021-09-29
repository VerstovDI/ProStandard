package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;


public class App {


    public static void main(String[] args) throws InterruptedException {

        String dirToSave = Paths.get("resources").toAbsolutePath().toString();

        for (int i = 9; i <= 110; i++) { // парсим стандарты 24-й категории
            String numberToParse = "24." + String.format("%03d", i);
            GetAndParseProffStandard.getAndParseStandard(numberToParse, dirToSave);
        }

    }


}
