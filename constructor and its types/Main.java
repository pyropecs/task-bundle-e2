

public class Main {
    public static void main(String[] args) {
        Hotel h1 = new Hotel();
        Hotel h2 = new Hotel("Luxury Hotel", 5000);
        h2.displayHotelDetails();
        Hotel h3 = h2;
        System.out.println( h3.equals(h2) );
        Hotel h4 = new Hotel(h2);
        h4.displayHotelDetails();
        System.out.println(h4.equals(h2));
    }

}


class Hotel {
    private String name;
    private int price;


    public Hotel() {
        //default constructor
        System.out.println("default constructor is called");

    }

    public Hotel(String name, int price) {
        //parameterized constructor
        this.name = name;
        this.price = price;
        System.out.println("parameterized constructor is called");
    }

    public Hotel(Hotel anotherHotel) {
        //copy constructor
        this.name = anotherHotel.name;
        this.price = anotherHotel.price;
        System.out.println("copy constructor is called");
    }

    public void displayHotelDetails() {
        System.out.println("the Hotel name is " + name + " The price is " + price);
    }


}
