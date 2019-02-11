package kontrolatzailea;

public class Autobusak {
	
	private int kodBus, zPlaza;
	private float kmKontsumoa;
	private String kolorea;
	
	public Autobusak(int kodBus, int zPlaza, float kmKontsumoa, String kolorea) {
		
		this.kodBus = kodBus;
		this.zPlaza = zPlaza;
		this.kmKontsumoa = kmKontsumoa;
		this.kolorea = kolorea;
	}
	
	public int getKodBus() {
		return kodBus;
	}
	
	public void setKodBus(int kodBus) {
		this.kodBus = kodBus;
	}
	
	public int getzPlaza() {
		return zPlaza;
	}
	
	public void setzPlaza(int zPlaza) {
		this.zPlaza = zPlaza;
	}
	
	public float getKmKontsumoa() {
		return kmKontsumoa;
	}
	
	public void setKmKontsumoa(float kmKontsumoa) {
		this.kmKontsumoa = kmKontsumoa;
	}
	
	public String getKolorea() {
		return kolorea;
	}
	
	public void setKolorea(String kolorea) {
		this.kolorea = kolorea;
	}
	

}
