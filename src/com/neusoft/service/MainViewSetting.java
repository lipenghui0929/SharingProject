package com.neusoft.service;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Document;

import com.neusoft.util.ImageUtil;
import com.neusoft.util.PropertiesUtil;
import com.neusoft.util.ViewSetingUtil;
import com.neusoft.util.XMLUtil;
import com.neusoft.view.FieldSettingFrame;
import com.neusoft.view.MainFrame;

/**
 * ������Ĳ�����
 * @author chenzhenhua
 *
 */
public class MainViewSetting {
	
	public FieldSettingFrame fieldSettingFrame = null;
	MainFrame mainFrame;
	JMenu skinMenu;
	JMenuItem defaultSkin;
	JMenuItem blueSkin;
	JMenuItem blackSkin;
	JMenuItem greySkin;
	JMenuItem yellowSkin;
	JMenuItem greenSkin;
	JMenuItem purpleSkin;
	JMenuItem officeSilver2007Skin;
	JMenuItem aharaSkin;
	static final String jMenuChangeSkinText;
	static final String jMenuImportTempletText;
	static final String jMenuExitText;
	boolean isSuccess;
	JMenuBar jMenuBar = new JMenuBar();
	JMenu importTemplet;
	JMenu exit;
	static final Properties prop;
	static{
		prop = PropertiesUtil.prop;
		jMenuChangeSkinText = prop.getProperty("MainFrame.JMenu.changeSkin");
		jMenuImportTempletText = prop
				.getProperty("MainFrame.JMenu.importTemplet");
		jMenuExitText = prop.getProperty("MainFrame.JMenu.exit");
	}
	
	/**
	 * ����Ƿ�ģ��ʱ������ʾ��Ϣ
	 */
	private void showMessageDialog() {
		JOptionPane.showMessageDialog(null, prop.getProperty("MainFrame.importTemplet.showMessage"));
	}
	
	/**
	 * ����˳�ϵͳʱ������ʾ����
	 * @param frame
	 */
	public void setExitMenuBar(JFrame frame) {
		addExitMenu(frame);
		// �˳�ϵͳ�ĵ���¼�
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ViewSetingUtil.exitSystemWarn();
			}
		});
	}

	/**
	 * ��������ӵ���ģ��Ĳ˵���
	 * 
	 * @param frame
	 */
	public void setImportTempletMenuBar(final MainFrame frame) {
		addImportTempletMenu(frame);
		// ����ģ��ĵ���¼�
		importTemplet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser jFileChooser = ViewSetingUtil
						.getFileChooserInstance();
				int result = jFileChooser.showOpenDialog(null);//
				// �ж��Ƿ�Ϊ�򿪵İ�ť
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jFileChooser.getSelectedFile();// ȡ��ѡ�е��ļ�
					String filePath = selectedFile.getPath(); // ѡ���ļ���·��
					Document document = XMLUtil.getDocument(filePath);
					if (document != null) {
						String archiveCategoryName = XMLUtil
								.getArchiveCategoryName(document);// ��������
						List<String> levelsName = XMLUtil
								.getLevelsName(document);
						if (levelsName != null) {
							Map<String, Map<String, String>> totalFieldsInfo = FieldViewSetting
									.getTotalFieldsInfo(document, levelsName);
							if (fieldSettingFrame == null) {
								fieldSettingFrame = new FieldSettingFrame(
										archiveCategoryName, levelsName,
										totalFieldsInfo,frame,getContainer());
							}else {
								fieldSettingFrame.dispose();
								fieldSettingFrame = null;
								fieldSettingFrame = new FieldSettingFrame(
										archiveCategoryName, levelsName,
										totalFieldsInfo,frame,getContainer());
//								fieldSettingFrame.setVisible(true);
							}
							fieldSettingFrame.addWindowListener(new WindowAdapter() {  
								public void windowClosing(WindowEvent e) { 
									fieldSettingFrame.dispose();
									fieldSettingFrame = null;
								 }   
								});
						} else {
							showMessageDialog();
						}
					} else {
						showMessageDialog();
					}
				}

			}

		});
	}
	
	/**
	 * ��ӵ���ģ��Ĳ˵���,���Ҹ��˵�������Сͼ��
	 * 
	 * @param frame
	 */
	public void addImportTempletMenu(JFrame frame) {
		importTemplet = new JMenu(jMenuImportTempletText);
		importTemplet.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.ImportTempletMenu.image"),true));
		jMenuBar.add(importTemplet);
		frame.setJMenuBar(jMenuBar);
	}

	/**
	 * ����˳��Ĳ˵�����Ҹ��˵�������Сͼ��
	 * 
	 * @param frame
	 */
	public void addExitMenu(JFrame frame) {
		exit = new JMenu(jMenuExitText);
		exit.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.ExitMenu.image"),true));
		jMenuBar.add(exit);
		jMenuBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		// frame.setJMenuBar(jMenuBar);

	}
	
	/**
	 * ��ӻ�Ƥ���Ĳ˵���,���Ҹ��˵�������Сͼ��
	 * 
	 * @param frame
	 */
	public void addSkinMenu(JFrame frame) {
		skinMenu = new JMenu(jMenuChangeSkinText);
		skinMenu.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.changeSkin"),true));
		defaultSkin = new JMenuItem("Ĭ��");
		defaultSkin.setIcon(ImageUtil
				.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.defaultSkin"),true));
		blueSkin = new JMenuItem("ǳ��ɫ");
		blueSkin.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.blueSkin"),true));
		greySkin = new JMenuItem("ǳ��ɫ");
		greySkin.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.greySkin"),true));
		yellowSkin = new JMenuItem("ǳ��ɫ");
		yellowSkin
				.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.yellowSkin"),true));
		greenSkin = new JMenuItem("ǳ��ɫ");
		greenSkin.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.greenSkin"),true));
		purpleSkin = new JMenuItem("ǳ��ɫ");
		purpleSkin
				.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.purpleSkin"),true));
		blackSkin = new JMenuItem("��ɫ");
		blackSkin.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.blackSkin"),true));
		aharaSkin = new JMenuItem("����ɫ");
		aharaSkin.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.aharaSkin"),true));
		officeSilver2007Skin = new JMenuItem("����ɫ");
		officeSilver2007Skin.setIcon(ImageUtil
				.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.SkinMenu.officeSilver2007Skin"),true));
		skinMenu.add(defaultSkin);
		skinMenu.add(blueSkin);
		skinMenu.add(greySkin);
		skinMenu.add(yellowSkin);
		skinMenu.add(greenSkin);
		skinMenu.add(purpleSkin);
		skinMenu.add(blackSkin);
		skinMenu.add(officeSilver2007Skin);
		skinMenu.add(aharaSkin);
		jMenuBar.add(skinMenu);
		frame.setJMenuBar(jMenuBar);
	}
	
	/**
	 * �л���ͬƤ��
	 * @param frame
	 */
	public void setSkinMenu(final MainFrame frame) {
		addSkinMenu(frame);
//		mainFrame = frame;
		// Ĭ��Ƥ���ĵ���¼�
		defaultSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin", "default");
				if (isSuccess) {
					ViewSetingUtil.setSkin("default");
//					frame.loadTree();
					
				}
			}
		});
		// ǳ��ɫƤ���ĵ���¼�
		blueSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin",
						"BusinessBlueSteelSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("BusinessBlueSteelSkin");
					frame.loadTree();
				}
			}
		});
		// ǳ��ɫƤ���ĵ���¼�
		greySkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin", "CremeSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("CremeSkin");
					frame.loadTree();
				}
			}
		});

		// ǳ��ɫƤ���ĵ���¼�
		yellowSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin",
						"FieldOfWheatSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("FieldOfWheatSkin");
					frame.loadTree();
				}
			}
		});

		// ǳ��ɫƤ���ĵ���¼�
		greenSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin",
						"GreenMagicSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("GreenMagicSkin");
					frame.loadTree();
				}
			}
		});

		// ǳ��ɫƤ���ĵ���¼�
		purpleSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil
						.setSkinProper("skin", "MistAquaSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("MistAquaSkin");
					frame.loadTree();
				}
			}
		});

		// ��ɫƤ���ĵ���¼�
		blackSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin",
						"BusinessBlackSteelSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("BusinessBlackSteelSkin");
					frame.loadTree();
				}
			}
		});
		// ����ɫƤ���ĵ���¼�
		aharaSkin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin", "SaharaSkin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("SaharaSkin");
					frame.loadTree();
				}
			}
		});

		// ����ɫƤ���ĵ���¼�
		officeSilver2007Skin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSuccess = PropertiesUtil.setSkinProper("skin",
						"OfficeSilver2007Skin");
				if (isSuccess) {
					ViewSetingUtil.setSkin("OfficeSilver2007Skin");
					frame.loadTree();
				}
			}
		});

	}
	
	public MainViewSetting getContainer() {
		return this;
	}

}
