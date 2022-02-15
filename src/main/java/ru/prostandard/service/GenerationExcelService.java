package ru.prostandard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.prostandard.controller.MainController;
import ru.prostandard.model.competence_model.CompetenceModel;
import ru.prostandard.model.competence_model.SummaryTable;
import ru.prostandard.model.competence_model.tcl.ProfessionalTaskType;
import ru.prostandard.repository.ProfessionalTaskTypeRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenerationExcelService {
    private final ProfessionalTaskTypeRepository professionalTaskTypeRepository;
    private final Logger logger = LoggerFactory.getLogger(GenerationExcelService.class);
    @Transactional
    public void generate(CompetenceModel competenceModel, String dirToSave) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ПК");
        setWidth(sheet);

        CellStyle arial14 = getArial(workbook, (short) 14);
        CellStyle arial9Bold = getArialBold(workbook, (short) 9);
        CellStyle arial11Bold = getArialBold(workbook, (short) 11);
        CellStyle arial11 = getArial(workbook, (short) 11);

        writeRowAndCell(sheet, arial14, 0, competenceModel.getSpecialization().getSpecializationCode());

        writeRowAndCell(sheet, arial14, 1, "Обязательные профессиональные компетенции");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));

        Row row2 = sheet.createRow(2);
        writeCell(arial9Bold, row2, 0, "Код и наименование профессиональной компетенции");

        writeCell(arial9Bold, row2, 1, "Код и наименование индикатора достижения профессиональной компетенции");

        List<ProfessionalTaskType> professionalTaskTypes = professionalTaskTypeRepository.findAll();
        List<SummaryTable> summaryTables = competenceModel.getSummaryTables();
        int currentRow = 3;
        int countNumber = 1;
        for (ProfessionalTaskType professionalTaskType : professionalTaskTypes) {
            Collection<SummaryTable> summaryTablesFiltered = summaryTables.stream()
                    .filter(t -> t.getProfessionalTaskType().getTaskTypeId().equals(professionalTaskType.getTaskTypeId())).collect(Collectors.toSet());

            currentRow = writeData(sheet, arial11, arial11Bold, summaryTablesFiltered, currentRow, professionalTaskType, countNumber);
            countNumber += summaryTablesFiltered.size();
        }

        String pathToSave = dirToSave + File.separator + "cm " + competenceModel.getSpecialization().getSpecializationCode() + ".xlsx";
        if (!Files.exists(Path.of(dirToSave))) {
            new File(dirToSave).mkdirs();
        }

        try (FileOutputStream outputStream = new FileOutputStream(pathToSave)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void writeCell(CellStyle style, Row row, int cellNumber, String cellValue) {
        Cell columnNameLeft = row.createCell(cellNumber);
        columnNameLeft.setCellValue(cellValue);
        columnNameLeft.setCellStyle(style);
    }

    private void writeRowAndCell(Sheet sheet, CellStyle style, int rowNumber, String specializationCode) {
        writeCell(style, sheet.createRow(rowNumber), 0, specializationCode);
    }

    private void setWidth(Sheet sheet) {
        sheet.setColumnWidth(0, 44 * 256);
        sheet.setColumnWidth(1, 95 * 256);
    }

    private CellStyle getArialBold(XSSFWorkbook workbook, short i) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints(i);
        font.setBold(true);
        style.setWrapText(true);
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle getArial(XSSFWorkbook workbook, short i) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints(i);
        style.setWrapText(true);
        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private int writeData(Sheet sheet, CellStyle cellStyle, CellStyle cellStyleBold, Collection<SummaryTable> summaryTables, int currentRow, ProfessionalTaskType professionalTaskType, int countNumber) {
        if (summaryTables.isEmpty()) {
            return currentRow;
        }
        writeRowAndCell(sheet, cellStyleBold, currentRow, "Тип задачи профессиональной деятельности: " + professionalTaskType.getTypeDescription());
        sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow, 0, 1));
        currentRow++;
        for (SummaryTable summaryTable : summaryTables) {
            Row row = sheet.createRow(currentRow);
            writeCell(cellStyle, row, 0,
                    "ПК-" + (countNumber) + " " + summaryTable.getEducationalCompetence().getCompetenceName());

            writeCell(cellStyle, row, 1,
                    "З-ПК-" + (countNumber) + " знать: " + summaryTable.getNecessaryKnowledge().getDescription());

            writeCell(cellStyle, sheet.createRow(currentRow + 1), 1,
                    "У-ПК-" + (countNumber) + " уметь: " + summaryTable.getRequiredSkill().getDescription());

            writeCell(cellStyle, sheet.createRow(currentRow + 2), 1,
                    "В-ПК-" + (countNumber) + " владеть навыками: " + summaryTable.getLaborAction().getDescription());

            sheet.addMergedRegion(new CellRangeAddress(currentRow, currentRow + 2, 0, 0));
            currentRow += 3;
            countNumber++;
        }
        return currentRow;
    }
}
