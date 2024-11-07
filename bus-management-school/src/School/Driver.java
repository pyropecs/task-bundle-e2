package School;

public class Driver extends Person {
    private int licenseNumber;
    private double yearsOfExperience;

    public Driver(String name, String address, int age, char gender, int licenseNumber, double yearsOfExperience) {
        super(name, address, age, gender);
        this.licenseNumber = licenseNumber;
        this.yearsOfExperience = yearsOfExperience;

    }


    public void about() {
        System.out.println(" Driver Name:" + getName() + "\n Driving Experience:" + yearsOfExperience + "\n License number" + licenseNumber
                + "\n Address:" + getAddress() + "\n Age:" + getAge() + "\n Gender:" + getGender());
    }


}
