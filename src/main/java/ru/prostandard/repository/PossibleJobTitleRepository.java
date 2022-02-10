package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.profstandards.PossibleJobTitle;

import java.util.List;

@Repository
public interface PossibleJobTitleRepository extends JpaRepository<PossibleJobTitle, Long> {
    @Query(value = "select proff.get_possible_job_titles_id_by_ed_req_and_key_words(:p_educational_requirements,:p_key_words)"
            , nativeQuery = true)
    List<Long> findPossibleJobTitlesByEducationalLevelAndByKeyWords(@Param("p_educational_requirements") String educationalRequirements,
                                                                    @Param("p_key_words") String keyWords);
}