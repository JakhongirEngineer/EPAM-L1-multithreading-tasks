package com.epam.l1.multithreading.task5.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandlerUtils {

    public static boolean createNewFile(String fileName) throws IOException {
        File newFile = new File(fileName);
        return newFile.createNewFile();
    }

    public static List<String> readFileLineByLine(String fileName) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            return stream.collect(Collectors.toList());
        }
    }



    public static void deleteFile(String fileName) throws IOException {
        Files.delete(Paths.get(fileName));
    }

    public static boolean overrideFileContent(String fileName, List<String> newContent) throws IOException {
        Files.write(Path.of(fileName), newContent);
        return true;
    }

}
