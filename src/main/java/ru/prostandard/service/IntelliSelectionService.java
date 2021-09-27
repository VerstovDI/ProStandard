package ru.prostandard.service;

import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.ProfstandardDTO;

import java.util.HashMap;

/**
 * Интеллектуальный сервис подбора профстандартов
 */
@Service
public class IntelliSelectionService {

    /**
     * Хэшмапа для хранения релевантных для текущей выдачи профстандартов.
     * Ключ - регистрационный номер профстандарта.
     * Значение - DTO профстандарта.
     * Изначально хэшмапа пуста.
     */
    private HashMap<Long, ProfstandardDTO> profstandards;
    // TODO: тут заинъектить БД, откуда будем тащить данные
    // TODO: запилить метод, который будет выдавать хэшмапу с данными.
}
