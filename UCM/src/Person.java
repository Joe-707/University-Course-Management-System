import java.time.*;
import java.util.*;


public abstract class Person {
    protected String name;
    protected String email;
    protected Date birthDate;


    public Person(String name, String email, Date birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge(Date birth) {
        birth=this.birthDate;
        LocalDate dob=birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dob,LocalDate.now()).getYears();
    }



    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Age: " + getAge(birthDate));

    }
}



