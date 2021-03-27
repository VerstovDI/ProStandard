package ru.selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Methods {

    public static void userCanSearchKeywordWithGoogle() {
        open("https://google.com/ncr");
        $(new By.ByName("q")).setValue("selenide").pressEnter();
        $$("#ires li.g").shouldHaveSize(10);
        $("#ires li.g").shouldHave(Condition.text("selenide"));
    }

    public static void openProfStandardsFilter(){
        open("https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-professionalnykh-standartov");
        $(new By.ByClassName("ps-hide-show")).pressEnter();
    }

    //set value and find
    public static void findProfStandardByNumber(String number){
         $("input[type='text'][name='arrFilter_ff[CODE]']").setValue(number).pressEnter();
    }

    //show all standards in this area, you can write only number of are, for example 06
    public static void showAllProfStandardsByProfAreaFromRosmintrud(String profArea){
        $("[name='arrFilter_pf[RANGE_PROFACT]']")
                .selectOptionContainingText(profArea);
        $("input[type='submit'][name='set_filter']").pressEnter();
    }
}
