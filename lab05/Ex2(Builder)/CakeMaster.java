package Ex2;

public class CakeMaster {
	private CakeBuilder builder;

	public Cake getCake() {
		return this.builder.getCake();
	}
	
	public void setCakeBuilder(CakeBuilder cakeBuilder) {
		this.builder = cakeBuilder;
	}

	public void createCake(Shape shape, int num, String m) {
		this.builder.createCake();
		this.builder.setCakeShape(shape);
		this.builder.getCake().setNumCakeLayers(num);
		this.builder.addMessage(m);
	}

	public void createCake(int num, String m) {
		this.builder.createCake();
		this.builder.getCake().setNumCakeLayers(num);
		this.builder.addMessage(m);
	}
	public void createCake(String m) {
		this.builder.createCake();
		this.builder.addMessage(m);
	}
}
