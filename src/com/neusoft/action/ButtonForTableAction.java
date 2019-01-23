package com.neusoft.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.neusoft.base.ColumndateUtil;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.FsbService;
import com.neusoft.service.impl.FsbServiceImpl;

public class ButtonForTableAction extends AbstractCellEditor implements TableCellRenderer, TableCellEditor{

	private static final long serialVersionUID = 7989563484682936260L;

	JTable table = null;
	AbstractTableModel model = null;
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton editButton = new JButton("修改");
	JButton deleteButton = new JButton("删除");

	public ButtonForTableAction(JTable tableAndModel,String treeName) {
		super();
//		JButton viewButton2 = new JButton(new AbstractAction("yy") {
//			
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Viewing");
//			}
//		});
//		JButton editButton2 = new JButton(new AbstractAction("edit2") {
//			;
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(null, "Editing");
//			}
//		});
		//panel1.setOpaque(true);

		//panel2.setOpaque(true);
		
		this.table = tableAndModel;
		model = (AbstractTableModel) table.getModel();
		
		if ("接收数据".equals(treeName)) {
			addActionForJsb();
			
		} else if ("发送数据".equals(treeName)) {
			addActionForFsb();
		}
		
		panel2.add(editButton);
		panel2.add(deleteButton);
		//editButton.setBackground(Color.red);
		
		
		
	}
	
	public void addActionForJsb(){
		
      editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				Jsb jsb = new Jsb();
				int i = table.getSelectedRow();
				//获取列数
				int columnCount = model.getColumnCount(); 
				/*for (int j = 0; j < columnCount; j++) {
					String id = (String)model.getValueAt(i, j);
				}*/
				
				/*Boolean removeFsb = fsbService.removeFsb(id);
				System.out.println("deleteButton:"+id);*/
				//JOptionPane.showMessageDialog(null, s);
				//调用修改
				Boolean modifyFsb = ColumndateUtil.modifyJsb(jsb);
			}
		});
		
      deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				
				int i = table.getSelectedRow();
				String id = (String)model.getValueAt(i, 1);
				Boolean removeFsb = ColumndateUtil.removeJsb(id);
				System.out.println("deleteButton:"+id);
			}
		});
	}
	
	public void addActionForFsb(){
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				Fsb fsb = new Fsb();
				int i = table.getSelectedRow();
				//获取列数
				int columnCount = model.getColumnCount(); 
				for (int j = 0; j < columnCount; j++) {
					String id = (String)model.getValueAt(i, j);
				}
				
				/*Boolean removeFsb = fsbService.removeFsb(id);
				System.out.println("deleteButton:"+id);*/
				//JOptionPane.showMessageDialog(null, s);
				//调用修改
				Boolean modifyFsb = ColumndateUtil.modifyFsb(fsb);
			}
		});
		
      deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				
				int i = table.getSelectedRow();
				String id = (String)model.getValueAt(i, 1);
				Boolean removeFsb = ColumndateUtil.removeFsb(id);
				System.out.println("deleteButton:"+id);
			}
		});
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		panel2.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
		
		
		
	//	panel2.setBackground(table.getBackground());
		return panel2;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		panel2.setBackground(table.getSelectionBackground());
		return panel2;
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}
}
