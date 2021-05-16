package ru.selenide;

import com.codeborne.selenide.WebDriverRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static java.lang.Thread.sleep;
import static ru.selenide.FileUtills.getLastFilePath;
import static ru.selenide.SelenideUtills.*;


public class App {


    public static void main(String[] args) throws IOException, InterruptedException {
       String number = "06.003";
        String pathToSave = Paths.get("resources").toAbsolutePath().toString()
                + File.separator + number;
        setUp(pathToSave);
        openProfStandardsFilter();
        findProfStandardByNumber(number);
        downloadOpenedProfStandard();
        sleep(5000);
        WebDriverRunner.getWebDriver().quit();
        System.out.println(getLastFilePath(pathToSave).get());
        //showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
