package ru.prostandard.model.competence_model;

import ru.prostandard.model.competence_model.tcl.ProfessionalCompetence;
import ru.prostandard.model.competence_model.tcl.ProfessionalTaskType;
import ru.prostandard.model.profstandards.LaborAction;
import ru.prostandard.model.profstandards.NecessaryKnowledge;
import ru.prostandard.model.profstandards.RequiredSkill;

import javax.persistence.*;

/**
 * Сводная таблица, содержащая ссылки на ЗУН, на проф. кометенции, тип проф. задачи.<br/>
 * Создана в канонах денормализации в угоду удобства, скорости поиска и наглядности.<br/>
 * Соответствует таблице proff.prof_competence в базе данных приложения.
 */
@Entity
@Table(name = "prof_competence", schema = "proff")
public class SummaryTable {

    /**
     * Составной (композитный) первичный ключ, содержащий все ссылки на все участвующие таблицы
     */
    @EmbeddedId
    private SummaryTableId summaryTableId;

    /**
     * Ссылка на компетентностную модель
     */
    @ManyToOne
    @MapsId("modelId")
    @JoinColumn(name = "model_id")
    private CompetenceModel competenceModel;

    /**
     * Ссылка на ссылка на профессиональную компетенцию
     */
    @ManyToOne
    @MapsId("competenceId")
    @JoinColumn(name = "competence_id")
    private ProfessionalCompetence professionalCompetence;

    /**
     * Ссылка на тип задачи профессиональной деятельности
     */
    @ManyToOne
    @MapsId("professionalTaskTypeId")
    @JoinColumn(name = "prof_task_type_id")
    private ProfessionalTaskType professionalTaskType;

    /**
     * Cсылка на знание
     */
    @ManyToOne
    @MapsId("knowledgeId")
    @JoinColumn(name = "knowledge_id")
    private NecessaryKnowledge necessaryKnowledge;

    /**
     * Cсылка на умение
     */
    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private RequiredSkill requiredSkill;

    /**
     * Ссылка на навык
     */
    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "labor_action_id")
    private LaborAction laborActionId;

    public SummaryTable() {
    }

    public SummaryTable(SummaryTableId summaryTableId,
                        CompetenceModel competenceModel,
                        ProfessionalCompetence professionalCompetence,
                        ProfessionalTaskType professionalTaskType,
                        NecessaryKnowledge necessaryKnowledge,
                        RequiredSkill requiredSkill,
                        LaborAction laborActionId) {
        this.summaryTableId = new SummaryTableId(
                competenceModel.getModelId(),
                professionalCompetence.getCompetenceId(),
                professionalTaskType.getTaskTypeId(),
                necessaryKnowledge.getId(),
                requiredSkill.getId(),
                laborActionId.getId());
        this.competenceModel = competenceModel;
        this.professionalCompetence = professionalCompetence;
        this.professionalTaskType = professionalTaskType;
        this.necessaryKnowledge = necessaryKnowledge;
        this.requiredSkill = requiredSkill;
        this.laborActionId = laborActionId;
    }
}
