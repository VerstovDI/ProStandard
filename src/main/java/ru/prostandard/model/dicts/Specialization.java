package ru.prostandard.model.dicts;

import ru.prostandard.model.competence_model.CompetenceModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dict_specialization", schema = "proff")
public class Specialization implements Serializable {

    @Id
    @Column(name = "spec_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "spec_code")
    private String specializationCode;

    @Column(name = "spec_description")
    private String specializationDescription;

    @OneToOne(mappedBy = "specialization")
    private CompetenceModel competenceModel;

    public Specialization() {
    }

    public Specialization(Integer id, String specializationCode, String specializationDescription) {
        this.id = id;
        this.specializationCode = specializationCode;
        this.specializationDescription = specializationDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecializationCode() {
        return specializationCode;
    }

    public void setSpecializationCode(String specializationCode) {
        this.specializationCode = specializationCode;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }

    public CompetenceModel getCompetenceModel() {
        return competenceModel;
    }

    public void setCompetenceModel(CompetenceModel competenceModel) {
        this.competenceModel = competenceModel;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", specializationCode='" + specializationCode + '\'' +
                ", specializationDescription='" + specializationDescription + '\'' +
                '}';
    }
}
