package ru.prostandard.model.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.prostandard.model.search.SearchFilter;

import java.io.Serializable;

/**
 * Класс (DTO) для передачи профстандарта клиенту.
 */
@AllArgsConstructor
@NoArgsConstructor
public class StandardDTO extends SearchFilter implements Serializable {

    private static final long serialVersionUID = 1020345830010232213L;

    /** Регистрационный номер профессионального стандарта */
    private Long regNumber;

    /** Код профессионального стандарта */
    private String profstandardCode;

    /** Наименование профессионального стандарта */
    private String profstandardName;

}
