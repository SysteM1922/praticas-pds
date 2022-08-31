public class Main {
	public static void main(String[] args) {
		Chef chef = new Chef().setSucessor(new SushiChef().setSucessor(new PastaChef()
				.setSucessor(new BurgerChef().setSucessor(new PizzaChef().setSucessor(new DessertChef())))));
		chef.request("veggie burger");
		chef.request("Pasta Carbonara");
		chef.request("PLAIN pizza, no toppings!");
		chef.request("sushi nigiri and sashimi");
		chef.request("salad with tuna");
		chef.request("strawberry ice cream and waffles dessert");
	}
}
