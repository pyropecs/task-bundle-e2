import tasks.*;

public class Main {
public static void main(String[] args){
int[] nums = {2,3,4,5};
    Copy c1 = new Copy(2,nums);
Copy c2 = c1; //shallow copy only copying reference of the object
System.out.println(c2.equals(c1)); // true because object variable referencing to same object
Copy c3 = new Copy(c1); // deep copy using copy constructor
    System.out.println(c3.equals(c1));//false because newly object is created and values are copied into it

Wrapper w1 = new Wrapper(20,nums);
w1.displayWrapper();
Wrapper.display(); // calling static method it can be invoked by using class name itself 
w1.display(); // still static method can be invoked by object itself
String s = "test string";
String s2 ="test string";
System.out.println(s == s2); // it will return true because the string already present in string pool

String sObj = new String("test string");
String s2Obj = new String("test string");
System.out.println( s2Obj == sObj); // return false because it creates two object both are not related even though same string

String s3 = sObj.intern(); // s3 points to the interned String "test string" which is in string pool   
System.out.println( s == s3); 

}


}
