package Entities.Services;

import java.io.*;
import java.util.Scanner;

public class SearchForNameAgeEmail {

    public static void FileSearch() {
        String DIRECTORY = "C:\\dev\\temp\\txtfiles\\users\\";

        try {
            File FOLDER = new File(DIRECTORY);
            File[] allFiles = FOLDER.listFiles();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o termo de busca: ");
            String search = scanner.nextLine();

            for (File file : allFiles) {
                if (file.isFile()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(search)) {
                                System.out.println("Encontrado em: \n" + file.getName() + ": " + line + "\n");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}