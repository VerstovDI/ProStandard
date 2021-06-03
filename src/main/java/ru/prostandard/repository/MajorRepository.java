package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.dicts.Major;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {

}
