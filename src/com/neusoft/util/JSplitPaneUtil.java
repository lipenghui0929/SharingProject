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
	//序号、IMSI、手机号、状态(可用、不可用)、手机号、位置
	static Object[] columnTitle = { "序号", "IMSI", "接收号", "状态(可用、不可用)", "本机号","内容"};
/*	static Object[][] columnJsbdate = ColumndateUtil.listJsbArray(columnTitle.length);
	

	
	static JTable upTable = ViewSetingUtil.createTableView(false, columndate,
			columnTitle);
	static JTable middleTable = ViewSetingUtil.createTableView(false, columndate, 
			columnTitle);
	static JTable downTable = ViewSetingUtil.createTableView(false, columnJsbdate,
			columnTitle);*/
	
	/**
	 * 根据叶子节点生成分割容器(点击案卷，文件，电子文件节点)
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
		// 设置中间分割条大小
		jSplitPane.setDividerSize(10);
		// 设置分割条位置
		jSplitPane.setDividerLocation(185);
		// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}*/
	public static JSplitPane createJSplitPaneByLeafNode(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane,JPanel queryPanel){
		AbstractTableModel tableModel= null;
		Object object = node.getUserObject();
		JTable downTable = null;
		if ("接收数据".equals(object.toString())) {
			//columnJsbdate = ColumndateUtil.listJsbArray(columnTitle.length);
		} else if ("发送数据".equals(object.toString())) {
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
		   
		
		// 设置中间分割条大小
		jSplitPane.setDividerSize(10);
		// 设置分割条位置
		jSplitPane.setDividerLocation(185);
		// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	
	/**
	 * 根据分枝节点生成分割容器(比如点击文书档案，声像档案节点)
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
		// 设置中间分割条大小
				jSplitPane.setDividerSize(10);
				// 设置分割条位置
				jSplitPane.setDividerLocation(185);
				// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
				jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;

	}*/

	/**
	 * 横向分割
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
	 * 纵向分割
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
	 * 生成默认的分割容器
	 * @param leftJPanel
	 * @param rightJPanel
	 * @return
	 */
	public static JSplitPane createDefaultJSplitPane(JScrollPane leftJPanel,JScrollPane rightJPanel) {
		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftJPanel, rightJPanel);
		// 设置中间分割条大小
		jSplitPane.setDividerSize(10);
		// 设置分割条位置
		jSplitPane.setDividerLocation(185);
		// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
		
	}
	
	//点击按钮查询数据
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
		
		// 设置中间分割条大小
		jSplitPane.setDividerSize(10);
		// 设置分割条位置
		jSplitPane.setDividerLocation(185);
		// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
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
		
		// 设置中间分割条大小
		jSplitPane.setDividerSize(10);
		// 设置分割条位置
		jSplitPane.setDividerLocation(185);
		// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
		jSplitPane.setOneTouchExpandable(true);
		return jSplitPane;
	}
	

}
