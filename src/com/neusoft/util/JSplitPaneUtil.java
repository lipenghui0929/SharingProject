package com.neusoft.util;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;

import com.neusoft.action.ButtonForTableAction;
import com.neusoft.base.ColumndateUtil;
import com.neusoft.base.FsbTableModel;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;


public class JSplitPaneUtil {
	//��š�IMSI���ֻ��š�״̬(���á�������)���ֻ��š�λ��
	static Object[] columnTitle = { "���", "IMSI", "���պ�", "״̬(���á�������)", "������","����"};
/*	static Object[][] columnJsbdate = ColumndateUtil.listJsbArray(columnTitle.length);
	

	
	static JTable upTable = ViewSetingUtil.createTableView(false, columndate,
			columnTitle);
	static JTable middleTable = ViewSetingUtil.createTableView(false, columndate, 
			columnTitle);
	static JTable downTable = ViewSetingUtil.createTableView(false, columnJsbdate,
			columnTitle);*/
	
	/**
	 * ����Ҷ�ӽڵ����ɷָ�����(��������ļ��������ļ��ڵ�)
	 * @param node
	 * @param leftJPanel
	 * @param jSplitPane
	 * @return
	 */
	/*public static JSplitPane createJSplitPaneByLeafNode(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane){
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
	}*/
	public static JSplitPane createJSplitPaneByLeafNode(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane,JPanel queryPanel){
		AbstractTableModel tableModel= null;
		Object object = node.getUserObject();
		JTable downTable = null;
		if ("��������".equals(object.toString())) {
			//columnJsbdate = ColumndateUtil.listJsbArray(columnTitle.length);
		} else if ("��������".equals(object.toString())) {
			//columnJsbdate = ColumndateUtil.listFsbArray(columnTitle.length);
			tableModel = new FsbTableModel();
			//downTable = new JTable(tableModel);
			downTable = ViewSetingUtil.createTableView(tableModel);
		}

		
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		int childCount = node.getChildCount();
		if(childCount == 0 && node.isLeaf()){
			JScrollPane jScrollPaneU = new JScrollPane(queryPanel);
			jSplitPane3.setLeftComponent(jScrollPaneU);
		
			JScrollPane jScrollPaneD = new JScrollPane(downTable);
			jSplitPane3.setRightComponent(jScrollPaneD);
		}

		jSplitPane.setLeftComponent(leftJPanel);
		jSplitPane.setRightComponent(jSplitPane3);
		   
		
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
/*	public static JSplitPane createJSplitPaneByBranch(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane) {
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

	}*/

	/**
	 * ����ָ�
	 * @param newOrientation
	 * @return
	 */
	public static JSplitPane createLandscapeJSplitPane(int newOrientation) {
		JSplitPane jSplitPane = new JSplitPane(newOrientation);
		jSplitPane.setDividerSize(10);
		jSplitPane.setDividerLocation(50);
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
	
	//�����ť��ѯ����
	public static JSplitPane createJSplitPaneByButton(JScrollPane leftJPanel,JSplitPane jSplitPane,Jsb jsb,JPanel queryPanel){
		Object[][] qcolumndate = ColumndateUtil.listJsbArray(jsb,columnTitle.length);
		JTable qdownTable = ViewSetingUtil.createTableView(false, qcolumndate,
				columnTitle);
	    /*jSplitPane.setLeftComponent(leftJPanel);
	    jSplitPane.setRightComponent(new JScrollPane(qdownTable));*/
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JScrollPane jScrollPaneU = new JScrollPane(queryPanel);
		jSplitPane3.setLeftComponent(jScrollPaneU);
	
		JScrollPane jScrollPaneD = new JScrollPane(qdownTable);
		jSplitPane3.setRightComponent(jScrollPaneD);
		

		jSplitPane.setLeftComponent(leftJPanel);
		jSplitPane.setRightComponent(jSplitPane3);
		
		// �����м�ָ�����С
		jSplitPane.setDividerSize(10);
		// ���÷ָ���λ��
		jSplitPane.setDividerLocation(185);
		// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	
	public static JSplitPane createJSplitPaneByButtonForFsb(JScrollPane leftJPanel,JSplitPane jSplitPane,Fsb fsb,JPanel queryPanel){
		Object[][] qcolumndate = ColumndateUtil.listFsbArray(fsb,columnTitle.length);
		JTable qdownTable = ViewSetingUtil.createTableView(false, qcolumndate,
				columnTitle);
	    /*jSplitPane.setLeftComponent(leftJPanel);
	    jSplitPane.setRightComponent(new JScrollPane(qdownTable));*/
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		JScrollPane jScrollPaneU = new JScrollPane(queryPanel);
		jSplitPane3.setLeftComponent(jScrollPaneU);
	
		JScrollPane jScrollPaneD = new JScrollPane(qdownTable);
		jSplitPane3.setRightComponent(jScrollPaneD);
		

		jSplitPane.setLeftComponent(leftJPanel);
		jSplitPane.setRightComponent(jSplitPane3);
		
		// �����м�ָ�����С
		jSplitPane.setDividerSize(10);
		// ���÷ָ���λ��
		jSplitPane.setDividerLocation(185);
		// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	

}
