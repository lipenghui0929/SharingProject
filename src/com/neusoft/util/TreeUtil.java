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
	 * 展开一棵树
	 * 
	 * @param tree
	 */
	public static void expandTree(JTree tree) {
		// 根节点
		TreeNode node = (TreeNode) tree.getModel().getRoot();
		expandAll(tree, new TreePath(node), true);
	}

	/**
	 * 完全展开一棵树或关闭一棵树
	 * 
	 * @param tree
	 *            JTree
	 * @param parent
	 *            父节点
	 * @param expand
	 *            true 表示展开，false 表示关闭
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
