class ModelWeightsCache {
    private static volatile ModelWeightsCache instance;

    private ModelWeightsCache(){
        System.out.println("Loading 2GB model weights into memory...(Cached successfully)");
    }

    public static ModelWeightsCache getInstance(){
        if(instance==null){
            synchronized(ModelWeightsCache.class){
                if(instance==null){
                    instance=new ModelWeightsCache();
                }
            }
        }
        return instance;
    }

    public void predict(String data){
        System.out.println("Running interface on: "+data);
    }
}

public class Model {
    public static void main(String[] args){
        ModelWeightsCache m=ModelWeightsCache.getInstance();
        m.predict("im.png");

        ModelWeightsCache m2=ModelWeightsCache.getInstance();
        m2.predict("sdaklfj.png");
    }
}
