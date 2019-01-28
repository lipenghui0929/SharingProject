package com.neusoft.util;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
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
import com.neusoft.base.ImsiTableModel;
import com.neusoft.base.JsbTableModel;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;


public class JSplitPaneUtil {

	/**
	 * 页码描述
	 */
	private static String pageDesContent = "当前为第currentPage页/共pageCont页";
	private static JLabel pageDesc = new JLabel();
	
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
	public static void getPageDesc(Page page){
		String desContent = pageDesContent;
		desContent = desContent.replaceAll("currentPage",Integer.toString(page.getPageNow()+1));
		desContent = desContent.replaceAll("pageCont",Integer.toString(page.getPageCount()));
		pageDesc.setText(desContent);
	}
	public static JSplitPane createJSplitPaneByLeafNode(DefaultMutableTreeNode node,JScrollPane leftJPanel,JSplitPane jSplitPane,JPanel queryPanel,JPanel pagingPanel,Page page){
		
		
		Object object = node.getUserObject();
		JTable downTable = null;
		if ("接收数据".equals(object.toString())) {
			downTable = ViewSetingUtil.createTableView(new JsbTableModel(page));
			
		} else if ("发送数据".equals(object.toString())) {
			downTable = ViewSetingUtil.createTableView(new FsbTableModel(page));
		}else if ("imsi".equals(object.toString())) {
			downTable = ViewSetingUtil.createTableView(new ImsiTableModel(page));
		}
		
		getPageDesc(page);
		pagingPanel.add(pageDesc);
		
		int columnCount = downTable.getColumnCount();
		//添加按钮
		ButtonForTableAction bt = new ButtonForTableAction(downTable,object.toString());
		if(columnCount > 0){
			TableColumn btnColumn = downTable.getColumnModel().getColumn(columnCount-1);
			btnColumn.setCellRenderer(bt);
			btnColumn.setCellEditor(bt);
		}
		
		//JSplitPane.VERTICAL_SPLIT
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPane2 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		int childCount = node.getChildCount();
		if(childCount == 0 && node.isLeaf()){
			
			//表格
			JScrollPane jScrollPaneD = new JScrollPane(downTable);
			
			//二级且套
			jSplitPane2.setLeftComponent(pagingPanel);
			//查询
			jSplitPane2.setRightComponent(queryPanel);
			jSplitPane2.setDividerLocation(0.5);
			
			//三级且套
			jSplitPane3.setLeftComponent(jSplitPane2);
			jSplitPane3.setRightComponent(jScrollPaneD);
			
			jSplitPane3.setDividerLocation(95);
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
	public static JSplitPane createJSplitPaneByButton(JScrollPane leftJPanel,JSplitPane jSplitPane,Jsb jsb,JPanel queryPanel,JPanel pagingPanel,Page page){
		
	    JTable downTable = ViewSetingUtil.createTableView(new JsbTableModel(jsb,page));
		
		int columnCount = downTable.getColumnCount();
		//添加按钮
		ButtonForTableAction bt = new ButtonForTableAction(downTable,"接收数据");
		if(columnCount > 0){
			TableColumn btnColumn = downTable.getColumnModel().getColumn(columnCount-1);
			btnColumn.setCellRenderer(bt);
			btnColumn.setCellEditor(bt);
		}
		//描述
		getPageDesc(page);
		pagingPanel.add(pageDesc);
		
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPane2 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		//表格
		JScrollPane jScrollPaneD = new JScrollPane(downTable);
		
		//二级且套
		jSplitPane2.setLeftComponent(pagingPanel);
		jSplitPane2.setRightComponent(queryPanel);
		jSplitPane2.setDividerLocation(0.5);
		
		//三级且套
		jSplitPane3.setLeftComponent(jSplitPane2);
		jSplitPane3.setRightComponent(jScrollPaneD);
		
		jSplitPane3.setDividerLocation(95);
		

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
	
	public static JSplitPane createJSplitPaneByButtonForFsb(JScrollPane leftJPanel,JSplitPane jSplitPane,Fsb fsb,JPanel queryPanel,JPanel pagingPanel,Page page){
		
		JTable downTable = ViewSetingUtil.createTableView(new FsbTableModel(fsb,page));
		
		int columnCount = downTable.getColumnCount();
		//添加按钮
		ButtonForTableAction bt = new ButtonForTableAction(downTable,"发送数据");
		if(columnCount > 0){
			TableColumn btnColumn = downTable.getColumnModel().getColumn(columnCount-1);
			btnColumn.setCellRenderer(bt);
			btnColumn.setCellEditor(bt);
		}
		
		JSplitPane jSplitPane3 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPane2 = createLandscapeJSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		//描述
		getPageDesc(page);
		pagingPanel.add(pageDesc);
		//表格
		JScrollPane jScrollPaneD = new JScrollPane(downTable);
		
		//二级且套
		jSplitPane2.setLeftComponent(pagingPanel);
		jSplitPane2.setRightComponent(queryPanel);
		jSplitPane2.setDividerLocation(0.5);
		
		//三级且套
		jSplitPane3.setLeftComponent(jSplitPane2);
		jSplitPane3.setRightComponent(jScrollPaneD);
		
		jSplitPane3.setDividerLocation(95);
		

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
