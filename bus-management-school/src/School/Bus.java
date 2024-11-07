package School;

import java.util.ArrayList;

public class Bus {

    private final int busNumber;
    private Driver busDriver;
    protected ArrayList<Person> passengers = new ArrayList<>();
    protected int capacity;


    public Bus(int busNumber, int capacity) {

        this.busNumber = busNumber;
        this.capacity = capacity;

    }


    //Compile time polymorphism
    public void assign(Driver driver) {
        //Assign the bus driver to the bus
        if (checkPassengerIsExistOnAnotherBus(driver)) {

            if (busDriver == null) {

                this.busDriver = driver;
                driver.assignBusNumber(getBusNumber());
                System.out.println("driver " + driver.getName() + " assigned successfully on bus number " + busNumber);

            } else {

                System.out.println("Already bus driver " + this.busDriver.getName() + " is There");

            }
        } else {

            System.out.println(driver.getName() + " You are already assigned to " + driver.getAssignedBusNumber());

        }

    }


    public void assign(Person passenger) {
        //Assign the passenger to the respective bus
        if (checkPassengerIsExistOnAnotherBus(passenger)) {
            int passengersCapacity = capacity - 1;

            if (passengersCapacity >= passengers.size()) {

                passengers.add(passenger);
                passenger.assignBusNumber(getBusNumber());
                System.out.println("passenger " + passenger.getName() + " assigned successfully on bus number " + busNumber);

            } else {

                System.out.println("Bus is Already full");

            }
        } else {

            System.out.println(passenger.getName() + " You are already assigned to " + passenger.getAssignedBusNumber());

        }
    }

    public void dropPerson(Person passenger) {
        //Person will get drop from the bus
        if (passengers.contains(passenger) == true) {

            passengers.remove(passenger);
            passenger.removeBusNumber();
            System.out.println("passenger " + passenger.getName() + " dropped successfully");

        } else {

            System.out.println("the Passenger " + passenger.getName() + " is not there in the bus");

        }

    }

    public boolean checkPassengerIsExistOnAnotherBus(Person p) {
        //To check the passenger is existed in another bus
        return p.getAssignedBusNumber() == null;
    }

    //Getters
    public int getBusNumber() {

        return busNumber;
    }

    public int getCapacity() {

        return capacity;
    }
}
