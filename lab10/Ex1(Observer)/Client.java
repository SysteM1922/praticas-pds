package Ex1;
import java.util.ArrayList;

public class Client extends Observer {
    private String name;

    public Client(String name) {
        this.name = name;
        this.auctionProducts = new ArrayList<Product>();
    }

    public void bid(Product prod, double bidAmmount) {
        if (!this.auctionProducts.contains(prod)) {
            if (prod.getState() == State.AUCTION) {
                prod.attach(this);
                this.auctionProducts.add(prod);
            } else {
                System.out.println("This item is no in auction at the moment!");
                return;
            }
        }

        if (prod.getPrice() > bidAmmount) {
            System.out.println("Bid too low! Bid should be at least " + prod.getPrice());
            return;
        }

        // if reaches this point bid is valid
        prod.setPrice(bidAmmount);

    }

    @Override
    public void update(Product prod, boolean newBid) {
        // Inform client about the new bid
        if (newBid == true) {
            System.out.printf("Annoucement for Client %s --> Product %s has a new bid --> %f\n", this.name,
                    prod.toString(), prod.getPrice());
        } else {
            if (prod.getState() == State.SALES) {
                System.out.printf("Annoucement for Client %s --> Product %s has been sold --> %f\n", this.name,
                        prod.toString(), prod.getPrice());
            } else if (prod.getState() == State.STOCK) {
                System.out.printf("Annoucement for Client %s --> Product %s stoped being in the auction", this.name,
                        prod.toString());
            }
        }

    }
}
