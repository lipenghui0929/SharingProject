package com.neusoft.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.neusoft.util.DateChooser;
import com.neusoft.view.VolumeInputFrame;

public class JTextFieldClickAction implements MouseListener{
	VolumeInputFrame volumeFrame = null;
	String dateString = "";
	JDialog frame;
	JTextField jTextField;
	public static void main(String[] args) {
		
	}
	public JTextFieldClickAction(VolumeInputFrame volumeInputFrame){
		volumeFrame = volumeInputFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (frame == null) {
//			frame = new JFrame("日期");
			frame = new JDialog(volumeFrame, "日期", true);
		}
    	frame.setSize(320, 230);
    	final DateChooser datePicker = new DateChooser();
    	jTextField = ((JTextField)e.getComponent());
//		System.out.println(((JTextField)e.getComponent()).getText());
        datePicker.addActionListener(new ActionListener() {// 事件捕获
                    public void actionPerformed(ActionEvent e) {
                    	dateString = datePicker.getSelectedDate();
                    	jTextField.setText(dateString);
                    	frame.dispose();
                    }
                });
//        frame.addWindowListener(new WindowAdapter() {  
//			public void windowClosing(WindowEvent e) { 
//				frame.dispose();
//				frame = null;
//			 }   
//			});
        frame.getContentPane().add(datePicker);
//        ViewSetingUtil.noChange(frame);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
		
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
