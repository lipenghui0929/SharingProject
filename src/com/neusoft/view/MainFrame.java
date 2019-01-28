package com.neusoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.eltima.components.ui.DatePicker;
import com.neusoft.action.JToolBarFieldAction;
import com.neusoft.base.ColumndateUtil;
import com.neusoft.ddmk.damin.Fsb;
import com.neusoft.ddmk.damin.Jsb;
import com.neusoft.ddmk.damin.Page;
import com.neusoft.service.MainViewSetting;
import com.neusoft.service.SeasDefaultTreeCellRenderer;
import com.neusoft.util.DatePluginUtil;
import com.neusoft.util.DateUtil;
import com.neusoft.util.ExcelUtil;
import com.neusoft.util.FileUtil;
import com.neusoft.util.ImageUtil;
import com.neusoft.util.JSplitPaneUtil;
import com.neusoft.util.PropertiesUtil;
import com.neusoft.util.StringUtil;
import com.neusoft.util.SystemToMinimumUtil;
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
	
	private Page pageFsb = new Page();
	private Page pageJsb = new Page();
	private Page pageImsi = new Page();
	
	public JTree tree;
	private JFrame mainFrame = this;
	public DefaultMutableTreeNode top = new DefaultMutableTreeNode("ţţ����");
	JToolBarFieldAction jToolBarFieldAction = new JToolBarFieldAction();
	
	JScrollPane leftJPanel;
	JScrollPane rightJPanel;
	JSplitPane jSplitPane;
	JButton allSelectBtn;
	JButton addBtn;
	JButton editBtn;
	JButton deleteBtn;
	JButton refreshBtn;
	List<JButton> buttons;
	//��ѯģ��
	JPanel queryPanelForJsb = new JPanel();
	JPanel queryPanelForFsb = new JPanel();
	JPanel queryPanelForImsi = new JPanel();
	//��ҳ
	JPanel pagingPanel = new JPanel();
    
	//JSB��ѯ��ť
	JTextField sjhTextForJsb;
	JTextField sjhFieldForJsb;
	
	JTextField bjhTextForJsb;
	JTextField bjhFieldForJsb;
	
	JTextField dateTextForJsb;
	DatePicker datepickForJsb;
	
	//fSB��ѯ��ť
	JTextField sjhTextForFsb;
	JTextField sjhFieldForFsb;
	
	JTextField bjhTextForFsb;
	JTextField bjhFieldForFsb;
	
	JTextField dateTextForFsb;
	DatePicker datepickForFsb;
	
	
	
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
				Object[] possibleValues = {"��������", "imsi" };
				Object selectedValue =JOptionPane.showInputDialog(null, "��ѡ����������ģ�飺","ѡ���ɫ��", JOptionPane.INFORMATION_MESSAGE, null, possibleValues,possibleValues[0]);
				if(selectedValue != null){
					System.out.println("selectedValue"+selectedValue);
					final VolumeInputFrame volumeInputFrame = new VolumeInputFrame(selectedValue.toString());
					volumeInputFrame.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							ViewSetingUtil.closeWarn(volumeInputFrame);
							// volumeInputFrame.dispose();
							// volumeInputFrame = null;
						}
					});
				}
				
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
		
		toolBar.addSeparator();
		toolBar.add(addBtn);
		toolBar.addSeparator();
		toolBar.add(editBtn);
		toolBar.addSeparator();
		toolBar.add(deleteBtn);
		
		toolBar.addSeparator();
		toolBar.add(allSelectBtn);
		
		
		
		toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		getContainer().add(toolBar, BorderLayout.NORTH);
		buttons = new ArrayList<JButton>();
		for (Component c : toolBar.getComponents()) {
			if (c instanceof JButton) {
				String text = ((JButton) c).getText();
				if ((!"ˢ��".equals(text)) && (!"����".equals(text))) {
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
		addPagePanel();
		addQueryPanelForJsb();
		addQueryPanelForFsb();
		addQueryPanelForImsi();
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
				System.out.println(object.toString());
				pageFsb.setPageNow(0);
				if ("��������".equals(object.toString())) {
					//��ʼ��ҳ��
					pageJsb.setPageNow(0);
					pageJsb.setTatolCount(ColumndateUtil.getConutForJsb(capsulationJsb()));
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForJsb,pagingPanel,pageJsb));
					setButtonIsEnabled(buttons, true);
				} else if ("��������".equals(object.toString())) {
					//��ʼ��ҳ��
					pageFsb.setPageNow(0);
					pageFsb.setTatolCount(ColumndateUtil.getConutForFsb(capsulationFsb()));
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForFsb,pagingPanel,pageFsb));
					setButtonIsEnabled(buttons, true);
				} else if ("imsi".equals(object.toString())) {
					//��ʼ��ҳ��
					pageImsi.setPageNow(0);
					pageImsi.setTatolCount(ColumndateUtil.getConutForImsi());
					
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForImsi,pagingPanel,pageImsi));
					setButtonIsEnabled(buttons, true);
				}  else {
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
	 * ��ѯ��
	 */
	public void addQueryPanelForJsb(){
		
		sjhTextForJsb = new JTextField("���պ�:");
		sjhFieldForJsb = new JTextField(15);
		
		bjhTextForJsb  = new JTextField("������:");
		bjhFieldForJsb = new JTextField(15);
		
		dateTextForJsb = new JTextField("ʱ��:");
		datepickForJsb = DatePluginUtil.getDatePicker();
		
		JButton queryBtn = new JButton("��ѯ");
		queryBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("jsb��ѯ");
				Jsb jsb = capsulationJsb();
				pageJsb.setPageNow(0);
				pageJsb.setTatolCount(ColumndateUtil.getConutForJsb(jsb));
				add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,jsb,queryPanelForJsb,pagingPanel,pageJsb));
				setButtonIsEnabled(buttons, false);
			}
		});
		
		queryPanelForJsb.add(sjhTextForJsb);
		queryPanelForJsb.add(sjhFieldForJsb);
		queryPanelForJsb.add(bjhTextForJsb);
		queryPanelForJsb.add(bjhFieldForJsb);
		queryPanelForJsb.add(dateTextForJsb);
		queryPanelForJsb.add(datepickForJsb);
		queryPanelForJsb.add(queryBtn);
	}
	public void addQueryPanelForFsb(){
		
		sjhTextForFsb = new JTextField("���պ�:");
		sjhFieldForFsb = new JTextField(15);
		
		bjhTextForFsb  = new JTextField("������:");
		bjhFieldForFsb = new JTextField(15);
		
		dateTextForFsb = new JTextField("ʱ��:");
		datepickForFsb = DatePluginUtil.getDatePicker();
		
		JButton queryBtn = new JButton("��ѯ");
		queryBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Fsb fsb = capsulationFsb();
				pageFsb.setPageNow(0);
				pageFsb.setTatolCount(ColumndateUtil.getConutForFsb(fsb));
				add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,fsb,queryPanelForFsb,pagingPanel,pageFsb));
				setButtonIsEnabled(buttons, false);
			}
		});
		
		queryPanelForFsb.add(sjhTextForFsb);
		queryPanelForFsb.add(sjhFieldForFsb);
		queryPanelForFsb.add(bjhTextForFsb);
		queryPanelForFsb.add(bjhFieldForFsb);
		queryPanelForFsb.add(dateTextForFsb);
		queryPanelForFsb.add(datepickForFsb);
		queryPanelForFsb.add(queryBtn);
		jToolBarFieldAction.UpLoadFile(queryPanelForFsb);
	}
	
	public void addQueryPanelForImsi(){
		
		jToolBarFieldAction.UpLoadFile(queryPanelForImsi);
	}
	
	//��ҳ���
	public void addPagePanel(){
		
		JButton buttonS = new JButton("��ҳ");
		//buttonS.setActionCommand("��ҳ"); 
		
		JButton button1 = new JButton("��һҳ");
		JButton button2 = new JButton("��һҳ");
		JButton buttonM = new JButton("ĩҳ");
		
        pagingPanel.add(buttonS); 
		pagingPanel.add(button1); 
		pagingPanel.add(button2); 
		pagingPanel.add(buttonM); 
		
		//countFsb
		buttonS.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("��������".equals(object.toString())) {
					pageJsb.setPageNow(0);
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
				} else if ("��������".equals(object.toString())) {
					pageFsb.setPageNow(0);
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				}else if ("imsi".equals(object.toString())) {
					pageImsi.setPageNow(0);
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForImsi,pagingPanel,pageImsi));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("��������".equals(object.toString())) {
					
					if(pageJsb.getPageNow()<=0){ 
						pageJsb.setPageNow(1);; 
					} 
					pageJsb.setPageNow(pageJsb.getPageNow()-1);
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
					
				} else if ("��������".equals(object.toString())) {
					if(pageFsb.getPageNow()<=0){ 
						pageFsb.setPageNow(1);; 
					} 
					pageFsb.setPageNow(pageFsb.getPageNow()-1);
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				}
				else if ("imsi".equals(object.toString())) {
					if(pageImsi.getPageNow()<=0){ 
						pageImsi.setPageNow(1);; 
					} 
					pageImsi.setPageNow(pageImsi.getPageNow()-1);
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForImsi,pagingPanel,pageImsi));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		button2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("��������".equals(object.toString())) {
					
					if(pageJsb.getPageNow()<pageJsb.getPageCount()-1){ 
						pageFsb.setPageNow(pageJsb.getPageNow()+1); 
						add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
					}
				} else if ("��������".equals(object.toString())) {
					
					System.out.println("��ǰҳ"+pageFsb.getPageNow());
					System.out.println(pageFsb.getPageCount());
					
					if(pageFsb.getPageNow()<pageFsb.getPageCount()-1){ 
						pageFsb.setPageNow(pageFsb.getPageNow()+1); 
						add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
					}
					
				}else if ("imsi".equals(object.toString())) {
					
					if(pageImsi.getPageNow()<pageImsi.getPageCount()-1){ 
						pageImsi.setPageNow(pageImsi.getPageNow()+1); 
						add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForImsi,pagingPanel,pageImsi));
					}
					
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		buttonM.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("��������".equals(object.toString())) {
					pageJsb.setPageNow(pageJsb.getPageCount()-1); 
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForFsb,pagingPanel,pageJsb));
				} else if ("��������".equals(object.toString())) {
					pageFsb.setPageNow(pageFsb.getPageCount()-1); 
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				} else if ("imsi".equals(object.toString())) {
					
					pageImsi.setPageNow(pageImsi.getPageCount()-1); 
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForImsi,pagingPanel,pageImsi));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		
	}

	public Fsb capsulationFsb(){
		Fsb fsb = new Fsb();
		//���պ�
		String bjh = bjhFieldForFsb.getText().trim();
		//������
		String sjh = sjhFieldForFsb.getText().trim();
		System.out.println(sjh);
		//ʱ��
		Date date = (Date)datepickForFsb.getValue();
		
		if(bjh != null && !"".equals(bjh)){
			fsb.setBjh(bjh);
			fsb.setQueryforBjh(true);
		}
		if(sjh != null && !"".equals(sjh)){
			fsb.setSjh(sjh);
			fsb.setQueryforSjh(true);;
		}
		if(date != null){
			fsb.setSj(date);
			fsb.setQueryforSj(true);
		}
		System.out.println("fsb:[bjh-"+bjh+";sjh-"+sjh+";date-"+date+"]");
		return fsb;
	}
	
	public Jsb capsulationJsb(){
		Jsb jsb = new Jsb();
		//���պ�
		String bjh = bjhFieldForJsb.getText().trim();
		//������
		String sjh = sjhFieldForJsb.getText().trim();
		System.out.println(sjh);
		//ʱ��
		Date date = (Date)datepickForJsb.getValue();
		
		if(bjh != null && !"".equals(bjh)){
			jsb.setBjh(bjh);
			jsb.setQueryforBjh(true);
		}
		if(sjh != null && !"".equals(sjh)){
			jsb.setSjh(sjh);
			jsb.setQueryforSjh(true);;
		}
		if(date != null){
			jsb.setSj(date);
			jsb.setQueryforSj(true);
		}
		
		System.out.println("jsb:[bjh-"+bjh+";sjh-"+sjh+";date-"+date+"]");
		return jsb;
	}
	
	/**
	 * ����˳�ϵͳʱ������ʾ��Ϣ
	 */
	public void exitSystemWarn() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 //ViewSetingUtil.exitSystemWarn();
				//��С��
				mainFrame.setVisible(false);
				SystemToMinimumUtil.miniTray(mainFrame);
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
