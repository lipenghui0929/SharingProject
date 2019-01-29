package com.neusoft.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.neusoft.base.ColumndateUtil;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Imsi;
import com.neusoft.ddmk.damin.Jsb;

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
			panel2.add(editButton);
		}else if ("imsi".equals(treeName)) {
			addActionForImsi();
			panel2.add(editButton);
		}
		
		panel2.add(deleteButton);
		
		
		
	}
	
	public void addActionForJsb(){
		
       editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				
				int i = table.getSelectedRow();
				
				Boolean select = (Boolean)model.getValueAt(i, 0);
				
				if(select){
					
					Jsb jsb = new Jsb();
					
					Integer id = (Integer)model.getValueAt(i, 1);
					jsb.setId(id);
					String imsi = (String)model.getValueAt(i, 2);
					jsb.setImsi(imsi);
					String sjh = (String)model.getValueAt(i, 3);
					jsb.setSjh(sjh);
					String mc = (String)model.getValueAt(i, 4);
					jsb.setMc(mc);
					String bjh = (String)model.getValueAt(i, 5);
					jsb.setBjh(bjh);
					String nr = (String)model.getValueAt(i, 6);
					jsb.setNr(nr);
					
					//调用修改
					Boolean modifyJsb = ColumndateUtil.modifyJsb(jsb);
					
					if(modifyJsb){
						JOptionPane.showMessageDialog(null, "修改成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "修改失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
      deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				//获取行数
				int i = table.getSelectedRow();
				Boolean select = (Boolean)model.getValueAt(i, 0);
				if(select){
					Integer id = (Integer)model.getValueAt(i, 1);
					Boolean removeJsb = ColumndateUtil.removeJsb(id);
					if(removeJsb){
						JOptionPane.showMessageDialog(null, "删除成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "删除失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
					System.out.println("deleteButtonJsb:"+id);
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("Booleanelect:"+select);
				
			}
		});
	}
	
	public void addActionForFsb(){
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				int i = table.getSelectedRow();

				Boolean select = (Boolean)model.getValueAt(i, 0);
				if(select){
					Fsb fsb = new Fsb();
					
					String id = (String)model.getValueAt(i, 1);
					fsb.setId(id);
					String imsi = (String)model.getValueAt(i, 2);
					fsb.setImsi(imsi);
					String sjh = (String)model.getValueAt(i, 3);
					fsb.setSjh(sjh);
					String mc = (String)model.getValueAt(i, 4);
					fsb.setMc(mc);
					String bjh = (String)model.getValueAt(i, 5);
					fsb.setBjh(bjh);
					String nr = (String)model.getValueAt(i, 6);
					fsb.setNr(nr);
					
					Boolean modifyFsb = ColumndateUtil.modifyFsb(fsb);
					
					if(modifyFsb){
						JOptionPane.showMessageDialog(null, "修改成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "修改失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
      deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				
				int i = table.getSelectedRow();
				Boolean select = (Boolean)model.getValueAt(i, 0);
				
				if(select){
					String id = (String)model.getValueAt(i, 1);
					Boolean removeFsb = ColumndateUtil.removeFsb(id);
					if(removeFsb){
						JOptionPane.showMessageDialog(null, "删除成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "删除失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
					System.out.println("deleteButtonFsb:"+id);
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("Booleanelect:"+select);
				
			}
		});
	}
	
   public void addActionForImsi(){
		
		editButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				int i = table.getSelectedRow();

				Boolean select = (Boolean)model.getValueAt(i, 0);
				if(select){
					Imsi imsiData = new Imsi();
					
					Integer id = (Integer)model.getValueAt(i, 1);
					imsiData.setId(id);
					String imei = (String)model.getValueAt(i, 2);
					imsiData.setImei(imei);
					String imsi = (String)model.getValueAt(i, 3);
					imsiData.setImsi(imsi);
					String haoma = (String)model.getValueAt(i, 4);
					imsiData.setHaoma(haoma);
					String ccid = (String)model.getValueAt(i, 5);
					imsiData.setCcid(ccid);
					String sn = (String)model.getValueAt(i, 6);
					imsiData.setSn(sn);
					String jx = (String)model.getValueAt(i, 7);
					imsiData.setJx(jx);
					String rjbb = (String)model.getValueAt(i, 8);
					imsiData.setRjbb(rjbb);
					String cs = (String)model.getValueAt(i, 9);
					imsiData.setCs(cs);
					String cmiit = (String)model.getValueAt(i, 10);
					imsiData.setCmiit(cmiit);
					String a = (String)model.getValueAt(i, 11);
					imsiData.setA(a);
					String b = (String)model.getValueAt(i, 12);
					imsiData.setB(b);
					String zc = (String)model.getValueAt(i, 13);
					imsiData.setZc(zc);
					String st = (String)model.getValueAt(i, 14);
					imsiData.setStruts(st);
					String bz1 = (String)model.getValueAt(i, 15);
					imsiData.setBeizhu1(bz1);
					String bz2 = (String)model.getValueAt(i, 16);
					imsiData.setBeizhu2(bz2);
					
					
					Boolean modifyFsb = ColumndateUtil.modifyImsi(imsiData);
					
					if(modifyFsb){
						JOptionPane.showMessageDialog(null, "修改成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "修改失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		
      deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				// TODO Auto-generated method stub
				
				int i = table.getSelectedRow();
				Boolean select = (Boolean)model.getValueAt(i, 0);
				
				if(select){
					Integer id = (Integer)model.getValueAt(i, 1);
					Boolean removeImsi = ColumndateUtil.removeImsi(id);
					if(removeImsi){
						JOptionPane.showMessageDialog(null, "删除成功！", "提示",JOptionPane.ERROR_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "删除失败！", "提示",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条数据进行操作！", "提示",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println("Booleanelect:"+select);
				
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
