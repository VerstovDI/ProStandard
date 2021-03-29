package ru.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;

import static ru.selenide.Methods.*;


public class App {
    public static void setUp() {
        //  Configuration.downloadsFolder = downloadDir;
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        //   chromePrefs.put("profile.default_content_settings.popups", 0);
        //  chromePrefs.put("download.prompt_for_download", "true");
        chromePrefs.put("safebrowsing.enabled", "true");
        String downloadDir = Paths.get("resources").toAbsolutePath().toString();
        // System.out.println(Configuration.downloadsFolder);
        //TODO здесь менять директорию для загрузки при каждом запуске
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

    public static void main(String[] args) throws FileNotFoundException {

        setUp();
        openProfStandardsFilter();
        findProfStandardByNumber("06.003");
        downloadOpenedProfStandard();


        //showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
