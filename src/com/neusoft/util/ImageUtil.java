package com.neusoft.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	
	/**
	 * ����ͼƬ��·���õ�ImageIcon����
	 * @param imgPath ͼƬ·��
	 * @param isCondense �Ƿ��ͼƬѹ��
	 * @return
	 */
	public static ImageIcon getImageIcon(String imgPath,boolean isCondense) {
		ImageIcon imageIcon = new ImageIcon(imgPath);
		if (isCondense) {
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20,
					Image.SCALE_DEFAULT));// �ڳ����н�ͼƬѹ��
		}
		return imageIcon;
		
	}

}
