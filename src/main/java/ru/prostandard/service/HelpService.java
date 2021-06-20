package ru.prostandard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.HelpInfoDTO;
import ru.prostandard.repository.EducationLevelRepository;
import ru.prostandard.repository.MajorRepository;
import ru.prostandard.repository.SpecializationRepository;

@Service
@RequiredArgsConstructor
public class HelpService {

    private final EducationLevelRepository educationLevelRepository;
    private final MajorRepository majorRepository;
    private final SpecializationRepository specializationRepository;

    public HelpInfoDTO getInfo() {
        // TODO: тут вызов справочной инфы о приложении. Подумать, хранить ли её в БД.
        // TODO: в будущем исправить на вычисляемые значения (см. HelpInfoDTO.java)
        HelpInfoDTO helpInfoDTO = new HelpInfoDTO(
                "",
                "0.1_prototype",
                "",
                "21.06.2021");
        return helpInfoDTO;
    }
}
