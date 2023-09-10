import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static ArrayList<Staff> staffList = new ArrayList<>();

    public static ArrayList<Trainee> traineeList = new ArrayList<>();

    public static void main(String[] args) {

        // Create preset staff
        LocalDate currentDate = LocalDate.now();
        staffList.add(new Staff(100, "Leeroy Jenkins", "Human Paladin", 30000, currentDate));
        staffList.add(new Staff(101, "Hanz Fritz", "khajiit", 20500, currentDate));
        staffList.add(new Staff(102, "Luigi", "Game character", 15000, currentDate));

        traineeList.add(new Trainee(103, "Link", "Game Character", LocalDate.of(2024, 9, 8), "Great swordsmanship"));
        traineeList.add(new Trainee(104, "Leon", "Zombie apocalypse survivor", LocalDate.of(2025, 5, 3), "Great zombie slayer"));

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
                    "\n'stafflist: Show all staff and trainees" +
                    "\n'exit': Quit the program");
            System.out.println("\u001B[0m");

            switch (scanner.nextLine()) {
                case "add" -> {
                    RegisterStaff();
                    break;
                }
                case "remove" -> {
                    RemoveStaff("");
                    break;
                }
                case "edit" -> {
                    EditStaff();
                    break;
                }
                case "stafflist" -> {
                    StaffList();
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

    public static void RemoveStaff(String ifMessage) {

        System.out.println(
                "\nStaff:");
        for (Staff obj : staffList) {
            System.out.println(
                    "Name: " + obj.getName() + " ID: " + obj.getId() + " Gender: " + obj.getGender()
            );
        }

        System.out.println(
                "\nTrainee:");
        for (Trainee obj : traineeList) {
            System.out.println(
                    "Name: " + obj.getName() + " ID: " + obj.getId() + " Gender: " + obj.getGender()
            );
        }
        if (ifMessage != null)  {
            System.out.print(ifMessage);
        }

        boolean found = false;
        Object foundItem = "null";

        System.out.print("\nID: ");
        Scanner scanner = new Scanner(System.in);
        String searchValue = scanner.nextLine();

        try {
            for (Staff item : staffList) {
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = "ID: " + item.getId() + "\nNAME: " + item.getName();
                    break;
                }
            }

            for (Trainee item : traineeList) {
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = "ID: " + item.getId() + "\nNAME: " + item.getName();
                    break;
                }
            }
        } catch (NumberFormatException e){
            RemoveStaff("Only ID-numbers accepted!");
        }

        if (found) {
            System.out.print(foundItem + "\n\u001B[31mRemove?\u001B[0m");
            System.out.print(" (y/n): ");
            switch (scanner.nextLine()) {
                case "y", "Y":
                    staffList.removeIf(staff -> staff.getId() == (Integer.parseInt(searchValue)));

                case "n", "N":
                    break;
            }

        } else {
            System.out.println("Not found!");

            System.out.print("\npress ENTER to return...");
            scanner.nextLine();
        }
    }

    public static void EditStaff() {

    }

    public static void StaffList() {

        System.out.println(
                "\nStaff:");
        for (Staff obj : staffList) { // List all staff
            System.out.println(
                    "Name: " + obj.getName() + " ID: " + obj.getId() + " Gender: " + obj.getGender()
            );
        }
        System.out.println(
                "\nTrainee:");
        for (Trainee obj : traineeList) { // List all trainees
            System.out.println(
                    "Name: " + obj.getName() + " ID: " + obj.getId() + " Gender: " + obj.getGender()
            );
        }

        System.out.println("Total: " + (staffList.size() + traineeList.size()) +
                " \nStaff: " + staffList.size() +
                " \nTrainees: " + traineeList.size());

        System.out.print("\npress ENTER to return...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}