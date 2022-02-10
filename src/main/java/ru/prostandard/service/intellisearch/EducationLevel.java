package ru.prostandard.service.intellisearch;

/**
 * Класс-перечисление для хранения уровней образования
 */
public enum EducationLevel {

    SPECIALIST(1, "специалитет"),
    BACHELOR(2, "бакалавриат"),
    MASTER(3, "магистратура"),
    POSTGRADUATE(4, "аспирантура");

    /**
     * Числовое значение уровня образования
     */
    private Integer educationLevelValue;

    /**
     * Название уровня образования в РФ
     */
    private String educationLevelName;

    EducationLevel(Integer educationLevelValue, String educationLevelName) {
        this.educationLevelValue = educationLevelValue;
        this.educationLevelName = educationLevelName;
    }

    public Integer getEducationLevelValue() {
        return educationLevelValue;
    }

    public String getEducationLevelName() {
        return educationLevelName;
    }



}
