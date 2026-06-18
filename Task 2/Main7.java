
interface clickable {
    void onClick();
}
class Button implements clickable {
    public void onClick(){
        System.out.println("Button is clicked!");
    }
    
}
public class Main7{
    public static void main(String[] args){
        Button b=new Button();
        b.onClick();
    }
}
