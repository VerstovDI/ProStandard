package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.profstandards.NecessaryKnowledge;

import java.util.List;

@Repository
public interface NecessaryKnowledgeRepository extends JpaRepository<NecessaryKnowledge, Long> {
    @Query(value = "select proff.get_necessary_knowledge_id_by_ed_req_and_key_words(:p_educational_requirements,:p_key_words)"
            , nativeQuery = true)
    List<Long> findNecessaryKnowledgesByEducationalLevelAndByKeyWords(@Param("p_educational_requirements") String educationalRequirements,
                                                                      @Param("p_key_words") String keyWords);
}
