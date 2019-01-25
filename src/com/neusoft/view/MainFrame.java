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
 * 档案系统录入工具的主界面窗口
 * 
 * @author chenzhenhua
 *
 */
public class MainFrame extends JFrame {
	
	private Page pageFsb = new Page();
	private Page pageJsb = new Page();
	
	public JTree tree;
	private JFrame mainFrame = this;
	public DefaultMutableTreeNode top = new DefaultMutableTreeNode("牛牛管理");
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
	//查询模块
	JPanel queryPanelForJsb = new JPanel();
	JPanel queryPanelForFsb = new JPanel();
	//分页
	JPanel pagingPanel = new JPanel();
    
	//查询按钮
	JTextField sjhText;
	JTextField sjhField;
	
	JTextField bjhText;
	JTextField bjhField;
	
	JTextField dateText;
	DatePicker datepick;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel timeJLabel;
	static {
		ViewSetingUtil.setSkin(PropertiesUtil.propSkin.getProperty("skin"));
		// top = new DefaultMutableTreeNode("档案管理");
	}

	/**
	 * 设置按钮数组是否启用或者禁用
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
		allSelectBtn = new JButton("全选");
		allSelectBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.allSelect"), false));
		addBtn = new JButton("增加");
		addBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.add"), false));
		// 给button增加监听器
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
		editBtn = new JButton("编辑");
		editBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.edit"), false));
		deleteBtn = new JButton("删除");
		deleteBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.delete"), false));
		refreshBtn = new JButton("刷新");
		refreshBtn.setIcon(ImageUtil.getImageIcon(PropertiesUtil.prop.getProperty("MainFrame.refresh"), false));
		toolBar.add(refreshBtn);
		refreshBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loadTree();
			}
		});
		
		toolBar.addSeparator();
		toolBar.add(allSelectBtn);
		toolBar.addSeparator();
		toolBar.add(addBtn);
		toolBar.addSeparator();
		toolBar.add(editBtn);
		toolBar.addSeparator();
		toolBar.add(deleteBtn);
		
		
		
		toolBar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		getContainer().add(toolBar, BorderLayout.NORTH);
		buttons = new ArrayList<JButton>();
		for (Component c : toolBar.getComponents()) {
			if (c instanceof JButton) {
				if (!"刷新".equals(((JButton) c).getText())) {
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
		setButtons();
		pageFsb.setTatolCount(ColumndateUtil.getConutForFsb(capsulationFsb()));
		pageJsb.setTatolCount(ColumndateUtil.getConutForJsb(capsulationJsb()));
		addPagePanel();
		addQueryPanelForJsb();
		addQueryPanelForFsb();
		new TimeGo().start();// 启动线程
	}

	/**
	 * 创建左右和上下的分割面板
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
		jToolBar.setToolTipText("当前时间");
		jToolBar.setBorder(BorderFactory.createBevelBorder(1));
		jToolBar.add(timeJLabel, BorderLayout.EAST);
		frame.add(jToolBar, BorderLayout.SOUTH);
		tree = loadTree();
	}

	public JTree loadTree() {
		if (top.isLeaf() == false) {
			top.removeAllChildren();
		}
		List<File> list = FileUtil.findExcelFile(SystemUtil.getProjectRootPath(), "模板");
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
		// 设置自定义描述类
		tree.setCellRenderer(new SeasDefaultTreeCellRenderer());
		TreeUtil.expandTree(tree);
		tree.updateUI();
		leftJPanel.setViewportView(tree);
		// 添加选择事件
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				Object object = node.getUserObject();
				pageFsb.setPageNow(0);
				/*// 说明选中的节点是档案门类的节点，而不是选中档案管理，接收数据，文件，电子文件节点
				// 选中档案门类节点，主窗口的右侧加载该档案门类下的接收数据，文件，电子文件(3级)或者文件，电子文件(2级)*/
				if ("接收数据".equals(object.toString())) {
					//add(JSplitPaneUtil.createJSplitPaneByBranch(node, leftJPanel, jSplitPane));
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForJsb,pagingPanel,pageFsb));
					setButtonIsEnabled(buttons, true);
				} else if ("发送数据".equals(object.toString())) {
					add(JSplitPaneUtil.createJSplitPaneByLeafNode(node, leftJPanel, jSplitPane,queryPanelForFsb,pagingPanel,pageFsb));
					setButtonIsEnabled(buttons, true);
				} else {
					jSplitPane.setLeftComponent(leftJPanel);
					jSplitPane.setRightComponent(rightJPanel);
					// 设置中间分割条大小
					jSplitPane.setDividerSize(10);
					// 设置分割条位置
					jSplitPane.setDividerLocation(185);
					// 在分割条上添加小三角按钮可以实现JSplitPane左右/上下组件的快速展开或折叠。
					jSplitPane.setOneTouchExpandable(true);
					add(jSplitPane);
					setButtonIsEnabled(buttons, false);
				}

			}

		});
		return tree;
	}
	
	/**
	 * 查询块
	 */
	public void addQueryPanelForJsb(){
		
		
		JButton queryBtn = new JButton("查询");
		queryBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				Jsb jsb = capsulationJsb();
				pageJsb.setPageNow(0);
				pageJsb.setTatolCount(ColumndateUtil.getConutForJsb(jsb));
				add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,jsb,queryPanelForJsb,pagingPanel,pageJsb));
				setButtonIsEnabled(buttons, false);
			}
		});
		
		queryPanelForJsb.add(sjhText);
		queryPanelForJsb.add(sjhField);
		queryPanelForJsb.add(bjhText);
		queryPanelForJsb.add(bjhField);
		queryPanelForJsb.add(dateText);
		queryPanelForJsb.add(datepick);
		queryPanelForJsb.add(queryBtn);
	}
	public void addQueryPanelForFsb(){
		
		JButton queryBtn = new JButton("查询");
		queryBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				Fsb fsb = capsulationFsb();
				pageFsb.setPageNow(0);
				pageFsb.setTatolCount(ColumndateUtil.getConutForFsb(fsb));
				add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,fsb,queryPanelForFsb,pagingPanel,pageFsb));
				setButtonIsEnabled(buttons, false);
			}
		});
		
		queryPanelForFsb.add(sjhText);
		queryPanelForFsb.add(sjhField);
		queryPanelForFsb.add(bjhText);
		queryPanelForFsb.add(bjhField);
		queryPanelForFsb.add(dateText);
		queryPanelForFsb.add(datepick);
		queryPanelForFsb.add(queryBtn);
		jToolBarFieldAction.UpLoadFile(queryPanelForFsb);
	}
	
	//分页面板
	public void addPagePanel(){
		
		JButton buttonS = new JButton("首页");
		//buttonS.setActionCommand("首页"); 
		
		JButton button1 = new JButton("上一页");
		JButton button2 = new JButton("下一页");
		JButton buttonM = new JButton("末页");
		
        pagingPanel.add(buttonS); 
		pagingPanel.add(button1); 
		pagingPanel.add(button2); 
		pagingPanel.add(buttonM); 
		
		//countFsb
		buttonS.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("接收数据".equals(object.toString())) {
					pageJsb.setPageNow(0);
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
				} else if ("发送数据".equals(object.toString())) {
					pageFsb.setPageNow(0);
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("接收数据".equals(object.toString())) {
					
					if(pageJsb.getPageNow()<=0){ 
						pageJsb.setPageNow(1);; 
					} 
					pageJsb.setPageNow(pageJsb.getPageNow()-1);
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
					
				} else if ("发送数据".equals(object.toString())) {
					if(pageFsb.getPageNow()<=0){ 
						pageFsb.setPageNow(1);; 
					} 
					pageFsb.setPageNow(pageFsb.getPageNow()-1);
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		button2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("接收数据".equals(object.toString())) {
					
					if(pageJsb.getPageNow()<pageJsb.getPageCount()-1){ 
						pageFsb.setPageNow(pageJsb.getPageNow()+1); 
						add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForJsb,pagingPanel,pageJsb));
					}
				} else if ("发送数据".equals(object.toString())) {
					
					System.out.println("当前页"+pageFsb.getPageNow());
					System.out.println(pageFsb.getPageCount());
					
					if(pageFsb.getPageNow()<pageFsb.getPageCount()-1){ 
						pageFsb.setPageNow(pageFsb.getPageNow()+1); 
						add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
					}
					
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		buttonM.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Object object = node.getUserObject();
				
				if ("接收数据".equals(object.toString())) {
					pageJsb.setPageNow(pageJsb.getPageCount()-1); 
					add(JSplitPaneUtil.createJSplitPaneByButton(leftJPanel, jSplitPane,capsulationJsb(),queryPanelForFsb,pagingPanel,pageJsb));
				} else if ("发送数据".equals(object.toString())) {
					pageFsb.setPageNow(pageFsb.getPageCount()-1); 
					add(JSplitPaneUtil.createJSplitPaneByButtonForFsb(leftJPanel, jSplitPane,capsulationFsb(),queryPanelForFsb,pagingPanel,pageFsb));
				}
				
				setButtonIsEnabled(buttons, false);
			}
		});
		
		
	}

	public Fsb capsulationFsb(){
		Fsb fsb = new Fsb();
		//接收号
		String bjh = bjhField.getText().trim();
		//本机号
		String sjh = sjhField.getText().trim();
		//时间
		Date date = (Date)datepick.getValue();
		
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
		return fsb;
	}
	
	public Jsb capsulationJsb(){
		Jsb jsb = new Jsb();
		//接收号
		String bjh = bjhField.getText().trim();
		//本机号
		String sjh = sjhField.getText().trim();
		//时间
		Date date = (Date)datepick.getValue();
		
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
		return jsb;
	}
	
	//按钮组
	public void setButtons(){
		
		sjhText = new JTextField("接收号:");
		sjhField = new JTextField(15);
		
		bjhText  = new JTextField("本机号:");
		bjhField = new JTextField(15);
		
		dateText= new JTextField("时间:");
		datepick = DatePluginUtil.getDatePicker();
		
	}
	/**
	 * 点击退出系统时弹出提示信息
	 */
	public void exitSystemWarn() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				 //ViewSetingUtil.exitSystemWarn();
				//最小化
				mainFrame.setVisible(false);
				SystemToMinimumUtil.miniTray(mainFrame);
			}
		});
	}

	/**
	 * 返回当前对象
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
				// + "【" + "当前时间:" + sdf.format(new Date()) + "】");
				getContainer().timeJLabel.setText("当前时间:" + DateUtil.getNow());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
