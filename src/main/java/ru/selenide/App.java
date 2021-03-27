package ru.selenide;

import com.codeborne.selenide.Configuration;

import static ru.selenide.Methods.*;
import static ru.selenide.Methods.userCanSearchKeywordWithGoogle;

public class App {
    public static void main(String[] args) {
        //  userCanSearchKeywordWithGoogle();
        Configuration.pageLoadTimeout = 120000;
        Configuration.holdBrowserOpen = true;
        openProfStandardsFilter();
      //  findProfStandardByNumber("06.003");
        showAllProfStandardsByProfAreaFromRosmintrud("06");
    }
}
