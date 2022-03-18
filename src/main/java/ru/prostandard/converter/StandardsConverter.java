package ru.prostandard.converter;

import org.springframework.stereotype.Component;
import ru.prostandard.model.dto.standard.StandardDTO;
import ru.prostandard.model.profstandards.Standard;

import java.util.ArrayList;
import java.util.List;

@Component
public class StandardsConverter implements IConverter<StandardDTO, Standard> {


    @Override
    public StandardDTO convertToDTO(Standard model) {
        return null;
    }

    @Override
    public Standard convertToModel(StandardDTO dto) {
        return null;
    }

    @Override
    public List<StandardDTO> convertToDTO(List<Standard> standardList) {
        List<StandardDTO> standardDTOList = new ArrayList<>();
        for (Standard standard : standardList) {
            standardDTOList.add(new StandardDTO(
                    standard.getRegistrationNumber(),
                    standard.getCodeKindProfessionalActivity(),
                    standard.getNameProfessionalStandart()));
        }
        return standardDTOList;
    }

    @Override
    public List<Standard> convertToModel(List<StandardDTO> dtoList) {
        return null;
    }
}
