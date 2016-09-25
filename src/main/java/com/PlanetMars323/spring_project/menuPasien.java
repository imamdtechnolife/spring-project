package com.PlanetMars323.spring_project;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import org.freixas.jcalendar.JCalendarCombo;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;

public class menuPasien extends JPanel {
	private JTextField txtNoRM;
	private JTextField txtNoReg;
	private JTextField txtNama;
	private JTextField txtTempatLahir;
	private JTextField txtAgama;
	private JTextField txtSuku;
	private JTextField txtPendidikan;
	private JTextField txtPekerjaan;
	private JTextField txtKelurahan;
	private JTextField txtKecamatan;
	private JTextField txtKabupaten;
	private JTextField txtTelepon;
	private JTextField txtNoKtpsim;
	private JTextField txtAnakke;
	private JTextField txtUmur;
	private JTextField txtNoBpjs;
	private JTextField txtNoRujukan;
	Connection konek = null;
	private JFormattedTextField txtTanggal;
	private JTextArea txtAlamat;
	private JComboBox cmboStatusNikah;
	private JComboBox cmboGolDarah;
	private JButton btnSimpan;
	private JButton btnBatal;
	private JCalendarCombo tanggal; 
	private JCalendarCombo cmboTanggal;
	private JTextField txtCariPasien;
	DefaultTableModel tabelIdentitasPasien = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. RM", "No. Reg", "Nama", "Tempat Lahir", "Tgl. Lahir", "Jns Kelamin", "Status Nikah", "Agama", "Suku", "Pendidikan", "Pekerjaan", "Alamat", "Kelurahan", "Kecamatan", "Kabupaten", "No. Telp", "No. KTP/SIM", "Anak-Ke", "Umur", "Gol. Darah", "No. BPJS", "No. Rujukan"
			}
		);
	private JTable table;
	private JButton btnRefresh;
	private JButton btnUbah;
	private JButton btnHapus;
	private JTabbedPane tabbedPane;
	private JButton bntEksekusiUbah;
	private JComboBox cmboJenisKelamin;
	private JButton btnPasienRjBaru;
	private JButton btnPasienRiBaru;
	private JLabel lblNoRJ;
	private JLabel lblNoRI;

	/**
	 * Create the panel.
	 */
	public menuPasien() {
		setLayout(new GridLayout(0,1));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1135, 638);
		add(tabbedPane);
		JPanel panelTabelPasien = new JPanel();
		tabbedPane.addTab("Tabel Identitas Utama Pasien", null, panelTabelPasien, null);
		panelTabelPasien.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 115, 1340, 353);
		panelTabelPasien.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(tabelIdentitasPasien);
		table.addMouseListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						ambilDataPasien();
						btnUbah.setEnabled(true);
						btnHapus.setEnabled(true);
					}
				});
		scrollPane_1.setViewportView(table);
		
		
		JLabel lblNewLabel = new JLabel("Tabel Identitas Utama Pasien");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel.setBounds(10, 11, 423, 33);
		panelTabelPasien.add(lblNewLabel);
		
		txtCariPasien = new JTextField();
		txtCariPasien.setToolTipText("Masukkan Nomor RM Pasien");
		txtCariPasien.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtCariPasien.setBounds(126, 75, 190, 29);
		panelTabelPasien.add(txtCariPasien);
		txtCariPasien.setColumns(10);
		
		JLabel lblCariPasien = new JLabel("Cari Pasien :");
		lblCariPasien.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		lblCariPasien.setBounds(25, 74, 93, 29);
		panelTabelPasien.add(lblCariPasien);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(1251, 23, 99, 33);
		panelTabelPasien.add(btnRefresh);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(484, 479, 99, 33);
		btnUbah.setEnabled(false);
		panelTabelPasien.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Hapus.png")));
		btnHapus.setBounds(591, 479, 99, 33);
		btnHapus.setEnabled(false);
		panelTabelPasien.add(btnHapus);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Daftar Identitas Utama Pasien", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblDaftarRekamMedis = new JLabel("Daftar Identitas Utama Pasien");
		lblDaftarRekamMedis.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		lblDaftarRekamMedis.setBounds(25, 11, 493, 43);
		panel.add(lblDaftarRekamMedis);
		
		JLabel lblNoRm = new JLabel("No. RM");
		lblNoRm.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoRm.setBounds(48, 84, 70, 32);
		panel.add(lblNoRm);
		
		JLabel lblNoReg = new JLabel("No. Reg");
		lblNoReg.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoReg.setBounds(48, 127, 85, 23);
		panel.add(lblNoReg);
		
		JLabel lblNama = new JLabel("Nama");
		lblNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNama.setBounds(48, 164, 46, 23);
		panel.add(lblNama);
		
		JLabel lblTempatLahir = new JLabel("Tempat Lahir");
		lblTempatLahir.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblTempatLahir.setBounds(48, 198, 126, 28);
		panel.add(lblTempatLahir);
		
		JLabel lblTanggalLahir = new JLabel("Tanggal Lahir");
		lblTanggalLahir.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblTanggalLahir.setBounds(48, 237, 112, 29);
		panel.add(lblTanggalLahir);
		
		JLabel lblStatusNikah = new JLabel("Status Nikah");
		lblStatusNikah.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblStatusNikah.setBounds(48, 277, 126, 24);
		panel.add(lblStatusNikah);
		
		JLabel lblAgama = new JLabel("Agama");
		lblAgama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblAgama.setBounds(48, 347, 126, 25);
		panel.add(lblAgama);
		
		JLabel lblSuku = new JLabel("Suku");
		lblSuku.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblSuku.setBounds(48, 383, 126, 23);
		panel.add(lblSuku);
		
		JLabel lblPendidikan = new JLabel("Pendidikan");
		lblPendidikan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPendidikan.setBounds(375, 84, 85, 32);
		panel.add(lblPendidikan);
		
		JLabel lblPekerjaan = new JLabel("Pekerjaan");
		lblPekerjaan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPekerjaan.setBounds(375, 127, 126, 26);
		panel.add(lblPekerjaan);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblAlamat.setBounds(375, 164, 85, 23);
		panel.add(lblAlamat);
		
		JLabel lblKelurahan = new JLabel("Kelurahan");
		lblKelurahan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblKelurahan.setBounds(385, 268, 102, 28);
		panel.add(lblKelurahan);
		
		JLabel lblKecamatan = new JLabel("Kecamatan");
		lblKecamatan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblKecamatan.setBounds(385, 307, 102, 29);
		panel.add(lblKecamatan);
		
		JLabel lblKabupaten = new JLabel("Kabupaten");
		lblKabupaten.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblKabupaten.setBounds(385, 347, 85, 24);
		panel.add(lblKabupaten);
		
		JLabel lblNoTelp = new JLabel("Telepon");
		lblNoTelp.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoTelp.setBounds(383, 390, 70, 32);
		panel.add(lblNoTelp);
		
		JLabel lblNoKtp = new JLabel("No. KTP / SIM");
		lblNoKtp.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoKtp.setBounds(758, 95, 112, 33);
		panel.add(lblNoKtp);
		
		JLabel lblAnakKe = new JLabel("Anak Ke");
		lblAnakKe.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblAnakKe.setBounds(758, 139, 85, 23);
		panel.add(lblAnakKe);
		
		JLabel lblUmur = new JLabel("Umur");
		lblUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblUmur.setBounds(758, 173, 46, 28);
		panel.add(lblUmur);
		
		JLabel lblGolDarah = new JLabel("Gol. Darah");
		lblGolDarah.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblGolDarah.setBounds(758, 212, 94, 29);
		panel.add(lblGolDarah);
		
		JLabel lblNoBpjs = new JLabel("No. BPJS");
		lblNoBpjs.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoBpjs.setBounds(758, 253, 94, 24);
		panel.add(lblNoBpjs);
		
		JLabel lblNoRujukan = new JLabel("No. Rujukan");
		lblNoRujukan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblNoRujukan.setBounds(758, 288, 94, 25);
		panel.add(lblNoRujukan);
		
		txtNoRM = new JTextField();
		txtNoRM.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoRM.setBounds(169, 87, 86, 29);
		panel.add(txtNoRM);
		txtNoRM.setColumns(10);
		
		txtNoReg = new JTextField();
		txtNoReg.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoReg.setColumns(10);
		txtNoReg.setBounds(169, 125, 138, 29);
		panel.add(txtNoReg);
		
		txtNama = new JTextField();
		txtNama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNama.setColumns(10);
		txtNama.setBounds(169, 162, 176, 29);
		panel.add(txtNama);
		
		txtTempatLahir = new JTextField();
		txtTempatLahir.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtTempatLahir.setColumns(10);
		txtTempatLahir.setBounds(169, 199, 148, 29);
		panel.add(txtTempatLahir);
		
		txtAgama = new JTextField();
		txtAgama.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtAgama.setColumns(10);
		txtAgama.setBounds(169, 350, 101, 29);
		panel.add(txtAgama);
		
		txtSuku = new JTextField();
		txtSuku.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtSuku.setColumns(10);
		txtSuku.setBounds(169, 385, 126, 29);
		panel.add(txtSuku);
		
		txtPendidikan = new JTextField();
		txtPendidikan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtPendidikan.setColumns(10);
		txtPendidikan.setBounds(478, 91, 86, 29);
		panel.add(txtPendidikan);
		
		txtPekerjaan = new JTextField();
		txtPekerjaan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtPekerjaan.setColumns(10);
		txtPekerjaan.setBounds(478, 129, 216, 29);
		panel.add(txtPekerjaan);
		
		txtKelurahan = new JTextField();
		txtKelurahan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtKelurahan.setColumns(10);
		txtKelurahan.setBounds(478, 273, 216, 29);
		panel.add(txtKelurahan);
		
		txtKecamatan = new JTextField();
		txtKecamatan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtKecamatan.setColumns(10);
		txtKecamatan.setBounds(478, 312, 216, 29);
		panel.add(txtKecamatan);
		
		txtKabupaten = new JTextField();
		txtKabupaten.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtKabupaten.setColumns(10);
		txtKabupaten.setBounds(478, 350, 216, 29);
		panel.add(txtKabupaten);
		
		txtTelepon = new JTextField();
		txtTelepon.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtTelepon.setColumns(10);
		txtTelepon.setBounds(478, 392, 138, 29);
		panel.add(txtTelepon);
		
		txtNoKtpsim = new JTextField();
		txtNoKtpsim.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoKtpsim.setColumns(10);
		txtNoKtpsim.setBounds(872, 104, 197, 29);
		panel.add(txtNoKtpsim);
		
		txtAnakke = new JTextField();
		txtAnakke.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtAnakke.setColumns(10);
		txtAnakke.setBounds(872, 137, 46, 29);
		panel.add(txtAnakke);
		
		txtUmur = new JTextField();
		txtUmur.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtUmur.setColumns(10);
		txtUmur.setBounds(872, 174, 46, 29);
		panel.add(txtUmur);
		
		txtNoBpjs = new JTextField();
		txtNoBpjs.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoBpjs.setColumns(10);
		txtNoBpjs.setBounds(872, 252, 216, 29);
		panel.add(txtNoBpjs);
		
		txtNoRujukan = new JTextField();
		txtNoRujukan.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtNoRujukan.setColumns(10);
		txtNoRujukan.setBounds(872, 287, 207, 29);
		panel.add(txtNoRujukan);
		
		cmboStatusNikah = new JComboBox();
		cmboStatusNikah.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboStatusNikah.setBounds(170, 280, 176, 29);
		cmboStatusNikah.addItem("-- Pilih Status --");
		cmboStatusNikah.addItem("Nikah");
		cmboStatusNikah.addItem("Belum Nikah");
		panel.add(cmboStatusNikah);
		
		/**
		tanggal = new JCalendarCombo();
		tanggal.setDateFormat(new SimpleDateFormat("dd/MM/yy"));
		DateFormat format = new SimpleDateFormat("dd/MM/yy");
		DateFormatter memformat = new DateFormatter(format);
		txtTanggal = new JFormattedTextField(memformat);
		txtTanggal.setValue(new Date());
		txtTanggal.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		txtTanggal.setBounds(168, 237, 102, 29);
		panel.add(tanggal);
		**/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(478, 163, 190, 94);
		panel.add(scrollPane);
		
		txtAlamat = new JTextArea();
		txtAlamat.setFont(new Font("Monospaced", Font.PLAIN, 14));
		scrollPane.setViewportView(txtAlamat);
		
		cmboGolDarah = new JComboBox();
		cmboGolDarah.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		cmboGolDarah.setBounds(872, 213, 197, 29);
		cmboGolDarah.addItem("-- Pilih Gol. Darah --");
		cmboGolDarah.addItem("A");
		cmboGolDarah.addItem("B");
		cmboGolDarah.addItem("O");
		cmboGolDarah.addItem("AB");
		panel.add(cmboGolDarah);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		btnSimpan.setBounds(806, 458, 99, 43);
		panel.add(btnSimpan);
		
		btnBatal = new JButton("Batal");
		btnBatal.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		btnBatal.setBounds(931, 458, 102, 43);
		panel.add(btnBatal);
		
		cmboTanggal = new JCalendarCombo();
		cmboTanggal.setBounds(168, 237, 139, 29);
		cmboTanggal.setDateFormat(new SimpleDateFormat("yy-MM-dd"));
		panel.add(cmboTanggal);
		
		bntEksekusiUbah = new JButton("Ubah");
		bntEksekusiUbah.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		bntEksekusiUbah.setBounds(1053, 458, 99, 43);
		bntEksekusiUbah.setEnabled(false);
		panel.add(bntEksekusiUbah);
		
		JLabel lblJenisKelamin = new JLabel("Jenis Kelamin");
		lblJenisKelamin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblJenisKelamin.setBounds(48, 312, 126, 25);
		panel.add(lblJenisKelamin);
		
		cmboJenisKelamin = new JComboBox();
		cmboJenisKelamin.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		cmboJenisKelamin.setBounds(169, 312, 176, 29);
		cmboJenisKelamin.addItem("-- Pilih  Jenis Kelamin --");
		cmboJenisKelamin.addItem("Laki-laki");
		cmboJenisKelamin.addItem("Perempuan");
		panel.add(cmboJenisKelamin);
		
		btnPasienRjBaru = new JButton("Pasien RJ Baru");
		btnPasienRjBaru.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/buku-daftar.png")));
		btnPasienRjBaru.setBounds(991, 28, 155, 57);
		panel.add(btnPasienRjBaru);
		
		btnPasienRiBaru = new JButton("Pasien RI Baru");
		btnPasienRiBaru.setIcon(new ImageIcon(menuPasien.class.getResource("/com/PlanetMars323/spring_project/Image/rawat-inap.png")));
		btnPasienRiBaru.setBounds(1163, 28, 155, 57);
		panel.add(btnPasienRiBaru);
		
		lblNoRJ = new JLabel("New label");
		lblNoRJ.setBounds(1042, 11, 46, 14);
		lblNoRJ.setVisible(false);
		panel.add(lblNoRJ);
		
		lblNoRI = new JLabel("New label");
		lblNoRI.setBounds(1229, 11, 46, 14);
		lblNoRI.setVisible(false);
		panel.add(lblNoRI);
		
		penghendel hendel = new penghendel();
		btnSimpan.addActionListener(hendel);
		btnBatal.addActionListener(hendel);
		txtCariPasien.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		btnHapus.addActionListener(hendel);
		bntEksekusiUbah.addActionListener(hendel);
		btnPasienRjBaru.addActionListener(hendel);
		btnPasienRiBaru.addActionListener(hendel);
		
		tampilPasien();
	}
	
	public void simpanPasien()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("insert into pasien(no_rm,no_reg,nama,tempat_lahir,tanggal_lahir,jenis_kelamin,sttus_nikah,agama,suku,pendidikan,pekerjaan,alamat,kelurahan,kecamatan,kabupaten,no_telp,no_ktp_sim,anak_ke,umur,gol_darah,no_bpjs,no_rujukan) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, txtNoRM.getText());
			ps.setString(2, txtNoReg.getText());
			ps.setString(3, txtNama.getText());
			ps.setString(4, txtTempatLahir.getText());
			ps.setString(5, (String) cmboTanggal.getSelectedItem());
			ps.setString(6, (String) cmboJenisKelamin.getSelectedItem());
			ps.setString(7, (String) cmboStatusNikah.getSelectedItem());
			ps.setString(8, txtAgama.getText());
			ps.setString(9, txtSuku.getText());
			ps.setString(10, txtPendidikan.getText());
			ps.setString(11, txtPekerjaan.getText());
			ps.setString(12, txtAlamat.getText());
			ps.setString(13, txtKelurahan.getText());
			ps.setString(14, txtKecamatan.getText());
			ps.setString(15, txtKabupaten.getText());
			ps.setString(16, txtTelepon.getText());
			ps.setString(17, txtNoKtpsim.getText());
			ps.setString(18, txtAnakke.getText());
			ps.setInt(19, (int) Integer.parseInt(txtUmur.getText()));
			ps.setString(20, (String) cmboGolDarah.getSelectedItem());
			ps.setString(21, txtNoBpjs.getText());
			ps.setString(22, txtNoReg.getText());
			ps.executeUpdate();
			
			if(lblNoRI.getText().equals(""))
			{
				PreparedStatement ps2 = konek.prepareStatement("update pasien_daftar set nomor_rm='"+txtNoRM.getText()+"' where nomor_urut="+lblNoRJ.getText()+"");
				ps2.executeUpdate();
			}
			else
			{
				
			}
			
			
			JOptionPane.showMessageDialog(null, "Data berhasil tersimpan!");
			ps.close();
			konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol simpan : "+ex.getMessage());
		}
		finally
		{
			bersihPasien();
			tampilPasien();
		}
	}
	
	private void tampilPasien()
	{
		tabelIdentitasPasien.getDataVector().removeAllElements();
		tabelIdentitasPasien.fireTableDataChanged();
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien");
			
			while(result.next())
			{
				Object obj[] = new Object[22];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				obj[4] = result.getDate(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getString(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				obj[12] = result.getString(13);
				obj[13] = result.getString(14);
				obj[14] = result.getString(15);
				obj[15] = result.getString(16);
				obj[16] = result.getString(17);
				obj[17] = result.getString(18);
				obj[18] = result.getInt(19);
				obj[19] = result.getString(20);
				obj[20] = result.getString(21);
				obj[21] = result.getString(22);
				
				tabelIdentitasPasien.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode tampil tabel : "+ex.getMessage());
		}
	}
	
	private void cariPasien()
	{
		tabelIdentitasPasien.getDataVector().removeAllElements();
		tabelIdentitasPasien.fireTableDataChanged();
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select * from pasien where no_rm like '%"+txtCariPasien.getText()+"%' ");
			
			while(result.next())
			{
				Object obj[] = new Object[22];
				obj[0] = result.getString(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getString(4);
				obj[4] = result.getDate(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getString(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				obj[12] = result.getString(13);
				obj[13] = result.getString(14);
				obj[14] = result.getString(15);
				obj[15] = result.getString(16);
				obj[16] = result.getString(17);
				obj[17] = result.getString(18);
				obj[18] = result.getInt(19);
				obj[19] = result.getString(20);
				obj[20] = result.getString(21);
				obj[21] = result.getString(22);
				
				tabelIdentitasPasien.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode cari nama : "+ex.getMessage());
		}
	}
	

	private void bersihPasien()
	{
		txtCariPasien.setText("");
		txtCariPasien.requestFocus();
		txtNoRM.setText("");
		txtNoReg.setText("");
		txtNama.setText("");
		txtTempatLahir.setText("");
		txtAgama.setText("");
		txtSuku.setText("");
		txtPendidikan.setText("");
		txtPekerjaan.setText("");
		txtKelurahan.setText("");
		txtKecamatan.setText("");
		txtKabupaten.setText("");
		txtTelepon.setText("");
		txtNoKtpsim.setText("");
		txtAnakke.setText("");
		txtUmur.setText("");
		txtNoBpjs.setText("");
		txtNoRujukan.setText("");
		txtAlamat.setText("");
		cmboJenisKelamin.setSelectedIndex(0);
		cmboStatusNikah.setSelectedIndex(0);
		cmboGolDarah.setSelectedIndex(0);
		table.clearSelection();
		tampilPasien();
		btnSimpan.setEnabled(true);
		bntEksekusiUbah.setEnabled(false);
		btnHapus.setEnabled(false);
		btnUbah.setEnabled(false);
		cmboTanggal.setDate(new Date());
		lblNoRJ.setText("");
		lblNoRI.setText("");
	
	}
	
	private void ambilDataPasien()
	{
		int i = table.getSelectedRow();
		
		String ambilNoRm = (String) tabelIdentitasPasien.getValueAt(i, 0);
		txtNoRM.setText(ambilNoRm);
		
		String ambilNoReg = (String) tabelIdentitasPasien.getValueAt(i, 1);
		txtNoReg.setText(ambilNoReg);
		
		String ambilNama = (String) tabelIdentitasPasien.getValueAt(i, 2);
		txtNama.setText(ambilNama);
		
		String ambilTempatLahir = (String) tabelIdentitasPasien.getValueAt(i, 3);
		txtTempatLahir.setText(ambilTempatLahir);
		
		Date ambilTanggalLahir = (Date) tabelIdentitasPasien.getValueAt(i, 4);
		cmboTanggal.setDate(ambilTanggalLahir);
		
		String ambilJenisKelamin = (String) tabelIdentitasPasien.getValueAt(i, 5);
		cmboJenisKelamin.setSelectedItem(ambilJenisKelamin);
		
		String ambilStatusNikah = (String) tabelIdentitasPasien.getValueAt(i, 6);
		cmboStatusNikah.setSelectedItem(ambilStatusNikah);
		
		String ambilAgama = (String) tabelIdentitasPasien.getValueAt(i, 7);
		txtAgama.setText(ambilAgama);
		
		String ambilSuku = (String) tabelIdentitasPasien.getValueAt(i, 8);
		txtSuku.setText(ambilSuku);
		
		String ambilPendidikan = (String) tabelIdentitasPasien.getValueAt(i, 9);
		txtPendidikan.setText(ambilPendidikan);
		
		String ambilPekerjaan = (String) tabelIdentitasPasien.getValueAt(i, 10);
		txtPekerjaan.setText(ambilPekerjaan);
		
		String ambilAlamat = (String) tabelIdentitasPasien.getValueAt(i, 11);
		txtAlamat.setText(ambilAlamat);
		
		String ambilKelurahan = (String) tabelIdentitasPasien.getValueAt(i, 12);
		txtKelurahan.setText(ambilKelurahan);
		
		String ambilKecamatan = (String) tabelIdentitasPasien.getValueAt(i, 13);
		txtKecamatan.setText(ambilKecamatan);
		
		String ambilKabupaten = (String) tabelIdentitasPasien.getValueAt(i, 14);
		txtKabupaten.setText(ambilKabupaten);
		
		String ambilTelp = (String) tabelIdentitasPasien.getValueAt(i, 15);
		txtTelepon.setText(ambilTelp);
		
		String ambilKtpsim = (String) tabelIdentitasPasien.getValueAt(i, 16);
		txtNoKtpsim.setText(ambilKtpsim);
		
		String ambilAnakke = (String) tabelIdentitasPasien.getValueAt(i, 17);
		txtAnakke.setText(ambilAnakke);
		
		int ambilUmur = (int) tabelIdentitasPasien.getValueAt(i, 18);
		txtUmur.setText(""+ambilUmur);
		
		String ambilGolDarah = (String) tabelIdentitasPasien.getValueAt(i, 19);
		cmboGolDarah.setSelectedItem(ambilGolDarah);
		
		String ambilNobpjs = (String) tabelIdentitasPasien.getValueAt(i, 20);
		txtNoBpjs.setText(ambilNobpjs);
		
		String ambilNorujukan = (String) tabelIdentitasPasien.getValueAt(i, 21);
		txtNoRujukan.setText(ambilNorujukan);
		
	}
	
	private void ubahDataPasien()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("update pasien set no_rm=?,no_reg=?,nama=?,tempat_lahir=?,tanggal_lahir=?,jenis_kelamin=?,sttus_nikah=?,agama=?,suku=?,pendidikan=?,pekerjaan=?,alamat=?,kelurahan=?,kecamatan=?,kabupaten=?,no_telp=?,no_ktp_sim=?,anak_ke=?,umur=?,gol_darah=?,no_bpjs=?,no_rujukan=? where no_rm=?");
			ps.setString(1, txtNoRM.getText());
			ps.setString(2, txtNoReg.getText());
			ps.setString(3, txtNama.getText());
			ps.setString(4, txtTempatLahir.getText());
			ps.setString(5, (String) cmboTanggal.getSelectedItem());
			ps.setString(6, (String) cmboJenisKelamin.getSelectedItem());
			ps.setString(7, (String) cmboStatusNikah.getSelectedItem());
			ps.setString(8, txtAgama.getText());
			ps.setString(9, txtSuku.getText());
			ps.setString(10, txtPendidikan.getText());
			ps.setString(11, txtPekerjaan.getText());
			ps.setString(12, txtAlamat.getText());
			ps.setString(13, txtKelurahan.getText());
			ps.setString(14, txtKecamatan.getText());
			ps.setString(15, txtKabupaten.getText());
			ps.setString(16, txtTelepon.getText());
			ps.setString(17, txtNoKtpsim.getText());
			ps.setString(18, txtAnakke.getText());
			ps.setInt(19, (int) Integer.parseInt(txtUmur.getText()));
			ps.setString(20, (String) cmboGolDarah.getSelectedItem());
			ps.setString(21, txtNoBpjs.getText());
			ps.setString(22, txtNoReg.getText());
			ps.setString(23, txtNoRM.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
			ps.close();
			konek.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul ubahDataPasien : "+ex.getMessage());
		}
		finally
		{
			bersihPasien();
			tampilPasien();
			tabbedPane.setSelectedIndex(0);
		}
	}
	
	private void hapusDataPasien()
	{
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("delete from pasien where no_rm='"+txtNoRM.getText()+"'");
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul hapusDataPasien : "+ex.getMessage());
		}
		finally
		{
			bersihPasien();
			tampilPasien();
		}
	}
	
	private void ambilDaftarPasienRJ()
	{
		String nama="",jenis_kelamin="";
		int umur=0,no_rj=0;
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select nomor_urut,nama,umur,jenis_kelamin from pasien_daftar order by nomor_urut desc");
			
			if(result.next())
			{
				no_rj = result.getInt(1);
				nama = result.getString(2);
				umur = result.getInt(3);
				jenis_kelamin = result.getString(4);
				
			}
			
			lblNoRJ.setText(""+no_rj);
			txtNama.setText(nama);
			txtUmur.setText(""+umur);
			cmboJenisKelamin.setSelectedItem(jenis_kelamin);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul ambilDaftarPasienRJ() : "+ex.getMessage());
		}
	}
	
	private void ambilDaftarPasienRI()
	{
		String nama="",status_nikah="",alamat="";
		int umur=0, no_ri=0;
		try
		{
			konek = konek_database.getKonekDB();
			Statement state = konek.createStatement();
			ResultSet result = state.executeQuery("select kd_rawat_inap,nama,umur,alamat,sttus_nikah from pasien_rawat_inap order by kd_rawat_inap desc");
			
			if(result.next())
			{
				no_ri = result.getInt(1);
				nama = result.getString(2);
				umur = result.getInt(3);
				alamat = result.getString(4);
				status_nikah = result.getString(5);
			}
			
			lblNoRI.setText(""+no_ri);
			txtNama.setText(nama);
			txtUmur.setText(""+umur);
			txtAlamat.setText(alamat);
			cmboStatusNikah.setSelectedItem(status_nikah);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul ambilDaftarPasienRI() : "+ex.getMessage());
		}
	}
	
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnSimpan)
			{
				if(txtNoRM.getText().equals("") || txtAlamat.getText().equals("") || txtNoReg.getText().equals("") || txtNama.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Anda belum mengisi kolom yang utama ");
					JOptionPane.showMessageDialog(null, "silahkan diisi :)");
				}
				else
				{
					simpanPasien();
					tampilPasien();
					tabbedPane.setSelectedIndex(0);
				}
				
			}
			else if(e.getSource()==btnBatal)
			{
				bersihPasien();
			}
			else if(e.getSource()==txtCariPasien)
			{
				cariPasien();
			}
			else if(e.getSource()==btnRefresh)
			{
				bersihPasien();
			}
			else if(e.getSource()==btnHapus)
			{
				hapusDataPasien();
			}
			else if(e.getSource()==btnUbah)
			{
				tabbedPane.setSelectedIndex(1);
				btnSimpan.setEnabled(false);
				bntEksekusiUbah.setEnabled(true);
			}
			else if(e.getSource()==bntEksekusiUbah)
			{
				ubahDataPasien();
			}
			else if(e.getSource()==btnPasienRjBaru)
			{
				ambilDaftarPasienRJ();
			}
			else if(e.getSource()==btnPasienRiBaru)
			{
				ambilDaftarPasienRI();
			}
		}
	}
}
