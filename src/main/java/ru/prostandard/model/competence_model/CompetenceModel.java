package ru.prostandard.model.competence_model;

import lombok.Getter;
import lombok.Setter;
import ru.prostandard.model.competence_model.tcl.CompetenceModelStatus;
import ru.prostandard.model.dicts.Specialization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сущность "Компетентностная модель".
 * Одной компетентностной модели
 */
@Entity
@Table(name = "competence_model", schema = "proff")
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "model_id")
    private List<SummaryTable> summaryTables=new ArrayList<>();

    public CompetenceModel() {
    }

    public CompetenceModel(Integer modelId, Specialization specialization,
                           CompetenceModelStatus modelStatus) {
        this.modelId = modelId;
        this.specialization = specialization;
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
