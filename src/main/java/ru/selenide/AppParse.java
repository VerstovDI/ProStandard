package ru.selenide;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.selenide.Utills.FileUtills.getLastFilePath;
import static ru.selenide.ParseXML.parse;

public class AppParse {
    public static void main(String[] args) throws IOException {

        String number = "06.003";
        String pathToSave = Paths.get("resources").toAbsolutePath().toString()
                + File.separator + number;
        Path path = getLastFilePath(pathToSave).get();
        //parse(Path.of("c:\\Users\\VAR\\Downloads\\ProfessionalStandarts_1055.xml"));
        parse(path);

    }
}
