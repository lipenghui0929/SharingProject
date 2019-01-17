package com.neusoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import com.neusoft.service.MainViewSetting;
import com.neusoft.util.ExcelUtil;
import com.neusoft.util.FileUtil;
import com.neusoft.util.ImageUtil;
import com.neusoft.util.PropertiesUtil;
import com.neusoft.util.StringUtil;
import com.neusoft.util.SystemUtil;
import com.neusoft.util.ViewSetingUtil;

/**
 * �����ļ��������ļ����ֶ���Ϣ��
 * 
 * @author chenzhenhua
 *
 */
public class FieldSettingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MainFrame mainFrame;

	// �Զ�ά�����һά����������һ��JTable����
	Object[][] volumeTable = null; // �������ݱ�

	Object[][] fileTable = null; // �ļ����ݱ�

	Object[][] electronicalFileTable = null; // �����ļ����ݱ�
	
	JTable volumeTab = null;
	
	JTable fileTab = null;
	
	JTable electronicalFileTab = null;

	// ����һά������Ϊ�б���
	public static final Object[] columnTitle = { "�ֶ���", "��������", "����", "�Ƿ��", "Ĭ��ֵ" };

	// �Զ�ά�����һά����������һ��JTable����

	private String archiveCategoryName;// ��������

	private List<String> levelsName; // �����ȼ�(�а����ļ��������ļ�3���Ļ����ļ��������ļ�2����)

	private Map<String, Map<String, String>> totalFieldsInfo;// ������ļ��͵����ļ��µ��ֶ���Ϣ

	public String getArchiveCategoryName() {
		return archiveCategoryName;
	}

	public void setArchiveCategoryName(String archiveCategoryName) {
		this.archiveCategoryName = archiveCategoryName;
	}

	public List<String> getLevelsName() {
		return levelsName;
	}

	public void setLevelsName(List<String> levelsName) {
		this.levelsName = levelsName;
	}

	public Map<String, Map<String, String>> getTotalFieldsInfo() {
		return totalFieldsInfo;
	}

	public void setTotalFieldsInfo(
			Map<String, Map<String, String>> totalFieldsInfo) {
		this.totalFieldsInfo = totalFieldsInfo;
	}

	public FieldSettingFrame(String archiveCategoryName,
			List<String> levelsName,
			Map<String, Map<String, String>> totalFieldsInfo,MainFrame frame,MainViewSetting mainViewSetting)
			throws HeadlessException {
		this();
		this.mainFrame = frame;
		ViewSetingUtil.setJFrameTitle(this, archiveCategoryName);
		this.archiveCategoryName = archiveCategoryName;
		this.levelsName = levelsName;
		this.totalFieldsInfo = totalFieldsInfo;
		setTableData(totalFieldsInfo);
		createTabbedPane(this, levelsName,mainViewSetting);
	}

	/**
	 * ���������õ���ά������
	 * 
	 * @param key
	 */
	private void setTableData(String key) {
		if ("����".equals(key) || "�ļ�".equals(key) || "�����ļ�".equals(key)) {
			Map<String, String> volumeFieldsInfo = totalFieldsInfo.get(key);
			int volumeFieldsCount = volumeFieldsInfo.size();
			int column = columnTitle.length;
			if ("����".equals(key)) {
				volumeTable = new Object[volumeFieldsCount][column];
			} else if ("�ļ�".equals(key)) {
				fileTable = new Object[volumeFieldsCount][column];
			} else if ("�����ļ�".equals(key)) {
				electronicalFileTable = new Object[volumeFieldsCount][column];
			}
			for (int i = 0; i < volumeFieldsCount;) {
				for (int j = 0; j < column; j++) {
					if ("����".equals(key)) {
						volumeTable[i][j] = "";
					} else if ("�ļ�".equals(key)) {
						fileTable[i][j] = "";
					} else if ("�����ļ�".equals(key)) {
						electronicalFileTable[i][j] = "";
					}
					for (String fieldKey : volumeFieldsInfo.keySet()) {
						if (j == 0) {
							if ("����".equals(key)) {
								volumeTable[i][j] = fieldKey;
							} else if ("�ļ�".equals(key)) {
								fileTable[i][j] = fieldKey;
							} else if ("�����ļ�".equals(key)) {
								electronicalFileTable[i][j] = fieldKey;
							}
						} else {
							String str = volumeFieldsInfo.get(fieldKey);
							int strCount = StringUtil.getStrCount(str, ",");
							String[] FieldsValue = str.split(",");
							if (strCount == 2) {
								// ��2��,����
								if (j == 1) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[0];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[0];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[0];
									}
								} else if (j == 2) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[1];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[1];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[1];
									}
								} else if (j == 3) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[2];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[2];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[2];
									}
								} else if (j == 4) {
									if ("����".equals(key)) {
										volumeTable[i][j] = "";
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = "";
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = "";
									}
								}
							} else if (strCount == 3) {
								// ��3��,����
								if (j == 1) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[0];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[0];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[0];
									}
								} else if (j == 2) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[1];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[1];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[1];
									}
								} else if (j == 3) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[2];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[2];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[2];
									}
								} else if (j == 4) {
									if ("����".equals(key)) {
										volumeTable[i][j] = FieldsValue[3];
									} else if ("�ļ�".equals(key)) {
										fileTable[i][j] = FieldsValue[3];
									} else if ("�����ļ�".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[3];
									}
								}
							}
						}
						i++;
						if (i == volumeFieldsCount) {
							if (i == volumeFieldsCount && j == column - 1) {
								i = volumeFieldsCount;
							} else if (i == volumeFieldsCount) {
								i = 0;
							}
						}
						if (j == column - 1 && i == volumeFieldsCount) {
							i = volumeFieldsCount;
						}
					}
				}
			}
		}
	}

	/**
	 * ���������õ���ά������
	 * 
	 * @param totalFieldsInfo
	 */
	private void setTableData(Map<String, Map<String, String>> totalFieldsInfo) {
		if (totalFieldsInfo != null) {
			for (String key : totalFieldsInfo.keySet()) {
				setTableData(key);
			}
		}
	}

	static {
//		ViewSetingUtil.setSkin(PropertiesUtil.propSkin.getProperty("skin"));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FieldSettingFrame frame = new FieldSettingFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ���ñ�����۽���
	 * 
	 * @param table
	 * @param columnTitle
	 * @return
	 */
	public JTable setTableView(Object[][] table, Object[] columnTitle) {
		JTable myTable = ViewSetingUtil.createTableView(false, table, columnTitle);
		for (int j = 0; j < columnTitle.length; j++) {
			TableColumn column = myTable.getColumnModel().getColumn(j);
			if (j == columnTitle.length - 1) {
				column.setPreferredWidth(600);
			} else if (j == 0) {
				column.setPreferredWidth(135);
			} else if (j == 1) {
				column.setPreferredWidth(80);
			} else if (j == 2) {
				column.setPreferredWidth(60);
			} else if (j == 3) {
				column.setPreferredWidth(60);
			}
		}
		return myTable;
	}

	/**
	 * ���ѡ����
	 */
	public void createTabbedPane(final JFrame frame, List<String> levelsName,final MainViewSetting mainViewSetting) {
		if (levelsName != null && levelsName.size() > 0) {
			JTabbedPane tabbedPane = new JTabbedPane(); // ����ѡ�������
			JPanel jPanel = new JPanel();
			JLabel label1 = new JLabel(this.getArchiveCategoryName() + "�ֶ���Ϣ");
			label1.setFont(new Font("Dialog", 1, 15));
			label1.setForeground(Color.BLUE);
			jPanel.add(label1);
			// ����ģ��İ�ť(��Ҫд����¼�)
			JButton templetBtn = new JButton("����"
					+ this.getArchiveCategoryName() + "ģ��");
			templetBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					boolean	flag = ExcelUtil.exportExcel(archiveCategoryName + "ģ��", SystemUtil.getProjectRootPath(), columnTitle, volumeTab, fileTab, electronicalFileTab);
					String pathString = SystemUtil.getProjectRootPath() + File.separator + archiveCategoryName + "ģ��.xls";
					boolean DataExcelFlag = false;
					if (flag) {
						DataExcelFlag = ExcelUtil.buildDataExcel(pathString, archiveCategoryName);
					}
					if (DataExcelFlag) {
						JOptionPane.showMessageDialog(null, "����" + archiveCategoryName + "ģ��ɹ�!");
//						mainViewSetting.fieldSettingFrame.dispose();
						frame.dispose();
						mainViewSetting.fieldSettingFrame = null;
						mainFrame.loadTree();
					}else {
						JOptionPane.showMessageDialog(null, "����" + archiveCategoryName + "ģ��ǰ��رչ�����!");
					}

				}
			});
			
			templetBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.templetBtn"),false));
			jPanel.add(templetBtn);
			frame.add(jPanel, BorderLayout.NORTH);
			for (int i = 0; i < levelsName.size(); i++) {
				String level = levelsName.get(i);
				if (volumeTable != null && "����".equals(level)) {
					volumeTab = setTableView(volumeTable, columnTitle);
					JScrollPane panel1 = new JScrollPane(volumeTab);
					tabbedPane.addTab(level + "-" + volumeTable.length + "���ֶ�",
							ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.volumeTable"),false),
							panel1, level + "�ֶ���Ϣ�б�");
				} else if (fileTable != null && "�ļ�".equals(level)) {
					fileTab = setTableView(fileTable, columnTitle);
					JScrollPane panel2 = new JScrollPane(fileTab);
					tabbedPane.addTab(level + "-" + fileTable.length + "���ֶ�",
							ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.fileTable"),false),
							panel2, level + "�ֶ���Ϣ�б�");
				} else if (electronicalFileTable != null
						&& "�����ļ�".equals(level)) {
					electronicalFileTab = setTableView(
							electronicalFileTable, columnTitle);
					JScrollPane panel3 = new JScrollPane(electronicalFileTab);
					tabbedPane.addTab(level + "-"
							+ electronicalFileTable.length + "���ֶ�", ImageUtil
							.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.electronicalFileTab"),false),
							panel3, level + "�ֶ���Ϣ�б�");
				}

			}
			frame.add(tabbedPane);
		}

	}

	/**
	 * Create the frame.
	 */
	public FieldSettingFrame() {
		ViewSetingUtil.setLogo(this, "Frame.logo");
		ViewSetingUtil.noChange(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 600);
		// addMenu(this);
		ViewSetingUtil.showCentre(this);
	}
	
	private FieldSettingFrame getContainer() {
		return this;
	}

}
