package com.neusoft.base;

import javax.swing.table.AbstractTableModel;

import com.neusoft.ddmk.damin.Jsb;

public class JsbTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 2564872464841573178L;
	
	// �����ͷ����
	String[] head = { "ѡ��","ID", "IMSI", "���պ�", "״̬(���á�������)", "������","����","����"};
	
	Object[][] data = null;

	// ������ÿһ�е���������
	/*Class[] typeArray = { Boolean.class, Object.class, Object.class,
			Object.class, Object.class, Object.class };*/
   

	public JsbTableModel() {
		data = ColumndateUtil.listJsbArray(head.length);
	}
	
	public JsbTableModel(Jsb jsb) {
		data = ColumndateUtil.listJsbArray(jsb,head.length);
	}

	// ��ñ�������
	@Override
	public int getColumnCount() {
		return head.length;
	}

	// ��ñ�������
	@Override
	public int getRowCount() {
		return data.length;
	}

	// ��ñ���������
	@Override
	public String getColumnName(int column) {
		return head[column];
	}
 
	// ��ñ��ĵ�Ԫ�������
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
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
		 System.out.println("getColumnClass"+c);
	     return getValueAt(0, c).getClass();
	 }

}
