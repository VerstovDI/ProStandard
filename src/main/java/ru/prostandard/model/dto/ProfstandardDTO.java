package ru.prostandard.model.dto;

/**
 * Класс (DTO) для передачи профстандарта клиенту.
 */
public class ProfstandardDTO {

    /**
     * Регистрационный номер профессионального стандарта
     */
    private Long regNumber;

    /**
     * Код профессионального стандарта
     */
    private String profstandardCode;

    /**
     * Наименование профессионального стандарта
     */
    private String profstandardName;

    private String kindOfProfessionalActivity;
}
