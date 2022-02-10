package ru.prostandard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.profstandards.Standard;

import java.util.List;

/**
 * Базовый репозиторий для работы с проф. стандартами
 */
@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {

    /**
     * Метод, выдающий список ID подобранных проф. стандартов
     * по уровням образования и ключевым словам
     * @param educationalRequirements строка уровней образования
     * @param keyWords строка ключевых слов
     * @return список ID подорбанных проф. стандартов
     */
    @Query(value = "SELECT proff.get_proff_standarts_id_by_ed_req_and_key_words(" +
            ":p_educational_requirements," +
            ":p_key_words)", nativeQuery = true)
    List<Long> findStandardsByEducationalLevelAndByKeyWords(
            @Param("p_educational_requirements") String educationalRequirements,
            @Param("p_key_words") String keyWords);

    /**
     * Метод, выдающий список проф. станартов по их ID
     * @param ids список ID проф. стандартов
     * @return список проф. стандартов
     */
    @Query(value = "SELECT st FROM Standard st WHERE st.id IN :ids")
    List<Standard> findAllByIds(List<Long> ids);
}
