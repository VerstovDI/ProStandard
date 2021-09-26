package ru.Aidar.ParsingProffStandards.Utills.GetFromResource;

public interface IParseUtils {
    void setUp(String downloadDir);

    void openProfStandardsFilter();

    void findProfStandardByNumber(String number);

    void downloadOpenedProfStandard();

}
