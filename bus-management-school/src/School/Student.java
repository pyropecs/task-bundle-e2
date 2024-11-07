package School;

public class Student extends Person {
    private int rollNumber;
    private String _class;

    public Student(String name, String address, int age, char gender, int rollNumber, String _class) {

        super(name, address, age, gender);
        this.rollNumber = rollNumber;
        this._class = _class;

    }

    //Method overriding run-time polymorphism

    public void about() {

        System.out.println(" Student Name:" + getName() + "\n Class:" + _class
                + "\n Roll number:" + rollNumber +
                " \n Address:" + getAddress() + "\n Age:" + getAge() + "\n Gender:" + getGender());

    }

}
