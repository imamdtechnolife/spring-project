package com.PlanetMars323.spring_project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class hal_utama extends JFrame {

	private JPanel contentPane;
	public JButton btnDaftarPenyakit;
	public JButton btnDaftarPasien;
	private JPanel panel;
	private JLabel lbl;
	menuPengguna obj = new menuPengguna();
	menuRawatInap objRawatInap = new menuRawatInap();
	panelDaftarDokter objPemeriksaan = new panelDaftarDokter();
	panelHalamanAwal objLogin = new panelHalamanAwal();
	menuLaporan objLaporan = new menuLaporan();
	menuRawatJalan objDaftarPasien = new menuRawatJalan();
	menuPemeriksaan objRMRawatJalan = new menuPemeriksaan();
	menuPasien objPasien = new menuPasien();
	panelDaftarPenyakit objMacamPenyakit = new panelDaftarPenyakit();
	panelProfilKu objProfil = new panelProfilKu();
	panelProfilRS objProfilRS = new panelProfilRS();
	public JButton btnDokter;
	public JButton btnRawatJalan;
	public JButton btnRawatInap;
	public JButton btnPasien;
	public JButton btnPengguna;
	public JButton btnPembayaran;
	public JButton btnLaporan;
	JButton btnMasuk;
	private JPanel panelMasuk;
	private JTextField textField;
	private JPasswordField passwordField;
	Connection konek = null;
	JMenuItem penggunaLogout;
	JComboBox comboLevel;
	menuLaporan tampilKunjungan;
	private JMenuItem aboutDeveloper;
	private JMenuItem aboutRS;
	
	/**
	 * membuat frame.
	 */
	public hal_utama() {
		super("Sistem Informasi Pelayanan Kesehatan Rumah Sakit Bhayangkara Mataram");
		setIconImage(Toolkit.getDefaultToolkit().getImage(hal_utama.class.getResource("image/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 701);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setEnabled(false);
		toolBar.setBounds(0, 22, 1350, 65);
		contentPane.add(toolBar);
		
		btnDaftarPasien = new JButton("Rawat Jalan");
		btnDaftarPasien.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/buku-daftar.png")));
		btnDaftarPasien.setEnabled(false);
		toolBar.add(btnDaftarPasien);
		toolBar.addSeparator();
		
		btnDaftarPenyakit = new JButton("Daftar Penyakit");
		btnDaftarPenyakit.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/d.png")));
		btnDaftarPenyakit.setEnabled(false);
		toolBar.add(btnDaftarPenyakit);
		toolBar.addSeparator();
		
		btnDokter = new JButton("Dokter");
		btnDokter.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/dokter.png")));
		btnDokter.setEnabled(false);
		toolBar.add(btnDokter);
		toolBar.addSeparator();
		
		btnRawatJalan = new JButton("Pemeriksaan");
		btnRawatJalan.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/rawat-jalan.png")));
		btnRawatJalan.setEnabled(false);
		toolBar.add(btnRawatJalan);
		toolBar.addSeparator();
		
		btnRawatInap = new JButton("Rawat Inap");
		btnRawatInap.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/rawat-inap.png")));
		btnRawatInap.setEnabled(false);
		toolBar.add(btnRawatInap);
		toolBar.addSeparator();
		
		btnPasien = new JButton("Pasien");
		btnPasien.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/bersalin.png")));
		btnPasien.setEnabled(false);
		toolBar.add(btnPasien);
		toolBar.addSeparator();
		
		btnLaporan = new JButton("Laporan");
		btnLaporan.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/laporan-1.png")));
		btnLaporan.setEnabled(false);
		toolBar.add(btnLaporan);
		toolBar.addSeparator();
		
		btnPengguna = new JButton("Pengguna");
		btnPengguna.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/kgpg_identity.png")));
		btnPengguna.setEnabled(false);
		toolBar.add(btnPengguna);
		
		panel = new JPanel();
		panel.setBounds(0, 86, 1350, 577);
		
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		panelMasuk = new JPanel();
		panel.add(panelMasuk, "name_9557694810851");
		panelMasuk.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Bookman Old Style", Font.BOLD, 27));
		lblLogin.setBounds(656, 174, 111, 35);
		panelMasuk.add(lblLogin);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(565, 240, 111, 14);
		panelMasuk.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPassword.setBounds(565, 273, 69, 14);
		panelMasuk.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField.setBounds(686, 240, 196, 29);
		panelMasuk.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBounds(686, 272, 196, 29);
		panelMasuk.add(passwordField);
		
		btnMasuk = new JButton("Masuk");
		btnMasuk.setIcon(new ImageIcon(hal_utama.class.getResource("/com/PlanetMars323/spring_project/Image/in.png")));
		btnMasuk.setBounds(638, 362, 111, 35);
		panelMasuk.add(btnMasuk);
		
		JLabel lblLevelUser = new JLabel("Level User");
		lblLevelUser.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblLevelUser.setBounds(565, 308, 89, 14);
		panelMasuk.add(lblLevelUser);
		
		comboLevel = new JComboBox();
		comboLevel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboLevel.setBounds(686, 303, 163, 29);
		comboLevel.addItem("-- Pilih Level --");
		comboLevel.addItem("Petugas TPPRJ");
		comboLevel.addItem("Petugas TPPRI");
		comboLevel.addItem("Dokter");
		comboLevel.addItem("Pengolah Data");
		comboLevel.addItem("Kepala RS");
		panelMasuk.add(comboLevel);
		
		
		JMenu menuPengguna = new JMenu("Pengguna");
		JMenu menuAbout = new JMenu("Tentang");
		
		penggunaLogout = new JMenuItem("Logout");
		
		aboutRS = new JMenuItem("RS. Bhayangkara Mataram");
		aboutDeveloper = new JMenuItem("Pengembang");
		
		menuPengguna.add(penggunaLogout);
		
		menuAbout.add(aboutRS);
		menuAbout.add(aboutDeveloper);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menuPengguna);
		menuBar.add(menuAbout);
		menuBar.setBounds(0, 0, 1350, 21);
		
		
		contentPane.add(menuBar);
		
		penghendel hendel = new penghendel();
		btnDaftarPenyakit.addActionListener(hendel);
		btnDaftarPasien.addActionListener(hendel);
		btnPasien.addActionListener(hendel);
		btnPengguna.addActionListener(hendel);
		btnDokter.addActionListener(hendel);
		btnLaporan.addActionListener(hendel);
		btnMasuk.addActionListener(hendel);
		penggunaLogout.addActionListener(hendel);
		btnRawatJalan.addActionListener(hendel);
		btnRawatInap.addActionListener(hendel);
		aboutDeveloper.addActionListener(hendel);
		aboutRS.addActionListener(hendel);
		
		tampilKunjungan = new menuLaporan();

	}
	
	/**
	 * Metode Login
	 */
	public void login()
	{
		try{
			
		String username = textField.getText().toString();
		String password = passwordField.getText().toString();
		String level = comboLevel.getSelectedItem().toString();
			
		konek = konek_database.getKonekDB();
		Statement state = konek.createStatement();
		ResultSet result = state.executeQuery("select username, pass, lvel from akun where username='"+username+"' and pass='"+password+"' and lvel='"+level+"' ");
		
		if(result.next())
		{
			JOptionPane.showMessageDialog(null, "Alhamdulillah Anda berhasil login :)");
			
			if(level == "Pengolah Data")
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLogin);
				panel.repaint();
				panel.revalidate();
				
				btnDaftarPasien.setEnabled(true);
				btnPengguna.setEnabled(true);
				btnDaftarPenyakit.setEnabled(true);
				btnPasien.setEnabled(true);
				btnDokter.setEnabled(true);
				btnLaporan.setEnabled(true);
				btnRawatInap.setEnabled(true);
				btnRawatJalan.setEnabled(true);
			}
			else if(level == "Petugas TPPRJ")
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLogin);
				panel.repaint();
				panel.revalidate();
				
				btnDaftarPasien.setEnabled(true);
			}
			else if(level == "Dokter")
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLogin);
				panel.repaint();
				panel.revalidate();
				
				btnRawatJalan.setEnabled(true);
				btnRawatInap.setEnabled(true);
				btnDokter.setEnabled(true);
			}
			else if(level == "Kepala RS")
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLogin);
				panel.repaint();
				panel.revalidate();
				
				btnLaporan.setEnabled(true);
				btnRawatJalan.setEnabled(true);
				btnRawatInap.setEnabled(true);
			}
			else if(level == "Petugas TPPRI")
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLogin);
				panel.repaint();
				panel.revalidate();
				
				btnRawatInap.setEnabled(true);
			}	
		}		
		else
		{
			JOptionPane.showMessageDialog(null, "Username atau Password yang anda masukkan salah");
			JOptionPane.showMessageDialog(null, "silahkan ulangi kembali");
			textField.setText("");
			passwordField.setText("");
			comboLevel.setSelectedIndex(0);
			textField.requestFocus();
		}
		konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada kelas login "+ex.getMessage());
		}
		finally
		{
			textField.setText("");
			passwordField.setText("");
		}
	}
	
	/**
	 * Metode me-nonaktifkan menu toolbar
	 */
	private void buttonDisabled()
	{
		btnPasien.setEnabled(false);
		btnDaftarPasien.setEnabled(false);
		btnDaftarPenyakit.setEnabled(false);
		btnDokter.setEnabled(false);
		btnLaporan.setEnabled(false);
		btnPengguna.setEnabled(false);
		btnRawatInap.setEnabled(false);
		btnRawatJalan.setEnabled(false);
		comboLevel.setSelectedIndex(0);
	}

	/**
	 * 
	 * sistem overiding tombol
	 * 
	 * @author Imam Afriyadi
	 *
	 *  
	 */
	public class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnDaftarPenyakit)
			{
				
			panel.removeAll();
			panel.repaint();
			panel.revalidate();
			
			panel.add(objMacamPenyakit);
			panel.repaint();
			panel.revalidate();
			
		}
			else if(e.getSource()==btnDaftarPasien)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objDaftarPasien);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==btnPasien)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objPasien);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==btnPengguna)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(obj);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==btnDokter)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objPemeriksaan);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==btnLaporan)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objLaporan);
				panel.repaint();
				panel.revalidate();
				
				tampilKunjungan.tampilTabelKunjunganPasienPolri();
			}
			else if(e.getSource()==btnMasuk)
			{
				login();
			}
			else if(e.getSource()==penggunaLogout)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(panelMasuk);
				panel.repaint();
				panel.revalidate();
				
				buttonDisabled();
			}
			else if(e.getSource()==btnRawatJalan)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				panel.add(objRMRawatJalan);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==btnRawatInap)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				panel.add(objRawatInap);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==aboutDeveloper)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				panel.add(objProfil);
				panel.repaint();
				panel.revalidate();
			}
			else if(e.getSource()==aboutRS)
			{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				panel.add(objProfilRS);
				panel.repaint();
				panel.revalidate();
			}
	}
}
}