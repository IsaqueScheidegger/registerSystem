package Entities.Services;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SelectMenu {

    public static void showMenu() {
        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running){
            try {
                System.out.println("Menu:");
                System.out.println("1 - Cadastrar novo Usuário");
                System.out.println("2 - Listar todos usuários cadastrados");
                System.out.println("3 - Cadastrar nova pergunta no formulário");
                System.out.println("4 - Deletar pergunta no formulário");
                System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
                System.out.print("Insira sua resposta: ");
                int choice = sc.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        System.out.println("Opção selecionada: \n1 - Cadastrar novo Usuário:\n");
                        User user = Register.registerForm();
                        SaveUser.saveFileMethod(user);
                        break;
                    case 2:
                        System.out.println("Opção selecionada: \n2- Listando todos os usuários cadastrados:\n");
                        ListFiles.listFilesInDirectory("C:\\dev\\temp\\txtfiles\\users");
                        break;
                    case 3:
                        System.out.println("Opção selecionada: \n3 - Cadastre uma nova pergunta no formulário:\n");
                        AddDelQuestions.addQuestion();
                        break;
                    case 4:
                        System.out.println("Opção selecionada: \n4 - Delete uma pergunta no formulário\n");
                        AddDelQuestions.delQuestion();
                        break;
                    case 5:
                        System.out.println("Opção selecionada: \nPesquisar usuário por nome ou idade ou email:\n");
                        SearchForNameAgeEmail.FileSearch();
                        break;
                    default:
                        System.out.println("Opção Inválida. Por favor, digite o número de uma das opções disponíveis.\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número inteiro.");
                sc.nextInt();
            } catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
