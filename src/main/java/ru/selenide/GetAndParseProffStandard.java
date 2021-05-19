package ru.selenide;

import com.codeborne.selenide.WebDriverRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.Thread.sleep;
import static ru.selenide.Parsing.ParseXML.parse;
import static ru.selenide.Utills.FileUtills.getLastFilePath;
import static ru.selenide.Utills.SelenideUtills.*;

public class GetAndParseProffStandard {
    public static void getAndParseStandard(String number, String dirToSave) throws IOException, InterruptedException {
        String pathToSave = dirToSave + File.separator + number;
        setUp(pathToSave);
        openProfStandardsFilter();
        findProfStandardByNumber(number);
        downloadOpenedProfStandard();
        sleep(5000);
        WebDriverRunner.getWebDriver().close();
        WebDriverRunner.getWebDriver().quit();

        Path savedPath = getLastFilePath(pathToSave).get();
        parse(String.valueOf(savedPath));
    }
}
