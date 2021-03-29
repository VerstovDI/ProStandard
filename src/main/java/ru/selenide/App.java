package ru.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static ru.selenide.Methods.*;


public class App {
    public static void setUp() {
        var downloadDir = Paths.get("resources").toAbsolutePath().toString();
        System.out.println(File.separator);
        Configuration.downloadsFolder=downloadDir;
        System.out.println( Configuration.downloadsFolder);
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
     //   chromePrefs.put("profile.default_content_settings.popups", 0);
      //  chromePrefs.put("download.prompt_for_download", "true");
        chromePrefs.put("safebrowsing.enabled", "true");
        chromePrefs.put("download.default_directory",   downloadDir);
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
        downloadProfStandardByNumber();


        //showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
