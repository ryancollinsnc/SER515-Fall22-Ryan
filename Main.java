import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Facade Pattern called
        int tmp=-1;
        Facade fc = new Facade();
        fc.createProductList();
        while(tmp!=2) {
            System.out.println("1) Login to Buyer / Seller Account");
            System.out.println("2) Quit Program");
            System.out.print("Enter option: ");
            Scanner sc = new Scanner(System.in);
            tmp = sc.nextInt();
            if (tmp==1) {
                if (!fc.login()) {
                    System.out.println("Login has Failed - Try Again");
                }else {
                    //Facade Design Pattern
                    fc.createUser();
                    fc.attachProductToUser();
                    fc.productOperation();
                }
            }
        }
        Reminder rem = new Reminder(fc);
        rem.remind();
    }
}
