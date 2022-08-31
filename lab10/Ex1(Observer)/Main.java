package Ex1;
public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("PS1 1st editon", 500.0);
        Product p2 = new Product("Noite estrelada", 30000.0);
        Product p3 = new Product("taco de golfe", 10.0);
        Product p4 = new Product("anel diamantes", 3500.0);

        Client c1 = new Client("Zé António");
        Client c2 = new Client("DiCaprio");
        Client c3 = new Client("Ricky Gervais");

        Manager mg = new Manager("Neil Patrick Harris");
        mg.register_Product(p1);
        mg.register_Product(p2);
        mg.register_Product(p3);
        mg.register_Product(p4);

        p1.setState(State.AUCTION);
        p3.setState(State.AUCTION);

        c1.bid(p1, 500.4);
        System.out.println("");
        c1.bid(p3, 20);
        System.out.println("");

        p4.setState(State.AUCTION);

        c2.bid(p1, 520.6);
        System.out.println("");

        c1.bid(p1, 600);
        System.out.println("");

        c3.bid(p4, 4242.42);
        System.out.println("");

        p1.setState(State.SALES);
        c2.bid(p1, 10);

    }
}