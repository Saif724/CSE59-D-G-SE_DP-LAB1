class NumberChecker{
    void check(int num){
        System.out.println(num%2==0 && num>10);
    }
}
public class Main2{
    public static void main(String[] args){
        NumberChecker nc=new NumberChecker();
        nc.check(238746);
    }
}
