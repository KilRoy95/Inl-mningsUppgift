import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("\u001B[31m");
        System.out.println("'add': add a staff or trainee to the system." +
                "\n'remove': remove staff or trainee from the system." +
                "\n'edit': edit a staff or trainee previously added to the system." +
                "\n'exit': Quit the program");
        menu();

    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean menuBool = false;
        while (menuBool = true) {

            switch (scanner.nextLine()) {

                case "add":
                    RegisterStaff();
                case "remove":
                case "edit":
                case "exit":
                    System.exit(0);
            }
        }
    }

    public static void RegisterStaff() {
        Scanner scanner = new Scanner(System.in);
        String[] queryBuilder = new String[6];
        System.out.println("Choose which to create:\n'1': Staff. '2': Trainee");
        String userType = "";

        switch (scanner.nextLine()) {
            case "1" -> { // Create staff
                System.out.println("Creating: Staff");
                String[] staffQuery = {"ID: ", "Name: ", "Gender: ", "Salary: "};
                for (int i = 0; i < staffQuery.length; i++) {
                    System.out.print(staffQuery[i]);
                    String Answer = scanner.nextLine();
                    queryBuilder[i] = Answer;
                }
                System.out.println("Created new staff:\n");
                for (String i : queryBuilder) {
                    System.out.println(i);
                }
                return;
            }
            case "2" -> { // Create trainee
                System.out.println("Creating: Trainee");
                String[] traineeQuery = {"ID: ", "Name: ", "Gender: "};
                for (int i = 0; i < traineeQuery.length; i++) {
                    System.out.print(traineeQuery[i]);
                    String Answer = scanner.nextLine();
                    queryBuilder[i] = Answer;

                }
                System.out.println("Created new trainee:\n");
                for (String i : queryBuilder) {
                    System.out.println(i);
                }
                return;
            }
        }
    }
}
