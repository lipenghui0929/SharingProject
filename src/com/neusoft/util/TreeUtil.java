package com.neusoft.util;

import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreeUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * չ��һ����
	 * 
	 * @param tree
	 */
	public static void expandTree(JTree tree) {
		// ���ڵ�
		TreeNode node = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(node), true);
	}

	/**
	 * ��ȫչ��һ������ر�һ����
	 * 
	 * @param tree
	 *            JTree
	 * @param parent
	 *            ���ڵ�
	 * @param expand
	 *            true ��ʾչ����false ��ʾ�ر�
	 */
	public static void expandAll(JTree tree, TreePath parent, boolean expand) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();

		if (node.getChildCount() > 0) {
			for (Enumeration e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAll(tree, path, expand);
			}
		}
		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

}
