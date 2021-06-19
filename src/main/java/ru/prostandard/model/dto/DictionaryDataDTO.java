package ru.prostandard.model.dto;

import lombok.*;

import java.io.Serializable;


/**
 * Класс для передачи данных запроса с клиента на сервер о требуемой конфигурации
 * для подбора профстандартов
 */
@Data
@AllArgsConstructor
public class DictionaryDataDTO implements Serializable {

    /** Уровень образования */
    private String educationLevel;

    /** Код специализации */
    private String specializationCode;

    /** Направление обучения */
    private String subjMajor;

    /** URL ресурса для загрузки и парсинга проф. стандартов */
    private String resourceToDownload;
}
