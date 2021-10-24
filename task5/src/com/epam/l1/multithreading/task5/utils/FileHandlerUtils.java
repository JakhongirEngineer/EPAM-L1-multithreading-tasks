package com.epam.l1.multithreading.task5.utils;

import com.epam.l1.multithreading.task5.exceptions.UnableToCreateFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandlerUtils {

    public static void createNewFile(List<String> content ,String fileName) throws IOException, UnableToCreateFile {
        File newFile = new File(fileName);
        boolean isCreated = newFile.createNewFile();
        if (!isCreated){
            throw new UnableToCreateFile("unable to create a file.");
        }
        Files.write(Path.of(fileName), content);
    }

    public static List<String> readFileLineByLine(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        }
    }

    public static boolean deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(fileName));
        return true;
    }

    public static boolean overrideFileContent(String fileName, List<String> newContent) throws IOException {
        Files.write(Path.of(fileName), newContent);
        return true;
    }
}
