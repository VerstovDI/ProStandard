package ru.Aidar.ParsingProffStandards.Parsing.Impl;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.Aidar.ParsingProffStandards.DB.DAO.*;
import ru.Aidar.ParsingProffStandards.DB.domain.Resource;
import ru.Aidar.ParsingProffStandards.DB.domain.Standard;
import ru.Aidar.ParsingProffStandards.Parsing.IParseXML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static ru.Aidar.ParsingProffStandards.Utills.ParseXMLUtils.*;


public class ParseXML implements IParseXML {
    private static final Logger log = Logger.getLogger(ParseXML.class);
    private static final String propFileName = "config.properties";

    @Override
    public void parse(String path) {
        log.info("Запуск разбора XML");

        log.info("Создание DAO");
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
        try {
            log.info("Запуск загрузки XML в ОП");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            log.info("Поиск и установка ресурса в БД");
            Integer idOfResource1;
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);) {
                Properties properties = new Properties();
                properties.load(inputStream);
                idOfResource1 = Integer.valueOf(properties.getProperty("idOfResource1"));
            } catch (IOException io) {
                log.error(io);
                log.error("Error Finish!!!");
                return;
            }
            Resource resource = resourceDAO.findById(idOfResource1);
            Standard standard = getStandard(document, resource);
            standardsDAO.save(standard);
            log.info("getAndSaveEmploymentGroupOKZ");
            getAndSaveEmploymentGroupOKZ(document, standard, employmentGroupOkzDAO, employmentGroupOkvedDAO);
            log.info("getAndSaveEmploymentGroupOkved");
            getAndSaveEmploymentGroupOkved(document, standard, employmentGroupOkvedDAO);
            log.info("getAndSaveGeneralizedWorkFunctions");
            getAndSaveGeneralizedWorkFunctions(document, standard, generalizedWorkFunctionsDAO, possibleJobTitlesDAO, educationalRequirementsDAO, particularWorkFunctionsDAO, laborActionsDAO, requiredSkillsDAO, necessaryKnowledgeDAO);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e);
        }
    }
}
