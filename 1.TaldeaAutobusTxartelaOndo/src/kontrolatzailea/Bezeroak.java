package kontrolatzailea;

import java.util.Date;

public class Bezeroak {
	
	private String NAN, izena, abizenak, sexua, pasahitza;
	private Date jaioData;
	
	public Bezeroak(String Nan, String izena, String abizenak, Date jaioData, String sexua, String pasahitza) {
		
		NAN = Nan;
		this.izena = izena;
		this.abizenak = abizenak;
		this.jaioData = jaioData;
		this.sexua = sexua;
		this.pasahitza = pasahitza;
	}
	
	public String getNAN() {
		return NAN;
	}
	
	public void setNAN(String Nan) {
		NAN = Nan;
	}
	
	public String getIzena() {
		return izena;
	}
	
	public void setIzena(String izena) {
		this.izena = izena;
	}
	
	public String getAbizenak() {
		return abizenak;
	}
	
	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}
	
	public Date getjaioData() {
		return jaioData;
	}
	
	public void setjaioData(Date data) {
		this.jaioData = data;
	}
	
	public String getSexua() {
		return sexua;
	}
	
	public void setSexua(String sexua) {
		this.sexua = sexua;
	}
	
	public String getPasahitza() {
		return pasahitza;
	}
	
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	

}
