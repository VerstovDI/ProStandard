set
default_text_search_config = russian;

create
or replace function proff.get_proff_standarts_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_proff_standarts_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- proff_standarts
    distinct (to_tsvector_proff_standarts.proff_standarts_id)
from proff.to_tsvector_educational_requirements
         left join proff.generalized_work_functions
                   on (generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         inner join proff.to_tsvector_proff_standarts
                    on (generalized_work_functions.proff_standarts_id = to_tsvector_proff_standarts.proff_standarts_id
                        and (to_tsvector_proff_standarts.purpose_kind_professional_activity @@ to_tsquery(p_key_words)
                            or to_tsvector_proff_standarts.name_professional_standart @@ to_tsquery(p_key_words)))
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_generalized_work_functions_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_generalized_work_functions_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- gwf
    distinct (to_tsvector_generalized_work_functions.id_gwf)
--		,to_tsvector_generalized_work_functions.name_gwf,
--		,to_tsvector_educational_requirements.to_tsvector

from proff.to_tsvector_educational_requirements
         inner join proff.to_tsvector_generalized_work_functions
                    on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_generalized_work_functions.name_gwf @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_possible_job_titles_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_possible_job_titles_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- possible_job_titles
    distinct (to_tsvector_possible_job_titles.id_possible_job_title)

from proff.to_tsvector_educational_requirements
         left join proff.to_tsvector_generalized_work_functions
                   on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         inner join proff.to_tsvector_possible_job_titles
                    on (to_tsvector_possible_job_titles.id_gwf = to_tsvector_generalized_work_functions.id_gwf)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_possible_job_titles.title @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_particular_work_functions_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_particular_work_functions_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- particular_work_functions
    distinct (to_tsvector_particular_work_functions.id_particular_work_function)

from proff.to_tsvector_educational_requirements
         left join proff.to_tsvector_generalized_work_functions
                   on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         inner join proff.to_tsvector_particular_work_functions
                    on (to_tsvector_particular_work_functions.id_gwf = to_tsvector_generalized_work_functions.id_gwf)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_particular_work_functions.name_wf @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_labor_actions_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_labor_actions_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- labor_actions
    distinct (to_tsvector_labor_actions.id_labor_action)

from proff.to_tsvector_educational_requirements
         left join proff.to_tsvector_generalized_work_functions
                   on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         left join proff.to_tsvector_particular_work_functions
                   on (to_tsvector_particular_work_functions.id_gwf = to_tsvector_generalized_work_functions.id_gwf)
         inner join proff.to_tsvector_labor_actions on (to_tsvector_labor_actions.id_particular_work_function =
                                                        to_tsvector_particular_work_functions.id_particular_work_function)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_labor_actions.description @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_required_skills_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_required_skills_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select       -- required_skills
    distinct (to_tsvector_required_skills.id_required_skill)

from proff.to_tsvector_educational_requirements
         left join proff.to_tsvector_generalized_work_functions
                   on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         left join proff.to_tsvector_particular_work_functions
                   on (to_tsvector_particular_work_functions.id_gwf = to_tsvector_generalized_work_functions.id_gwf)
         inner join proff.to_tsvector_required_skills on (to_tsvector_required_skills.id_particular_work_function =
                                                          to_tsvector_particular_work_functions.id_particular_work_function)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_required_skills.description @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;

------------------------------------------------------------------------------------------------------------------------------------------------------------------

create
or replace function proff.get_necessary_knowledge_id_by_ed_req_and_key_words(in p_educational_requirements text,
																		  in p_key_words text,
																		  out p_necessary_knowledge_id integer)
returns setof integer as
$BODY$
BEGIN
return query
select distinct(to_tsvector_necessary_knowledge.id_necessary_knowledge)

from proff.to_tsvector_educational_requirements
         left join proff.to_tsvector_generalized_work_functions
                   on (to_tsvector_generalized_work_functions.id_gwf = to_tsvector_educational_requirements.id_gwf)
         left join proff.to_tsvector_particular_work_functions
                   on (to_tsvector_particular_work_functions.id_gwf = to_tsvector_generalized_work_functions.id_gwf)
         inner join proff.to_tsvector_necessary_knowledge
                    on (to_tsvector_necessary_knowledge.id_particular_work_function =
                        to_tsvector_particular_work_functions.id_particular_work_function)
where to_tsvector_educational_requirements.to_tsvector @@ to_tsquery(p_educational_requirements)
  and
    to_tsvector_necessary_knowledge.description @@ to_tsquery(p_key_words);
END;
$BODY$
language plpgsql;
