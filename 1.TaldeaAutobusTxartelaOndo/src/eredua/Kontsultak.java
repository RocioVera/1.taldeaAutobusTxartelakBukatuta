package eredua;

import java.util.ArrayList;
import java.sql.*;
import kontrolatzailea.*;

public class Kontsultak {

	//Leiho2-ko kontsultak
	/**
	 * Linea taulako datuak hartu.
	 * @author talde1
	 * @return arrayLineak
	 */
	public static ArrayList<Lineak> lineakDatuak() {
		ArrayList<Lineak> arrayLineak = new ArrayList<Lineak>();
		Statement st = null;
		Connection konexioa = Konexioa.getConexion();
		String kodLinea, izena;
		try {
			st = konexioa.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM linea");
			while (rs.next()) {
				kodLinea = (rs.getString("Cod_Linea"));
				izena = (rs.getString("Nombre"));
				Lineak lineak = new Lineak(kodLinea, izena);
				arrayLineak.add(lineak);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return arrayLineak;
	}

	/**
	 * Hartzen duen lineatik gehien kontsumitzen duen autobusa bueltatzen du.
	 * @author talde1
	 * @param linea
	 * @return autobusak
	 */
	public static Autobusak autobusKotsMax(String linea) {
		Statement st = null;
		Connection konexioa = Konexioa.getConexion();
		int kodBus, nPlaza;
		String kolorea;
		float kontsumoa;
		Autobusak autobusak = null;
		try {
			st = konexioa.createStatement();
			ResultSet rs = st.executeQuery("SELECT a.* FROM autobus a, linea_autobus l WHERE l.Cod_Linea like "+ "'" + linea + "'" +" AND a.Cod_bus = l.Cod_bus AND Consumo_km IN (SELECT max(Consumo_km) FROM autobus A, linea_autobus L WHERE l.Cod_Linea like "+ "'" + linea + "'" +" AND a.Cod_bus = l.Cod_bus)");
			while (rs.next()) {
				kodBus = (rs.getInt("Cod_bus"));
				nPlaza = (rs.getInt("N_plazas"));
				kontsumoa = (rs.getFloat(3));
				kolorea = (rs.getString(4));
				autobusak = new Autobusak(kodBus, nPlaza, kontsumoa, kolorea);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return autobusak;
	}

	//Leiho3-ko kontsultak
	/**
	 * Sartutako linearen geltokiak bueltatu.
	 * @author talde1
	 * @param linea
	 * @return arrayparada
	 */
	public static ArrayList<Geltokiak> geltokiakAtera(String linea) {
		ArrayList<Geltokiak> arrayparada = new ArrayList<Geltokiak>();
		PreparedStatement st = null;
		Connection konexioa = Konexioa.getConexion();
		try {
			st = konexioa.prepareStatement(
					"SELECT p.Cod_Parada, p.Nombre, p.Calle, p.Latitud, p.Longitud, lp.Cod_Linea FROM parada p, linea_parada lp,linea l WHERE p.Cod_Parada=lp.Cod_Parada and l.Cod_Linea=lp.Cod_Linea AND l.Cod_Linea like "
							+ "'" + linea + "'");
			ResultSet rs = st.executeQuery();
			int kodGeltokia;
			String izena,kalea;
			float altuera,luzera;
			while (rs.next()) {
				kodGeltokia = (rs.getInt(1));
				izena = (rs.getString(2));
				kalea = (rs.getString(3));
				altuera = (rs.getFloat(4));
				luzera = (rs.getFloat(5));
				Geltokiak parada = new Geltokiak(kodGeltokia, izena, kalea, altuera, luzera);
				arrayparada.add(parada);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return arrayparada;
	}
	
	
	/**
	 * Hartuko duen autobusaren libre ez dauden lekuak bueltatu.
	 * @author talde1
	 * @param ibilbideData
	 * @param autobusa 
	 * @return txartelaZPlazaKont
	 */
	public static int txartelaZPlaza(String ibilbideData, Autobusak autobusa) {
		PreparedStatement st = null;
		Connection konexioa = Konexioa.getConexion();
		int txartelaZPlazaKont = 0;
		try {
			st = konexioa.prepareStatement(
					"SELECT COUNT(ibilbideData) FROM billete WHERE ibilbideData LIKE '" + ibilbideData + "'"+ " AND Cod_Bus = "+ "'"+autobusa.getKodBus()+"'");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				txartelaZPlazaKont = (rs.getInt("COUNT(ibilbideData)"));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return txartelaZPlazaKont;
	}

	//Leiho4-ko kontsultak
	/**
	 * Bezero (cliente) taulako datuak hartu.
	 * @author talde1
	 * @return arrayBezeroak
	 */
	public static ArrayList<Bezeroak> bezeroDatuak() {
		ArrayList<Bezeroak> arrayBezeroak = new ArrayList<Bezeroak>();
		Statement st = null;
		Connection konexioa = Konexioa.getConexion();
		String izena, abizenak, NAN, pasahitza, sexua;
		java.sql.Date data;
		ResultSet rs = null;
		try {
			st = konexioa.createStatement();
			rs = st.executeQuery("SELECT * FROM cliente");
			while (rs.next()) {
				NAN = (rs.getString(1));
				izena = (rs.getString(2));
				abizenak = (rs.getString(3));
				data = (rs.getDate(4));
				sexua = (rs.getString(5));
				pasahitza = (rs.getString(6));
				Bezeroak bezeroa = new Bezeroak(NAN, izena, abizenak, data, sexua, pasahitza);
				arrayBezeroak.add(bezeroa);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return arrayBezeroak;
	}


	//Leiho5-ko kontsultak
	/** 
	 * Sartutako geltokiaren izena atera,
	 * @author talde1
	 * @param kodGeltokia
	 * @return geltIzena
	 */
	public static String geltokiarenIzena(int kodGeltokia) {
		PreparedStatement st = null;
		Connection konexioa = Konexioa.getConexion();
		String geltIzena = null;
		try {
			st = konexioa
					.prepareStatement("SELECT DISTINCT(nombre) FROM parada WHERE Cod_Parada=" + "'" + kodGeltokia + "'");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				geltIzena = (rs.getString("nombre"));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return geltIzena;

	}
	
	
	//Leiho4-ko insertak
	/**
	 * Bezero berriak erregistratu.
	 * @author talde1
	 * @param pasahitza
	 * @param NAN
	 * @param izena
	 * @param abizenak
	 * @param sexua
	 * @param jaioData
	 * @return arrayBezeroak
	 */
	public static ArrayList<Bezeroak> erregistratuBezeroak(String pasahitza, String NAN, String izena, String abizenak,
			String sexua, String jaioData) {
		ArrayList<Bezeroak> arrayBezeroak = new ArrayList<Bezeroak>();
		Connection konexioa = Konexioa.getConexion();
		try {
			PreparedStatement st = konexioa.prepareStatement(
					"INSERT INTO `cliente` (`DNI`, `Nombre`, `Apellidos`, `Fecha_nac`, `Sexo`, `Contrasena`)"
							+ " VALUES(?, ?, ?, ?, ?, ?)");
			st.setString(1, NAN);
			st.setString(2, izena);
			st.setString(3, abizenak);
			st.setString(4, jaioData);
			st.setString(5, sexua);
			st.setString(6, pasahitza);
			st.executeUpdate();
			st.close();
			System.out.println("Gehitu da");
		} catch (SQLException e) {
			System.out.println("Ez da gehitu");
		}
		arrayBezeroak = bezeroDatuak();
		return arrayBezeroak;
	}

	//Leiho5-ko insertak
	/** Erositako billeteak gorde.
	 * @author talde1
	 * @param txartela
	 * @param ibilbideData
	 * @param ibilbideZbk
	 * @param guztiraPrez
	 * @param hasierakoGeltokiaKod
	 * @param amaierakoGeltokiaKod
	 */
	public static void billeteaKontsulta(Txartelak txartela, String ibilbideData, int ibilbideZbk, float guztiraPrez, int hasierakoGeltokiaKod, int amaierakoGeltokiaKod) {
		// kalkulatu prezioa distantziaren metodoak dietzen
		Connection konexioa = Konexioa.getConexion();
		guztiraPrez = (float) (Math.round(guztiraPrez * 100.0) / 100.0);

		try {
			PreparedStatement st = konexioa.prepareStatement(
					"INSERT INTO `billete` (`NTrayecto`, `Cod_Linea`, `Cod_Bus`, `Cod_Parada_Inicio`, `Cod_Parada_Fin`, `Fecha`, `Hora`, `DNI`, `Precio`, `ibilbideData`)"
							+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			st.setInt(1, ibilbideZbk);
			st.setString(2, txartela.getkodLinea());
			st.setInt(3, txartela.getkodBus());
			st.setInt(4, hasierakoGeltokiaKod);
			st.setInt(5, amaierakoGeltokiaKod);
			st.setDate(6, txartela.getData());
			st.setTimestamp(7, txartela.getOrdua());
			st.setString(8, txartela.getNan());
			st.setFloat(9, guztiraPrez);
			st.setString(10, ibilbideData);
			st.executeUpdate();
			st.close();
			System.out.println("Gehitu da");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
