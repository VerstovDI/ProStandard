package ru.prostandard.repository.standard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.dto.StandardDTO;
import ru.prostandard.model.profstandards.Standard;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Главный интерфейс получения информации о профессиональных стандартах
 */
@Repository
public interface MatchedStandardRepository
        extends JpaRepository<Standard, Long>, StandardFilterRepository {

}
