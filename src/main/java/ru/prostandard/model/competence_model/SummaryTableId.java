package ru.prostandard.model.competence_model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SummaryTableId implements Serializable {

    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "competence_id")
    private Integer competenceId;

    @Column(name = "prof_task_type_id")
    private Integer professionalTaskTypeId;

    @Column(name = "knowledge_id")
    private Long knowledgeId;

    @Column(name = "skill_id")
    private Long skillId;

    @Column(name = "labor_action_id")
    private Long laborActionId;

    public SummaryTableId() {
    }

    public SummaryTableId(Integer modelId, Integer competenceId,
                          Integer professionalTaskTypeId, Long knowledgeId,
                          Long skillId, Long laborActionId) {
        this.modelId = modelId;
        this.competenceId = competenceId;
        this.professionalTaskTypeId = professionalTaskTypeId;
        this.knowledgeId = knowledgeId;
        this.skillId = skillId;
        this.laborActionId = laborActionId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Integer competenceId) {
        this.competenceId = competenceId;
    }

    public Integer getProfessionalTaskTypeId() {
        return professionalTaskTypeId;
    }

    public void setProfessionalTaskTypeId(Integer professionalTaskTypeId) {
        this.professionalTaskTypeId = professionalTaskTypeId;
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getLaborActionId() {
        return laborActionId;
    }

    public void setLaborActionId(Long laborActionId) {
        this.laborActionId = laborActionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SummaryTableId)) return false;
        SummaryTableId that = (SummaryTableId) o;
        return Objects.equals(modelId, that.modelId)
                && Objects.equals(competenceId, that.competenceId)
                && Objects.equals(professionalTaskTypeId, that.professionalTaskTypeId)
                && Objects.equals(knowledgeId, that.knowledgeId)
                && Objects.equals(skillId, that.skillId)
                && Objects.equals(laborActionId, that.laborActionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelId, competenceId, professionalTaskTypeId,
                            knowledgeId, skillId, laborActionId);
    }

    @Override
    public String toString() {
        return "SummaryTableId{" +
                "modelId=" + modelId +
                ", competenceId=" + competenceId +
                ", professionalTaskTypeId=" + professionalTaskTypeId +
                ", knowledgeId=" + knowledgeId +
                ", skillId=" + skillId +
                ", laborAction=" + laborActionId +
                '}';
    }
}
