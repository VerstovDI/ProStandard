package ru.prostandard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostandard.model.dicts.EducationLevel;
import ru.prostandard.repository.EducationLevelRepository;

import java.util.List;

@Service
public class EducationLevelService {

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    /**
     * Метод для получения списка всех образовательных уровней
     * @return List - список со всеми образовательными уровнями
     */
    public List<EducationLevel> list() {
        return educationLevelRepository.findAll();
    }
}
