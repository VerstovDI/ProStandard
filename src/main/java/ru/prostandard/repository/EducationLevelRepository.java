package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.dicts.EducationLevel;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {
}
