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
 * Класс для передачи данных запроса типа "Справка" основного окна приложенияы
 */
public class HelpInfoDTO implements Serializable {

    /** Версия приложения */
    private String appVersion; // TODO: хардкод. Исправить на вычисляемое значение.

    /** Уровень образования */
    private String educationLevelValue;

    /** Код специализации */
    private String specializationCode;

    /** Id направления */
    private Long majorId;

}
