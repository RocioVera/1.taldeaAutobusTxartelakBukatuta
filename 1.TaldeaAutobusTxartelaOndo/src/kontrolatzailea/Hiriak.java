package kontrolatzailea;

public class Hiriak {
	private String kodPostal, izena;
	
	public Hiriak(String kodPostal, String izena) {
	
		this.kodPostal = kodPostal;
		this.izena = izena;
	}
	
	public String getKodPostal() {
		return kodPostal;
	}
	
	public void setKodPostal(String kodPostal) {
		this.kodPostal = kodPostal;
	}
	
	public String getIzena() {
		return izena;
	}
	
	public void setIzena(String izena) {
		this.izena = izena;
	}
	
	

}
