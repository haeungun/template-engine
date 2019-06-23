package com.grace.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {

    public static boolean write(String outputPath, String s) {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            byte[] strToBytes = s.getBytes();
            outputStream.write(strToBytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
