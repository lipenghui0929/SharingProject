package com.neusoft.service;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.neusoft.util.ImageUtil;
import com.neusoft.util.PropertiesUtil;

/**
 * 自定义树描述类,将树的每个节点设置成不同的图标
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
	 * 重写父类DefaultTreeCellRenderer的方法
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		// 执行父类原型操作
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);

		setText(value.toString());

		if (sel) {
			setForeground(getTextSelectionColor());
		} else {
			setForeground(getTextNonSelectionColor());
		}

		// 得到每个节点的TreeNode
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		// 得到每个节点的text
		String str = node.toString();

		// 判断是哪个文本的节点设置对应的值（这里如果节点传入的是一个实体,则可以根据实体里面的一个类型属性来显示对应的图标）
		if ("档案管理".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("tree.SeasDefaultTreeCellRenderer"),true));
		} else if ("接收数据".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.volumeTable"),true));
		} else if ("发送数据".equals(str)) {
			this.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.fileTable"),true));
		} else if ("imsi".equals(str)) {
			this.setIcon(ImageUtil
					.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.electronicalFileTab"),true));
		} else {
			// 设置档案门类节点的图标
			this.setIcon(ImageUtil
					.getImageIcon(PropertiesUtil.prop.getProperty("tree.archiveCategory"),true));
		}
		return this;
	}
}
