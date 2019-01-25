package com.neusoft.base;

import javax.swing.table.AbstractTableModel;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Page;

public class FsbTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2564872464841573178L;
	
	// �����ͷ����
	String[] head = { "ѡ��","ID", "IMSI", "���պ�", "״̬(���á�������)", "������","����","����"};
	
	Object[][] data = null;

	// ������ÿһ�е���������
	/*Class[] typeArray = { Boolean.class, Object.class, Object.class,
			Object.class, Object.class, Object.class };*/


	public FsbTableModel(Page page) {
		data = ColumndateUtil.listFsbArray(head.length,page);
	}
	
	public FsbTableModel(Fsb fsb,Page page) {
		data = ColumndateUtil.listFsbArray(fsb,head.length,page);
	}
	
	// ��ñ�������
	@Override
	public int getColumnCount() {
		return head.length;
	}

	// ��ñ�������
	@Override
	public int getRowCount() {
		
		if(data != null){
			return data.length;
		}
		return 0;
	}

	// ��ñ���������
	@Override
	public String getColumnName(int column) {
		return head[column];
	}
 
	// ��ñ��ĵ�Ԫ�������
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data != null){
			return data[rowIndex][columnIndex];
		}
		return data;
	}
 
	// ʹ�����пɱ༭��
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if (columnIndex == 1) {
	        return false;
	    } else {
	        return true;
	    }
	}
 
	// �滻��Ԫ���ֵ
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	 public Class getColumnClass(int c) {
		 //System.out.println("getColumnClass"+c);
		 if(data != null && ((c == 0) || (c == head.length -1))){
			 return getValueAt(0, c).getClass();
		 }
		 return null;
	 }

}
