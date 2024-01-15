package Bank.lib;
// This project is a Investment system,it is used to keep track of the stock price after each trading day. A user
//Enters a buyin price and the closing price on subsequent trading days. Then the earning are evaluated. The proccess stops when a negatie closing price is entered
import java.util.Scanner;
import java.text.*;

public class Trading {
    public static void main (String[] args){


        Scanner scan= new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Enter the buying price: ");
        double bPrice= scan.nextDouble();

        //We have to track how many days have passed after buying

        int day = 1;

        double cPrice=0.1;

        //We need to make sure the earning are not negative in order to continue the program

        while(true){
            System.out.println("Enter the closing price for day " + day + "(any negative value to leave: ");
            cPrice= scan.nextDouble();

            //we make sure the closing price is negative

            if (cPrice<0.0) break;

            //Now that we know the closing price isn't negative we can calculate the earnings

            double earning= cPrice-bPrice;
            if(earning>0){
                System.out.println("After day " + day + ", you earned " + df.format(earning) + "per share.");
            }else if (earning<0){
                System.out.println("After day " + day + ", you lost " + df.format(-earning)  + "per share.");
            }else{
                System.out.println("After day " + day + ", you have " + df.format(earning) + "no earnings per share.");
            }

            day++;
        }

        scan.close();


    }
}
