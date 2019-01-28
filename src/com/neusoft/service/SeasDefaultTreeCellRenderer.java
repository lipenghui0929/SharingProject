package com.neusoft.service;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.neusoft.util.ImageUtil;
import com.neusoft.util.PropertiesUtil;

/**
 * �Զ�����������,������ÿ���ڵ����óɲ�ͬ��ͼ��
 * 
 * @author chenzhenhua
 *
 */
public class SeasDefaultTreeCellRenderer extends DefaultTreeCellRenderer {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ��д����DefaultTreeCellRenderer�ķ���
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		// ִ�и���ԭ�Ͳ���
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		setText(value.toString());

		if (sel) {
			setForeground(getTextSelectionColor());
		} else {
			setForeground(getTextNonSelectionColor());
		}

		// �õ�ÿ���ڵ��TreeNode
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		// �õ�ÿ���ڵ��text
		String str = node.toString();

		// �ж����ĸ��ı��Ľڵ����ö�Ӧ��ֵ����������ڵ㴫�����һ��ʵ��,����Ը���ʵ�������һ��������������ʾ��Ӧ��ͼ�꣩
		if ("��������".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("tree.SeasDefaultTreeCellRenderer"),true));
		} else if ("��������".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.volumeTable"),true));
		} else if ("��������".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.fileTable"),true));
		} else if ("imsi".equals(str)) {
			this.setIcon(ImageUtil
					.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.electronicalFileTab"),true));
		} else {
			// ���õ�������ڵ��ͼ��
			this.setIcon(ImageUtil
					.getImageIcon(PropertiesUtil.prop.getProperty("tree.archiveCategory"),true));
		}
		return this;
	}
}
