package ikuspegia;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	GridBagConstraints config = new GridBagConstraints();

	public Frame() {
		this.setTitle("1.taldearen txartel salmenta");
		this.setSize(new Dimension(600, 600));
		this.setResizable(false); // neurketak ez aldatzeko
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}