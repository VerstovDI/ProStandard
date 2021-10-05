package ru.Aidar.ParsingProffStandards.Utills.GetFromResource.Impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.TimeoutException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.Aidar.ParsingProffStandards.Utills.GetFromResource.IParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;

public class ParseUtilsSelenideRosmintrud implements IParseUtils {
    private static final Logger log = Logger.getLogger(ParseUtilsSelenideRosmintrud.class);
    private static final String propFileName = "config.properties";

    /**
     * Показывает все стандарты в профф сйере. Можно написать, например 06
     *
     * @param profArea проффессиональная сфера
     */
    public static void showAllProfStandardsByProfAreaFromRosmintrud(String profArea) {
        $("[name='arrFilter_pf[RANGE_PROFACT]']")
                .selectOptionContainingText(profArea);
        $("input[type='submit'][name='set_filter']").pressEnter();
    }

    /**
     * Метод для настройки драйвера перед скачиванием.
     *
     * @param downloadDir папка для срохранения профф стандарта
     */
    public void setUp(String downloadDir) {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("safebrowsing.enabled", "true");
        try {
            Files.createDirectories(Paths.get(downloadDir));
        } catch (IOException e) {
            log.error(e);
            log.error("Error Finish!!!");
            return;
        }
        chromePrefs.put("download.default_directory", downloadDir);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        Configuration.browserCapabilities = options;

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Configuration.timeout = Integer.parseInt(properties.getProperty("Configuration.timeout"));
            Configuration.pageLoadTimeout = Integer.parseInt(properties.getProperty("Configuration.pageLoadTimeout"));
            Configuration.holdBrowserOpen = Boolean.parseBoolean(properties.getProperty("Configuration.holdBrowserOpen"));
            Configuration.headless = Boolean.parseBoolean(properties.getProperty("Configuration.headless"));// не открывать браузер
        } catch (IOException io) {
            log.error(io);
            log.error("Error Finish!!!");
            return;
        }
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

    /**
     * На сайте мин труда открывает фильтр профф стандартов
     */
    public void openProfStandardsFilter() {
        String relativeOrAbsoluteUrlOpenProfStandardsFilter;
        String classNameOpenProfStandardsFilter;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            relativeOrAbsoluteUrlOpenProfStandardsFilter = properties.getProperty("relativeOrAbsoluteUrlOpenProfStandardsFilter");
            classNameOpenProfStandardsFilter = properties.getProperty("classNameOpenProfStandardsFilter");
        } catch (IOException io) {
            log.error(io);
            log.error("Error Finish!!!");
            return;
        }
        open(relativeOrAbsoluteUrlOpenProfStandardsFilter);
        $(new By.ByClassName(classNameOpenProfStandardsFilter)).pressEnter();
    }

    /**
     * Ищет профф стандарт по его номеру
     *
     * @param number номер профф стандарта
     * @return
     */
    public boolean findProfStandardByNumber(String number) {
        String cssSelectorUrlFindProfStandardByNumber;
        String findByCssSelectorUrlFindProfStandardByNumber;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            cssSelectorUrlFindProfStandardByNumber = properties.getProperty("cssSelectorUrlFindProfStandardByNumber");
            findByCssSelectorUrlFindProfStandardByNumber = properties.getProperty("findByCssSelectorUrlFindProfStandardByNumber");
        } catch (IOException io) {
            log.error(io);
            log.error("Error Finish!!!");
            return false;
        }
        try {
            $(cssSelectorUrlFindProfStandardByNumber).setValue(number).pressEnter();
        } catch (TimeoutException timeoutException) {
            log.error(timeoutException);
            log.error("Error Finish!!!");
            return false;
        }
        //Selenide.sleep(3000);
        try {
            SelenideElement element = $$(findByCssSelectorUrlFindProfStandardByNumber).findBy(Condition.text(number));
            element.doubleClick();
        } catch (ElementNotFound ex) {
            log.error("Can't find standard " + number + " SKIP", ex);
            return false;
        }
        return true;
    }

    /**
     * Скачивает открытый профф стандарт
     *
     * @return
     */
    public boolean downloadOpenedProfStandard() {
        //  String path = $("input[type='submit'][class='button'][value='Скачать в XML']").download().getPath();
        try {
            $("input[type='submit'][class='button'][value='Скачать в XML']").click();
        } catch (NoSuchElementException noSuchElementException) {
            log.error(noSuchElementException);
            return false;
        }
        return true;
    }
}
