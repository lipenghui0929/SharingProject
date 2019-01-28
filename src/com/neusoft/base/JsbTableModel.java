package com.neusoft.base;

import javax.swing.table.AbstractTableModel;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;

public class JsbTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2564872464841573178L;
	
	// 定义表头数据
	String[] head = { "选择","ID", "IMSI", "接收号", "状态(可用、不可用)", "本机号","内容","操作"};
	
	Object[][] data = null;

	// 定义表格每一列的数据类型
	Class[] typeArray = { Boolean.class, String.class, String.class,
			String.class, String.class, String.class,String.class,Object.class };
   

	public JsbTableModel(Page page) {
		data = ColumndateUtil.listJsbArray(head.length,page);
	}
	
	public JsbTableModel(Jsb jsb,Page page) {
		data = ColumndateUtil.listJsbArray(jsb,head.length,page);
	}

	// 获得表格的列数
	@Override
	public int getColumnCount() {
		return head.length;
	}

	// 获得表格的行数
	@Override
	public int getRowCount() {

		if(data != null){
			return data.length;
		}
		return 0;
	}

	// 获得表格的列名称
	@Override
	public String getColumnName(int column) {
		return head[column];
	}
 
	// 获得表格的单元格的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data != null){
			return data[rowIndex][columnIndex];
		}
		return data;
	}
 
	// 使表格具有可编辑性
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 1) {
	        return false;
	    } else {
	        return true;
	    }
	}
 
	// 替换单元格的值
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	 public Class getColumnClass(int c) {
	
		 /*if(data != null && (getRowCount() != 0)){
			 Object valueAt = getValueAt(0, c);
			 if(valueAt != null){
				 return valueAt.getClass();
			 }
		 }*/
		 return typeArray[c];
	    
	 }

}
