public class Phone {
	private String processador;
	private double preco;
	private double memoria;
	private String camera;
	
	public Phone(String processador, double preco, double memoria, String camera) {
		this.processador = processador;
		this.preco = preco;
		this.memoria = memoria;
		this.camera = camera;
	}

	public String getProcessador() {
		return processador;
	}

	public double getPreco() {
		return preco;
	}

	public double getMemoria() {
		return memoria;
	}

	public String getCamera() {
		return camera;
	}

	@Override
	public String toString() {
		return "Phone [camera=" + camera + ", memoria=" + memoria + ", preco=" + preco + ", processador=" + processador
				+ "]";
	}

}
