package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

/**
 * Pembuatan Panel Daftar Dokter
 * @author Imam Afriyadi
 *
 */
public class panelDaftarDokter extends JPanel {
	private JTextField txtIdDokter;
	private JTextField txtNamaDokter;
	private JTextField txtNoTelpDokter;
	private JButton btnSimpanDokter;
	private JButton btnBatalDokter;
	Connection konek = null;
	JTextArea txtAlamatDokter;
	JComboBox cmboStatusDokter;
	JComboBox cmboSpesialisDokter;
	private JTable table;
	DefaultTableModel modelTabelDokter = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. ID", "Nama & Gelar", "Spesialis", "No. Telp / Hp", "Alamat", "Status"
			}
		);
	private JButton btnHapus;
	private JButton btnUbah;
	private JButton btnRefresh;

	/**
	 * Constructor
	 */
	public panelDaftarDokter() {
		setLayout(new GridLayout(0,1));	
		setVisible(true);
		
		JTabbedPane tabDokter = new JTabbedPane(JTabbedPane.TOP);
		tabDokter.setBounds(0, 0, 1058, 750);
		add(tabDokter);
		
		JPanel panelDaftarDokter = new JPanel();
		tabDokter.addTab("Daftar Dokter", null, panelDaftarDokter, null);
		panelDaftarDokter.setLayout(null);
		
		JLabel lblDaftarDokter = new JLabel("Daftar Dokter");
		lblDaftarDokter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblDaftarDokter.setBounds(25, 11, 244, 32);
		panelDaftarDokter.add(lblDaftarDokter);
		
		JLabel lblIdDokter = new JLabel("ID Dokter");
		lblIdDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblIdDokter.setBounds(48, 68, 101, 14);
		panelDaftarDokter.add(lblIdDokter);
		
		JLabel lblNamaDokter = new JLabel("Nama beserta gelar");
		lblNamaDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNamaDokter.setBounds(48, 104, 150, 21);
		panelDaftarDokter.add(lblNamaDokter);
		
		JLabel lblNoTelp = new JLabel("No. Telp / Hp");
		lblNoTelp.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoTelp.setBounds(48, 149, 113, 20);
		panelDaftarDokter.add(lblNoTelp);
		
		JLabel lblAlamat_1 = new JLabel("Alamat");
		lblAlamat_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblAlamat_1.setBounds(48, 199, 78, 14);
		panelDaftarDokter.add(lblAlamat_1);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblStatus.setBounds(48, 300, 66, 14);
		panelDaftarDokter.add(lblStatus);
		
		JLabel lblSpesialis = new JLabel("Spesialis");
		lblSpesialis.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblSpesialis.setBounds(48, 348, 113, 22);
		panelDaftarDokter.add(lblSpesialis);
		
		txtIdDokter = new JTextField();
		txtIdDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtIdDokter.setBounds(212, 62, 86, 29);
		panelDaftarDokter.add(txtIdDokter);
		txtIdDokter.setColumns(10);
		
		txtNamaDokter = new JTextField();
		txtNamaDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNamaDokter.setColumns(10);
		txtNamaDokter.setBounds(212, 98, 217, 29);
		panelDaftarDokter.add(txtNamaDokter);
		
		txtNoTelpDokter = new JTextField();
		txtNoTelpDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoTelpDokter.setColumns(10);
		txtNoTelpDokter.setBounds(212, 143, 136, 29);
		panelDaftarDokter.add(txtNoTelpDokter);
		
		cmboStatusDokter = new JComboBox();
		cmboStatusDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboStatusDokter.setBounds(212, 294, 179, 29);
		cmboStatusDokter.addItem("-- Pilih Status --");
		cmboStatusDokter.addItem("BIDOKKES");
		cmboStatusDokter.addItem("INTERNSHIP");
		panelDaftarDokter.add(cmboStatusDokter);
		
		cmboSpesialisDokter = new JComboBox();
		cmboSpesialisDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboSpesialisDokter.setBounds(212, 342, 179, 29);
		cmboSpesialisDokter.addItem("-- Pilih Spesialis --");
		cmboSpesialisDokter.addItem("Umum");
		cmboSpesialisDokter.addItem("Gigi");
		cmboSpesialisDokter.addItem("Mata");
		cmboSpesialisDokter.addItem("Bedah");
		cmboSpesialisDokter.addItem("Urologi");
		cmboSpesialisDokter.addItem("Radiologi");
		cmboSpesialisDokter.addItem("THT");
		cmboSpesialisDokter.addItem("Penyakit Dalam");
		cmboSpesialisDokter.addItem("Urologi");
		cmboSpesialisDokter.addItem("Anak");
		panelDaftarDokter.add(cmboSpesialisDokter);
		
		btnSimpanDokter = new JButton("Simpan");
		btnSimpanDokter.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		btnSimpanDokter.setBounds(499, 402, 101, 32);
		panelDaftarDokter.add(btnSimpanDokter);
		
		btnBatalDokter = new JButton("Batal");
		btnBatalDokter.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		btnBatalDokter.setBounds(610, 402, 101, 32);
		panelDaftarDokter.add(btnBatalDokter);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/b-dokter.png")));
		lblNewLabel.setBounds(485, 62, 234, 345);
		panelDaftarDokter.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 194, 266, 78);
		panelDaftarDokter.add(scrollPane);
		
		txtAlamatDokter = new JTextArea();
		txtAlamatDokter.setLocation(212, 0);
		scrollPane.setViewportView(txtAlamatDokter);
		txtAlamatDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(756, 68, 546, 302);
		panelDaftarDokter.add(scrollPane_1);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(modelTabelDokter);
		table.getColumnModel().getColumn(1).setPreferredWidth(139);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);
		table.getColumnModel().getColumn(4).setPreferredWidth(105);
		table.getColumnModel().getColumn(5).setPreferredWidth(99);
		scrollPane_1.setViewportView(table);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(499, 402, 101, 32);
		btnUbah.setVisible(false);
		panelDaftarDokter.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/Hapus.png")));
		btnHapus.setBounds(610, 402, 101, 32);
		btnHapus.setVisible(false);
		panelDaftarDokter.add(btnHapus);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(panelDaftarDokter.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(558, 458, 101, 32);
		panelDaftarDokter.add(btnRefresh);
		
		penghendel hendel = new penghendel();
		btnBatalDokter.addActionListener(hendel);
		btnSimpanDokter.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		btnHapus.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				ambilDataDokter();
				btnUbah.setVisible(true);
				btnHapus.setVisible(true);
				btnSimpanDokter.setVisible(false);
				btnBatalDokter.setVisible(false);
			}
		});
		tampilIsiTabel();
	}
	
	
	/**
	 * metode pembuatan kode dokter secara otomatis
	 * (canceled)
	 */
	/**
	private void kodeDokterOtomatis()
	{
		String tipekode1 = "D00";
		String tipekode2 = "D0";
		String tipekode3 = "D";
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_id from dokter");
			
			if(result.next())
			{
				for(int i; i<10;i++)
				{
					txtIdDokter.setText(tipekode1+""+i);
				}
				
			}
			else 
			{
				txtIdDokter.setText("D001");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode kodeDokterOtomatis : "+ex.getMessage());
		}
		
	}
	**/
	
	
	/**
	 * metode simpan dokter
	 */
	private void simpanDokter()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("insert into dokter (no_id,nama_dokter,no_telp,alamat,sttus,spesialis) values (?,?,?,?,?,?)");
			ps.setString(1, txtIdDokter.getText());
			ps.setString(2, txtNamaDokter.getText());
			ps.setString(3, txtNoTelpDokter.getText());
			ps.setString(4, txtAlamatDokter.getText());
			ps.setString(5, (String) cmboStatusDokter.getSelectedItem());
			ps.setString(6, (String) cmboSpesialisDokter.getSelectedItem());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan!");
			konek.close();
			ps.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada method simpanDokter() : "+ex.getMessage());
		}
		finally
		{
			bersihDokter();
			tampilIsiTabel();
		}
		
	}
	
	/**
	 * metode menampilkan isi tabel
	 */
	private void tampilIsiTabel()
	{
		modelTabelDokter.getDataVector().removeAllElements();
		modelTabelDokter.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_id,nama_dokter,spesialis,no_telp,alamat,sttus from dokter");
			
			while(result.next())
			{
				Object obj[] = new Object[6];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				
				modelTabelDokter.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode tampilIsiTabel : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	/**
	 * metode membersihkan isi kolom pada panel 'Daftar Dokter'
	 */
	private void bersihDokter()
	{
		txtIdDokter.setText("");
		txtNamaDokter.setText("");
		txtNoTelpDokter.setText("");
		txtAlamatDokter.setText("");
		cmboStatusDokter.setSelectedIndex(0);
		cmboSpesialisDokter.setSelectedIndex(0);
		btnSimpanDokter.setVisible(true);
		btnBatalDokter.setVisible(true);
		btnUbah.setVisible(false);
		btnHapus.setVisible(false);
	}
	
	/**
	 * metode mengambil data dari tabel dokter kemudian menampilkannya ke field dokter
	 */
	private void ambilDataDokter()
	{
		int i = table.getSelectedRow();
		
		if(i==-1)
		{
			return;
		}
		
		String ambilIdDokter = (String) modelTabelDokter.getValueAt(i, 0);
		txtIdDokter.setText(ambilIdDokter);
		
		String ambilNamaDokter = (String) modelTabelDokter.getValueAt(i, 1);
		txtNamaDokter.setText(ambilNamaDokter);
		
		String ambilSpesialisDokter = (String) modelTabelDokter.getValueAt(i, 2);
		cmboSpesialisDokter.setSelectedItem(ambilSpesialisDokter);
		
		String ambilNoTelpDokter = (String) modelTabelDokter.getValueAt(i, 3);
		txtNoTelpDokter.setText(ambilNoTelpDokter);
		
		String ambilAlamatDokter = (String) modelTabelDokter.getValueAt(i, 4);
		txtAlamatDokter.setText(ambilAlamatDokter);
		
		String ambilStatusDokter = (String) modelTabelDokter.getValueAt(i, 5);
		cmboStatusDokter.setSelectedItem(ambilStatusDokter);

	}
	
	/**
	 * metode mengubah data dokter
	 */
	private void ubahDokter()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("update dokter set no_id=?, nama_dokter=?, spesialis=?, no_telp=?, alamat=?, sttus=? where no_id=?");
			ps.setString(1, txtIdDokter.getText());
			ps.setString(2, txtNamaDokter.getText());
			ps.setString(3, (String) cmboSpesialisDokter.getSelectedItem());
			ps.setString(4, txtNoTelpDokter.getText());
			ps.setString(5, txtAlamatDokter.getText());
			ps.setString(6, (String) cmboStatusDokter.getSelectedItem());
			ps.setString(7, txtIdDokter.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ubahDokter : "+ex.getMessage());
		}
		finally
		{
			bersihDokter();
			tampilIsiTabel();
		}
	}
	
	private void hapusDokter()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("delete from dokter where no_id=?");
			ps.setString(1, txtIdDokter.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode hapusDokter : "+ex.getMessage());
		}
		finally
		{
			bersihDokter();
			tampilIsiTabel();
		}
	}
	
	
	/**
	 * 	Mengoverride fungsi tombol
	 * @author Imam Afriyadi
	 *
	 */
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnSimpanDokter)
			{
				boolean status = cmboStatusDokter.getSelectedItem().equals("-- Pilih Status --");
				boolean spesialis = cmboSpesialisDokter.getSelectedItem().equals("-- Pilih Spesialis");
				
				if(txtIdDokter.getText().equals("") || txtNamaDokter.getText().equals("") || txtNoTelpDokter.getText().equals("") || txtAlamatDokter.getText().equals("") || status || spesialis)
				{
					JOptionPane.showMessageDialog(null, "Anda belum mengisi semua field");
					JOptionPane.showMessageDialog(null, "Silahkan dilengkapi terlebih dahulu :)");
				}
				else
				{
					simpanDokter();
				}
			}
			else if(e.getSource()==btnBatalDokter)
			{
				bersihDokter();
			}
			else if(e.getSource()==btnUbah)
			{
				ubahDokter();
			}
			else if(e.getSource()==btnHapus)
			{
				hapusDokter();
			}
			else if(e.getSource()==btnRefresh)
			{
				bersihDokter();
			}
			
			
		}
	}
}
