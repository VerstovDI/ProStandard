begin;

drop schema if exists proff cascade;
create schema proff;

CREATE TABLE proff.tcl_resource (  --откуда загружено
id_resource serial PRIMARY KEY,
url varchar(100)  NOT NULL
);

CREATE TABLE proff.proff_standarts ( -- профф стандарт
	code_kind_professional_activity varchar(6) PRIMARY KEY,
	date_of_approval date NOT NULL,
	date_of_downloading date NOT NULL, --дата загрузки
	name_professional_standart varchar(100) NOT NULL,
	registration_number integer NOT NULL,
	order_number varchar(10) NOT NULL,
	kind_professional_activity varchar NOT NULL,
	purpose_kind_professional_activity varchar NOT NULL,
	id_resource integer NOT NULL,
	FOREIGN KEY(id_resource)  REFERENCES proff.tcl_resource     (id_resource)  ON DELETE RESTRICT ON UPDATE CASCADE
);



 CREATE TABLE proff.employment_group_okz  --код ОКЗ
(
  сode_okz integer PRIMARY KEY,
  name_okz varchar(100),
  code_kind_professional_activity varchar(6),
  FOREIGN KEY(code_kind_professional_activity)  REFERENCES proff.proff_standarts     (code_kind_professional_activity)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.employment_group_okved  ---КОД ОКВЕД
(
  сode_okved varchar PRIMARY KEY,
  name_okved varchar(100),
  code_kind_professional_activity varchar(6),
  FOREIGN KEY(code_kind_professional_activity)  REFERENCES proff.proff_standarts     (code_kind_professional_activity)  ON DELETE RESTRICT ON UPDATE CASCADE
); 


CREATE TABLE proff.generalized_work_functions ---- обобщ трудовые функции 
(
  id_gwf serial  PRIMARY KEY,
  сode_gwf varchar(5) NOT NULL,
  name_gwf varchar(100) NOT NULL,
  level_of_qualification integer NOT NULL,
  code_kind_professional_activity varchar(6),
  FOREIGN KEY(code_kind_professional_activity)  REFERENCES proff.proff_standarts     (code_kind_professional_activity)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.possible_job_titles  -- возможные профессии
(
  id_possible_job_title serial  PRIMARY KEY,
  title varchar(1000) NOT NULL,
  id_gwf integer NOT NULL,
  FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.educational_requirements --требования к уровню образования
(
  id_educational_requirement serial  PRIMARY KEY,
  educational_requirement varchar(100)  NOT NULL,
  id_gwf integer NOT NULL,
  FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);



---- на этом обобщ трудовые функции все

CREATE TABLE proff.particular_work_functions  --трудовые функции
(
  id_particular_work_function serial  PRIMARY KEY,
  сode_wf varchar(5) NOT NULL,
  name_wf varchar(100) NOT NULL,
  sub_qualification integer NOT NULL,
  id_gwf integer NOT NULL,
  FOREIGN KEY(id_gwf)  REFERENCES proff.generalized_work_functions (id_gwf)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.labor_actions  --трудовые действия(навыки)
(
  id_labor_action serial  PRIMARY KEY,
  description varchar(1000) NOT NULL,
  
  id_particular_work_function integer NOT NULL,
  FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.required_skills  --Необходимые умения
(
  id_required_skill serial  PRIMARY KEY,
  description varchar(1000) NOT NULL,
  
  id_particular_work_function integer NOT NULL,
  FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE proff.necessary_knowledge  --Необходимые знания
(
  id_necessary_knowledge serial  PRIMARY KEY,
  description varchar(1000) NOT NULL,
  id_particular_work_function integer NOT NULL,
  FOREIGN KEY(id_particular_work_function)  REFERENCES proff.particular_work_functions (id_particular_work_function)  ON DELETE RESTRICT ON UPDATE CASCADE
);
end;