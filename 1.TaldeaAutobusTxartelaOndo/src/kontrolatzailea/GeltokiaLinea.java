package kontrolatzailea;

public class GeltokiaLinea {
	private String kodLinea;
	private int kodGeltokia;
	
	public GeltokiaLinea(String kodLinea, int kodGeltokia) {
		
		this.kodLinea = kodLinea;
		this.kodGeltokia = kodGeltokia;
	}
	
	public String getKodLinea() {
		return kodLinea;
	}
	
	public void setKodLinea(String kodLinea) {
		this.kodLinea = kodLinea;
	}
	
	public int getKodGeltokia() {
		return kodGeltokia;
	}
	
	public void setKodGeltokia(int kodGeltokia) {
		this.kodGeltokia = kodGeltokia;
	}
	

}
