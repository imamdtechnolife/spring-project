package com.PlanetMars323.spring_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class frame extends JFrame{

	Container konten = getContentPane();
	
	public frame()
	{
		super("Tes");
		setLayout(new FlowLayout());
		setSize(300,300);
		setVisible(true);
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("Allahuakbar!");
		JButton button = new JButton("Tekan");
		button.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Alhamdulillah!");
			}
				}
		);
		
		konten.add(label);
		konten.add(button);
		
	}
}
