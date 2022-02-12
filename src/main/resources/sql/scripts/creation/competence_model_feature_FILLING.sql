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
