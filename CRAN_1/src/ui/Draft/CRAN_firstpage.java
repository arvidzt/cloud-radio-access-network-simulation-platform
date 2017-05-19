package ui.Draft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import ui.CRAN_excel.Datashow;

public class CRAN_firstpage extends JFrame implements MouseListener,ActionListener{
	JMenuBar jmb;//主干菜单
	JMenu jm1,jm2,jm3,jm4;//网络部署，链路部署，网络优化，运行配置
	JMenuItem jmi31,jmi32,jmi33,jmi34;//次级菜单栏
	JMenuItem jmi11,jmi12,jmi13,jmi14;//次级菜单栏
	JMenuItem jmi21,jmi22,jmi23;//次级菜单栏
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JPanel panel_1;
	private JPanel panel_1_1;
	private JSplitPane splitPane;
	private JPanel panel_2;
	private JPanel panel_3;
	
	private JLabel label;
	private JTree tree;
	private JPanel panel_4;
	private JTextField textField;
	private JButton button_3;
	
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	private JPanel panel_5;
	private JButton button_4;
	private JButton button_5;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRAN_firstpage frame = new CRAN_firstpage();
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
	public CRAN_firstpage() {
//菜单栏
		jmb=new JMenuBar();
		jm1=new JMenu("网络部署");
		jm2=new JMenu("链路部署");
		jm3=new JMenu("网络优化");
		jm4=new JMenu("运行配置");
		
		jmi11=new JMenuItem("BBU");
		jmi11.addActionListener(this);//添加监听
		jmi12=new JMenuItem("RRU");
		jmi13=new JMenuItem("UE");
		jmi14=new JMenuItem("参数文件导入");
		jmi21=new JMenuItem("BBU-BBU");
		jmi22=new JMenuItem("BBU-RRU");
		jmi23=new JMenuItem("RRU-UE");
		jmi31=new JMenuItem("链接规划(P)");
		jmi31.setMnemonic('P');
		jmi32=new JMenuItem("负载均衡(B)");
		jmi32.setMnemonic('B');
		jmi33=new JMenuItem("自主管理(M)");
		jmi33.setMnemonic('M');
		jmi34=new JMenuItem("节能补偿(S)");
		jmi34.setMnemonic('S');
		//将菜单系项添加到菜单上
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jm1.add(jmi11);
		jm1.add(jmi12);
		jm1.add(jmi13);
		jm1.add(jmi14);
		jm2.add(jmi21);
		jm2.add(jmi22);
		jm2.add(jmi23);
		jm3.add(jmi31);
		jm3.add(jmi32);
		jm3.add(jmi33);
		jm3.add(jmi34);
		//将菜单条添加到窗体上
		this.setJMenuBar(jmb);
		
		panel = new JPanel();
		jmb.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		button = new JButton("账号管理");
		panel.add(button);
		
		button_1 = new JButton("用户管理");
		panel.add(button_1);
		
		button_2 = new JButton("退出");
		panel.add(button_2);
//设置窗体属性
		this.setTitle("CRAN仿真实验平台");
		this.setSize(1219, 603);//窗体大小
		this.setLocation(100, 100);//窗体位置
	    //禁止用户改变窗体大小
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
//分栏	
//上栏
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
//下栏
		panel_1_1= new JPanel();
		getContentPane().add(panel_1_1, BorderLayout.SOUTH);
		panel_1_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		button_9 = new JButton("  刷新  ");
		panel_1_1.add(button_9);
		
		button_4 = new JButton("BBU拓扑链路关系");
		panel_1_1.add(button_4);
		
		button_5 = new JButton("RRU拓扑链路关系");
		panel_1_1.add(button_5);
		
		button_6 = new JButton("参数文件保存");
		panel_1_1.add(button_6);
		
		button_7 = new JButton("\u53C2\u6570\u6587\u4EF6\u5BFC\u51FA");
		panel_1_1.add(button_7);
		
		button_8 = new JButton("\u62D3\u6251\u53C2\u6570\u4FDD\u5B58");
		panel_1_1.add(button_8);
		
		splitPane = new JSplitPane();
		getContentPane().add(splitPane, BorderLayout.CENTER);
//左栏		
		panel_2 = new JPanel();
		splitPane.setLeftComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(2,1, 0, 0));
		
		label = new JLabel("网络资源");//网络资源
		label.setFont(new Font("黑体", Font.BOLD, 30));
		panel_3.add(label);
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(30);
		
		button_3 = new JButton("\u641C\u7D22");
		panel_4.add(button_3);
		
		/*tree_1 = new JTree();
		panel_2.add(tree_1, BorderLayout.CENTER);*/
		//树形列表
		//根节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("根节点");
		//一级节点
		DefaultMutableTreeNode node_first1 = new DefaultMutableTreeNode("区域BBU池数据");
		DefaultMutableTreeNode node_first2 = new DefaultMutableTreeNode("网元列表");  
		DefaultMutableTreeNode node_first3 = new DefaultMutableTreeNode("链路关系");
		DefaultMutableTreeNode node_first4 = new DefaultMutableTreeNode("资源库列表");  
		 //二级节点
		node_second2_1 = new DefaultMutableTreeNode("BBU",false); 
		node_second2_2 = new DefaultMutableTreeNode("RRU数据",false);  
		node_second2_3= new DefaultMutableTreeNode("UE",false);
		node_second2_4 = new DefaultMutableTreeNode("用户状态查询/修改",false);  
		
		node_second3_1 = new DefaultMutableTreeNode("BBU-BBU链路管理",false); 
		node_second3_2 = new DefaultMutableTreeNode("BBU-RRU链路管理",false);  
		node_second3_3= new DefaultMutableTreeNode("RRU-UE链路管理",false);
		
		node_second4_1 = new DefaultMutableTreeNode("信道模型",false); 
		node_second4_2 = new DefaultMutableTreeNode("光路消耗模型",false);  
		node_second4_3= new DefaultMutableTreeNode("移动性模型",false);
		node_second4_4 = new DefaultMutableTreeNode("业务量模型",false);  
		node_second4_5 = new DefaultMutableTreeNode("射频资源模型",false); 
		//添加树枝
		root.add(node_first1);
		root.add(node_first2);
		root.add(node_first3);
		root.add(node_first4);
		
		node_first2.add(node_second2_1);
		node_first2.add(node_second2_2);
		node_first2.add(node_second2_3);
		node_first2.add(node_second2_4);
		node_first3.add(node_second3_1);
		node_first3.add(node_second3_2);
		node_first3.add(node_second3_3);
		node_first4.add(node_second4_1);
		node_first4.add(node_second4_2);
		node_first4.add(node_second4_3);
		node_first4.add(node_second4_4);
		node_first4.add(node_second4_5);
		//利用根节点直接创建树Treeroot
		Treeroot=new JTree(root);
		Treeroot.addMouseListener(this);;
		panel_2.add(Treeroot, BorderLayout.CENTER);
		//定制树
		Treeroot.setRootVisible(false);//无根节点
		Treeroot.setRowHeight(20);//树节点的行高
		DefaultTreeCellRenderer treeCellRenderer=new DefaultTreeCellRenderer();//获得树节点的绘制对象
		treeCellRenderer=(DefaultTreeCellRenderer)Treeroot.getCellRenderer();
		treeCellRenderer.setLeafIcon(null);
		treeCellRenderer.setClosedIcon(null);
		treeCellRenderer.setOpenIcon(null);
		Treeroot.setFont(new Font("宋体", Font.PLAIN, 17));
		
		//panel_5 = new JPanel();
//右边，画图		
		First_panel fpaint=new First_panel();
		//tuoputu tuopu_panel=new tuoputu();
		splitPane.setRightComponent(fpaint);
		
		
		
		
		

	}
	

// ActionListener的方法
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jmi11)
		{
			
		}
		
	}


	@Override//鼠标点击
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		DefaultMutableTreeNode selectionNode=(DefaultMutableTreeNode)Treeroot.getLastSelectedPathComponent();	
		 if(e.getClickCount()==2)
		 { //鼠标双击事件
			 
			 if(selectionNode.equals(node_second2_3)){ //叶子节点的监听
			    System.out.println("ok");
			    Datashow sa=new Datashow(this, "UE数据", true);//记得对jb2注册监听，TRUE 模式对话框
			   }
			  }
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

}

class First_panel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		setBackground(Color.white);
		g.drawOval(100, 100, 20, 20);
	}
}
