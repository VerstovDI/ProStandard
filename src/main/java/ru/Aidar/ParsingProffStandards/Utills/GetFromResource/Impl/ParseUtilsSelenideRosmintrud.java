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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.codeborne.selenide.Selenide.*;

public class ParseUtilsSelenideRosmintrud implements IParseUtils {
    private static final Logger log = Logger.getLogger(ParseUtilsSelenideRosmintrud.class);

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
            log.error("Finish!!!");
            return;
        }
        chromePrefs.put("download.default_directory", downloadDir);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        Configuration.browserCapabilities = options;
        Configuration.timeout = 120000;
        Configuration.pageLoadTimeout = 120000;
        Configuration.holdBrowserOpen = true;
        //Configuration.headless = true;// не открывать браузер
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

    /**
     * На сайте мин труда открывает фильтр профф стандартов
     */
    public void openProfStandardsFilter() {
        open("https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok" +
                "/natsionalnyy-reestr-professionalnykh-standartov/reestr-professionalnykh-standartov");
        $(new By.ByClassName("ps-hide-show")).pressEnter();
    }

    /**
     * Ищет профф стандарт по его номеру
     *
     * @param number номер профф стандарта
     */
    public void findProfStandardByNumber(String number) {
        $("input[type='text'][name='KPF']").setValue(number).pressEnter();
        //Selenide.sleep(3000);
        SelenideElement element = $$("a").findBy(Condition.text(number));
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
