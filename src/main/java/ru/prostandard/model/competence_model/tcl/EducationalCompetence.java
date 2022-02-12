package ru.prostandard.model.competence_model.tcl;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность таблицы-классификатора проф. компетенций, вводимых методистом
 */
@Entity
@Table(name = "tcl_educational_competence", schema = "proff")
public class EducationalCompetence {

    /**
     * Уникальный идентификатор профессиональной компетенции
     */
    @Id
    @Column(name = "educational_competence_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer competenceId;

    /**
     * Описание профессиональной компетенции.<br/>
     * Например,
     * "(ПК-4) Способен разрабатывать, согласовывать и выпускать все виды проектной документации".
     */
    @Column(name = "competence_name")
    private String competenceName;

    public EducationalCompetence() {
    }

    public EducationalCompetence(Integer competenceId, String competenceName) {
        this.competenceId = competenceId;
        this.competenceName = competenceName;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EducationalCompetence)) return false;
        EducationalCompetence that = (EducationalCompetence) o;
        return Objects.equals(getCompetenceId(), that.getCompetenceId()) && Objects.equals(getCompetenceName(), that.getCompetenceName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompetenceId(), getCompetenceName());
    }

    @Override
    public String toString() {
        return "ProfessionalCompetence{" +
                "competenceId=" + competenceId +
                ", competenceName='" + competenceName + '\'' +
                '}';
    }
}
