package ru.prostandard.model.competence_model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tcl_model_status", schema = "proff")
public class CompetenceModelStatus {

    @Id
    @Column(name = "status_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long statusId;

    @Column(name = "status_description")
    private String statusDescription;

    public CompetenceModelStatus(Long statusId, String statusDescription) {
        this.statusId = statusId;
        this.statusDescription = statusDescription;
    }

    public CompetenceModelStatus() {

    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
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
