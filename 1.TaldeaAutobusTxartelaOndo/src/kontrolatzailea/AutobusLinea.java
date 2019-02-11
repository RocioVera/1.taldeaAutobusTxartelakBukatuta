package kontrolatzailea;

public class AutobusLinea {
	
	private String kodLinea;
	private int kodBus;
	
	public AutobusLinea(String kodLinea, int kodBus) {
		
		this.kodLinea = kodLinea;
		this.kodBus = kodBus;
	}

	public String getKodLinea() {
		return kodLinea;
	}

	public void setKodLinea(String kodLinea) {
		this.kodLinea = kodLinea;
	}

	public int getKodBus() {
		return kodBus;
	}

	public void setKodBus(int kodBus) {
		this.kodBus = kodBus;
	}

}
