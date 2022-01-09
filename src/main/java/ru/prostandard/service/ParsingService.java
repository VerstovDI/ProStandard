package ru.prostandard.service;

import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.utils.ParsingUtils;

@Service
public class ParsingService {

    public Object parseResource(SearchDTO parsingDataDTO) {
        String targetParsingString = ParsingUtils.getTargetParsingString(parsingDataDTO);
        String typicalString = "06.*";

        return targetParsingString;
    }
}
