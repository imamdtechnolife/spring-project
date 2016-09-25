package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.Font;

public class menuPengguna extends JPanel {
	
	JPanel panelUbahPassword = new JPanel();
	JPanel newPanel2 = new JPanel();
	private JTable table;
	private JTextField txtNama;
	private JTable table_3;
	private JTextField txtUsername;
	private JTextField txtNoTelp;
	private JTextField txtUsername2;
	private JButton btnSimpan;
	private JComboBox cmbLevel;
	private JTextArea txtAlamat;
	private JPasswordField txtPassword;
	private JButton btnUbah;
	private JButton btnBatal;
	private JPasswordField txtCpassword;
	Connection konek = null;
	private JPasswordField passwordLama;
	private JPasswordField passwordBaru;
	private JPasswordField passwordCfmPassBaru;
	private JTable tabelUser;
	DefaultTableModel modelTabelUser = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Level", "Nama", "No. Telp / HP", "Alamat"
			}
		);
	DefaultTableModel modeTabelPenggunaLengkap = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. ID", "Nama", "Username", "Password", "Level", "No. Telp / HP", "Alamat"
			}
		);
	private JTable tabelPenggunaLengkap;
	private JTextField txtNoID;
	private JTextField txtNoID2;
	private JTextField txtCariNoID;
	private JButton btnRefresh;
	
	/**
	 * Create the panel.
	 */
	public menuPengguna() {
		setLayout(new GridLayout(0,1));
		setVisible(true);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		newPanel2.setLayout(null);
		
		JPanel panelDaftarPengguna = new JPanel();
		tabbedPane.addTab("Daftar Pengguna", null, panelDaftarPengguna, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(42, 36, 324, 123);
		newPanel2.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.setModel(modelTabelUser);
		scrollPane_2.setViewportView(table_3);
		panelDaftarPengguna.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(525, 47, 489, 462);
		panelDaftarPengguna.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNama = new JLabel("Nama :");
		lblNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNama.setBounds(26, 79, 64, 14);
		panel_2.add(lblNama);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUsername.setBounds(27, 113, 96, 14);
		panel_2.add(lblUsername);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtNama.setBounds(209, 73, 233, 29);
		panel_2.add(txtNama);
		txtNama.setColumns(10);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon(menuPengguna.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		btnSimpan.setBounds(145, 379, 105, 33);
		panel_2.add(btnSimpan);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtUsername.setColumns(10);
		txtUsername.setBounds(209, 107, 233, 29);
		panel_2.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPassword.setBounds(27, 147, 96, 14);
		panel_2.add(lblPassword);
		
		JLabel lblKonfirmasiPassword = new JLabel("Konfirmasi Password :");
		lblKonfirmasiPassword.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblKonfirmasiPassword.setBounds(27, 183, 154, 14);
		panel_2.add(lblKonfirmasiPassword);
		
		JLabel lblLevel = new JLabel("Level :");
		lblLevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblLevel.setBounds(26, 208, 105, 30);
		panel_2.add(lblLevel);
		
		JLabel lblNoTelpon = new JLabel("No. Telpon :");
		lblNoTelpon.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoTelpon.setBounds(27, 249, 105, 18);
		panel_2.add(lblNoTelpon);
		
		JLabel lblAlamat = new JLabel("Alamat :");
		lblAlamat.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblAlamat.setBounds(27, 285, 105, 14);
		panel_2.add(lblAlamat);
		
		cmbLevel = new JComboBox();
		cmbLevel.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		cmbLevel.setBounds(209, 211, 177, 29);
		cmbLevel.addItem("-- Pilih Level --");
		cmbLevel.addItem("Petugas TPPRJ");
		cmbLevel.addItem("Petugas TPPRI");
		cmbLevel.addItem("Dokter");
		cmbLevel.addItem("Pengolah Data");
		cmbLevel.addItem("Kepala RS");
		panel_2.add(cmbLevel);
		
		txtNoTelp = new JTextField();
		txtNoTelp.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtNoTelp.setColumns(10);
		txtNoTelp.setBounds(209, 243, 233, 29);
		panel_2.add(txtNoTelp);
		
		btnBatal = new JButton("Batal");
		btnBatal.setIcon(new ImageIcon(menuPengguna.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		btnBatal.setBounds(270, 379, 105, 33);
		panel_2.add(btnBatal);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(209, 141, 233, 29);
		panel_2.add(txtPassword);
		
		txtCpassword = new JPasswordField();
		txtCpassword.setBounds(209, 177, 233, 29);
		panel_2.add(txtCpassword);
		
		JLabel lblPerhatian = new JLabel("");
		lblPerhatian.setBounds(145, 315, 226, 14);
		panel_2.add(lblPerhatian);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(209, 280, 233, 59);
		panel_2.add(scrollPane_1);
		
		txtAlamat = new JTextArea();
		scrollPane_1.setViewportView(txtAlamat);
		
		JLabel lblNoId = new JLabel("No ID :");
		lblNoId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoId.setBounds(26, 43, 64, 14);
		panel_2.add(lblNoId);
		
		txtNoID = new JTextField();
		txtNoID.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtNoID.setColumns(10);
		txtNoID.setBounds(209, 36, 233, 29);
		panel_2.add(txtNoID);
		
		JLabel lblDaftarPengguna = new JLabel("Daftar Pengguna");
		lblDaftarPengguna.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblDaftarPengguna.setBounds(24, 11, 245, 33);
		panelDaftarPengguna.add(lblDaftarPengguna);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 75, 455, 404);
		panelDaftarPengguna.add(scrollPane);
		
		tabelUser = new JTable();
		tabelUser.setModel(modelTabelUser);
		scrollPane.setViewportView(tabelUser);
		tabbedPane.addTab("Ubah Password", null, panelUbahPassword, null);
		panelUbahPassword.setLayout(null);
		
		JLabel lblUsername_1 = new JLabel("Username :");
		lblUsername_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUsername_1.setBounds(58, 117, 85, 26);
		panelUbahPassword.add(lblUsername_1);
		
		JLabel lblPasswordBaru = new JLabel("Password Baru :");
		lblPasswordBaru.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPasswordBaru.setBounds(58, 193, 132, 26);
		panelUbahPassword.add(lblPasswordBaru);
		
		txtUsername2 = new JTextField();
		txtUsername2.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtUsername2.setBounds(231, 111, 198, 29);
		panelUbahPassword.add(txtUsername2);
		txtUsername2.setColumns(10);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(menuPengguna.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(174, 313, 105, 33);
		panelUbahPassword.add(btnUbah);
		
		passwordLama = new JPasswordField();
		passwordLama.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		passwordLama.setBounds(231, 148, 198, 29);
		panelUbahPassword.add(passwordLama);
		
		passwordBaru = new JPasswordField();
		passwordBaru.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		passwordBaru.setBounds(231, 187, 198, 29);
		panelUbahPassword.add(passwordBaru);
		
		passwordCfmPassBaru = new JPasswordField();
		passwordCfmPassBaru.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		passwordCfmPassBaru.setBounds(231, 230, 198, 29);
		panelUbahPassword.add(passwordCfmPassBaru);
		
		JLabel lblUlangPasswordBaru = new JLabel("Ulang Password Baru :");
		lblUlangPasswordBaru.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUlangPasswordBaru.setBounds(58, 236, 161, 23);
		panelUbahPassword.add(lblUlangPasswordBaru);
		
		JLabel lblPasswordLama = new JLabel("Password Lama :");
		lblPasswordLama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPasswordLama.setBounds(58, 154, 132, 28);
		panelUbahPassword.add(lblPasswordLama);
		
		JLabel lblUbahPassword = new JLabel("Ubah Password");
		lblUbahPassword.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblUbahPassword.setBounds(24, 11, 254, 36);
		panelUbahPassword.add(lblUbahPassword);
		
		JLabel lblNoId_1 = new JLabel("No. ID :");
		lblNoId_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoId_1.setBounds(58, 79, 85, 26);
		panelUbahPassword.add(lblNoId_1);
		
		txtNoID2 = new JTextField();
		txtNoID2.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtNoID2.setColumns(10);
		txtNoID2.setBounds(231, 71, 198, 29);
		panelUbahPassword.add(txtNoID2);
		JPanel panelPenggunaLengkap = new JPanel();
		tabbedPane.addTab("Pengguna Lengkap", null, panelPenggunaLengkap, null);
		panelPenggunaLengkap.setLayout(null);
		
		JLabel lblDaftarLengkapPengguna = new JLabel("Review Lengkap Pengguna");
		lblDaftarLengkapPengguna.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblDaftarLengkapPengguna.setBounds(24, 11, 389, 38);
		panelPenggunaLengkap.add(lblDaftarLengkapPengguna);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(34, 105, 1009, 522);
		panelPenggunaLengkap.add(scrollPane_3);
		
		tabelPenggunaLengkap = new JTable();
		tabelPenggunaLengkap.setModel(modeTabelPenggunaLengkap);
		tabelPenggunaLengkap.getColumnModel().getColumn(0).setPreferredWidth(132);
		tabelPenggunaLengkap.getColumnModel().getColumn(1).setPreferredWidth(178);
		tabelPenggunaLengkap.getColumnModel().getColumn(2).setPreferredWidth(126);
		tabelPenggunaLengkap.getColumnModel().getColumn(3).setPreferredWidth(136);
		tabelPenggunaLengkap.getColumnModel().getColumn(4).setPreferredWidth(116);
		tabelPenggunaLengkap.getColumnModel().getColumn(5).setPreferredWidth(123);
		tabelPenggunaLengkap.getColumnModel().getColumn(6).setPreferredWidth(194);
		scrollPane_3.setViewportView(tabelPenggunaLengkap);
		
		JLabel lblCariNoId = new JLabel("Cari No. ID :");
		lblCariNoId.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblCariNoId.setBounds(697, 80, 93, 14);
		panelPenggunaLengkap.add(lblCariNoId);
		
		txtCariNoID = new JTextField();
		txtCariNoID.setBounds(795, 74, 248, 20);
		panelPenggunaLengkap.add(txtCariNoID);
		txtCariNoID.setColumns(10);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(menuPengguna.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(944, 26, 99, 33);
		panelPenggunaLengkap.add(btnRefresh);
		
		table = new JTable();
		table.setToolTipText("Daftar Pengguna");
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"nomor identitas", "nama", "Password"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		
		penghendel hendel = new penghendel();
		btnSimpan.addActionListener(hendel);
		btnBatal.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		txtCariNoID.addActionListener(hendel);
		
		clear();
	}
	
	private void add()
	{
		
		if( txtNoID.getText().equals("") ||	txtAlamat.getText().equals("") || txtNama.getText().equals("") ||	txtNoTelp.getText().equals("") || txtUsername.getText().equals("") || txtPassword.getText().equals("") || txtCpassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Anda belum mengisi semua kolom yang ada.");
			clear();
		}
		else
		{
			if(txtPassword.getText().equals(txtCpassword.getText()))
			{
				try
				{
					char[] pass = txtCpassword.getPassword();
					String sandi = "";
					for(int i=0; i<pass.length; i++)
					{
						sandi = sandi + pass[i];
					}
					
					konek = konek_database.getKonekDB();
					PreparedStatement ps = konek.prepareStatement("insert into akun (no_id_pengguna, nama, username, pass, lvel, no_telp, alamat) values (?,?,?,?,?,?,?)");
					ps.setString(1, txtNoID.getText());
					ps.setString(2, txtNama.getText());
					ps.setString(3, txtUsername.getText());
					ps.setString(4, sandi);
					ps.setString(5, (String) cmbLevel.getSelectedItem());
					ps.setString(6, txtNoTelp.getText());
					ps.setString(7, txtAlamat.getText());
					ps.executeUpdate();
					ps.close();
					
					JOptionPane.showMessageDialog(null, "Username dan Password berhasil tersimpan!", "Pesan",JOptionPane.INFORMATION_MESSAGE);
					konek.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol simpan : "+ ex.getMessage(),"Pesan Kesalahan", JOptionPane.INFORMATION_MESSAGE);
				}
				finally
				{
					clear();
					tampilIsiTabelUser();
					tampilTabelUserLengkap();
				}
				
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Passoword masih belum sama");
				clear();
			}
		}
		
		
		
	}
	
	public void tampilIsiTabelUser()
	{
		modelTabelUser.getDataVector().removeAllElements();
		modelTabelUser.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select lvel,nama,no_telp,alamat from akun");
			
			while(result.next())
			{
				Object obj[] = new Object[4];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				
				modelTabelUser.addRow(obj);
			}
			
			result.close();
			state.close();
			konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampil tabel : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	public void tampilTabelUserLengkap()
	{
		modeTabelPenggunaLengkap.getDataVector().removeAllElements();
		modeTabelPenggunaLengkap.fireTableDataChanged();
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from akun");
			
			while(result.next())
			{
				Object obj[] = new Object[7];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				
				modeTabelPenggunaLengkap.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampilTabelUserLengkap : "+ex.getMessage());
		}
	}
	
	public void cariAkun()
	{
		modeTabelPenggunaLengkap.getDataVector().removeAllElements();
		modeTabelPenggunaLengkap.fireTableDataChanged();
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from akun where no_id_pengguna='"+txtCariNoID.getText()+"'");
			
			while(result.next())
			{
				Object obj[] = new Object[7];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				
				modeTabelPenggunaLengkap.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampilTabelUserLengkap : "+ex.getMessage());
		}
	}
	
	
	private void change()
	{
		try
		{
			String no_id_pengguna = txtNoID2.getText();
			String username = txtUsername2.getText().toString();
			String password = passwordLama.getText().toString();
				
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_id_pengguna, username, pass from akun where no_id_pengguna='"+no_id_pengguna+"' and username='"+username+"' and pass='"+password+"' ");
			
			if(result.next())
			{
				if(passwordBaru.getText().equals(passwordCfmPassBaru.getText()))
				{
					try
					{
						konek = konek_database.getKonekDB();
						PreparedStatement ps = konek.prepareStatement("update akun set pass=? where username=?");
						ps.setString(1, passwordCfmPassBaru.getText());
						ps.setString(2, txtUsername2.getText());
						ps.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Password Berhasil diubah!","Pesan",JOptionPane.INFORMATION_MESSAGE);
						konek.close();
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol simpan password baru : "+ ex.getMessage(),"Pesan Kesalahan", JOptionPane.INFORMATION_MESSAGE);
					}
					finally
					{
						clear2();
						tampilIsiTabelUser();
						tampilTabelUserLengkap();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "password baru yang anda masukkan belum sama.");
					clear2();
					tampilIsiTabelUser();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "username / password lama yang anda masukkan tidak sesuai.");
				clear2();
			}
		}
		catch(Exception ex)
		{
			
		}
		
			
		}
			

	public void clear()
	{
		txtNama.setText("");
		txtUsername.setText("");
		cmbLevel.setSelectedIndex(0);
		txtNoTelp.setText("");
		txtAlamat.setText("");
		txtPassword.setText("");
		txtCpassword.setText("");
		txtNoID.setText("");
		txtNoID2.setText("");
		txtCariNoID.setText("");
		tampilIsiTabelUser();
		tampilTabelUserLengkap();
		clear2();
	}
	
	public void clear2()
	{
		txtNoID2.setText("");
		txtUsername2.setText("");
		passwordLama.setText("");
		passwordBaru.setText("");
		passwordCfmPassBaru.setText("");
	}
	
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnSimpan)
			{
				add();
			}
			else if(e.getSource()==btnBatal)
			{
				clear();
			}
			else if(e.getSource()==btnUbah)
			{
				change();
			}
			else if(e.getSource()==btnRefresh)
			{
				clear();
			}
			else if(e.getSource()==txtCariNoID)
			{
				cariAkun();
			}
		}
	}
}
