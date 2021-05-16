package ru.selenide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;

public class FileUtills {
    public static Optional<Path> getLastFilePath(String dir) throws IOException {
        return Files.list(Path.of(dir))    // here we get the stream with full directory listing
                .filter(f -> !Files.isDirectory(f))  // exclude subdirectories from listing
                .max(Comparator.comparingLong(f -> f.toFile().lastModified()));
    }

    public static void main(String[] args) throws IOException {


    }
}