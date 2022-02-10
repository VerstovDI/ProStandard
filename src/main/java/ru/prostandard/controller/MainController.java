package ru.prostandard.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.prostandard.model.dto.HelpInfoDTO;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.model.dto.standard.StandardDTO;
import ru.prostandard.model.profstandards.Standard;
import ru.prostandard.service.DictionaryService;
import ru.prostandard.service.HelpService;
import ru.prostandard.service.intellisearch.IntelliSelectionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Контроллер, обрабатывающий запросы главной страницы приложения.
 * Отвечает за отправку и приём JSON с главного окна составления профстандарта.
 */
@RestController(value = "localhost:8081/")
@RequiredArgsConstructor
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    /** Сервис для ведения справочной информации приложения */
    private final DictionaryService dictionaryService;

    @Autowired
    private final HelpService helpService;

    @Autowired
    private final IntelliSelectionService intelliSelectionService;

    /**
     * GET-запрос с отправкой данных на получения списка всех подобранных стандартов
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

            List<String> keywordsList = Stream.of(keywords.split(",")).collect(Collectors.toList());
            SearchDTO searchDTO =
                    new SearchDTO(educationLevel, specializationCode, subjMajor, resourceToDownload, keywordsList);

            List<Standard> foundStandards = intelliSelectionService.getProfstandards(searchDTO);
            List<StandardDTO> standardDTOList = new ArrayList<>();
            for (Standard standard : foundStandards) {
                standardDTOList.add(new StandardDTO(
                       standard.getRegistrationNumber(),
                       standard.getCodeKindProfessionalActivity(),
                       standard.getNameProfessionalStandart()));
            }
           /* foundStandards.add(new StandardDTO(1L,
                                        "09.05.01",
                                        "Применения и эксплуатация автоматизированных систем специального назначения"));
            foundStandards.add(new StandardDTO(2L,
                                                "09.04.03",
                                                "Программная инженерия"));*/
            // TODO: доделать
            logger.info("Подбор профессиональных стандартов прошёл успешно");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Access-Control-Allow-Origin", "http://localhost:8080");
            return ResponseEntity.ok().headers(httpHeaders).body(standardDTOList);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение справки о приложении.
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
}
