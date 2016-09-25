package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class menuPemeriksaan extends JPanel {
	private JTextField txtNama;
	private JTextField txtUmur;
	private JTextField txtNoReg;
	private JTextField txtNoRm;
	private JComboBox cmboPoli;
	private JFormattedTextField txtTanggal;
	private JFormattedTextField txtJam;
	private JButton btnCari;
	private JTextArea txtTherapi;
	private JButton btnSimpan;
	private JButton btnBatal;
	Connection konek = null;
	private JTextField txtIcd10;
	private JTextField txtDtd;
	private JButton btnDiagnose;
	private JComboBox cmboMacamPenyakit;
	private JList listPenyakit;
	DefaultListModel listModel;
	private JTextField txtCariDokter;
	private JButton btnCariDokter;
	private JLabel lblDokter;
	private JTable table;
	DefaultTableModel modelTabelRMRJ = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. RJ", "Nama", "Umur", "No. Reg", "No. RM", "Status Pasien", "Poliklinik", "Tanggal", "Jam", "Therapi", "TTD / ID Dokter"
			}
		);
	private JButton btnUbah;
	private JButton btnHapus;
	private JButton btnRefresh;
	private JTabbedPane tabbedPane;
	private JComboBox cmboStatusPasien;
	private DefaultListModel modelListMacamPenyakit = new DefaultListModel();
	private JTextField txtNoRJ;
	private JButton btnRefresh2;
	private JButton btnEksekusiUbah;

	/**
	 * Create the panel.
	 */
	public menuPemeriksaan() {
		setLayout(new GridLayout(0,1));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1084, 637);
		add(tabbedPane);
		
		JPanel panelTabelRMRJ = new JPanel();
		tabbedPane.addTab("Review Rekam Medis Pasien Rawat Jalan", null, panelTabelRMRJ, null);
		panelTabelRMRJ.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(33, 92, 1180, 506);
		panelTabelRMRJ.add(scrollPane_2);
		
		table = new JTable();
		table.setModel(modelTabelRMRJ);
		table.getColumnModel().getColumn(10).setPreferredWidth(126);
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				ambilDataRMRJ();
				btnHapus.setEnabled(true);
				btnUbah.setEnabled(true);
			}
		});
		scrollPane_2.setViewportView(table);
		
		JLabel lblTabelRekamMedis = new JLabel("Review Rekam Medis Pasien Rawat Jalan");
		lblTabelRekamMedis.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblTabelRekamMedis.setBounds(23, 11, 607, 40);
		panelTabelRMRJ.add(lblTabelRekamMedis);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(683, 48, 98, 33);
		btnUbah.setEnabled(false);
		panelTabelRMRJ.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Hapus.png")));
		btnHapus.setBounds(786, 48, 98, 33);
		btnHapus.setEnabled(false);
		panelTabelRMRJ.add(btnHapus);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(894, 48, 98, 33);
		panelTabelRMRJ.add(btnRefresh);
		
		listModel = new DefaultListModel();
		//membuatJam();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormatter df = new DateFormatter(format); 
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("Rekam Medis Pasien Rawat Jalan", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(384, 245, 397, 124);
		panel.add(scrollPane_1);
		
		txtTherapi = new JTextArea();
		scrollPane_1.setViewportView(txtTherapi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 245, 277, 124);
		panel.add(scrollPane);
		listPenyakit = new JList(modelListMacamPenyakit);
		scrollPane.setViewportView(listPenyakit);
		
		
		JLabel lblAnamesePemeriksaan = new JLabel("Anamese & Pemeriksaan / Diagnose");
		lblAnamesePemeriksaan.setBounds(62, 213, 264, 29);
		panel.add(lblAnamesePemeriksaan);
		lblAnamesePemeriksaan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblTherapi = new JLabel("Therapi");
		lblTherapi.setBounds(384, 220, 93, 14);
		panel.add(lblTherapi);
		lblTherapi.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		//TimeFormat formatJam = new SimpleTimeFormat();
		txtJam = new JFormattedTextField();
		txtJam.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtJam.setBounds(1214, 44, 127, 29);
		panel.add(txtJam);
		
		JLabel lblJam = new JLabel("Jam :");
		lblJam.setBounds(1139, 44, 46, 29);
		panel.add(lblJam);
		lblJam.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblTanggal_1 = new JLabel("Tanggal :");
		lblTanggal_1.setBounds(1139, 11, 69, 29);
		panel.add(lblTanggal_1);
		lblTanggal_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtTanggal = new JFormattedTextField(df);
		txtTanggal.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtTanggal.setBounds(1214, 11, 127, 29);
		txtTanggal.setValue(new Date());
		panel.add(txtTanggal);
		
		cmboPoli = new JComboBox();
		cmboPoli.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboPoli.setBounds(965, 84, 171, 29);
		/**
		cmboBagian.addItem("Poli Umum");
		cmboBagian.addItem("Poli Gigi");
		cmboBagian.addItem("Poli Mata");
		cmboBagian.addItem("Poli Bedah");
		cmboBagian.addItem("Poli Urologi");
		cmboBagian.addItem("Poli Radiologi");
		cmboBagian.addItem("Poli THT");
		cmboBagian.addItem("Poli Penyakit Dalam");
		cmboBagian.addItem("Poli BKIA");
		cmboBagian.addItem("Poli Laktasi");
		**/
		cmboPoli.addItem("-- Pilih Poli --");
		cmboPoli.addItem("Poli Umum");
		cmboPoli.addItem("Poli Gigi");
		cmboPoli.addItem("Poli Mata");
		cmboPoli.addItem("Poli Bedah");
		cmboPoli.addItem("Poli Urologi");
		cmboPoli.addItem("Poli Radiologi");
		cmboPoli.addItem("Poli THT");
		cmboPoli.addItem("Poli Penyakit Dalam");
		cmboPoli.addItem("Poli BKIA");
		cmboPoli.addItem("Poli Laktasi");
		cmboPoli.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						boolean i = cmboPoli.getSelectedItem().equals("Gigi");
						if(i)
						{
							ambilDataMacamPenyakitDokterGigi();
						}
						else
						{
							ambilDataMacamPenyakit();
						}
					}
				}
		);
		
		panel.add(cmboPoli);
		
		JLabel lblTanggal = new JLabel("Poli :");
		lblTanggal.setBounds(847, 85, 46, 29);
		panel.add(lblTanggal);
		lblTanggal.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		btnCari = new JButton("Cari");
		btnCari.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Cari.png")));
		btnCari.setBounds(711, 113, 89, 39);
		panel.add(btnCari);
		
		txtNoRm = new JTextField();
		txtNoRm.setToolTipText("Masukkan No. RM kemudian klik tombol 'Cari'");
		txtNoRm.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoRm.setBounds(552, 115, 149, 29);
		panel.add(txtNoRm);
		txtNoRm.setColumns(10);
		
		txtNoReg = new JTextField();
		txtNoReg.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoReg.setBounds(552, 85, 149, 29);
		panel.add(txtNoReg);
		txtNoReg.setColumns(10);
		
		JLabel lblNoReg = new JLabel("No. Reg :");
		lblNoReg.setBounds(440, 83, 74, 31);
		panel.add(lblNoReg);
		lblNoReg.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblNoRm = new JLabel("No. RM :");
		lblNoRm.setBounds(440, 122, 80, 22);
		panel.add(lblNoRm);
		lblNoRm.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		txtUmur = new JTextField();
		txtUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtUmur.setBounds(140, 152, 46, 29);
		panel.add(txtUmur);
		txtUmur.setColumns(10);
		
		JLabel lblUmur = new JLabel("Umur :");
		lblUmur.setBounds(65, 152, 65, 29);
		panel.add(lblUmur);
		lblUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblNama = new JLabel("Nama :");
		lblNama.setBounds(65, 120, 65, 31);
		panel.add(lblNama);
		lblNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNama.setBounds(140, 122, 259, 29);
		panel.add(txtNama);
		txtNama.setColumns(10);
		
		JLabel lblRekamMedisRawat = new JLabel("Rekam Medis Pasien Rawat Jalan");
		lblRekamMedisRawat.setBounds(25, 11, 465, 33);
		panel.add(lblRekamMedisRawat);
		lblRekamMedisRawat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		btnSimpan.setBounds(810, 273, 104, 39);
		panel.add(btnSimpan);
		
		btnBatal = new JButton("Batal");
		btnBatal.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		btnBatal.setBounds(810, 315, 104, 39);
		panel.add(btnBatal);
		
		btnDiagnose = new JButton("Diagnose");
		btnDiagnose.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/oke.png")));
		btnDiagnose.setBounds(566, 383, 112, 26);
		panel.add(btnDiagnose);
		
		cmboMacamPenyakit = new JComboBox();
		cmboMacamPenyakit.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		cmboMacamPenyakit.setBounds(72, 380, 471, 29);
		itemCombo item = new itemCombo();
		cmboMacamPenyakit.addItemListener(item);
		panel.add(cmboMacamPenyakit);
		
		txtIcd10 = new JTextField();
		txtIcd10.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtIcd10.setBounds(166, 430, 173, 29);
		panel.add(txtIcd10);
		txtIcd10.setColumns(10);
		
		txtDtd = new JTextField();
		txtDtd.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtDtd.setColumns(10);
		txtDtd.setBounds(166, 470, 86, 29);
		panel.add(txtDtd);
		
		JLabel lblNewLabel = new JLabel("No. ICD 10 :");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNewLabel.setBounds(62, 433, 94, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNoDtd = new JLabel("No. DTD :");
		lblNoDtd.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoDtd.setBounds(62, 473, 69, 14);
		panel.add(lblNoDtd);
		
		btnCariDokter = new JButton("Cari");
		btnCariDokter.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Cari.png")));
		btnCariDokter.setBounds(1061, 127, 89, 39);
		panel.add(btnCariDokter);
		
		txtCariDokter = new JTextField();
		txtCariDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtCariDokter.setBounds(965, 124, 86, 29);
		panel.add(txtCariDokter);
		txtCariDokter.setColumns(10);
		
		JLabel lblNoIdDokter = new JLabel("No. ID Dokter :");
		lblNoIdDokter.setBounds(847, 126, 104, 23);
		panel.add(lblNoIdDokter);
		lblNoIdDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		lblDokter = new JLabel();
		lblDokter.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblDokter.setBounds(922, 173, 396, 29);
		panel.add(lblDokter);
		
		JLabel lblDokter_1 = new JLabel("Dokter :");
		lblDokter_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblDokter_1.setBounds(847, 179, 65, 23);
		panel.add(lblDokter_1);
		
		JLabel lblStatusPasien = new JLabel("Status Pasien :");
		lblStatusPasien.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblStatusPasien.setBounds(440, 152, 117, 29);
		panel.add(lblStatusPasien);
		
		cmboStatusPasien = new JComboBox();
		cmboStatusPasien.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboStatusPasien.setBounds(552, 153, 160, 29);
		cmboStatusPasien.addItem("-- Pilih Status --");
		cmboStatusPasien.addItem("Personil");
		cmboStatusPasien.addItem("PNS");
		cmboStatusPasien.addItem("Siswa Dikbang");
		cmboStatusPasien.addItem("Siswa Diktuk");
		cmboStatusPasien.addItem("Tahanan");
		cmboStatusPasien.addItem("Mandiri");
		cmboStatusPasien.addItem("BPJS");
		cmboStatusPasien.addItem("Purnawirawan");
		panel.add(cmboStatusPasien);
		
		JLabel lblNoRj = new JLabel("No. RJ :");
		lblNoRj.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoRj.setBounds(65, 82, 65, 31);
		panel.add(lblNoRj);
		
		txtNoRJ = new JTextField();
		txtNoRJ.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoRJ.setColumns(10);
		txtNoRJ.setBounds(140, 84, 69, 29);
		txtNoRJ.setEditable(true);
		panel.add(txtNoRJ);
		
		btnEksekusiUbah = new JButton("Ubah");
		btnEksekusiUbah.setEnabled(false);
		btnEksekusiUbah.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnEksekusiUbah.setBounds(924, 273, 104, 39);
		panel.add(btnEksekusiUbah);
		
		btnRefresh2 = new JButton("Refresh");
		btnRefresh2.setIcon(new ImageIcon(menuPemeriksaan.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh2.setBounds(1224, 113, 104, 39);
		panel.add(btnRefresh2);
		
		
		penghendel hendel = new penghendel();
		btnCari.addActionListener(hendel);
		btnDiagnose.addActionListener(hendel);
		btnSimpan.addActionListener(hendel);
		btnBatal.addActionListener(hendel);
		btnCariDokter.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		btnHapus.addActionListener(hendel);
		btnEksekusiUbah.addActionListener(hendel);
		btnRefresh2.addActionListener(hendel);
		txtNoRJ.addActionListener(hendel);
		
		bersihPanelRMRJ();
	}
	
	private void bersihPanelRMRJ()
	{
		/**
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_rj from rm_pasien_rawat_jalan order by no_rj desc");
			
			if(result.next())
			{
				int i = result.getInt(1) + 1;
				txtNoRJ.setText(""+i);
			}
			else
			{
				txtNoRJ.setText("1");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada model no_rj otomatis : "+ex.getMessage());
		}
		**/
		ambilDataMacamPenyakit();
		membuatJam();
		tampilTabelRMRJ();
		btnSimpan.setEnabled(true);
		btnHapus.setEnabled(false);
		btnUbah.setEnabled(false);
		btnEksekusiUbah.setEnabled(false);
		table.clearSelection();
		txtCariDokter.setText("");
		txtDtd.setText("");
		txtIcd10.setText("");
		txtTherapi.setText("");
		txtUmur.setText("");
		txtNama.setText("");
		txtNoReg.setText("");
		txtNoRm.setText("");
		cmboPoli.setSelectedIndex(0);
		cmboMacamPenyakit.setSelectedIndex(0);
		txtTanggal.setValue(new Date());
		lblDokter.setText("");
		//listPenyakit.
		modelListMacamPenyakit.clear();
		cmboStatusPasien.setSelectedIndex(0);
		txtNoRJ.setText("");
	}
	
	private class itemCombo implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(e.getSource()==cmboMacamPenyakit)
			{
				cariPenyakit();
			}
		}
	}
	
	
	private void membuatJam()
	{
		String nol_jam = "";
		String nol_menit = "";
		String nol_detik = "";
		
		Date date = new Date();
		int j = date.getHours();
		int m = date.getMinutes();
		int d = date.getSeconds();
		
		if(j<=9)
		{
			nol_jam = "0";
		}
		if(m<=9)
		{
			nol_menit = "0";
		}
		if(d<=9)
		{
			nol_detik = "0";
		}
		
		
		String jam =  nol_jam + Integer.toString(j);
		String menit = nol_menit + Integer.toString(m);
		String detik = nol_detik + Integer.toString(d);
		
		String timer = jam+":"+menit+":"+detik;
		txtJam.setText(timer);
	}
	
	
	public void ambilDataMacamPenyakit() 
	{
		cmboMacamPenyakit.removeAllItems();
		cmboMacamPenyakit.addItem("-- Pilih Macam Penyakit --");
		String macamPenyakit = "";
		try
		{
		konek = konek_database.getKonekDB();
		Statement state = konek.createStatement();
		ResultSet result = state.executeQuery("select macam_penyakit from macam_penyakit");
		
			while(result.next())
			{
				String daftarPenyakit = result.getString(1);
				
				cmboMacamPenyakit.addItem(daftarPenyakit);
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ambildatamacampenyakit : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private void ambilDataMacamPenyakitDokterGigi() 
	{
		cmboMacamPenyakit.removeAllItems();
		cmboMacamPenyakit.addItem("-- Pilih Macam Penyakit --");
		String macamPenyakit = "";
		try
		{
		konek = konek_database.getKonekDB();
		Statement state = konek.createStatement();
		ResultSet result = state.executeQuery("select macam_penyakit from macam_penyakit where pen_gigidanmulut='Ya'");
		
			while(result.next())
			{
				String daftarPenyakit = result.getString(1);
				
				cmboMacamPenyakit.addItem(daftarPenyakit);
			}		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ambildatamacampenyakit : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private void simpanDiagnosa()
	{
		boolean status_pasien = (boolean) cmboStatusPasien.getSelectedItem().equals("-- Pilih Status --");
		
		if(txtNoRm.getText().equals("") || txtIcd10.getText().equals("") || status_pasien)
		{
			JOptionPane.showMessageDialog(null, "Anda belum mengisi data dengan lengkap");
			JOptionPane.showMessageDialog(null, "Mohon dilengkapi :)");
		}
		else
		{
			modelListMacamPenyakit.addElement(" - "+cmboMacamPenyakit.getSelectedItem());
			
				try
				{
					konek = konek_database.getKonekDB();
					PreparedStatement ps = konek.prepareStatement("insert into daftar_penyakit (no_rm,no_icd10,status_pasien) values(?,?,?)");
					ps.setString(1, txtNoRm.getText());
					ps.setString(2, txtIcd10.getText());
					ps.setString(3, (String) cmboStatusPasien.getSelectedItem());
					ps.executeUpdate();
					
					konek.close();
					ps.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode simpanDiagnosa : "+ex.getMessage());
				}
				finally
				{
					
				}
			}	
		}
	
	private void simpanRMRawatJalan()
	{
		
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("insert into rm_pasien_rawat_jalan (no_rj,no_reg,no_rm,nama, umur,poli,tanggal,jam,terapi,no_id,status_pasien) values (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, (int) Integer.parseInt(txtNoRJ.getText()));
			ps.setString(2, txtNoReg.getText());
			ps.setString(3, txtNoRm.getText());
			ps.setString(4, txtNama.getText());
			ps.setString(5, txtUmur.getText());
			ps.setString(6, (String) cmboPoli.getSelectedItem());
			ps.setString(7, txtTanggal.getText());
			ps.setString(8, txtJam.getText());
			ps.setString(9, txtTherapi.getText());
			ps.setString(10, txtCariDokter.getText());
			ps.setString(11, (String) cmboStatusPasien.getSelectedItem());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !");
			ps.close();
			konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode simpanRMRawat Jalan : "+ex.getMessage());
		}
		finally
		{
			tampilTabelRMRJ();
			bersihPanelRMRJ();
		}
	}
	
	public void cariPasien()
	{
		
		String nama = "";
		int umur = 0;
		String no_reg = "";
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nama,umur,no_reg from pasien where no_rm ='"+txtNoRm.getText()+"'");
			
			if(result.next())
			{
				nama = result.getString(1);
				 umur = result.getInt(2);
				 no_reg = result.getString(3);
				 
			}
			
			txtNama.setText(nama);
			txtUmur.setText(""+umur);
			txtNoReg.setText(no_reg);
			
			ResultSet resultPenyakit = state.executeQuery("select no_icd10 from daftar_penyakit where no_rm="+txtNoRm.getText()+"");
			while(resultPenyakit.next())
			{
				String i = resultPenyakit.getString(1);
				modelListMacamPenyakit.addElement(i);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ambil pasien : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private void cariDokter()
	{	
		String nama_dokter = "";
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nama_dokter from dokter where no_id='"+txtCariDokter.getText()+"'");
			
			if(result.next())
			{
				nama_dokter = result.getString(1);
			}
			
			lblDokter.setText(nama_dokter);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ambildokter : "+ex.getMessage());
		}
	}
	
	private void tampilTabelRMRJ()
	{
		modelTabelRMRJ.getDataVector().removeAllElements();
		modelTabelRMRJ.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_rj,nama,umur,no_reg,no_rm,status_pasien,poli,tanggal,jam,terapi,no_id from rm_pasien_rawat_jalan");
			
			while(result.next())
			{
				Object obj[] = new Object[11];
				obj[0] = result.getInt(1);
				obj[1] = result.getString(2);
				obj[2] = result.getInt(3);
				obj[3] = result.getString(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getDate(8);
				obj[8] = result.getTime(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				
				modelTabelRMRJ.addRow(obj);
				
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode tampilTabelRMRJ : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private void ubahRMRJ()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("update rm_pasien_rawat_jalan set nama=?,umur=?,no_reg=?,no_rm=?,poli=?,tanggal=?,jam=?,terapi=?,no_id=?,status_pasien=? where no_rj=?");
			ps.setString(1, txtNama.getText());
			ps.setInt(2, Integer.parseInt(txtUmur.getText()));
			ps.setString(3, txtNoReg.getText());
			ps.setString(4, txtNoRm.getText());
			ps.setString(5, (String) cmboPoli.getSelectedItem());
			ps.setString(6, txtTanggal.getText());
			ps.setString(7, txtJam.getText());
			ps.setString(8, txtTherapi.getText());
			ps.setString(9, txtCariDokter.getText());
			ps.setString(10, (String) cmboStatusPasien.getSelectedItem());
			ps.setInt(11, Integer.parseInt(txtNoRJ.getText()));
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
			ps.close();
			konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul ubahRMRJ : "+ex.getMessage());
		}
		finally
		{
			bersihPanelRMRJ();
		}
	}
	
	private void hapusRMRJ()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("delete from rm_pasien_rawat_jalan where no_rj=?");
			ps.setString(1, txtNoRJ.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada meode hpusRMRJ : "+ex.getMessage());
		}
		finally
		{
			tampilTabelRMRJ();
		}
	}
	
	private void ambilDaftarRawatJalan()
	{
		String no_rm="",status_pasien="",poli="";
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nomor_rm,status_personil,bagian_dikunjungi from pasien_daftar where nomor_urut="+txtNoRJ.getText()+"");
			
			if(result.next())
			{
				no_rm = result.getString(1);
				status_pasien = result.getString(2);
				poli = result.getString(3);
			}
			
			txtNoRm.setText(no_rm);
			cmboStatusPasien.setSelectedItem(status_pasien);
			cmboPoli.setSelectedItem(poli);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul ambilPasienRawatJalan() : "+ex.getMessage());
		}
	}
	
	private void ambilDataRMRJ()
	{
		int i = table.getSelectedRow();
		
		int ambilKodeRJ = (int) modelTabelRMRJ.getValueAt(i, 0);
		txtNoRJ.setText(""+ambilKodeRJ);
		
		String ambilNama = (String) modelTabelRMRJ.getValueAt(i, 1);
		txtNama.setText(ambilNama);
		
		int ambilUmur = (int) modelTabelRMRJ.getValueAt(i, 2);
		txtUmur.setText(""+ambilUmur);
		
		String ambilNoReg = (String) modelTabelRMRJ.getValueAt(i, 3);
		txtNoReg.setText(ambilNoReg);
		
		String ambilNoRm = (String) modelTabelRMRJ.getValueAt(i, 4);
		txtNoRm.setText(ambilNoRm);
		
		String ambilStatusPasien = (String) modelTabelRMRJ.getValueAt(i, 5);
		cmboStatusPasien.setSelectedItem(ambilStatusPasien);
		
		String ambilPoli = (String) modelTabelRMRJ.getValueAt(i, 6);
		cmboPoli.setSelectedItem(ambilPoli);
		
		Date ambilTanggal = (Date) modelTabelRMRJ.getValueAt(i, 7);
		txtTanggal.setText(""+ambilTanggal);
		
		Time ambilJam = (Time) modelTabelRMRJ.getValueAt(i, 8);
		txtJam.setText(""+ambilJam);
		
		String ambilTerapi = (String) modelTabelRMRJ.getValueAt(i, 9);
		txtTherapi.setText(ambilTerapi);
		
		String ambilDokter = (String) modelTabelRMRJ.getValueAt(i, 10);
		txtCariDokter.setText(ambilDokter);
	}
	
	private void cariPenyakit()
	{
		String no_icd10 = "";
		String no_dtd = "";
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select no_icd10, no_dtd from macam_penyakit where macam_penyakit like '%"+cmboMacamPenyakit.getSelectedItem()+"%'");
		
			if(result.next())
			{
				no_icd10 = result.getString(1);
				no_dtd = result.getString(2);
			}
			
			txtDtd.setText(no_dtd);
			txtIcd10.setText(no_icd10);
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada cariPenyakit : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private class penghendel implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==btnSimpan)
			{
				simpanRMRawatJalan();
			}
			else if(e.getSource()==btnBatal)
			{
				bersihPanelRMRJ();
			}
			else if(e.getSource()==btnDiagnose)
			{
				simpanDiagnosa();
			}
			else if(e.getSource()==btnCariDokter)
			{
				cariDokter();
			}
			else if(e.getSource()==btnCari)
			{
				cariPasien();
			}
			else if(e.getSource()==btnUbah)
			{
				btnSimpan.setEnabled(false);
				btnEksekusiUbah.setEnabled(true);
				tabbedPane.setSelectedIndex(1);
				try
				{
					String nama_dokter = "";
					konek = konek_database.getKonekDB();
					Statement state = konek.createStatement();
					ResultSet resultPenyakit = state.executeQuery("select no_icd10 from daftar_penyakit where no_rm="+txtNoRm.getText()+"");
					while(resultPenyakit.next())
					{
						String i = resultPenyakit.getString(1);
						modelListMacamPenyakit.addElement(i);
					}
					
					ResultSet result = state.executeQuery("select nama_dokter from dokter where no_id='"+txtCariDokter.getText()+"'");
					
					if(result.next())
					{
						nama_dokter = result.getString(1);
					}
					
					lblDokter.setText(nama_dokter);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada btnUbah : "+ex.getMessage());
				}
			}
			else if(e.getSource()==btnHapus)
			{
				hapusRMRJ();
			}
			else if(e.getSource()==btnRefresh)
			{
				bersihPanelRMRJ();
			}
			else if(e.getSource()==btnEksekusiUbah)
			{
				ubahRMRJ();
			}
			else if(e.getSource()==btnRefresh2)
			{
				bersihPanelRMRJ();
			}
			else if(e.getSource()==txtNoRJ)
			{
				ambilDaftarRawatJalan();
			}
		}
		
	}
}
