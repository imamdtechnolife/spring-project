package com.PlanetMars323.spring_project;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 * Sistem Informasi Pelayanan Kesehatan Rumah Sakit Bhayangkara Mataram
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada tema : "+ex.getMessage(),"Pesan Kesalahan",JOptionPane.INFORMATION_MESSAGE);
			}
    	hal_utama frame = new hal_utama();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
    }
}
