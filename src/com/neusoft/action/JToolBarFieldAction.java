package com.neusoft.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.service.FsbService;
import com.neusoft.service.impl.FsbServiceImpl;

public class JToolBarFieldAction {
	
	private FsbService fsbService = new FsbServiceImpl();

	public void UpLoadFile(JPanel jPanel) {
		JToolBar jToolBar = new JToolBar(); 
		JLabel jl = new JLabel("     ��ѡ��");// ����һ��Label��ǩ
		jl.setHorizontalAlignment(SwingConstants.LEFT);// ��ʽ�������־���
		jPanel.add("North", jl);// ����ǩ��ӵ�������
		jPanel.add("North", jToolBar);
		JButton developer = new JButton("�ϴ��ļ�");
		developer.setHorizontalAlignment(SwingConstants.CENTER);
		jToolBar.add(developer);// �ϴ��ļ���ť��ӵ�����
		jPanel.add("North", jToolBar);
		developer.addMouseListener(new MouseAdapter() { // ���������¼�
			public void mouseClicked(MouseEvent event) {
				eventOnImport(new JButton());
			}
		}); // �ļ��ϴ�����
	}
	
	public  void eventOnImport(JButton developer) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		/** �����ļ����� * */
		FileNameExtensionFilter filter = new FileNameExtensionFilter("war",
		"xml", "txt", "doc", "docx");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(developer);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			/** �õ�ѡ����ļ�* */
			File[] arrfiles = chooser.getSelectedFiles();
				if (arrfiles == null || arrfiles.length == 0) {
				return;
			}
			
			FileInputStream in = null;
			InputStreamReader inReader = null;
			BufferedReader read = null;
			Fsb fsb = null;
			try {
				for (File f : arrfiles) {
					in = new FileInputStream(f);
					inReader = new InputStreamReader(in,"utf-8");
					read = new BufferedReader(inReader);

					while (true) {
						fsb = new Fsb();
						
						String readLine = read.readLine();
						if(readLine == null){
							break;
						}
						System.out.println(readLine);
						String[] split = readLine.split("&&&");
						System.out.println(split.length);
						System.out.println(split[0]);
						System.out.println(split[1]);
						System.out.println(split[2]);
						
						fsb.setSjh(split[0].trim());
						fsb.setXx(split[1].trim());
						fsb.setBjh(split[2].trim());
						
						//��������
						fsbService.saveFsb(fsb);
					}
				}
				JOptionPane.showMessageDialog(null, "�ϴ��ɹ���", "��ʾ",
				JOptionPane.INFORMATION_MESSAGE);
			
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "�ϴ�ʧ�ܣ�", "��ʾ",
				JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "�ϴ�ʧ�ܣ�", "��ʾ",
				JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}finally {
				if(read != null){
					try {
						read.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
