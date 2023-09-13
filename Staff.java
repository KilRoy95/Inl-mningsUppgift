import java.time.LocalDate;

public class Staff extends Person {
    private int salary;
    private LocalDate startDate;

    public Staff(int id, String name, String gender, int salary, LocalDate startDate) {
        super(id, name, gender);

        this.salary = salary;
        this.startDate = startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
