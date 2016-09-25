package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class panelProfilKu extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelProfilKu() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(panelProfilKu.class.getResource("/com/PlanetMars323/spring_project/Image/profilku.png")));
		lblNewLabel.setBounds(206, 69, 504, 360);
		add(lblNewLabel);

	}
}
