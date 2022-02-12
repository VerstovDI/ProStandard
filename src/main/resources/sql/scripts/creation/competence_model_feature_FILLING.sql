-- Наполнение справочных таблиц для создания всех связанных с компетентностной моделью сущностей


-- Наполняем proff.tcl_model_status  -- статус компетентностной модели
-- PROJECT = новый несогласованный проект компетент. модели
INSERT INTO proff.tcl_model_status (status_description) VALUES ('PROJECT');
-- ARCHIVE = компетент. модель считается нах-щейся в архиве
INSERT INTO proff.tcl_model_status (status_description) VALUES ('ARCHIVE');
-- ACTIVE = компетент. модель считается активной, т.е. доступна для использования в любой момент
INSERT INTO proff.tcl_model_status (status_description) VALUES ('ACTIVE');

-- Наполняем proff.tcl_proffessional_competence
-- Несколько тестовых заполнений из тестового документа для 09.05.01
INSERT INTO proff.tcl_educational_competence (competence_name)
VALUES ('Способен применять научно обоснованные перспективные методы исследования и решать задачи на основе знания мировых тенденций развития вычислительной техники и информационных технологий с внедрением результатов исследований в реальный сектор экономики');
INSERT INTO proff.tcl_educational_competence (competence_name)
VALUES ('Способен разрабатывать модели и компоненты высокопроизводительного защищенного программно-аппаратного обеспечения и автоматизированных систем обработки информации и управления с использованием современных инструментальных средств и технологий');
INSERT INTO proff.tcl_educational_competence (competence_name)
VALUES ('Способен организовывать работу и руководить коллективами разработчиков в области информатики и вычислительной техники');
INSERT INTO proff.tcl_educational_competence (competence_name)
VALUES ('Способен разрабатывать, согласовывать и выпускать все виды проектной документации');
INSERT INTO proff.tcl_educational_competence (competence_name)
VALUES ('Способен разрабатывать образовательные программы и учебно-методические материалы, а также проводить лекционные и практические занятия по дисциплинам в области информатики и вычислительной техники');

-- Наполняем proff.tcl_proffessional_task_type  -- тип задач профессиональной деятельности
INSERT INTO proff.tcl_proffessional_task_type (type_description) VALUES ('научно-исследовательский');
INSERT INTO proff.tcl_proffessional_task_type (type_description) VALUES ('технологический');
INSERT INTO proff.tcl_proffessional_task_type (type_description) VALUES ('организационно-управленческий');
INSERT INTO proff.tcl_proffessional_task_type (type_description) VALUES ('проектный');

-- Наполняем proff.dict_specialization-- Специализации
INSERT INTO proff.dict_specialization (spec_code,spec_description) VALUES ('09.05.01','Применение и эксплутация автоматизированных систем специального назначения');

-- Наполняем proff.competence_model  -- Компетентностная модель
INSERT INTO proff.competence_model (spec_code_id,status_id) VALUES (1,1);

-- Наполняем proff.prof_competence_linked  -- Компетентностная модель со связями
INSERT INTO proff.prof_competence_linked  VALUES (1,1,1,1,1,1);
INSERT INTO proff.prof_competence_linked  VALUES (1,2,1,2,2,2);
INSERT INTO proff.prof_competence_linked  VALUES (1,3,2,3,3,3);
INSERT INTO proff.prof_competence_linked  VALUES (1,4,3,1,4,4);