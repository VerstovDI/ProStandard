package ru.prostandard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prostandard.model.competence_model.CompetenceModel;
import ru.prostandard.model.competence_model.SummaryTable;
import ru.prostandard.model.competence_model.tcl.ProfessionalTaskType;
import ru.prostandard.repository.ProfessionalTaskTypeRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class GenerationExcelService {
    private final ProfessionalTaskTypeRepository professionalTaskTypeRepository;
    @Transactional
    public void generate(CompetenceModel competenceModel) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ПК");
        sheet.setColumnWidth(0, 44 * 256);
        sheet.setColumnWidth(1, 95 * 256);

        CellStyle arial14 = workbook.createCellStyle();
        XSSFFont fontArial14 = ((XSSFWorkbook) workbook).createFont();
        fontArial14.setFontName("Arial");
        fontArial14.setFontHeightInPoints((short) 14);
        arial14.setFont(fontArial14);

        Cell specialisation = sheet.createRow(0).createCell(0);
        specialisation.setCellValue(competenceModel.getSpecialization().getSpecializationCode());
        specialisation.setCellStyle(arial14);


        Cell name = sheet.createRow(1).createCell(0);
        name.setCellValue("Обязательные профессиональные компетенции");
        name.setCellStyle(arial14);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
////
        CellStyle arial9Bold = workbook.createCellStyle();
        XSSFFont fontArial9Bold = ((XSSFWorkbook) workbook).createFont();
        fontArial9Bold.setFontName("Arial");
        fontArial9Bold.setFontHeightInPoints((short) 9);
        fontArial9Bold.setBold(true);
        arial9Bold.setFont(fontArial9Bold);
        ///
        Cell columnNameLeft = sheet.createRow(2).createCell(0);
        columnNameLeft.setCellValue("Код и наименование профессиональной компетенции");
        columnNameLeft.setCellStyle(arial9Bold);

        Cell columnNameRight = sheet.createRow(2).createCell(1);
        columnNameRight.setCellValue("Код и наименование индикатора достижения профессиональной компетенции");
        columnNameRight.setCellStyle(arial9Bold);

        CellStyle arial11Bold = workbook.createCellStyle();
        XSSFFont fontArial11Bold = ((XSSFWorkbook) workbook).createFont();
        fontArial11Bold.setFontName("Arial");
        fontArial11Bold.setFontHeightInPoints((short) 11);
        fontArial11Bold.setBold(true);
        arial11Bold.setFont(fontArial11Bold);


        List<ProfessionalTaskType> professionalTaskTypes = professionalTaskTypeRepository.findAll();
        List<SummaryTable> summaryTables = competenceModel.getSummaryTables();

        Optional<ProfessionalTaskType> optionalProfessionalTaskType1 = professionalTaskTypes.stream()
                .filter(t -> t.getTypeDescription().equalsIgnoreCase("научно-исследовательский")).findAny();
        if (optionalProfessionalTaskType1.isEmpty()) {
            log.error("В базе отсутствет Тип задачи профессиональной деятельности: научно-исследовательский");
            return;
        }

        Cell columnNameType1 = sheet.createRow(3).createCell(0);
        columnNameType1.setCellValue("Тип задачи профессиональной деятельности: научно-исследовательский");
        columnNameType1.setCellStyle(arial11Bold);
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));

        CellStyle arial11 = workbook.createCellStyle();
        XSSFFont fontArial11 = ((XSSFWorkbook) workbook).createFont();
        fontArial11.setFontName("Arial");
        fontArial11.setFontHeightInPoints((short) 11);
        arial11.setFont(fontArial11);

        Collection<SummaryTable> summaryTables1 = summaryTables.stream()
                .filter(t -> t.getProfessionalTaskType().getTaskTypeId().equals(optionalProfessionalTaskType1.get().getTaskTypeId())).collect(Collectors.toSet());
        int currentRow=4;
        currentRow=writeData(sheet, arial11, summaryTables1, currentRow);

        Optional<ProfessionalTaskType> optionalProfessionalTaskType2 = professionalTaskTypes.stream()
                .filter(t -> t.getTypeDescription().equalsIgnoreCase("организационно-управленческий")).findAny();
        if (optionalProfessionalTaskType2.isEmpty()) {
            log.error("В базе отсутствет Тип задачи профессиональной деятельности: организационно-управленческий");
            return;
        }
        Collection<SummaryTable> summaryTables2 = summaryTables.stream()
                .filter(t -> t.getProfessionalTaskType().getTaskTypeId().equals(optionalProfessionalTaskType2.get().getTaskTypeId())).collect(Collectors.toSet());
        Cell columnNameType2 = sheet.createRow(currentRow).createCell(0);
        columnNameType2.setCellValue("Тип задачи профессиональной деятельности: научно-исследовательский");
        columnNameType2.setCellStyle(arial11Bold);
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 1));
        currentRow++;
        currentRow=writeData(sheet, arial11, summaryTables2, currentRow);

        Optional<ProfessionalTaskType> optionalProfessionalTaskType3 = professionalTaskTypes.stream()
                .filter(t -> t.getTypeDescription().equalsIgnoreCase("проектный")).findAny();
        if (optionalProfessionalTaskType3.isEmpty()) {
            log.error("В базе отсутствет Тип задачи профессиональной деятельности: проектный");
            return;
        }
        Collection<SummaryTable> summaryTables3 = summaryTables.stream()
                .filter(t -> t.getProfessionalTaskType().getTaskTypeId().equals(optionalProfessionalTaskType3.get().getTaskTypeId())).collect(Collectors.toSet());
        Cell columnNameType3 = sheet.createRow(currentRow).createCell(0);
        columnNameType3.setCellValue("Тип задачи профессиональной деятельности: проектный");
        columnNameType3.setCellStyle(arial11Bold);
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 1));
        currentRow++;
        currentRow=writeData(sheet, arial11, summaryTables3, currentRow);

        String pathToSave = Paths.get("resources").toAbsolutePath()//TODO -сделать потом выборным в методе
                + File.separator + "excels" + File.separator + "км " + competenceModel.getSpecialization().getSpecializationCode();

        try (FileOutputStream outputStream = new FileOutputStream(pathToSave)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int writeData(Sheet sheet, CellStyle cellStyle, Collection<SummaryTable> summaryTables, int currentRow) {
        if(summaryTables.isEmpty()){
            return currentRow;
        }
        for(SummaryTable summaryTable: summaryTables){
            Cell leftCell = sheet.createRow(currentRow).createCell(0);
            leftCell.setCellValue(summaryTable.getEducationalCompetence().getCompetenceName());
            leftCell.setCellStyle(cellStyle);

            Cell rightCellKnowledge = sheet.createRow(currentRow).createCell(1);
            rightCellKnowledge.setCellValue("З-ПК-"+(currentRow -3)+" знать: "+summaryTable.getNecessaryKnowledge().getDescription());
            rightCellKnowledge.setCellStyle(cellStyle);

            Cell rightCellSkill = sheet.createRow(currentRow +1).createCell(1);
            rightCellSkill.setCellValue("У-ПК-"+(currentRow -3)+" уметь: "+summaryTable.getRequiredSkill().getDescription());
            rightCellSkill.setCellStyle(cellStyle);

            Cell rightCellLaborAction = sheet.createRow(currentRow +2).createCell(1);
            rightCellLaborAction.setCellValue("В-ПК-"+(currentRow -3)+" владеть навыками: "+summaryTable.getLaborAction().getDescription());
            rightCellLaborAction.setCellStyle(cellStyle);

            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow +2, 0, 0));
            currentRow+=3;
        }
        return currentRow;
    }
}
