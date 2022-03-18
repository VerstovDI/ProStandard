package ru.prostandard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.prostandard.converter.StandardsConverter;
import ru.prostandard.model.dto.HelpInfoDTO;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.model.dto.standard.StandardDTO;
import ru.prostandard.service.DictionaryService;
import ru.prostandard.service.HelpService;
import ru.prostandard.service.intellisearch.IntelliSelectionService;
import ru.prostandard.utils.RequestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Контроллер, обрабатывающий запросы главной страницы приложения.
 * Отвечает за отправку и приём JSON с главного окна составления профстандарта.
 */
@RestController(value = "localhost:8081/")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    /**
     * Сервис для ведения справочной информации приложения
     */
    private final DictionaryService dictionaryService;

    /**
     * Сервис для показа всей справочной информации приложения
     */
    private final HelpService helpService;

    /**
     * Сервис для взаимодействия с функционалом подбора профстандартов
     */
    private final IntelliSelectionService intelliSelectionService;

    /**
     * Конвертер модели стандарта в DTO стандарта
     */
    private final StandardsConverter standardsConverter;

    @Autowired
    public MainController(DictionaryService dictionaryService, HelpService helpService,
                          IntelliSelectionService intelliSelectionService, StandardsConverter standardsConverter) {
        this.dictionaryService = dictionaryService;
        this.helpService = helpService;
        this.intelliSelectionService = intelliSelectionService;
        this.standardsConverter = standardsConverter;
    }

    /**
     * GET-запрос с отправкой данных на получения списка всех подобранных стандартов
     *
     * @return Список подобранных стандартов
     */
    @GetMapping(value = "/standards")
    @ResponseBody
    public ResponseEntity<List<StandardDTO>> getStandards(@RequestParam String educationLevel,
                                                          @RequestParam String specializationCode,
                                                          @RequestParam String subjMajor,
                                                          @RequestParam String resourceToDownload,
                                                          @RequestParam String keywords) {
        try {
            logger.info("Отправка данных с формы выдачи стандартов начата...");
            List<String> keywordsList = RequestUtils.prepareKeywordsStringToList(keywords);
            SearchDTO searchDTO = new SearchDTO(educationLevel, specializationCode, subjMajor, resourceToDownload, keywordsList);
            List<StandardDTO> standardDTOList = standardsConverter.convertToDTO(intelliSelectionService.getProfstandards(searchDTO));
           /* foundStandards.add(new StandardDTO(1L,
                                        "09.05.01",
                                        "Применения и эксплуатация автоматизированных систем специального назначения"));
            foundStandards.add(new StandardDTO(2L,
                                                "09.04.03",
                                                "Программная инженерия"));*/
            // TODO: доделать
            logger.info("Подбор профессиональных стандартов прошёл успешно");
            /*
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Access-Control-Allow-Origin", "http://localhost:8080");
            */
            return ResponseEntity.ok(standardDTOList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение справки о приложении.
     *
     * @return Статус запроса.
     */
    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<HelpInfoDTO> getInfo() {
        try {
            logger.info("Открытие модального окна \"Справка о приложении\"");
            HelpInfoDTO helpInfoDTO = helpService.getInfo();
            return new ResponseEntity<>(helpInfoDTO, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Ручное обновление пользователем состояния профстандартов, хранящихся в БД
     *
     * @return ...
     */
    @PutMapping("/manual-update")
    public ResponseEntity<Object> updateManually() {
        try {
            logger.info("Проверка наличия обновлений данных о профстандартах...");
            // TODO: логика, свзяанная с вызовом сервиса Айдара в многопоточном режиме
            // TODO: возможно, возвращать, сколько (какие?) стандарты были обновлены - списком.
            //для обновления состояния БД
            logger.info("Данные о профстандартах обновлены. База находится в актуальном состоянии.");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Впоследствии добавить @PutMapping для обновления (корректировки) введенных
    //  пользователем данных для будущего парсинга

    /**
     * Возвращает сгенерированную эксель
     *
     * @param filePath - путь к файлу
     * @return excel с КМ
     * @apiNote example http://localhost:8081/excel/download?filePath=src/main/resources/excels/cm 09.05.01.xlsx
     */
    @RequestMapping(path = "/excel/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String filePath) throws IOException {
        File file = new File(filePath);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        int indexOf = filePath.indexOf("cm");
        ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                //.filename( filePath.substring(indexOf), StandardCharsets.UTF_8)
                .filename(filePath.substring(indexOf), StandardCharsets.US_ASCII)
                .name("attachment")
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(contentDisposition);
        httpHeaders.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
        httpHeaders.setContentLength(file.length());

        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);
    }
}
