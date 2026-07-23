import java.util.ArrayList;
import java.util.Scanner;
class Course{
    String subjectName;
    ArrayList<String> stuName=new ArrayList<>();
    ArrayList<Double> stuMarks=new ArrayList<>();
    public Course(String subjectName){
        this.subjectName=subjectName;
    }
    public void addStudent(String name,double marks){
        stuName.add(name);
        stuMarks.add(marks);
    }
    public String getHigherScorerName(){
        Double mx=0.0;
        String name="";
        for(int i=0; i<stuMarks.size(); i++){
            if(stuMarks.get(i)>mx){
                mx=stuMarks.get(i);
                name=stuName.get(i);
            }
        }
        return name;
    }
    public Double getHigherScorerMark(){
        Double mx=0.0;
        for(int i=0; i<stuMarks.size(); i++){
            if(stuMarks.get(i)>mx){
                mx=stuMarks.get(i);
            }
        }
        return mx;
    }
    public int countTopGrades(double threshold){
        int cnt=0;
        for(int i=0; i<stuMarks.size(); i++){
            if(stuMarks.get(i)>=threshold){
                cnt++;
            }
        }
        return cnt;
    }
}

public class Main1 {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);

        String subjectName=sc.next();
        
        Course crs=new Course(subjectName);
        int n=sc.nextInt();
        while(n>0){
            String subName=sc.next();
            Double subMark=sc.nextDouble();

            crs.addStudent(subName, subMark);
            n--;

        }

        System.out.println("Highest Scorer: "+crs.getHigherScorerName()+" ("+crs.getHigherScorerMark()+")");
        System.out.println("Number of A+(80+): "+crs.countTopGrades(80.0));
        sc.close();
    }
}
