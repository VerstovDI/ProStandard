package ru.prostandard.repository.standard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.prostandard.model.dto.StandardDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Главный интерфейс получения информации о профессиональных стандартах
 */
@Repository
public interface StandardRepository
        extends JpaRepository<StandardDTO, Long>, StandardFilterRepository {

    /**
     * Метод получения профессиональных стандартов в формате "ключ () - значение (данные стандарта)"
     * @param standardIds Список из ID требуемых для поиска стандартов
     * @return Список полученных стандартов
     */
    List<StandardDTO> getStandardsByIds(List<Integer> standardIds);

    /**
     * Метод получения требуемого стандарта по ID
     * @param standardId ID искомого стандарта
     * @return Объект, содержащий найденный стандарт
     */
    Optional<StandardDTO> getStandardById(Integer standardId);

    /**
     * Метод получения всех профессиональных стандартов
     * @return Коллекция со всеми профессиональными стандартами
     */
    Collection<StandardDTO> getAllStandards();

    /**
     * Метод сохранения профессионального стандарта
     * @param standard Стандарт, который требуется сохранить в БД
     * @return Результат операции (True - успеншое сохранение, False - не удалось сохранить)
     */
    boolean saveStandard(StandardDTO standard);

    /**
     * Метод обновления профессионального стандарта
     * @param standard Стандарт, который требуется обновить в БД
     * @return Результат операции (True - успеншое обновление, False - не удалось обновить)
     */
    boolean updateStandard(StandardDTO standard);

    /**
     * Метод удаления профессионального стандарта
     * @param standard Стандарт, который требуется удалить из БД
     * @return Результат операции (True - успеншое удаление, False - не удалось удалить)
     */
    boolean deleteStandard(StandardDTO standard);

}
