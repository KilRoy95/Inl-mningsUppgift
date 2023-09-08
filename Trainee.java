import java.time.LocalDate;

public class Trainee extends Person{
    private LocalDate endDate;
    private String credentials;

    public Trainee(int id, String name, String gender, LocalDate endDate, String credentials) {
        super(id, name, gender);

        this.endDate = endDate;
        this.credentials = credentials;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}


