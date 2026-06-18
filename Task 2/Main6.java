
class Applet{
    void launch(){
        System.out.println("Applet launched!");
    }
}
class GameApplet extends Applet {
    void play(){
        System.out.println("Playing Game!");
    }
}
public class Main6{
    public static void main(String[] args){
        GameApplet ga=new GameApplet();
        ga.play();
        ga.launch();
    }
}
