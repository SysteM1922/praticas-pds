package Ex1;
import java.util.ArrayList;

abstract class Observer {
    public abstract void update(Product p, boolean newBid);

    protected ArrayList<Product> auctionProducts;
}
