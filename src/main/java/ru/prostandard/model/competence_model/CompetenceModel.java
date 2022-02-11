package ru.prostandard.model.competence_model;

import ru.prostandard.model.competence_model.tcl.CompetenceModelStatus;
import ru.prostandard.model.dicts.Specialization;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность "Компетентностная модель".
 * Одной компетентностной модели
 */
@Entity
@Table(name = "competence_model", schema = "proff")
public class CompetenceModel {

    /**
     * Уникальный идентификатор компетентностной модели
     */
    @Id
    @Column(name = "model_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modelId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spec_code_id", referencedColumnName = "spec_id")
    private Specialization specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private CompetenceModelStatus modelStatus;

    public CompetenceModel() {
    }

    public CompetenceModel(Integer modelId, Specialization specialization,
                           CompetenceModelStatus modelStatus) {
        this.modelId = modelId;
        this.specialization = specialization;
        this.modelStatus = modelStatus;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public CompetenceModelStatus getModelStatus() {
        return modelStatus;
    }

    public void setModelStatus(CompetenceModelStatus modelStatus) {
        this.modelStatus = modelStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetenceModel)) return false;
        CompetenceModel that = (CompetenceModel) o;
        return Objects.equals(modelId, that.modelId)
                && Objects.equals(specialization, that.specialization)
                && Objects.equals(modelStatus, that.modelStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, specialization, modelStatus);
    }

    @Override
    public String toString() {
        return "CompetenceModel{" +
                "modelId=" + modelId +
                ", specialization=" + specialization +
                ", modelStatus=" + modelStatus +
                '}';
    }
}
