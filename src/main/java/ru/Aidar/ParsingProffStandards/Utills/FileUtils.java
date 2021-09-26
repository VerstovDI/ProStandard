package ru.Aidar.ParsingProffStandards.Utills;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

public class FileUtils {
    private static final Logger log = Logger.getLogger(FileUtils.class);

    public static Optional<Path> getLastFilePath(String dir) {
        try {
            return Files.list(Path.of(dir))    // here we get the stream with full directory listing
                    .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
                    .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
        } catch (IOException e) {
            log.error(e);
        }
        return Optional.empty();
    }

}