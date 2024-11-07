package School;

public  class Person {
    private String name;
    private String address;
    private int age;
    private char gender;
    private Integer assignedBusNumber ;

    public Person(String name, String address, int age, char gender) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }

    //abstract method


    //getters and setters
    public String getName() {

        return this.name;
    }

    public String getAddress() {

        return this.address;
    }

    public int getAge() {

        return this.age;
    }

    public char getGender() {

        return this.gender;
    }

    public Integer getAssignedBusNumber() {

        return assignedBusNumber;
    }

    public void assignBusNumber(int assignedBusNumber) {
        this.assignedBusNumber = assignedBusNumber;
    }

    public void removeBusNumber() {
        this.assignedBusNumber = null;
    }
}
