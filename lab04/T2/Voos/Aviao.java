package Voos;

public class Aviao {
	private int nT;									// número de passageiros na classe turística
	private int filasT; 							// número de filas da classe turística
	private int lugaresT; 							// número de lugares em cada fila da classe turística
	private int nE;									// número de passageiros na classe executiva
	private int filasE; 							// número de filas da classe executiva
	private int lugaresE; 							// número de lugares em cada fila da classe executiva
	private int[][] execGrid;						// esquema dos lugares executivos reservados
	private int[][] touristGrid;					// esquema dos lugares turistícos reservados

	public Aviao(int filasT, int lugaresT, int filasE, int lugaresE) {
		this.filasE =filasE;
		this.filasT =filasT;
		this.lugaresE = lugaresE;
		this.lugaresT = lugaresT;
		this.nT = filasT*lugaresT;
		this.nE = filasE*lugaresE;
		this.execGrid = new int[filasE][lugaresE];
		this.touristGrid = new int[filasT][lugaresT];

		// empty executive class
		for(int i = 0; i < filasE; i++) {
			for(int j = 0; j < lugaresE; j++) {
				execGrid[i][j] = 0;}
		}

		// empty tourism class
		for(int i = 0; i < filasT; i++) {
			for(int j = 0; j < lugaresT; j++) {
				touristGrid[i][j] = 0;}
		}
	}

	public Aviao(int filasT, int lugaresT) {
		this.nT = filasT*lugaresT;
		this.filasT =filasT;
		this.lugaresT = lugaresT;
		this.touristGrid = new int[filasT][lugaresT];

		// empty tourism class
		for(int i = 0; i < filasT; i++) {
			for(int j = 0; j < lugaresT; j++) {
				touristGrid[i][j] = 0;}
		}
	}

	public int getnT() {
		return nT;
	}

	public int getnE() {
		return nE;
	}

	public void setnE(int nE) {this.nE = nE;}
	public void setnT(int nT) {
		this.nT = nT;
	}
	public int getFilasE() {
		return filasE;
	}
	public int getFilasT() {
		return filasT;
	}
	public int getLugaresE() {
		return lugaresE;
	}
	public int getLugaresT() {
		return lugaresT;
	}
	public int getFilas() { return (this.filasE + this.filasT); }
	public int[][] getExecGrid() { return execGrid; }
	public int[][] getTouristGrid() { return touristGrid; }
	public void setExecGrid(int[][] execGrid) { this.execGrid = execGrid; }
	public void setTouristGrid(int[][] touristGrid) { this.touristGrid = touristGrid; }
}
