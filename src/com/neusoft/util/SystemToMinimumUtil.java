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
	
	public static void miniTray(final JFrame mf) {//窗口最小化到任务栏托盘

	    //托盘图标
	    ImageIcon trayImg = ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("VolumeInputFrame.close"), false);
		
		PopupMenu pop = new PopupMenu();//增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		MenuItem exit = new MenuItem("退出");
		
		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // 按下还原键
			
				tray.remove(trayIcon);
				mf.setVisible(true);
				mf.setExtendedState(JFrame.NORMAL);
				mf.toFront();
			}

	   });

		exit.addActionListener(new ActionListener() { // 按下退出键
		
			public void actionPerformed(ActionEvent e) {
			
				tray.remove(trayIcon);
				System.exit(0);
			
			}
		
		});

		pop.add(show);
		pop.add(exit);
		
		trayIcon = new TrayIcon(trayImg.getImage(), "牛 牛系统", pop);
		trayIcon.setImageAutoSize(true);
		
		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // 鼠标器双击事件
			
				if (e.getClickCount() == 2) {
				
					tray.remove(trayIcon); // 移去托盘图标
					mf.setVisible(true);
					mf.setExtendedState(JFrame.NORMAL); // 还原窗口
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
