package School;

public class NormalBus extends Bus {

    public NormalBus(int busNumber, int capacity) {
        super(busNumber, capacity);
    }


    public void showBusDetails() {

        System.out.println("it is a Normal bus");
        System.out.println("The Bus Number " + getBusNumber());
        System.out.println("the capacity of the bus " + getCapacity());

    }
}
