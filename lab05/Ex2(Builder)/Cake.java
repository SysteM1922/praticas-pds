package Ex2;

class Cake {
	private Shape shape;
	private String cakeLayer;
	private int numCakeLayers;
	private Cream midLayerCream;
	private Cream topLayerCream;
	private Topping topping;
	private String message;

	public Cake() {
		this.shape = Shape.Circle;
		this.numCakeLayers = 1;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public String getCakeLayer() {
		return cakeLayer;
	}

	public void setCakeLayer(String cakeLayer) {
		this.cakeLayer = cakeLayer;
	}

	public int getNumCakeLayers() {
		return numCakeLayers;
	}

	public void setNumCakeLayers(int numCakeLayers) {
		this.numCakeLayers = numCakeLayers;
	}

	public Cream getMidLayerCream() {
		return midLayerCream;
	}

	public void setMidLayerCream(Cream midLayerCream) {
		this.midLayerCream = midLayerCream;
	}

	public Cream getTopLayerCream() {
		return topLayerCream;
	}

	public void setTopLayerCream(Cream topLayerCream) {
		this.topLayerCream = topLayerCream;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cakeLayer == null) ? 0 : cakeLayer.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((midLayerCream == null) ? 0 : midLayerCream.hashCode());
		result = prime * result + numCakeLayers;
		result = prime * result + ((shape == null) ? 0 : shape.hashCode());
		result = prime * result + ((topLayerCream == null) ? 0 : topLayerCream.hashCode());
		result = prime * result + ((topping == null) ? 0 : topping.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cake other = (Cake) obj;
		if (cakeLayer == null) {
			if (other.cakeLayer != null)
				return false;
		} else if (!cakeLayer.equals(other.cakeLayer))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (midLayerCream != other.midLayerCream)
			return false;
		if (numCakeLayers != other.numCakeLayers)
			return false;
		if (shape != other.shape)
			return false;
		if (topLayerCream != other.topLayerCream)
			return false;
		if (topping != other.topping)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return cakeLayer+" cake with "+numCakeLayers+" layers" +(midLayerCream != null ? " and "+midLayerCream+" cream" : "")+", topped with "+topLayerCream+" and "+topping+". Message says: \""+message+"\".";
	}	
}
