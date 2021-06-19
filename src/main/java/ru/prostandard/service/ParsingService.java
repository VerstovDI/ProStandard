package ru.prostandard.service;

import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.DictionaryDataDTO;
import ru.prostandard.utils.ParsingUtils;

@Service
public class ParsingService {

    public Object parseResource(DictionaryDataDTO parsingDataDTO) {
        String tempDir = ParsingUtils.createTemporaryDir();
        String targetParsingString = ParsingUtils.getTargetParsingString(tempDir, parsingDataDTO);
        return targetParsingString;
    }
}
