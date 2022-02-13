package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.competence_model.CompetenceModel;
import ru.prostandard.model.competence_model.tcl.ProfessionalTaskType;

@Repository
public interface CompetenceModelRepository extends JpaRepository<CompetenceModel, Integer> {
}
