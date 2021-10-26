CREATE
INDEX id_gwf_idx
ON proff.generalized_work_functions(id_gwf);

CREATE
INDEX id_professional_standart_idx
ON proff.proff_standarts( proff_standarts_id);

CREATE
INDEX id_particular_work_function_idx
ON proff.particular_work_functions (id_particular_work_function);

CREATE
INDEX id_gwf_in_educational_requirements_idx
ON proff.educational_requirements(id_gwf);

CREATE
INDEX id_educational_requirement_idx
ON proff.educational_requirements(id_educational_requirement);

CREATE
INDEX id_possible_job_title_idx
ON proff.possible_job_titles(id_possible_job_title);

CREATE
INDEX id_labor_action_idx
ON proff.labor_actions(id_labor_action);

CREATE
INDEX id_required_skill_idx
ON proff.required_skills(id_required_skill);

CREATE
INDEX id_necessary_knowledge_idx
ON proff.necessary_knowledge(id_necessary_knowledge);

CREATE
INDEX to_tsvector_id_gwf_idx
ON proff.to_tsvector_generalized_work_functions(id_gwf);

CREATE
INDEX to_tsvector_id_particular_work_function_idx
ON proff.to_tsvector_particular_work_functions(id_particular_work_function);

CREATE
INDEX to_tsvector_educational_requirement_idx
ON proff.to_tsvector_educational_requirements
USING gin ("to_tsvector");

CREATE
INDEX to_tsvector_proff_standarts_name_professional_standart_idx
ON proff.to_tsvector_proff_standarts
USING gin ("name_professional_standart");

CREATE
INDEX to_tsvector_proff_standarts_purpose_kind_professional_activity_idx
ON proff.to_tsvector_proff_standarts
USING gin ("purpose_kind_professional_activity");

CREATE
INDEX to_tsvector_generalized_work_functions_name_gwf
ON proff.to_tsvector_generalized_work_functions
USING gin (name_gwf);

CREATE
INDEX to_tsvector_possible_job_titles_title
ON proff.to_tsvector_possible_job_titles
USING gin (title);

CREATE
INDEX to_tsvector_particular_work_functions_name_wf
ON proff.to_tsvector_particular_work_functions
USING gin (name_wf);

CREATE
INDEX to_tsvector_labor_actions_description
ON proff.to_tsvector_labor_actions
USING gin (description);

CREATE
INDEX to_tsvector_required_skills_description
ON proff.to_tsvector_required_skills
USING gin (description);


CREATE
INDEX to_tsvector_necessary_knowledge_description
ON proff.to_tsvector_necessary_knowledge
USING gin (description);