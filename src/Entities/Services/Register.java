package Entities.Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Register {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String FORMULARIO_PATH = "C:\\dev\\temp\\txtfiles\\formulario.txt";
    public static final String USERS_PATH = "C:\\dev\\temp\\txtfiles\\users\\";

    public static User registerForm() {
        User user = new User();
        List<String> answers = new ArrayList<>();
        int nQuestion = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(FORMULARIO_PATH))) {
            // 1
            System.out.print(nQuestion + " - " + br.readLine() + " ");
            nQuestion++;
            String userName = SCANNER.nextLine();
            while (user.getName() == null) {
                try {
                    validateNameLength(userName);
                    checkIfNameExists(userName);
                    user.setName(formatUserName(userName));
                } catch (InputMismatchException e) {
                    System.out.print(e.getMessage());
                    userName = SCANNER.nextLine();
                }
            }
            // 2
            System.out.print(nQuestion + " - " + br.readLine() + " ");
            nQuestion++;
            String userEmail = SCANNER.nextLine();

            while (user.getEmail() == null) {
                try {
                    validateEmail(userEmail);
                    checkIfEmailExists(userEmail);
                    user.setEmail(userEmail);
                } catch (InputMismatchException e) {
                    System.out.print(e.getMessage());
                    userEmail = SCANNER.nextLine();
                }
            }

            // 3
            System.out.print(nQuestion + " - " + br.readLine() + " ");
            nQuestion++;
            String userAge = SCANNER.nextLine();

            try {
                checkIfAgeIsInt(userAge);
                checkIfUserIsOfLegalAge(userAge);
                user.setAge(Integer.valueOf(userAge));
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                userAge = SCANNER.nextLine();
            }

            // 4
            System.out.print(nQuestion + " - " + br.readLine() + " ");
            nQuestion++;
            String heightInput = SCANNER.nextLine();
            while (!heightInput.matches("\\d+,\\d{2}")) {
                System.out.print("Altura inválida! Por favor, insira um número no formato 0,00: ");
                heightInput = SCANNER.nextLine();
            }
            double userHeight = Double.parseDouble(heightInput.replace(',', '.'));
            user.setHeight(userHeight);

            // 5, 6...
            String question;
            while ((question = br.readLine()) != null) {
                System.out.print(nQuestion + " - " + question + " ");
                nQuestion++;
                String userAnswer = SCANNER.nextLine();
                answers.add(userAnswer);
            }

            user.setAnswers(answers);

            System.out.println("\n" + "Usuário \"" + user.getName() + "\" cadastrado com sucesso:" + "\n" + user.toString());

        } catch (
                IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }


        return user;
    }

    private static void validateNameLength(String userName) {
        while (!userName.matches("^[a-zA-Z\\s]{9,}$")) {
            throw new InputMismatchException("Erro. O nome deve ter pelo menos 9 letras: ");
        }
    }

    private static void checkIfNameExists(String userName) {

        File FOLDER = new File(USERS_PATH);
        File[] allFiles = FOLDER.listFiles();
        for (File file : allFiles) {
            if (file.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while ((reader.readLine().equals(formatUserName(userName)))) {
                        throw new InputMismatchException("Erro. Este nome já existe. Escreva outro: ");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private static String formatUserName(String userName) {
        String[] nameParts = userName.split("\\s+");
        StringBuilder formattedName = new StringBuilder();

        for (String part : nameParts) {
            if (!part.isEmpty()) {
                String firstLetter = part.substring(0, 1).toUpperCase();
                String restOfName = part.substring(1).toLowerCase();
                formattedName.append(firstLetter).append(restOfName).append(" ");
            }
        }

        return formattedName.toString().trim();
    }

    private static void validateEmail(String userEmail) {
        if (!userEmail.matches("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new InputMismatchException("Erro. Passe um email válido: ");
        }
    }

    private static void checkIfEmailExists(String userEmail) {
        File FOLDER = new File(USERS_PATH);
        File[] allFiles = FOLDER.listFiles();
        for (File file : allFiles) {
            if (file.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    reader.readLine();
                    String emailLine = reader.readLine();
                    if ((emailLine != null && emailLine.contains(userEmail))) {
                        throw new InputMismatchException("Erro. Este email já existe, escreva outro: ");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private static void checkIfAgeIsInt(String userAge) {
        while (!userAge.matches("\\d+")) {
            throw new InputMismatchException("Idade inválida! Por favor, digite apenas números: ");
        }
    }

    private static void checkIfUserIsOfLegalAge(String userAge) {
        if (Integer.parseInt(userAge) < 18) {
            throw new InputMismatchException("Idade inálida! O usuário deve ser maior de 18 anos. ");
        }
    }

}
