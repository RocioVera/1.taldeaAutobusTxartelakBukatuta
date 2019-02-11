package ikuspegia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import kontrolatzailea.*;

public class Leiho2 extends JFrame {
	private static final long serialVersionUID = 1L;
	//panelan ikusten diren bariableak
	private JTextField txtErabakiLinea;
	private JRadioButton L1, L2, L3, L4;
	private ButtonGroup group;
	private JButton btn_next = new JButton("Hurrengoa"), restart = new JButton("\u2302");

	//bariableak
	private String hartutakoLinea;
	private Autobusak autobusa;
	
	/**
	 * Ze linea hartu nahi duen erabaki behar den panela sortu
	 * @param lineak
	 * @author talde1
	 */
	public Leiho2(ArrayList<Lineak> lineak) {
		//panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setTitle("1.taldearen txartel salmenta");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		Font font;
		font = new Font("Verdana", Font.PLAIN, 16);

		// botoiak
		btn_next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//lamar al metodo que elije autobus
				Metodoak.hirugarrenLeihoa(hartutakoLinea, autobusa);
				dispose();
			}
		});
		btn_next.setBounds(350, 500, 122, 32);
		btn_next.setFont(new Font("Tahoma", Font.ITALIC, 16));
		btn_next.setForeground(Color.RED);
		btn_next.setBackground(Color.LIGHT_GRAY);

		getContentPane().add(btn_next);
		btn_next.setVisible(false);
		
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodoak.lehenengoLeihoa();
				dispose();
			}
		});
		restart.setBounds(125, 500, 72, 32);
		restart.setForeground(Color.RED);
		restart.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(restart);

		// Linea bakoitzaren izena eta kodigoa jartzeko
		L1 = new JRadioButton(lineak.get(0).getKodLinea() + "        " + lineak.get(0).getIzena());
		L2 = new JRadioButton(lineak.get(1).getKodLinea() + "        " + lineak.get(1).getIzena());
		L3 = new JRadioButton(lineak.get(2).getKodLinea() + "        " + lineak.get(2).getIzena());
		L4 = new JRadioButton(lineak.get(3).getKodLinea() + "        " + lineak.get(3).getIzena());
		group = new ButtonGroup();
		txtErabakiLinea = new JTextField();

		// non dagoen linea bakoitza kolokatuta
		L1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setVisible(true);
				hartutakoLinea="L1";
				//llamar a consulta
				autobusa = Metodoak.autobusKotsMaxMetodoa(hartutakoLinea);
			}
		});
		L1.setBounds(47, 85, 530, 85);
		L1.setFont(font);
		getContentPane().add(L1);

		L2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setVisible(true);
				hartutakoLinea="L2";
				autobusa = Metodoak.autobusKotsMaxMetodoa(hartutakoLinea);
			}
		});
		L2.setBounds(47, 187, 530, 85);
		L2.setFont(font);
		getContentPane().add(L2);

		L3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setVisible(true);
				hartutakoLinea="L3";
				autobusa = Metodoak.autobusKotsMaxMetodoa(hartutakoLinea);
			}
		});
		L3.setBounds(47, 288, 530, 85);
		L3.setFont(font);
		getContentPane().add(L3);

		L4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_next.setVisible(true);
				hartutakoLinea="L4";
				autobusa = Metodoak.autobusKotsMaxMetodoa(hartutakoLinea);
			}
		});
		L4.setBounds(47, 380, 530, 85);
		L4.setFont(font);
		getContentPane().add(L4);

		//bakarrik bat hartu ahal izateko
		group.add(L1);
		group.add(L2);
		group.add(L3);
		group.add(L4);

		//mezua
		txtErabakiLinea.setText("Klikatu nahi duzun linearen gainean");
		txtErabakiLinea.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		txtErabakiLinea.setHorizontalAlignment(SwingConstants.CENTER);
		txtErabakiLinea.setForeground(Color.LIGHT_GRAY);
		txtErabakiLinea.setBackground(Color.RED);
		txtErabakiLinea.setEditable(false);
		txtErabakiLinea.setBounds(0, 24, 600, 50);
		getContentPane().add(txtErabakiLinea);

	}
}