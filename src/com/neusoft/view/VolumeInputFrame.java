package com.neusoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.neusoft.action.JTextFieldClickAction;
import com.neusoft.util.DateUtil;
import com.neusoft.util.ExcelUtil;
import com.neusoft.util.FileUtil;
import com.neusoft.util.Message;
import com.neusoft.util.StringUtil;
import com.neusoft.util.SystemUtil;
import com.neusoft.util.ViewSetingUtil;

/**
 * 案卷级录入窗体界面
 * 
 * @author chenzhenhua
 *
 */
public class VolumeInputFrame extends JFrame {
	/**
	 * 
	 */

	JButton closeButton;

	private static final long serialVersionUID = 1L;

	JScrollPane JScrollPane;
	JPanel jPanel;
	Map<String, String> allfieldInfo = null;
	JLabel jLab = null; // 主键id

	/**
	 * 创建窗体上的工具栏
	 */
	public void CreateToolBar(final VolumeInputFrame volumeInputFrame) {
		List<JButton> buttons = new ArrayList<JButton>();
		closeButton = ViewSetingUtil.createJButtonAndIcon("关闭", "VolumeInputFrame.close");// 关闭按钮
		// 关闭按钮的操作
		// 给button增加监听器
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ViewSetingUtil.closeWarn(volumeInputFrame);
			}
		});
		JButton lastButton = ViewSetingUtil.createJButtonAndIcon("上一条", "VolumeInputFrame.last");// 上一条按钮
		JButton nextButton = ViewSetingUtil.createJButtonAndIcon("下一条", "VolumeInputFrame.next");// 下一条按钮
		JButton addButton = ViewSetingUtil.createJButtonAndIcon("增加", "VolumeInputFrame.add");// 增加按钮
		JButton appendButton = ViewSetingUtil.createJButtonAndIcon("追加", "VolumeInputFrame.append");// 追加按钮
		JButton deleteButton = ViewSetingUtil.createJButtonAndIcon("删除", "VolumeInputFrame.delete");// 删除按钮
		JButton inputFileButton = ViewSetingUtil.createJButtonAndIcon("录入文件", "VolumeInputFrame.inputFile");// 录入文件按钮
		JButton saveButton = ViewSetingUtil.createJButtonAndIcon("保存", "VolumeInputFrame.save");// 保存按钮
		// 保存按钮的操作
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jLab = new JLabel();
				jLab.setName("volumeId");// 设置主键
				jLab.setBounds(new Rectangle(0, 0, 0, 0));
				jLab.setText(StringUtil.createIdByUUID());// 设置主键id
				jLab.setVisible(false);
				volumeInputFrame.jPanel.add(jLab);
				// volumeInputFrame.jPanel.repaint();
				Component[] components = jPanel.getComponents();
				Message mes = checkDate(allfieldInfo, components);
				boolean flag = mes.isResult();
				if (flag) {
					// 数据校验成功，调用保存方法
					String pathString = SystemUtil.getProjectRootPath() + File.separator
							+ FileUtil.findExcelFile(SystemUtil.getProjectRootPath(), "数据").get(0).getName();
					boolean isSuccess = saveVolumeData(pathString, "案卷", components);
					if (isSuccess) {
						JOptionPane.showMessageDialog(null, "保存成功！", "提示", JOptionPane.WARNING_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "保存！", "提示失败！", JOptionPane.WARNING_MESSAGE);
					}
						
				} else {
					// 数据校验失败，弹出提示框
					JOptionPane.showMessageDialog(null, mes.getMessage(), "提示", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		buttons.add(closeButton);
		buttons.add(lastButton);
		buttons.add(nextButton);
		buttons.add(addButton);
		buttons.add(appendButton);
		buttons.add(deleteButton);
		buttons.add(inputFileButton);
		buttons.add(saveButton);
		JToolBar toolBar = ViewSetingUtil.createToolBar(buttons);
		this.add(toolBar, BorderLayout.NORTH);
	}

	/**
	 * 保存案卷信息
	 * 
	 * @param pathString
	 * @param excelType
	 * @param levelsName
	 * @param components
	 * @return
	 */
	public boolean saveVolumeData(String pathString, String levelsName, Component[] components) {
		boolean flag = true;
		HSSFWorkbook hssFWorkbook = ExcelUtil.getHSSFWorkbookByExcelPath(pathString);
		HSSFSheet hssFSheet = ExcelUtil.getHSSFSheet(hssFWorkbook, levelsName);
		List<String> fields = ExcelUtil.getFieldsByDataExcelPath(hssFSheet);
		HSSFRow hssFRow = ExcelUtil.createRow(hssFSheet);
		int fieldCount = fields.size();
		int componentLength = components.length;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(pathString);
				for (int j = 0; j < componentLength; j++) {
					for (int i = 0; i < fieldCount; i++) {
					if (components[j] instanceof JTextField) {
						// 得到每个文本框的名字
						JTextField jtextField = (JTextField) components[j];
						String jTextFieldName = jtextField.getName();
						System.out.println("jTextFieldName = "  +  jTextFieldName);
						System.out.println("字段= " + jtextField.getText());
						if (jTextFieldName.equals(fields.get(i))) {
							System.out.println( "====" + jtextField.getText());
							HSSFCell hssFCell = hssFRow.createCell(i);
							hssFCell.setCellValue(jtextField.getText());
						}
					}
				}	
				}

			
			hssFWorkbook.write(out);
		} catch (IOException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					flag = false;
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 数据校验
	 * 
	 * @param allfieldInfo
	 *            字段的集合
	 * @param components
	 *            面板上的所有控件
	 * @return
	 */
	public Message checkDate(Map<String, String> allfieldInfo, Component[] components) {
		boolean flag = true;
		String message = "";
		Message mes = new Message();
		int componentLength = components.length;
		for (int i = 0; i < componentLength; i++) {
			if (components[i] instanceof JTextField) {
				// 文本框控件
				// 得到每个文本框的名字
				String jTextFieldName = ((JTextField) components[i]).getName();
				if (allfieldInfo != null) {
					for (Entry<String, String> entry : allfieldInfo.entrySet()) {
						String fieldKey = entry.getKey();
						String fieldValue = entry.getValue();
						String[] fieldValueArr = fieldValue.split(",");
						if (fieldKey.equals(jTextFieldName)) {
							if ("不可以".equals(fieldValueArr[2])) {
								if (((JTextField) components[i]).getText().trim().length() <= 0
										|| "".equals(((JTextField) components[i]).getText().trim())) {
									flag = false;
									message += fieldKey + "不能为空！\n";
								}
							}
							if (!"".equals(fieldValueArr[1]) && !"日期".equals(fieldValueArr[0])) {
								if (((JTextField) components[i]).getText().length() > Integer
										.parseInt(fieldValueArr[1])) {
									flag = false;
									message += fieldKey + "长度不能大于" + fieldValueArr[1] + "！\n";
								} else if ("整数".equals(fieldValueArr[0])) {
									boolean isNumeric = StringUtil.isNumeric(((JTextField) components[i]).getText());
									if (!isNumeric) {
										flag = false;
										message += fieldKey + "必须是整数类型" + "！\n";
									}

								}
							}
							if ("".equals(fieldValueArr[1])) {
								if (((JTextField) components[i]).getText().length() > 200) {
									flag = false;
									message += fieldKey + "长度不能大于" + 200 + "！\n";
								}
							}
						}
					}
				}
			} else if (components[i] instanceof JComboBox) {
				// 下拉框控件
				// 得到每个下拉框的名字
				String jComboBoxName = ((JComboBox) components[i]).getName();

			}
		}
		mes.setResult(flag);
		mes.setMessage(message);
		return mes;
	}

	/**
	 * 创建面板组件
	 * 
	 * @param text
	 * @return
	 */
	public JPanel createJPanel(String text) {
		JScrollPane = ViewSetingUtil.createJScrollPane(getContainer().getWidth(), getContainer().getHeight());
		jPanel = ViewSetingUtil.createJPane(getContainer().getWidth(), getContainer().getHeight(), null);
		JLabel titleJLable = ViewSetingUtil.createTitleJLable(text, 20);
		// 靠左、上，宽度,高度
		titleJLable.setBounds(new Rectangle(((jPanel.getWidth() - titleJLable.getWidth()) / 2), 10,
				titleJLable.getWidth(), titleJLable.getHeight()));
		jPanel.add(titleJLable);
		JScrollPane.setViewportView(jPanel);
		this.add(JScrollPane);
		return jPanel;
	}

	/**
	 * 根据是否为空生成JLabel对象(如果字段不可以为空的话，字段文本后面需要加*，可以为空的话，字段文本后面不需要加*)
	 * 
	 * @param fieldKey
	 * @param fieldValueArr
	 * @return
	 */
	public JLabel createJLableByIsNull(String fieldKey, String[] fieldValueArr) {
		boolean isNull = false;
		if ("不可以".equals(fieldValueArr[2])) {
			isNull = true;
		}
		JLabel jLab = ViewSetingUtil.createJLable(fieldKey, isNull);
		return jLab;
	}

	/**
	 * 根据档案的模板动态生成录入界面
	 * 
	 * @param jPanel
	 */
	public void produceViewByTemplate(JPanel jPanel, Map<String, String> fields) {
		allfieldInfo = fields;
		int x = 20;
		int y = 10;
		JTextFieldClickAction jTextFieldClickAction = new JTextFieldClickAction(this);
		for (Entry<String, String> entry : fields.entrySet()) {
			y += 35;
			String fieldKey = entry.getKey();
			String fieldValue = entry.getValue();
			String[] fieldValueArr = fieldValue.split(",");
			JLabel jLab = createJLableByIsNull(fieldKey, fieldValueArr);
			// System.out.println("fieldValue = " + fieldValue);
			Rectangle rectangle = new Rectangle(x, y, jLab.getWidth(), 25);// 靠左、上20，宽度为400,高度200
			jLab.setBounds(rectangle);
			jPanel.add(jLab);
			if ("日期".equals(fieldValueArr[0]) && fieldValueArr.length == 3) {
				final JTextField jTextField = ViewSetingUtil.createJTextField(fieldKey);
				jTextField.setEnabled(false);
				jTextField.setForeground(Color.BLUE);
				// 给button增加监听器
				jTextField.addMouseListener(jTextFieldClickAction);
				// jTextField.setText(DateUtil.getTime(DateUtil.YYYY_MM_DD));
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jTextField.setBounds(rectangle2);
				jPanel.add(jTextField);
			} else if ("日期".equals(fieldValueArr[0]) && fieldValueArr.length == 4
					&& "fx:datenow()".equalsIgnoreCase(fieldValueArr[3])) {
				final JTextField jTextField = ViewSetingUtil.createJTextField(fieldKey);
				jTextField.setEnabled(false);
				jTextField.setForeground(Color.BLUE);
				// 给button增加监听器
				jTextField.addMouseListener(jTextFieldClickAction);
				jTextField.setText(DateUtil.getTime(DateUtil.YYYY_MM_DD));
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jTextField.setBounds(rectangle2);
				jPanel.add(jTextField);
			} else if (fieldValueArr.length == 3) {
				final JTextField jTextField = ViewSetingUtil.createJTextField(fieldKey);
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jTextField.setBounds(rectangle2);
				jPanel.add(jTextField);
			} else if (fieldValueArr.length == 4 && !fieldValueArr[3].contains("fx")
					&& !"FondsNo".equalsIgnoreCase(fieldValueArr[3])) {
				// String def[] = { "普通职员", "管理员", "02" };
				// JComboBox jComboBox = new JComboBox(def);
				JComboBox jComboBox = new JComboBox();
				jComboBox.setSelectedItem(fieldValueArr[3]);
				// jComboBox.setSelectedIndex(2);
				jComboBox.setEditable(true);
				jComboBox.setName(fieldKey);
				jComboBox.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jComboBox.setBounds(rectangle2);
				jPanel.add(jComboBox);
			} else if (fieldValueArr.length == 4 && fieldValueArr[3].contains("fx")) {
				final JTextField jTextField = ViewSetingUtil.createJTextField(fieldKey);
				jTextField.setText("带了fx函数");
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jTextField.setBounds(rectangle2);
				jPanel.add(jTextField);
			} else if (fieldValueArr.length == 4 && "fondsno".equalsIgnoreCase(fieldValueArr[3])) {
				final JTextField jTextField = ViewSetingUtil.createJTextField(fieldKey);
				Rectangle rectangle2 = new Rectangle(jLab.getWidth() + x + 40, y, 600, 25);// 靠左、上20，宽度为400,高度200
				jTextField.setBounds(rectangle2);
				jPanel.add(jTextField);
			}
		}
		jPanel.setPreferredSize(new Dimension(ViewSetingUtil.getDimension().width, y + 80));
		jPanel.revalidate();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VolumeInputFrame frame = new VolumeInputFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 设置窗体的标题
	 * 
	 * @param title
	 */
	public VolumeInputFrame(String title) {
		this();
		ViewSetingUtil.setJFrameTitle(this, title);
	}

	/**
	 * Create the frame.
	 */
	public VolumeInputFrame() {
		CreateToolBar(this);
		ViewSetingUtil.setLogo(this, "Frame.logo");
		ViewSetingUtil.noChange(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(ViewSetingUtil.getDimension().width - 350, ViewSetingUtil.getDimension().height - 100);
		ViewSetingUtil.showCentre(this);
		String pathString = SystemUtil.getProjectRootPath() + File.separator
				+ FileUtil.findExcelFile(SystemUtil.getProjectRootPath(), "模板").get(0).getName();
		// System.out.println(ExcelUtil.getSheetNamesByExcel(pathString));
		Map<String, String> fields = ExcelUtil.getFieldsByExcelPath(pathString, "案卷");
		produceViewByTemplate(createJPanel("声像档案-案卷"), fields);
	}

	/**
	 * 返回当前对象
	 * 
	 * @return
	 */
	public VolumeInputFrame getContainer() {
		return this;
	}

}
