package com.dmmsoft.adminpanel.emailsender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class PathFileReader {

    public Path resourceFilePath;


    public PathFileReader(Path filePath) {
        this.resourceFilePath = filePath;
    }

    public String getFileAsString() throws IOException {
        List<String> fileContent = Files.readAllLines(resourceFilePath, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : fileContent) {
            stringBuilder.append(item);
        }
        return stringBuilder.toString();
    }

}
