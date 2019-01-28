package com.neusoft.service;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Label;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.neusoft.util.ImageUtil;
import com.neusoft.util.PropertiesUtil;
import com.neusoft.util.ViewSetingUtil;

public class TreeIconDemo extends JFrame {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;

	private JTree tree;
	
	
	JMenuBar jMenuBar = new JMenuBar();
	static final String jMenuImportTempletText;
	static final String jMenuExitText;
	static final String jMenuChangeSkinText;
	JLabel label;

	static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy��MM��dd�� EEEE HH:mm:ss");
	static {
		ViewSetingUtil.setSkin(PropertiesUtil.propSkin.getProperty("skin"));
		final Properties prop = PropertiesUtil.prop;
		jMenuImportTempletText = prop
				.getProperty("MainFrame.JMenu.importTemplet");
		jMenuExitText = prop.getProperty("MainFrame.JMenu.exit");
		jMenuChangeSkinText = prop.getProperty("MainFrame.JMenu.changeSkin");
	}

	private JPanel contentPane;
	JMenu importTemplet;
	JMenu SkinMenu;
	JMenu exit;
	JMenuItem defaultSkin;
	JMenuItem blueSkin;
	JMenuItem greySkin;
	JMenuItem yellowSkin;
	JMenuItem greenSkin;
	JMenuItem purpleSkin;
	boolean isSuccess;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TreeIconDemo frame = new TreeIconDemo();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public TreeIconDemo() {
//		super();
//		setBounds(100, 100, 500, 375);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		tree = new JTree();
		scrollPane.setViewportView(tree);

		// ��������
		DefaultTreeModel defaultTreeModel = createModel();

		// ��������
		tree.setModel(defaultTreeModel);

		// �����Զ���������
		tree.setCellRenderer(new SeasDefaultTreeCellRenderer());
		addToolBar(this);
		addImportTempletMenu(this);
		addExitMenu(this);
		createJSplitPane(this);
		ViewSetingUtil.noChange(this);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * ��ӵ���ģ��Ĳ˵���,���Ҹ��˵�������Сͼ��
	 * 
	 * @param frame
	 */
	public void addImportTempletMenu(JFrame frame) {
		importTemplet = new JMenu(jMenuImportTempletText);
		importTemplet.setIcon(ImageUtil.getImageIcon("src/images/input.png",true));
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
		exit.setIcon(ImageUtil.getImageIcon("src/images/exit.png",true));
		jMenuBar.add(exit);
		// frame.setJMenuBar(jMenuBar);

	}
	
	/**
	 * �����������ߵķָ����
	 * 
	 * @param frame
	 */
	public void createJSplitPane(JFrame frame) {
		final Object[] columnTitle = { "�������ݵ���", "������������", "�����", "��ŵص�", "�鵵����" };
		final Object[][] columndate = {
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" },
				{ "aaaa", "www", "eeee", "www", "eeee" } };
		// JPanel jPane1 = new JPanel();
		// JPanel jPane2 = new JPanel();
		// JScrollPane leftJPanel = new JScrollPane(jPane1);
		// JScrollPane rightJPanel = new JScrollPane(jPane2);
		JScrollPane leftJPanel = new JScrollPane();
		JScrollPane rightJPanel = new JScrollPane();

		// JPanel jPane3 = new JPanel();
		// JPanel jPane4 = new JPanel();
		// JScrollPane upJPanel = new JScrollPane(jPane3);
		// JScrollPane downJPanel = new JScrollPane(jPane4);
		JTable upTable = new JTable(columndate, columnTitle);
		upTable.setFont(new Font("Menu.font", Font.PLAIN, 13));
		JTable middleTable = new JTable(columndate, columnTitle);
		JTable downTable = new JTable(columndate, columnTitle);
		JScrollPane upJPanel = new JScrollPane(upTable);// �ϲ���
		Label labelUp = new Label("��������");
		// upJPanel.add(labelUp,BorderLayout.NORTH);
		JScrollPane middleJPanel = new JScrollPane(middleTable);// �м䲿��
		JScrollPane downJPanel = new JScrollPane(downTable);// �²���

		// JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		// leftJPanel, rightJPanel);

		JSplitPane jSplitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				middleJPanel, downJPanel);
		jSplitPane3.setDividerSize(10);
		jSplitPane3.setDividerLocation(200);
		jSplitPane3.setOneTouchExpandable(true);

		JSplitPane jSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				upJPanel, jSplitPane3);
		jSplitPane2.setDividerSize(10);
		jSplitPane2.setDividerLocation(200);
		jSplitPane2.setOneTouchExpandable(true);

		JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				leftJPanel, jSplitPane2);

		// �����м�ָ�����С
		jSplitPane.setDividerSize(10);
		// ���÷ָ���λ��
		jSplitPane.setDividerLocation(185);
		// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
		jSplitPane.setOneTouchExpandable(true);
		frame.add(jSplitPane);
		label = new JLabel();
		JToolBar jToolBar = new JToolBar();
		jToolBar.setFloatable(false);
		jToolBar.setToolTipText("��ǰʱ��");
		jToolBar.setBorder(BorderFactory.createBevelBorder(1));
		jToolBar.add(label);
		frame.add(jToolBar, BorderLayout.SOUTH);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode("�������");
		DefaultMutableTreeNode volumeNode = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode fileNode = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode electronicalFileNode = new DefaultMutableTreeNode(
				"imsi");
		document.add(volumeNode);
		document.add(fileNode);
		document.add(electronicalFileNode);
		top.add(document);
		final JTree tree = new JTree(top);
		// leftJPanel.add(tree);
		// �����Զ���������
		tree.setCellRenderer(new SeasDefaultTreeCellRenderer());
		// leftJPanel.setViewportView(jPane1.add(tree));
		leftJPanel.setViewportView(tree);

		rightJPanel.setViewportView(new JTable(columndate, columnTitle));
		// ���ѡ���¼�
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object object = node.getUserObject();
				System.out.println(node.getUserObject().toString());
				if (node.isLeaf()) {
					System.out.println("��ѡ���ˣ�" + object.toString());
					System.out.println(node.toString());
				}

			}
		});

	}
	
	public void addToolBar(JFrame frame) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		JButton button1 = new JButton("ȫѡ");
		button1.setIcon(ImageUtil.getImageIcon("src/images/allSelect.png",false));
		JButton button2 = new JButton("����");
		button2.setIcon(ImageUtil.getImageIcon("src/images/add.png",false));
		JButton button3 = new JButton("�༭");
		button3.setIcon(ImageUtil.getImageIcon("src/images/edit.png",false));
		JButton button4 = new JButton("ɾ��");
		button4.setIcon(ImageUtil.getImageIcon("src/images/delete.png",false));
		JButton button5 = new JButton("ˢ��");
		button5.setIcon(ImageUtil.getImageIcon("src/images/refresh.png",false));
		toolBar.add(button1);
		toolBar.add(button2);
		toolBar.add(button3);
		toolBar.add(button4);
		toolBar.add(button5);
		 Container contentPane = frame.getContentPane();
         contentPane.add(toolBar, BorderLayout.NORTH);
	}
	/**
	 * �������ڵ�ģ��
	 * 
	 * @return
	 */
	public DefaultTreeModel createModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode b = new DefaultMutableTreeNode("��������");
		DefaultMutableTreeNode c = new DefaultMutableTreeNode("imsi");
		root.add(b);
		root.add(c);
		return new DefaultTreeModel(root);
	}

}
