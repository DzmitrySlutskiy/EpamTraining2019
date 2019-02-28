package com.epam.cleancode.codequalitymetrics.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static List<String> readFile(String filePath) throws IOException, URISyntaxException {
        return Files.readAllLines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()), Charset.defaultCharset());
    }

}
