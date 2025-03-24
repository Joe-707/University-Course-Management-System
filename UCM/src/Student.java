public class Student extends Person {
    private String stdId;
    public Student(String name, String email, int birthYear, int birthMonth, int birthDay) {
        super(name, email, birthYear, birthMonth, birthDay);
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
