
class CountDown{
    void start(){
        for(int i=5; i>0; i--){
            System.out.println(i+" ");
        }
        System.out.println("Go!");
    }
}
public class Main3{
    public static void main(String[] args){
        CountDown cd=new CountDown();
        cd.start();
    }
}
