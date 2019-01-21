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
 * �������ù�����
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
	static Font font = new Font("����", Font.PLAIN,12);

	static {
		viewSetingUtil = new ViewSetingUtil();
		final Properties prop = PropertiesUtil.prop;
		message = prop.getProperty("MainFrame.exit.message");
		titleMessage = prop.getProperty("MainFrame.exit.titleMessage");
	}

	/**
	 * 
	 * @param isEnabled �Ƿ�ɱ༭
	 * @return
	 */
	public static JTable createTableView(boolean isEnabled,Object[][] table, Object[] columnTitle) {
		JTable myTable = new JTable(table, columnTitle);
		
//		myTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		myTable.setFont(new Font("Menu.font", Font.PLAIN, 13));
		myTable.setEnabled(isEnabled); // �����Ա༭
		myTable.setGridColor(Color.BLACK);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ˮƽ������
		SeasTableCellRenderer seasRender = new SeasTableCellRenderer();
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		for (int j = 0; j < columnTitle.length; j++) {
			boolean flag = ((String)columnTitle[j]).contains("����");
			if (flag) {
				myTable.getColumn(columnTitle[j]).setCellRenderer(seasRender);
			}else {
				render.setHorizontalAlignment(SwingConstants.CENTER);
				myTable.getColumn(columnTitle[j]).setCellRenderer(render); // ��Ԫ���е��ı�������ʾ
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
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);// ˮƽ������
		
//		myTable.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
	    myTable.setFont(new Font("Menu.font", Font.PLAIN, 13));
		myTable.setGridColor(Color.BLACK);
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		for (int j = 0; j < columnCount; j++) {
			TableColumn column = myTable.getColumnModel().getColumn(j);
			if(!"ѡ��".equals(((String)tableModel.getColumnName(j))) && !"����".equals(((String)tableModel.getColumnName(j)))){
				render.setHorizontalAlignment(SwingConstants.CENTER);
				myTable.getColumn(tableModel.getColumnName(j)).setCellRenderer(render); // ��Ԫ���е��ı�������ʾ
			}
			if("ѡ��".equals(((String)tableModel.getColumnName(j)))){
				column.setPreferredWidth(60);
			}else if("����".equals(((String)tableModel.getColumnName(j)))){
				column.setPreferredWidth(170);
			}else{
				column.setPreferredWidth(120);
			}
			
			
		}
		return myTable;
		
	}

	/**
	 * �˳�ϵͳʱ�ľ���(һ���ǵ�����ϽǵĲ�����ǵ���˳�ϵͳ��ťʱ��Ҫ�����û��Ƿ����Ҫ�˳�ϵͳʱ���ø÷���)
	 */
	public static void exitSystemWarn() {
		int exi = JOptionPane.showConfirmDialog(null, message, titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			System.exit(0); // �˳�ϵͳ
		} else {
			return;
		}
	}
	
	public static void exitSystemToMinimum() {
		int exi = JOptionPane.showConfirmDialog(null, message, titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			System.exit(0); // �˳�ϵͳ
		} else {
			return;
		}
	}
	
	/**
	 * ����رհ�ť������ʾ��Ϣ
	 */
	public static void closeWarn(JFrame frame) {
		int exi = JOptionPane.showConfirmDialog(frame, "ȷ���ر���", titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (exi == JOptionPane.YES_OPTION) {
			frame.dispose();
		} else {
			return;
		}
	}

	/**
	 * ����Ƥ��
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
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		frame.setSize(screenWidth - 100, screenHeight - 100);
		showCentre(frame);
	}

	/**
	 * ��ȡ��Ļ�ĳߴ�
	 * 
	 * @return
	 */
	public static Dimension getDimension() {
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		return screenSize;
	}

	/**
	 * ���������ʾ
	 * 
	 * @param frame
	 */
	public static void showCentre(JFrame frame) {
		int windowWidth = frame.getWidth(); // ��ô��ڿ�
		int windowHeight = frame.getHeight(); // ��ô��ڸ�
		Dimension screenSize = getDimension(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
		frame.setVisible(true);
	}

	/**
	 * ��ȡһ��JFileChooser����(����ģʽ)
	 * 
	 * @return
	 */
	public static JFileChooser getFileChooserInstance() {
		if (instance == null) {
			instance = new JFileChooser();
			instance.setFileSelectionMode(JFileChooser.FILES_ONLY);// ����ʾ�ļ�
			instance.setMultiSelectionEnabled(false);// ������ѡ�����ļ�
			instance.setDialogTitle(PropertiesUtil.prop
					.getProperty("jFileChooser"));
			instance.setPreferredSize(new Dimension(720, 420));
			instance.setFileFilter(new SeasFilter("seas"));
		}
		return instance;
	}

	/**
	 * ���ô���ı���(����������ļ��ж�ȡ)
	 */
	public static void setTitle(JFrame frame, String title) {
		frame.setTitle(PropertiesUtil.prop.getProperty(title));
	}
	
	/**
	 * ���ô���ı���(���ⲻ�������ļ��ж�ȡ)
	 */
	public static void setJFrameTitle(JFrame frame, String title) {
		frame.setTitle(title);
	}

	/**
	 * ���ô����logo
	 */
	public static void setLogo(JFrame frame, String imgPath) {
		viewSetingUtil.setImage(imgPath);
		if (image != null) {
			frame.setIconImage(image);// ����ͼ��
		}
	}

	/**
	 * ���ô����Image
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
	 * ���ô��ڴ�С�����Ըı�
	 * 
	 * @param frame
	 */
	public static void noChange(JFrame frame) {
		frame.setResizable(false);// ���ڴ�С���ɸı�
	}

	/**
	 * ���ô������
	 * 
	 * @param frame
	 */
	public static void max(JFrame frame) {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**
	 * �����������ť�Ĺ�����
	 * @param jButtons
	 */
	public static JToolBar createToolBar(List<JButton> jButtons){
		JToolBar jToolBar = new JToolBar();
		jToolBar.setFloatable(false);//���ù����������ƶ�
		jToolBar.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,
				Color.BLACK));//���ù������ı߿���ʽ
		for (JButton button : jButtons) {
			jToolBar.add(button);
			jToolBar.addSeparator();//�������ϵİ�ť֮����ӷָ���
		}
		return jToolBar;
	}
	
	/**
	 * ��������Сͼ��İ�ť
	 * @param jButtons
	 */
	public static JButton createJButtonAndIcon(String buttonText,String iconPath){
		JButton jButton = new JButton(buttonText);
		jButton.setIcon(ImageUtil.getImageIcon(
				PropertiesUtil.prop.getProperty(iconPath), false));
		return jButton;
	}
	
	/**
	 * ����һ����������ʽ��JLable����
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
	 * �����Ƿ��Ǳ���������JLabel����
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
	 * ����JTextField����
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
	 * ����JScrollPane����
	 * @return
	 */
	public static JScrollPane createJScrollPane() {
		JScrollPane jScrollPane = new JScrollPane();
		return jScrollPane;
	}
	
	/**
	 * ����һ���������������JScrollPane����
	 * @param width
	 * @param height
	 * @return
	 */
	public static JScrollPane createJScrollPane(int width, int height) {
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setPreferredSize(new Dimension(width - 5, height));
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//�������������
//		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return jScrollPane;
		
	}
	
	/**
	 * ����һ��JPane����
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
