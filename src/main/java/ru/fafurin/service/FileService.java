package ru.fafurin.service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    public void writeStringToFile(String string, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(formatString(string));
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String getStringFromFile(String filename) {
        Path path = Paths.get(filename);
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    private String formatString(String str) {
        return str.replace("[", "").
                   replace(",", "").
                   replace("\n ", "").
                   replace("]", "");
    }
}
