package ikuspegia;

import java.awt.*;
import javax.swing.*;
import kontrolatzailea.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import com.toedter.calendar.*;

public class Leiho4 extends JFrame {
	private static final long serialVersionUID = 1L;
	// panelan ikusten diren bariableak
	private JTextField txtPrezioTot = new JTextField(), txtNan = new JTextField(), txtIzena = new JTextField(),
			txtAbizenak = new JTextField(), txtSexua = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JLabel lblPrezioTotala, lblNan, lblPasahitza, lblIzena, lblAbizenak, lblJaioData, lblSexua,
			lblErroreakonektatu, lblKonekBezeroMezua;
	private JButton btnHasiSaioa, btnKonektatu = new JButton("Konektatu"),
			btnErregistratuNahi = new JButton("Erregistratu"), btnErregistratu = new JButton("Erregistratu"),
			btn_next = new JButton("Hurrengoa"), btn_prev = new JButton("Atzera"), restart = new JButton("\u2302");
	private JDateChooser txtJaioData = new JDateChooser();
	private JTextFieldDateEditor dataEzEditatu; // kentzeko eskuz sartu ahal izana

	// bariableak
	private java.util.Date jaioData;
	private SimpleDateFormat dataFormato = new SimpleDateFormat("yyyy-MM-dd");
	private String jaioDataString, pasahitza, nan, izena, abizenak, sexua, nanLarria;;
	private float guztiraPrez;
	private boolean balPasa, balNan, balErregis, nanBalErregistratu;
	private int nanLuzera = 8, izenLuzera = 49, abizenLuzera = 99, pasahitzLuzera = 49, sexuLuzera = 0;
	private char letra;

	/**
	 * login edo erregistratu ahal den panela sortu
	 * 
	 * @param hartutakoLinea
	 * @param autobusa
	 * @param ibilbideZbk
	 * @param hasierakoGeltokiaKod
	 * @param amaierakoGeltokiaKod
	 * @param altuera1
	 * @param luzera1
	 * @param altuera2
	 * @param luzera2
	 * @author talde1
	 * @param arrayGeltokia
	 * @param dataEtorri
	 * @param dataJoan
	 */
	public Leiho4(String hartutakoLinea, Autobusak autobusa, int ibilbideZbk, int hasierakoGeltokiaKod,
			int amaierakoGeltokiaKod, double altuera1, double luzera1, double altuera2, double luzera2,
			ArrayList<Geltokiak> arrayGeltokia, String dataJoan, String dataEtorri) {
		// panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		this.setTitle("1.taldearen txartel salmenta");

		// botoiak
		btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodoak.bostgarrenLeihoa(hartutakoLinea, autobusa, ibilbideZbk, hasierakoGeltokiaKod,
						amaierakoGeltokiaKod, guztiraPrez, nan, altuera1, luzera1, altuera2, luzera2, arrayGeltokia,
						dataJoan, dataEtorri);
				dispose();
			}
		});
		btn_next.setBounds(423, 500, 122, 32);
		btn_next.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btn_next.setForeground(Color.RED);
		getContentPane().add(btn_next);

		btn_prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodoak.hirugarrenLeihoa(hartutakoLinea, autobusa);
				dispose();
			}
		});
		btn_prev.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btn_prev.setForeground(Color.RED);
		btn_prev.setBounds(38, 500, 99, 32);
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
		getContentPane().add(restart);

		// non, formatua eta zer jarri
		lblPrezioTotala = new JLabel("Prezio totala:");
		lblPrezioTotala.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPrezioTotala.setBounds(157, 37, 117, 20);
		getContentPane().add(lblPrezioTotala);

		txtPrezioTot.setEditable(false);
		txtPrezioTot.setColumns(10);
		txtPrezioTot.setBounds(281, 37, 86, 20);

		// guztiraPrez kalkulatzeko metodoari deitu
		guztiraPrez = Metodoak.kalkPrezioa(autobusa.getKmKontsumoa(), autobusa.getzPlaza(), altuera1, luzera1, altuera2,
				luzera2, ibilbideZbk);
		txtPrezioTot.setText(guztiraPrez + " €");
		getContentPane().add(txtPrezioTot);
		btn_next.setVisible(false);

		btnKonektatu.setVisible(false);
		lblNan = new JLabel("NAN:");
		lblPasahitza = new JLabel("Pasahitza:");

		lblKonekBezeroMezua = new JLabel("Hasi saioa edo erregistratu");
		lblKonekBezeroMezua.setForeground(Color.BLUE);
		lblKonekBezeroMezua.setHorizontalAlignment(SwingConstants.CENTER);
		lblKonekBezeroMezua.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 14));
		lblKonekBezeroMezua.setBounds(122, 74, 328, 25);
		getContentPane().add(lblKonekBezeroMezua);

		btnErregistratu.setVisible(false);

		// hasi saioari ematerakoan agertu behar diren bariableak
		btnHasiSaioa = new JButton("Hasi saioa");
		btnHasiSaioa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// bisibilitatea
				btnKonektatu.setEnabled(true);
				btnKonektatu.setVisible(true);
				btnErregistratu.setVisible(false);
				btnErregistratu.setEnabled(true);
				btnHasiSaioa.setVisible(false);
				btn_next.setVisible(false);
				btnErregistratuNahi.setVisible(true);

				lblKonekBezeroMezua.setVisible(false);
				lblErroreakonektatu.setVisible(false);
				lblIzena.setVisible(false);
				lblAbizenak.setVisible(false);
				lblJaioData.setVisible(false);
				lblSexua.setVisible(false);
				lblPasahitza.setVisible(true);
				lblNan.setVisible(true);

				txtIzena.setVisible(false);
				txtAbizenak.setVisible(false);
				txtJaioData.setVisible(false);
				txtSexua.setVisible(false);
				passwordField.setVisible(true);
				txtNan.setVisible(true);

				txtIzena.setEnabled(true);
				txtAbizenak.setEnabled(true);
				txtJaioData.setEnabled(true);
				txtSexua.setEnabled(true);
				passwordField.setEnabled(true);
				txtNan.setEnabled(true);

				lblNan.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblNan.setBounds(214, 211, 62, 20);
				getContentPane().add(lblNan);

				txtNan.setBounds(283, 211, 86, 20);
				getContentPane().add(txtNan);
				txtNan.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						letra = e.getKeyChar();
						// 9 baino gehiago edo espazioa sartzerakoan
						if (txtNan.getText().length() > nanLuzera || (letra == '\b'))
							e.consume(); // ez du godetzen
					}
				});

				lblPasahitza.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblPasahitza.setBounds(184, 266, 93, 20);
				getContentPane().add(lblPasahitza);

				passwordField.setEchoChar('*');
				passwordField.setBounds(283, 269, 86, 20);
				getContentPane().add(passwordField);
			}
		});
		btnHasiSaioa.setBounds(143, 112, 109, 25);
		getContentPane().add(btnHasiSaioa);

		lblErroreakonektatu = new JLabel();
		lblErroreakonektatu.setVisible(false);
		lblErroreakonektatu.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblErroreakonektatu);

		// hasi saioari eman ostean ziurtatzeko bezeroa erregistratuta dagoela eta erosi
		// dezakela billetea
		btnKonektatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bbdd-ra konektatu pasahitza frogatzeko
				nan = txtNan.getText();
				pasahitza = String.valueOf(passwordField.getPassword());
				balPasa = Metodoak.frogatuPasahitza(pasahitza);
				balNan = Metodoak.frogatuNAN(nan);
				if (balPasa && balNan) {
					btn_next.setVisible(true);
					lblErroreakonektatu.setBounds(145, 329, 318, 22);
					lblErroreakonektatu.setForeground(Color.BLACK);
					lblErroreakonektatu.setText("Konektatuta");
					lblErroreakonektatu.setVisible(true);
					passwordField.setEnabled(false);
					txtNan.setEnabled(false);
					btnKonektatu.setEnabled(false);
					btnErregistratuNahi.setVisible(false);
				} else {
					lblErroreakonektatu.setBounds(145, 329, 318, 22);
					lblErroreakonektatu.setForeground(Color.RED);
					lblErroreakonektatu.setText("NAN-a edo pasahitza ez dago ondo, sartu berriz");
					lblErroreakonektatu.setVisible(true);
				}
			}
		});
		btnKonektatu.setBounds(245, 360, 104, 25);
		getContentPane().add(btnKonektatu);

		lblIzena = new JLabel("Izena:");
		lblAbizenak = new JLabel("Abizenak:");
		lblJaioData = new JLabel("Jaio data:");
		lblSexua = new JLabel("Sexua (E/G):");

		// erregistratu ematerakoan agertu behar diren bariableak
		btnErregistratuNahi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// bisibilitatea
				btn_next.setVisible(false);
				btnErregistratu.setVisible(true);
				btnHasiSaioa.setVisible(true);
				btnKonektatu.setVisible(false);
				btnErregistratuNahi.setVisible(false);

				lblKonekBezeroMezua.setVisible(false);
				lblErroreakonektatu.setVisible(false);
				lblIzena.setVisible(true);
				lblAbizenak.setVisible(true);
				lblJaioData.setVisible(true);
				lblSexua.setVisible(true);
				lblPasahitza.setVisible(true);
				lblNan.setVisible(true);

				txtIzena.setVisible(true);
				txtAbizenak.setVisible(true);
				txtJaioData.setVisible(true);
				txtSexua.setVisible(true);
				passwordField.setVisible(true);
				txtNan.setVisible(true);
				passwordField.setEnabled(true);
				txtNan.setEnabled(true);

				// non, formatua eta zer jarri
				lblNan.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblNan.setBounds(212, 150, 62, 20);
				getContentPane().add(lblNan);

				txtNan.setBounds(281, 150, 86, 20);
				getContentPane().add(txtNan);
				txtNan.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						letra = e.getKeyChar();
						// 9 baino gehiago edo espazioa sartzerakoan
						if (txtNan.getText().length() > nanLuzera || (letra == '\b'))
							e.consume(); // ez du godetzen
					}
				});

				lblIzena.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblIzena.setBounds(203, 183, 62, 20);
				getContentPane().add(lblIzena);

				txtIzena.setBounds(281, 185, 86, 20);
				getContentPane().add(txtIzena);
				txtIzena.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						// 50 baino gehiago ez sartzeko
						if (txtIzena.getText().length() > izenLuzera)
							e.consume(); // ez du godetzen
					}
				});

				lblAbizenak.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblAbizenak.setBounds(178, 226, 93, 20);
				getContentPane().add(lblAbizenak);

				txtAbizenak.setBounds(281, 228, 86, 20);
				getContentPane().add(txtAbizenak);
				txtAbizenak.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						// 100 baino gehiago ez sartzeko
						if (txtAbizenak.getText().length() > abizenLuzera)
							e.consume(); // ez du godetzen
					}
				});

				// non, formatua eta zer jarri
				lblPasahitza.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblPasahitza.setBounds(175, 360, 93, 20);
				getContentPane().add(lblPasahitza);

				passwordField.setEchoChar('*');
				passwordField.setBounds(281, 362, 86, 20);
				getContentPane().add(passwordField);
				passwordField.addKeyListener(new KeyAdapter() {
					@SuppressWarnings("deprecation")
					public void keyTyped(KeyEvent e) {
						letra = e.getKeyChar();
						// 50 baino gehiago ez sartzeko
						if (passwordField.getText().length() > pasahitzLuzera)
							e.consume(); // ez du godetzen
					}
				});

				// non, formatua eta zer jarri
				lblSexua.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblSexua.setBounds(143, 270, 122, 20);
				getContentPane().add(lblSexua);

				txtSexua.setBounds(281, 272, 86, 20);
				getContentPane().add(txtSexua);
				txtSexua.addKeyListener(new KeyAdapter() {
					public void keyTyped(KeyEvent e) {
						letra = e.getKeyChar();
						// bakarrik karakter bat (V,v,M,m) sartzekoa
						if (txtSexua.getText().length() > sexuLuzera
								|| letra != 'E' && letra != 'e' && letra != 'G' && letra != 'g')
							e.consume(); // ez du godetzen
					}
				});

				// non, formatua eta zer jarri
				lblJaioData.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblJaioData.setBounds(175, 315, 90, 20);
				getContentPane().add(lblJaioData);

				txtJaioData.setDateFormatString("dd-MM-yyyy");
				txtJaioData.setBounds(281, 315, 112, 20);
				dataEzEditatu = (JTextFieldDateEditor) txtJaioData.getDateEditor();
				dataEzEditatu.setEditable(false);
				getContentPane().add(txtJaioData);
				txtJaioData.setMaxSelectableDate(Date.valueOf(LocalDate.now()));// gehienez jarri gaurko data

			}
		});
		btnErregistratuNahi.setBounds(313, 112, 122, 25);
		getContentPane().add(btnErregistratuNahi);

		// ziurtatzeko bete dituela datu guztiak
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bbdd-ra konektatu pasahitza frogatzeko
				nan = txtNan.getText();
				pasahitza = String.valueOf(passwordField.getPassword());
				izena = txtIzena.getText();
				abizenak = txtAbizenak.getText();
				jaioData = txtJaioData.getDate();
				sexua = txtSexua.getText();
				lblErroreakonektatu.setForeground(Color.RED);
				lblErroreakonektatu.setBounds(122, 445, 318, 22);
				lblErroreakonektatu.setVisible(true);

				// erregistratzen duen metodoari deitu
				if (jaioData != null)
					jaioDataString = dataFormato.format(jaioData);

				if (nan != null && nan.length() == nanLuzera + 1)
					nanBalErregistratu = Metodoak.nanBalidazioa(nan);

				if (nanBalErregistratu) {
					nanLarria = nan.substring(8).toUpperCase();
					nan = nan.substring(0, 8) + nanLarria;
					balErregis = Metodoak.erregistratuBezeroak(pasahitza, nan, izena, abizenak, sexua, jaioDataString);

					if (nan.length() - 1 == nanLuzera && !Metodoak.nanGordetaEgon(nan))
						txtNan.setEnabled(false);
					else if (!izena.isEmpty())
						txtIzena.setEnabled(false);
					else if (!abizenak.isEmpty())
						txtAbizenak.setEnabled(false);
					else if (!sexua.isEmpty())
						txtSexua.setEnabled(false);
					else if (jaioData != null)
						txtJaioData.setEnabled(false);
					else if (pasahitza.length() != 0)
						passwordField.setEnabled(false);
				}

				if (balErregis) {
					if (Metodoak.nanGordetaEgon(nan)) {
						lblErroreakonektatu.setText("Erregistratuta zaude, hasi saioa.");
						passwordField.setEnabled(false);
						txtIzena.setEnabled(false);
						txtAbizenak.setEnabled(false);
						txtJaioData.setEnabled(false);
						txtSexua.setEnabled(false);
					} else {
						btn_next.setVisible(true);
						lblErroreakonektatu.setForeground(Color.BLACK);
						lblErroreakonektatu.setText("Erregistratuta");
						passwordField.setEnabled(false);
						txtNan.setEnabled(false);
						txtIzena.setEnabled(false);
						txtAbizenak.setEnabled(false);
						txtJaioData.setEnabled(false);
						txtSexua.setEnabled(false);
						btnErregistratu.setEnabled(false);
						btnHasiSaioa.setVisible(false);
					}
				} else { //!balErregis
					lblErroreakonektatu.setForeground(Color.RED);
					if (nan.length() + 1 < nanLuzera)
						lblErroreakonektatu.setText("nan-a bete behar duzu.");
					else if (nanBalErregistratu == false)
						lblErroreakonektatu.setText("nan-a txarto sartu duzu.");
					else if (Metodoak.nanGordetaEgon(nan)) {
						lblErroreakonektatu.setText("Erregistratuta zaude, hasi saioa.");
						passwordField.setEnabled(false);
						txtIzena.setEnabled(false);
						txtAbizenak.setEnabled(false);
						txtJaioData.setEnabled(false);
						txtSexua.setEnabled(false);
					} else if (izena.isEmpty())
						lblErroreakonektatu.setText("izena bete behar duzu.");
					else if (abizenak.isEmpty())
						lblErroreakonektatu.setText("abizena bete behar duzu.");
					else if (sexua.isEmpty())
						lblErroreakonektatu.setText("sexua bete behar duzu.");
					else if (jaioData == null)
						lblErroreakonektatu.setText("jaioData bete behar duzu.");
					else if (pasahitza.length() == 0)
						lblErroreakonektatu.setText("pasahitza bete behar duzu.");
				}
			}

		});
		btnErregistratu.setBounds(233, 408, 109, 25);
		getContentPane().add(btnErregistratu);

	}
}