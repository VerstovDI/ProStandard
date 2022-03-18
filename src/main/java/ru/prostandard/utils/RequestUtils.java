package ru.prostandard.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestUtils {

    public static List<String> prepareKeywordsStringToList(String keywords) {
        return Stream.of(keywords.split(",")).collect(Collectors.toList());
    }
}
