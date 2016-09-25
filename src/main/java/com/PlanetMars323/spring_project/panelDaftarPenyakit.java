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
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

public class panelDaftarPenyakit extends JPanel {
	private JTextField txtDaftarTerperinci;
	private JTable table;
	private JTextField txtNoDtd;
	private JButton btnSimpan;
	private JButton btnBatal;
	private DefaultTableModel modelTabelMacamPenyakit = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No. DTD", "No. ICD-10", "Macam Penyakit", "Penyakit Gigi dan Mulut"
			}
		);
	Connection konek = null;
	private JTextArea txtMacamPenyakit;
	private JButton btnUbah;
	private JButton btnHapus;
	private JButton btnRefresh;
	private JCheckBox chckbxYa;

	/**
	 * Create the panel.
	 */
	public panelDaftarPenyakit() {
		setLayout(new GridLayout(0,1));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 880, 709);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Macam Penyakit", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Macam Penyakit");
		lblNewLabel.setBounds(25, 11, 327, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 27));
		
		JLabel lblNoDtd = new JLabel("No. DTD");
		lblNoDtd.setBounds(53, 77, 86, 14);
		panel.add(lblNoDtd);
		lblNoDtd.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JLabel lblNoDaftarTerperinci = new JLabel("No. ICD-10");
		lblNoDaftarTerperinci.setBounds(52, 114, 156, 30);
		panel.add(lblNoDaftarTerperinci);
		lblNoDaftarTerperinci.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		txtDaftarTerperinci = new JTextField();
		txtDaftarTerperinci.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtDaftarTerperinci.setBounds(231, 117, 187, 29);
		panel.add(txtDaftarTerperinci);
		txtDaftarTerperinci.setColumns(10);
		
		JLabel lblMacamPenyakit = new JLabel("Macam Penyakit");
		lblMacamPenyakit.setBounds(53, 163, 156, 29);
		panel.add(lblMacamPenyakit);
		lblMacamPenyakit.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(231, 170, 267, 85);
		panel.add(scrollPane_1);
		
		txtMacamPenyakit = new JTextArea();
		scrollPane_1.setViewportView(txtMacamPenyakit);
		
		btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(225, 305, 99, 33);
		panel.add(btnSimpan);
		btnSimpan.setIcon(new ImageIcon(panelDaftarPenyakit.class.getResource("/com/PlanetMars323/spring_project/Image/Simpan.png")));
		
		btnBatal = new JButton("Batal");
		btnBatal.setBounds(345, 305, 99, 33);
		panel.add(btnBatal);
		btnBatal.setIcon(new ImageIcon(panelDaftarPenyakit.class.getResource("/com/PlanetMars323/spring_project/Image/Batal.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(537, 74, 619, 403);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(modelTabelMacamPenyakit);
		table.getColumnModel().getColumn(2).setPreferredWidth(342);
		table.getColumnModel().getColumn(3).setPreferredWidth(124);
		scrollPane.setViewportView(table);
		
		txtNoDtd = new JTextField();
		txtNoDtd.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		txtNoDtd.setBounds(231, 75, 86, 29);
		panel.add(txtNoDtd);
		txtNoDtd.setColumns(10);
		
		btnUbah = new JButton("Ubah");
		btnUbah.setIcon(new ImageIcon(panelDaftarPenyakit.class.getResource("/com/PlanetMars323/spring_project/Image/Ubah.png")));
		btnUbah.setBounds(225, 305, 99, 33);
		btnUbah.setVisible(false);
		panel.add(btnUbah);
		
		btnHapus = new JButton("Hapus");
		btnHapus.setIcon(new ImageIcon(panelDaftarPenyakit.class.getResource("/com/PlanetMars323/spring_project/Image/Hapus.png")));
		btnHapus.setBounds(345, 305, 99, 33);
		btnUbah.setVisible(false);
		panel.add(btnHapus);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setIcon(new ImageIcon(panelDaftarPenyakit.class.getResource("/com/PlanetMars323/spring_project/Image/Refresh.png")));
		btnRefresh.setBounds(289, 384, 99, 33);
		panel.add(btnRefresh);
		
		JLabel lblPenyakitGigi = new JLabel("Penyakit Gigi & Mulut");
		lblPenyakitGigi.setFont(new Font("Bookman Old Style", Font.PLAIN, 14));
		lblPenyakitGigi.setBounds(52, 261, 177, 29);
		panel.add(lblPenyakitGigi);
		
		chckbxYa = new JCheckBox("Ya");
		chckbxYa.setFont(new Font("Bookman Old Style", Font.PLAIN, 13));
		chckbxYa.setBounds(231, 265, 97, 23);
		panel.add(chckbxYa);
		
		penghendel hendel = new penghendel();
		btnSimpan.addActionListener(hendel);
		btnBatal.addActionListener(hendel);
		btnUbah.addActionListener(hendel);
		btnHapus.addActionListener(hendel);
		btnRefresh.addActionListener(hendel);
		
		tampilIsiTabel();
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				ambilDataMacamPenyakit();
				btnUbah.setVisible(true);
				btnHapus.setVisible(true);
				btnSimpan.setVisible(false);
				btnBatal.setVisible(false);
			}
		});

	}
	
	public void simpanMacamPenyakit()
	{
		
		String i = "";
		if(chckbxYa.isSelected())
		{
			i="Ya";
			try
			{
				konek = konek_database.getKonekDB();
				PreparedStatement ps = konek.prepareStatement("insert into macam_penyakit (no_icd10,no_dtd,macam_penyakit,pen_gigidanmulut) values (?,?,?,?)");
				ps.setString(1, txtDaftarTerperinci.getText());
				ps.setString(2, txtNoDtd.getText());
				ps.setString(3, txtMacamPenyakit.getText());
				ps.setString(4, i);
				
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan!");
				ps.close();
				konek.close();
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada method simpanMacamPenyakit : "+ex.getMessage());
			}
			finally
			{
				bersihMacamPenyakit();
				tampilIsiTabel();
			}
		}
		else
		{
			try
			{
				konek = konek_database.getKonekDB();
				PreparedStatement ps = konek.prepareStatement("insert into macam_penyakit (no_icd10,no_dtd,macam_penyakit,pen_gigidanmulut) values (?,?,?,?)");
				ps.setString(1, txtDaftarTerperinci.getText());
				ps.setString(2, txtNoDtd.getText());
				ps.setString(3, txtMacamPenyakit.getText());
				ps.setString(4, i);
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan!");
				ps.close();
				konek.close();
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada method simpanMacamPenyakit else : "+ex.getMessage());
			}
			finally
			{
				bersihMacamPenyakit();
				tampilIsiTabel();
			}
		}
	}
	
	public void bersihMacamPenyakit()
	{
		txtNoDtd.setText("");
		txtDaftarTerperinci.setText("");
		txtMacamPenyakit.setText("");
		btnBatal.setVisible(true);
		btnSimpan.setVisible(true);
		btnUbah.setVisible(false);
		btnHapus.setVisible(false);
		chckbxYa.setSelected(false);
		table.clearSelection();
	}
	
	public void tampilIsiTabel()
	{
		modelTabelMacamPenyakit.getDataVector().removeAllElements();
		modelTabelMacamPenyakit.fireTableDataChanged();
		
		try
		{
		konek = konek_database.getKonekDB();
		Statement state = konek.createStatement();
		ResultSet result = state.executeQuery("select no_dtd,no_icd10,macam_penyakit,pen_gigidanmulut from macam_penyakit");
		
		while(result.next())
		{
			Object obj[] = new Object[4];
			obj[0] = result.getString(1);
			obj[1] = result.getString(2);
			obj[2] = result.getString(3);
			obj[3] = result.getString(4);
			
			modelTabelMacamPenyakit.addRow(obj);		
		}
		
		
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tampilTabel : "+ex.getMessage());
		}
		finally
		{
			
		}
	}
	
	private void ambilDataMacamPenyakit()
	{
		int i = table.getSelectedRow();
		chckbxYa.setSelected(false);
		
		String ambilNoDtd = (String) modelTabelMacamPenyakit.getValueAt(i, 0);
		txtNoDtd.setText(ambilNoDtd);
		
		String ambilDaftarTerperinci  = (String) modelTabelMacamPenyakit.getValueAt(i, 1);
		txtDaftarTerperinci.setText(ambilDaftarTerperinci);
		
		String ambilMacamPenyakit = (String) modelTabelMacamPenyakit.getValueAt(i, 2);
		txtMacamPenyakit.setText(ambilMacamPenyakit);
		
		String ambilPenyakitgigidanmulut = (String) modelTabelMacamPenyakit.getValueAt(i, 3);
		if(ambilPenyakitgigidanmulut.equals("Ya"))
		{
			chckbxYa.setSelected(true);
		}
		
	}
	
	private void ubahIsiTabel()
	{
		String o="";
		if(chckbxYa.isSelected())
		{
			o="Ya";
		}
		else 
		{
			o="";
		}
		try
		{
			konek = konek_database.getKonekDB();
			PreparedStatement ps = konek.prepareStatement("update macam_penyakit set no_icd10=?, no_dtd=?,macam_penyakit=?, pen_gigidanmulut=? where no_icd10=?");
			ps.setString(1, txtDaftarTerperinci.getText());
			ps.setString(2, txtNoDtd.getText());
			ps.setString(3, txtMacamPenyakit.getText());
			ps.setString(4, o);
			ps.setString(5, txtDaftarTerperinci.getText());
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil diubah !");
			tampilIsiTabel();
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada meode ubahIsiTabel : "+ex.getMessage());
		}
		finally
		{
			bersihMacamPenyakit();
			
		}
	}
	
	private void hapusIsiTabel()
	{
		try
		{
			konek = konek_database.getKonekDB();
			String query = "delete from macam_penyakit where no_icd10='"+txtDaftarTerperinci.getText()+"'";
			PreparedStatement ps = konek.prepareStatement(query);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Data berhasil dihapus !");
			tampilIsiTabel();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada hapusIsiTabel : "+ex.getMessage());
		}
		finally
		{
			bersihMacamPenyakit();
			
		}
	}
	
	/**
	private void defolt()
	{
		btnUbah.setVisible(false);
		btnHapus.setVisible(false);
		btnSimpan.setVisible(true);
		btnBatal.setVisible(true);
	}
	**/
	
	private class penghendel implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==btnSimpan)
			{
				if(txtDaftarTerperinci.getText().equals("") || txtMacamPenyakit.getText().equals("") || txtNoDtd.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Anda belum mengisi dengan lengkap kolom yang ada ");
					JOptionPane.showMessageDialog(null, "Silahkan isi dengan lengkap :)");
				}
				else
				{
					simpanMacamPenyakit();	
				}
				
			}
			else if(e.getSource()==btnBatal)
			{
				bersihMacamPenyakit();
			}
			else if(e.getSource()==btnUbah)
			{
				ubahIsiTabel();
			}
			else if(e.getSource()==btnHapus)
			{
				hapusIsiTabel();
			}
			else if(e.getSource()==btnRefresh)
			{
				bersihMacamPenyakit();
			}
		}
	}
}
