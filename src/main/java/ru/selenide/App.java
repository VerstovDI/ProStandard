package ru.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;

import static ru.selenide.Methods.*;


public class App {
    public static void main(String[] args) {

        Configuration.pageLoadTimeout = 120000;
        Configuration.holdBrowserOpen = true;
        Configuration.fileDownload = FileDownloadMode.FOLDER;

        openProfStandardsFilter();
        findProfStandardByNumber("06.003");
        downloadProfStandardByNumber();
        //showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
