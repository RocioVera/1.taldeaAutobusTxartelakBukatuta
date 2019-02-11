package kontrolatzailea;

import java.sql.Date;
import java.sql.Timestamp;

public class Txartelak {
	
	private int zIbilbidea, kodBus, kodGeltokiHasiera, kodGeltokiAmaiera;
	private String kodLinea, Nan, ibilbideData;
	private float prezioa;
	private Date data;
	private Timestamp ordua;
	
	public Txartelak(String kodLinea, int kodBus, int kodGeltokiHasiera, int kodGeltokiAmaiera,
			Date data, Timestamp ordua, String NAN, float prezioa, int zIbilbidea, String ibilbideData) {
		
		this.kodLinea = kodLinea;
		this.kodBus = kodBus;
		this.kodGeltokiHasiera = kodGeltokiHasiera;
		this.kodGeltokiAmaiera = kodGeltokiAmaiera;
		this.data = data;
		this.ordua = ordua;
		this.Nan = NAN;
		this.prezioa = prezioa;
		this.zIbilbidea = zIbilbidea;
		this.ibilbideData =  ibilbideData;
	}
	
	public String getIbilbideData() {
		return ibilbideData;
	}

	public void setIbilbideData(String ibilbideData) {
		this.ibilbideData = ibilbideData;
	}

	public int getzIbilbidea() {
		return zIbilbidea;
	}

	public void setzIbilbidea(int zIbilbidea) {
		this.zIbilbidea = zIbilbidea;
	}

	public String getkodLinea() {
		return kodLinea;
	}

	public void setkodLinea(String kodLinea) {
		this.kodLinea = kodLinea;
	}

	public int getkodBus() {
		return kodBus;
	}

	public void setkodBus(int kodBus) {
		this.kodBus = kodBus;
	}

	public int getkodGeltokiHasiera() {
		return kodGeltokiHasiera;
	}

	public void setkodGeltokiHasiera(int kodGeltokiHasiera) {
		this.kodGeltokiHasiera = kodGeltokiHasiera;
	}

	public int getkodGeltokiAmaiera() {
		return kodGeltokiAmaiera;
	}

	public void setkodGeltokiAmaiera(int kodGeltokiAmaiera) {
		this.kodGeltokiAmaiera = kodGeltokiAmaiera;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Timestamp getOrdua() {
		return ordua;
	}

	public void setOrdua(Timestamp ordua) {
		this.ordua = ordua;
	}

	public String getNan() {
		return Nan;
	}

	public void setNan(String NAN) {
		this.Nan = NAN;
	}

	public float getPrezioa() {
		return prezioa;
	}

	public void setPrezioa(float prezioa) {
		this.prezioa = prezioa;
	}

	

}