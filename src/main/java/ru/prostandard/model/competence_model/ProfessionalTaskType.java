package ru.prostandard.model.competence_model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tcl_proffessional_task_type", schema = "proff")
public class ProfessionalTaskType {

    @Id
    @Column(name = "type_id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskTypeId;

    @Column(name = "type_description")
    private String typeDescription;

    public ProfessionalTaskType() {
    }

    public ProfessionalTaskType(Long taskTypeId, String typeDescription) {
        this.taskTypeId = taskTypeId;
        this.typeDescription = typeDescription;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessionalTaskType)) return false;
        ProfessionalTaskType that = (ProfessionalTaskType) o;
        return Objects.equals(getTaskTypeId(), that.getTaskTypeId()) && Objects.equals(getTypeDescription(), that.getTypeDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskTypeId(), getTypeDescription());
    }

    @Override
    public String toString() {
        return "ProfessionalTaskType{" +
                "taskTypeId=" + taskTypeId +
                ", typeDescription='" + typeDescription + '\'' +
                '}';
    }
}
