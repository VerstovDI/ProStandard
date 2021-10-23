package ru.prostandard.model.dto;

import lombok.*;
import ru.prostandard.model.search.SearchFilter;

import java.io.Serializable;


/**
 * Класс для передачи данных запроса с клиента на сервер о требуемой конфигурации
 * для подбора профстандартов
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class DictionaryDataDTO extends SearchFilter implements Serializable {

    private static final long serialVersionUID = -1329588300102932213L;

    /** Уровень образования */
    private String educationLevel;

    /** Код специализации */
    private String specializationCode;

    /** Направление обучения */
    private String subjMajor;

    /** URL ресурса для загрузки и парсинга профессиональных стандартов */
    private String resourceToDownload;
}
