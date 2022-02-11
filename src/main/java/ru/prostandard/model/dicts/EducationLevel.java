package ru.prostandard.model.dicts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dict_education_level", schema = "proff")
public class EducationLevel implements Serializable {

    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "level_value")
    private Integer levelValue;

    @Column(name = "description")
    private String levelDescription;

    public EducationLevel() {

    }

    public EducationLevel(Integer id, Integer levelValue, String levelDescription) {
        this.id = id;
        this.levelValue = levelValue;
        this.levelDescription = levelDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Integer levelValue) {
        this.levelValue = levelValue;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    @Override
    public String toString() {
        return "EducationLevel{" +
                "id=" + id +
                ", levelValue=" + levelValue +
                ", levelDescription='" + levelDescription + '\'' +
                '}';
    }
}
