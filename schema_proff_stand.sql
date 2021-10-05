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
    spec_id serial,
    major_id serial,
    FOREIGN KEY (level_id) REFERENCES proff.dict_education_level(level_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (spec_id) REFERENCES proff.dict_specialization(spec_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (major_id) REFERENCES proff.dict_major(major_id)
    ON DELETE CASCADE ON UPDATE CASCADE
    );
