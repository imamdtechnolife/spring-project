package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class panelProfilRS extends JPanel {

	/**
	 * Create the panel.
	 */
	public panelProfilRS() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(panelProfilRS.class.getResource("/com/PlanetMars323/spring_project/Image/profilRS.png")));
		lblNewLabel.setBounds(419, 11, 528, 711);
		add(lblNewLabel);

	}

}
