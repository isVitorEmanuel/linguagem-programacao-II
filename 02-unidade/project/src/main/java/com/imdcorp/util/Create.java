package main.java.com.imdcorp.util;

import main.java.com.imdcorp.enums.Gender;
import main.java.com.imdcorp.enums.Level;
import main.java.com.imdcorp.enums.Training;
import main.java.com.imdcorp.model.ADMTechnician;
import main.java.com.imdcorp.model.Address;
import main.java.com.imdcorp.model.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Create {

    /**
     * This function validate user input and creates an address.
     *
     * @return A created address.
     */
    private Address createAddress() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(">>> Digite a rua: ");
        String street = scanner.nextLine();

        Integer number = null;
        while (number == null || number <= 0) {
            try {
                System.out.print(">>> Digite o número da casa (apenas números positivos): ");
                number = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERRO > Número inválido. Tente novamente.");
            }
        }

        System.out.print(">>> Digite o bairro: ");
        String neighborhood = scanner.nextLine();

        System.out.print(">>> Digite a cidade: ");
        String city = scanner.nextLine();

        String CEP = null;
        while (CEP == null || !CEP.matches("\\d{8}")) {
            System.out.print(">>> Digite o CEP (8 dígitos): ");
            CEP = scanner.nextLine();
            if (!CEP.matches("\\d{8}")) {
                System.out.println("ERRO > CEP inválido. Tente novamente.");
            }
        }

        return new Address(street, number, neighborhood, city, CEP);
    }

    /**
     * This function validate user input and creates a teacher.
     *
     * @return A created teacher.
     */
    public Teacher createTeacher() {
        Scanner scanner = new Scanner(System.in);

        try {
            String name = null;
            while (name == null || !name.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                System.out.print(">>> Digite o nome do professor (apenas letras e espaços): ");
                name = scanner.nextLine();
                if (!name.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                    System.out.println("ERRO > Nome inválido. Use apenas letras e espaços.");
                    name = null;
                }
            }

            String CPF = null;
            while (CPF == null || !CPF.matches("\\d{11}")) {
                System.out.print(">>> Digite o CPF do professor (apenas 11 números): ");
                CPF = scanner.nextLine();
                if (!CPF.matches("\\d{11}")) {
                    System.out.println("ERRO > CPF inválido. Deve conter exatamente 11 números.");
                    CPF = null;
                }
            }

            LocalDate dateBirth = null;
            while (dateBirth == null) {
                try {
                    System.out.print(">>> Digite a data de nascimento (formato yyyy-MM-dd): ");
                    String dateBirthInput = scanner.nextLine();
                    dateBirth = LocalDate.parse(dateBirthInput, DateTimeFormatter.ISO_DATE);
                } catch (DateTimeParseException e) {
                    System.out.println("ERRO > Formato de data inválido. Tente novamente.");
                }
            }

            Gender gender = null;
            while (gender == null) {
                try {
                    System.out.print(">>> Digite o gênero do professor (M / F): ");
                    String genderInput = scanner.nextLine();

                    if (genderInput.equalsIgnoreCase("M")) { genderInput = "MALE"; }
                    else if (genderInput.equalsIgnoreCase("F")) { genderInput = "FEMI"; }
                    else { genderInput = "INVALID"; }

                    gender = Gender.valueOf(genderInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("ERRO > Opção de gênero inválida. Tente novamente.");
                }
            }

            System.out.println("- - Endereço do professor: ");
            Address address = this.createAddress();

            Long enrollment = null;
            while (enrollment == null) {
                try {
                    System.out.print(">>> Digite o número de matrícula: ");
                    enrollment = Long.parseLong(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("ERRO > Número de matrícula inválido. Tente novamente.");
                }
            }

            Double salary = null;
            while (salary == null) {
                try {
                    System.out.print(">>> Digite o salário: ");
                    salary = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("ERRO > Valor de salário inválido. Tente novamente.");
                }
            }

            System.out.print(">>> Digite o departamento: ");
            String department = scanner.nextLine();

            Integer workload = null;
            while (workload == null) {
                try {
                    System.out.print(">>> Digite a carga horária: ");
                    workload = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("ERRO > Carga horária inválida. Tente novamente.");
                }
            }

            LocalDate entryDate = null;
            while (entryDate == null) {
                try {
                    System.out.print(">>> Digite a data de contratação (formato yyyy-MM-dd): ");
                    String entryDateInput = scanner.nextLine();
                    entryDate = LocalDate.parse(entryDateInput, DateTimeFormatter.ISO_DATE);
                } catch (DateTimeParseException e) {
                    System.out.println("ERRO > Formato de data inválido. Tente novamente.");
                }
            }

            Level level = null;
            while (level == null) {
                try {
                    System.out.print(">>> Digite o nível do professor (I à VIII): ");
                    level = Level.valueOf(scanner.nextLine().toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("ERRO > Nível inválido. Tente novamente.");
                }
            }

            Training teacherTraining = null;
            while (teacherTraining == null) {
                try {
                    System.out.print(">>> Digite a formação do professor (Especialização | Mestrado | Doutorado): ");
                    String trainingInput = scanner.nextLine();

                    if (trainingInput.equalsIgnoreCase("Especialização")) {
                        trainingInput = "SPECIALIZATION";
                    } else if (trainingInput.equalsIgnoreCase("Mestrado")) {
                        trainingInput = "MASTER";
                    } else if (trainingInput.equalsIgnoreCase("Doutorado")) {
                        trainingInput = "DOCTORATE";
                    } else {
                        trainingInput = "INVALID";
                    }

                    teacherTraining = Training.valueOf(trainingInput.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("ERRO > Formação inválida. Tente novamente.");
                }
            }

            System.out.print(">>> Digite as disciplinas (separe por vírgula): ");
            String[] subjectsArray = scanner.nextLine().split(",");
            List<String> subjects = new ArrayList<>();
            for (String subject : subjectsArray) {
                subjects.add(subject.trim());
            }

            return new Teacher(level, teacherTraining, subjects, name, CPF, dateBirth, gender,
                               address, enrollment, salary, department, workload, entryDate);

        }
        catch (Exception e) {
            System.out.println("ERROR > Ocorreu um erro inesperado: " + e.getMessage());
            return null;
        }
    }


    // TODO: Implement the register of technician.
    public ADMTechnician createTechnician() {
        return null;
    }
}
