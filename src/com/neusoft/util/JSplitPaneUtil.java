package com.neusoft.util;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultMutableTreeNode;

public class JSplitPaneUtil {
	
	static Object[] columnTitle = { "������", "��������", "����ŵ���", "��ŵص�", "�鵵����" };
	static Object[][] columndate = {
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" },
			{ "aaaa", "www", "eeee", "www", "eeee" } };
	
	static JTable upTable = ViewSetingUtil.createTableView(true, columndate,
			columnTitle);
	static JTable middleTable = ViewSetingUtil.createTableView(true, columndate,
			columnTitle);
	static JTable downTable = ViewSetingUtil.createTableView(true, columndate,
			columnTitle);
	
	/**
	 * ����Ҷ�ӽڵ����ɷָ�����(��������ļ��������ļ��ڵ�)
	 * @param node
	 * @param leftJPanel
	 * @param jSplitPane
	 * @return
	 */
	public static JSplitPane createJSplitPaneByLeafNode(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane){
		int childCount = node.getChildCount();
		if(childCount == 0 && node.isLeaf()){
			jSplitPane.setLeftComponent(leftJPanel);
			jSplitPane.setRightComponent(new JScrollPane(downTable));
		}
		// �����м�ָ�����С
				jSplitPane.setDividerSize(10);
				// ���÷ָ���λ��
				jSplitPane.setDividerLocation(185);
				// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
				jSplitPane.setOneTouchExpandable(true);
				return jSplitPane;
	}
	
	/**
	 * ���ݷ�֦�ڵ����ɷָ�����(���������鵵�������񵵰��ڵ�)
	 * @param node
	 * @param leftJPanel
	 * @param jSplitPane
	 * @return
	 */
	public static JSplitPane createJSplitPaneByBranch(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane) {
		int childCount = node.getChildCount();
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPane2 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		if (childCount == 3) {
			for (int i = 0; i < childCount; i++) {
				if (i == 0) {
					JScrollPane jScrollPane = new JScrollPane(upTable);
					jSplitPane3.setLeftComponent(jScrollPane);
				}else if(i == 1){
					JScrollPane jScrollPane = new JScrollPane(middleTable);
					jSplitPane3.setRightComponent(jScrollPane);
				}else if((i == childCount - 1)){
					JScrollPane jScrollPane = new JScrollPane(downTable);
					jSplitPane2.setLeftComponent(jScrollPane);
					jSplitPane2.setRightComponent(jSplitPane3);
				}
			}
//			jSplitPane = createVerticalJSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			jSplitPane.setLeftComponent(leftJPanel);
			jSplitPane.setRightComponent(jSplitPane2);
		}else if (childCount == 2) {
			for (int i = 0; i < childCount; i++) {
				if (i == 0) {
					JScrollPane jScrollPane = new JScrollPane(middleTable);
					jSplitPane3.setLeftComponent(jScrollPane);
				}else if((i == childCount - 1)){
					JScrollPane jScrollPane = new JScrollPane(downTable);
					jSplitPane3.setRightComponent(jScrollPane);
				}
			}
//			jSplitPane = createVerticalJSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			jSplitPane.setLeftComponent(leftJPanel);
			jSplitPane.setRightComponent(jSplitPane3);
		}
		// �����м�ָ�����С
				jSplitPane.setDividerSize(10);
				// ���÷ָ���λ��
				jSplitPane.setDividerLocation(185);
				// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
				jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;

	}

	/**
	 * ����ָ�
	 * @param newOrientation
	 * @return
	 */
	public static JSplitPane createLandscapeJSplitPane(int newOrientation) {
		JSplitPane jSplitPane = new JSplitPane(newOrientation);
		jSplitPane.setDividerSize(10);
		jSplitPane.setDividerLocation(200);
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	
	/**
	 * ����ָ�
	 * @param newOrientation
	 * @return
	 */
	public static JSplitPane createVerticalJSplitPane(int newOrientation) {
		JSplitPane jSplitPane = new JSplitPane(newOrientation);
		jSplitPane.setDividerSize(10);
		jSplitPane.setDividerLocation(185);
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	
	
	/**
	 * ����Ĭ�ϵķָ�����
	 * @param leftJPanel
	 * @param rightJPanel
	 * @return
	 */
	public static JSplitPane createDefaultJSplitPane(JScrollPane leftJPanel,JScrollPane rightJPanel) {
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftJPanel, rightJPanel);
		// �����м�ָ�����С
		jSplitPane.setDividerSize(10);
		// ���÷ָ���λ��
		jSplitPane.setDividerLocation(185);
		// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
		
	}

}
