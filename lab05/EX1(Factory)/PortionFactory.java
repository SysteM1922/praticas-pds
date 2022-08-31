import java.util.Scanner;

public class PortionFactory {

    public static Portion create(String type, Temperature temp) {
        if (type.equals("Beverage")) {
            if (temp.equals(Temperature.WARM)) {
                return new Beverage("Milk", temp);
            } else {
                Scanner sc = new Scanner(System.in);
                System.out.print("Insira a fruta para o seu FruitJuice: ");
                String fruit = sc.nextLine();
                System.out.println("");
                sc.close();
                return new Beverage("FruitJuice", temp, fruit);
            }
        } else if (type.equals("Meat")) {
            if (temp.equals(Temperature.COLD)) {
                return new Meat("Tuna", temp);
            } else {
                return new Meat("Pork", temp);
            }
        }
        return null;
    }
}
