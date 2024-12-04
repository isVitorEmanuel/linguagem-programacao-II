import main.java.com.imdcorp.services.Operations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Operations operations = new Operations();
        int option;

        do {
            System.out.println("========================= Menu ======================");
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
            System.out.println("=====================================================");

            switch (option) {
                case 1 -> operations.registerTeacher();
                case 2 -> operations.registerADMTechnician();
                case 3 -> operations.listTeachers();
                case 4 -> operations.listADMTechnicians();
                case 5 -> operations.searchTeacher();
                case 6 -> operations.searchADMTechnician();
                case 7 -> operations.deleteTeacher();
                case 8 -> operations.deleteTechnician();
                case 0 -> System.out.println("Saindo do Sistema...");
                default -> System.out.println(">>> Opção inválida!");
            }
        } while (option != 0);

        operations.closeApplication();
    }
}