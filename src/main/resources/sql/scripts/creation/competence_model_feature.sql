CREATE TABLE IF NOT EXISTS proff.tcl_model_status(  -- таблица-классификатор возможных статусов ЖЦ компетентностной модели
                                                     status_id serial PRIMARY KEY,  -- уникальный идентификатор статуса
                                                     status_description varchar(15) UNIQUE -- описание статуса (например, PROJECT, ARCHIVE, ACTIVE)
);

CREATE TABLE IF NOT EXISTS proff.tcl_educational_competence( -- таблица-классификатор образ. компетенций, вводимых методистом
                                                                 educational_competence_id serial PRIMARY KEY,  -- уникальный идентификатор образовательной компетенции
                                                                 competence_name varchar(500) UNIQUE  -- описание компетенции (например, "(ПК-4) Способен разрабатывать, согласовывать и выпускать все виды проектной документации")
);

CREATE TABLE IF NOT EXISTS proff.tcl_proffessional_task_type(  -- таблица-классификатор типа задач профессиональной деятельности
                                                                type_id serial PRIMARY KEY, -- уникальный идентификатор типа профессиональной деятельности
                                                                type_description varchar(50) UNIQUE -- описание типа профессиональной деятельности (например, "научно-исследовательский", "педагогический". Возможно, пустой вариант - тогда ПК стоит отдельно и не имеет типа задачи)
);

CREATE TABLE IF NOT EXISTS proff.competence_model(  -- Компетентностная модель
                                                     model_id serial PRIMARY KEY,  -- уникальный идентификатор модели
                                                     spec_code_id integer,  -- ссылка на код специальности (ссылается на справочник)
                                                     status_id integer, -- ссылка на статус модели
                                                     FOREIGN KEY (spec_code_id) REFERENCES proff.dict_specialization (spec_id)
                                                         ON DELETE RESTRICT ON UPDATE CASCADE,
                                                     FOREIGN KEY (status_id) REFERENCES proff.tcl_model_status (status_id)
                                                         ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS proff.prof_competence_linked(  -- профессиональные компетенции
                                                    model_id integer,  -- ссылка на компетентностную модель
                                                    competence_id integer,  -- ссылка на оброзовательную компетенцию
                                                    prof_task_type_id integer, -- ссылка на тип задачи профессиональной деятельности
                                                    knowledge_id bigint, -- ссылка на знание
                                                    skill_id bigint, -- ссылка на умение
                                                    labor_action_id bigint, -- ссылка на навык
                                                    PRIMARY KEY(model_id, competence_id, prof_task_type_id, knowledge_id, skill_id, labor_action_id),
                                                    FOREIGN KEY (model_id) REFERENCES proff.competence_model (model_id)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE,
                                                    FOREIGN KEY (competence_id) REFERENCES proff.tcl_educational_competence(educational_competence_id)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE,
                                                    FOREIGN KEY (prof_task_type_id) REFERENCES proff.tcl_proffessional_task_type(type_id)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE,
                                                    FOREIGN KEY (knowledge_id) REFERENCES proff.necessary_knowledge(id_necessary_knowledge)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE,
                                                    FOREIGN KEY (skill_id) REFERENCES proff.required_skills(id_required_skill)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE,
                                                    FOREIGN KEY (labor_action_id) REFERENCES proff.labor_actions(id_labor_action)
                                                        ON DELETE RESTRICT ON UPDATE CASCADE
);