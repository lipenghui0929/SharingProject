package com.neusoft.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTree;

import com.neusoft.view.MainFrame;
import com.neusoft.view.VolumeInputFrame;

public class ButtonClickAction implements ActionListener {
	MainFrame frame;
	JTree tree;
	String nodeName;

	VolumeInputFrame volumeInputFrame = null;

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public ButtonClickAction() {

	}

	public ButtonClickAction(String nodeName) {
		this.nodeName = nodeName;
	}

	public ButtonClickAction(MainFrame frame) {
		this.frame = frame;
		// System.out.println("****" +
		// frame.tree.getLastSelectedPathComponent());
		// System.out.println("****" + frame.tree.getLeadSelectionPath());
		System.out.println("****" + frame.tree);
		System.out.println("****" + frame.tree);
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("1=" + frame);
		System.out.println("2=" + frame.tree);
		System.out.println("3=" + frame.tree.getLeadSelectionPath());
		System.out.println("4=" + frame.tree.getLeadSelectionPath().toString());
		System.out.println("****" + frame.tree.getLastSelectedPathComponent());
		// String title = frame.tree.getLeadSelectionPath().toString();
		//
		// System.out.println(title);
		if (volumeInputFrame == null) {
			volumeInputFrame = new VolumeInputFrame("");
			volumeInputFrame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					volumeInputFrame.dispose();
					volumeInputFrame = null;
				}
			});
		} else {
			volumeInputFrame.setVisible(true);
		}
		System.out.println("用于执行了单击操作" + frame.tree.getLeadSelectionPath());

	}

}
