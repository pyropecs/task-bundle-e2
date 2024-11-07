package tasks;

public class Wrapper extends Copy {
private int digit = 10; 
private Integer digitInteger = digit; // autoboxing because it changes primitive type int type into wrapper class Integer  

    public Wrapper(int num ,int[] arr){

        super(num,arr);
    
}

    public void displayWrapper(){ // non-static method it will be called by object only
        System.out.println("protected variable num inherited from the copy class :" + num); // num is a protected variable which is accessible inside the package
        String s =  digitInteger.toString(); // converted Integer type to String type using Integer predefined methods
        System.out.println("Integer wrapper class method to converted to string:" + s);
        int digitDown = digitInteger; //unboxing wrapper class Integer to primitve int type
        System.out.println("the primitive int type: " + digitDown);
    }
    public static void display(){
        System.out.println("this is static method from class Wrapper"); // no need to create object it can be invoked using class name 
    }

}
