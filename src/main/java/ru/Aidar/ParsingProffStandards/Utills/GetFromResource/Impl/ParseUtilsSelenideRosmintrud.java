package ru.Aidar.ParsingProffStandards.Utills.GetFromResource.Impl;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.Aidar.ParsingProffStandards.Utills.GetFromResource.IParseUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
        Configuration.timeout = 120000;
        Configuration.pageLoadTimeout = 120000;
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName); ){
            Properties properties = new Properties();
            properties.load(inputStream);
            Configuration.holdBrowserOpen = Boolean.parseBoolean(properties.getProperty("Configuration.holdBrowserOpen"));
            Configuration.headless = Boolean.parseBoolean(properties.getProperty("Configuration.headless"));// не открывать браузер
        }
        catch (IOException io) {
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
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName); ){
            Properties properties = new Properties();
            properties.load(inputStream);
            relativeOrAbsoluteUrlOpenProfStandardsFilter = properties.getProperty("relativeOrAbsoluteUrlOpenProfStandardsFilter");
            classNameOpenProfStandardsFilter = properties.getProperty("classNameOpenProfStandardsFilter");
        }
        catch (IOException io) {
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
     */
    public void findProfStandardByNumber(String number) {
        String cssSelectorUrlFindProfStandardByNumber;
        String findByCssSelectorUrlFindProfStandardByNumber;
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName); ){
            Properties properties = new Properties();
            properties.load(inputStream);
            cssSelectorUrlFindProfStandardByNumber = properties.getProperty("cssSelectorUrlFindProfStandardByNumber");
            findByCssSelectorUrlFindProfStandardByNumber = properties.getProperty("findByCssSelectorUrlFindProfStandardByNumber");
        }
        catch (IOException io) {
            log.error(io);
            log.error("Error Finish!!!");
            return;
        }
        $(cssSelectorUrlFindProfStandardByNumber).setValue(number).pressEnter();
        //Selenide.sleep(3000);
        SelenideElement element = $$(findByCssSelectorUrlFindProfStandardByNumber).findBy(Condition.text(number));
        element.doubleClick();

    }

    /**
     * Скачивает открытый профф стандарт
     */
    public void downloadOpenedProfStandard() {
        //  String path = $("input[type='submit'][class='button'][value='Скачать в XML']").download().getPath();
        $("input[type='submit'][class='button'][value='Скачать в XML']").click();
    }
}
