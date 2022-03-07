package ru.Aidar.ParsingProffStandards.Parsing.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static ru.Aidar.ParsingProffStandards.Utills.ParseXMLUtils.*;


public class ParseXML implements IParseXML {
    private static final Logger log = LogManager.getLogger(ParseXML.class);
    private static final String propFileName = "config.properties";

    @Override
    public void parse(String path) {
        log.info("Запуск разбора XML");

        log.debug("Создание DAO");
        ResourceDAO resourceDAO = new ResourceDAO();
        StandardsDAO standardsDAO = new StandardsDAO();
        try {
            log.debug("Запуск загрузки XML в ОП");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            log.debug("Поиск и установка ресурса в БД");
            int idOfResource1;
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
                Properties properties = new Properties();
                properties.load(inputStream);
                idOfResource1 = Integer.parseInt(properties.getProperty("idOfResource1"));
            } catch (IOException io) {
                log.error(io);
                log.error("Error Finish!!!");
                return;
            }

            String codeKindProfessionalActivity = document.getDocumentElement()
                    .getElementsByTagName("CodeKindProfessionalActivity")
                    .item(0).getTextContent();
            log.info("Код стандарта " + codeKindProfessionalActivity);
            List<Standard> standardsByKindProfessionalActivity = standardsDAO.findByCodeKindProfessionalActivity(codeKindProfessionalActivity);

            Optional<Standard> newestOptionalStandardFromDB = standardsByKindProfessionalActivity.stream()
                    .max(Comparator.comparing(Standard::getDateOfApproval));

            if (newestOptionalStandardFromDB.isPresent()
                    && !newestOptionalStandardFromDB.get().getDateOfApproval().before(getStandardDateOfApproval(document))) {
                log.info("стандарт " + codeKindProfessionalActivity + " не является новым, уже существует в базе");
                return;
            }

            Resource resource = resourceDAO.findById(idOfResource1);
            Standard standard = getStandard(document, resource);
            standardsDAO.save(standard);

            GeneralizedWorkFunctionsDAO generalizedWorkFunctionsDAO = new GeneralizedWorkFunctionsDAO();
            PossibleJobTitlesDAO possibleJobTitlesDAO = new PossibleJobTitlesDAO();
            EducationalRequirementsDAO educationalRequirementsDAO = new EducationalRequirementsDAO();
            ParticularWorkFunctionsDAO particularWorkFunctionsDAO = new ParticularWorkFunctionsDAO();
            LaborActionsDAO laborActionsDAO = new LaborActionsDAO();
            RequiredSkillsDAO requiredSkillsDAO = new RequiredSkillsDAO();
            NecessaryKnowledgeDAO necessaryKnowledgeDAO = new NecessaryKnowledgeDAO();
            EmploymentGroupOkvedDAO employmentGroupOkvedDAO = new EmploymentGroupOkvedDAO();
            EmploymentGroupOkzDAO employmentGroupOkzDAO = new EmploymentGroupOkzDAO();

            log.debug("getAndSaveEmploymentGroupOKZ");
            getAndSaveEmploymentGroupOKZ(document, standard, employmentGroupOkzDAO, employmentGroupOkvedDAO);

            log.debug("getAndSaveEmploymentGroupOkved");
            getAndSaveEmploymentGroupOkved(document, standard, employmentGroupOkvedDAO);

            log.debug("getAndSaveGeneralizedWorkFunctions");
            getAndSaveGeneralizedWorkFunctions(document, standard, generalizedWorkFunctionsDAO, possibleJobTitlesDAO, educationalRequirementsDAO, particularWorkFunctionsDAO, laborActionsDAO, requiredSkillsDAO, necessaryKnowledgeDAO);

            log.info("Окончание провесса для № " + codeKindProfessionalActivity);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error(e);
        }
    }
}
