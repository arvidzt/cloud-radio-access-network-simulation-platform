/*
 * 类名：BBU2RRU_Link
 * 功能：BBU与RRU链路部署界面
 */
package ui.Add_link;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ui.Share_bag.BBU_sql;
import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;
import ui.Share_bag.Num_search_module;

public class BBU2RRU_Link extends JDialog implements ActionListener{
	boolean b;
	private JPanel contentPanel;
	JLabel label2;
	JTree Treeroot=null;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	Num_search_module bbuc,bbu;
	int bbupool_num;
	JPanel panel_1,panel_3,panel_4,panel,panel_2,panel_5,panel_6,panel_7,panel_8,panel_9,panel_10,buttonPane;
	JScrollPane jsp1,jsp2;
	private JLabel label;
	private JButton button,button_1,button_2,button_3,cancelButton;
	JTable jt;
	BBU2RRU_Link_module BBU2RRU_Link_table,sql_allnum_model;
	JComboBox jcb1,jcb2,jcb3,jcb4;//下拉列表
	String []jg1={"环型","总线型"};//链路类型数组，不填写默认是0,0：环型，1：总线型
	int BBU2RRU_Link_add_rownum;
	Vector jg2=null;
	Vector jg3=null;
	Vector jg4=null;
	int total_num;
	int bbu_num_total,rru_num_total,ue_num_total;//数据库中一共有多少个bbu
	JLabel lableBBUPool,lableBBU,lableRRU,lableUE;
	JPanel panel_12;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			BBU2RRU_Link dialog = new BBU2RRU_Link();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**************************************
	 * 功能：构造链路部署界面
	 ***************************************/
	public BBU2RRU_Link(Frame owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public BBU2RRU_Link() {
		setTitle("BBU与RRU链路输入");

		setBounds(170, 20, 1000, 700);//窗体位置
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());

		
		panel_1 = new JPanel();
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(140);
		panel_1.add(splitPane, BorderLayout.CENTER);
/***************************************左栏************************************/		
		panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));	
		
		String path_10="image"+File.separator+"ADD_Link"+File.separator+"Link_Left.png";
		Image image_10=new ImageIcon(path_10).getImage(); 
		panel_10= new BackgroundPanel(image_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		label2 = new JLabel("网元列表");
		label2.setFont(MyTools.f1);
//		label2.setForeground(Color.white);
		panel_10.add(label2);
		panel_3.add(panel_10, BorderLayout.NORTH);
		
		panel_4 = new JPanel();		
		panel_4.setBackground(Color.white);
		build_tree();
		panel_4.add(Treeroot, BorderLayout.CENTER);
		jsp1=new JScrollPane(panel_4);
		panel_3.add(jsp1, BorderLayout.CENTER);
/*****************************************右栏************************************/		
		String path_2="image"+File.separator+"ADD_Link"+File.separator+"Link_Right.png";
		Image image_2=new ImageIcon(path_2).getImage(); 
		panel= new BackgroundPanel(image_2);
//		panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
//		String path_2="image"+File.separator+"ADD_Link"+File.separator+"ADD_BBU_Right.png";
//		Image image_2=new ImageIcon(path_2).getImage(); 
//		panel_2 = new BackgroundPanel(image_2);
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout((new BorderLayout()));
		/***************************************
		 * 右栏标题行
		 * 功能：实时读取数据库中BBUPOOL,BBU,RRU,UE的个数，显示出来
		 * ************************************/
		{
			JPanel panel_BBU_RRU_UE=new JPanel();
			panel_BBU_RRU_UE.setOpaque(false);
			panel_BBU_RRU_UE.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			panel_2.add(panel_BBU_RRU_UE,BorderLayout.EAST);
			{
				String path_BBUPool="image"+File.separator+"public"+File.separator+"bbu池个数按钮.png";
				Icon icon_BBUPool=new ImageIcon(path_BBUPool);
				JLabel lblBbu_1 = new JLabel(icon_BBUPool);//可以利用生成左栏时的数据bbupool_num
				lblBbu_1.setPreferredSize(new Dimension(icon_BBUPool.getIconWidth(), icon_BBUPool.getIconHeight()));
				panel_BBU_RRU_UE.add(lblBbu_1);
				int temp_bbupool_num=bbupool_num;//bbupool_num一共有多少个bbupool
				String string_bbupool_num=Integer.toString(temp_bbupool_num);
				lableBBUPool = new JLabel(string_bbupool_num);
				lableBBUPool.setFont(MyTools.f15);
				panel_BBU_RRU_UE.add(lableBBUPool);
				
				JLabel lable_blank=new JLabel(" ");//空格
				panel_BBU_RRU_UE.add(lable_blank);
				
			}
			{
				String path_BBU="image"+File.separator+"public"+File.separator+"bbu个数按钮.png";
				Icon icon_BBU=new ImageIcon(path_BBU);
				JLabel lblBbu_2 = new JLabel(icon_BBU);//可以利用生成左栏时的数据bbupool_num
				lblBbu_2.setPreferredSize(new Dimension(icon_BBU.getIconWidth(), icon_BBU.getIconHeight()));
				panel_BBU_RRU_UE.add(lblBbu_2);
				//需要遍历数据库
				String sql="exec RowNum ?";
				String []paras={"Bbu"};
				sql_allnum_model=new BBU2RRU_Link_module();
				ResultSet Bbu_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
				try {
					Bbu_allnum_sql.next();
					bbu_num_total=Bbu_allnum_sql.getInt(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				int temp_bbu_num=bbu_num_total;
				String string_bbu_num=Integer.toString(temp_bbu_num);//数据库中bbu总数
				lableBBU = new JLabel(string_bbu_num);
				lableBBU.setFont(MyTools.f15);
				panel_BBU_RRU_UE.add(lableBBU);
				
				JLabel lable_blank=new JLabel(" ");//空格
				panel_BBU_RRU_UE.add(lable_blank);
			}
			{
				String path_RRU="image"+File.separator+"public"+File.separator+"rru个数按钮.png";
				Icon icon_RRU=new ImageIcon(path_RRU);
				JLabel lblRru = new JLabel(icon_RRU);//可以利用生成左栏时的数据bbupool_num
				lblRru.setPreferredSize(new Dimension(icon_RRU.getIconWidth(), icon_RRU.getIconHeight()));
				panel_BBU_RRU_UE.add(lblRru);
				String sql="exec RowNum ?";
				String []paras={"Rru"};
				sql_allnum_model=new BBU2RRU_Link_module();
				ResultSet Rru_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
				try {
					Rru_allnum_sql.next();
					rru_num_total=Rru_allnum_sql.getInt(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				int temp_rru_num=rru_num_total;
				String string_rru_num=Integer.toString(temp_rru_num);//数据库中rru总数
				lableRRU = new JLabel(string_rru_num);
				lableRRU.setFont(MyTools.f15);
				panel_BBU_RRU_UE.add(lableRRU);
				
				JLabel lable_blank=new JLabel(" ");//空格
				panel_BBU_RRU_UE.add(lable_blank);
			}
			{
				String path_UE="image"+File.separator+"public"+File.separator+"ue个数按钮.png";
				Icon icon_UE=new ImageIcon(path_UE);
				JLabel lblUe = new JLabel(icon_UE);//可以利用生成左栏时的数据bbupool_num
				lblUe.setPreferredSize(new Dimension(icon_UE.getIconWidth(), icon_UE.getIconHeight()));
				panel_BBU_RRU_UE.add(lblUe);
				String sql="exec RowNum ?";
				String []paras={"Ue"};
				sql_allnum_model=new BBU2RRU_Link_module();
				ResultSet Ue_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
				try {
					Ue_allnum_sql.next();
					ue_num_total=Ue_allnum_sql.getInt(1);
				} catch (Exception e) {
					// TODO: handle exception
				}
				int temp_ue_num=ue_num_total;
				String string_ue_num=Integer.toString(temp_ue_num);
				lableUE = new JLabel(string_ue_num);
				lableUE.setFont(MyTools.f15);
				panel_BBU_RRU_UE.add(lableUE);
			}
		}
		
		panel_12 = new JPanel();
		panel_12.setOpaque(false);
		panel_2.add(panel_12, BorderLayout.WEST);
		label = new JLabel("BBU与RRU链路输入");
		label.setFont(MyTools.f1);
		panel_12.add(label);
		
		panel_5 = new JPanel();
		panel_5.setOpaque(false);
		panel.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new GridLayout(1, 2, 0, 0));
		
//		String path_6="image"+File.separator+"ADD_Link"+File.separator+"ADD_BBU_Right2.png";
//		Image image_6=new ImageIcon(path_6).getImage(); 
//		panel_6 = new BackgroundPanel(image_6);
		panel_6 = new JPanel();
		panel_6.setOpaque(false);
		panel_5.add(panel_6);
		panel_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		button = new JButton("添加");
//		button.setForeground(Color.white);
		button.setFont(MyTools.f14);
//		button.setContentAreaFilled(false);
		button.addActionListener(this);
		panel_6.add(button);
		
		button_1 = new JButton("保存");
//		button_1.setForeground(Color.white);
		button_1.setFont(MyTools.f14);
//		button_1.setContentAreaFilled(false);
		button_1.addActionListener(this);
		panel_6.add(button_1);
		
//		String path_7="image"+File.separator+"ADD_Link"+File.separator+"ADD_BBU_Right2.png";
//		Image image_7=new ImageIcon(path_7).getImage(); 
//		panel_7 = new BackgroundPanel(image_7);
		panel_7 = new JPanel();
		panel_7.setOpaque(false);
		panel_5.add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		button_2 = new JButton("修改");
//		button_2.setForeground(Color.white);
		button_2.setFont(MyTools.f14);
//		button_2.setContentAreaFilled(false);
		button_2.addActionListener(this);
		panel_7.add(button_2);
		
		button_3 = new JButton("删除");
//		button_3.setForeground(Color.white);
		button_3.setFont(MyTools.f14);
//		button_3.setContentAreaFilled(false);
		button_3.addActionListener(this);
		panel_7.add(button_3);
		//bbuc_link_module显示BBU池链路表格
		panel_8 = new JPanel();
		panel_8.setLayout(new BorderLayout());
		panel.add(panel_8, BorderLayout.CENTER);
		BBU2RRU_Link_table=new BBU2RRU_Link_module();
		jt=new JTable(BBU2RRU_Link_table);
		
		//JTable的背景颜色设置
		jt.setBackground(new Color(240, 245, 250));

		jt.setRowHeight(20);//修改表格宽度
		//获得表头
		JTableHeader tableH = jt.getTableHeader();
		tableH.setFont(MyTools.f12);
		tableH.setBackground(new Color(204, 208, 213));
		  
		DefaultTableCellRenderer r =   new   DefaultTableCellRenderer();   
		r.setHorizontalAlignment(JLabel.CENTER);   
		r.setFont(MyTools.f12);
		jt.setDefaultRenderer(Object.class,r);
				
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);		
		//初始化jsp
		jsp2=new JScrollPane(jt);
		jsp2.getViewport().setBackground(Color.white);
		//把jsp加入到JFrame中
		panel_8.add(jsp2);
		//刷新展示的表格jt
		String sql="select * from LinkBbu2Rru where 1=?";//再从UE中调出添加的数据		
		String []paras={"1"};//可以显示所有已经添加的UE
		BBU2RRU_Link_table=new BBU2RRU_Link_module();
		BBU2RRU_Link_table.BBU2RRU_Link_search(sql,paras);
		
		jt.setModel(BBU2RRU_Link_table);
		/**************************************
		 * 退出按钮
		 ***************************************/
		{
			String path_button="image"+File.separator+"ADD_Link"+File.separator+"Link_bottom.png";
			Image image_button=new ImageIcon(path_button).getImage(); 
			buttonPane= new BackgroundPanel(image_button);
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);			
			cancelButton = new JButton("退出");
			cancelButton.setFont(MyTools.f14);
			cancelButton.setActionCommand("退出");
			cancelButton.addActionListener(this);
			buttonPane.add(cancelButton);
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
		
		panel_4.add(Treeroot, BorderLayout.CENTER);
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
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==button)//添加
		{
			BBU2RRU_Link_add_rownum=BBU2RRU_Link_add_rownum+1;//表格里面添加新数据的行数
			BBU2RRU_Link_table.addRows(BBU2RRU_Link_add_rownum);
			BBU2RRU_Link_table.fireTableRowsInserted(BBU2RRU_Link_add_rownum, BBU2RRU_Link_add_rownum+1);
			jt.setModel(BBU2RRU_Link_table);
			
			jcb1=new JComboBox<>(jg1);	//链路类型		
			jcb1.setEditable(false);			
			jt.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(jcb1)); //列是从0开始数的
			//jcg2的下拉框list数据是从数据库里读出来的
			//接入环
//			jg2=new Vector<>();
//				String sql1="select * from LinkCircle where 1=?";//	
//				String []paras1={"1"};//
//				BBU_sql circlid_sql=new BBU_sql();
//				ResultSet rs=circlid_sql.search(sql1, paras1);
//				try {
//					while(rs.next())
//					{
//						jg2.add(rs.getString(1));//LinkCircleId
//					}
//				} catch (SQLException e1) {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}finally
//				{
//					//关闭
//					circlid_sql.close();
//				}				
//			jcb2=new JComboBox<>(jg2);			
//			jcb2.setEditable(false);			
//			jt.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(jcb2)); //列是从0开始数的
			//jg3,jg4,bbu端点id
			jg3=new Vector<>();
			String sql2="select * from Bbu where 1=?";//	
			String []paras2={"1"};//
			BBU_sql bbuid_sql=new BBU_sql();
			ResultSet bbu_rs=bbuid_sql.search(sql2, paras2);
			try {
				while(bbu_rs.next())
				{
					jg3.add(bbu_rs.getString(1));//LinkCircleId
				}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}finally
			{
				//关闭
				bbuid_sql.close();
			}				
			jcb3=new JComboBox<>(jg3);			
			jcb3.setEditable(false);			
			jt.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(jcb3)); //列是从0开始数的
			
			jg4=new Vector<>();
			String sql3="select * from Rru where 1=?";
			String []paras3={"1"};
			BBU_sql bbuid2_sql=new BBU_sql();
			ResultSet bbu2_rs=bbuid2_sql.search(sql3, paras3);
			try {
				while(bbu2_rs.next())
				{
					jg4.add(bbu2_rs.getString(1));//LinkCircleId
				}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}finally
			{
				//关闭
				bbuid2_sql.close();
			}				
			jcb4=new JComboBox<>(jg4);			
			jcb4.setEditable(false);			
			jt.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jcb4)); //列是从0开始数的
		}else if(e.getSource()==button_1)//保存
		{
			if(BBU2RRU_Link_add_rownum!=0)
			{
				String sql1="exec RowNum ?";
				String []paras1={"LinkBbu2Rru"};
				String Bbu2BbuLink_searchrow=Update_totalnum(sql1,paras1);
				int temp_Bbu2BbuLink_searchrow= Integer.parseInt(Bbu2BbuLink_searchrow);
				//判断必须填写数字，不能填写字母
				boolean judge=true;
				for(int j=temp_Bbu2BbuLink_searchrow;j<temp_Bbu2BbuLink_searchrow+BBU2RRU_Link_add_rownum;j++)
				{
					String MaxTransRate=(String) BBU2RRU_Link_table.getValueAt(j, 3);//MaxTransRate
					boolean temp=isNumeric(MaxTransRate);
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
						jt.getCellEditor().stopCellEditing();
					} catch (Exception e1) {
						// TODO: handle exception
					}	
					for(int i=temp_Bbu2BbuLink_searchrow;i<temp_Bbu2BbuLink_searchrow+BBU2RRU_Link_add_rownum;i++)
					{
						String []paras=new String[13];
						paras[0]="LinkBbu2Rru";
						paras[1]=(String) BBU2RRU_Link_table.getValueAt(i, 0);//LinkPathId
						paras[2]=(String) BBU2RRU_Link_table.getValueAt(i, 1);//LinkEnd1
						paras[3]=(String) BBU2RRU_Link_table.getValueAt(i, 2);//LinkEnd2
						paras[4]="0";//RealLength
						if(BBU2RRU_Link_table.getValueAt(i, 4).equals("环型"))
						{
							paras[5]="0";//LinkType,0：环型，1：总线型
						}else if(BBU2RRU_Link_table.getValueAt(i, 4).equals("总线型"))
						{
							paras[5]="1";//LinkType,0：环型，1：总线型
						}
//						paras[5]=(String) BBU2RRU_Link_table.getValueAt(i, 4);//LinkType
						paras[6]=(String) BBU2RRU_Link_table.getValueAt(i, 3);//MaxTransRate
						paras[7]="0";//Attenuationnull
						paras[8]="0";//Delay
						paras[9]="0";//ErrorRate
						paras[10]="0";//Cost
						paras[11]="0";//(String) BBU2RRU_Link_table.getValueAt(i, 5);//LinkCircleId
						paras[12]="0";//FibersNum 
						String sql="exec Add_Link ?,?,?,?,?,?,?,?,?,?,?,?,?";//还是要一行一行的添加exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
						b=BBU2RRU_Link_table.add_delete_change_to_sql(sql, paras);
						}
					//刷新展示的表格
					String sql2="select * from LinkBbu2Rru where 1=?";//再从Bbupool中调出bbu的数据		
					String []paras2={"1"};
					BBU2RRU_Link_table=new BBU2RRU_Link_module();
					BBU2RRU_Link_table.BBU2RRU_Link_search(sql2,paras2);
					jt.setModel(BBU2RRU_Link_table);
					if(b==true)
					{
						//保存成功
						JOptionPane.showMessageDialog(this, "保存完成");//弹出对话框
					}
					BBU2RRU_Link_add_rownum=0;
				}
		}
		}
		else if(e.getSource()==button_2)//修改
		{
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else
			{
				new BBU2RRU_Link_modify(this, "修改", true, BBU2RRU_Link_table, rowNum);
				//刷新表格
				String sql2="select * from LinkBbu2Rru where 1=?";//再从Bbupool中调出bbu的数据		
				String []paras2={"1"};
				BBU2RRU_Link_table=new BBU2RRU_Link_module();
				BBU2RRU_Link_table.BBU2RRU_Link_search(sql2,paras2);
				jt.setModel(BBU2RRU_Link_table);
			}
		}else if(e.getSource()==button_3)//删除
		{
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}else
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除此条链路", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					String Link_ID=(String) BBU2RRU_Link_table.getValueAt(rowNum,0);
					String sql="exec Drop_Link ?,?";
					String []paras={"LinkBbu2Rru",Link_ID};
					b=BBU2RRU_Link_table.add_delete_change_to_sql(sql, paras);
					//刷新表格
					String sql1="select * from LinkBbu2Rru where 1=?";//再从Bbu中调出bbu的数据		
					String []paras1={"1"};
					BBU2RRU_Link_table=new BBU2RRU_Link_module();
					BBU2RRU_Link_table.BBU2RRU_Link_search(sql1,paras1);
					jt.setModel(BBU2RRU_Link_table);
					//
					if(BBU2RRU_Link_add_rownum!=0)
					{
						BBU2RRU_Link_add_rownum=BBU2RRU_Link_add_rownum-1;
					}
					else
					{
						BBU2RRU_Link_add_rownum=0;
					}
					if(b==true)
					{
						//删除成功
						JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
					}
				}
			}
		}
		else if(e.getSource()==cancelButton)//取消//////////////////////////////////////////////////////////
		{
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
		if(index<0)
		{
			for (int i = str.length();--i>=0;)
			  {    
				   if (!Character.isDigit(str.charAt(i)))
				   {  
				    return false;  
				   }
			   }
		}
		else{
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
	private String Update_totalnum(String sql1, String[] paras1) {
		// TODO 自动生成的方法存根
		sql_allnum_model=new BBU2RRU_Link_module();
		ResultSet allnum_sql=sql_allnum_model.total_num_sql(sql1,paras1);
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

}
