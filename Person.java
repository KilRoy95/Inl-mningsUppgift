public class Person {
    private int id = 0;
    private String name;
    private String gender;

    public Person(int id, String name, String gender) {
        this.id = ++id;
        this.name = name;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}