package ru.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.lang.Thread.sleep;
import static ru.selenide.FilesMethods.getLastFilePath;
import static ru.selenide.Methods.*;


public class App {
    public static void setUp(String downloadDir) throws IOException {
        //  Configuration.downloadsFolder = downloadDir;
        HashMap<String, Object> chromePrefs = new HashMap<>();
        //   chromePrefs.put("profile.default_content_settings.popups", 0);
        //  chromePrefs.put("download.prompt_for_download", "true");
        chromePrefs.put("safebrowsing.enabled", "true");
        //String downloadDir = Paths.get("resources").toAbsolutePath().toString();
        // System.out.println(Configuration.downloadsFolder);
        //TODO здесь менять директорию для загрузки при каждом запуске
        Files.createDirectories(Paths.get(downloadDir));
        chromePrefs.put("download.default_directory", downloadDir);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
     /*   DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
               */
        Configuration.browserCapabilities = options;
        Configuration.timeout = 120000;
        Configuration.pageLoadTimeout = 120000;
        Configuration.holdBrowserOpen = true;
        //Configuration.headless = true;
        Configuration.fileDownload = FileDownloadMode.FOLDER;
    }

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
        System.out.println(getLastFilePath(pathToSave));
        //showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
