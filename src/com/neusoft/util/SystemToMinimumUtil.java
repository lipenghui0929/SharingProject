package com.neusoft.util;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TextArea;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SystemToMinimumUtil {
	
	static boolean regStatus = false;
	private static TrayIcon trayIcon = null;
	static SystemTray tray = SystemTray.getSystemTray();
	
	public static void miniTray(final JFrame mf) {//������С��������������

	    //����ͼ��
	    ImageIcon trayImg = ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("VolumeInputFrame.close"), false);
		
		PopupMenu pop = new PopupMenu();//���������һ��˵�
		MenuItem show = new MenuItem("��ԭ");
		MenuItem exit = new MenuItem("�˳�");
		
		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // ���»�ԭ��
			
				tray.remove(trayIcon);
				mf.setVisible(true);
				mf.setExtendedState(JFrame.NORMAL);
				mf.toFront();
			}

	   });

		exit.addActionListener(new ActionListener() { // �����˳���
		
			public void actionPerformed(ActionEvent e) {
			
				tray.remove(trayIcon);
				System.exit(0);
			
			}
		
		});

		pop.add(show);
		pop.add(exit);
		
		trayIcon = new TrayIcon(trayImg.getImage(), "ţ ţϵͳ", pop);
		trayIcon.setImageAutoSize(true);
		
		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // �����˫���¼�
			
				if (e.getClickCount() == 2) {
				
					tray.remove(trayIcon); // ��ȥ����ͼ��
					mf.setVisible(true);
					mf.setExtendedState(JFrame.NORMAL); // ��ԭ����
					mf.toFront();
				}
	
			}
		
		});

		try {
		
			tray.add(trayIcon);
		
		} catch (AWTException e1) {
		// TODO Auto-generated catch block
			e1.printStackTrace();
		}

   }

}
