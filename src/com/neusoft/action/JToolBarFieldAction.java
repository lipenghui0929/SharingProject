package com.neusoft.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
		JLabel jl = new JLabel("     请选择：");// 创建一个Label标签
		jl.setHorizontalAlignment(SwingConstants.LEFT);// 样式，让文字居中
		jPanel.add("North", jl);// 将标签添加到容器中
		jPanel.add("North", jToolBar);
		JButton developer = new JButton("上传文件");
		developer.setHorizontalAlignment(SwingConstants.CENTER);
		jToolBar.add(developer);// 上传文件按钮添加到容器
		jPanel.add("North", jToolBar);
		developer.addMouseListener(new MouseAdapter() { // 添加鼠标点击事件
			public void mouseClicked(MouseEvent event) {
				eventOnImport(new JButton());
			}
		}); // 文件上传功能
	}
	
	public  void eventOnImport(JButton developer) {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		/** 过滤文件类型 * */
		FileNameExtensionFilter filter = new FileNameExtensionFilter("war",
		"xml", "txt", "doc", "docx");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(developer);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			/** 得到选择的文件* */
			File[] arrfiles = chooser.getSelectedFiles();
				if (arrfiles == null || arrfiles.length == 0) {
				return;
			}
			
			List<Fsb> fsbs = new ArrayList<Fsb>();
			FileInputStream in = null;
			InputStreamReader inReader = null;
			BufferedReader read = null;
			try {
				for (File f : arrfiles) {
					in = new FileInputStream(f);
					inReader = new InputStreamReader(in,"utf-8");
					read = new BufferedReader(inReader);

					while (true) {
						
						Fsb fsb = new Fsb();
						
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
						
						
						fsb.setId(UUID.randomUUID().toString());
						fsb.setSjh(split[0].trim());
						fsb.setXx(split[1].trim());
						fsb.setBjh(split[2].trim());
						
						fsbs.add(fsb);
						
					}
					
					//保存数据
					fsbService.saveFsbs(fsbs);
				}
				JOptionPane.showMessageDialog(null, "上传成功！", "提示",
				JOptionPane.INFORMATION_MESSAGE);
			
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "上传失败！", "提示",
				JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "上传失败！", "提示",
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
