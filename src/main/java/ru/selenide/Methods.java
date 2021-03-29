package ru.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;

public class Methods {

    public static void openProfStandardsFilter() {
        open("https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-professionalnykh-standartov");
        $(new By.ByClassName("ps-hide-show")).pressEnter();
    }

    //set value and find
    public static void findProfStandardByNumber(String number) {
        $("input[type='text'][name='arrFilter_ff[CODE]']").setValue(number).pressEnter();
        //Selenide.sleep(3000);
        SelenideElement element = $$("a").findBy(Condition.text(number));
        element.doubleClick();

    }

    //TODO переместить после скачивания, чтоб в папке ток 1 файл. или же знать имя файла - тоже легко
    public static void downloadOpenedProfStandard() throws FileNotFoundException {
        //  String path = $("input[type='submit'][class='button'][value='Скачать в XML']").download().getPath();
        $("input[type='submit'][class='button'][value='Скачать в XML']").click();
    }

    //show all standards in this area, you can write only number of are, for example 06
    public static void showAllProfStandardsByProfAreaFromRosmintrud(String profArea) {
        $("[name='arrFilter_pf[RANGE_PROFACT]']")
                .selectOptionContainingText(profArea);
        $("input[type='submit'][name='set_filter']").pressEnter();
    }
}
