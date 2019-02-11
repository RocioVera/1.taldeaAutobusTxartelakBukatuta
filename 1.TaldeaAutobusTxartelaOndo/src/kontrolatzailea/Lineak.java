package kontrolatzailea;

public class Lineak {
	
	private String kodLinea, izena;
	
	public Lineak(String kodLinea, String izena) {	
		this.kodLinea = kodLinea;
		this.izena = izena;
	}
	
	public String getKodLinea() {
		return kodLinea;
	}
	
	@Override
	public String toString() {
		return "Lineak [kodLinea=" + kodLinea + ", izena=" + izena + "]";
	}

	public void setKodLinea(String kodLinea) {
		this.kodLinea = kodLinea;
	}
	
	public String getIzena() {
		return izena;
	}
	
	public void setIzena(String izena) {
		this.izena = izena;
	}
	

}
