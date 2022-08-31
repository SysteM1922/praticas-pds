package Ex1;
import java.util.ArrayList;

public class Manager extends Observer {
    private String nameManager;

    protected ArrayList<Product> stockProducts;
    protected ArrayList<Product> salesProducts;

    public Manager(String nameManager) {
        this.nameManager = nameManager;
        this.auctionProducts = new ArrayList<Product>();
        this.stockProducts = new ArrayList<Product>();
        this.salesProducts = new ArrayList<Product>();
    }

    public void register_Product(Product prod) {
        if (prod.getState() == State.AUCTION) {
            this.auctionProducts.add(prod);
        } else if (prod.getState() == State.STOCK) {
            this.stockProducts.add(prod);
        } else if (prod.getState() == State.SALES) {
            this.salesProducts.add(prod);
        }

        prod.attach(this);
    }

    @Override
    public void update(Product prod, boolean newBid) {
        if (newBid == true) {
            System.out.printf("Anouncement for Manager %s --> Product %s has a new bid --> %f\n", this.nameManager,
                    prod.toString(), prod.getPrice());
        } else {
            if (prod.getState() == State.AUCTION) {
                this.auctionProducts.add(prod);
                this.stockProducts.remove(prod);
            } else if (prod.getState() == State.STOCK) {
                this.stockProducts.add(prod);
                this.auctionProducts.remove(prod);
            } else if (prod.getState() == State.SALES) {
                this.salesProducts.add(prod);
                this.auctionProducts.remove(prod);
                System.out.printf("Anouncement for Manager %s --> Product %s has been sold --> %f\n", this.nameManager,
                        prod.toString(), prod.getPrice());
            }
        }
    }

}
