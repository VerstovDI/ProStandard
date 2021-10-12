begin;

drop schema if exists proff cascade;
create schema proff;

CREATE TABLE proff.tcl_resource (  --откуда загружено
                                    id_resource serial PRIMARY KEY,
                                    url varchar(500)  NOT NULL
);

CREATE TABLE proff.proff_standarts(                                                             -- профф стандарт
                                      proff_standarts_id                 serial PRIMARY KEY,
                                      code_kind_professional_activity    varchar(10),
                                      date_of_approval                   date         NOT NULL,
                                      date_of_downloading                date         NOT NULL, --дата загрузки
                                      name_professional_standart         varchar(500) NOT NULL,
                                      registration_number                integer      NOT NULL,
                                      order_number                       varchar(20)  NOT NULL,
                                      kind_professional_activity         varchar      NOT NULL,
                                      purpose_kind_professional_activity varchar      NOT NULL,
                                      id_resource                        BIGINT       NOT NULL,
                                      FOREIGN KEY (id_resource) REFERENCES proff.tcl_resource (id_resource) ON DELETE RESTRICT ON UPDATE CASCADE
);



CREATE TABLE proff.employment_group_okz  --код ОКЗ
(
    сode_okz           integer PRIMARY KEY,
    name_okz           varchar(1000),
    proff_standarts_id BIGINT,
    FOREIGN KEY (proff_standarts_id) REFERENCES proff.proff_standarts (proff_standarts_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.employment_group_okved  ---КОД ОКВЕД
(
    id_okved           serial PRIMARY KEY,
    сode_okved         varchar,
    name_okved         varchar(1000),
    proff_standarts_id BIGINT,
    FOREIGN KEY (proff_standarts_id) REFERENCES proff.proff_standarts (proff_standarts_id) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE proff.generalized_work_functions ---- обобщ трудовые функции
(
    id_gwf                 serial PRIMARY KEY,
    сode_gwf               varchar(50)   NOT NULL,
    name_gwf               varchar(1000) NOT NULL,
    level_of_qualification varchar(10)   NOT NULL,
    proff_standarts_id     BIGINT,
    FOREIGN KEY (proff_standarts_id) REFERENCES proff.proff_standarts (proff_standarts_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.possible_job_titles  -- возможные профессии
(
    id_possible_job_title serial  PRIMARY KEY,
    title varchar(1000) NOT NULL,
    id_gwf BIGINT NOT NULL,
    FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.educational_requirements --требования к уровню образования
(
    id_educational_requirement serial  PRIMARY KEY,
    educational_requirement varchar(1000)  NOT NULL,
    id_gwf BIGINT NOT NULL,
    FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);



---- на этом обобщ трудовые функции все

CREATE TABLE proff.particular_work_functions  --трудовые функции
(
    id_particular_work_function serial  PRIMARY KEY,
    сode_wf varchar(100) NOT NULL,
    name_wf varchar(10000) NOT NULL,
    sub_qualification integer NOT NULL,
    id_gwf BIGINT NOT NULL,
    FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.labor_actions  --трудовые действия(навыки)
(
    id_labor_action serial  PRIMARY KEY,
    description varchar(10000) NOT NULL,

    id_particular_work_function BIGINT NOT NULL,
    FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.required_skills  --Необходимые умения
(
    id_required_skill serial  PRIMARY KEY,
    description varchar(10000) NOT NULL,

    id_particular_work_function BIGINT NOT NULL,
    FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.necessary_knowledge  --Необходимые знания
(
    id_necessary_knowledge serial  PRIMARY KEY,
    description varchar(10000) NOT NULL,
    id_particular_work_function BIGINT NOT NULL,
    FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);

insert into proff.tcl_resource (id_resource,url)
values (default,'https://profstandart.rosmintrud.ru/obshchiy-informatsionnyy-blok/natsionalnyy-reestr-professionalnykh-standartov/reestr-professionalnykh-standartov');

end;

/* ---------------- ЧАСТЬ СКРИПТА, ОТВЕТСТВЕННАЯ ЗА СОЗДАНИЕ СЛОВАРЕЙ ДАННЫХ ---------------- */
-- Таблица-справочник со специальностями
-- DROP TABLE IF EXISTS proff.dict_specialization
CREATE TABLE IF NOT EXISTS proff.dict_specialization
(
    spec_id serial PRIMARY KEY,
    spec_code int UNIQUE,  -- код специальности (например, 09.05.01)
    spec_description varchar(300) UNIQUE NOT NULL  -- текстовое описание специальности (например, "Применение и эксплутация ...")
    );

-- Таблица-справочник со специализациями(направлениями подготовки)
-- DROP TABLE IF EXISTS proff.dict_major
CREATE TABLE IF NOT EXISTS proff.dict_major
(
    major_id serial PRIMARY KEY,
    major_description varchar(300) UNIQUE NOT NULL  -- текстовое описание направления подготовки (например, "АСО ИУ")
    );

-- Таблица-справочник с уровнями образования
-- DROP TABLE IF EXISTS proff.dict_education_level
CREATE TABLE IF NOT EXISTS proff.dict_education_level
(
    level_id serial PRIMARY KEY,
    level_value int UNIQUE, -- уровень образования
    description varchar(100) UNIQUE NOT NULL  -- текстовое название уровня
    );

-- Сводная таблица
-- DROP TABLE IF EXISTS proff.dict_pivot
CREATE TABLE IF NOT EXISTS proff.dict_pivot
(
    record_id serial PRIMARY KEY,
    level_id serial,
    spec_id
    serial,
    major_id
    serial,
    FOREIGN
    KEY
(
    level_id
) REFERENCES proff.dict_education_level
(
    level_id
)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY
(
    spec_id
) REFERENCES proff.dict_specialization
(
    spec_id
)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY
(
    major_id
) REFERENCES proff.dict_major
(
    major_id
)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    );

-- Таблицы для индексов
CREATE TABLE proff.to_tsvector_educational_requirements AS
SELECT id_educational_requirement, to_tsvector('russian', educational_requirement), id_gwf
FROM proff.educational_requirements;


CREATE TABLE proff.to_tsvector_proff_standarts AS
SELECT proff_standarts_id
     , to_tsvector('russian', "name_professional_standart")         as name_professional_standart
     , to_tsvector('russian', "purpose_kind_professional_activity") as purpose_kind_professional_activity
FROM proff.proff_standarts;


CREATE TABLE proff.to_tsvector_generalized_work_functions AS
SELECT id_gwf, to_tsvector('russian', "name_gwf") as name_gwf
FROM proff.generalized_work_functions;

CREATE TABLE proff.to_tsvector_possible_job_titles AS
SELECT id_possible_job_title, to_tsvector('russian', "title") as title, id_gwf
FROM proff.possible_job_titles;


CREATE TABLE proff.to_tsvector_particular_work_functions AS
SELECT id_particular_work_function, to_tsvector('russian', "name_wf") as name_wf, id_gwf
FROM proff.particular_work_functions;


CREATE TABLE proff.to_tsvector_labor_actions AS
SELECT id_labor_action, to_tsvector('russian', "description") as description, id_particular_work_function
FROM proff.labor_actions;

CREATE TABLE proff.to_tsvector_required_skills AS
SELECT id_required_skill, to_tsvector('russian', "description") as description, id_particular_work_function
FROM proff.required_skills;

CREATE TABLE proff.to_tsvector_necessary_knowledge AS
SELECT id_necessary_knowledge, to_tsvector('russian', "description") as description, id_particular_work_function
FROM proff.necessary_knowledge;

---triggers
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_educational_requirements() RETURNS TRIGGER AS $update_to_tsvector_educational_requirements$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_educational_requirements
WHERE id_educational_requirement = OLD.id_educational_requirement;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_educational_requirements
SET to_tsvector = to_tsvector('russian', NEW.educational_requirement),
    id_gwf=NEW.id_gwf
WHERE id_educational_requirement = OLD.id_educational_requirement;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_educational_requirements
select NEW.id_educational_requirement, to_tsvector('russian', NEW.educational_requirement), NEW.id_gwf;
RETURN NEW;
END IF;
RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
END;
$update_to_tsvector_educational_requirements$
LANGUAGE plpgsql;

CREATE TRIGGER update_to_tsvector_educational_requirements
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.educational_requirements
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_educational_requirements();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_proff_standarts() RETURNS TRIGGER AS $update_to_tsvector_proff_standarts$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_proff_standarts
WHERE proff_standarts_id = OLD.proff_standarts_id;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_proff_standarts
SET name_professional_standart        = to_tsvector('russian', NEW.name_professional_standart),
    purpose_kind_professional_activity=to_tsvector('russian', NEW.purpose_kind_professional_activity)
WHERE proff_standarts_id = OLD.proff_standarts_id;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_proff_standarts
select NEW.proff_standarts_id
     , to_tsvector('russian', NEW.name_professional_standart)
     , to_tsvector('russian', NEW.purpose_kind_professional_activity);
RETURN NEW;
END IF;
RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
END;
$update_to_tsvector_proff_standarts$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_proff_standarts
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.proff_standarts
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_proff_standarts();

--------------------------------------------------------------------

CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_generalized_work_functions() RETURNS TRIGGER AS $update_to_tsvector_generalized_work_functions$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_generalized_work_functions
WHERE id_gwf = OLD.id_gwf;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_generalized_work_functions
SET name_gwf = to_tsvector('russian', NEW.name_gwf)
WHERE id_gwf = OLD.id_gwf;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_generalized_work_functions
select NEW.id_gwf, to_tsvector('russian', NEW.name_gwf);
RETURN NEW;
END IF;
RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
END;
$update_to_tsvector_generalized_work_functions$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_generalized_work_functions
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.generalized_work_functions
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_generalized_work_functions();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_possible_job_titles() RETURNS TRIGGER AS $update_to_tsvector_possible_job_titles$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_possible_job_titles
WHERE id_possible_job_title = OLD.id_possible_job_title;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_possible_job_titles
SET title = to_tsvector('russian', NEW.title),
    id_gwf=NEW.id_gwf
WHERE id_possible_job_title = OLD.id_possible_job_title;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_possible_job_titles
select NEW.id_possible_job_title, to_tsvector('russian', NEW.title), NEW.id_gwf;
RETURN NEW;
END IF;
RETURN NULL; -- возвращаемое значение для триггера AFTER игнорируется
END;
$update_to_tsvector_possible_job_titles$
LANGUAGE plpgsql;

--DROP TRIGGER if exists  update_to_tsvector_possible_job_titles ON proff.possible_job_titles ;
CREATE TRIGGER update_to_tsvector_possible_job_titles
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.possible_job_titles
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_possible_job_titles();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_particular_work_functions() RETURNS TRIGGER AS $update_to_tsvector_particular_work_functions$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_particular_work_functions
WHERE id_particular_work_function = OLD.id_particular_work_function;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_particular_work_functions
SET name_wf = to_tsvector('russian', NEW.name_wf)
WHERE id_particular_work_function = OLD.id_particular_work_function;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_particular_work_functions
select NEW.id_particular_work_function, to_tsvector('russian', NEW.name_wf), NEW.id_gwf;
RETURN NEW;
END IF;
RETURN NULL;
END;
$update_to_tsvector_particular_work_functions$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_particular_work_functions
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.particular_work_functions
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_particular_work_functions();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_labor_actions() RETURNS TRIGGER AS $update_to_tsvector_labor_actions$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_labor_actions
WHERE id_labor_action = OLD.id_labor_action;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_labor_actions
SET description                = to_tsvector('russian', NEW.description),
    id_particular_work_function=NEW.id_particular_work_function
WHERE id_labor_action = OLD.id_labor_action;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_labor_actions
select NEW.id_labor_action, to_tsvector('russian', NEW.description), NEW.id_particular_work_function;
RETURN NEW;
END IF;
RETURN NULL;
END;
$update_to_tsvector_labor_actions$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_labor_actions
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.labor_actions
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_labor_actions();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_required_skills() RETURNS TRIGGER AS $update_to_tsvector_required_skills$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_required_skills
WHERE id_required_skill = OLD.id_required_skill;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_required_skills
SET description                = to_tsvector('russian', NEW.description),
    id_particular_work_function=NEW.id_particular_work_function
WHERE id_required_skill = OLD.id_required_skill;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_required_skills
select NEW.id_required_skill, to_tsvector('russian', NEW.description), NEW.id_particular_work_function;
RETURN NEW;
END IF;
RETURN NULL;
END;
$update_to_tsvector_required_skills$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_required_skills
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.required_skills
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_required_skills();
--------------------------------------------------------------------
CREATE
OR REPLACE FUNCTION proff.update_to_tsvector_necessary_knowledge() RETURNS TRIGGER AS $update_to_tsvector_necessary_knowledge$
BEGIN
        IF
(TG_OP = 'DELETE') THEN
DELETE
FROM proff.to_tsvector_necessary_knowledge
WHERE id_necessary_knowledge = OLD.id_necessary_knowledge;
RETURN OLD;
ELSIF
(TG_OP = 'UPDATE') THEN
UPDATE proff.to_tsvector_necessary_knowledge
SET description                = to_tsvector('russian', NEW.description),
    id_particular_work_function=NEW.id_particular_work_function
WHERE id_necessary_knowledge = OLD.id_necessary_knowledge;
RETURN NEW;
ELSIF
(TG_OP = 'INSERT') THEN
            INSERT INTO proff.to_tsvector_necessary_knowledge
select NEW.id_necessary_knowledge, to_tsvector('russian', NEW.description), NEW.id_particular_work_function;
RETURN NEW;
END IF;
RETURN NULL;
END;
$update_to_tsvector_necessary_knowledge$
LANGUAGE plpgsql;


CREATE TRIGGER update_to_tsvector_necessary_knowledge
    AFTER INSERT OR
UPDATE OR
DELETE
ON proff.necessary_knowledge
    FOR EACH ROW EXECUTE PROCEDURE proff.update_to_tsvector_necessary_knowledge();