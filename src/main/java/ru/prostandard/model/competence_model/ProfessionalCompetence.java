package ru.prostandard.model.competence_model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tcl_proffessional_competence", schema = "proff")
public class ProfessionalCompetence {

    @Id
    @Column(name = "prof_competence_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long competenceId;

    @Column(name = "competence_name")
    private String competenceName;

    public ProfessionalCompetence() {
    }

    public ProfessionalCompetence(Long competenceId, String competenceName) {
        this.competenceId = competenceId;
        this.competenceName = competenceName;
    }

    public Long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Long competenceId) {
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
        if (!(o instanceof ProfessionalCompetence)) return false;
        ProfessionalCompetence that = (ProfessionalCompetence) o;
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
