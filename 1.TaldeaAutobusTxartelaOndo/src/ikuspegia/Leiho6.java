package ikuspegia;

import java.awt.*;
import javax.swing.*;
import kontrolatzailea.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Leiho6 extends JFrame {
	private static final long serialVersionUID = 1L;
	// panelan ikusten diren bariableak
	private JLabel lblEskerrikAskoMezua1, lblEskerrikAskoMezua2;
	private JButton btnTiketaImprimatu;
	private JTextArea txtTiket;

	/**
	 * Tiketa imprimatzen duen panela sortu
	 * 
	 * @param hartutakoLinea
	 * @param autobusa
	 * @param ibilbideZbk
	 * @param hasierakoGeltokiaKod
	 * @param amaierakoGeltokiaKod
	 * @author talde1
	 * @param txartela
	 * @param geltIzenak
	 * @param dataEtorri
	 * @param dataJoan
	 * @param arrayGeltokia
	 */
	public Leiho6(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk, int hasierakoGeltokiaKod,
			int amaierakoGeltokiaKod, Txartelak txartela, ArrayList<String> geltIzenak, String dataJoan,
			String dataEtorri) {
		// panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		this.setTitle("1.taldearen txartel salmenta");

		// Eskerrik asko mezua
		lblEskerrikAskoMezua1 = new JLabel("Eskerrik asko Termibus-eko");
		lblEskerrikAskoMezua1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEskerrikAskoMezua1.setFont(new Font("MS PMincho", Font.BOLD, 30));
		lblEskerrikAskoMezua1.setBounds(53, 13, 493, 40);
		getContentPane().add(lblEskerrikAskoMezua1);

		lblEskerrikAskoMezua2 = new JLabel("makinetan erosteagatik");
		lblEskerrikAskoMezua2.setFont(new Font("MS PMincho", Font.BOLD, 30));
		lblEskerrikAskoMezua2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEskerrikAskoMezua2.setBounds(63, 55, 493, 40);

		getContentPane().add(lblEskerrikAskoMezua2);

		txtTiket = new JTextArea();
		txtTiket.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		if (txartela.getzIbilbidea() == 1)
			txtTiket.setText("\n Ibilbidearen datuak:  \n" + "\t-Hartutako linea:  " + txartela.getkodLinea()
					+ "\n\t-Zure autobusaren kodigoa:  " + txartela.getkodBus() + "\n\t-Hasierako geltokia:  "
					+ geltIzenak.get(0) + "\n\t-Amaierako geltokia:  " + geltIzenak.get(1)
					+ "\n\t-Hartutako eguna eta ordua: " + dataJoan + " " + "\n     \n\n Bezeroaren datuak: \n\t-NAN: "
					+ txartela.getNan() + "\n\n Erosketaren datuak: \n\t-Bidaiaren prezioa:  " + txartela.getPrezioa()
					+ "€");

		else if (txartela.getzIbilbidea() == 2)
			txtTiket.setText(" Ibilbidearen datuak:  \n" + "      -Hartutako linea:  " + txartela.getkodLinea()
					+ "      -Zure autobusaren kodigoa:  " + txartela.getkodBus()
					+ "\n\n      *Lehenengo bidaia\n\t-Hasierako geltokia:  " + geltIzenak.get(0)
					+ "\n\t-Amaierako geltokia:  " + geltIzenak.get(1) + "\n\t-Hartutako eguna eta ordua: " + dataJoan
					+ "\n      *Bigarren bidaia\n\t-Hasierako geltokia:  " + geltIzenak.get(1)
					+ "\n\t-Amaierako geltokia:  " + geltIzenak.get(0) + "\n\t-Hartutako eguna eta ordua: " + dataEtorri
					+ "\n\n Bezeroaren datuak: \n" + "      -NAN: " + txartela.getNan() + "\n\n Erosketaren datuak: \n"
					+ "      -Bidaiaren prezioa:  " + txartela.getPrezioa() + "€");

		txtTiket.setEditable(false);
		txtTiket.setBackground(Color.LIGHT_GRAY);
		txtTiket.setBounds(37, 146, 545, 391);
		getContentPane().add(txtTiket);

		btnTiketaImprimatu = new JButton("Tiketa imprimatu eta bukatu erosketa");
		btnTiketaImprimatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Metodoak.Leiho_segunduak();
				dispose();
			}
		});
		btnTiketaImprimatu.setBounds(131, 108, 306, 25);
		getContentPane().add(btnTiketaImprimatu);

	}
}