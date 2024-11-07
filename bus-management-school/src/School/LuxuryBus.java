package School;

import java.util.ArrayList;

public class LuxuryBus extends Bus {
    private boolean airConditioning;

    public LuxuryBus(int busNumber, int capacity) {

        super(busNumber, capacity);

    }

    @Override
    public void assign(Person passenger) {
        if (checkPassengerIsExistOnAnotherBus(passenger)) {

            if (checkEligibilityForPassengers(passenger)) {
                int passengersCapacity = capacity - 1; // subtracting 1 for driver seat
                if (passengersCapacity >= passengers.size()) {

                    passengers.add(passenger);
                    passenger.assignBusNumber(getBusNumber());
                    System.out.println("passenger " + passenger.getName() + " assigned successfully on bus number " + getBusNumber());

                } else {

                    System.out.println("Bus is Already full");
                }

            } else {

                System.out.println("you are not allowed to board this bus");
            }

        } else {

            System.out.println(passenger.getName() + " You are already assigned to " + passenger.getAssignedBusNumber());
        }
    }

    public void enableAirConditioning() {
        airConditioning = true;
    }

    public void disableAirConditioning() {
        airConditioning = false;
    }

    private boolean checkEligibilityForPassengers(Person p) {
        //To check the only eligible passenger are boarding that is only teachers are eligible for luxury bus
        if (!(p instanceof Teacher)) {
            System.out.println("Sorry " + p.getName() + " you must be a teacher to offer this luxury bus");
            return false;
        }

        return true;
    }

    public void showBusDetails() {
        System.out.println("An luxury bus with " + (airConditioning ? "AC" : "Non-AC"));
        System.out.println("The Bus Number " + getBusNumber());
        System.out.println("the capacity of the bus " + getCapacity());
    }
}


