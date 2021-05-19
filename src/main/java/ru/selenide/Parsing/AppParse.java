package ru.selenide.Parsing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.selenide.Utills.FileUtills.getLastFilePath;
import static ru.selenide.Parsing.ParseXML.parse;

public class AppParse {
    public static void main(String[] args) throws IOException {
        String number1 = "06.003";
        String number2 = "06.040";
        String pathToSave1 = Paths.get("resources").toAbsolutePath().toString()
                + File.separator + number1;
        String pathToSave2 = Paths.get("resources").toAbsolutePath().toString()
                + File.separator + number2;
        Path path1 = getLastFilePath(pathToSave1).get();
        parse(path1.toString());
        Path path2 = getLastFilePath(pathToSave2).get();
        parse(path2.toString());

    }
}
