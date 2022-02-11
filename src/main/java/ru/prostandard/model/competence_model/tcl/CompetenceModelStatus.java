package ru.prostandard.model.competence_model.tcl;

import ru.prostandard.model.competence_model.CompetenceModel;

import javax.persistence.*;
import java.util.Objects;

/**
 * Сущность таблицы-классификатоа возможных статусов ЖЦ компетентностной модели
 */
@Entity
@Table(name = "tcl_model_status", schema = "proff")
public class CompetenceModelStatus {

    /**
     * Уникальный идентификатор статуса компетентностной модели
     */
    @Id
    @Column(name = "status_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statusId;

    /**
     * Описание статуса.<br/>
     * (Например,
     *      "PROJECT" (в стадии проекта, новая),
     *      "ARCHIVE" (архивная модель),
     *      "ACTIVE" (действующая модель)
     * )
     */
    @Column(name = "status_description")
    private String statusDescription;

    @OneToOne(mappedBy = "modelStatus")
    private CompetenceModel competenceModel;

    public CompetenceModelStatus(Integer statusId, String statusDescription) {
        this.statusId = statusId;
        this.statusDescription = statusDescription;
    }

    public CompetenceModelStatus() {

    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public CompetenceModel getCompetenceModel() {
        return competenceModel;
    }

    public void setCompetenceModel(CompetenceModel competenceModel) {
        this.competenceModel = competenceModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetenceModelStatus)) return false;
        CompetenceModelStatus that = (CompetenceModelStatus) o;
        return Objects.equals(getStatusId(), that.getStatusId()) && Objects.equals(getStatusDescription(), that.getStatusDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusId(), getStatusDescription());
    }

    @Override
    public String toString() {
        return "CompetenceModelStatus{" +
                "statusId=" + statusId +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }
}
