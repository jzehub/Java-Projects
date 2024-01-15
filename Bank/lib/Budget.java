package Bank.lib;
//A user inputs the amount available to spend. 
//Then the user inputs the proportions of the amount on each expense item. 
//The expense input can stop if the total proportion exceeds 100%. Finally, the program shows the amount spent on each expense item.


import java.util.Scanner;
import java.util.ArrayList;

public class Budget {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("How much do you want to spend? ");
        double sAmount = scan.nextDouble();
        double total=0;
        int i = 0;

        ArrayList<Double> proportion = new ArrayList<Double>();

        System.out.println("Enter your proportion: of various expenses: ");
        System.out.println("The system stops if cumulative proportion exceeds 100%");

        do{
            System.out.println("Your proportion of expense " + (i+1) + ":");

            double value=scan.nextDouble();
            proportion.add(value);
            total += proportion.get(i);
            i++;
        }while(total<=100);
        
        scan.close();

        if (total>100) {
            double cumulativeTotal =0.0;
            for(int j=0; j<proportion.size()-1;j++){
                cumulativeTotal += proportion.get(j);
            }

            proportion.set(proportion.size()-1, 100.0 - cumulativeTotal);
        }

        for(double value : proportion){
            double expense=value*total/100.0;
            System.out.println("Your " + value + "% equals $ " + expense);
        }





    }
    

}
