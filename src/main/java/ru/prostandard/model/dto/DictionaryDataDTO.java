package ru.prostandard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
/**
 * Класс для передачи данных запроса с клиента на сервер о требуемой конфигурации
 * для подбора профстандартов
 */
public class DictionaryDataDTO implements Serializable {

    /** Уровень образования */
    private String educationLevelValue;

    /** Код специализации */
    private String specializationCode;

    /** Id направления */
    private Long majorId;

    /** URL ресурса для загрузки и парсинга проф. стандартов */
    private String resourceToDownload;
}
