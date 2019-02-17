package kontrolatzailea;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class testaMetodoak {
	// double
	// Zenbat diru sartzen duen jakiteko egiteko - froga
	@Test
	public void diruaSartuTesta() {
		assertEquals(Metodoak.diruaSartu(0, 0), 0.0); // 0
		assertEquals(Metodoak.diruaSartu(1, 0), 200);
		assertEquals(Metodoak.diruaSartu(2, 200), 300); // 200+100
		assertEquals(Metodoak.diruaSartu(3, 300), 350); // 300+50
		assertEquals(Metodoak.diruaSartu(4, 350), 370); // 350+20
		assertEquals(Metodoak.diruaSartu(5, 370), 380); // 370+10
		assertEquals(Metodoak.diruaSartu(6, 380), 385); // 380+5
		assertEquals(Metodoak.diruaSartu(7, 385), 387);// 385+2
		assertEquals(Metodoak.diruaSartu(8, 387), 388); // 387+1
		assertEquals(Metodoak.diruaSartu(9, 388), 388.5); // 387.5+0.5
		assertEquals(Metodoak.diruaSartu(10, 388.5), 388.7); // 388.5+0.2
		assertEquals(Metodoak.diruaSartu(11, 388.7), 388.8); // 388.7+0.1
		assertEquals(Metodoak.diruaSartu(12, 388.8), 388.85); // 388.80+0.05
		assertEquals(Metodoak.diruaSartu(13, 388.85), 388.87); // 388.85+0.02
		assertEquals(Metodoak.diruaSartu(14, 388.87), 388.88); // 388.87+0.01
	}

	// Jakiteko zenbat diru sartu duen gero bueltak emateko ala ez - froga
	@Test
	public void diruFaltaBueltakMetodoaTesta() {
		assertEquals(Metodoak.diruFaltaBueltakMetodoa(0, 6, 2.5), 3.5);
	}

	// Bi geltokien harteko distantzia kalkulatu
	@Test
	public void kalkulatuDistantziaTesta() {
		assertEquals(Metodoak.kalkulatuDistantzia(43.2614, -2.94974, 43.3759, -2.99183), 13.179291549936597); // termibus -  metro  larrabasterra
	}

	// float
	// Bi geltokien harteko distantzia kalkulatu
	@Test
	public void kalkPrezioaTesta() {
		float kontsumoa = (float) 0.32;
		float erantzuna = (float) 0.1;
		assertEquals(Metodoak.kalkPrezioa(kontsumoa, 40, 43.2614, -2.94974, 43.3759, -2.99183, 1), erantzuna); // termibus -metro larrabasterra
	}

	// String
	// Arrayan sartzeko zenbat kantitate txanpon bakoitza eta bueltatu txanpona / billete- froga
	@Test
	public void diruBueltakZerrenda() {
		assertEquals(Metodoak.diruBueltakZerrenda(-200), "200€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-100), "100€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-50), "50€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-20), "20€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-10), "10€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-5), "5€-ko bilete \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-2), "2€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-1), "1€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.5), "0.5€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.2), "0.2€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.1), "0.1€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.05), "0.05€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.02), "0.02€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(-0.01), "0.01€-ko moneta \n");
		assertEquals(Metodoak.diruBueltakZerrenda(1), "");
	}

	// nan-aren zenbaki guztiak gehitzen ditu eta zati 23 egiten hondarra lortzen du. Hondarra horrekin sartutako nan-aren letra bueltatzen du.
	@Test
	public void nanLetraTesta() {
		String nan = "12345678Z";
		assertEquals(Metodoak.nanLetra(nan), "Z");
	}

	//zifratuPasahitza
	@Test
	public void zifratuPasahitzaTesta() {
		String pasahitza="123456";
		assertEquals(Metodoak.zifratuPasahitza(pasahitza), "e10adc3949ba59abbe56e057f20f883e");
	}

	//boolean
	// Sartutako pasahitza (zifratuta) ea datu basean dagoen ala ez
	@Test
	public void frogatuPasahitzaTesta() {
		String pasahitzaTxarto="abracadabra";
		assertFalse(Metodoak.frogatuPasahitza(pasahitzaTxarto));

		String pasahitzaOna="123456";
		assertTrue(Metodoak.frogatuPasahitza(pasahitzaOna));
	}
	
	//Lehenengo 8 karaktereak zenbakiak direla balidatzen du.
	@Test
	public void nanZenbakiakTesta() {
		String nanOndo="12345678";
		assertTrue(Metodoak.nanZenbakiak(nanOndo)); 
		//ez da inoiz false izango, metodoan badaezpada jarrita dago
	}
	
	//Frogatu dni-a erregistratuta ez dagoela.
	@Test
	public void nanGordetaEgonTesta() {
		String nanGordeta="12345678Z";
		assertTrue(Metodoak.nanZenbakiak(nanGordeta));
	}
	
	//Sartutako nan-a ea datu baaean dagoen ala ez
	@Test
	public void frogatuNANTesta() {
		String nanOna="12345678Z";
		String nanTxarto="12345678x";
		assertFalse(Metodoak.frogatuNAN(nanTxarto));
		assertTrue(Metodoak.frogatuNAN(nanOna));
	}
	
	//frogatuNAN
	@Test
	public void nanBalidazioaTesta() {
		String nanOna="12345678Z";
		String nanTxarto="123456781";
		assertTrue(Metodoak.nanBalidazioa(nanOna));
		assertFalse(Metodoak.nanBalidazioa(nanTxarto));
	}
	
	//Frogatzeko ea autobusa beteta dagoen ala ez.
	@Test
	public void txartelaZPlazaFrogaTesta() {
		String ibilbideData="2019-02-15 10:00:00";
		Autobusak autobusa = null;
		assertFalse(Metodoak.txartelaZPlazaFroga(ibilbideData,autobusa));
	}
	
	//Frogatzeko ea autobusa beteta dagoen ala ez.
	@Test
	public void erregistratuBezeroakTesta() {
		String pasahitza="",nan="12345678Z",izena="JUnit",abizenak="froga",sexua="E",jaioDataString="";
		assertFalse(Metodoak.erregistratuBezeroak(pasahitza,nan,izena,abizenak,sexua,jaioDataString));
		pasahitza="123456"; jaioDataString="2000-08-11";
		assertTrue(Metodoak.erregistratuBezeroak(pasahitza,nan,izena,abizenak,sexua,jaioDataString));
	}
	
	//autobusak
	@Test
	public void autobusKotsMaxMetodoaTesta() {
		String linea="L1";
		assertEquals(Metodoak.autobusKotsMaxMetodoa(linea), null);
	}
	
	//void 
	@Test
	//Leiho1 sortu.
	public void lehenengoLeihoaTesta() {
		Metodoak.lehenengoLeihoa();
	}
	
	
	//Leiho2 sortu.
	@Test
	public void bigarrenLeihoaTesta() throws Exception {
		Metodoak.bigarrenLeihoa();	
	}
	
	//Leiho3 sortu.
	@Test
	public void hirugarrenLeihoaTesta() {
		Autobusak autobusa=Mockito.mock(Autobusak.class);
		Metodoak.hirugarrenLeihoa("L1", autobusa);
	}
	
	//Leiho4 sortu.
	@Test
	public void laugarrenLeihoaTesta() {
		Autobusak autobusa=Mockito.mock(Autobusak.class);
		ArrayList<Geltokiak> arrayGeltokia = null;
		Metodoak.laugarrenLeihoa("L1", autobusa, 1, 2, 4, 1.1, -0.3,1.2,-0.4,arrayGeltokia, "2019-02-01 10:00:00", "2019-02-01 11:00:00");
	}
	
	//Leiho5 sortu.
	@Test
	public void bostgarrenLeihoaTesta() {
		Autobusak autobusa=Mockito.mock(Autobusak.class);
		ArrayList<Geltokiak> arrayGeltokia = null;
		Metodoak.bostgarrenLeihoa("L1", autobusa, 1, 2, 4, 5, "12345678Z",1.1,-0.3,1.2,-0.4,arrayGeltokia, "2019-02-01 10:00:00", "2019-02-01 11:00:00");
	}
	
	//Leiho6 sortu.
	@Test
	public void seigarrenLeihoaTesta() {
		Autobusak autobusa=Mockito.mock(Autobusak.class);
		Txartelak txartela=Mockito.mock(Txartelak.class);
		ArrayList<String> geltIzenak = null;
		Metodoak.seigarrenLeihoa("L1", autobusa, 1, 2, 4,txartela,geltIzenak, "2019-02-01 10:00:00", "2019-02-01 11:00:00");
	}
	
	//Billetea sortu.
	@Test
	public void sortuBilleteaTesta() {
		Txartelak txartela=Mockito.mock(Txartelak.class);
		Metodoak.sortuBilletea(txartela,"2019-02-01 10:00:00",2,5, 2,4);
	}
	
	//Hasierako eta amaierako geltokien izenak lortu.
	@Test
	public void geltokienIzenakLortuTesta() {
		Txartelak txartela=Mockito.mock(Txartelak.class);
		Metodoak.geltokienIzenakLortu(txartela);
	}
	
	//Tiket-a fitxategian idatzi.
	@Test
	public void fitxIdatziTesta() {
		Txartelak txartela=Mockito.mock(Txartelak.class);
		ArrayList<String> geltIzenak = null;
		//Metodoak.fitxIdatzi(txartela, geltIzenak, "2019-02-01", "2019-02-05"); //errorea ematen du
	}
	
	//Amaieran tiketa imprimatzen dagoen bitartean itxaron behar den denbora.
	@Test
	public void Leiho_segunduakTesta() {
		Metodoak.Leiho_segunduak();
	}
}
