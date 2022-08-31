package Ex2;

interface CakeBuilder {
	public void setCakeShape(Shape shape);
	public void addCakeLayer();
	public void addCreamLayer();
	public void addTopLayer();
	public void addTopping();
	public void addMessage(String m);
	public void createCake();
	public Cake getCake();
}

class ChocolateCakeBuilder implements CakeBuilder {
	protected Cake cake = new Cake();

	public void setCakeShape(Shape shape) {
		cake.setShape(shape);
	}

	public void addCakeLayer() {
		cake.setCakeLayer("Soft chocolate");
	};

	public void addCreamLayer() {};

	public void addTopLayer() {
		cake.setTopLayerCream(Cream.Whipped_Cream);
	};

	public void addTopping() {
		cake.setTopping(Topping.Fruit);
	};

	public void addMessage(String m) {
		cake.setMessage(m);
	};

	public void createCake() {
		addCakeLayer();
		addTopLayer();
		addTopping();
	};
	
	public Cake getCake() {
		return cake;
	}
}

class SpongeCakeBuilder implements CakeBuilder {
	protected Cake cake = new Cake();

	public void setCakeShape(Shape shape) {
		cake.setShape(shape);
	}

	public void addCakeLayer() {
		cake.setCakeLayer("Sponge");
	};

	public void addCreamLayer() {
		cake.setMidLayerCream(Cream.Red_Berries);
	};

	public void addTopLayer() {
		cake.setTopLayerCream(Cream.Whipped_Cream);
	};

	public void addTopping() {
		cake.setTopping(Topping.Fruit);
	};

	public void addMessage(String m) {
		cake.setMessage(m);
	};

	public void createCake() {
		addCakeLayer();
		addCreamLayer();
		addTopLayer();
		addTopping();
	};
	
	public Cake getCake() {
		return cake;
	}
}

class YogurtCakeBuilder implements CakeBuilder {
	protected Cake cake = new Cake();

	public void setCakeShape(Shape shape) {
		cake.setShape(shape);
	}

	public void addCakeLayer() {
		cake.setCakeLayer("Yogurt");
	};

	public void addCreamLayer() {
		cake.setMidLayerCream(Cream.Vanilla);
	};

	public void addTopLayer() {
		cake.setTopLayerCream(Cream.Red_Berries);
	};

	public void addTopping() {
		cake.setTopping(Topping.Chocolate);
	};

	public void addMessage(String m) {
		cake.setMessage(m);
	};

	public void createCake() {
		addCakeLayer();
		addCreamLayer();
		addTopLayer();
		addTopping();
	};
	
	public Cake getCake() {
		return cake;
	}

}

class StrawberryCakeBuilder implements CakeBuilder {
	protected Cake cake = new Cake();

	public void setCakeShape(Shape shape) {
		cake.setShape(shape);
	}

	public void addCakeLayer() {
		cake.setCakeLayer("Fresh Strawberry");
	};

	public void addCreamLayer() {};

	public void addTopLayer() {
		cake.setTopLayerCream(Cream.Strawberry_Cream);
	};

	public void addTopping() {
		cake.setTopping(Topping.Strawberry);
	};

	public void addMessage(String m) {
		cake.setMessage(m);
	};

	public void createCake() {
		addCakeLayer();
		addTopLayer();
		addTopping();
	};
	
	public Cake getCake() {
		return cake;
	}

}

