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
 * 案卷，文件，电子文件的字段信息类
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

	// 以二维数组和一维数组来创建一个JTable对象
	Object[][] volumeTable = null; // 案卷数据表

	Object[][] fileTable = null; // 文件数据表

	Object[][] electronicalFileTable = null; // 电子文件数据表
	
	JTable volumeTab = null;
	
	JTable fileTab = null;
	
	JTable electronicalFileTab = null;

	// 定义一维数据作为列标题
	public static final Object[] columnTitle = { "字段名", "数据类型", "长度", "是否空", "默认值" };

	// 以二维数组和一维数组来创建一个JTable对象

	private String archiveCategoryName;// 档案门类

	private List<String> levelsName; // 档案等级(有案卷，文件，电子文件3级的或者文件，电子文件2级的)

	private Map<String, Map<String, String>> totalFieldsInfo;// 案卷和文件和电子文件下的字段信息

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
	 * 将数据设置到二维数组中
	 * 
	 * @param key
	 */
	private void setTableData(String key) {
		if ("案卷".equals(key) || "文件".equals(key) || "电子文件".equals(key)) {
			Map<String, String> volumeFieldsInfo = totalFieldsInfo.get(key);
			int volumeFieldsCount = volumeFieldsInfo.size();
			int column = columnTitle.length;
			if ("案卷".equals(key)) {
				volumeTable = new Object[volumeFieldsCount][column];
			} else if ("文件".equals(key)) {
				fileTable = new Object[volumeFieldsCount][column];
			} else if ("电子文件".equals(key)) {
				electronicalFileTable = new Object[volumeFieldsCount][column];
			}
			for (int i = 0; i < volumeFieldsCount;) {
				for (int j = 0; j < column; j++) {
					if ("案卷".equals(key)) {
						volumeTable[i][j] = "";
					} else if ("文件".equals(key)) {
						fileTable[i][j] = "";
					} else if ("电子文件".equals(key)) {
						electronicalFileTable[i][j] = "";
					}
					for (String fieldKey : volumeFieldsInfo.keySet()) {
						if (j == 0) {
							if ("案卷".equals(key)) {
								volumeTable[i][j] = fieldKey;
							} else if ("文件".equals(key)) {
								fileTable[i][j] = fieldKey;
							} else if ("电子文件".equals(key)) {
								electronicalFileTable[i][j] = fieldKey;
							}
						} else {
							String str = volumeFieldsInfo.get(fieldKey);
							int strCount = StringUtil.getStrCount(str, ",");
							String[] FieldsValue = str.split(",");
							if (strCount == 2) {
								// 有2个,逗号
								if (j == 1) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[0];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[0];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[0];
									}
								} else if (j == 2) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[1];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[1];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[1];
									}
								} else if (j == 3) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[2];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[2];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[2];
									}
								} else if (j == 4) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = "";
									} else if ("文件".equals(key)) {
										fileTable[i][j] = "";
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = "";
									}
								}
							} else if (strCount == 3) {
								// 有3个,逗号
								if (j == 1) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[0];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[0];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[0];
									}
								} else if (j == 2) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[1];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[1];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[1];
									}
								} else if (j == 3) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[2];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[2];
									} else if ("电子文件".equals(key)) {
										electronicalFileTable[i][j] = FieldsValue[2];
									}
								} else if (j == 4) {
									if ("案卷".equals(key)) {
										volumeTable[i][j] = FieldsValue[3];
									} else if ("文件".equals(key)) {
										fileTable[i][j] = FieldsValue[3];
									} else if ("电子文件".equals(key)) {
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
	 * 将数据设置到二维数组中
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
	 * 设置表格的外观界面
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
	 * 添加选项卡组件
	 */
	public void createTabbedPane(final JFrame frame, List<String> levelsName,final MainViewSetting mainViewSetting) {
		if (levelsName != null && levelsName.size() > 0) {
			JTabbedPane tabbedPane = new JTabbedPane(); // 创建选项卡面板对象
			JPanel jPanel = new JPanel();
			JLabel label1 = new JLabel(this.getArchiveCategoryName() + "字段信息");
			label1.setFont(new Font("Dialog", 1, 15));
			label1.setForeground(Color.BLUE);
			jPanel.add(label1);
			// 生成模板的按钮(需要写点击事件)
			JButton templetBtn = new JButton("生成"
					+ this.getArchiveCategoryName() + "模板");
			templetBtn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					boolean	flag = ExcelUtil.exportExcel(archiveCategoryName + "模板", SystemUtil.getProjectRootPath(), columnTitle, volumeTab, fileTab, electronicalFileTab);
					String pathString = SystemUtil.getProjectRootPath() + File.separator + archiveCategoryName + "模板.xls";
					boolean DataExcelFlag = false;
					if (flag) {
						DataExcelFlag = ExcelUtil.buildDataExcel(pathString, archiveCategoryName);
					}
					if (DataExcelFlag) {
						JOptionPane.showMessageDialog(null, "生成" + archiveCategoryName + "模板成功!");
//						mainViewSetting.fieldSettingFrame.dispose();
						frame.dispose();
						mainViewSetting.fieldSettingFrame = null;
						mainFrame.loadTree();
					}else {
						JOptionPane.showMessageDialog(null, "生成" + archiveCategoryName + "模板前请关闭工作表!");
					}

				}
			});
			
			templetBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.templetBtn"),false));
			jPanel.add(templetBtn);
			frame.add(jPanel, BorderLayout.NORTH);
			for (int i = 0; i < levelsName.size(); i++) {
				String level = levelsName.get(i);
				if (volumeTable != null && "案卷".equals(level)) {
					volumeTab = setTableView(volumeTable, columnTitle);
					JScrollPane panel1 = new JScrollPane(volumeTab);
					tabbedPane.addTab(level + "-" + volumeTable.length + "个字段",
							ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.volumeTable"),false),
							panel1, level + "字段信息列表");
				} else if (fileTable != null && "文件".equals(level)) {
					fileTab = setTableView(fileTable, columnTitle);
					JScrollPane panel2 = new JScrollPane(fileTab);
					tabbedPane.addTab(level + "-" + fileTable.length + "个字段",
							ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.fileTable"),false),
							panel2, level + "字段信息列表");
				} else if (electronicalFileTable != null
						&& "电子文件".equals(level)) {
					electronicalFileTab = setTableView(
							electronicalFileTable, columnTitle);
					JScrollPane panel3 = new JScrollPane(electronicalFileTab);
					tabbedPane.addTab(level + "-"
							+ electronicalFileTable.length + "个字段", ImageUtil
							.getImageIcon(PropertiesUtil.prop.getProperty("FieldSettingFrame.JTabbedPane.electronicalFileTab"),false),
							panel3, level + "字段信息列表");
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
