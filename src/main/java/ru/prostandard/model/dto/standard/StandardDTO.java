package ru.prostandard.model.dto.standard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.prostandard.model.search.SearchFilter;

import java.io.Serializable;

/**
 * Класс (DTO) для передачи профстандарта клиенту.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StandardDTO extends SearchFilter implements Serializable {

    private static final long serialVersionUID = 1020345830010232213L;

    /** Регистрационный номер профессионального стандарта */
    @JsonProperty(value = "regNumber")
    private Integer regNumber;

    /** Код профессионального стандарта */
    @JsonProperty(value = "profstandardCode")
    private String profstandardCode;

    /** Наименование профессионального стандарта */
    @JsonProperty(value = "profstandardName")
    private String profstandardName;

}
