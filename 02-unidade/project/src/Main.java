import main.java.com.imdcorp.services.Operations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operations operations = new Operations();
        int option;

        do {
            System.out.println("================= Menu ===============");
            System.out.println("[1] Cadastrar Professor");
            System.out.println("[2] Cadastrar Técnico");
            System.out.println("[3] Listar Professores");
            System.out.println("[4] Listar Técnicos");
            System.out.println("[5] Buscar Professor");
            System.out.println("[6] Buscar Técnico");
            System.out.println("[7] Deletar Professor");
            System.out.println("[8] Deletar Técnico");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> operations.registerTeacher();
                case 2 -> System.out.println("Cadastrartecnicosssor");
                case 3 -> operations.listTeachers();
                case 4 -> System.out.println("Listar Técnicos");
                case 5 -> System.out.println("Buscar Professor");
                case 6 -> System.out.println("Buscar ténciso");
                case 7 -> System.out.println("Deletar Professor");
                case 8 -> System.out.println("Deletar técnico");
                case 0 -> System.out.println("Sair");
                default -> System.out.println(">>> Opção inválida!");
            }
        } while (option != 0);

        operations.closeApplication();
    }
}