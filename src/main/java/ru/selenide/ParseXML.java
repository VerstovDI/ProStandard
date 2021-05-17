package ru.selenide;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.selenide.DB.DAO.EmploymentGroupOkvedDAO;
import ru.selenide.DB.DAO.EmploymentGroupOkzDAO;
import ru.selenide.DB.DAO.ResourceDAO;
import ru.selenide.DB.DAO.StandardsDAO;
import ru.selenide.DB.domain.EmploymentGroupOkved;
import ru.selenide.DB.domain.EmploymentGroupOkz;
import ru.selenide.DB.domain.Resource;
import ru.selenide.DB.domain.Standard;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParseXML {
    public static void parse(Path path) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path.toString());
            ResourceDAO resourceDAO = new ResourceDAO();
            Resource resource = resourceDAO.findById(1);
            StandardsDAO standardsDAO = new StandardsDAO();
            Standard standard = getStandard(document, resource);
            standardsDAO.save(standard);
            saveEmploymentGroupOKZ(document, standard);

            saveEmploymentGroupOkved(document, standard);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void saveEmploymentGroupOkved(Document document, Standard standard) {
        Node employmentGroupListOKVED = document.getDocumentElement().getElementsByTagName("EmploymentGroup").item(0).getChildNodes().item(1);
        if (employmentGroupListOKVED != null) {
            EmploymentGroupOkvedDAO employmentGroupOkvedDAO = new EmploymentGroupOkvedDAO();
            NodeList childNodes = employmentGroupListOKVED.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                EmploymentGroupOkved employmentGroupOkved = new EmploymentGroupOkved(childNodes.item(i).getChildNodes().item(0).getTextContent()
                        , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                employmentGroupOkvedDAO.save(employmentGroupOkved);
            }
        }
    }

    private static void saveEmploymentGroupOKZ(Document document, Standard standard) {
        Node employmentGroupListOKZ = document.getDocumentElement().getElementsByTagName("EmploymentGroup").item(0).getChildNodes().item(0);
        if (employmentGroupListOKZ != null && employmentGroupListOKZ.getNodeName().equals("ListOKZ")) {
            EmploymentGroupOkzDAO employmentGroupOkzDAO = new EmploymentGroupOkzDAO();
            NodeList childNodes = employmentGroupListOKZ.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                EmploymentGroupOkz employmentGroupOkz = new EmploymentGroupOkz(Integer.valueOf(childNodes.item(i).getChildNodes()
                        .item(0).getTextContent().replaceAll("\\.", ""))
                        , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                employmentGroupOkzDAO.save(employmentGroupOkz);
            }
        } else if (employmentGroupListOKZ.getNodeName().equals("ListOKVED")) {
            EmploymentGroupOkvedDAO employmentGroupOkvedDAO = new EmploymentGroupOkvedDAO();
            NodeList childNodes = employmentGroupListOKZ.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                EmploymentGroupOkved employmentGroupOkved = new EmploymentGroupOkved(childNodes.item(i).getChildNodes().item(0).getTextContent()
                        , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                employmentGroupOkvedDAO.save(employmentGroupOkved);
            }
        }
    }

    private static Standard getStandard(Document document, Resource resource) {
        String nameProfessionalStandart = document.getDocumentElement().getElementsByTagName("NameProfessionalStandart")
                .item(0).getTextContent();
        Integer registrationNumber = Integer.valueOf(document.getDocumentElement().getElementsByTagName("RegistrationNumber")
                .item(0).getTextContent().replaceAll("\\.", ""));
        String orderNumber = document.getDocumentElement().getElementsByTagName("OrderNumber")
                .item(0).getTextContent();
        Date dateOfApproval = Date.valueOf(LocalDate.parse(document.getDocumentElement().getElementsByTagName("DateOfApproval")
                .item(0).getTextContent(), DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH)));
        String kindProfessionalActivity = document.getDocumentElement().getElementsByTagName("KindProfessionalActivity")
                .item(0).getTextContent();
        String codeKindProfessionalActivity = document.getDocumentElement().getElementsByTagName("CodeKindProfessionalActivity")
                .item(0).getTextContent();
        String purposeKindProfessionalActivity = document.getDocumentElement().getElementsByTagName("PurposeKindProfessionalActivity")
                .item(0).getTextContent().replaceAll("<br />", "");
        Date dateOfDownloading = Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        Standard standard = new Standard(codeKindProfessionalActivity, dateOfApproval, dateOfDownloading, nameProfessionalStandart
                , registrationNumber, orderNumber, kindProfessionalActivity, purposeKindProfessionalActivity, resource);
        return standard;
    }

}
