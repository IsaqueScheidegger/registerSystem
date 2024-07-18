package Entities.Services;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddDelQuestions {

    public static void addQuestion() {

        final String ADD_USER_FORMULARIO_PATH = "C:\\dev\\temp\\txtfiles\\formulario.txt";

        Scanner SCANNER = new Scanner(System.in);

        String newQuestion;

        do {
            System.out.print("Digite a nova pergunta a ser adicionada: ");
            newQuestion = SCANNER.nextLine();

            if (newQuestion.isEmpty()) {
                System.out.println("A pergunta não pode ser vazia. Por favor, digite uma pergunta válida.");
            }
        } while (newQuestion.isEmpty());

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADD_USER_FORMULARIO_PATH, true))) {
            bw.newLine();
            bw.write(newQuestion);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ADD_USER_FORMULARIO_PATH))) {
            String line;
            int qNumber = 1;

            while ((line = br.readLine()) != null) {
                System.out.println(qNumber + " - " + line);
                qNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void delQuestion() {
        final String DEL_USER_FORMULARIO_PATH = "C:\\dev\\temp\\txtfiles\\formulario.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DEL_USER_FORMULARIO_PATH))) {
            String line;
            int lineNumber = 1;

            while (lineNumber <= 4 && (line = br.readLine()) != null) {
                lines.add(line);
                lineNumber++;
            }

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nDigite a pergunta a ser apagada: ");
        String questionToDelete = sc.nextLine().trim();

        boolean isQuestionInHeader = false;
        for (int i = 0; i < 4; i++) {
            if (lines.get(i).equals(questionToDelete)) {
                isQuestionInHeader = true;
                break;
            }
        }

        if (isQuestionInHeader) {
            System.out.println("\n" + "Erro:" + "\n" + "Essa pergunta não pode ser apagada." + "\n");
            return;
        }

        boolean found = false;
        for (int i = 4; i < lines.size(); i++) {
            if (lines.get(i).trim().equals(questionToDelete)) {
                lines.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Por favor, digite a pergunta corretamente.");
            System.out.println("\n" + "Erro:" + "\n" + "Pergunta não encontrada." + "\n");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DEL_USER_FORMULARIO_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
