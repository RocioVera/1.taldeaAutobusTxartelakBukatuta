package ikuspegia;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import kontrolatzailea.*;

public class Leiho1 extends JFrame {
	private static final long serialVersionUID = 1L;
	//panelan ikusten diren bariableak
	private JButton aurreraBtn;
/**
 * Ongietorria ematen duen panela sortu
 * @author talde1
 */
	public Leiho1() {
		//panelaren propietateak
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\Argazkiak\\logoa.png")); 
		getContentPane().setLayout(null);
		this.setBounds(350,50,600,600);
		this.setTitle("1.taldearen txartel salmenta");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		aurreraBtn = new JButton("");
		aurreraBtn.setIcon(new ImageIcon(".\\Argazkiak\\ongiEtorri.png"));
		aurreraBtn.setBounds(0, -30, 600, 630);
		getContentPane().add(aurreraBtn);
		aurreraBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Metodoak.bigarrenLeihoa();
				dispose();
			}
		});
		
	}		
}