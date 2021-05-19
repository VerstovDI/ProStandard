package ru.selenide.Utills;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.selenide.DB.DAO.*;
import ru.selenide.DB.domain.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ParseXMLUtills {
    public static void getAndSaveGeneralizedWorkFunctions(Document document, Standard standard, GeneralizedWorkFunctionsDAO generalizedWorkFunctionsDAO, PossibleJobTitlesDAO possibleJobTitlesDAO, EducationalRequirementsDAO educationalRequirementsDAO, ParticularWorkFunctionsDAO particularWorkFunctionsDAO, LaborActionsDAO laborActionsDAO, RequiredSkillsDAO requiredSkillsDAO, NecessaryKnowledgeDAO necessaryKnowledgeDAO) {
        NodeList generalizedWorkFunctions = document.getDocumentElement().getElementsByTagName("GeneralizedWorkFunction");
        //перебор обобщенных трудовых функций
        for (int i = 0; i < generalizedWorkFunctions.getLength(); i++) {
            NodeList generalizedWorkFunctionChildNodes = generalizedWorkFunctions.item(i).getChildNodes();
            if (generalizedWorkFunctionChildNodes != null) {
                GeneralizedWorkFunction generalizedWorkFunction = new GeneralizedWorkFunction(generalizedWorkFunctionChildNodes.item(0).getTextContent(),
                        generalizedWorkFunctionChildNodes.item(1).getTextContent(), Integer.parseInt(generalizedWorkFunctionChildNodes.item(2).getTextContent())
                        , standard);
                generalizedWorkFunctionsDAO.save(generalizedWorkFunction);
                getAndSavePossibleJobTitles(possibleJobTitlesDAO, generalizedWorkFunctionChildNodes, generalizedWorkFunction);
                getAndSaveEducationalRequirements(educationalRequirementsDAO, generalizedWorkFunctionChildNodes, generalizedWorkFunction);
                getAndSaveParticularWorkFunctions(particularWorkFunctionsDAO, laborActionsDAO, requiredSkillsDAO, necessaryKnowledgeDAO, generalizedWorkFunctionChildNodes, generalizedWorkFunction);
            }
        }
    }

    public static void getAndSaveParticularWorkFunctions(ParticularWorkFunctionsDAO particularWorkFunctionsDAO, LaborActionsDAO laborActionsDAO, RequiredSkillsDAO requiredSkillsDAO, NecessaryKnowledgeDAO necessaryKnowledgeDAO, NodeList generalizedWorkFunctionChildNodes, GeneralizedWorkFunction generalizedWorkFunction) {
        NodeList particularWorkFunctions = generalizedWorkFunctionChildNodes.item(9).getChildNodes();
        if (particularWorkFunctions != null) {
            for (int j = 0; j < particularWorkFunctions.getLength(); j++) {
                NodeList particularWorkFunctionNodes = particularWorkFunctions.item(j).getChildNodes();
                if (particularWorkFunctionNodes != null) {
                    ParticularWorkFunction particularWorkFunction = new ParticularWorkFunction(particularWorkFunctionNodes.item(0).getTextContent(),
                            particularWorkFunctionNodes.item(1).getTextContent(),
                            Integer.parseInt(particularWorkFunctionNodes.item(2).getTextContent())
                            , generalizedWorkFunction);
                    particularWorkFunctionsDAO.save(particularWorkFunction);
                    getAndSaveLaborActions(laborActionsDAO, particularWorkFunctionNodes, particularWorkFunction);
                    getAndSaveRequiredSkills(requiredSkillsDAO, particularWorkFunctionNodes, particularWorkFunction);
                    getAndSaveNecessaryKnowledgeList(necessaryKnowledgeDAO, particularWorkFunctionNodes, particularWorkFunction);
                }
            }
        }
    }

    public static void getAndSaveNecessaryKnowledgeList(NecessaryKnowledgeDAO necessaryKnowledgeDAO, NodeList particularWorkFunctionNodes, ParticularWorkFunction particularWorkFunction) {
        NodeList necessaryKnowledgeList = particularWorkFunctionNodes.item(5).getChildNodes();
        if (necessaryKnowledgeList != null) {
            for (int necessaryKnowledgeListCount = 0; necessaryKnowledgeListCount < necessaryKnowledgeList.getLength(); necessaryKnowledgeListCount++) {
                NecessaryKnowledge necessaryKnowledge = new NecessaryKnowledge(necessaryKnowledgeList.item(necessaryKnowledgeListCount).getTextContent(), particularWorkFunction);
                necessaryKnowledgeDAO.save(necessaryKnowledge);
            }
        }
    }

    public static void getAndSaveRequiredSkills(RequiredSkillsDAO requiredSkillsDAO, NodeList particularWorkFunctionNodes, ParticularWorkFunction particularWorkFunction) {
        NodeList requiredSkills = particularWorkFunctionNodes.item(4).getChildNodes();
        if (requiredSkills != null) {
            for (int requiredSkillsCount = 0; requiredSkillsCount < requiredSkills.getLength(); requiredSkillsCount++) {
                RequiredSkill requiredSkill = new RequiredSkill(requiredSkills.item(requiredSkillsCount).getTextContent(), particularWorkFunction);
                requiredSkillsDAO.save(requiredSkill);
            }
        }
    }

    public static void getAndSaveLaborActions(LaborActionsDAO laborActionsDAO, NodeList particularWorkFunctionNodes, ParticularWorkFunction particularWorkFunction) {
        NodeList laborActions = particularWorkFunctionNodes.item(3).getChildNodes();
        if (laborActions != null) {
            for (int laborActionsCount = 0; laborActionsCount < laborActions.getLength(); laborActionsCount++) {
                LaborAction laborAction = new LaborAction(laborActions.item(laborActionsCount).getTextContent(), particularWorkFunction);
                laborActionsDAO.save(laborAction);
            }
        }
    }

    public static void getAndSaveEducationalRequirements(EducationalRequirementsDAO educationalRequirementsDAO, NodeList generalizedWorkFunctionChildNodes, GeneralizedWorkFunction generalizedWorkFunction) {
        NodeList educationalRequirements = generalizedWorkFunctionChildNodes.item(4).getChildNodes();
        if (educationalRequirements != null) {
            for (int j = 0; j < educationalRequirements.getLength(); j++) {
                EducationalRequirement educationalRequirement = new EducationalRequirement(educationalRequirements.item(j).getTextContent(), generalizedWorkFunction);
                educationalRequirementsDAO.save(educationalRequirement);
            }
        }
    }

    public static void getAndSavePossibleJobTitles(PossibleJobTitlesDAO possibleJobTitlesDAO, NodeList generalizedWorkFunctionChildNodes, GeneralizedWorkFunction generalizedWorkFunction) {
        NodeList possibleJobTitles = generalizedWorkFunctionChildNodes.item(3).getChildNodes();
        if (possibleJobTitles != null) {
            for (int j = 0; j < possibleJobTitles.getLength(); j++) {
                PossibleJobTitle possibleJobTitle = new PossibleJobTitle(possibleJobTitles.item(j).getTextContent(), generalizedWorkFunction);
                possibleJobTitlesDAO.save(possibleJobTitle);
            }
        }
    }

    public static void getAndSaveEmploymentGroupOkved(Document document, Standard standard, EmploymentGroupOkvedDAO employmentGroupOkvedDAO) {
        Node employmentGroupListOKVED = document.getDocumentElement().getElementsByTagName("EmploymentGroup").item(0).getChildNodes().item(1);
        if (employmentGroupListOKVED != null) {
            NodeList childNodes = employmentGroupListOKVED.getChildNodes();
            if (childNodes != null) {
                for (int i = 0; i < childNodes.getLength(); i++) {
                    EmploymentGroupOkved employmentGroupOkved = new EmploymentGroupOkved(childNodes.item(i).getChildNodes().item(0).getTextContent()
                            , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                    employmentGroupOkvedDAO.save(employmentGroupOkved);
                }
            }
        }
    }

    public static void getAndSaveEmploymentGroupOKZ(Document document, Standard standard,EmploymentGroupOkzDAO employmentGroupOkzDAO,EmploymentGroupOkvedDAO employmentGroupOkvedDAO ) {
        Node employmentGroupListOKZ = document.getDocumentElement().getElementsByTagName("EmploymentGroup").item(0).getChildNodes().item(0);
        if (employmentGroupListOKZ != null && employmentGroupListOKZ.getNodeName().equals("ListOKZ")) {

            NodeList childNodes = employmentGroupListOKZ.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                EmploymentGroupOkz employmentGroupOkz = new EmploymentGroupOkz(Integer.valueOf(childNodes.item(i).getChildNodes()
                        .item(0).getTextContent().replaceAll("\\.", ""))
                        , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                employmentGroupOkzDAO.save(employmentGroupOkz);
            }
        } else if (employmentGroupListOKZ != null && employmentGroupListOKZ.getNodeName().equals("ListOKVED")) {

            NodeList childNodes = employmentGroupListOKZ.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                EmploymentGroupOkved employmentGroupOkved = new EmploymentGroupOkved(childNodes.item(i).getChildNodes().item(0).getTextContent()
                        , childNodes.item(i).getChildNodes().item(1).getTextContent(), standard);
                employmentGroupOkvedDAO.save(employmentGroupOkved);
            }
        }
    }

    public static Standard getStandard(Document document, Resource resource) {
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
