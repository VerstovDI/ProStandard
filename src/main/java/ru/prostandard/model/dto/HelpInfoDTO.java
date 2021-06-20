package ru.prostandard.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Класс (DTO) для передачи данных запроса типа "Справка" основного окна приложения.
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HelpInfoDTO implements Serializable {

    /** О приложении */
    private String aboutApp;

    /** Версия приложения */
    private String appVersion; // TODO: В будущем убрать хардкод. Исправить на вычисляемое значение.

    /** Информация о разработчике приложения */
    private String developerInfo;

    /** Дата последнего обновления приложения */
    private String lastUpdateInfo;

}
