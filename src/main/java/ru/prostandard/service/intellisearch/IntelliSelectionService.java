package ru.prostandard.service.intellisearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.model.dto.standard.StandardDTO;
import ru.prostandard.model.profstandards.Standard;
import ru.prostandard.model.search.SearchFilter;
import ru.prostandard.repository.StandardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Интеллектуальный сервис формирования выборки профстандартов.
 * Данный сервис является реализацией системы поддержки принятия решений (DSS) пользователя
 * по выборке необходимых профессиональных стандартов.
 */
@Service
public class IntelliSelectionService {

    /**
     * Список, хранящий последнюю проведённую выборку профессиональных стандартов
     */
    private List<StandardDTO> profstandards;

    @Autowired
    private StandardRepository standardRepository;

    /**
     * Метод подбора профессиональных стандартов
     *
     * @param searchDTO входные данные для подбора
     * @return список подобранных проф. стандартов
     */
    public List<Standard> getProfstandards(SearchDTO searchDTO) {
        // TODO: хардкод-заглушка, ур. образования не привязан к SearchDTO
        List<EducationLevel> educationLevels = new ArrayList<>();
        educationLevels.add(EducationLevel.SPECIALIST);
        List<Long> prostandardsIds =
                getProfstandardIdsByEducationLevelAndKeywordString(educationLevels,
                        searchDTO.getKeywords());

        List<Standard> matchedProfstandards = getProfstandardsByIds(prostandardsIds);
        return matchedProfstandards;
    }

    /**
     * Метод для обновления профессиональных стандартов, хранящихся в базе, в соответствие с
     * актуальной информацией
     *
     * @return true - стандарт был обновлен. Иначе - false
     */ // TODO: доработать
    public boolean updateProstandards(Standard standard) {
        List<SearchFilter> searchFilters = new ArrayList<>();
        String profstandartNumber = "";
        String dirToSave = "";
        //searchFilters.add(searchDTO);
        // GetAndParseProffStandard.getAndParseStandard(profstandartNumber, dirToSave);
        //profstandards = matchedStandardRepository.sendSearchFilters(searchFilters);
        //return profstandards;
        return true;
    }

    /**
     * Метод для получения списка ID профстандартов, потенциально подходящих для составления
     * компетентностной модели
     *
     * @param educationLevels уровени образования
     * @param keywords       список ключевых слов
     * @return список, содержащий ID подобранных профстандартов
     */
    private List<Long> getProfstandardIdsByEducationLevelAndKeywordString(
            List<EducationLevel> educationLevels,
            List<String> keywords) {

        // TODO: сделать список уровней образования, поправить multiple choice на фронте

        return standardRepository.findStandardsByEducationalLevelAndByKeyWords(
                SearchHelper.createEdRequirementsString(educationLevels),
                SearchHelper.keywordsString(keywords));
    }

    /**
     * Метод, возвращающий сущности стандартов по их ID
     * @param ids список ID профстандартов
     * @return список, содержащий требуемые стандарты
     */
    private List<Standard> getProfstandardsByIds(List<Long> ids) {
        return standardRepository.findAllByIds(ids);
    }

    /**
     * Класс-помощник по формированию поисковых запросов на подбор профстандартов
     */
    private static class SearchHelper {

        // Блок логических операторов, исп-щихся при полнотекстовом поиске
        private static final String AND = "&";
        private static final String OR = "|";
        private static final String NOT = "!";

        /**
         * Метод формирования строки поиска по ключевым словам для хранимых процедур
         * @param keywords ключевы слова поиска
         * @return строка, состоящая из ключевых слов
         */
        private static String keywordsString(List<String> keywords) {
            // TODO: в будущем доработать, чтобы была гибкость выбора
            StringBuilder keywordString = new StringBuilder();
            for (int i = 0; i < keywords.size(); i++) {
                keywordString.append(keywords.get(i));
                if (i == keywords.size()-1) {
                    break;
                } else
                {
                    keywordString.append(" ").append(SearchHelper.OR).append(" ");
                }
            }
            return keywordString.toString();
        }

        /**
         * Метод формирования фрагмента строки полнотекстового поиска, содержащей требуемые уровни
         * образования
         * @param educationLevels список уровне образования
         * @return строка, содержащая фрагмент строки, составленный из уровней образования
         */
        private static String createEdRequirementsString(List<EducationLevel> educationLevels) {
            return createSearchStringWithOperator(
                    educationLevels
                            .stream()
                            .flatMap(edLevel -> Stream.of(edLevel.getEducationLevelName()))
                            .collect(Collectors.toList()),
                    SearchHelper.OR);
        }

        /**
         * Простейший метод, составляющий строку
         * для полнотекстового поиска из требумых фрагментов и оператора
         * @param targetStringParts требумые фрагменты (слова) формируемой строки
         * @param operator логический оператор связывания фрагментов
         * @return готовая строка, составленная из оператора и фрагментов
         */
        private static String createSearchStringWithOperator(
                List<String> targetStringParts,
                String operator) {
            StringBuilder keywordString = new StringBuilder();
            for (int i = 0; i < targetStringParts.size(); i++) {
                keywordString.append(targetStringParts.get(i));
                if (i == targetStringParts.size()-1) {
                    break;
                } else
                {
                    keywordString.append(" ").append(operator).append(" ");
                }
            }
            return keywordString.toString();
        }
    }

}
