package kontrolatzailea;

public class HiriaGeltokia {
	private int kodGeltokia;
	private String kodPostal;
	
	public HiriaGeltokia(int kodGeltokia, String kodPostal) {
		
		this.kodGeltokia = kodGeltokia;
		this.kodPostal = kodPostal;
	}
	
	public int getKodGeltokia() {
		return kodGeltokia;
	}
	public void setKodGeltokia(int kodGeltokia) {
		this.kodGeltokia = kodGeltokia;
	}
	public String getKodPostal() {
		return kodPostal;
	}
	public void setKodPostal(String kodPostal) {
		this.kodPostal = kodPostal;
	}
	

}
