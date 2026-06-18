
class Sensor{
    String  name;
    int batteryLevel;
    void charge(){
        batteryLevel=100;
        System.out.println("Sensor fully Charged!");
    }
}
public class Main4{
    public static void main(String[] args){
        Sensor s=new Sensor();
        s.charge();
    }
}
