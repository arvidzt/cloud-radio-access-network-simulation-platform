/*
 * 类名：Ue_add
 * 功能：UE群与UE部署界面
 */
package ui.Add_bbu_rru_ue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.*;

import java.io.File;
import java.lang.Math;

import ui.Share_bag.*;

public class Ue_add extends JDialog implements ActionListener,ListSelectionListener{
	boolean b;
	String judge_UEGroup_UE;
	private  JPanel contentPanel;
//	private final JPanel contentPanel = new JPanel();
	JSplitPane splitPane;
	JPanel panel1,panel_1,panel2,panel2_1,panel2_2;
	JButton button1,button2;
	JTextField textField,textField_1,textField_2,textField_3;
	Num_search_module bbuc,bbu;
	int bbupool_num;
	int ue_add_rownum1=0;//添加的ue1行数
	int ue_add_row_alreaday1=0;//已经添加了的ue1个数
	int ue_add_rownum2=0;//添加的ue2行数
	int ue_add_row_alreaday2=0;//已经添加了的ue2个数
	String ue_add_first=null;//第一次添加之前数据库已有的最大的UE ID 
	String UeGroup_add_first=null;//第一次添加之前UeGroup中已有的最大UE群ID
	int bbu_num_total,rru_num_total,ue_num_total;//bbu总数,rru总数，ue总数
	JLabel lable_BBUPool,lable_BBU,lable_RRU,lable_Ue;
	UE_table_module1 ue1_table,ue1_table_uemaxid;//ue1_table_uemaxid专门为得到UE最大id号而写的module
	UE_table_module2 sql_allnum_model,ue2_table;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	JTable jt1,jt2;
	JScrollPane jsp1,jsp2;
	JComboBox jcb1,jcb2,jcb3;//下拉列表
	String []jg1={"室外","室内"};//室内外信息数组，不填写默认是0，1：室内。0：室外
	String []jg2={"0","1","2"};//天线类型数组，不填写默认是0
	Vector jg3=null;//表格2对应的ue群id，根据表格1里面存在的去选择的。
	String ue_id_max;
	ListSelectionModel selectionMode1;
	private JPanel panel;
	private JPanel panel_2, panel_3,panel_4,panel_5,panel_6,panel_7,panel_8,panel_9,panel_10,panel_11,panel_node;
	private JButton button,button_1,button_2,btnNewButton,btnNewButton_1,btnNewButton_2,cancelButton;
	int total_num;
	int already_exist_row1,already_exist_row2;
	int jt2_ue_count;
	String jt1_uegroup_id;
	private JButton button_3;
	private JButton button_4;
	int uenum;
	private JPanel panel_12;
	private JPanel panel_13;
	private JLabel lblUe_1;

//	public static void main(String[] args) {
//		try {
//			Ue_add dialog = new Ue_add();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public Ue_add(Frame owner,String title,boolean modal){
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//public Ue_add(){
		//设置窗体属性
		setTitle("UE参数输入");
		setBounds(150, 50, 1054, 662);
		getContentPane().setLayout(new BorderLayout());
		String path="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Right3.png";
		Image image=new ImageIcon(path).getImage(); 
		contentPanel = new BackgroundPanel(image);
		contentPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		splitPane = new JSplitPane();
		splitPane.setOpaque(false);
		splitPane.setDividerLocation(130);
		contentPanel.add(splitPane);
		panel1 = new JPanel();
/***************************************左栏************************************/
		splitPane.setLeftComponent(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		{
			String path_10="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Left.png";
			Image image_10=new ImageIcon(path_10).getImage(); 
			panel_node= new BackgroundPanel(image_10);
			panel_node.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JLabel label = new JLabel("网元列表");
//			label.setFont(new Font("黑体", Font.BOLD, 20));
			label.setFont(MyTools.f1);
//			label.setForeground(Color.white);
			panel_node.add(label);
			panel1.add(panel_node, BorderLayout.NORTH);
		}
		{
			panel_1 = new JPanel();
			panel1.add(panel_1, BorderLayout.CENTER);
			panel_1.setBackground(Color.white);
			build_tree();
			panel_1.add(Treeroot, BorderLayout.CENTER);
			
		}
/*****************************************右栏************************************/
		panel2 = new JPanel();
		panel2.setOpaque(false);
		splitPane.setRightComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		
		panel2_2 = new JPanel();
		panel2_2.setOpaque(false);
		panel2.add(panel2_2, BorderLayout.NORTH);
		panel2_2.setLayout(new GridLayout(1, 2, 0, 0));
//		panel2_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		panel_13 = new JPanel();
		panel_13.setOpaque(false);
		panel2_2.add(panel_13);
		panel_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblUe_1 = new JLabel("UE参数录入");
		lblUe_1.setFont(MyTools.f1);
		panel_13.add(lblUe_1);
		/***************************************
		 * 右栏标题行
		 * 功能：实时读取数据库中BBUPOOL,BBU,RRU,UE的个数，显示出来
		 * ************************************/
		panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel2_2.add(panel_12);
		{
			int temp_bbupool_num=bbupool_num;//bbupool_num一共有多少个bbupool
			String string_bbupool_num=Integer.toString(temp_bbupool_num);
			lable_BBUPool=new JLabel();
			lable_BBUPool.setFont(MyTools.f15);
			lable_BBUPool.setText(string_bbupool_num);
		}
		{
			//需要遍历数据库
			String sql="exec RowNum ?";
			String []paras={"Bbu"};
			sql_allnum_model=new UE_table_module2();
			ResultSet Bbu_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
			try {
				Bbu_allnum_sql.next();
				bbu_num_total=Bbu_allnum_sql.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			int temp_bbu_num=bbu_num_total;
			String string_bbu_num=Integer.toString(temp_bbu_num);//数据库中bbu总数
			lable_BBU=new JLabel();
			lable_BBU.setFont(MyTools.f15);
			lable_BBU.setText(string_bbu_num);
		}
		{
			//需要遍历数据库	
			String sql="exec RowNum ?";
			String []paras={"Rru"};
			sql_allnum_model=new UE_table_module2();
			ResultSet Rru_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
			try {
				Rru_allnum_sql.next();
				rru_num_total=Rru_allnum_sql.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			int temp_rru_num=rru_num_total;
			String string_rru_num=Integer.toString(temp_rru_num);//数据库中rru总数
			lable_RRU=new JLabel();
			lable_RRU.setFont(MyTools.f15);
			lable_RRU.setText(string_rru_num);
		}
		{
			//需要遍历数据库
			String sql="exec RowNum ?";
			String []paras={"Ue"};
			sql_allnum_model=new UE_table_module2();
			ResultSet Ue_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
			try {
				Ue_allnum_sql.next();
				ue_num_total=Ue_allnum_sql.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			int temp_ue_num=ue_num_total;
			String string_ue_num=Integer.toString(temp_ue_num);
			lable_Ue=new JLabel();
			lable_Ue.setFont(MyTools.f15);
			lable_Ue.setText(string_ue_num);
		}

		panel_12.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		String path_BBUPool="image"+File.separator+"public"+File.separator+"bbu池个数按钮.png";
		Icon icon_BBUPool=new ImageIcon(path_BBUPool);
		JLabel lblBbu_1 = new JLabel(icon_BBUPool);//可以利用生成左栏时的数据bbupool_num
		lblBbu_1.setPreferredSize(new Dimension(icon_BBUPool.getIconWidth(), icon_BBUPool.getIconHeight()));
		panel_12.add(lblBbu_1);
		lblBbu_1.setFont(MyTools.f15);
		panel_12.add(lable_BBUPool);
		JLabel lable_blank=new JLabel(" ");//空格
		panel_12.add(lable_blank);
		
		String path_BBU="image"+File.separator+"public"+File.separator+"bbu个数按钮.png";
		Icon icon_BBU=new ImageIcon(path_BBU);
		JLabel lblBbu_2 = new JLabel(icon_BBU);//可以利用生成左栏时的数据bbupool_num
		lblBbu_2.setPreferredSize(new Dimension(icon_BBU.getIconWidth(), icon_BBU.getIconHeight()));
		panel_12.add(lblBbu_2);
		lblBbu_2.setFont(MyTools.f15);
		panel_12.add(lable_BBU);
		lable_blank=new JLabel(" ");//空格
		panel_12.add(lable_blank);
		
		String path_RRU="image"+File.separator+"public"+File.separator+"rru个数按钮.png";
		Icon icon_RRU=new ImageIcon(path_RRU);
		JLabel lblRru = new JLabel(icon_RRU);//可以利用生成左栏时的数据bbupool_num
		lblRru.setPreferredSize(new Dimension(icon_RRU.getIconWidth(), icon_RRU.getIconHeight()));
		panel_12.add(lblRru);
		lblRru.setFont(MyTools.f15);
		panel_12.add(lable_RRU);
		lable_blank=new JLabel(" ");//空格
		panel_12.add(lable_blank);
		
		String path_UE="image"+File.separator+"public"+File.separator+"ue个数按钮.png";
		Icon icon_UE=new ImageIcon(path_UE);
		JLabel lblUe = new JLabel(icon_UE);//可以利用生成左栏时的数据bbupool_num
		lblUe.setPreferredSize(new Dimension(icon_UE.getIconWidth(), icon_UE.getIconHeight()));
		panel_12.add(lblUe);
		lblUe.setFont(MyTools.f15);
		panel_12.add(lable_Ue);
		lable_blank=new JLabel(" ");//空格
		panel_12.add(lable_blank);
		/***************************************
		 * 两个JTable表格
		 * ************************************/		
		panel = new JPanel();
		panel.setOpaque(false);
		panel2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		/***********上面表格：显示UE群的列表及其相关控件**********/
		panel_3 = new JPanel();
		panel_3.setOpaque(false);
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_4.setOpaque(false);
		panel_3.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_4.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		button_1 = new JButton("添加");
		button_1.setFont(MyTools.f14);
		button_1.addActionListener(this);
		panel_8.add(button_1);
		
		button_2 = new JButton("保存");
		button_2.setFont(MyTools.f14);
		button_2.addActionListener(this);
		panel_8.add(button_2);
		
		panel_9 = new JPanel();
		panel_9.setOpaque(false);
		panel_4.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		button = new JButton("删除");
		button.setFont(MyTools.f14);
		button.addActionListener(this);
		
		button_3 = new JButton("修改");
		button_3.setFont(MyTools.f14);
		button_3.addActionListener(this);
		panel_9.add(button_3);
		panel_9.add(button);
		
		//ue_table显示UE表格的
		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel_5.setLayout(new BorderLayout());
		panel_3.add(panel_5, BorderLayout.CENTER);
		ue1_table=new UE_table_module1();
		jt1=new JTable(ue1_table);
		
		//JTable的背景颜色设置
		jt1.setBackground(new Color(240, 245, 250));

		jt1.setRowHeight(20);//修改表格宽度
		//获得表头
		JTableHeader tableH = jt1.getTableHeader();
		tableH.setFont(MyTools.f12);
		tableH.setBackground(new Color(204, 208, 213));
		  
		DefaultTableCellRenderer r =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		r.setFont(MyTools.f12);
		jt1.setDefaultRenderer(Object.class,r);
		jt1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		jt1.setRowHeight(20);
		jt1.setRowSelectionAllowed(true);
		selectionMode1 = jt1.getSelectionModel();
		selectionMode1.addListSelectionListener(this);
		//初始化jsp
		jsp1=new JScrollPane(jt1);
		jsp1.getViewport().setBackground(Color.white);
		//把jsp加入到JFrame中
		panel_5.add(jsp1);
		
		/***********下面表格：显示UE的列表及其相关控件**********/
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_2.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		

		btnNewButton_2 = new JButton("添加");
		btnNewButton_2.setFont(MyTools.f14);
		btnNewButton_2.addActionListener(this);
		panel_10.add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("保存");
		btnNewButton_1.setFont(MyTools.f14);
		btnNewButton_1.addActionListener(this);
		panel_10.add(btnNewButton_1);
		
		panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_6.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnNewButton = new JButton("删除");
		btnNewButton.setFont(MyTools.f14);
		btnNewButton.addActionListener(this);
		
		button_4 = new JButton("修改");
		button_4.setFont(MyTools.f14);
		button_4.addActionListener(this);
		panel_11.add(button_4);
		panel_11.add(btnNewButton);
		
		//ue2_table显示UE表格的
		panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_7.setLayout(new BorderLayout());
		panel_2.add(panel_7, BorderLayout.CENTER);
		ue2_table=new UE_table_module2();
		jt2=new JTable(ue2_table);
		
		//JTable的背景颜色设置
		jt2.setBackground(new Color(240, 245, 250));

		jt2.setRowHeight(20);//修改表格宽度
		//获得表头
		JTableHeader tableH2 = jt2.getTableHeader();
		tableH2.setFont(MyTools.f12);
		tableH2.setBackground(new Color(204, 208, 213));
//		tableH2.setBackground(new Color(255, 255, 255));
		
		DefaultTableCellRenderer r2 =   new   DefaultTableCellRenderer();   
		r2.setHorizontalAlignment(JLabel.CENTER);   
		r2.setFont(MyTools.f12);
		jt2.setDefaultRenderer(Object.class,r2);
		jt2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt2.setRowHeight(20);
		//初始化jsp
		jsp2=new JScrollPane(jt2);
		jsp2.getViewport().setBackground(Color.white);
		//把jsp加入到JFrame中
		panel_7.add(jsp2);
		//刷新展示的表格jt1
		String sql2="select * from UeGroup where 1=?";//再从UeGroup中调出添加的数据	
		String []paras2={"1"};//可以显示所有的UeGroup  
		ue1_table=new UE_table_module1();
		ue1_table.table_search(sql2,paras2);
		jt1.setModel(ue1_table);
		already_exist_row1=jt1.getRowCount();
//		int modify_row1=already_exist_row1;
//		ue1_table.For_modify(modify_row1);//将所有已经添加过的置为不可修改
		//刷新展示的表格jt2
		String sql4="select * from Ue where 1=?";//再从UE中调出添加的数据		
		String []paras4={"1"};//可以显示所有已经添加的UE
		ue2_table=new UE_table_module2();
		ue2_table.table_search(sql4,paras4);
		jt2.setModel(ue2_table);
		already_exist_row2=jt2.getRowCount();
//		int modify_row2=already_exist_row2;
//		ue2_table.For_modify(modify_row2);//将所有已经添加过的置为不可修改
		{
			/**************************************
			 * 添加退出按钮
			 ***************************************/
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setForeground(Color.white);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("退出");
				cancelButton.setFont(MyTools.f14);
				cancelButton.setActionCommand("退出");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	/**************************************
	 * 功能：创建左侧的目录树
	 ***************************************/
	private void build_tree() {
		// TODO 自动生成的方法存根
		//树形列表
		//根节点
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("根节点");
		
		//一级节点
		String sql="select * from BbuPool where 1=?";
		String []paras={"1"};
		bbuc=new Num_search_module();
		bbuc.search_num(sql, paras,1);//只需要BBU池的第一列数据，获取BBU池的ID号,因为bbu池的id号是不连续的。
		bbupool_num=bbuc.getRowCount();
		for(int i=0;i<bbupool_num;i++)//i这里表示第几行，但是行数不等于ID号
		{
			DefaultMutableTreeNode node_first = new DefaultMutableTreeNode("BBU池"+bbuc.getValueAt(i, 0));
			//二级节点
			String sql2="select * from Bbu where BbuPoolId=?";
			//int temp=i;
			//String BbuPoolId=String.valueOf(temp);//转换后int temp——>String temp了，所以不能直接用int i，要用一个临时变量temp
//			String BbuPoolId=Integer.toString(i);//int类型转换成string类型
			String BbuPoolId=(String) bbuc.getValueAt(i, 0);
			String []paras2={BbuPoolId};
			bbu=new Num_search_module();
			bbu.search_num(sql2, paras2,2);//需要BBU的两列数据，获取BBU的ID号，和BBU池的ID号
			int bbu_num=bbu.getRowCount();
			for(int j=0;j<bbu_num;j++)
			{
				DefaultMutableTreeNode node_second = new DefaultMutableTreeNode("BBU"+bbu.getValueAt(j, 0),false);
				
				node_first.add(node_second);
			}
			root.add(node_first);
		}
		//利用根节点直接创建树Treeroot
		Treeroot=new JTree(root);
		TreeSelectionModel treeSelectionModel;//获得树的选择模式
		treeSelectionModel=Treeroot.getSelectionModel();
		//设置树的选择模式为单选
		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		panel_1.add(Treeroot, BorderLayout.CENTER);
		//定制树
		Treeroot.setRootVisible(false);//无根节点
		Treeroot.setRowHeight(20);//树节点的行高
		DefaultTreeCellRenderer treeCellRenderer=new DefaultTreeCellRenderer();//获得树节点的绘制对象
		treeCellRenderer=(DefaultTreeCellRenderer)Treeroot.getCellRenderer();
		treeCellRenderer.setLeafIcon(new ImageIcon("image"+File.separator +"LeafIcon.png"));
		treeCellRenderer.setClosedIcon(new ImageIcon("image"+File.separator +"ClosedIcon.png"));
		treeCellRenderer.setOpenIcon(new ImageIcon("image"+File.separator +"OpenIcon.png"));
		Treeroot.setFont(MyTools.f14);
		//设置树默认为展开模式
		//按前序遍历所有节点
		Enumeration<?>enumaration;
		enumaration=root.preorderEnumeration();
		while(enumaration.hasMoreElements())
		{
			DefaultMutableTreeNode node;
			node=(DefaultMutableTreeNode)enumaration.nextElement();
			if(!node.isLeaf())
			{
				TreePath path=new TreePath(node.getPath());
				Treeroot.expandPath(path);
			}
		}
	}
	
	/**************************************
	 * 功能：界面上各个按钮的监听响应功能
	 ***************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		/***************添加UE群按钮:添加ue1******************/
		if(arg0.getSource()==button_1)
		{
			ue_add_rownum1=ue_add_rownum1+1;//表格里面添加新数据的行数
			ue1_table.addRows(ue_add_rownum1);
			ue1_table.fireTableRowsInserted(ue_add_row_alreaday1, ue_add_row_alreaday1+1);
			jt1.setModel(ue1_table);
				
			
			jcb1=new JComboBox<>(jg1);
			jcb2=new JComboBox<>(jg2);	
			jcb1.setEditable(false);
			jcb2.setEditable(false);
			jt1.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcb1)); //列是从0开始数的
			jt1.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jcb2)); //列是从0开始数的
		}
		/***************保存UE群按钮：保存ue1******************/
		else if(arg0.getSource()==button_2)
		{
			if(ue_add_rownum1!=0)
			{
				//判断X,Y坐标不能不填写，否则，不能保存
				boolean judge=true;
				for(int j=(already_exist_row1+ue_add_row_alreaday1);j<(already_exist_row1+ue_add_row_alreaday1)+ue_add_rownum1;j++)
				{
					String X=(String) ue1_table.getValueAt(j, 2);//X
					String Y=(String) ue1_table.getValueAt(j, 3);//Y
					String R=(String) ue1_table.getValueAt(j, 5);//R
					if(X==null||Y==null||R==null||X.length()==0||Y.length()==0||R.length()==0)
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"中心X坐标、中心Y坐标、半径R不能为空");
						break;
					}
					String UEnum=(String) ue1_table.getValueAt(j, 1);//UeNum
					boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(R)&isNumeric(UEnum);
					if(!(temp))
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"请填写数字！");
						return;
					}
				}
				if(judge==true)	
				{
					try {
						jt1.getCellEditor().stopCellEditing();
					} catch (Exception e) {
						// TODO: handle exception
					}
					for(int i=already_exist_row1+ue_add_row_alreaday1;i<already_exist_row1+ue_add_row_alreaday1+ue_add_rownum1;i++)
					{
						//保存到UE群表格中
						String []paras=new String[8];
						paras[0]=(String) ue1_table.getValueAt(i, 0);//UE群ID
						paras[1]=(String) ue1_table.getValueAt(i, 5);//R
						paras[2]=(String) ue1_table.getValueAt(i, 2);//X
						paras[3]=(String) ue1_table.getValueAt(i, 3);//Y
						paras[4]="5";//UE群分布方式,/////////////////////////////////////UE群分布方式数据库中存的是5，但是应该存“随机分布”	
						if(ue1_table.getValueAt(i, 6).equals("室外"))//室内外信息4，1：室内，0：室外
						{
							paras[5]="0";
						}else if(ue1_table.getValueAt(i, 6).equals("室内"))
						{
							paras[5]="1";
						}
						paras[6]=(String) ue1_table.getValueAt(i, 7);//天线类型5
						paras[7]=(String) ue1_table.getValueAt(i, 1);//UeNum
						String sql="insert into UeGroup values(?,?,?,?,?,?,?,?)";
						b=ue1_table.add_delete_change_to_sql(sql, paras);
						//保存到UE表格中
						//根据分布方式得到X,Y坐标
						int X=Integer.parseInt((String) ue1_table.getValueAt(i, 2));
//						System.out.println((String) ue1_table.getValueAt(i, 2));
//						System.out.println(X);//坐标只能输入整数
						int Y=Integer.parseInt((String) ue1_table.getValueAt(i, 3));
						int R= Integer.parseInt((String) ue1_table.getValueAt(i, 5));
						Random random = new Random();
						int num_uegroup = Integer.parseInt( (String) ue1_table.getValueAt(i,1));
						for(int j=0;j<num_uegroup;j++)
						{
							String []paras1=new String[21];
							paras1[0]=get_ueid();//ue id
							if(ue1_table.getValueAt(i, 6).equals("室外")){
								paras1[1]="0";
							}else if(ue1_table.getValueAt(i, 6).equals("室内"))
							{
								paras1[1]="1";
							}
//							paras1[1]=(String) ue1_table.getValueAt(i, 6);//UE分布方式,1：室内，0：室外
							paras1[2]=String.valueOf(random.nextInt(2*R)+X-R);//X
							paras1[3]=String.valueOf(random.nextInt(2*R)+Y-R);//Y
							paras1[4]="0";//Z
							paras1[5]="-1";//从属RRu的编号
							paras1[6]=null;//分配的RB数目
							paras1[7]=null;//发射功率
							paras1[8]=(String) ue1_table.getValueAt(i, 7);//天线类型
							paras1[9]=null;//用户是否处于活跃状态
							paras1[10]=(String) ue1_table.getValueAt(i, 0);;//UE群ID
							paras1[11]=null;
							paras1[12]=null;
							paras1[13]=null;
							paras1[14]=null;
							paras1[15]=null;
							paras1[16]=null;
							paras1[17]=null;
							paras1[18]=null;
							paras1[19]=null;
							paras1[20]=null;
							String sql1="insert into ue values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//							String sql1="insert into ue ([UeId],[UeType],[X],[Y],[Z],[ServiceRruId],[RbNum],[UeTransPower],[UeAntennaId],[IsActivity],[UeGroupId],[ModelType],[SNR],[TotalBit],[TTISent],[AverageRate],[BbuId],[TotalRb],[Dis],[Priority],[WorkType])values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							b=ue1_table.add_delete_change_to_sql(sql1, paras1);
						}
						
					}

					//刷新展示的表格jt1
					String sql2="select * from UeGroup where 1=?";//再从UeGroup中调出添加的数据	
					String []paras2={"1"};//可以显示所有的UeGroup  
					ue1_table=new UE_table_module1();
					ue1_table.table_search(sql2,paras2);
					jt1.setModel(ue1_table);
					//刷新展示的表格jt2
					String sql4="select * from Ue where 1=?";//再从UE中调出添加的数据		
					String []paras4={"1"};//可以显示所有已经添加的UE
					ue2_table=new UE_table_module2();
					ue2_table.table_search(sql4,paras4);
					jt2.setModel(ue2_table);
					
					//刷新显示UE总数的textfield
					String sql3="exec RowNum ?";
					String []paras3={"Ue"};
					String string_ue_num1=Update_totalnum(sql3,paras3);
//					textField_3.setText(string_ue_num1);
					lable_Ue.setText(string_ue_num1);
					if(b==true)
					{
						//保存成功
						JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
					}
					ue_add_row_alreaday1=ue_add_row_alreaday1+ue_add_rownum1;//已经添加过的+这次新添加的
//					int modify_row1=already_exist_row1+ue_add_row_alreaday1;
//					ue1_table.For_modify(modify_row1);//将所有已经添加过的置为不可修改
					ue_add_rownum1=0;
				}
			
			}
		}
		/***************修改UE群按钮******************/
		else if(arg0.getSource()==button_3)
		{
			int rowNum=this.jt1.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else
			{
				new UE1_modify(this, "修改", true, ue1_table, rowNum);
				//刷新展示的表格jt1
				String sql1="select * from UeGroup where 1=?";//再从UeGroup中调出添加的数据	
				String []paras1={"1"};//可以显示所有的UeGroup  
				ue1_table=new UE_table_module1();
				ue1_table.table_search(sql1,paras1);
				jt1.setModel(ue1_table);	
				//刷新展示的表格jt2
				String sql4="select * from Ue where 1=?";//再从UE中调出添加的数据		
				String []paras4={"1"};//可以显示所有已经添加的UE
				ue2_table=new UE_table_module2();
				ue2_table.table_search(sql4,paras4);
				jt2.setModel(ue2_table);
			}
		}
		/***************删除UE群按钮，先删除UE群，再删除UE群对应的UE******************/
		else if(arg0.getSource()==button)
		{
			int rowNum=this.jt1.getSelectedRow();//返回用户点中的行
			//判断UE群数据库是否有数据，如果数据库里面还没有数据，会提示：请先保存再删除。
			String sql4="exec RowNum ?";
			String []paras4={"UeGroup"};
			String string_uegroup_num=Update_totalnum(sql4,paras4);//得到目前数据库中总行数
			//System.out.println(string_uegroup_num);
			if((rowNum==-1))//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else if(string_uegroup_num.equals("0"))
			{
				//提示
				JOptionPane.showMessageDialog(this,"数据库没有数据");
				return;
			}
			else 
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除UE群，会对应删除UE群下的所有UE", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					//先删除UE群，再删除UE
					String UEgroup_ID=(String) ue1_table.getValueAt(rowNum,0);
//					System.out.println(UEgroup_ID);
					String sql="exec Drop_UeGroup ?";//这句话既删除了UE群，又删除了UE
					String []paras={UEgroup_ID};
					b=ue1_table.add_delete_change_to_sql(sql, paras);
					//刷新展示的表格jt1
					String sql1="select * from UeGroup where 1=?";//再从UeGroup中调出添加的数据	
					String []paras1={"1"};//可以显示所有的UeGroup  
					ue1_table=new UE_table_module1();
					ue1_table.table_search(sql1,paras1);
					jt1.setModel(ue1_table);
					//刷新展示的表格jt2
					String sql5="select * from Ue where 1=?";//再从UE中调出添加的数据		
					String []paras5={"1"};//可以显示所有已经添加的UE
					ue2_table=new UE_table_module2();
					ue2_table.table_search(sql5,paras5);
					jt2.setModel(ue2_table);
					
					//刷新显示UE总数的texrfield
					String sql2="exec RowNum ?";
					String []paras2={"Ue"};
					String string_ue_num=Update_totalnum(sql2,paras2);//更新了数据库中ue总数
//					textField_3.setText(string_ue_num);
					lable_Ue.setText(string_ue_num);
					if(b==true)
					{
						//删除成功
						JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
					}
					ue_add_row_alreaday1=ue_add_row_alreaday1-1;
					ue_add_rownum1=0;
				}
			}
		}
/***************添加UE按钮：ue2的添加******************/
	else if(arg0.getSource()==btnNewButton_2)
		{
			ue_add_rownum2=ue_add_rownum2+1;//表格里面添加新数据的行数	
			ue2_table.addRows(ue_add_rownum2);
			ue2_table.fireTableRowsInserted(ue_add_row_alreaday2, ue_add_row_alreaday2+1);
			jt2.setModel(ue2_table);
			
			jcb1=new JComboBox<>(jg1);
			jcb2=new JComboBox<>(jg2);	
			jcb1.setEditable(true);
			jcb2.setEditable(true);
			jt2.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jcb1)); //列是从0开始数的
			jt2.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(jcb2)); //列是从0开始数的
			jt2.setRowHeight(20);
			//jcb3表格2最后一列ue群id
			jg3=new Vector<>();
			jg3.add("-1(不添加到任何UeGroup当中)");//我想表达“空”的意思，但是，数据库不能存入文字，所以用11111代替
			String sql2="select * from UeGroup where 1=?";//	
			String []paras2={"1"};//
			BBU_sql uegroup_sql=new BBU_sql();
			ResultSet uegroup_rs=uegroup_sql.search(sql2, paras2);
			try {
				while(uegroup_rs.next())
				{
					jg3.add(uegroup_rs.getString(1));//ue群id一共有哪些
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}finally
			{
				//关闭
				uegroup_sql.close();
			}				
			jcb3=new JComboBox<>(jg3);			
			jcb3.setEditable(false);			
			jt2.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcb3)); //列是从0开始数的
		}
		 /***************保存UE按钮：ue2的保存******************/
	else if(arg0.getSource()==btnNewButton_1)
		{
			if(ue_add_rownum2!=0)//如果有添加
			{
				//判断X,Y,Z坐标不能不填写，否则，不能保存
				boolean judge=true;
				for(int j=already_exist_row2+ue_add_row_alreaday2;j<already_exist_row2+ue_add_row_alreaday2+ue_add_rownum2;j++)
				{
					String X=(String) ue2_table.getValueAt(j, 1);//X
					String Y=(String) ue2_table.getValueAt(j, 2);//Y
					String Z=(String) ue2_table.getValueAt(j, 3);//Z
					if(X==null||Y==null||Z==null)
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"X,Y,Z坐标不能为空");
						break;
					}
					boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z);
					if(!(temp))
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"请填写数字！");
						return;
					}
				}
				if(judge==true)	
				{
					try {
						jt2.getCellEditor().stopCellEditing();
					} catch (Exception e) {
						// TODO: handle exception
					}
					//保存到数据库
					for(int i=already_exist_row2+ue_add_row_alreaday1*10+ue_add_row_alreaday2;i<already_exist_row2+ue_add_row_alreaday1*10+ue_add_row_alreaday2+ue_add_rownum2;i++)
					{
						
						String []paras1=new String[11];
						paras1[0]=(String) ue2_table.getValueAt(i, 0);//ue id
//						paras1[1]=(String) ue2_table.getValueAt(i, 4);//UE分布方式：室内，室外
						if(ue2_table.getValueAt(i, 4).equals("室外"))
						{
							paras1[1]="0";
						}else if(ue2_table.getValueAt(i, 4).equals("室内"))
						{
							paras1[1]="1";
						}
						paras1[2]=(String) ue2_table.getValueAt(i, 1);//X
						paras1[3]=(String) ue2_table.getValueAt(i, 2);//Y
						paras1[4]=(String) ue2_table.getValueAt(i, 3);//Z
						paras1[5]="-1";//从属RRu的编号
						paras1[6]=null;//分配的RB数目
						paras1[7]=null;//发射功率
						paras1[8]=(String) ue2_table.getValueAt(i, 5);//天线Id
						paras1[9]=null;//用户是否处于活跃状态
						if(ue2_table.getValueAt(i, 6).equals("-1(不添加到任何UeGroup当中)"))
						{
							 ue2_table.setValueAt("-1",i, 6);
						}
						paras1[10]=(String) ue2_table.getValueAt(i, 6);//UE群ID
						String sql1="exec Add_Ue ?,?,?,?,?,?,?,?,?,?,?";//还是要一行一行的添加   insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
						b=ue2_table.add_delete_change_to_sql(sql1, paras1);
						//根据每一行添加的ue群id，因为表格2的添加，在uegroup表格里面把表格1对应的UeNum对应值增加
//						if((boolean)ue2_table.getValueAt(i, 6).equals("111"))//此ue不添加到任何ue群
//						{
//							continue;
//						}else//此ue添加到指定ue群
//						{
//							//查询当前对应ue群的UeNum是多少
//							System.out.println("ok");
//							String sql7="select UeNum from UeGroup where UeGroupId=?";
//							String []paras7={(String) ue2_table.getValueAt(i, 6)};
//							BBU_sql UeNum_sql=new BBU_sql();
//							ResultSet UeNum_rs=UeNum_sql.search(sql7, paras7);
//							try {
//								while(UeNum_rs.next())
//								{
//									uenum=UeNum_rs.getInt(1);//当前对应ue群的UeNum
//								}
//							} catch (SQLException e) {
//								// TODO 自动生成的 catch 块
//								e.printStackTrace();
//							}finally
//							{
//								//关闭
//								UeNum_sql.close();
//							}		
//							String sql6="update UeGroup set UeNum=? where UeGroupId=?";
//							String []paras6={String.valueOf(uenum+1),(String) ue2_table.getValueAt(i, 6)};
//							ue1_table=new UE_table_module1();
//							ue1_table.add_delete_change_to_sql(sql6, paras6);
//						}
//						
					}
				
					//刷新展示的表格jt2
					String sql5="select * from Ue where 1=?";//再从UE中调出添加的数据		
					String []paras5={"1"};//可以显示所有已经添加的UE
					ue2_table=new UE_table_module2();
					ue2_table.table_search(sql5,paras5);
					jt2.setModel(ue2_table);
					
					//刷新显示UE总数的textfield
					String sql3="exec RowNum ?";
					String []paras3={"Ue"};
					String string_ue_num2=Update_totalnum(sql3,paras3);////更新了数据库中ue总数
//					textField_3.setText(string_ue_num2);
					lable_Ue.setText(string_ue_num2);
					//刷新jt1
					String sql8="select * from UeGroup where 1=?";//再从UE中调出添加的数据		
					String []paras8={"1"};//可以显示所有已经添加的UE
					ue1_table=new UE_table_module1();
					ue1_table.table_search(sql8,paras8);
					jt1.setModel(ue1_table);
					if(b==true)
					{
						//保存成功
						JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
					}
				ue_add_row_alreaday2=ue_add_row_alreaday2+ue_add_rownum2;//已经添加过的+这次新添加的
				ue_add_rownum2=0;
				}
				
		}
		}
		/***************修改UE按钮：ue2的修改******************/
		else if(arg0.getSource()==button_4)
		{
			int rowNum=this.jt2.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else
			{
				new UE2_modify(this, "修改", true, ue2_table, rowNum);
				//刷新展示的表格jt1
				String sql1="select * from Ue where 1=?";//再从UeGroup中调出添加的数据	
				String []paras1={"1"};//可以显示所有的UeGroup  
				ue2_table=new UE_table_module2();
				ue2_table.table_search(sql1,paras1);
				jt2.setModel(ue2_table);		
			}
		}
		/***************删除UE按钮：ue2的删除******************/
		else if(arg0.getSource()==btnNewButton)
		{
			int rowNum=this.jt2.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行,或者一行都没有添加
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}	
			else 
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除RRU", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					String ID=(String) ue2_table.getValueAt(rowNum,0);
					String sql="exec Drop_Ue ?";
					String []paras={ID};
					b=ue2_table.add_delete_change_to_sql(sql, paras);
//					//
//					String sql1="select * from Ue where UeId>?";//再从UE中调出添加的数据		
//					String []paras1={ue_add_first};//可以显示所有已经添加的UE
//					ue2_table=new UE_table_module2();
//					ue2_table.table_search(sql1,paras1);
//					jt2.setModel(ue2_table);
					//刷新展示的表格jt2
					String sql5="select * from Ue where 1=?";//再从UE中调出添加的数据		
					String []paras5={"1"};//可以显示所有已经添加的UE
					ue2_table=new UE_table_module2();
					ue2_table.table_search(sql5,paras5);
					jt2.setModel(ue2_table);
					//刷新显示bbu总数的texrfield
					String sql2="exec RowNum ?";
					String []paras2={"Ue"};
					String string_ue_num=Update_totalnum(sql2,paras2);//更新了数据库中ue总数
//					textField_3.setText(string_bbu_num);
					lable_Ue.setText(string_ue_num);
					if(b==true)
					{
						//删除成功
						JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
					}
					ue_add_row_alreaday2=ue_add_row_alreaday2-1;
					ue_add_rownum2=0;
				}
			}
		}
		else if(arg0.getSource()==cancelButton)
		{
			System.out.println("ok");
			this.dispose();//关闭对话框
		}
		
	}

	public static boolean isNumeric(String str){ 
		if(str==null)
		{
			return true;
		}
		if(str=="")
		{
			return true;
		}
		int index = str.indexOf(".");
		if(index<0)//若输入的是整数
		{
			for (int i = str.length();--i>=0;)
			  {    
				   if (!Character.isDigit(str.charAt(i)))
				   {  
				    return false;  
				   }
			   }
		}
		else{//若输入的是小数
			String num1 = str.substring(0, index); 
			String num2 = str.substring(index + 1);
			String num=num1+num2;
			for (int i = num.length();--i>=0;)
			  {    
				   if (!Character.isDigit(num.charAt(i)))
				   {  
				    return false;  
				   }
			   }
		}
		  return true;  
		} 

	/***************实时读取数据库中对象个数，更新总数的函数******************/
	private String Update_totalnum(String sql, String[] paras) {//用来更新总数Textfield
		// TODO 自动生成的方法存根
		sql_allnum_model=new UE_table_module2();
		ResultSet allnum_sql=sql_allnum_model.total_num_sql(sql,paras);
		try {
			allnum_sql.next();
			total_num=allnum_sql.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int temp_num=total_num;
		String string_num_total=Integer.toString(temp_num);//数据库中ue总数
		return string_num_total;
	}
	/***************得到ue表中即将添加的UE ID号******************/
	public String get_ueid()
	{
		String sql="exec RowMax ?";
		String []paras={"Ue"};
		ue1_table_uemaxid=new UE_table_module1();
		ResultSet Ue_max_id=ue1_table_uemaxid.total_num_sql(sql, paras);//ue_max_id返回ue当前最大id号
		
		Vector hang=new Vector<>();
		try {
			Ue_max_id.next();
			int temp=Ue_max_id.getInt(1)+1;
			ue_id_max=Integer.toString(temp); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ue_id_max;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自动生成的方法存根
//		if(e.getSource()==selectionMode1)
//		{
//			int rowNum=this.jt1.getSelectedRow();//返回用户点中的行
//			if(rowNum!=-1)//只有当用户有选中行时，才进入
//			{
//				jt1_uegroup_id=(String) jt1.getValueAt(rowNum,0);//jt1中选中的UE群ID号
//				jt2_ue_count=jt2.getRowCount();//jt2_ue_count表格2一共有多少行	
//				//刷新展示的表格jt2
//				String sql5="select * from Ue where UeGroupId=?";//再从UE中调出添加的数据		
//				String []paras5={jt1_uegroup_id};//可以显示所有已经添加的UE
//				ue2_table=new UE_table_module2();
//				ue2_table.table_search(sql5,paras5);
//				jt2.setModel(ue2_table);
//			}
//			else
//			{
//				//刷新展示的表格jt2
//				String sql5="select * from Ue where 1=?";//再从UE中调出添加的数据		
//				String []paras5={"1"};//可以显示所有已经添加的UE
//				ue2_table=new UE_table_module2();
//				ue2_table.table_search(sql5,paras5);
//				jt2.setModel(ue2_table);
//			}
//		}
		}
	}
