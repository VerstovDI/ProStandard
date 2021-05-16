package ru.selenide;

import ru.selenide.DB.DAO.ResourceDAO;
import ru.selenide.DB.DAO.StandardsDAO;
import ru.selenide.DB.domain.Resource;
import ru.selenide.DB.domain.Standard;

import java.sql.Date;


public class AppDB {
    public static void main(String[] args) {
        ResourceDAO resourceDAO= new ResourceDAO();
        Resource  resource = resourceDAO.findById(1);
        System.out.println(resource);
        Standard standard = new Standard("111", Date.valueOf("2020-01-10"), Date.valueOf("2021-05-10"),
                "name",123,
                "orderNumb","kind","purpose",resource);
        StandardsDAO standardsDAO = new StandardsDAO();
        standardsDAO.save(standard);
        System.out.println(standardsDAO.findByNameProfessionalStandart("name"));
        System.out.println(standardsDAO.findByCodeKindProfessionalActivity("111"));
    }
}
