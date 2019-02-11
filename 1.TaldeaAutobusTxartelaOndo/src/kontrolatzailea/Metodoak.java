package kontrolatzailea;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import eredua.*;
import ikuspegia.*;

public class Metodoak {
	// Leihoak sortu
	/**
	 * Leiho1 sortu.
	 * @author talde1
	 */
	public static void lehenengoLeihoa() {
		Leiho1 Leiho1 = new Leiho1();
		Leiho1.setVisible(true);
	}

	/**
	 * Leiho2 sortu.
	 * @author talde1
	 */
	public static void bigarrenLeihoa() {
		ArrayList<Lineak> lineak = new ArrayList<Lineak>();
		lineak = Kontsultak.lineakDatuak();
		Leiho2 Leiho2 = new Leiho2(lineak);
		Leiho2.setVisible(true);
	}

	/**
	 * Leiho3 sortu.
	 * @author talde1
	 */
	public static void hirugarrenLeihoa(String hartutakoLinea, Autobusak autobusa) {
		Leiho3 Leiho3 = new Leiho3(hartutakoLinea, autobusa);
		Leiho3.setVisible(true);

	}

	/**
	 * Leiho4 sortu.
	 * @author talde1
	 * @param luzera2
	 * @param altuera2
	 * @param luzera1
	 * @param altuera1
	 * @param arrayGeltokia
	 * @param dataEtorri
	 * @param dataJoan
	 */
	public static void laugarrenLeihoa(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk,
			int hasierakoGeltokiaKod, int amaierakoGeltokiaKod, double altuera1, double luzera1, double altuera2,
			double luzera2, ArrayList<Geltokiak> arrayGeltokia, String dataJoan, String dataEtorri) {
		Leiho4 Leiho4 = new Leiho4(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod, amaierakoGeltokiaKod,
				altuera1, luzera1, altuera2, luzera2, arrayGeltokia, dataJoan, dataEtorri);
		Leiho4.setVisible(true);
	}

	/**
	 * Leiho5 sortu.
	 * @author talde1
	 * @param luzera2
	 * @param altuera2
	 * @param luzera1
	 * @param altuera1
	 * @param arrayGeltokia
	 * @param dataEtorri
	 * @param dataJoan
	 */
	public static void bostgarrenLeihoa(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk,
			int hasierakoGeltokiaKod, int amaierakoGeltokiaKod, float guztiraPrez, String nan, double altuera1,
			double luzera1, double altuera2, double luzera2, ArrayList<Geltokiak> arrayGeltokia, String dataJoan,
			String dataEtorri) {
		Leiho5 Leiho5 = new Leiho5(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod, amaierakoGeltokiaKod,
				guztiraPrez, nan, altuera1, luzera1, altuera2, luzera2, arrayGeltokia, dataJoan, dataEtorri);
		Leiho5.setVisible(true);
	}

	/**
	 * Leiho6 sortu.
	 * @author talde1
	 * @param txartela
	 * @param geltIzenak
	 * @param dataEtorri
	 * @param dataJoan
	 * @param arrayGeltokia
	 */
	public static void seigarrenLeihoa(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk,
			int hasierakoGeltokiaKod, int amaierakoGeltokiaKod, Txartelak txartela, ArrayList<String> geltIzenak,
			String dataJoan, String dataEtorri) {
		Leiho6 Leiho6 = new Leiho6(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod, amaierakoGeltokiaKod,
				txartela, geltIzenak, dataJoan, dataEtorri);
		Leiho6.setVisible(true);
	}

	// Leiho2-ko metodoak
	/**
	 * Hartzen duen lineatik gehien kontsumitzen duen autobusa bueltatzen du.
	 * @author talde1
	 * @param linea
	 * @return autobusKotsMaxArray
	 */
	public static Autobusak autobusKotsMaxMetodoa(String linea) {
		return Kontsultak.autobusKotsMax(linea);
	}

	// Leiho3-ko metodoak
	/**
	 * Sartutako linearen geltoki guztiak bueltatzen du.
	 * @author talde1
	 * @param hartutakoLinea
	 * @return arrayGeltokia
	 */
	public static ArrayList<Geltokiak> geltokiakAteraMetodoa(String hartutakoLinea) {
		ArrayList<Geltokiak> arrayGeltokia = Kontsultak.geltokiakAtera(hartutakoLinea);
		return arrayGeltokia;
	}

	/**
	 * Bi geltokien harteko distantzia kalkulatu.
	 * @author talde1
	 * @param altuera1
	 * @param luzera1
	 * @param altuera2
	 * @param luzera2
	 * @return distantzia
	 */
	public static double kalkulatuDistantzia(double altuera1, double luzera1, double altuera2, double luzera2) {
		double lurrarenErradioa = 6371;
		double dLat = Math.toRadians(altuera2 - altuera1);
		double dLng = Math.toRadians(luzera2 - luzera1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2)
				+ Math.pow(sindLng, 2) * Math.cos(Math.toRadians(altuera1)) * Math.cos(Math.toRadians(altuera2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		double distantzia = lurrarenErradioa * va2;
		return distantzia;
	}

	/**
	 * Frogatzeko ea autobusa beteta dagoen ala ez.
	 * @author talde1
	 * @param ibilbideData
	 * @param autobusa
	 * @return txartelaZPlazaBal
	 */
	public static boolean txartelaZPlazaFroga(String ibilbideData, Autobusak autobusa) {
		boolean txartelaZPlazaBal = false;
		int txartelaZPlazaKont = Kontsultak.txartelaZPlaza(ibilbideData);
		if (txartelaZPlazaKont <= autobusa.getzPlaza()) {
			txartelaZPlazaBal = true;
		}
		return txartelaZPlazaBal;
	}

	// Leiho4-ko metodoak
	/**
	 * Bi geltokien harteko distantzia kalkulatu.
	 * @author talde1
	 * @param kontsumoa
	 * @param eserKop
	 * @param altuera1
	 * @param luzera1
	 * @param altuera2
	 * @param luzera2
	 * @param ibilbideZbk
	 * @return totPertsonaBilletea
	 */
	public static float kalkPrezioa(float kontsumoa, int eserKop, double altuera1, double luzera1, double altuera2,
			double luzera2, int ibilbideZbk) {
		float zenbatIrabazi, totBidaia, totPertsonaBilletea;
		final double onurak = 0.20, erregaia = 0.80;
		double distantzia = kalkulatuDistantzia(altuera1, luzera1, altuera2, luzera2);

		zenbatIrabazi = (float) (erregaia * kontsumoa * distantzia);
		totBidaia = (float) (zenbatIrabazi * onurak + zenbatIrabazi);
		totPertsonaBilletea = (totBidaia / eserKop) * ibilbideZbk; // ibilbideZBK=1(joan) edo 2 (joan/etorria)
		totPertsonaBilletea = (float) (Math.round(totPertsonaBilletea * 100.0) / 100.0);
		return totPertsonaBilletea;
	}

	/**
	 * Frogatu dni-a erregistratuta ez dagoela.
	 * @author talde1
	 * @param nan
	 * @return balNan
	 */
	public static boolean nanGordetaEgon(String nan) {
		ArrayList<Bezeroak> bezeroak = new ArrayList<>();
		boolean balNan = false;
		bezeroak = Kontsultak.bezeroDatuak();
		for (Bezeroak bezeroak2 : bezeroak) {
			if (bezeroak2.getNAN().equals(nan)) {
				balNan = true;
			}
		}
		return balNan;
	}

	/**
	 * Sartutako pasahitza (zifratuta) ea datu basean dagoen ala ez.
	 * @author talde1
	 * @param pasahitza
	 * @return bal
	 */
	public static boolean frogatuPasahitza(String pasahitza) {
		boolean bal = false;
		String pasaEnkr = "";
		pasaEnkr = zifratuPasahitza(pasahitza);
		ArrayList<Bezeroak> bezeroak = new ArrayList<>();
		bezeroak = Kontsultak.bezeroDatuak();
		for (Bezeroak bezeroak2 : bezeroak) {
			if (pasaEnkr.equals(bezeroak2.getPasahitza()))
				bal = true;
		}
		return bal;
	}

	/**
	 * Sartutako nan-a ea datu baaean dagoen ala ez.
	 * @author talde1
	 * @param nan
	 * @return bal
	 */
	public static boolean frogatuNAN(String nan) {
		boolean bal = false;
		ArrayList<Bezeroak> bezeroak = new ArrayList<>();
		bezeroak = Kontsultak.bezeroDatuak();
		for (Bezeroak bezeroak2 : bezeroak) {
			if (nan.equals(bezeroak2.getNAN()))
				bal = true;
		}
		return bal;
	}

	/**
	 * Frogatu dni-a erregistratuta ez dagoela. Ez balegoke eta datuak hutzik ere ez, 
	 * bezeroen erregistroa egin datu basean.
	 * @author talde1
	 * @param pasahitza
	 * @param nan
	 * @param izena
	 * @param abizenak
	 * @param sexua
	 * @param jaioDataString
	 * @return bal
	 */
	public static boolean erregistratuBezeroak(String pasahitza, String nan, String izena, String abizenak,
			String sexua, String jaioDataString) {
		boolean bal = true;
		String pasaEnkr = "";
		pasaEnkr = zifratuPasahitza(pasahitza);
		ArrayList<Bezeroak> bezeroak = new ArrayList<>();

		// fitxeroari bidali
		if (pasahitza.length() == 0 || nan.length() < 8 || izena.isEmpty() || abizenak.isEmpty() || sexua.isEmpty()
				|| nan.length() < 8 || jaioDataString == null)
			bal = false;

		if (!nanGordetaEgon(nan))
			bal = false;
		if (bal && nanGordetaEgon(nan))
			bezeroak = Kontsultak.erregistratuBezeroak(pasaEnkr, nan, izena, abizenak, sexua, jaioDataString);
		return bal;
	}

	/**
	 * Sartutako pasahitza zifratu.
	 * @author talde1
	 * @param pasahitza
	 * @return hashtext
	 */
	public static String zifratuPasahitza(String pasahitza) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pasahitza.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Balidatu nan-a (lehenengo 8 zbk eta 9.a letra)
	 * @author talde1
	 * @param nan
	 * @return nanBalidazioa
	 */
	public static boolean nanBalidazioa(String nan) {
		String nanLetra = nan.substring(8), nanLarria = nanLetra.toUpperCase();
		boolean nanBalidazioa = false;
		if (nan.length() != 9 || Character.isLetter(nan.charAt(8)) == false)
			nanBalidazioa = false;

		if (nanZenbakiak(nan) == true && nanLetra(nan).equals(nanLarria))
			nanBalidazioa = true;
		return nanBalidazioa;
	}

	/**
	 * Lehenengo 8 karaktereak zenbakiak direla balidatzen du. 8 zenbaki ez badaude
	 * NAN-a ez dago ondo.
	 * @author talde1
	 * @param nan
	 * @return balNan
	 */
	public static boolean nanZenbakiak(String nan) {
		String zbk, nanOna = "";
		boolean balNan = false;
		String[] zbkArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < nan.length() - 1; i++) { // sartutako nan-a
			zbk = nan.substring(i, i + 1);
			for (int j = 0; j < nan.length(); j++) { // zenbakien arraya
				if (zbk.equals(zbkArray[j]))
					nanOna += zbkArray[j];
			}
		}
		if (nanOna.length() != 9)
			balNan = true;
		else
			balNan = false;
		return balNan;
	}

	/**
	 * nan-aren zenbaki guztiak gehitzen ditu eta zati 23 egiten hondarra lortzen
	 * du. Hondarra horrekin sartutako nan-aren letra bueltatzen du.
	 * @author talde1
	 * @param nan
	 * @return nanLarria
	 */
	public static String nanLetra(String nan) {
		int nanGehiketa = Integer.parseInt(nan.substring(0, 8)), hondarra;
		String nanLarria = null;
		String[] zbkArray = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V",
				"H", "L", "C", "K", "E" };
		hondarra = nanGehiketa % 23;
		nanLarria = zbkArray[hondarra];
		return nanLarria;
	}

	// Leiho5-ko metodoak
	/**
	 * Zenbat diru sartzen duen jakiteko egiteko.
	 * @author talde1
	 * @param kont
	 * @param sartutakoa
	 * @return sartutakoa
	 */
	public static double diruaSartu(int kont, double sartutakoa) {
		switch (kont) {
		case 1:
			sartutakoa += 200;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 2:
			sartutakoa += 100;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 3:
			sartutakoa += 50;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 4:
			sartutakoa += 20;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 5:
			sartutakoa += 10;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 6:
			sartutakoa += 5;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 7:
			sartutakoa += 2;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 8:
			sartutakoa += 1;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 9:
			sartutakoa += 0.5;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 10:
			sartutakoa += 0.2;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 11:
			sartutakoa += 0.1;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 12:
			sartutakoa += 0.05;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 13:
			sartutakoa += 0.02;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;
		case 14:
			sartutakoa += 0.01;
			sartutakoa = Math.round(sartutakoa * 100.0) / 100.0;
			break;

		default:
			sartutakoa = 0;
			break;
		}
		return sartutakoa;
	}

	/**
	 * Jakiteko zenbat diru sartu duen gero bueltak emateko ala ez.
	 * @author talde1
	 * @param diruFalta
	 * @param guztiraPrez
	 * @param sartutakoa
	 * @return diruFalta
	 */
	public static double diruFaltaBueltakMetodoa(double diruFalta, double guztiraPrez, double sartutakoa) {
		diruFalta = guztiraPrez - sartutakoa;
		diruFalta = Math.round(diruFalta * 100.0) / 100.0;
		return diruFalta;
	}

	/**
	 * Arrayan sartzeko zenbat kantitate txanpon bakoitza eta bueltatu txanpona /
	 * billete.
	 * @author talde1
	 * @param diruFalta
	 * @return bueltakString
	 */
	public static String diruBueltakZerrenda(double diruFalta) {
		String bueltakString = "";
		double bueltak = 0;
		if (diruFalta < 0) {
			bueltak = -diruFalta;
			for (double i = bueltak; i > 0; i = bueltak) {
				if (bueltak >= 200) {
					bueltak = bueltak - 200;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "200€-ko bilete \n";

				} else if (bueltak >= 100) {
					bueltak = bueltak - 100;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "100€-ko bilete \n";

				} else if (bueltak >= 50) {
					bueltak = bueltak - 50;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "50€-ko bilete \n";

				} else if (bueltak >= 20) {
					bueltak = bueltak - 20;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "20€-ko bilete \n";

				} else if (bueltak >= 10) {
					bueltak = bueltak - 10;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "10€-ko bilete \n";

				} else if (bueltak >= 5) {
					bueltak = bueltak - 5;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "5€-ko bilete \n";

				} else if (bueltak >= 2) {
					bueltak = bueltak - 2;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "2€-ko moneta \n";

				} else if (bueltak >= 1) {
					bueltak = bueltak - 1;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "1€-ko moneta \n";

				} else if (bueltak >= 0.5) {
					bueltak = bueltak - 0.5;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.5€-ko moneta \n";

				} else if (bueltak >= 0.2) {
					bueltak = bueltak - 0.2;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.2€-ko moneta \n";

				} else if (bueltak >= 0.1) {
					bueltak = bueltak - 0.1;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.1€-ko moneta \n";

				} else if (bueltak >= 0.05) {
					bueltak = bueltak - 0.05;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.05€-ko moneta \n";

				} else if (bueltak >= 0.02) {
					bueltak = bueltak - 0.02;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.02€-ko moneta \n";

				} else if (bueltak >= 0.01) {
					bueltak = bueltak - 0.01;
					bueltak = Math.round(bueltak * 100.0) / 100.0;
					bueltakString = bueltakString + "0.01€-ko moneta \n";
				}
			}
		}
		return bueltakString;
	}

	/**
	 * Hasierako eta amaierako geltokien izenak lortu.
	 * @author talde1
	 * @param txartela
	 * @return geltIzenak
	 */
	public static ArrayList<String> geltokienIzenakLortu(Txartelak txartela) {
		ArrayList<String> geltIzenak = new ArrayList<>();
		String geltHasiIzena = Kontsultak.geltokiarenIzena(txartela.getkodGeltokiHasiera());
		geltIzenak.add(geltHasiIzena);
		String geltAmaIzena = Kontsultak.geltokiarenIzena(txartela.getkodGeltokiAmaiera());
		geltIzenak.add(geltAmaIzena);
		return geltIzenak;
	}

	/**
	 * Billetea sortu.
	 * @author talde1
	 * @param txartela
	 * @param ibilbideData
	 * @param ibilbideZbk
	 * @param guztiraPrez
	 * @param hasierakoGeltokiaKod
	 * @param amaierakoGeltokiaKod
	 */
	public static void sortuBilletea(Txartelak txartela, String ibilbideData, int ibilbideZbk, float guztiraPrez,
			int hasierakoGeltokiaKod, int amaierakoGeltokiaKod) {
		Kontsultak.billeteaKontsulta(txartela, ibilbideData, ibilbideZbk, guztiraPrez, hasierakoGeltokiaKod,
				amaierakoGeltokiaKod);
	}

	/**
	 * Tiket-a fitxategian idatzi.
	 * @author talde1
	 * @param txartela
	 * @param geltIzenak
	 * @param dataJoan
	 * @param dataEtorri
	 */
	public static void fitxIdatzi(Txartelak txartela, ArrayList<String> geltIzenak, String dataJoan,
			String dataEtorri) {
		FileWriter fitx = null;
		PrintWriter pw = null;

		try {
			fitx = new FileWriter("src\\eredua\\Billetea", true);
			pw = new PrintWriter(fitx);

			pw.println("Ibilbidearen datuak:  \n" + "\tHartutako linea:  " + txartela.getkodLinea() + "\t\t\t\t"
					+ "\tZure autobusaren kodigoa:  " + txartela.getkodBus() + "\n\t" + "Hasierako geltokia:  "
					+ geltIzenak.get(0) + "\t\t\t" + "Amaierako geltokia:  " + geltIzenak.get(1) + "\n");
			pw.println("Bezeroaren datuak:  \n" + "\tNAN:  " + txartela.getNan() + "\n");
			if (txartela.getzIbilbidea() == 1)
				pw.println("Erosketaren datuak:  \n" + "\tData eta ordua:  " + txartela.getOrdua());
			else if (txartela.getzIbilbidea() == 2)
				pw.println("Erosketaren datuak:  \n" + "\t*Lehenengo bidaiaren data eta ordua:  " + dataJoan
						+ "\n\t*Bigarren bidaiaren data eta ordua:  " + dataEtorri);
			pw.println("\n\tBidaiaren prezioa:  " + txartela.getPrezioa() + "\n");
			pw.println(
					"******************************************************************************************************************************************");
			pw.println("");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fitx)
					fitx.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// Leiho6-ko metodoak
	/**
	 * Amaieran tiketa imprimatzen dagoen bitartean itxaron behar den denbora.
	 * @author talde1
	 */
	public static void Leiho_segunduak() {
		for (int i = 1; i <= 5; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		lehenengoLeihoa();
	}

}
