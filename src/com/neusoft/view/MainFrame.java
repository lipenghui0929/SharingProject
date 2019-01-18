package com.neusoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.service.MainViewSetting;
import com.neusoft.service.SeasDefaultTreeCellRenderer;
import com.neusoft.util.DateUtil;
import com.neusoft.util.ExcelUtil;
import com.neusoft.util.FileUtil;
import com.neusoft.util.ImageUtil;
import com.neusoft.util.JSplitPaneUtil;
import com.neusoft.util.PropertiesUtil;
import com.neusoft.util.StringUtil;
import com.neusoft.util.SystemUtil;
import com.neusoft.util.TreeUtil;
import com.neusoft.util.ViewSetingUtil;

/**
 * ����ϵͳ¼�빤�ߵ������洰��
 * 
 * @author chenzhenhua
 *
 */
public class MainFrame extends JFrame {

	public JTree tree;
	public DefaultMutableTreeNode top = new DefaultMutableTreeNode("ţţ����");
	JScrollPane leftJPanel;
	JScrollPane rightJPanel;
	JSplitPane jSplitPane;
	JSplitPane jSplitPane3;
	JSplitPane jSplitPane2;
	JButton allSelectBtn;
	JButton addBtn;
	JButton editBtn;
	JButton deleteBtn;
	JButton refreshBtn;
	JButton queryBtn;
	List<JButton> buttons;
	//�ı���
	JTextField dateField;
	JTextField bjhField;
	JTextField sjhField;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel timeJLabel;
	static {
		ViewSetingUtil.setSkin(PropertiesUtil.propSkin.getProperty("skin"));
		// top = new DefaultMutableTreeNode("��������");
	}

	/**
	 * ���ð�ť�����Ƿ����û��߽���
	 * 
	 * @param jButtons
	 * @param isEnabled
	 */
	public void setButtonIsEnabled(List<JButton> jButtons, boolean isEnabled) {
		for (int i = 0; i < jButtons.size(); i++) {
			jButtons.get(i).setEnabled(isEnabled);
		}

	}

	public void addToolBar(MainFrame frame) {
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		allSelectBtn = new JButton("ȫѡ");
		allSelectBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.allSelect"), false));
		addBtn = new JButton("����");
		addBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.add"), false));
		// ��button���Ӽ�����
		// addBtn.addActionListener(new ButtonClickAction(frame));
		addBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// if (volumeInputFrame == null) {
				final VolumeInputFrame volumeInputFrame = new VolumeInputFrame("");
				volumeInputFrame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						ViewSetingUtil.closeWarn(volumeInputFrame);
						// volumeInputFrame.dispose();
						// volumeInputFrame = null;
					}
				});
				// } else {
				// volumeInputFrame.dispose();
				// volumeInputFrame = null;
				// volumeInputFrame = new VolumeInputFrame("");
				// volumeInputFrame.setVisible(true);
				// }

			}
		});
		editBtn = new JButton("�༭");
		editBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.edit"), false));
		deleteBtn = new JButton("ɾ��");
		deleteBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.delete"), false));
		refreshBtn = new JButton("ˢ��");
		refreshBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.refresh"), false));
		toolBar.add(refreshBtn);
		refreshBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadTree();
			}
		});
		//��ѯ
		queryBtn = new JButton("��ѯ");
		queryBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.query"), false));
		queryBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Jsb jsb = new Jsb();
				//���պ�
				String bjh = bjhField.getText().trim();
				//������
				String sjh = sjhField.getText().trim();
				//ʱ��
				String date = dateField.getText().trim();
				
				if(bjh != null && !"".equals(bjh)){
					jsb.setBjh(bjh);
					jsb.setQueryforBjh(true);
				}
				if(sjh != null && !"".equals(sjh)){
					jsb.setSjh(sjh);
					jsb.setQueryforSjh(true);;
				}
				if((bjh != null && !"".equals(bjh)) || (sjh != null && !"".equals(sjh))){
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,jsb));
				}else{
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane));
				}
				
				setButtonIsEnabled(buttons, true);
				
				System.out.println("date:"+date+";bjh"+bjh+";sjh:"+sjh);
			}
		});
		dateField = new JTextField();
		//dateField.setSize(20,20);
		bjhField = new JTextField();
		sjhField = new JTextField();
		
		toolBar.addSeparator();
		toolBar.add(allSelectBtn);
		toolBar.addSeparator();
		toolBar.add(addBtn);
		toolBar.addSeparator();
		toolBar.add(editBtn);
		toolBar.addSeparator();
		toolBar.add(deleteBtn);
		toolBar.addSeparator();
		toolBar.add(queryBtn);
		toolBar.addSeparator();
		toolBar.add(dateField);
		toolBar.addSeparator();
		toolBar.add(sjhField);
		toolBar.addSeparator();
		toolBar.add(bjhField);
		
		
		
		toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		getContainer().add(toolBar, BorderLayout.NORTH);
		buttons = new ArrayList<JButton>();
		for (Component c : toolBar.getComponents()) {
			if (c instanceof JButton) {
				if (!"ˢ��".equals(((JButton) c).getText())) {
					buttons.add((JButton) c);
				}
			}
		}
		setButtonIsEnabled(buttons, false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		ViewSetingUtil.setTitle(this, "MainFrame.title");
		ViewSetingUtil.setLogo(this, "Frame.logo");
//		ViewSetingUtil.max(this);
		MainViewSetting mainViewSetting = new MainViewSetting();
		mainViewSetting.setImportTempletMenuBar(this);
		mainViewSetting.setSkinMenu(this);
		mainViewSetting.setExitMenuBar(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ViewSetingUtil.setMainFrameSize(this);
		ViewSetingUtil.noChange(this);
		exitSystemWarn();
		createJSplitPane(this);
		addToolBar(this);
		new TimeGo().start();// �����߳�
	}

	/**
	 * �������Һ����µķָ����
	 * 
	 * @param frame
	 */
	public void createJSplitPane(JFrame frame) {
		leftJPanel = new JScrollPane();
		rightJPanel = new JScrollPane();
		jSplitPane = JSplitPaneUtil.createDefaultJSplitPane(leftJPanel, rightJPanel);
		frame.add(jSplitPane);
		timeJLabel = new JLabel();
		timeJLabel.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.date"), true));
		JToolBar jToolBar = new JToolBar();
		jToolBar.setFloatable(false);
		jToolBar.setToolTipText("��ǰʱ��");
		jToolBar.setBorder(BorderFactory.createBevelBorder(1));
		jToolBar.add(timeJLabel, BorderLayout.EAST);
		frame.add(jToolBar, BorderLayout.SOUTH);
		tree = loadTree();
	}

	public JTree loadTree() {
		if (top.isLeaf() == false) {
			top.removeAllChildren();
		}
		List<File> list = FileUtil.findExcelFile(SystemUtil.getProjectRootPath(), "ģ��");
		if (list != null && list.size() > 0) {
			for (File file : list) {
				System.out.println(StringUtil.getDocumentNameByFileName(file.getName()));
				String documentName = StringUtil.getDocumentNameByFileName(file.getName());
				DefaultMutableTreeNode document = new DefaultMutableTreeNode(documentName);
				List<String> sheetNames = ExcelUtil
						.getSheetNamesByExcel(SystemUtil.getProjectRootPath() + File.separator + file.getName());
				int sheetNamesSize = sheetNames.size();
				if (sheetNames != null && sheetNamesSize > 0) {
					for (int i = 0; i < sheetNamesSize; i++) {
						DefaultMutableTreeNode node = new DefaultMutableTreeNode(sheetNames.get(i));
						document.add(node);
					}
				}
				top.add(document);
			}
		}
		tree = new JTree(top);
		// �����Զ���������
		tree.setCellRenderer(new SeasDefaultTreeCellRenderer());
		TreeUtil.expandTree(tree);
		tree.updateUI();
		leftJPanel.setViewportView(tree);
		// ���ѡ���¼�
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object object = node.getUserObject();
				if (StringUtil.isArchiveCategory(object.toString())) {
					// ˵��ѡ�еĽڵ��ǵ�������Ľڵ㣬������ѡ�е������������ļ��������ļ��ڵ�
					// ѡ�е�������ڵ㣬�����ڵ��Ҳ���ظõ��������µİ����ļ��������ļ�(3��)�����ļ��������ļ�(2��)
					add(JSplitPaneUtil.createJSplitPaneByBranch(node, leftJPanel, jSplitPane));
					setButtonIsEnabled(buttons, true);
				} else if (!StringUtil.isArchiveCategory(object.toString()) && (!"��������".equals(object.toString()))) {
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane));
					setButtonIsEnabled(buttons, true);
				} else {
					jSplitPane.setLeftComponent(leftJPanel);
					jSplitPane.setRightComponent(rightJPanel);
					// �����м�ָ�����С
					jSplitPane.setDividerSize(10);
					// ���÷ָ���λ��
					jSplitPane.setDividerLocation(185);
					// �ڷָ��������С���ǰ�ť����ʵ��JSplitPane����/��������Ŀ���չ�����۵���
					jSplitPane.setOneTouchExpandable(true);
					add(jSplitPane);
					setButtonIsEnabled(buttons, false);
				}

			}

		});
		return tree;
	}

	/**
	 * ����˳�ϵͳʱ������ʾ��Ϣ
	 */
	public void exitSystemWarn() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ViewSetingUtil.exitSystemWarn();
			}
		});
	}

	/**
	 * ���ص�ǰ����
	 * 
	 * @return
	 */
	public MainFrame getContainer() {
		return this;
	}

	// go table time and path
	class TimeGo extends Thread {
		public void run() {
			while (true) {
				// getContainer().setTitle(
				// PropertiesUtil.prop.getProperty("MainFrame.title")
				// + "��" + "��ǰʱ��:" + sdf.format(new Date()) + "��");
				getContainer().timeJLabel.setText("��ǰʱ��:" + DateUtil.getNow());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
