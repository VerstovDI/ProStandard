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

/**
 * Главный класс. Его метод отвечает за все.
 */
public class GetAndParseProfStandard {
    private static final Logger log = Logger.getLogger(GetAndParseProfStandard.class);
    private final static IParseXML iParseXML = new ParseXML();
    private static final IParseUtils iParseUtils = new ParseUtilsSelenideRosmintrud();

    /**
     * Поиск. Скачивание. Разбор стандарта
     * Если стандарт не существует в базе, или если в базе представлена старая версия стандарта, то сохраняется
     * Иначе отбой
     */
    public static void getAndParseStandard(String number, String dirToSave) {
        log.info("начало поиска стандарта № " + number);
        String pathToSave = dirToSave + File.separator + number;
        try {
            iParseUtils.setUp(pathToSave);
            iParseUtils.openProfStandardsFilter();
            if (iParseUtils.findProfStandardByNumber(number) && iParseUtils.downloadOpenedProfStandard()) {
                sleep(1000);
                Optional<Path> lastFilePath = getLastFilePath(pathToSave);
                if (lastFilePath.isPresent()) {
                    Path savedPath = lastFilePath.get();
                    iParseXML.parse(String.valueOf(savedPath));
                } else {
                    log.error("не удалось");
                }
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            WebDriverRunner.getWebDriver().close();
            WebDriverRunner.getWebDriver().quit();
        }
    }
}
