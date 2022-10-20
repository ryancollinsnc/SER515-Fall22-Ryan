import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // Facade Pattern called
        Facade object = new Facade();
        object.createProductList();
        boolean verify = object.login();
        if(!verify){
            System.out.println("Login Failed");
            System.exit(0);
        }
        object.createUser();
        object.attachProductToUser();
        object.productOperation();
        object.productOperation();


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
}
