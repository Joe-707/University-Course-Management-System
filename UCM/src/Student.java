import java.util.*;
public class Student extends Person {
    private String stdId;
    public Student(String name, String email, Date birthDate) {
        super(name, email, birthDate);
        this.stdId = "";
    }

    public String getStdId() {
        return stdId;
    }
    @Override
    public void display() {
        super.display();
        System.out.println("Student ID: " + stdId);
    }

}
