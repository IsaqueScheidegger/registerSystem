package Entities.Services;

import java.io.File;
import java.nio.file.DirectoryIteratorException;

public class ListFiles {

    public static void listFilesInDirectory(String directoryPath) {

        try {
            File directory = new File(directoryPath);

            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        System.out.println(file.getName());
                    }
                } else {
                    System.out.println("O diretório está vazio.");
                }
            }
        } catch (DirectoryIteratorException e) {
            System.out.println(e.getMessage());
        }
    }
}
