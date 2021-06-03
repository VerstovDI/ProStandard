package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.dicts.Specialization;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}
