package ru.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Methods {

    public static void openProfStandardsFilter(){
        open("https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-professionalnykh-standartov");
        $(new By.ByClassName("ps-hide-show")).pressEnter();
    }

    //set value and find
    public static void findProfStandardByNumber(String number){
         $("input[type='text'][name='arrFilter_ff[CODE]']").setValue(number).pressEnter();
        //Selenide.sleep(3000);
        SelenideElement element= $$("a").findBy(Condition.text(number));
        element.doubleClick();

    }
    public static void downloadProfStandardByNumber() throws FileNotFoundException {
      //  String path = $("input[type='submit'][class='button'][value='Скачать в XML']").download().getPath();
        $("input[type='submit'][class='button'][value='Скачать в XML']").click();
       // System.out.println(path);
    //    closeWebDriver();
    }
    //show all standards in this area, you can write only number of are, for example 06
    public static void showAllProfStandardsByProfAreaFromRosmintrud(String profArea){
        $("[name='arrFilter_pf[RANGE_PROFACT]']")
                .selectOptionContainingText(profArea);
        $("input[type='submit'][name='set_filter']").pressEnter();
    }
}
