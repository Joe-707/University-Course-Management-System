import java.time.LocalDate;

public abstract class Person {
    protected String name;
    protected String email;
    protected int birthYear;
    protected int birthMonth;
    protected int birthDay;

    public Person(String name, String email, int birthYear, int birthMonth, int birthDay) {
        this.name = name;
        this.email = email;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int calculateAge() {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        int currentDay = LocalDate.now().getDayOfMonth();
        int age = 0;
        if (birthYear > 1850 && birthMonth <= 12 && birthDay <= 31) {
            if (birthMonth > currentMonth) {
                age = (currentYear - birthYear) - 1;
            } else if (birthMonth == currentMonth) {
                if (birthDay > currentDay) {
                    age = (currentYear - birthYear) - 1;
                } else {
                    age = (currentYear - birthYear);
                }
            } else {
                age = (currentYear - birthYear);
            }
        } else {
            System.out.println("Invalid birth credentials");
        }
        return age;
    }
    public int getBirthYear() {
        return birthYear;
    }
    public int getBirthMonth() {
        return birthMonth;
    }
    public int getBirthDay() {
        return birthDay;
    }
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Birth Year: " + birthYear);
        System.out.println("Birth Month: " + birthMonth);
        System.out.println("Birth Day: " + birthDay);

    }
}



