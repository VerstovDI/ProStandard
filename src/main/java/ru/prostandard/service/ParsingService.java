package ru.prostandard.service;

import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.ParsingDataDTO;
import ru.prostandard.utils.ParsingUtils;

@Service
public class ParsingService {

    public Object parseResource(ParsingDataDTO parsingDataDTO) {
        String tempDir = ParsingUtils.createTemporaryDir();
        String targetParsingString = ParsingUtils.getTargetParsingString(tempDir, parsingDataDTO);
        return targetParsingString;
    }
}
