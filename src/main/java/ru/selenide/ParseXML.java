package ru.selenide;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;
import ru.selenide.DB.DAO.*;
import ru.selenide.DB.domain.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

import static ru.selenide.Utills.ParseXMLUtills.*;


public class ParseXML {
    public static void parse(Path path) {
        try {
            ResourceDAO resourceDAO = new ResourceDAO();
            StandardsDAO standardsDAO = new StandardsDAO();
            GeneralizedWorkFunctionsDAO generalizedWorkFunctionsDAO = new GeneralizedWorkFunctionsDAO();
            PossibleJobTitlesDAO possibleJobTitlesDAO = new PossibleJobTitlesDAO();
            EducationalRequirementsDAO educationalRequirementsDAO = new EducationalRequirementsDAO();
            ParticularWorkFunctionsDAO particularWorkFunctionsDAO = new ParticularWorkFunctionsDAO();
            LaborActionsDAO laborActionsDAO = new LaborActionsDAO();
            RequiredSkillsDAO requiredSkillsDAO = new RequiredSkillsDAO();
            NecessaryKnowledgeDAO necessaryKnowledgeDAO = new NecessaryKnowledgeDAO();
            EmploymentGroupOkvedDAO employmentGroupOkvedDAO = new EmploymentGroupOkvedDAO();
            EmploymentGroupOkzDAO employmentGroupOkzDAO = new EmploymentGroupOkzDAO();

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path.toString());

            Resource resource = resourceDAO.findById(1);
            Standard standard = getStandard(document, resource);
            standardsDAO.save(standard);

            getAndSaveEmploymentGroupOKZ(document, standard,employmentGroupOkzDAO,employmentGroupOkvedDAO);
            getAndSaveEmploymentGroupOkved(document, standard,employmentGroupOkvedDAO);
            getAndSaveGeneralizedWorkFunctions(document, standard, generalizedWorkFunctionsDAO, possibleJobTitlesDAO, educationalRequirementsDAO, particularWorkFunctionsDAO, laborActionsDAO, requiredSkillsDAO, necessaryKnowledgeDAO);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }



}
