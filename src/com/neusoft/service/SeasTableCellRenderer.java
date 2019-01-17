package com.neusoft.service;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class SeasTableCellRenderer extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		setForeground(Color.BLUE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setBackground(Color.YELLOW);
		table.setBackground(Color.YELLOW);
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
