package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.competence_model.tcl.ProfessionalTaskType;
import ru.prostandard.model.dicts.EducationLevel;

@Repository
public interface ProfessionalTaskTypeRepository extends JpaRepository<ProfessionalTaskType, Integer> {
}
