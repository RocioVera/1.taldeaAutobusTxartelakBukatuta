package kontrolatzailea;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Konexioa {
	private static Connection conexion = null;
	private String makina, bezeroa, pasahitza, portuaStr, zerbitzaria;
	private int portua;
	// Datu basearen izena hartu
	public Konexioa(String datuBasea) {

		String fitx = "src\\eredua\\DBkonexioa";
		try {
			FileReader fr = new FileReader(fitx);
			BufferedReader br = new BufferedReader(fr);

			Object[] arraya = br.lines().toArray();
			this.makina = String.valueOf(arraya[0]);
			this.bezeroa = String.valueOf(arraya[1]);
			this.pasahitza = String.valueOf(arraya[2]);
			this.portuaStr =String.valueOf(arraya[3]);
			this.portua = Integer.parseInt(portuaStr);
			this.zerbitzaria = String.valueOf(arraya[4]);

			fr.close();
		} catch (Exception e) {
			System.out.println("Fitxeroa (" + fitx + ") irakurtzerakoan salbuezpena: " + e);
		}

		this.zerbitzaria = "jdbc:mysql://" + this.makina + ":" + this.portua + "/" + datuBasea;

		// Driverra erregistratu
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver-a erregistatzerakoan errorea.");
			System.exit(0); // exekuzioa gelditu
		}

		// Zerbitzariarekin konexioa ezarri
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://" + this.makina, this.bezeroa, this.pasahitza);
		} catch (SQLException e) {
			System.err.println("Zerbitzariarekin konektatzerakoan errorea.");
			System.exit(0); // parar la ejecución
		}
		System.out.println(datuBasea+ " konektatuta" );
	}

	public static Connection getConexion() {
		return conexion;
	}

}