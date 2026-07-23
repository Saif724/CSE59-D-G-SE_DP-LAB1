import java.util.ArrayList;
import java.util.Scanner;
class ShoppingCart{
    ArrayList<String> itemName=new ArrayList<>();
    ArrayList<Double> price=new ArrayList<>();
    ArrayList<Integer> quantity=new ArrayList<>();
   
    public void addItem(String name,double p,int qty){
        itemName.add(name);
        price.add(p);
        quantity.add(qty);
    }

    public void applyDiscount(Double percentage){
        percentage=percentage/100;
        ArrayList<Double> arr=new ArrayList<>();
        for(int i=0; i<price.size(); i++){
            double x=price.get(i);
            x=x- (x*percentage);
            arr.add(x);
        }
        price=arr;
    }
    public double calculateTotalWithTax(Double taxRate){
        taxRate=taxRate/100.0;
        double ans=0;
        for(int i=0; i<price.size(); i++){
            ans=ans+((price.get(i)+price.get(i)*taxRate)*quantity.get(i));
        
        }
        return ans;
    }
    public double Subtotal(){
        double ans=0;
        for(int i=0; i<price.size(); i++){
            ans=ans+(price.get(i)*quantity.get(i));
        }
        return ans;
    }
}

public class Main2 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        
        ShoppingCart sCart=new ShoppingCart();
        int n=sc.nextInt();
        while(n>0){
            String name=sc.next();
            Double price=sc.nextDouble();
            int quantity=sc.nextInt();

            sCart.addItem(name, price, quantity);
            n--;

        }

        System.out.println("Discount: ");

        Double discount=sc.nextDouble();

        System.out.println("Tax: ");

        Double tax=sc.nextDouble();

        System.out.println("Subtotal: "+sCart.Subtotal());
        
        sCart.applyDiscount(discount);
        
        System.out.println("Total after "+discount+"% discount and "+tax+"% tax: "+sCart.calculateTotalWithTax(tax));
        sc.close();
    }
}
