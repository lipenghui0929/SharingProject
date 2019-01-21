package com.neusoft.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.BusinessSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.GreenMagicSkin;
import org.jvnet.substance.skin.MistAquaSkin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.SaharaSkin;

import com.neusoft.action.ButtonForTableAction;
import com.neusoft.base.FsbTableModel;
import com.neusoft.service.SeasTableCellRenderer;


/**
 * 界面设置工具类
 * 
 * @author chenzhenhua
 *
 */
public class ViewSetingUtil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Image image;
	private static JFileChooser instance;
	static ViewSetingUtil viewSetingUtil;
	static final String message;
	static final String titleMessage;
	static Font font = new Font("宋体", Font.PLAIN,12);

	static {
		viewSetingUtil = new ViewSetingUtil();
		final Properties prop = PropertiesUtil.prop;
		message = prop.getProperty("MainFrame.exit.message");
		titleMessage = prop.getProperty("MainFrame.exit.titleMessage");
	}

	/**
	 * 
	 * @param isEnabled 是否可编辑
	 * @return
	 */
	public static JTable createTableView(boolean isEnabled,Object[][] table, Object[] columnTitle) {
		JTable myTable = new JTable(table, columnTitle);
		
//		myTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		myTable.setFont(new Font("Menu.font", Font.PLAIN, 13));
		myTable.setEnabled(isEnabled); // 不可以编辑
		myTable.setGridColor(Color.BLACK);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 水平滚动条
		SeasTableCellRenderer seasRender = new SeasTableCellRenderer();
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		for (int j = 0; j < columnTitle.length; j++) {
			boolean flag = ((String)columnTitle[j]).contains("档号");
			if (flag) {
				myTable.getColumn(columnTitle[j]).setCellRenderer(seasRender);
			}else {
				render.setHorizontalAlignment(SwingConstants.CENTER);
				myTable.getColumn(columnTitle[j]).setCellRenderer(render); // 单元格中的文本居中显示
			}
			TableColumn column = myTable.getColumnModel().getColumn(j);
			column.setPreferredWidth(180);
		}
		return myTable;
		
	}
	
	public static JTable createTableView(AbstractTableModel tableModel) {
		
		int columnCount = tableModel.getColumnCount();
		
		JTable myTable = new JTable(tableModel);
		
		
		myTable.setRowHeight(30);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// 水平滚动条
		
//		myTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
	    myTable.setFont(new Font("Menu.font", Font.PLAIN, 13));
		myTable.setGridColor(Color.BLACK);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		for (int j = 0; j < columnCount; j++) {
			TableColumn column = myTable.getColumnModel().getColumn(j);
			if(!"选择".equals(((String)tableModel.getColumnName(j))) && !"操作".equals(((String)tableModel.getColumnName(j)))){
				render.setHorizontalAlignment(SwingConstants.CENTER);
				myTable.getColumn(tableModel.getColumnName(j)).setCellRenderer(render); // 单元格中的文本居中显示
			}
			if("选择".equals(((String)tableModel.getColumnName(j)))){
				column.setPreferredWidth(60);
			}else if("操作".equals(((String)tableModel.getColumnName(j)))){
				column.setPreferredWidth(170);
			}else{
				column.setPreferredWidth(120);
			}
			
			
		}
		return myTable;
		
	}

	/**
	 * 退出系统时的警告(一般是点击右上角的叉或者是点击退出系统按钮时需要提醒用户是否真的要退出系统时调用该方法)
	 */
	public static void exitSystemWarn() {
		int exi = JOptionPane.showConfirmDialog(null, message, titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			System.exit(0); // 退出系统
		} else {
			return;
		}
	}
	
	public static void exitSystemToMinimum() {
		int exi = JOptionPane.showConfirmDialog(null, message, titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			System.exit(0); // 退出系统
		} else {
			return;
		}
	}
	
	/**
	 * 点击关闭按钮弹出提示信息
	 */
	public static void closeWarn(JFrame frame) {
		int exi = JOptionPane.showConfirmDialog(frame, "确定关闭吗？", titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			frame.dispose();
		} else {
			return;
		}
	}

	/**
	 * 设置皮肤
	 */
	public static void setSkin(String skinType) {
		
		if ("default".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new BusinessSkin());
		}else if ("BusinessBlueSteelSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new BusinessBlueSteelSkin());
		}else if ("BusinessBlackSteelSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
		}else if ("OfficeSilver2007Skin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new OfficeSilver2007Skin());
		}else if ("SaharaSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new SaharaSkin());
		}else if ("CremeSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new CremeSkin());
		}else if ("FieldOfWheatSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new FieldOfWheatSkin());
		}else if ("GreenMagicSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new GreenMagicSkin());
		}else if ("MistAquaSkin".equals(skinType)) {
			SubstanceLookAndFeel.setSkin(new MistAquaSkin());
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
	}

	public static void setMainFrameSize(JFrame frame) {
		Dimension screenSize = getDimension();
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setSize(screenWidth - 100, screenHeight - 100);
		showCentre(frame);
	}

	/**
	 * 获取屏幕的尺寸
	 * 
	 * @return
	 */
	public static Dimension getDimension() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		return screenSize;
	}

	/**
	 * 窗体居中显示
	 * 
	 * @param frame
	 */
	public static void showCentre(JFrame frame) {
		int windowWidth = frame.getWidth(); // 获得窗口宽
		int windowHeight = frame.getHeight(); // 获得窗口高
		Dimension screenSize = getDimension(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// 设置窗口居中显示
		frame.setVisible(true);
	}

	/**
	 * 获取一个JFileChooser对象(单例模式)
	 * 
	 * @return
	 */
	public static JFileChooser getFileChooserInstance() {
		if (instance == null) {
			instance = new JFileChooser();
			instance.setFileSelectionMode(JFileChooser.FILES_ONLY);// 仅显示文件
			instance.setMultiSelectionEnabled(false);// 不允许选择多个文件
			instance.setDialogTitle(PropertiesUtil.prop
					.getProperty("jFileChooser"));
			instance.setPreferredSize(new Dimension(720, 420));
			instance.setFileFilter(new SeasFilter("seas"));
		}
		return instance;
	}

	/**
	 * 设置窗体的标题(标题从配置文件中读取)
	 */
	public static void setTitle(JFrame frame, String title) {
		frame.setTitle(PropertiesUtil.prop.getProperty(title));
	}
	
	/**
	 * 设置窗体的标题(标题不从配置文件中读取)
	 */
	public static void setJFrameTitle(JFrame frame, String title) {
		frame.setTitle(title);
	}

	/**
	 * 设置窗体的logo
	 */
	public static void setLogo(JFrame frame, String imgPath) {
		viewSetingUtil.setImage(imgPath);
		if (image != null) {
			frame.setIconImage(image);// 设置图标
		}
	}

	/**
	 * 设置窗体的Image
	 */
	public void setImage(String imgPath) {
		try {
			URL url = this.getClass().getResource(
					PropertiesUtil.prop.getProperty(imgPath));
			image = ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设置窗口大小不可以改变
	 * 
	 * @param frame
	 */
	public static void noChange(JFrame frame) {
		frame.setResizable(false);// 窗口大小不可改变
	}

	/**
	 * 设置窗口最大化
	 * 
	 * @param frame
	 */
	public static void max(JFrame frame) {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**
	 * 创建带多个按钮的工具栏
	 * @param jButtons
	 */
	public static JToolBar createToolBar(List<JButton> jButtons){
		JToolBar jToolBar = new JToolBar();
		jToolBar.setFloatable(false);//设置工具条不能移动
		jToolBar.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,
				Color.BLACK));//设置工具栏的边框样式
		for (JButton button : jButtons) {
			jToolBar.add(button);
			jToolBar.addSeparator();//工具栏上的按钮之间添加分割线
		}
		return jToolBar;
	}
	
	/**
	 * 创建带有小图标的按钮
	 * @param jButtons
	 */
	public static JButton createJButtonAndIcon(String buttonText,String iconPath){
		JButton jButton = new JButton(buttonText);
		jButton.setIcon(ImageUtil.getImageIcon(
				PropertiesUtil.prop.getProperty(iconPath), false));
		return jButton;
	}
	
	/**
	 * 创建一个带字体样式的JLable对象
	 * @param lableText
	 * @return
	 */
	public static JLabel createTitleJLable(String lableText,int fontSize){
		int textLength = lableText.length();
		JLabel jLabel = new JLabel(lableText);
		jLabel.setFont(new Font("Dialog", 1, fontSize));
		jLabel.setForeground(Color.BLUE);
		jLabel.setSize(textLength * fontSize * 2, fontSize);
		return jLabel;
	}
	
	/**
	 * 根据是否是必填项生成JLabel对象
	 * @param lableText
	 * @param isMust
	 * @return
	 */
	public static JLabel createJLable(String lableText,boolean isMust){
		int textLength = lableText.length();
		JLabel jLabel = null;
		if (isMust) {
			jLabel = new JLabel(lableText + ":" + "(*)");
		}else {
			jLabel = new JLabel(lableText + ":");
		}
		jLabel.setFont(font);
		jLabel.setName(lableText);
		jLabel.setForeground(Color.BLUE);
		jLabel.setSize(textLength * font.getSize() * 2, font.getSize());
		return jLabel;
	}
	
	/**
	 * 创建JTextField对象
	 * @param name
	 * @return
	 */
	public static JTextField createJTextField(String name) {
		JTextField jTextField = new JTextField();
		jTextField.setName(name);
		jTextField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		return jTextField;
		
	}
	
	/**
	 * 创建JScrollPane对象
	 * @return
	 */
	public static JScrollPane createJScrollPane() {
		JScrollPane jScrollPane = new JScrollPane();
		return jScrollPane;
	}
	
	/**
	 * 创建一个带纵向滚动条的JScrollPane对象
	 * @param width
	 * @param height
	 * @return
	 */
	public static JScrollPane createJScrollPane(int width, int height) {
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setPreferredSize(new Dimension(width - 5, height));
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//设置纵向滚动条
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return jScrollPane;
		
	}
	
	/**
	 * 创建一个JPane对象
	 * @param width
	 * @param height
	 * @return
	 */
	public static JPanel createJPane(int width, int height,LayoutManager mgr) {
		JPanel jPanel = new JPanel(mgr);
		jPanel.setSize(width, height);
		return jPanel;
		
	}

}
