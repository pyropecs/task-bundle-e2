package tasks;

public class Copy {
protected int num;
private int[] arr;
public Copy(int num,int[] arr){
    this.num = num;
    this.arr = arr;
}



public Copy(Copy c){
//deep copy
this.num = c.num;
this.arr= c.arr;
}

}
