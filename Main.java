import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final String reset = "\u001B[0m";
    public static final String inst = "\u001B[31m";
    public static final String info = "\u001B[32m";
    public static final String sel = "\u001B[33m";
    public static final String data = "\u001B[36m";

    public static List<Staff> staffList = new ArrayList<>();

    public static List<Trainee> traineeList = new ArrayList<>();

    public static void main(String[] args) {

        // Preset staff
        LocalDate currentDate = LocalDate.now();
        staffList.add(new Staff(99, "Greger", "Male", 37000, currentDate));
        staffList.add(new Staff(100, "Hans", "Male", 25000, currentDate));
        staffList.add(new Staff(101, "Louise", "Female", 15000, currentDate));
        staffList.add(new Staff(102, "Isabell", "Female", 20000, currentDate));

        traineeList.add(new Trainee(103, "Jocke", "Male", LocalDate.of(2024, 9, 8), "Great swordsmanship"));
        traineeList.add(new Trainee(104, "Jonte", "Male", LocalDate.of(2025, 5, 3), "Great zombie slayer"));

        menu();
    }

    public static void menu() {

        Scanner scanner = new Scanner(System.in);
        boolean menuBool = false;
        do {
            System.out.println(sel + "'add': " + inst + "add a staff or trainee to the system." +
                    sel + "\n'remove': " + inst + "remove staff or trainee from the system." +
                    sel + "\n'edit': " + inst + "edit a staff or trainee previously added to the system." +
                    sel + "\n'stafflist: " + inst + "Show all staff and trainees" +
                    sel + "\n'exit': " + inst + "Quit the program" + reset);

            switch (scanner.nextLine()) {
                case "add" -> {
                    RegisterStaff();
                    break;
                }
                case "remove", "erase" -> {
                    RemoveStaff("");
                    break;
                }
                case "edit", "change" -> {
                    EditStaff("");
                    break;
                }
                case "stafflist", "staff", "list" -> {
                    StaffList();
                    break;
                }
                case "exit", "quit" -> System.exit(0);
            }

        } while (!menuBool);
    }

    public static void RegisterStaff() {
        Scanner scanner = new Scanner(System.in);
        String[] queryBuilder = new String[6];
        System.out.println(inst + "Choose which to create:" + sel + "\n'1':" + inst + " Staff " + sel + "'2': " + inst + "Trainee");

        switch (scanner.nextLine()) {
            case "1" -> { // Create staff
                System.out.println(inst + "Creating: Staff" + reset);
                String[] staffQuery = {"Name: ", "Gender: ", "Salary: "};

                for (int i = 0; i < staffQuery.length; i++) {
                    System.out.print(staffQuery[i]);
                    String Answer = scanner.nextLine();
                    queryBuilder[i] = Answer;
                }

                LocalDate currentDate = LocalDate.now();
                Staff staff = new Staff(
                        ((staffList.size() + traineeList.size()) + 100),
                        queryBuilder[0],
                        queryBuilder[1],
                        Integer.parseInt(queryBuilder[2]),
                        currentDate);
                staffList.add(staff);

                staffList.sort(Comparator.comparing(Staff::getStartDate));
                System.out.println(inst + "\nNew staff created:" +
                        sel + " ID: " + data + staff.getId() +
                        sel + "  Name: " + data + staff.getName() +
                        sel + "  Gender: " + data + staff.getGender() +
                        sel + "  Salary: " + data + staff.getSalary() +
                        sel + "  Start-Date: " + data + staff.getStartDate() + "\n");

            }

            case "2" -> { // Create trainee
                System.out.println(inst + "Creating: Trainee" + reset);
                String[] traineeQuery = {"Name: ", "Gender: ", "End-Date: ", "Credentials: "};

                for (int i = 0; i < traineeQuery.length - 1; i++) {
                    System.out.print(traineeQuery[i]);
                    String Answer = scanner.nextLine();
                    if (i == 2) {
                        System.out.print(data + "Example: yyyy-mm-dd\n" + reset);
                    }

                    queryBuilder[i] = Answer;

                }

                Trainee trainee = new Trainee(
                        ((staffList.size() + traineeList.size()) + 100),
                        queryBuilder[0],
                        queryBuilder[1],
                        LocalDate.parse(queryBuilder[2]),
                        "");
                traineeList.add(trainee);
                traineeList.sort(Comparator.comparing(Trainee::getEndDate));
                System.out.println(inst + "New trainee created:" +
                        data + " ID: " + sel + trainee.getId() +
                        data + "  Name: " + sel + trainee.getName() +
                        data + "  Gender: " + sel + trainee.getGender() +
                        data + "  End-Date: " + sel + trainee.getEndDate() +
                        data + "  Credentials: " + info + trainee.getCredentials() + "\n");

            }
        }
    }

    public static void RemoveStaff(String ifMessage) {

        System.out.println(
                inst + "\nStaff:");
        for (Staff obj : staffList) {
            System.out.println(
                    sel + "Name: " + data + obj.getName() + sel + " ID: " + data + obj.getId() + sel + " Gender: " + data + obj.getGender() + reset);
        }

        System.out.println(
                inst + "\nTrainee:");
        for (Trainee obj : traineeList) {
            System.out.println(
                    sel + "Name: " + data + obj.getName() + sel + " ID: " + data + obj.getId() + sel + " Gender: " + data + obj.getGender() + reset);
        }
        if (ifMessage != null) {
            System.out.print(ifMessage);
        }

        boolean found = false;
        Object foundItem = null;

        System.out.print("\nID: ");
        Scanner scanner = new Scanner(System.in);
        String searchValue = scanner.nextLine();

        try {
            for (Staff item : staffList) {
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = item;
                    break;
                }
            }

            for (Trainee item : traineeList) {
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = item;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            RemoveStaff(inst + "(Only ID-numbers accepted!)");
        }

        if (found) {

            if (foundItem instanceof Staff staffItem) { // If foundItem is Staff-object

                System.out.println(inst + "\nSelected item:" +
                        sel + "\nID: " + data + staffItem.getId() +
                        sel + "\nNAME: " + data + staffItem.getName() +
                        sel + "\nGender: " + data + staffItem.getGender() +
                        sel + "\nSalary: " + data + staffItem.getSalary() +
                        sel + "\nStart-Date: " + data + staffItem.getStartDate());
                System.out.print(inst + "Remove?" + sel + " (y/n): ");

                switch (scanner.nextLine()) {
                    case "y", "Y":
                        staffList.removeIf(staff -> staff.getId() == (Integer.parseInt(searchValue)));
                        System.out.println(inst + "Staff removed: " + sel + "\nID: " + data + staffItem.getId() +
                                sel + " NAME: " + data + staffItem.getName() +
                                sel + " Gender: " + data + staffItem.getGender() +
                                sel + " Salary: " + data + staffItem.getSalary() +
                                sel + " Start-Date: " + data + staffItem.getStartDate() + "\n");
                    case "n", "N":
                        break;
                }
            } else { // Else foundItem is Trainee-object
                Trainee traineeItem = (Trainee) foundItem;
                System.out.println(inst + "\nSelected item:" +
                        sel + "\nID: " + data + traineeItem.getId() +
                        sel + "\nNAME: " + data + traineeItem.getName() +
                        sel + "\nGender: " + data + traineeItem.getGender() +
                        sel + "\nSalary: " + data + traineeItem.getEndDate() +
                        sel + "\nStart-Date: " + data + traineeItem.getCredentials());
                System.out.print(inst + "Remove?" + sel + " (y/n): ");

                switch (scanner.nextLine()) {
                    case "y", "Y":
                        traineeList.removeIf(trainee -> trainee.getId() == (Integer.parseInt(searchValue)));
                        System.out.println(inst + "Trainee removed: " + sel + "\nID: " + data + traineeItem.getId() +
                                sel + " NAME: " + data + traineeItem.getName() +
                                sel + " Gender: " + data + traineeItem.getGender() +
                                sel + " Salary: " + data + traineeItem.getEndDate() +
                                sel + " Start-Date: " + data + traineeItem.getCredentials() + "\n");
                    case "n", "N":
                        break;
                }
            }


        } else {
            System.out.println("Not found!");

            System.out.println("\npress ENTER to return...");
            scanner.nextLine();
        }
    }

    public static void EditStaff(String ifMessage) {

        System.out.println(
                inst + "\nStaff:");
        for (Staff obj : staffList) { // Write staffList to console
            System.out.println(
                    sel + "Name: " + data + obj.getName() +
                            sel + " ID: " + data + obj.getId() +
                            sel + " Gender: " + data + obj.getGender() +
                            sel + " Salary: " + data + obj.getSalary() +
                            sel + " End-Date: " + data + obj.getStartDate()
                            + reset);
        }

        System.out.println(
                inst + "\nTrainee:");
        for (Trainee obj : traineeList) { // write traineeList to console
            System.out.println(
                    sel + "Name: " + data + obj.getName() +
                            sel + " ID: " + data + +obj.getId() +
                            sel + " Gender: " + data + obj.getGender() +
                            sel + " End-Date: " + data + obj.getEndDate() +
                            sel + " Credentials: " + data + obj.getCredentials()
                            + reset);
        }
        if (ifMessage != null) { // Custom error message
            System.out.print(ifMessage);
        }


        System.out.print(inst + "\nSelect by ID");
        System.out.print(reset + "\nID: ");

        boolean found = false;
        Object foundItem = null;
        Scanner scanner = new Scanner(System.in);
        String searchValue = scanner.nextLine(); // Input value for search

        try {
            for (Staff item : staffList) { // Look for searchValue in staffList
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = item;

                    break;
                }
            }

            for (Trainee item : traineeList) { // Look for searchValue in traineeList
                if (item.getId() == Integer.parseInt(searchValue)) {
                    found = true;
                    foundItem = item;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            EditStaff("Only ID-numbers accepted!");
        }


        if (found) {

            if (foundItem instanceof Staff staffItem) {

                System.out.println(inst + "\nSelected item: " +
                        sel + "\nID: " + data + staffItem.getId() +
                        sel + "\nNAME: " + data + staffItem.getName() +
                        sel + "\nGender: " + data + staffItem.getGender() +
                        sel + "\nSalary: " + data + staffItem.getSalary() +
                        sel + "\nStart-Date: " + data + staffItem.getStartDate() + "\n");
                System.out.println(inst + "Choose by typing " + sel + "'Name', 'Gender', 'Salary'");

                switch (scanner.nextLine().toLowerCase()) {

                    case "name" -> {
                        System.out.print("Current: " + staffItem.getName() + " New Name: ");
                        String inputString = scanner.nextLine();
                        staffItem.setName(inputString);
                    }

                    case "gender", "gen" -> {
                        System.out.println("Current: " + staffItem.getGender() + " New Gender: ");
                        String inputString = scanner.nextLine();
                        staffItem.setGender(inputString);
                    }

                    case "salary", "sal" -> {
                        System.out.println("Current: " + staffItem.getSalary() + " New Salary: ");
                        String inputString = scanner.nextLine();
                        staffItem.setSalary(Integer.parseInt(inputString));
                    }
                }

            } else if (foundItem instanceof Trainee traineeItem) {

                System.out.println(inst + "\nSelected item:" +
                        sel + "\nID: " + data + traineeItem.getId() +
                        sel + "\nNAME: " + data + traineeItem.getName() +
                        sel + "\nGender: " + data + traineeItem.getGender() +
                        sel + "\nEnd-Date: " + data + traineeItem.getEndDate() +
                        sel + "\nStart-Date: " + data + traineeItem.getCredentials() + "\n");

                System.out.println(inst + "Choose by typing " + sel + "'ID', 'Name', 'Gender', or 'Credentials'");

                switch (scanner.nextLine().toLowerCase()) {

                    case "name" -> {
                        System.out.print("Current: " + traineeItem.getName() + "\nNew Name: ");
                        String inputString = scanner.nextLine();
                        traineeItem.setName(inputString);
                    }

                    case "gender", "gen" -> {
                        System.out.println("Current: " + traineeItem.getGender() + "\nNew Gender: ");
                        String inputString = scanner.nextLine();
                        traineeItem.setGender(inputString);
                    }

                    case "credentials", "creds", "cred" -> {
                        System.out.println("Current: " + traineeItem.getCredentials() + "\nNew Credentials: ");
                        String inputString = scanner.nextLine();
                        traineeItem.setCredentials(inputString);
                    }
                }
            }


        } else {
            System.out.println("Not found!");

            System.out.println(inst + "\npress ENTER to return...");
            scanner.nextLine();
        }

    }

    public static void StaffList() {

        System.out.println(
                inst + "\nStaff:");

        for (Staff obj : staffList) { // Foreach Staff in staffList
            System.out.println(
                    sel + "Name: " + data + obj.getName() +
                            sel + " ID: " + data + obj.getId() +
                            sel + " Gender: " + data + obj.getGender() +
                            sel + " Salary: " + data + obj.getSalary() +
                            sel + " Start-Date " + data + obj.getStartDate()
            );
        }
        System.out.println(
                inst + "\nTrainee:");

        for (Trainee obj : traineeList) { // Foreach Trainee in traineeList
            System.out.println(
                    sel + "Name: " + data + obj.getName() +
                            sel + " ID: " + data + obj.getId() +
                            sel + " Gender: " + data + obj.getGender() +
                            sel + " End-Date: " + data + obj.getEndDate() +
                            sel + " Credentials: " + data + obj.getCredentials()
            );
        }

        // Show total amount of current members in lists
        System.out.println(sel + "\nTotal: " + data + (staffList.size() + traineeList.size()) +
                sel + "\n   Staff: " + data + staffList.size() +
                sel + "\n   Trainees: " + data + traineeList.size());

        // Create new lists sorted by gender
        List<Staff> maleStaff = staffList.stream().filter(staff -> "Male".equals(staff.getGender())).toList();
        List<Staff> femaleStaff = staffList.stream().filter(staff -> "Female".equals(staff.getGender())).toList();

        // Calculate average for both gender
        int maleAverageSalary = calculateAverageSalary(maleStaff);
        int femaleAverageSalary = calculateAverageSalary(femaleStaff);

        System.out.println("Average Salary for Male Staff: " + maleAverageSalary);
        System.out.println("Average Salary for Female Staff: " + femaleAverageSalary);

        System.out.print(inst + "\npress ENTER to return...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    // Calculate the average salary for a list of staff members
    private static int calculateAverageSalary(List<Staff> staffList) {
        if (staffList.isEmpty()) {
            return 0; // Return 0 if the list is empty to avoid division by zero
        }

        int totalSalary = staffList.stream()
                .mapToInt(Staff::getSalary)
                .sum();

        return totalSalary / staffList.size();
    }
}