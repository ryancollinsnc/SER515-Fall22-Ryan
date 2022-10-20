import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Facade Pattern called
        Facade object = new Facade();
        object.createProductList();
        int option;
        do {
            System.out.println("\n\n1) LOGIN PAGE\n2) REMINDER\n3) EXIT");
            System.out.print("Enter option: ");
            Scanner inp = new Scanner(System.in);
            option = inp.nextInt();
            switch (option) {
                case 1:
                    boolean verify = object.login();
                    if (!verify) {
                        System.out.println("Login Failed");
                        continue;
                    }
                    object.createUser();
                    object.attachProductToUser();
                    object.productOperation();
                    break;

                case 2:
                    break;
            }
        }while(option!=3);
    }

        /*try{
            for(int i=0; i<10; i++){
                Product p = object.thePerson.productList.get(i);
                System.out.println(p.name + " " + p.category);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }*/
}
