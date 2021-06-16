package ru.prostandard.model.dto;

import lombok.*;

import java.io.Serializable;

/*@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/

/**
 * Класс для передачи данных запроса с клиента на сервер о требуемой конфигурации
 * для подбора профстандартов
 */
@Data
@AllArgsConstructor
public class DictionaryDataDTO implements Serializable {

    /** Уровень образования */
    private String educationLevelValue;

    /** Код специализации */
    private String specializationCode;

    /** Направление обучения */
    private String major;

    /** URL ресурса для загрузки и парсинга проф. стандартов */
    private String resourceToDownload;

    /*public DictionaryDataDTO() {

    }

    public DictionaryDataDTO(String educationLevelValue,
                             String specializationCode,
                             Long majorId,
                             String resourceToDownload) {
        this.educationLevelValue = educationLevelValue;
        this.specializationCode = specializationCode;
        this.majorId = majorId;
        this.resourceToDownload = resourceToDownload;
    }*/

}
