package School;

public class Teacher extends Person {
    private int employeeNumber;
    private String employeeDegree;
    private double salary;

    public Teacher(String name, String address, int age, char gender, int employeeNumber, String employeeDegree, double salary) {

        super(name, address, age, gender);
        this.employeeNumber = employeeNumber;
        this.employeeDegree = employeeDegree;
        this.salary = salary;

    }


    public void about() {

        System.out.println(" Teacher Name: " + getName() + "\n Degree:"
                + employeeDegree + "\n Employee Number:" + employeeNumber + " \n Salary:" + salary +
                " \n Address:" + getAddress() + "\n Age:" + getAge() + "\n My gender:" + getGender());

    }


}
