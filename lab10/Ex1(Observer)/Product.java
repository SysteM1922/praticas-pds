package Ex1;
import java.util.List;
import java.util.ArrayList;

public class Product {
    private int prodCode;
    private static int prodCodeCount = 0;
    private String prodDescription;
    private double startPrice;// starting price
    private double price; // current price (changed during bidding process)
    private State prodState;
    private List<Observer> observers = new ArrayList<Observer>();
    private double auctionDuration = 0;

    public Product(String prodDescription, double startPrice) {
        this.prodCode = ++prodCodeCount;
        this.prodDescription = prodDescription;
        this.startPrice = startPrice;
        this.price = startPrice;
        this.prodState = State.STOCK;
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public State getState() {
        return this.prodState;
    }

    public void setState(State state) {
        this.prodState = state;
        if (this.prodState == State.AUCTION) {
            this.auctionDuration = System.nanoTime();
        } else {
            this.auctionDuration = System.nanoTime() - this.auctionDuration;
            System.out.printf("Product was in Auction during %f", this.auctionDuration);
            if (state == State.STOCK) {
                this.price = this.startPrice;
            }
        }
        notifyAllObservers(false);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double bidAmmount) {
        this.price = bidAmmount;
        notifyAllObservers(true);
    }

    private void notifyAllObservers(boolean newBid) {
        for (Observer observer : observers) {
            observer.update(this, newBid);
        }
    }

    @Override
    public String toString() {
        return prodCode + ": " + prodDescription;
    }
}
