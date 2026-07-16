class ConfigurationManager {
    private static volatile ConfigurationManager instance;

    private ConfigurationManager(){
        System.out.println("Reading heavy config file from disk... (Only happens once)");
    }

    public static ConfigurationManager getInstance(){
        if( instance == null ){
            synchronized(ConfigurationManager.class){
                if(instance == null){
                    instance=new ConfigurationManager();
                }
            }
        }
        return instance; 
    }
}

public class ConfigManager {
    public static void main(String[] args){
        ConfigurationManager cm= ConfigurationManager.getInstance();
        ConfigurationManager cm2= ConfigurationManager.getInstance();

        System.out.println("Same instance in memory? "+ (cm==cm2));
        
    }
}
