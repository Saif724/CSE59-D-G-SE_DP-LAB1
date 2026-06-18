
class Printer{
    void printData(String s){
        System.out.println(s);
    }
    void printData(int num){
        System.out.println(num);
    }
}
public class Main5{
    public static void main(String[] args){
        Printer p=new Printer();
        p.printData("fjdklsj");
        p.printData(348957);
    }
}
