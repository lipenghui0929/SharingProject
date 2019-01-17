package com.neusoft.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageUtil {
	
	/**
	 * 根据图片的路径得到ImageIcon对象
	 * @param imgPath 图片路径
	 * @param isCondense 是否把图片压缩
	 * @return
	 */
	public static ImageIcon getImageIcon(String imgPath,boolean isCondense) {
		ImageIcon imageIcon = new ImageIcon(imgPath);
		if (isCondense) {
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(20, 20,
					Image.SCALE_DEFAULT));// 在程序中将图片压缩
		}
		return imageIcon;
		
	}

}
