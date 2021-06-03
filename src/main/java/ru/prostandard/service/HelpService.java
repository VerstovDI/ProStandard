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
        HelpInfoDTO helpInfoDTO = new HelpInfoDTO(
                "0.1_prototype",
                "asdas",
                "asdasd",
                123123123L);
        return helpInfoDTO;
    }
}
