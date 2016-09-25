package com.PlanetMars323.spring_project;

/**
 * Project	: Tugas Akhir
 * Title	: Sistem Informasi Pelayanan Kesehatan Berbasis Java dan MySQL Pada Rumah Sakit Bhayangkara Mataram
 * Author	: Imam Afriyadi
 */

/**
 * Menu : Rawat Inap
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;
import org.freixas.jcalendar.*;
import java.util.Date;

public class menuRawatInap extends JPanel{

	/**
	 * inisialisasi
	 * 
	 */
	Font tnr = new Font("Bookman Old Style", Font.ROMAN_BASELINE , 14);
	JLabel lbljudul = new JLabel("PASIEN RAWAT INAP");
	JLabel lbljudul2 = new JLabel("RUMAH SAKIT BHAYANGKARA MATARAM");
	JLabel lblno_reg = new JLabel("No. RM :");
	JLabel lblnama_penderita = new JLabel("Nama Penderita :");
	JLabel lblumur = new JLabel("Umur :");
	JLabel lblalamat = new JLabel("Alamat :");
	JLabel lblstatus = new JLabel("Status Pernikahan :");
	JLabel lblpekerjaan = new JLabel("Status Pasien :");
	JLabel lbltanggal_masuk = new JLabel("Tanggal Masuk :");
	JLabel lbltanggal_keluar = new JLabel("Tanggal Keluar :");
	JLabel lbllama = new JLabel("Lama :");
	JLabel lblruang_perawatan = new JLabel("Ruang Perawatan :");
	JLabel lbldiagnosa = new JLabel("Diagnosa :");
	JLabel lblKeterangan = new JLabel("Keterangan :");
	Icon iconTambah = new ImageIcon(getClass().getResource("Image/Tambah.png"));
	JButton btnTambah = new JButton("Tambah", iconTambah);
	Icon iconSimpan = new ImageIcon(getClass().getResource("Image/Simpan.png"));
	JButton btnSimpan = new JButton("Simpan", iconSimpan);
	Icon iconBatal = new ImageIcon(getClass().getResource("Image/Batal.png"));
	JButton btnBatal = new JButton("Batal", iconBatal);
	Icon iconUbah = new ImageIcon(getClass().getResource("Image/Ubah.png"));
	JButton btnUbah = new JButton("Ubah", iconUbah);
	Icon iconHapus = new ImageIcon(getClass().getResource("Image/Hapus.png"));
	JButton btnHapus = new JButton("Hapus", iconHapus);
	Icon iconRefresh = new ImageIcon(getClass().getResource("Image/Refresh.png"));
	JButton btnRefresh1 = new JButton("Refresh", iconRefresh);
	Icon iconRefresh2 = new ImageIcon(getClass().getResource("Image/Refresh.png"));
	JButton btnRefresh2 = new JButton("Refresh", iconRefresh2);
	Icon iconReport = new ImageIcon(getClass().getResource("Image/Laporan.png"));
	JButton btnReport = new JButton("Cetak", iconReport);
	Icon iconTotal = new ImageIcon(getClass().getResource("Image/Total.png"));
	JButton btnTotal = new JButton("Simpan HP", iconTotal);
	JTextField txtNoRm = new JTextField(5);
	JTextField txtNamaPenderita = new JTextField();
	JTextArea areaAlamat = new JTextArea(1,10);
	JScrollPane scrollAlamat = new JScrollPane(areaAlamat);
	JComboBox comboStatus = new JComboBox();
	JCalendarCombo tanggalMasuk = new JCalendarCombo();
	JCalendarCombo tanggalKeluar = new JCalendarCombo();
	JLabel lblResultLama = new JLabel();
	JComboBox cmboPoli = new JComboBox();
	JTextArea areaDiagnosa = new JTextArea(1,10);
	JScrollPane scrollDiagnosa = new JScrollPane(areaDiagnosa);
	JTextArea areaKeterangan = new JTextArea(1,10);
	JScrollPane scrollKeterangan = new JScrollPane(areaKeterangan);
	JPanel masterRawatInap = new JPanel();
	JPanel masterTombol = new JPanel();
	JScrollPane scrollRawatInap = new JScrollPane();
	JTabbedPane tabRawatInap = new JTabbedPane();
	//Container konten = getContentPane();
	JTable tabelPasienRawatInap = new JTable();
	String fieldPasienRawatInap[] = {"No. RI","No. RM","Nama Penderita","Umur (Tahun)","Alamat","Status Nikah","Status Pasien","Tanggal Masuk","Ruang Perawatan","Poli","Dokter","Diagnosa","Keterangan"}; 
	DefaultTableModel modelTabelPasienRawatInap = new DefaultTableModel(null, fieldPasienRawatInap);
	JScrollPane scrollTable = new JScrollPane();
	JPanel masterTabel = new JPanel();
	JPanel masterTombolEkstra = new JPanel();
	JPanel masterResult = new JPanel();
	private Connection connect = null;
	private Statement state = null;
	private ResultSet result = null;
	PreparedStatement ps = null;
	JComboBox cmboPenyakit = new JComboBox();
	JLabel lblIcd10 = new JLabel("No. ICD-10 :");
	JButton btnSimpanUbah = new JButton("Simpan", iconSimpan);
	private JComboBox cmboStatusPasien = new JComboBox();
	private JTextField txtUmur = new JTextField();
	private JTextField txtAlamat = new JTextField();
	private JLabel lblRuangInap = new JLabel("Ruangan : ");
	private JComboBox cmboRuangInap = new JComboBox();
	private JLabel lblDokter = new JLabel("Dokter : ");
	private JComboBox cmboDokter = new JComboBox();
	private JLabel lblPasAnggota = new JLabel("Pasien Anggota :");
	private JLabel lblPasPns = new JLabel("Pasien PNS :");
	private JLabel lblPasSiswaDikbang = new JLabel("Pasien Siswa DIKBANG :");
	private JLabel lblPasSiswaDiktuk = new JLabel("Pasien Siswa DIKTUK :");
	private JLabel lblPasTahanan = new JLabel("Pasien Tahanan :");
	private JLabel lblPasMandiri = new JLabel("Pasien Mandiri :");
	private JLabel lblPasBpjs = new JLabel("Pasien BPJS : ");
	private JLabel lblPasPurna = new JLabel("Pasien Purnawirawan : ");
	private JTextField txtPasAnggota = new JTextField(3);
	private JTextField txtPasPns = new JTextField(3);
	private JTextField txtPasSiswaDikbang = new JTextField(3);
	private JTextField txtPasSiswaDiktuk = new JTextField(3);
	private JTextField txtPasTahanan = new JTextField(3);
	private JTextField txtPasMandiri = new JTextField(3);
	private JTextField txtPasBpjs = new JTextField(3);
	private JTextField txtPasPurna = new JTextField(3);
	private JPanel PanelHP = new JPanel();
	private JComboBox cmboPoliklinik = new JComboBox();
	private JPanel panelPasienKeluar = new JPanel();
	private JTable tabelPasienKeluar = new JTable();
	private JScrollPane scrollTabelPasienKeluar = new JScrollPane();
	String kolomTabelPasienKeluar[] = {"No. RI","No. RM","Nama Penderita","Umur (Tahun)","Alamat","Status Nikah","Status Pasien","Tanggal Masuk","Poli","Diagnosa","Keterangan","Ruang Perawatan","Nama Dokter","tanggal Keluar","Lama"};
	DefaultTableModel modelTabelPasienKeluar = new DefaultTableModel(null, kolomTabelPasienKeluar);
	private Icon iconPasienKeluar = new ImageIcon(getClass().getResource("image/pasien-keluar.png"));
	private JButton btnPasienKeluar = new JButton("Pasien Keluar",iconPasienKeluar);
	private JLabel lblKodeRI = new JLabel("No. RI : ");
	private JTextField txtKodeRI = new JTextField();
	private Icon iconRefresh3 = new ImageIcon(getClass().getResource("image/refresh.png"));
	private JButton btnRefresh3 = new JButton("Refresh",iconRefresh3);
	private JLabel lblIdDokter = new JLabel("No. ID Dokter : ");
	private JTextField txtIdDokter = new JTextField();
	
	/**
	 * konstruktor kelas pasien_rawat_inap
	 */
	public menuRawatInap()
	{
		//super("Pasien Rawat Inap");
		//setIconImage(Toolkit.getDefaultToolkit().getImage(pasien_rawat_inap.class.getResource("Image/address-book-new.png")));
		setLayout(new GridLayout(0,1));
		JPanel panelJudul = new JPanel();
		panelJudul.add(lbljudul);
		panelJudul.add(lbljudul2);
		
		PanelHP.setLayout(new FlowLayout());
		PanelHP.add(lblPasAnggota);
		PanelHP.add(txtPasAnggota);
		PanelHP.add(lblPasPns);
		PanelHP.add(txtPasPns);
		PanelHP.add(lblPasSiswaDikbang);
		PanelHP.add(txtPasSiswaDikbang);
		PanelHP.add(lblPasSiswaDiktuk);
		PanelHP.add(txtPasSiswaDiktuk);
		PanelHP.add(lblPasTahanan);
		PanelHP.add(txtPasTahanan);
		PanelHP.add(lblPasMandiri);
		PanelHP.add(txtPasMandiri);
		PanelHP.add(lblPasBpjs);
		PanelHP.add(txtPasBpjs);
		PanelHP.add(lblPasPurna);
		PanelHP.add(txtPasPurna);
		
		//mengatur jenis huruf pada komponen pasien rawat inap
		txtNamaPenderita.setFont(tnr);
		comboStatus.setFont(tnr);
		tanggalMasuk.setFont(tnr);
		lblResultLama.setFont(tnr);
		cmboPoli.setFont(tnr);
		cmboPenyakit.setFont(tnr);
		areaDiagnosa.setFont(tnr);
		areaKeterangan.setFont(tnr);
		lblalamat.setFont(tnr);
		lbldiagnosa.setFont(tnr);
		lbljudul.setFont(tnr);
		lbljudul2.setFont(tnr);
		lblKeterangan.setFont(tnr);
		lbllama.setFont(tnr);
		lblnama_penderita.setFont(tnr);
		lblno_reg.setFont(tnr);
		txtNoRm.setFont(tnr);
		lblpekerjaan.setFont(tnr);
		lblruang_perawatan.setFont(tnr);
		lblstatus.setFont(tnr);
		lbltanggal_keluar.setFont(tnr);
		lbltanggal_masuk.setFont(tnr);
		lblumur.setFont(tnr);
		lblIcd10.setFont(tnr);
		cmboStatusPasien.setFont(tnr);
		txtUmur.setFont(tnr);
		txtAlamat.setFont(tnr);
		lblRuangInap.setFont(tnr);
		cmboRuangInap.setFont(tnr);
		lblDokter.setFont(tnr);
		cmboDokter.setFont(tnr);
		tanggalKeluar.setFont(tnr);
		lblKodeRI.setFont(tnr);
		txtKodeRI.setFont(tnr);
		lblIdDokter.setFont(tnr);
		txtIdDokter.setFont(tnr);
		
		masterRawatInap.setBorder(BorderFactory.createTitledBorder("Daftar Pasien Rawat Inap"));
		masterRawatInap.setLayout(new GridLayout(17,2));
		masterRawatInap.add(lblKodeRI);
		masterRawatInap.add(txtKodeRI);
		masterRawatInap.add(lblno_reg);
		masterRawatInap.add(txtNoRm);
		masterRawatInap.add(lblnama_penderita);
		masterRawatInap.add(txtNamaPenderita);
		masterRawatInap.add(lblumur);
		masterRawatInap.add(txtUmur);
		masterRawatInap.add(lblalamat);
		masterRawatInap.add(txtAlamat);
		masterRawatInap.add(lblstatus);
		masterRawatInap.add(comboStatus);
		masterRawatInap.add(lblpekerjaan);
		masterRawatInap.add(cmboStatusPasien);
		masterRawatInap.add(lbltanggal_masuk);
		//tanggal masuk
		tanggalMasuk.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		tanggalKeluar.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		tanggalKeluar.addDateListener(new DateListener()
		{
			@Override
			public void dateChanged(DateEvent arg0) {
				// TODO Auto-generated method stub
				
				
				Date tanggal_masuk = tanggalMasuk.getDate();
				Date tanggal_keluar = tanggalKeluar.getDate();
				
				try
				{
					long hariPertama = tanggal_masuk.getTime();
					long hariTerakhir = tanggal_keluar.getTime();
					long selisih = hariTerakhir-hariPertama;
					long lama = selisih / (24*60*60*1000);
					
					lblResultLama.setText(Long.toString(lama));
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada konversi waktu : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}
        );
		masterRawatInap.add(tanggalMasuk);
		masterRawatInap.add(lbltanggal_keluar);
		masterRawatInap.add(tanggalKeluar);
		masterRawatInap.add(lbllama);
		masterRawatInap.add(lblResultLama);
		masterRawatInap.add(lblRuangInap);
		masterRawatInap.add(cmboRuangInap);
		masterRawatInap.add(lblruang_perawatan);
		masterRawatInap.add(cmboPoli);
		masterRawatInap.add(lblDokter);
		masterRawatInap.add(cmboDokter);
		masterRawatInap.add(lblIdDokter);
		txtIdDokter.setEditable(false);
		masterRawatInap.add(txtIdDokter);
		masterRawatInap.add(lbldiagnosa);
		masterRawatInap.add(cmboPenyakit);
		masterRawatInap.add(lblIcd10);
		areaDiagnosa.setEditable(false);
		masterRawatInap.add(scrollDiagnosa);
		masterRawatInap.add(lblKeterangan);
		masterRawatInap.add(scrollKeterangan);
		
		masterTombol.add(btnPasienKeluar);
		masterTombol.add(btnSimpan);
		masterTombol.add(btnSimpanUbah);
		masterTombol.add(btnBatal);
		masterTombol.add(btnRefresh3);
		masterTombol.setBorder(BorderFactory.createTitledBorder("Tombol Aksi"));
		
		JPanel pnlGabungInputdanTombolUtm = new JPanel();
		pnlGabungInputdanTombolUtm.setLayout(new FlowLayout());
		//pnlGabungInputdanTombolUtm.add(panelJudul);		
		pnlGabungInputdanTombolUtm.add(masterRawatInap);
		pnlGabungInputdanTombolUtm.add(masterTombol);
		
		JScrollPane scrollPanelInput = new JScrollPane();
		scrollPanelInput.getViewport().add(pnlGabungInputdanTombolUtm);
		
		tabelPasienRawatInap.setModel(modelTabelPasienRawatInap);
		tabelPasienRawatInap.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollTable.getViewport().add(tabelPasienRawatInap);
		masterTabel.setLayout(new BorderLayout());
		masterTabel.add(scrollTable);
		tabelPasienRawatInap.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				ambilData();
				btnUbah.setEnabled(true);
				btnHapus.setEnabled(true);
				btnRefresh1.setEnabled(true);
				btnReport.setEnabled(false);
				btnTotal.setEnabled(false);
			}
		});
		
		masterTombolEkstra.add(cmboPoliklinik);
		masterTombolEkstra.add(PanelHP);
		//masterTombolEkstra.add(btnReport);
		masterTombolEkstra.add(btnTotal);
		masterTombolEkstra.add(btnUbah);
		masterTombolEkstra.add(btnHapus);
		masterTombolEkstra.add(btnRefresh1);
		masterTombolEkstra.setLayout(new FlowLayout());
		//masterTombolEkstra.setBorder(BorderFactory.createTitledBorder("Tombol aksi"));
		
		JPanel gabungTabeldanTombolEkstra = new JPanel();
		gabungTabeldanTombolEkstra.add(masterTabel);
		gabungTabeldanTombolEkstra.add(masterTombolEkstra);
		gabungTabeldanTombolEkstra.setLayout(new GridLayout(2,0));
		
		panelPasienKeluar.setLayout(new GridLayout(1,1));
		tabelPasienKeluar.setModel(modelTabelPasienKeluar);
		tabelPasienKeluar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollTabelPasienKeluar.getViewport().add(tabelPasienKeluar);
		panelPasienKeluar.add(scrollTabelPasienKeluar);
		
		//tab master
		tabRawatInap.addTab("Daftar Pasien Rawat Inap", scrollPanelInput);
		tabRawatInap.addTab("Pasien Rawat Inap", gabungTabeldanTombolEkstra);
		tabRawatInap.addTab("Pasien Keluar", panelPasienKeluar);
		
		add(tabRawatInap);
		
		penghendel hendelButton = new penghendel();
		btnTambah.addActionListener(hendelButton);
		btnSimpan.addActionListener(hendelButton);
		btnBatal.addActionListener(hendelButton);
		btnTotal.addActionListener(hendelButton);
		btnRefresh1.addActionListener(hendelButton);
		btnUbah.addActionListener(hendelButton);
		btnHapus.addActionListener(hendelButton);
		btnSimpanUbah.addActionListener(hendelButton);
		txtNoRm.addActionListener(hendelButton);
		btnPasienKeluar.addActionListener(hendelButton);
		btnRefresh3.addActionListener(hendelButton);
		
		item();
		tampilTabel();
		tampilPasienKeluar();
		kembali();
		
		//hasil pilih penyakit
		cmboPenyakit.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{

				if(cmboPenyakit.getSelectedIndex()==0)
				{
					return;
				}
				else
				{
					String i = (String) cmboPenyakit.getSelectedItem();
					String no_icd10 = "";
					try
					{
						connect = konek_database.getKonekDB();
						state = connect.createStatement();
						result = state.executeQuery("select no_icd10 from macam_penyakit where macam_penyakit='"+i+"'");
						
						if(result.next()){
							no_icd10 = result.getString(1);
							areaDiagnosa.setText(no_icd10);
						}
						
						connect.close();
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Terjadi "
								+ "kesalahan pada kode penyakit: "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			});
		
		cmboPoliklinik.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(cmboPoliklinik.getSelectedItem().equals("-- Pilih Poli --"))
						{
							tampilHP();
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Gigi"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Gigi'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Gigi'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Gigi'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Gigi'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Gigi'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Gigi'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Gigi'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Gigi'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik gigi : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Mata"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Mata'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Mata'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Mata'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Mata'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Mata'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Mata'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Mata'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Mata'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik mata : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Bedah"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Bedah'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Bedah'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Bedah'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Bedah'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Bedah'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Bedah'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Bedah'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Bedah'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik bedah : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Urologi"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Urologi'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Urologi'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Urologi'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Urologi'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Urologi'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Urologi'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Urologi'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Urologi'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik urologi : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Radiologi"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Radiologi'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Radiologi'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Radiologi'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Radiologi'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Radiologi'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Radiologi'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Radiologi'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Radiologi'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik radiologi : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli THT"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli THT'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli THT'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli THT'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli THT'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli THT'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli THT'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli THT'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli THT'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik THT : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Penyakit Dalam"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Penyakit Dalam'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Penyakit Dalam'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Penyakit Dalam'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Penyakit Dalam'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Penyakit Dalam'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Penyakit Dalam'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Penyakit Dalam'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Penyakit Dalam'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik penyakit dalam : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli BKIA"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli BKIA'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli BKIA'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli BKIA'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli BKIA'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli BKIA'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli BKIA'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli BKIA'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli BKIA'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik BKIA : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Laktasi"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Laktasi'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Laktasi'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Laktasi'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Laktasi'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Laktasi'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Laktasi'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Laktasi'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Laktasi'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik laktasi : "+ex.getMessage());
							}
						}
						if(cmboPoliklinik.getSelectedItem().equals("Poli Umum"))
						{
							try
							{
								connect = konek_database.getKonekDB();
								Statement state = connect.createStatement();
								ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil' and poli='Poli Umum'");
								if(result.next())
								{
									int anggota = result.getInt(1);
									txtPasAnggota.setText(""+anggota);
									result.close();
								}
								
								ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS' and poli='Poli Umum'");
								if(result2.next())
								{
									int pns = result2.getInt(1);
									txtPasPns.setText(""+pns);
									result2.close();
								}
								
								ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG' and poli='Poli Umum'");
								if(result3.next())
								{
									int siswa_dikbang = result3.getInt(1);
									txtPasSiswaDikbang.setText(""+siswa_dikbang);
									result3.close();
								}
								
								ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK' and poli='Poli Umum'");
								if(result4.next())
								{
									int siswa_diktuk = result4.getInt(1);
									txtPasSiswaDiktuk.setText(""+siswa_diktuk);
								}
								
								
								ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan' and poli='Poli Umum'");
								if(result5.next())
								{
									int tahanan = result5.getInt(1);
									txtPasTahanan.setText(""+tahanan);
								}
								
								ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri' and poli='Poli Umum'");
								if(result6.next())
								{
									int mandiri = result6.getInt(1);
									txtPasMandiri.setText(""+mandiri);
								}
								
								ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS' and poli='Poli Umum'");
								if(result7.next())
								{
									int bpjs = result7.getInt(1);
									txtPasBpjs.setText(""+bpjs);
								}
								
								ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan' and poli='Poli Umum'");
								if(result8.next())
								{
									int purna = result8.getInt(1);
									txtPasPurna.setText(""+purna);
								}
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboPoliklinik Umum : "+ex.getMessage());
							}
						}
					}
				}
		);
	}
	
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{

			if(e.getSource()==btnSimpan)
			{
				/**
				 * Proses penyimpanan data pasien rawat inap ke database
				 */
				try
				{
					
					connect = konek_database.getKonekDB();
					//connect.setAutoCommit(false);
					
					ps = connect.prepareStatement("insert into daftar_rawat_inap (no_rm,nama,umur,alamat,sttus_nikah,status_pasien,tanggal_masuk,ruang_perawatan,poli,no_id,no_icd10,keterangan) values(?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1, txtNoRm.getText());
					ps.setString(2, txtNamaPenderita.getText());
					ps.setInt(3, Integer.parseInt(txtUmur.getText()));
					ps.setString(4, txtAlamat.getText());
					ps.setString(5, (String) comboStatus.getSelectedItem());
					ps.setString(6, (String) cmboStatusPasien.getSelectedItem());
					ps.setString(7, (String) tanggalMasuk.getSelectedItem());
					ps.setString(8, (String) cmboRuangInap.getSelectedItem());
					ps.setString(9, (String) cmboPoli.getSelectedItem());
					ps.setString(10, txtIdDokter.getText());
					ps.setString(11, areaDiagnosa.getText());
					ps.setString(12, areaKeterangan.getText());
					ps.executeUpdate();
					
					
					PreparedStatement ps2 = connect.prepareStatement("insert into daftar_penyakit (no_rm,no_icd10,status_pasien) values (?,?,?)");
					ps2.setString(1, txtNoRm.getText());
					ps2.setString(2, areaDiagnosa.getText());
					ps2.setString(3, (String) cmboStatusPasien.getSelectedItem());
					ps2.executeUpdate();
					
					//connect.commit();
					
					JOptionPane.showMessageDialog(null, "Data berhasil tersimpan","Sukses",JOptionPane.INFORMATION_MESSAGE);	
					connect.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol simpan : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
				}
				finally
				{
					tampilTabel();
					kembali();
					
				}
			}
			else if(e.getSource()==btnBatal)
			{
				kembali();
			}
			else if(e.getSource()==btnTotal)
			{
				simpanHP();
				/**
				try{
				connect = konek_database.getKonekDB();
				state = connect.createStatement();
				result = state.executeQuery("select count(*) from pasien_rawat_inap");
				
				while(result.next())
				{
					Object obj[] = new Object[1];
					obj[0] = result.getString(1);
					
					JOptionPane.showMessageDialog(null,obj,"Total data yang sudah masuk",JOptionPane.INFORMATION_MESSAGE);
				}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol total : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
				}
				**/
			}
			else if(e.getSource()==btnRefresh1)
			{
				kembali();
			}
			else if(e.getSource()==btnUbah)
			{
				tabRawatInap.setSelectedIndex(0);
				btnSimpan.setVisible(false);
				btnSimpanUbah.setVisible(true);
			}		
			else if(e.getSource()==btnSimpanUbah)
			{
				try
				{
					connect = konek_database.getKonekDB();
					ps = connect.prepareStatement("update daftar_rawat_inap set no_rm=?, nama =?, umur=?, alamat=?,sttus_nikah=?,status_pasien=?,tanggal_masuk=?,ruang_perawatan=?,poli=?,no_id=?,no_icd10=?,keterangan=?,tanggal_keluar=?,lama=? where no_ri = ?");
					
					ps.setString(1, txtNoRm.getText());
					ps.setString(2, txtNamaPenderita.getText());
					ps.setInt(3, Integer.parseInt(txtUmur.getText()));
					ps.setString(4, txtAlamat.getText());
					ps.setString(5, (String) comboStatus.getSelectedItem());
					ps.setString(6, (String) cmboStatusPasien.getSelectedItem());
					ps.setString(7, (String) tanggalMasuk.getSelectedItem());
					ps.setString(8, (String) cmboRuangInap.getSelectedItem());
					ps.setString(9, (String) cmboPoli.getSelectedItem());
					ps.setString(10, txtIdDokter.getText());
					ps.setString(11, areaDiagnosa.getText());
					ps.setString(12, areaKeterangan.getText());
					ps.setString(13, (String) tanggalKeluar.getSelectedItem());
					ps.setInt(14, Integer.parseInt(lblResultLama.getText()));
					ps.setString(15, txtKodeRI.getText());
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Data berhasil diubah");
					ps.close();
					connect.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol simpan perubahan : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
				}
				finally
				{
					tampilTabel();
					btnSimpanUbah.setVisible(false);
					btnPasienKeluar.setVisible(true);
				}
			}
			else if(e.getSource()==btnHapus)
			{
				try
				{
					connect = konek_database.getKonekDB();
					ps = connect.prepareStatement("delete from pasien_rawat_inap where kd_rawat_inap = ?");
					
					ps.setInt(1, Integer.parseInt(txtKodeRI.getText()));
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
					ps.close();
					connect.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tombol hapus : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
				}
				finally
				{
					tampilTabel();
					kembali();
				}
			}
			else if(e.getSource()==txtNoRm)
			{
				ambilPasien();
			}
			else if(e.getSource()==btnPasienKeluar)
			{
				try
				{
					connect = konek_database.getKonekDB();
					ps = connect.prepareStatement("delete from pasien_rawat_inap where kd_rawat_inap = ?");
					
					ps.setInt(1, Integer.parseInt(txtKodeRI.getText()));
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Data berhasil tersimpan");
					ps.close();
					connect.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada btnPasienKeluar : "+ex.getMessage());
				}
				finally
				{
				tampilTabel();
				tampilHP();
				tampilPasienKeluar();
				kembali();
				tabRawatInap.setSelectedIndex(2);
				}
			}
			else if(e.getSource()==btnRefresh3)
			{
				refreshDaftarRawatInap();
			}
		}
	}
	
	//metode ambil data pasien
	private void ambilPasien() 
	{
		
		String nama = "", alamat="", sttus_nikah="";
		int umur = 0;
		try
		{
			connect = konek_database.getKonekDB();
			state = connect.createStatement();
			result = state.executeQuery("select nama,umur,alamat, sttus_nikah from pasien where no_rm ="+txtNoRm.getText()+"");
			
			if(result.next())
			{
				nama = result.getString(1);
				umur = result.getInt(2);
				alamat = result.getString(3);
				sttus_nikah = result.getString(4);
			}
			
			txtNamaPenderita.setText(nama);
			txtUmur.setText(""+umur);
			txtAlamat.setText(alamat);
			comboStatus.setSelectedItem(sttus_nikah);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada metode ambilPasien : "+ex.getMessage());
		}
	}
	
	//modul membersihkan panel pasien rawat inap
	void kembali()
	{
		 //pembuatan kode rawat inap otomatis
		txtKodeRI.setEditable(false);
		try
		{
			connect = konek_database.getKonekDB();
			state = connect.createStatement();
			result = state.executeQuery("select no_ri from daftar_rawat_inap order by no_ri desc");
			
			if(result.next())
			{
				int i = result.getInt(1) + 1;
				txtKodeRI.setText(Integer.toString(i));
			}
			else
			{
				txtKodeRI.setText("1");
			}
			
		}catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada pembuatan No. RI otomatis : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
		}
		
		//lblResultLama.setText("");
		txtNamaPenderita.setText("");
		//areaAlamat.setText("");
		areaDiagnosa.setText("");
		areaKeterangan.setText("");
		txtNoRm.setText("");
		txtUmur.setText("");
		txtAlamat.setText("");
		cmboPoli.setSelectedIndex(0);
		comboStatus.setSelectedIndex(0);
		//comboUmur.setSelectedIndex(0);
		cmboPenyakit.setSelectedIndex(0);
		cmboStatusPasien.setSelectedIndex(0);
		cmboDokter.setSelectedIndex(0);
		cmboRuangInap.setSelectedIndex(0);
		tanggalMasuk.setDate(new Date());
		btnUbah.setEnabled(false);
		btnHapus.setEnabled(false);
		btnReport.setEnabled(true);
		btnTotal.setEnabled(true);
		btnSimpanUbah.setVisible(false);
		btnRefresh1.setEnabled(false);
		btnSimpan.setVisible(true);
		lblResultLama.setText("");
		tabelPasienRawatInap.clearSelection();
		tampilHP();
		cmboPoliklinik.setSelectedIndex(0);
		btnPasienKeluar.setVisible(false);
		tanggalKeluar.setDate(new Date());
		tanggalMasuk.setDate(new Date());
		lblResultLama.setText("");
		txtIdDokter.setText("");
	}
	
	//tampil isi tabel pasien rawat inap
	private void tampilTabel()
	{
		modelTabelPasienRawatInap.getDataVector().removeAllElements();
		modelTabelPasienRawatInap.fireTableDataChanged();
		
		try
		{
			connect = konek_database.getKonekDB();
			state = connect.createStatement();
			result = state.executeQuery("select no_ri,no_rm,nama,umur,alamat,sttus_nikah,status_pasien,tanggal_masuk,ruang_perawatan,poli,no_id,no_icd10,keterangan from daftar_rawat_inap");
			
			while(result.next())
			{
				Object obj[] = new Object[13];
				obj[0] = result.getInt(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getInt(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getDate(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				obj[12] = result.getString(13);
				
				modelTabelPasienRawatInap.addRow(obj);
			}
			connect.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampil tabel : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void tampilHP()
	{
		try
		{
			connect = konek_database.getKonekDB();
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Personil'");
			if(result.next())
			{
				int anggota = result.getInt(1);
				txtPasAnggota.setText(""+anggota);
				result.close();
			}
			
			ResultSet result2 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='PNS'");
			if(result2.next())
			{
				int pns = result2.getInt(1);
				txtPasPns.setText(""+pns);
				result2.close();
			}
			
			ResultSet result3 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKBANG'");
			if(result3.next())
			{
				int siswa_dikbang = result3.getInt(1);
				txtPasSiswaDikbang.setText(""+siswa_dikbang);
				result3.close();
			}
			
			ResultSet result4 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Siswa DIKTUK'");
			if(result4.next())
			{
				int siswa_diktuk = result4.getInt(1);
				txtPasSiswaDiktuk.setText(""+siswa_diktuk);
			}
			
			
			ResultSet result5 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Tahanan'");
			if(result5.next())
			{
				int tahanan = result5.getInt(1);
				txtPasTahanan.setText(""+tahanan);
			}
			
			ResultSet result6 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Mandiri'");
			if(result6.next())
			{
				int mandiri = result6.getInt(1);
				txtPasMandiri.setText(""+mandiri);
			}
			
			ResultSet result7 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='BPJS'");
			if(result7.next())
			{
				int bpjs = result7.getInt(1);
				txtPasBpjs.setText(""+bpjs);
			}
			
			ResultSet result8 = state.executeQuery("select count(*) from daftar_rawat_inap where status_pasien='Purnawirawan'");
			if(result8.next())
			{
				int purna = result8.getInt(1);
				txtPasPurna.setText(""+purna);
			}
			
			//connect.commit();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul tampilHP : "+ex.getMessage());
		}
	}
	
	private void simpanHP()
	{
		try
		{
			connect = konek_database.getKonekDB();
			String simpanHP = "update rawat_inap set hp_anggota=hp_anggota+?,hp_pns=hp_pns+?,hp_siswa_dikbang=hp_siswa_dikbang+?,hp_siswa_diktuk=hp_siswa_diktuk+?,"
					+ "hp_tahanan=hp_tahanan+?,hp_mandiri=hp_mandiri+?,hp_bpjs=hp_bpjs+?,hp_jumlah=hp_anggota+hp_pns+hp_siswa_dikbang,hp_jumlah2=hp_siswa_diktuk+"
					+ "hp_tahanan+hp_mandiri+hp_bpjs,hp_total=hp_jumlah+hp_jumlah2 where poliklinik=?";
			PreparedStatement ps = connect.prepareStatement(simpanHP);
			ps.setInt(1, Integer.parseInt(txtPasAnggota.getText()));
			ps.setInt(2, Integer.parseInt(txtPasPns.getText()));
			ps.setInt(3, Integer.parseInt(txtPasSiswaDikbang.getText()));
			ps.setInt(4, Integer.parseInt(txtPasSiswaDiktuk.getText()));
			ps.setInt(5, Integer.parseInt(txtPasTahanan.getText()));
			ps.setInt(6, Integer.parseInt(txtPasMandiri.getText()));
			ps.setInt(7, Integer.parseInt(txtPasBpjs.getText()));
			ps.setString(8, (String) cmboPoliklinik.getSelectedItem());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data Hari Perawatan Berhasil disimpan !");
			connect.close();
			ps.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul simpanHP : "+ex.getMessage());
		}
		finally
		{
			kembali();
		}
	}
	
	//menampilkan isi dari variabel JComboBox
	private void item()
	{
		
		cmboStatusPasien.addItem("-- Pilih Status Pasien -- ");
		cmboStatusPasien.addItem("Personil");
		cmboStatusPasien.addItem("PNS");
		cmboStatusPasien.addItem("Siswa Dikbang");
		cmboStatusPasien.addItem("Siswa Diktuk");
		cmboStatusPasien.addItem("Tahanan");
		cmboStatusPasien.addItem("Mandiri");
		cmboStatusPasien.addItem("BPJS");
		cmboStatusPasien.addItem("Purnawirawan");
		
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
		
		cmboRuangInap.addItem("-- Pilih Ruangan --");
		cmboRuangInap.addItem("Ruang Seruni");
		cmboRuangInap.addItem("Ruang Flamboyan");
		cmboRuangInap.addItem("Ruang Teratai");
		cmboRuangInap.addItem("Ruang Kemuning");
		cmboRuangInap.addItem("Ruang Anggrek");
		cmboRuangInap.addItem("Ruang Lili");
		cmboRuangInap.addItem("Ruang Sakura");
		cmboRuangInap.addItem("Ruang Melati");
				
		comboStatus.addItem("-- Pilih Status --");
		comboStatus.addItem("Nikah");
		comboStatus.addItem("Belum Nikah");
		
		//pilih macam penyakit
		cmboPenyakit.addItem("-- Pilih Macam Penyakit --");
		
		try
		{
			String o = "";
			connect = konek_database.getKonekDB();
			state = connect.createStatement();
			result = state.executeQuery("select macam_penyakit from macam_penyakit");

			while(result.next())
			{
				o = result.getString(1);
				cmboPenyakit.addItem(o);
			}
			connect.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada kode penyakit : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
		}
		
		//pilih dokter
		String nama_dokter = "";
		cmboDokter.addItem("-- Pilih Dokter --");
		try
		{
			connect = konek_database.getKonekDB();
			state = connect.createStatement();
			result = state.executeQuery("select nama_dokter from dokter");
			
			while(result.next())
			{
				nama_dokter = result.getString(1);
				cmboDokter.addItem(nama_dokter);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada cari dokter : "+ex.getMessage());
		}
		cmboDokter.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String iddokter = "";
						try
						{
							connect = konek_database.getKonekDB();
							state = connect.createStatement();
							result = state.executeQuery("select no_id from dokter where nama_dokter='"+cmboDokter.getSelectedItem()+"'");
							
							if(result.next())
							{
								iddokter = result.getString(1);
								txtIdDokter.setText(iddokter);
							}
							
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada overide cmboDokter : "+ex.getMessage());
						}
					}
				}
		);
		
		
		cmboPoliklinik.addItem("-- Pilih Poli --");
		cmboPoliklinik.addItem("Poli Gigi");
		cmboPoliklinik.addItem("Poli Mata");
		cmboPoliklinik.addItem("Poli Bedah");
		cmboPoliklinik.addItem("Poli Urologi");
		cmboPoliklinik.addItem("Poli Radiologi");
		cmboPoliklinik.addItem("Poli THT");
		cmboPoliklinik.addItem("Poli Penyakit Dalam");
		cmboPoliklinik.addItem("Poli BKIA");
		cmboPoliklinik.addItem("Poli Laktasi");
		cmboPoliklinik.addItem("Poli Umum");
		/**
		 * Menyimpan data pada RadioButton (Pekerjaan)
		 
		String im = "";
		
		if(radioPns.isSelected())
		{
			im = "PNS";
		}
		else if(radioWiraswasta.isSelected())
		{
			im = "Wiraswasta";
		}
		else if(radioPelajar.isSelected())
		{
			im = "Pelajar";
		}
		else if(radioPelajar.isSelected())
		{
			im = "Lainnya";
		}
		else
		{
			im = "Kosong";
		}
		**/

		
		
		
		/**
		comboUmur.addItem("-- Pilih Tahun --");
		for(int i = 0;i<200;i++)
		{
			comboUmur.addItem(i+" Tahun");
		}
		**/
		
		/**
		
        **/
		
	}
	
	//mengmbil data pasien pada tabel rawat inap
	public void ambilData()
	{
		int i = tabelPasienRawatInap.getSelectedRow();
		
		int ambilKdRI = (int) modelTabelPasienRawatInap.getValueAt(i, 0);
		txtKodeRI.setText(""+ambilKdRI);
		
		String ambilNoRm = (String) modelTabelPasienRawatInap.getValueAt(i, 1);
		txtNoRm.setText(ambilNoRm);
		
		String ambilNamaPasien = (String) modelTabelPasienRawatInap.getValueAt(i, 2);
		txtNamaPenderita.setText(ambilNamaPasien);
		
		int ambilUmur = (int) modelTabelPasienRawatInap.getValueAt(i, 3);
		txtUmur.setText(""+ambilUmur);
		
		String ambilAlamat = (String) modelTabelPasienRawatInap.getValueAt(i, 4);
		txtAlamat.setText(ambilAlamat);
		
		String ambilStatus = (String) modelTabelPasienRawatInap.getValueAt(i, 5);
		comboStatus.setSelectedItem(ambilStatus);
		
		String ambilStatusPasien = (String) modelTabelPasienRawatInap.getValueAt(i, 6);
		cmboStatusPasien.setSelectedItem(ambilStatusPasien);
		
		Date ambilTanggalMasuk = (Date) modelTabelPasienRawatInap.getValueAt(i, 7);
		tanggalMasuk.setDate(ambilTanggalMasuk);
		
		String ambilRuangPerawatan = (String) modelTabelPasienRawatInap.getValueAt(i, 8);
		cmboRuangInap.setSelectedItem(ambilRuangPerawatan);
		
		String ambilPoli = (String) modelTabelPasienRawatInap.getValueAt(i, 9);
		cmboPoli.setSelectedItem(ambilPoli);
		
		String ambilDokter = (String) modelTabelPasienRawatInap.getValueAt(i, 10);
		cmboDokter.setSelectedItem(ambilDokter);
		
		String ambilDiagnosa = (String) modelTabelPasienRawatInap.getValueAt(i, 11);
		areaDiagnosa.setText(ambilDiagnosa);
		
		String ambilKeterangan = (String) modelTabelPasienRawatInap.getValueAt(i, 12);
		areaKeterangan.setText(ambilKeterangan);
	}
	
	private void tampilPasienKeluar()
	{
		modelTabelPasienKeluar.getDataVector().removeAllElements();
		modelTabelPasienRawatInap.fireTableDataChanged();
		try
		{
			connect = konek_database.getKonekDB();
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("select * from pasien_rawat_inap_keluar where not tanggal_keluar is null");
			while(result.next())
			{
				Object obj[] = new Object[15];
				obj[0] = result.getInt(1);
				obj[1] = result.getString(2);
				obj[2] = result.getString(3);
				obj[3] = result.getInt(4);
				obj[4] = result.getString(5);
				obj[5] = result.getString(6);
				obj[6] = result.getString(7);
				obj[7] = result.getDate(8);
				obj[8] = result.getString(9);
				obj[9] = result.getString(10);
				obj[10] = result.getString(11);
				obj[11] = result.getString(12);
				obj[12] = result.getString(13);
				obj[13] = result.getDate(14);
				obj[14] = result.getInt(15);
				
				modelTabelPasienKeluar.addRow(obj);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampilPasienKeluar : "+ex.getMessage());
		}
	}
	
	private void refreshDaftarRawatInap()
	{
		String macam_penyakit = "";
		String dokter = "";
		cmboPenyakit.removeAllItems();
		cmboPenyakit.addItem("-- Pilih Macam Penyakit --");
		cmboDokter.removeAllItems();
		cmboDokter.addItem("-- Pilih Dokter --");
		
		try
		{
			connect = konek_database.getKonekDB();
			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("select macam_penyakit from macam_penyakit");
			
			while(result.next())
			{
				macam_penyakit = result.getString(1);
				cmboPenyakit.addItem(macam_penyakit);
			}
			
			ResultSet result2 = state.executeQuery("select nama_dokter from dokter");
			
			while(result2.next())
			{
				dokter = result2.getString(1);
				cmboDokter.addItem(dokter);
			}
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada modul refreshDaftarRawatInap : "+ex.getMessage());
		}
	}
	

}
