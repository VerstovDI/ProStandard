package ru.prostandard.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.service.ParsingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ParsingUtils {
    private Logger logger = LoggerFactory.getLogger(ParsingUtils.class);

    @Autowired
    private ParsingService parsingService;

    public static Path createTemporaryDir(SearchDTO searchDTO) throws IOException {
        Path tempDirPath = Files.createTempDirectory("dictionaryDataDTO.");
        return tempDirPath;
    }

    public static String getTargetParsingString(SearchDTO parsingDataDTO) {
        //createTemporaryDir();
        return "";
    }

    // Внутренняя функция, доступна в будущем админу или вообще в сервер скрыть

    /**
     * Функция парсинга профессиональных стандартов
     * @param searchDTO
     * @return
     */
    public ParseInfo parseStandards(SearchDTO searchDTO) {
        try {
            logger.info("Начало парсинга профстандарта в базу данных...");
            if (searchDTO == null) {
                logger.error("Данные для парсинга пусты");
                throw new RuntimeException("Данные для парсинга пусты");
            }
            ParseInfo parseInfo = new ParseInfo();
            // TODO: возвращаем не просто ответ, а ещё и что-то от парсинга
            //  (краткая свода того, что было спарсено)
            parsingService.parseResource(searchDTO);
            logger.info("Данные о профстандарте были успешно добавлены на сервер");
            return parseInfo;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new ParseInfo();
    }
}
