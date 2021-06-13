package ru.prostandard.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.prostandard.model.dicts.EducationLevel;
import ru.prostandard.model.dto.DictionaryDataDTO;
import ru.prostandard.model.dto.ParsingDataDTO;
import ru.prostandard.repository.EducationLevelRepository;
import ru.prostandard.service.DictionaryService;
import ru.prostandard.service.HelpService;
import ru.prostandard.service.ParsingService;

/**
 * Контроллер, обрабатывающий запросы главной страницы приложения.
 * Отвечает за отправку и приём JSON с главного окна составления профстандарта.
 */
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/main")
@RequiredArgsConstructor
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private final DictionaryService dictionaryService;

    private final HelpService helpService;

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private ParsingService parsingService;

/*    // Главная страница приложения
    @GetMapping
    public String getMainPage() {
        return "index";
    }*/

    /**
     * Отправить запрос на сервер для получения подобранного списка стандартов
     * @param dictionaryDataDTO DTO для передачи всех необходимых для подбора параметров
     * @return Статус запроса
     */
    @PostMapping("/send-request")
    public ResponseEntity<Object> sendRequest(@RequestBody DictionaryDataDTO dictionaryDataDTO) {
        try {
            dictionaryService.createRequest(dictionaryDataDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение списка всех подобранных стандартов
     * @return
     */
    @GetMapping("/standards")
    public ResponseEntity<Object> getStandards() {
        try {
            // TODO: Что вызываем? Что возвращает?
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение справки о приложении
     * @return Статус запроса
     */
    @GetMapping("/info")
    public ResponseEntity<Object> getInfo() {
        try {
            // TODO: возвращать не статус запроса, а объект "Справка о приложении"
            helpService.getInfo();
            return new ResponseEntity<>(HttpStatus.OK);
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
            // TODO: логика, свзяанная с вызовом сервиса Айдара в многопоточном режиме
            // TODO: возможно, возвращать, сколько (какие?) стандарты были обновлены - списком.
            //для обновления состояния БД
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    // ---- БЫЛО ДЛЯ ТЕСТА
    @GetMapping("/education-levels")
    public ResponseEntity<Object> getAllEducationLevels() {
        try {
            Iterable<EducationLevel> educationLevels = educationLevelRepository.findAll();
            return new ResponseEntity<>(educationLevels, HttpStatus.OK);
        } catch (Exception ex) {
            logger .error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Внутренняя функция, доступна в будущем админу или вообще в сервер скрыть
    @PostMapping("/submit-parsing")
    public ResponseEntity<Object> selectStandards(
            @RequestBody ParsingDataDTO parsingDataDTO
    ) {
        if (parsingDataDTO == null) {
            logger.error("Parsing data is null!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            // TODO: возвращаем не просто ответ, а ещё и что-то от парсинга
            parsingService.parseResource(parsingDataDTO);
            logger.info("Parsing data was successfully transferred to the server!");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Впоследствии добавить @PutMapping для обновления (корректировки) введенных
    // пользователем данных для будущего парсинга
}
