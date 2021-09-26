package ru.Aidar.ParsingProffStandards;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.log4j.Logger;
import ru.Aidar.ParsingProffStandards.Parsing.IParseXML;
import ru.Aidar.ParsingProffStandards.Parsing.Impl.ParseXML;
import ru.Aidar.ParsingProffStandards.Utills.GetFromResource.IParseUtils;
import ru.Aidar.ParsingProffStandards.Utills.GetFromResource.Impl.ParseUtilsSelenideRosmintrud;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

import static java.lang.Thread.sleep;
import static ru.Aidar.ParsingProffStandards.Utills.FileUtils.getLastFilePath;

public class GetAndParseProffStandard {
    private static final Logger log = Logger.getLogger(GetAndParseProffStandard.class);
    private final static IParseXML iParseXML = new ParseXML();
    private static final IParseUtils iParseUtils = new ParseUtilsSelenideRosmintrud();

    public static void getAndParseStandard(String number, String dirToSave) throws InterruptedException {

        String pathToSave = dirToSave + File.separator + number;
        try {
            iParseUtils.setUp(pathToSave);
            iParseUtils.openProfStandardsFilter();
            iParseUtils.findProfStandardByNumber(number);
            iParseUtils.downloadOpenedProfStandard();
            sleep(5000);
        } catch (RuntimeException ex) {
            log.error(ex);
        } finally {
            WebDriverRunner.getWebDriver().close();
            WebDriverRunner.getWebDriver().quit();
        }
        Optional<Path> lastFilePath = getLastFilePath(pathToSave);
        if (lastFilePath.isPresent()) {
            Path savedPath = lastFilePath.get();
            iParseXML.parse(String.valueOf(savedPath));
        } else {
            log.error("не удалось");
        }


    }
}
