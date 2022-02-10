package ru.prostandard.repository.standard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Главный интерфейс получения информации о профессиональных стандартах
 */
@Repository
public interface MatchedStandardRepository extends StandardFilterRepository {

    @Query(value = "proff.get_proff_standarts_id_by_ed_req_and_key_words(:edLvl, :keywords)",
            nativeQuery = true)
    List<Integer> getProfstandardIdsByEducationalLevelAndKeywords(@Param("p_educational_requirements") Integer educationLevel,
                                                                  @Param("p_key_words") String keyWords);
}
