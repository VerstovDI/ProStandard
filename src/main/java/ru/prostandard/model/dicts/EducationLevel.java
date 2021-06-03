package ru.prostandard.model.dicts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dict_education_level", schema = "proff")
public class EducationLevel implements Serializable {

    @Id
    @Column(name = "level_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level_value")
    private Long levelValue;

    @Column(name = "description")
    private String levelDescription;

    public EducationLevel() {

    }

    public EducationLevel(Long id, Long levelValue, String levelDescription) {
        this.id = id;
        this.levelValue = levelValue;
        this.levelDescription = levelDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevelValue() {
        return levelValue;
    }

    public void setLevelValue(Long levelValue) {
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
