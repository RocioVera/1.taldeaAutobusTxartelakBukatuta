package Aplikazioa;

import kontrolatzailea.*;

public class AutobusTxartelaAPP {

	public static void main(String[] args) {
		String basedatos = "ethazi3";
		Konexioa konex = new Konexioa(basedatos);
		konex.getConexion();
		
		Metodoak.lehenengoLeihoa();

	}
}