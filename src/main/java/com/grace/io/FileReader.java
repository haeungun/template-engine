package com.grace.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static String read(String filePath) {
        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return s;
    }

}
