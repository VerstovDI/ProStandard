package ru.prostandard.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.prostandard.model.dicts.EducationLevel;
import ru.prostandard.model.dto.DictionaryDataDTO;
import ru.prostandard.model.dto.HelpInfoDTO;
import ru.prostandard.repository.EducationLevelRepository;
import ru.prostandard.service.DictionaryService;
import ru.prostandard.service.HelpService;
import ru.prostandard.service.ParsingService;

/**
 * Контроллер, обрабатывающий запросы главной страницы приложения.
 * Отвечает за отправку и приём JSON с главного окна составления профстандарта.
 */
@RestController
@RequiredArgsConstructor
public class MainController {
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    private final DictionaryService dictionaryService;

    private final HelpService helpService;

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private ParsingService parsingService;

    /**
     * Получение списка всех подобранных стандартов
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/standards")
    @ResponseBody
    public ResponseEntity<Object> getStandards(@RequestBody DictionaryDataDTO dictionaryDataDTO) {
        try {
            logger.info("Начало выдачи профессиональных стандартов");

            // TODO: Что вызываем? Что возвращает?

            logger.info("Выдача профессиональных стандартов прошла успешно");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Получение справки о приложении.
     * @return Статус запроса.
     */
    @CrossOrigin()
    @GetMapping("/info")
    @ResponseBody
    public ResponseEntity<HelpInfoDTO> getInfo() {
        try {
            // TODO: возвращать не статус запроса, а объект "Справка о приложении"
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
            // TODO: логика, свзяанная с вызовом сервиса Айдара в многопоточном режиме
            // TODO: возможно, возвращать, сколько (какие?) стандарты были обновлены - списком.
            //для обновления состояния БД
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Внутренняя функция, доступна в будущем админу или вообще в сервер скрыть
    @PostMapping("/submit-parsing")
    public ResponseEntity<Object> selectStandards(
            @RequestBody DictionaryDataDTO dictionaryDataDTO
    ) {
        if (dictionaryDataDTO == null) {
            logger.error("Parsing data is null!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            // TODO: возвращаем не просто ответ, а ещё и что-то от парсинга
            parsingService.parseResource(dictionaryDataDTO);
            logger.info("Parsing data was successfully transferred to the server!");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: Впоследствии добавить @PutMapping для обновления (корректировки) введенных
    //  пользователем данных для будущего парсинга
}
