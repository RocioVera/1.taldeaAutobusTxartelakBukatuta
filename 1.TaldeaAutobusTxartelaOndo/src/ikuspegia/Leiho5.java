package ikuspegia;

import java.awt.*;
import javax.swing.*;
import kontrolatzailea.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Leiho5 extends JFrame {
	private static final long serialVersionUID = 1L;
	// panelan ikusten diren bariableak
	private JTextField txtPrezioTot = new JTextField(), txtDiruFalta = new JTextField(), txtBueltak = new JTextField();
	private JTextArea txtrTxtareatxanponbueltak = new JTextArea();
	private JButton btn_next = new JButton("Hurrengoa"), btn_prev = new JButton("Atzera"),
			restart = new JButton("\u2302"), btn_200Euro, btn_100Euro, btn_50Euro, btn_20Euro, btn_10Euro, btn_5Euro,
			btn_2Euro, btn_1Euro, btn_50Zent, btn_20Zent, btn_10Zent, btn_5Zent, btn_2Zent, btn_1Zent, btnErosi;
	private JLabel lblPrezioTotala, lblZenbatDiru, lblEuro, lblZentimo, lblDiruFalta, lblBueltak;

	// bariableak
	private Txartelak txartela;
	private Date gaurkoData = Date.valueOf(LocalDate.now());
	private Timestamp ordua = new Timestamp(System.currentTimeMillis());
	private int kont = 0;
	private String txanponTot, diruFaltaString, ibilbideData;
	private double diruFalta, sartutakoa;
	private ArrayList<String> geltIzenak = new ArrayList<>();

	/**
	 * Ordaintzen duen panela sortu
	 * @param ibilbideZbk
	 * @param nan
	 * @param luzera2
	 * @param altuera2
	 * @param luzera1
	 * @param altuera1
	 * @author talde1
	 * @param arrayGeltokia 
	 * @param dataEtorri 
	 * @param dataJoan 
	 */
	public Leiho5(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk, int hasierakoGeltokiaKod,
			int amaierakoGeltokiaKod, float guztiraPrez, String nan, double altuera1, double luzera1, double altuera2,
			double luzera2, ArrayList<Geltokiak> arrayGeltokia, String dataJoan, String dataEtorri) {
		// panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setResizable(false); //neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		this.setTitle("1.taldearen txartel salmenta");

		// botoiak
		btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txartela = new Txartelak(hartutakoLinea, autobusa.getKodBus(), hasierakoGeltokiaKod,
						amaierakoGeltokiaKod, gaurkoData, ordua, nan, guztiraPrez, ibilbideZbk, ibilbideData);
				System.out.println(guztiraPrez+"AQUI");

				geltIzenak=Metodoak.geltokienIzenakLortu(txartela);

				if (txartela.getzIbilbidea()==1) {
					ibilbideData=dataJoan;
					Metodoak.sortuBilletea(txartela, ibilbideData,ibilbideZbk, (float)(guztiraPrez+0.005),hasierakoGeltokiaKod, amaierakoGeltokiaKod ); // billetea datu basera igotzen duen metodoari deitu
				}
				else if (txartela.getzIbilbidea()==2) {
					ibilbideData=dataJoan;
					Metodoak.sortuBilletea(txartela, ibilbideData,ibilbideZbk-1,(float)( guztiraPrez/2-0.005),hasierakoGeltokiaKod, amaierakoGeltokiaKod ); // billetea datu basera igotzen duen metodoari deitu
					ibilbideData=dataEtorri;
					Metodoak.sortuBilletea(txartela,ibilbideData,ibilbideZbk, (float)(guztiraPrez/2+0.005),amaierakoGeltokiaKod, hasierakoGeltokiaKod); // billetea datu basera igotzen duen metodoari deitu
				}
				Metodoak.fitxIdatzi(txartela, geltIzenak, dataJoan, dataEtorri); // billetea fitxategian sartzen duen metodoari deitu
				Metodoak.seigarrenLeihoa(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod,
						amaierakoGeltokiaKod, txartela,geltIzenak, dataJoan, dataEtorri);
				dispose();
			}
		});
		btn_next.setBounds(423, 500, 122, 32);
		btn_next.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btn_next.setBackground(Color.LIGHT_GRAY);
		btn_next.setForeground(Color.RED);
		btn_next.setVisible(false);
		getContentPane().add(btn_next);

		btn_prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodoak.laugarrenLeihoa(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod,
						amaierakoGeltokiaKod, altuera1, luzera1, altuera2, luzera2, arrayGeltokia,dataJoan, dataEtorri);

				dispose();
			}
		});
		btn_prev.setBounds(38, 500, 99, 32);
		btn_prev.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btn_prev.setForeground(Color.RED);
		btn_prev.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(btn_prev);

		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodoak.lehenengoLeihoa();
				dispose();
			}
		});
		restart.setBounds(245, 500, 72, 32);
		restart.setForeground(Color.RED);
		restart.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(restart);

		/* Prezio totala */
		lblPrezioTotala = new JLabel("Prezio totala:");
		lblPrezioTotala.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrezioTotala.setBounds(176, 29, 117, 20);
		getContentPane().add(lblPrezioTotala);

		txtPrezioTot.setEditable(false);
		txtPrezioTot.setColumns(10);
		txtPrezioTot.setBounds(300, 29, 86, 20);
		txtPrezioTot.setText(guztiraPrez + " €");
		getContentPane().add(txtPrezioTot);

		/* ZenbatDiru sartu nahi */
		lblZenbatDiru = new JLabel("Zenbat diru sartu nahi duzu?");
		lblZenbatDiru.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblZenbatDiru.setBounds(164, 72, 304, 20);
		getContentPane().add(lblZenbatDiru);

		lblEuro = new JLabel("Euroak");
		lblEuro.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEuro.setBounds(115, 119, 56, 20);
		getContentPane().add(lblEuro);

		lblZentimo = new JLabel("Zentimoak");
		lblZentimo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblZentimo.setBounds(411, 119, 134, 20);
		getContentPane().add(lblZentimo);

		btn_200Euro = new JButton("200\u20AC");
		btn_200Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 1;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_200Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_200Euro.setBackground(Color.WHITE);
		btn_200Euro.setBounds(36, 150, 89, 23);
		getContentPane().add(btn_200Euro);

		btn_100Euro = new JButton("100\u20AC");
		btn_100Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 2;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_100Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_100Euro.setBackground(Color.WHITE);
		btn_100Euro.setBounds(36, 184, 89, 23);
		getContentPane().add(btn_100Euro);

		btn_50Euro = new JButton("50\u20AC");
		btn_50Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 3;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_50Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_50Euro.setBackground(Color.WHITE);
		btn_50Euro.setBounds(36, 218, 89, 23);
		getContentPane().add(btn_50Euro);

		btn_20Euro = new JButton("20\u20AC");
		btn_20Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 4;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_20Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_20Euro.setForeground(Color.BLACK);
		btn_20Euro.setBackground(Color.WHITE);
		btn_20Euro.setBounds(36, 252, 89, 23);
		getContentPane().add(btn_20Euro);

		btn_10Euro = new JButton("10\u20AC");
		btn_10Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 5;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_10Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_10Euro.setBackground(Color.WHITE);
		btn_10Euro.setBounds(164, 150, 89, 23);
		getContentPane().add(btn_10Euro);

		btn_5Euro = new JButton("5\u20AC");
		btn_5Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 6;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_5Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_5Euro.setBackground(Color.WHITE);
		btn_5Euro.setBounds(164, 184, 89, 23);
		getContentPane().add(btn_5Euro);

		btn_2Euro = new JButton("2\u20AC");
		btn_2Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 7;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_2Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_2Euro.setBackground(Color.WHITE);
		btn_2Euro.setBounds(164, 218, 89, 23);
		getContentPane().add(btn_2Euro);

		btn_1Euro = new JButton("1\u20AC");
		btn_1Euro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 8;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_1Euro.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_1Euro.setBackground(Color.WHITE);
		btn_1Euro.setBounds(164, 252, 89, 23);
		getContentPane().add(btn_1Euro);

		btn_50Zent = new JButton("50 zent");
		btn_50Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 9;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_50Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_50Zent.setBackground(Color.WHITE);
		btn_50Zent.setBounds(341, 150, 89, 23);
		getContentPane().add(btn_50Zent);

		btn_20Zent = new JButton("20 zent");
		btn_20Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 10;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_20Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_20Zent.setBackground(Color.WHITE);
		btn_20Zent.setBounds(341, 184, 89, 23);
		getContentPane().add(btn_20Zent);

		btn_10Zent = new JButton("10 zent");
		btn_10Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 11;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_10Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_10Zent.setBackground(Color.WHITE);
		btn_10Zent.setBounds(341, 218, 89, 23);
		getContentPane().add(btn_10Zent);

		btn_5Zent = new JButton("5 zent");
		btn_5Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 12;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_5Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_5Zent.setBackground(Color.WHITE);
		btn_5Zent.setBounds(469, 150, 89, 23);
		getContentPane().add(btn_5Zent);

		btn_2Zent = new JButton("2 zent");
		btn_2Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 13;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_2Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_2Zent.setBackground(Color.WHITE);
		btn_2Zent.setBounds(469, 184, 89, 23);
		getContentPane().add(btn_2Zent);

		btn_1Zent = new JButton("1 zent");
		btn_1Zent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kont = 14;
				sartutakoa = Metodoak.diruaSartu(kont, sartutakoa);
			}
		});
		btn_1Zent.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_1Zent.setBackground(Color.WHITE);
		btn_1Zent.setBounds(469, 218, 89, 23);
		getContentPane().add(btn_1Zent);

		// Diru falta
		txtDiruFalta = new JTextField();
		txtDiruFalta.setForeground(Color.RED);
		txtDiruFalta.setEditable(false);
		txtDiruFalta.setColumns(10);
		txtDiruFalta.setBounds(166, 393, 64, 20);
		txtDiruFalta.setText(guztiraPrez + " €");
		getContentPane().add(txtDiruFalta);

		txtBueltak = new JTextField();
		// Zenbat bueltak eman behar
		txtBueltak.setForeground(Color.BLUE);
		txtBueltak.setEditable(false);
		txtBueltak.setColumns(10);
		txtBueltak.setBounds(166, 439, 64, 20);
		txtBueltak.setText("00.00€");
		getContentPane().add(txtBueltak);

		/* Erosi */
		btnErosi = new JButton("Erosi");
		btnErosi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diruFalta = Metodoak.diruFaltaBueltakMetodoa(diruFalta, guztiraPrez, sartutakoa);
				diruFaltaString = String.valueOf(diruFalta);

				if (diruFalta > 0) {
					// Diru falta
					txtDiruFalta.setText(diruFaltaString + " €");
					txtBueltak.setText("00.00 €");
					
				} else if (diruFalta < 0) {
					diruFaltaString = String.valueOf(-diruFalta);
					txtBueltak.setText(diruFaltaString + " €");
					txtDiruFalta.setText("00.00 €");
					// txanponTot
					txanponTot = Metodoak.diruBueltakZerrenda(diruFalta);
					txtrTxtareatxanponbueltak.setText(txanponTot);

				} else {
					txtDiruFalta.setText("00.00 €");
					txtBueltak.setText("00.00 €");
				}
				if (diruFalta <= 0) {
					btnErosi.setEnabled(false);
					btn_next.setVisible(true);
					btn_prev.setVisible(false);
					restart.setVisible(false);

					btn_200Euro.setEnabled(false);
					btn_100Euro.setEnabled(false);
					btn_50Euro.setEnabled(false);
					btn_20Euro.setEnabled(false);
					btn_20Euro.setEnabled(false);
					btn_10Euro.setEnabled(false);
					btn_5Euro.setEnabled(false);
					btn_2Euro.setEnabled(false);
					btn_1Euro.setEnabled(false);
					btn_50Zent.setEnabled(false);
					btn_20Zent.setEnabled(false);
					btn_10Zent.setEnabled(false);
					btn_5Zent.setEnabled(false);
					btn_2Zent.setEnabled(false);
					btn_2Zent.setEnabled(false);
					btn_1Zent.setEnabled(false);
				}
			}

		});
		btnErosi.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnErosi.setBackground(Color.WHITE);
		btnErosi.setForeground(new Color(0, 0, 0));
		btnErosi.setBounds(115, 321, 90, 38);
		getContentPane().add(btnErosi);

		// Diru falta
		lblDiruFalta = new JLabel("Diru falta:");
		lblDiruFalta.setForeground(Color.RED);
		lblDiruFalta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDiruFalta.setBounds(56, 393, 95, 20);
		getContentPane().add(lblDiruFalta);

		// Zenbat bueltak eman behar
		lblBueltak = new JLabel("Bueltak:");
		lblBueltak.setForeground(Color.BLUE);
		lblBueltak.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBueltak.setBounds(56, 439, 86, 20);
		getContentPane().add(lblBueltak);

		// Bueltak zerrenda
		txtrTxtareatxanponbueltak.setBackground(Color.LIGHT_GRAY);
		txtrTxtareatxanponbueltak.setText("Emaiozu botoiari");
		txtrTxtareatxanponbueltak.setBounds(322, 252, 172, 238);
		getContentPane().add(txtrTxtareatxanponbueltak);

	}
}
