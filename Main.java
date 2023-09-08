import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ArrayList staffList = new ArrayList();
    public static ArrayList traineeList = new ArrayList<>();

    public static void main(String[] args) {

        menu();

    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean menuBool = false;
        do {
            System.out.println("\u001B[31m");
            System.out.println("'add': add a staff or trainee to the system." +
                    "\n'remove': remove staff or trainee from the system." +
                    "\n'edit': edit a staff or trainee previously added to the system." +
                    "\n'exit': Quit the program");
            System.out.println("\u001B[0m");
            switch (scanner.nextLine()) {
                case "add" -> {
                    RegisterStaff();
                    break;
                }
                case "remove" -> {
                    break;
                }
                case "edit" -> {
                    break;
                }
                case "exit" -> System.exit(0);
            }
        } while (!menuBool);
    }

    public static void RegisterStaff() {
        Scanner scanner = new Scanner(System.in);
        String[] queryBuilder = new String[6];
        System.out.println("\u001B[31mChoose which to create:\n\u001B[0m'1': Staff. '2': Trainee");
        String userType = "";

        switch (scanner.nextLine()) {
            case "1" -> { // Create staff
                System.out.println("\u001B[31mCreating: Staff\n\u001B[0m");
                String[] staffQuery = {"ID: ", "Name: ", "Gender: ", "Salary: "};

                for (int i = 0; i < staffQuery.length; i++) {
                    System.out.print(staffQuery[i]);
                    String Answer = scanner.nextLine();
                    queryBuilder[i] = Answer;
                }

                LocalDate currentDate = LocalDate.now();
                Staff staff = new Staff(
                        Integer.parseInt(queryBuilder[0]),
                        queryBuilder[1],
                        queryBuilder[2],
                        Integer.parseInt(queryBuilder[3]),
                        currentDate);
                staffList.add(staff);

                System.out.println("\u001B[31mNew staff created\n\u001B[0m");
                System.out.println(
                        "ID: " + staff.getId() +
                                "\nName: " + staff.getName() +
                                "\nGender: " + staff.getGender() +
                                "\nSalary: " + staff.getSalary() +
                                "\nStart-Date: " + staff.getStartDate());
            }

            case "2" -> { // Create trainee
                System.out.println("Creating: Trainee");
                String[] traineeQuery = {"ID: ", "Name: ", "Gender: ", "End-Date: ", "Credentials: "};

                for (int i = 0; i < traineeQuery.length - 1; i++) {
                    System.out.print(traineeQuery[i]);
                    String Answer = scanner.nextLine();
                    if (i == 2) {
                        System.out.print("Example: yyyy-mm-dd\n");
                    }

                    queryBuilder[i] = Answer;

                }

                Trainee trainee = new Trainee(
                        Integer.parseInt(queryBuilder[0]),
                        queryBuilder[1],
                        queryBuilder[2],
                        LocalDate.parse(queryBuilder[3]),
                        "");
                traineeList.add(trainee);

                System.out.println("\u001B[31mNew trainee created\n\u001B[0m");
                System.out.println(
                        "ID: " + trainee.getId() +
                                "\nName: " + trainee.getName() +
                                "\nGender: " + trainee.getGender() +
                                "\nEnd-Date: " + trainee.getEndDate() +
                                "\nCredentials: " + trainee.getCredentials());
            }
        }
    }
}
