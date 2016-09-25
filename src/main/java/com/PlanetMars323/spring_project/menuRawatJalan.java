package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.freixas.jcalendar.JCalendarCombo;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class menuRawatJalan extends JPanel {
	private JTextField txtKeterangan;
	private JTextField txtNoUrut;
	private JTextField txtNoCM;
	private JTextField txtNama;
	private JTextField txtUmur;
	private JComboBox cmboJK;
	private JComboBox cmboPangkat;
	private JComboBox cmboSttsPersonil;
	private JComboBox cmboSatuan;
	private JComboBox cmboBagian;
	private JTextArea txtDiagnosa;
	private JTable table;
	private Connection konek;
	private JButton btnSimpan;
	private JButton btnBatal;
	private JCalendarCombo calendarCombo;
	private JCalendarCombo cmboAwal;
	private JCalendarCombo cmboAkhir;
	private JButton btnCariBerdasarTanggal;
	private JTextField txtCariNama;
	private JButton btnCariNama;
	private DefaultTableModel modelTabelDaftarKunjungan = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tanggal", "No. RJ", "No. RM", "Nama", "Umur", "Jenis Kelamin", "Pangkat", "Status Personil", "Kesatuan", "Bagian dikunjungi", "Diagnosa", "Keterangan"
			}
		);
	private JButton btnRefresh;
	private JButton btnUbah;
	private JButton btnHapus;
	private JButton btnEksekusiDaftarUlang;
	private JTabbedPane tabbedPane;
	private JButton btnEksekusiUbah;
	private JButton btnDaftarUlang;
	private DateFormat format;
	
	/**
	 * Create the panel.
	 */
	public menuRawatJalan() {
		setLayout(new GridLayout(0,1));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1074, 704);
		add(tabbedPane);
		
		JPanel panelTabelKunjungan = new JPanel();
		tabbedPane.addTab("Kunjungan", null, panelTabelKunjungan, null);
		panelTabelKunjungan.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 1341, 343);
		panelTabelKunjungan.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(modelTabelDaftarKunjungan);
		scrollPane_1.setViewportView(table);
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				ambilDaftarPengunjung();
				btnUbah.setEnabled(true);
				btnHapus.setEnabled(true);
				btnEksekusiDaftarUlang.setEnabled(true);
			}
		});
		
		JLabel lblTabelKunjungan = new JLabel("Kunjungan Rawat Jalan");
		lblTabelKunjungan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblTabelKunjungan.setBounds(24, 11, 358, 33);
		panelTabelKunjungan.add(lblTabelKunjungan);
		
		cmboAwal = new JCalendarCombo();
		cmboAwal.setBounds(882, 74, 146, 29);
		cmboAwal.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		panelTabelKunjungan.add(cmboAwal);
		
		JLabel lblNewLabel_1 = new JLabel("Sampai");
		lblNewLabel_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(1054, 80, 59, 14);
		panelTabelKunjungan.add(lblNewLabel_1);
		
		cmboAkhir = new JCalendarCombo();
		cmboAkhir.setBounds(1129, 74, 146, 29);
		cmboAkhir.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		panelTabelKunjungan.add(cmboAkhir);
		
		JLabel lblPencarianBerdasarkanTanggal = new JLabel("Pencarian Berdasarkan Tanggal Kunjungan :");
		lblPencarianBerdasarkanTanggal.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblPencarianBerdasarkanTanggal.setBounds(872, 50, 298, 20);
		panelTabelKunjungan.add(lblPencarianBerdasarkanTanggal);
		
		btnCariBerdasarTanggal = new JButton("");
		btnCariBerdasarTanggal.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Cari.png")));
		btnCariBerdasarTanggal.setBounds(1295, 74, 44, 33);
		panelTabelKunjungan.add(btnCariBerdasarTanggal);
		
		txtCariNama = new JTextField();
		txtCariNama.setToolTipText("Pencarian berdasarkan nama");
		txtCariNama.setBounds(588, 87, 173, 20);
		panelTabelKunjungan.add(txtCariNama);
		txtCariNama.setColumns(10);
		
		JLabel lblNama_1 = new JLabel("Nama");
		lblNama_1.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblNama_1.setBounds(540, 91, 59, 14);
		panelTabelKunjungan.add(lblNama_1);
		
		btnCariNama = new JButton("");
		btnCariNama.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Cari.png")));
		btnCariNama.setBounds(777, 74, 44, 33);
		panelTabelKunjungan.add(btnCariNama);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(1240, 11, 99, 33);
		panelTabelKunjungan.add(btnRefresh);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(465, 472, 103, 43);
		panelTabelKunjungan.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Hapus.png")));
		btnHapus.setBounds(572, 472, 103, 43);
		panelTabelKunjungan.add(btnHapus);
		
		btnDaftarUlang = new JButton("Daftar Ulang");
		btnDaftarUlang.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/daftar-ulang.png")));
		btnDaftarUlang.setBounds(685, 472, 121, 43);
		panelTabelKunjungan.add(btnDaftarUlang);
		
		
		JPanel panelDaftarKunjungan = new JPanel();
		tabbedPane.addTab("Daftar Pengunjung", null, panelDaftarKunjungan, null);
		panelDaftarKunjungan.setLayout(null);
		
		JLabel lblDaftarPasien = new JLabel("Daftar Pengunjung Rawat Jalan");
		lblDaftarPasien.setBounds(24, 11, 460, 33);
		panelDaftarKunjungan.add(lblDaftarPasien);
		lblDaftarPasien.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		
		JLabel lblTanggal = new JLabel("Tanggal :");
		lblTanggal.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblTanggal.setBounds(44, 111, 88, 20);
		panelDaftarKunjungan.add(lblTanggal);
		
		JLabel lblNoUrut = new JLabel("No. RJ :");
		lblNoUrut.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoUrut.setBounds(43, 157, 88, 14);
		panelDaftarKunjungan.add(lblNoUrut);
		
		txtNoUrut = new JTextField();
		txtNoUrut.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoUrut.setBounds(239, 150, 86, 29);
		panelDaftarKunjungan.add(txtNoUrut);
		txtNoUrut.setColumns(10);
		
		JLabel lblNoCm = new JLabel("No. RM :");
		lblNoCm.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoCm.setBounds(43, 196, 88, 14);
		panelDaftarKunjungan.add(lblNoCm);
		
		txtNoCM = new JTextField();
		txtNoCM.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoCM.setBounds(239, 189, 86, 29);
		panelDaftarKunjungan.add(txtNoCM);
		txtNoCM.setColumns(10);
		
		JLabel lblNama = new JLabel("Nama :");
		lblNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNama.setBounds(43, 237, 61, 14);
		panelDaftarKunjungan.add(lblNama);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNama.setBounds(239, 230, 284, 29);
		panelDaftarKunjungan.add(txtNama);
		txtNama.setColumns(10);
		
		JLabel lblUmur = new JLabel("Umur :");
		lblUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUmur.setBounds(43, 279, 61, 14);
		panelDaftarKunjungan.add(lblUmur);
		
		txtUmur = new JTextField();
		txtUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtUmur.setBounds(239, 272, 46, 29);
		panelDaftarKunjungan.add(txtUmur);
		txtUmur.setColumns(10);
		
		JLabel lblJenisKelamin = new JLabel("Jenis Kelamin :");
		lblJenisKelamin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblJenisKelamin.setBounds(43, 324, 119, 14);
		panelDaftarKunjungan.add(lblJenisKelamin);
		
		cmboJK = new JComboBox();
		cmboJK.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboJK.setBounds(239, 317, 221, 29);
		cmboJK.addItem("-- Pilih Jenis Kelamin --");
		cmboJK.addItem("Laki-laki");
		cmboJK.addItem("Perempuan");
		panelDaftarKunjungan.add(cmboJK);
		
		JLabel lblPangkat = new JLabel("Pangkat :");
		lblPangkat.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPangkat.setBounds(582, 141, 88, 20);
		panelDaftarKunjungan.add(lblPangkat);
		
		cmboPangkat = new JComboBox();
		cmboPangkat.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboPangkat.setBounds(778, 137, 184, 29);
		panelDaftarKunjungan.add(cmboPangkat);
		cmboPangkat.setEnabled(false);
		
		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblStatus.setBounds(582, 108, 129, 14);
		panelDaftarKunjungan.add(lblStatus);
		
		
		cmboSttsPersonil = new JComboBox();
		cmboSttsPersonil.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboSttsPersonil.setBounds(776, 101, 186, 29);
		cmboSttsPersonil.addItem("-- Pilih Status --");
		cmboSttsPersonil.addItem("Personil");
		cmboSttsPersonil.addItem("PNS");
		cmboSttsPersonil.addItem("Siswa Dikbang");
		cmboSttsPersonil.addItem("Siswa Diktuk");
		cmboSttsPersonil.addItem("Tahanan");
		cmboSttsPersonil.addItem("Mandiri");
		cmboSttsPersonil.addItem("BPJS");
		cmboSttsPersonil.addItem("Purnawirawan");
		panelDaftarKunjungan.add(cmboSttsPersonil);
		cmboSttsPersonil.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				if( cmboSttsPersonil.getSelectedItem().equals("Personil") || cmboSttsPersonil.getSelectedItem().equals("PNS") )
				{
					cmboPangkat.setEnabled(true);
					cmboSatuan.setEnabled(true);
				}
				else
				{
					cmboPangkat.setSelectedIndex(0);
					cmboPangkat.setEnabled(false);
					cmboSatuan.setSelectedIndex(0);
					cmboSatuan.setEnabled(false);
				}
			}
		});
		
		
		JLabel lblSatuan = new JLabel("Satuan :");
		lblSatuan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblSatuan.setBounds(582, 184, 88, 14);
		panelDaftarKunjungan.add(lblSatuan);
		
		cmboSatuan = new JComboBox();
		cmboSatuan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboSatuan.setBounds(778, 177, 197, 29);
		cmboSatuan.addItem("-- Pilih Satuan --");
		cmboSatuan.addItem("Polda NTB");
		cmboSatuan.addItem("Luar Polda NTB");
		panelDaftarKunjungan.add(cmboSatuan);
		cmboSatuan.setEnabled(false);
		
		JLabel lblBagianYangDikunjungi = new JLabel("Bagian Yang dikunjungi :");
		lblBagianYangDikunjungi.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblBagianYangDikunjungi.setBounds(582, 332, 186, 20);
		panelDaftarKunjungan.add(lblBagianYangDikunjungi);
		
		cmboBagian = new JComboBox();
		cmboBagian.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboBagian.setBounds(778, 329, 197, 29);
		cmboBagian.addItem("-- Pilih Bagian --");
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
		
		panelDaftarKunjungan.add(cmboBagian);
		
		JLabel lblDiagnosa = new JLabel("Diagnosa :");
		lblDiagnosa.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblDiagnosa.setBounds(582, 219, 88, 20);
		panelDaftarKunjungan.add(lblDiagnosa);
		
		JLabel lblKeterangan = new JLabel("Keterangan :");
		lblKeterangan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblKeterangan.setBounds(582, 375, 103, 17);
		panelDaftarKunjungan.add(lblKeterangan);
		
		txtKeterangan = new JTextField();
		txtKeterangan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtKeterangan.setBounds(778, 372, 247, 29);
		panelDaftarKunjungan.add(txtKeterangan);
		txtKeterangan.setColumns(10);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(820, 438, 103, 43);
		panelDaftarKunjungan.add(btnSimpan);
		btnSimpan.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		
		btnBatal = new JButton("Batal");
		btnBatal.setBounds(933, 438, 103, 43);
		panelDaftarKunjungan.add(btnBatal);
		btnBatal.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/b-daftar.png")));
		lblNewLabel.setBounds(1088, 108, 256, 266);
		panelDaftarKunjungan.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(778, 217, 284, 100);
		panelDaftarKunjungan.add(scrollPane);
		
		txtDiagnosa = new JTextArea();
		scrollPane.setViewportView(txtDiagnosa);
		txtDiagnosa.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		format = new SimpleDateFormat("yyyy-MM-dd");
		calendarCombo = new JCalendarCombo();
		calendarCombo.setBounds(239, 108, 158, 29);
		calendarCombo.setDateFormat(format);
		panelDaftarKunjungan.add(calendarCombo);
		
		/**
		 * configurasi terbaru
		 */
		txtNoUrut.setEditable(false);
		
		btnEksekusiDaftarUlang = new JButton("Daftar Ulang");
		btnEksekusiDaftarUlang.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/daftar-ulang.png")));
		btnEksekusiDaftarUlang.setBounds(1158, 438, 129, 43);
		panelDaftarKunjungan.add(btnEksekusiDaftarUlang);
		
		btnEksekusiUbah = new JButton("Ubah");
		btnEksekusiUbah.setIcon(new ImageIcon(menuRawatJalan.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnEksekusiUbah.setBounds(1045, 438, 103, 43);
		panelDaftarKunjungan.add(btnEksekusiUbah);
		//panelDaftarKunjungan.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtNoUrut, txtNoCM, txtNama, txtUmur, cmboJK, cmboSttsPersonil, cmboPangkat, cmboSatuan, txtDiagnosa, cmboBagian, txtKeterangan, btnSimpan, btnBatal, lblDaftarPasien, lblTanggal, lblNoUrut, lblNoCm, lblNama, lblUmur, lblJenisKelamin, lblPangkat, lblStatus, lblSatuan, lblBagianYangDikunjungi, lblDiagnosa, lblKeterangan, lblNewLabel, scrollPane, calendarCombo, btnEksekusiDaftarUlang, btnEksekusiUbah}));
		
		daftarPangkat();
		buatNoUrutOtomatis();
		tampilTabelKunjungan();
		
		penghendel hendel = new penghendel();
		btnSimpan.addActionListener(hendel);
		btnBatal.addActionListener(hendel);
		btnCariBerdasarTanggal.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		btnEksekusiUbah.addActionListener(hendel);
		btnHapus.addActionListener(hendel);
		btnDaftarUlang.addActionListener(hendel);
		btnEksekusiDaftarUlang.addActionListener(hendel);
		txtCariNama.addActionListener(hendel);
		btnCariNama.addActionListener(hendel);
		txtNoCM.addActionListener(hendel);

	}
	
	//metode membuat nomor urut otomatis pada txtNoUrut
	private void buatNoUrutOtomatis()
	{
		int noUrut = 0;
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nomor_urut from pasien_daftar order by nomor_urut desc");
			
			if(result.next())
			{
				noUrut = result.getInt(1) + 1;
				txtNoUrut.setText(""+noUrut);
			}
			else
			{
				txtNoUrut.setText("1");
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode buatNoUrutOtomatis : "+ex.getMessage());
		}
		finally
		{
			
		}
		
	}
	
	private void cariNomorRM()
	{
		modelTabelDaftarKunjungan.getDataVector().removeAllElements();
		modelTabelDaftarKunjungan.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien_daftar where nomor_rm like '%"+txtCariNama.getText()+"%'");
			
			while(result.next())
			{
				Object obj[] = new Object[12];
				obj[0] = result.getDate(1); 
				obj[1] = result.getInt(2);
				obj[2] = result.getInt(3);
				obj[3] = result.getString(4);
				obj[4] = result.getInt(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getString(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				
				modelTabelDaftarKunjungan.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode cariNoRM : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	//daftar isi cmboPangkat
	private void daftarPangkat()
	{
		cmboPangkat.addItem("-- Pilih Pangkat --");
		cmboPangkat.addItem("Bharada");
		cmboPangkat.addItem("Bharatu");
		cmboPangkat.addItem("Bharaka");
		cmboPangkat.addItem("Abripda");
		cmboPangkat.addItem("Abriptu");
		cmboPangkat.addItem("Abrippol");
		cmboPangkat.addItem("Bridpa");
		cmboPangkat.addItem("Briptu");
		cmboPangkat.addItem("Brippol");
		cmboPangkat.addItem("Bripka");
		cmboPangkat.addItem("Aipda");
		cmboPangkat.addItem("Aiptu");
		cmboPangkat.addItem("Ipda");
		cmboPangkat.addItem("Iptu");
		cmboPangkat.addItem("AKP");
		cmboPangkat.addItem("Kompol");
		cmboPangkat.addItem("AKBP");
		cmboPangkat.addItem("Kombes Pol");
		cmboPangkat.addItem("Brigjen Pol");
		cmboPangkat.addItem("Irjen Pol");
		cmboPangkat.addItem("Komjen Pol");
		cmboPangkat.addItem("Jend Pol");
	}
	
	//membuat panel kunjungan pasien kembali seperti semula (default)
	private void bersihKunjungan()
	{
		txtNoCM.setText("");
		txtNama.setText("");
		txtUmur.setText("");
		txtDiagnosa.setText("");
		txtKeterangan.setText("");
		cmboBagian.setSelectedIndex(0);
		cmboJK.setSelectedIndex(0);
		cmboPangkat.setSelectedIndex(0);
		cmboSatuan.setSelectedIndex(0);
		cmboSttsPersonil.setSelectedIndex(0);
		btnEksekusiDaftarUlang.setEnabled(false);
		btnUbah.setEnabled(false);
		btnHapus.setEnabled(false);
		btnEksekusiUbah.setEnabled(false);
		btnSimpan.setEnabled(true);
		btnBatal.setEnabled(true);
		table.clearSelection();
		buatNoUrutOtomatis();
		calendarCombo.setDate(new Date());
		

	}
	
	//metode pencarian nama pasien
	private void cariNama()
	{
		modelTabelDaftarKunjungan.getDataVector().removeAllElements();
		modelTabelDaftarKunjungan.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien_daftar where nama like '%"+txtCariNama.getText()+"%'");
			
			while(result.next())
			{
				Object obj[] = new Object[12];
				obj[0] = result.getDate(1); 
				obj[1] = result.getInt(2);
				obj[2] = result.getInt(3);
				obj[3] = result.getString(4);
				obj[4] = result.getInt(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getString(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				
				modelTabelDaftarKunjungan.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode cariNama : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	//metode untuk mengiputkan data pasien yang berkunjung (fungsi dari btnSimpan)
	private void simpanKunjungan()
	{
		
		try
		{
						
			konek = konek_database.getKonekDB();
			//konek.setAutoCommit(false);
			
			PreparedStatement ps = konek.prepareStatement("insert into pasien_daftar(tgl,nomor_urut,nomor_rm,nama,umur,jenis_kelamin,status_personil,pangkat,kesatuan,bagian_dikunjungi,diagnosa,keterangan) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, (String) calendarCombo.getSelectedItem());
			ps.setInt(2, (int) Integer.parseInt(txtNoUrut.getText()));
			ps.setString(3, txtNoCM.getText());
			ps.setString(4, txtNama.getText());
			ps.setInt(5, (int) Integer.parseInt(txtUmur.getText()));
			ps.setString(6, (String) cmboJK.getSelectedItem());
			ps.setString(7, (String) cmboSttsPersonil.getSelectedItem());
			ps.setString(8, (String) cmboPangkat.getSelectedItem());
			ps.setString(9, (String) cmboSatuan.getSelectedItem());
			ps.setString(10, (String) cmboBagian.getSelectedItem());
			ps.setString(11, txtDiagnosa.getText());
			ps.setString(12, txtKeterangan.getText());
			ps.executeUpdate();
			/**
			PreparedStatement ps2 = konek.prepareStatement("insert into pasien_polri (nama,sttus_personil,pangkat,kesatuan) values (?,?,?,?)");
			ps2.setString(1, txtNama.getText());
			ps2.setString(2, (String) cmboSttsPersonil.getSelectedItem());
			ps2.setString(3, (String) cmboPangkat.getSelectedItem());
			ps2.setString(4, (String) cmboSatuan.getSelectedItem());
			ps2.executeUpdate();
			**/
			//konek.commit();
			JOptionPane.showMessageDialog(null, "Data berhasil tersimpan!");
			buatNoUrutOtomatis();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode simpanKunjungan : "+ex.getMessage());
		}
		finally
		{
			bersihKunjungan();
			tampilTabelKunjungan();
		}
	}
	
	private void ubahPengunjung()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("update pasien_daftar set tgl=?,nomor_urut=?,nomor_rm=?,nama=?,umur=?,jenis_kelamin=?,status_personil=?,pangkat=?,kesatuan=?,bagian_dikunjungi=?,diagnosa=?,keterangan=? where nomor_rm=?");
			ps.setString(1, (String) calendarCombo.getSelectedItem());
			ps.setInt(2, (int) Integer.parseInt(txtNoUrut.getText()));
			ps.setInt(3, (int) Integer.parseInt(txtNoCM.getText()));
			ps.setString(4, txtNama.getText());
			ps.setInt(5, (int) Integer.parseInt(txtUmur.getText()));
			ps.setString(6, (String) cmboJK.getSelectedItem());
			ps.setString(7, (String) cmboSttsPersonil.getSelectedItem());
			ps.setString(8, (String) cmboPangkat.getSelectedItem());
			ps.setString(9, (String) cmboSatuan.getSelectedItem());
			ps.setString(10, (String) cmboBagian.getSelectedItem());
			ps.setString(11, txtDiagnosa.getText());
			ps.setString(12, txtKeterangan.getText());
			ps.setInt(13, (int) Integer.parseInt(txtNoCM.getText()));
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ubahPengunjung : "+ex.getMessage());
		}
		finally
		{
			tampilTabelKunjungan();
			bersihKunjungan();
		}
	}
	
	private void simpanKunjunganUlang()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("insert into pasien_daftar_ulang (tgl,nomor_urut,nomor_rm,nama,umur,jenis_kelamin,status_personil,pangkat,kesatuan,bagian_dikunjungi,diagnosa,keterangan) values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, (String) calendarCombo.getSelectedItem());
			ps.setInt(2, (int) Integer.parseInt(txtNoUrut.getText()));
			ps.setString(3, txtNoCM.getText());
			ps.setString(4, txtNama.getText());
			ps.setInt(5, (int) Integer.parseInt(txtUmur.getText()));
			ps.setString(6, (String) cmboJK.getSelectedItem());
			ps.setString(7, (String) cmboSttsPersonil.getSelectedItem());
			ps.setString(8, (String) cmboPangkat.getSelectedItem());
			ps.setString(9, (String) cmboSatuan.getSelectedItem());
			ps.setString(10, (String) cmboBagian.getSelectedItem());
			ps.setString(11, txtDiagnosa.getText());
			ps.setString(12, txtKeterangan.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil tersimpan !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode simpanKunjunganUlang : "+ex.getMessage());
		}
		finally
		{
			tampilTabelKunjungan();
			bersihKunjungan();
			tabbedPane.setSelectedIndex(0);
		}
	}
	
	private void tampilTabelKunjungan()
	{
		modelTabelDaftarKunjungan.getDataVector().removeAllElements();
		modelTabelDaftarKunjungan.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien_daftar");
			
			while(result.next())
			{
				Object obj[] = new Object[12];
					obj[0] = result.getDate(1); 
					obj[1] = result.getInt(2);
					obj[2] = result.getString(3);
					obj[3] = result.getString(4);
					obj[4] = result.getInt(5);
					obj[5] = result.getString(6);
					obj[6] = result.getString(7);
					obj[7] = result.getString(8);
					obj[8] = result.getString(9);
					obj[9] = result.getString(10);
					obj[10] = result.getString(11);
					obj[11] = result.getString(12);
					
					modelTabelDaftarKunjungan.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode tampilTabelKunjungan : "+ex.getMessage());
		}
		finally
		{
			bersihKunjungan();
		}
	}
	
	private void cariTanggal()
	{
		modelTabelDaftarKunjungan.getDataVector().removeAllElements();
		modelTabelDaftarKunjungan.fireTableDataChanged();
		
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien_daftar where tgl between ' "+cmboAwal.getSelectedItem()+"' and '"+cmboAkhir.getSelectedItem()+"' order by nama desc");
			
			while(result.next())
			{
				Object obj[] = new Object[12];
					obj[0] = result.getDate(1); 
					obj[1] = result.getInt(2);
					obj[2] = result.getString(3);
					obj[3] = result.getString(4);
					obj[4] = result.getInt(5);
					obj[5] = result.getString(6);
					obj[6] = result.getString(7);
					obj[7] = result.getString(8);
					obj[8] = result.getString(9);
					obj[9] = result.getString(10);
					obj[10] = result.getString(11);
					obj[11] = result.getString(12);
					
					modelTabelDaftarKunjungan.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode tampilTabelKunjungan : "+ex.getMessage());
		}
		finally
		{
			bersihKunjungan();
		}
	}
	
	private void cariNoRM()
	{
		String nama="",jenis_kelamin="";
		int umur = 0;
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nama,umur,jenis_kelamin from pasien where no_rm='"+txtNoCM.getText()+"'");
			
			if(result.next())
			{
				nama = result.getString(1);
				umur = result.getInt(2);
				jenis_kelamin = result.getString(3);	
			}
			
			txtNama.setText(nama);
			txtUmur.setText(""+umur);
			cmboJK.setSelectedItem(jenis_kelamin);
			
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul cariNoRM : "+ex.getMessage());
		}
	}
	
	
	private void ambilDaftarPengunjung()
	{
		int i = table.getSelectedRow();
		
		Date ambilTanggal = (Date) modelTabelDaftarKunjungan.getValueAt(i, 0);
		calendarCombo.setSelectedItem(ambilTanggal);
		
		int ambilNoUrut = (int) modelTabelDaftarKunjungan.getValueAt(i, 1);
		txtNoUrut.setText(""+ambilNoUrut);
		
		String ambilNoCM = (String) modelTabelDaftarKunjungan.getValueAt(i, 2);
		txtNoCM.setText(ambilNoCM);
		
		String ambilNama = (String) modelTabelDaftarKunjungan.getValueAt(i, 3);
		txtNama.setText(ambilNama);
		
		int ambilUmur = (int) modelTabelDaftarKunjungan.getValueAt(i, 4);
		txtUmur.setText(""+ambilUmur);
		
		String ambilJK = (String) modelTabelDaftarKunjungan.getValueAt(i, 5);
		cmboJK.setSelectedItem(ambilJK);
		
		String ambilPangkat = (String) modelTabelDaftarKunjungan.getValueAt(i, 6);
		cmboPangkat.setSelectedItem(ambilPangkat);
		
		String ambilStatusPersonil = (String) modelTabelDaftarKunjungan.getValueAt(i, 7);
		cmboSttsPersonil.setSelectedItem(ambilStatusPersonil);
		
		String ambilKesatuan = (String) modelTabelDaftarKunjungan.getValueAt(i, 8);
		cmboSatuan.setSelectedItem(ambilKesatuan);
		
		String ambilPoli = (String) modelTabelDaftarKunjungan.getValueAt(i, 9);
		cmboBagian.setSelectedItem(ambilPoli);
		
		String ambilDiagnosa = (String) modelTabelDaftarKunjungan.getValueAt(i, 10);
		txtDiagnosa.setText(ambilDiagnosa);
		
		String ambilKeterangan = (String) modelTabelDaftarKunjungan.getValueAt(i, 11);
		txtKeterangan.setText(ambilKeterangan);
	}
	
	private void hapusPengunjung()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("delete from pasien_daftar where nomor_urut ="+txtNoUrut.getText()+"");
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode hapusPengunjung : "+ex.getMessage());
		}
		finally
		{
			tampilTabelKunjungan();
			bersihKunjungan();
		}
	}
	
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnSimpan)
			{
				simpanKunjungan();
			}
			else if(e.getSource()==btnBatal)
			{
				bersihKunjungan();
				tabbedPane.setSelectedIndex(0);
			}
			else if(e.getSource()==btnCariNama)
			{
				cariNomorRM();
			}
			else if(e.getSource()==btnRefresh)
			{
				tampilTabelKunjungan();
			}
			else if(e.getSource()==btnCariBerdasarTanggal)
			{
				cariTanggal();
			}
			else if(e.getSource()==btnUbah)
			{
				tabbedPane.setSelectedIndex(1);
				btnEksekusiDaftarUlang.setEnabled(false);
				btnSimpan.setEnabled(false);
				btnBatal.setEnabled(true);
				btnEksekusiUbah.setEnabled(true);
			}
			else if(e.getSource()==btnEksekusiUbah)
			{
				ubahPengunjung();
			}
			else if(e.getSource()==btnHapus)
			{
				hapusPengunjung();
			}
			else if(e.getSource()==btnDaftarUlang)
			{
				tabbedPane.setSelectedIndex(1);
				btnEksekusiUbah.setEnabled(false);
				btnSimpan.setEnabled(false);
				btnBatal.setEnabled(true);
			}
			else if(e.getSource()==btnEksekusiDaftarUlang)
			{
				simpanKunjunganUlang();
			}
			else if(e.getSource()==txtCariNama)
			{
				cariNama();
			}
			else if(e.getSource()==txtNoCM)
			{
				cariNoRM();
			}
		}
	}
}
