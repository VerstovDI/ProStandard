package ru.prostandard.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Абстрактный класс, представляющий собой тип "Фильтр для поиска".
 * Фильтром для поиска может быть объект, содержаий что угодно:
 * уровень образования, направление обучения, специальность, ключевое слово, исключающие параметры и т.д.
 */
public abstract class SearchFilter {

    @JsonIgnore
    private String FILTER_TYPE;

}
