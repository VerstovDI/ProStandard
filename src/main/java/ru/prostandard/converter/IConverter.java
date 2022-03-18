package ru.prostandard.converter;

import java.util.List;

/**
 * Интерфейс конвертации DTO в модель и обратно
 * @param <T> класс DTO
 * @param <O> класс модели
 */
public interface IConverter<T, O> {

    /**
     * Конвертация из модели в DTO
     * @param model
     * @return
     */
    T convertToDTO(O model);

    /**
     * Конвертация из DTO в модель
     * @param dto
     * @return
     */
    O convertToModel(T dto);

    /**
     * Конвертация списка моделей в список dto
     * @param modelList
     * @return
     */
    List<T> convertToDTO(List<O> modelList);

    /**
     * Конвертация списка dto в список моделей
     * @param dtoList
     * @return
     */
    List<O> convertToModel(List<T> dtoList);
}
