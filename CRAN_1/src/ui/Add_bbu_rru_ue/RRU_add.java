/*
 * 类名：RRU_add
 * 功能：RRU部署界面
 */
package ui.Add_bbu_rru_ue;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.tree.*;

import org.jfree.util.StringUtils;

import ui.Share_bag.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.util.Enumeration;


public class RRU_add extends JDialog implements ActionListener,MouseListener{
	boolean b;
	private JPanel contentPanel;
	JSplitPane splitPane;
	JPanel panel1,panel_1,panel2,panel2_1,panel2_2,panel2_2_1,panel2_2_2,panel2_2_3,panel3,panel_10,buttonPane;
//	JTextField textField,textField_1,textField_2,textField_3;
	JPanel panel3_1,panel3_2,panel;
	JButton btnbbu_3,button_change;
	JLabel lableBBUPool,lableBBU,lableRRU,lableUE;
//	private JTextField textField_4,textField_5,textField_6,textField_7,textField_8,textField_10,textField_11;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	JTable jt,jt_BBU;
	int total_num;//总数
	String Bbu_num="0";//被选中的BBU
	String Bbupool_num;//被选中的BBUPOOL
	int bbupool_num;//bbu池总数，通过生成bbu树得到
	int rru_searchrow;//通过查询数据库得到的每个bbu对应的rru行数
	int rru_add_rownum,rru_all_row;
	int bbu_num_total,rru_num_total,ue_num_total;
	Rru_Table_module rru_table,sql_allnum_model;
	Bbu_table_module bbu_table;
	int bbu_row;
	Num_search_module bbuc,bbu;
	JButton button_1,button,button_2,button_3,cancelButton;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"关闭","打开"};//RRU状态属性数组，不填写默认是0,0：关闭，1：打开
	String []jg2={"0","1","2"};//天线类型数组，不填写默认是0
	JScrollPane scrollPane;
	DefaultMutableTreeNode root;
//	public static void main(String[] args) {
//		try {
//			RRU_add dialog = new RRU_add();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}


	public RRU_add(Frame owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//public RRU_add() {
		//设置窗体属性
		setTitle("RRU参数输入");
		setBounds(150, 50, 1070, 650);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		splitPane = new JSplitPane();
		splitPane.setDividerLocation(130);
		contentPanel.add(splitPane);
		panel1 = new JPanel();
/***************************************左栏************************************/
				splitPane.setLeftComponent(panel1);
				panel1.setLayout(new BorderLayout(0, 0));
				{
					String path_10="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Left.png";
					Image image_10=new ImageIcon(path_10).getImage(); 
					panel_10= new BackgroundPanel(image_10);
					panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					
					JLabel label = new JLabel("网元列表");
//					label.setFont(new Font("黑体", Font.BOLD, 20));
					label.setFont(MyTools.f1);
//					label.setForeground(Color.white);
					panel_10.add(label);
					panel1.add(panel_10, BorderLayout.NORTH);
				}
				{
					panel_1 = new JPanel();
					panel1.add(panel_1, BorderLayout.CENTER);
					panel_1.setBackground(Color.white);
					build_tree();
					panel_1.add(Treeroot, BorderLayout.CENTER);

				}		
/*****************************************右栏************************************/
	String path_2="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Right3.png";
	Image image_2=new ImageIcon(path_2).getImage(); 
	panel2= new BackgroundPanel(image_2);
	splitPane.setRightComponent(panel2);
	panel2.setLayout(new BorderLayout(0, 0));
	panel2_1 = new JPanel();
	panel2_1.setOpaque(false);
	panel2.add(panel2_1, BorderLayout.SOUTH);
	panel2_1.setLayout(new GridLayout(1, 2, 0, 0));
	
	JPanel panel_2 = new JPanel();
	panel_2.setOpaque(false);
	panel2_1.add(panel_2);
	panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	
	button_2 = new JButton("添加");
	button_2.setFont(MyTools.f14);
	button_2.addActionListener(this);
	panel_2.add(button_2);
	
	button_3 = new JButton("保存");
	button_3.setFont(MyTools.f14);
	button_3.addActionListener(this);
	panel_2.add(button_3);
	
	JPanel panel_3 = new JPanel();
	panel_3.setOpaque(false);
	panel2_1.add(panel_3);
	panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
	
	button = new JButton("修改");
	button.setFont(MyTools.f14);
	button.addActionListener(this);
	panel_3.add(button);
	
	button_1 = new JButton("删除");
	button_1.setFont(MyTools.f14);
	button_1.addActionListener(this);
	panel_3.add(button_1);
	
	panel2_2 = new JPanel();
	panel2_2.setOpaque(false);
	panel2.add(panel2_2, BorderLayout.NORTH);
	panel2_2.setLayout(new BorderLayout());
	{
		JPanel panelTitle=new JPanel();
		panelTitle.setOpaque(false);
		panelTitle.setLayout(new BorderLayout());
		JLabel lblBbu = new JLabel(" RRU参数录入");
		lblBbu.setFont(MyTools.f1);
		panelTitle.add(lblBbu,BorderLayout.CENTER);
		panel2_2.add(panelTitle,BorderLayout.WEST);
		
	}
	{
		JPanel panel_BBU_RRU_UE=new JPanel();
		panel_BBU_RRU_UE.setOpaque(false);
		panel_BBU_RRU_UE.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		panel2_2.add(panel_BBU_RRU_UE,BorderLayout.EAST);
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
			sql_allnum_model=new Rru_Table_module();
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
			//需要遍历数据库	
			String sql="exec RowNum ?";
			String []paras={"Rru"};
			sql_allnum_model=new Rru_Table_module();
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
			//需要遍历数据库
			String sql="exec RowNum ?";
			String []paras={"Ue"};
			sql_allnum_model=new Rru_Table_module();
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
	panel3 = new JPanel();
	panel3.setOpaque(false);
	panel2.add(panel3, BorderLayout.CENTER);
	panel3.setLayout(new GridLayout(2, 1, 0, 0));
	//显示每个bbu池对应bbu的列表
	{
		//BBU对应JTable
		panel3_1 = new JPanel();
		panel3_1.setOpaque(false);
		panel3.add(panel3_1);
		panel3_1.setLayout(new BorderLayout(0, 0));
			//bbu_panel显示BBU表格的
			bbu_table=new Bbu_table_module();
			jt_BBU=new JTable(bbu_table);
			jt_BBU.addMouseListener(this);
			//JTable的背景颜色设置
			jt_BBU.setBackground(new Color(240, 245, 250));

			jt_BBU.setRowHeight(20);//修改表格宽度
			//获得表头
			JTableHeader tableH = jt_BBU.getTableHeader();
			tableH.setFont(MyTools.f12);
			tableH.setBackground(new Color(204, 208, 213));
			  
			DefaultTableCellRenderer r =   new   DefaultTableCellRenderer();   
			r.setHorizontalAlignment(JLabel.CENTER);   
			r.setFont(MyTools.f12);
			jt_BBU.setDefaultRenderer(Object.class,r);
			
			jt_BBU.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane = new JScrollPane(jt_BBU);
			scrollPane.getViewport().setBackground(Color.white);
			panel3_1.add(scrollPane, BorderLayout.CENTER);
			//刷新展示的BBU表格
			String sql2="select * from Bbu where 1=?";//再从UeGroup中调出添加的数据	
			String []paras2={"1"};//可以显示所有的UeGroup  
			bbu_table=new Bbu_table_module();
			bbu_table.bbu_table_search(sql2,paras2);
			jt_BBU.setModel(bbu_table);
//			already_exist_rowbbupool=jt_bbupool.getRowCount();
			//BBU表格对应下面有一条蓝色的边栏
			{
				panel = new JPanel();
				panel.setOpaque(false);
				panel.setLayout(new GridLayout(1, 2, 0, 0));
				panel3_1.add(panel, BorderLayout.SOUTH);
				{
					JPanel panel_1 = new JPanel(); 
					panel_1.setOpaque(false);
					panel.add(panel_1);
				}
				{
					JPanel panel_1_2 = new JPanel(); 
					panel_1_2.setOpaque(false);
					panel.add(panel_1_2);
					panel_1_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					button_change = new JButton("修改");
					button_change.setFont(MyTools.f14);
					button_change.addActionListener(this);
					panel_1_2.add(button_change);
				}
			}
	}
	{
		//显示每个BBU对应RRU表格
		panel3_2 = new JPanel();
		panel3_2.setOpaque(false);
		panel3.add(panel3_2);
		panel3_2.setLayout(new BorderLayout(0, 0));
		//rru_table显示RRU表格的
		rru_table=new Rru_Table_module();
		jt=new JTable(rru_table);
		
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
		JScrollPane scrollPane = new JScrollPane(jt);
		scrollPane.getViewport().setBackground(Color.white);
		panel3_2.add(scrollPane, BorderLayout.CENTER);
	}
	/**************************************
	 * 添加退出按钮
	 ***************************************/
		{
			String path_button="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Bottom3.png";
			Image image_button=new ImageIcon(path_button).getImage(); 
			buttonPane= new BackgroundPanel(image_button);
//			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				cancelButton = new JButton("退出");
				cancelButton.setFont(MyTools.f14);
				cancelButton.setActionCommand("退出");//cancel
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
			}
		}
			/**************************************
			 * 功能：树形目录监听函数：点击树形目录节点，右边对应BBU和RRU表格会做相应变化
			 ***************************************/
		class SelListener implements TreeSelectionListener
		{

			public void valueChanged(TreeSelectionEvent se) {
				// TODO 自动生成的方法存根
				rru_add_rownum=0;
				JTree tree = (JTree)se.getSource();
				TreePath []selecttionpath=tree.getSelectionPaths();
				int get_pathcount=selecttionpath[0].getPathCount();
				if(get_pathcount==3)
				{
					DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					String selNodename=(String) selNode.getUserObject();
					String s[]=selNodename.split("U");
					Bbu_num=s[1];//选中BBU的编号
					System.out.println("Bbu_num:"+Bbu_num);
//					show_bbupool_textfield(Bbu_num);
		//rru_panel显示rru的表格
					
					String sql1="select * from Rru where ServiceBbuId=?";//再从Bbu中调出bbu的数据		
					String []paras1={Bbu_num};
					rru_table=new Rru_Table_module();
					rru_table.table_search(sql1,paras1);
					rru_searchrow=rru_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
					System.out.println("rru_searchrow"+rru_searchrow);
					jt.setModel(rru_table);
					//如果选中某个BBU，BBU表格高亮该BBU所在行。
					//需要遍历数据库
					String sql="select rn from(select rn = row_number()over(order by BbuId),* from Bbu) t Where BbuId =  ?";
					String []paras={Bbu_num};
					sql_allnum_model=new Rru_Table_module();
					ResultSet Bbu_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
					try {
						Bbu_allnum_sql.next();
						bbu_row=Bbu_allnum_sql.getInt(1)-1;//BBU池0对应第1行，所以要减1
					} catch (Exception e) {
						// TODO: handle exception
					}
					jt_BBU.setRowSelectionInterval(bbu_row,bbu_row);
			}
				if(get_pathcount==2)
				{
					DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					String selNodename=(String) selNode.getUserObject();
					if(selNodename=="独立RRU")
					{
						Bbu_num="-1";//变量Bbu_num是用来判断左边树形目录选中的是BBU还是独立RRU
						//既然选中“独立RRU”，右边BBU表格所有行接触现有选中状态
						jt_BBU.clearSelection();	
						//rru_panel显示rru的表格
						String sql1="select * from Rru where ServiceBbuId=?";//再从Bbu中调出bbu的数据		
						String []paras1={"-1"};
						rru_table=new Rru_Table_module();
						rru_table.table_search(sql1,paras1);
						rru_searchrow=rru_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
						jt.setModel(rru_table);
					}
					else//树形目录选中BBU池
					{
						jt_BBU.clearSelection();//BBU表格取消任意行选中状态
						//RRU表格空白，只有选中特定BBU，RRU表格才会显示该BBU对应的所有RRU数据
						rru_table=new Rru_Table_module();
						jt.setModel(rru_table);
						Bbu_num="0";//Bbu_num=0:未选中任何BBU，选中的是BBU池
					}
					
				}
			
		}
		}
		/**************************************
		 * 功能：界面上各个按钮的监听响应功能
		 ***************************************/
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO 自动生成的方法存根
			/***************添加rru******************/
			if(arg0.getSource()==button_2)
			{
				rru_add_rownum=rru_add_rownum+1;//表格里面添加新数据的行数
				
				rru_table.addRows(rru_add_rownum);
				rru_table.fireTableRowsInserted(rru_searchrow, rru_searchrow+1);
				jt.setModel(rru_table);
				jcb1=new JComboBox<>(jg1);
				jcb2=new JComboBox<>(jg2);	
				jcb1.setEditable(false);
				jcb2.setEditable(false);
				jt.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jcb1)); //列是从0开始数的
				jt.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(jcb2)); //列是从0开始数的
				
				
			}
			/***************保存rru******************/
			else if(arg0.getSource()==button_3)
			{
				if(rru_add_rownum!=0)
				{
					//判断X,YZ坐标不能不填写，否则，不能保存
					boolean judge=true;
					for(int j=rru_searchrow;j<rru_searchrow+rru_add_rownum;j++)
					{
						String X=(String) rru_table.getValueAt(j, 1);//X
						String Y=(String) rru_table.getValueAt(j, 2);//Y
						String Z=(String) rru_table.getValueAt(j, 3);//Z
						if(X==null||Y==null||Z==null)
						{
							judge=false;
							//提示
							JOptionPane.showMessageDialog(this,"X,Y,Z坐标不能为空");
							return;
						}
						String RruTransPower=(String) rru_table.getValueAt(j, 4);//RRU 发射功率
						String user_num=(String) rru_table.getValueAt(j, 5);//RRU覆盖范围内的用户数量
						String CarrierFrequent=(String) rru_table.getValueAt(j, 6);//载频	
						boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z)&isNumeric(RruTransPower)&isNumeric(user_num)&isNumeric(CarrierFrequent);
						if(!(temp))
						{
							judge=false;
							//提示
							JOptionPane.showMessageDialog(this,"请填写数字！");
							return;
						}
					}
					if(Bbu_num=="0")//未选中任意BBU，不能添加RRU，因为不知道添加到哪里
					{
						judge=false;
						rru_table=new Rru_Table_module();
						jt.setModel(rru_table);
						JOptionPane.showMessageDialog(this,"树形列表未选中所属BBU或者独立RRU，不能成功添加RRU！");
						return;
					}
					if(judge==true)	
					{
						try {
							jt.getCellEditor().stopCellEditing();
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						for(int i=rru_searchrow;i<rru_searchrow+rru_add_rownum;i++)
						{
							String []paras=new String[13];
							paras[0]=(String) rru_table.getValueAt(i, 0);//rru id
							if(Bbu_num=="-1")//如果选中“独立RRU”
							{
								paras[1]="-1";//对应数据库中BBU编号[ServiceBbuId]=-1
							}else//如果选择其他BBU
							{
								paras[1]=Bbu_num;//对应选中BBU的编号
							}
							paras[2]=null;//覆盖半径
							paras[3]=(String) rru_table.getValueAt(i, 1);//X
							paras[4]=(String) rru_table.getValueAt(i, 2);//Y
							paras[5]=(String) rru_table.getValueAt(i, 3);//Z
							paras[6]=(String) rru_table.getValueAt(i, 4);//RRU 发射功率
							paras[7]=null;//RRU的带宽资源
							paras[8]=(String) rru_table.getValueAt(i, 5);//RRU覆盖范围内的用户数量
							if(rru_table.getValueAt(i, 7).equals("关闭"))
							{
								paras[9]="0";
							}else if(rru_table.getValueAt(i, 7).equals("打开"))
							{
								paras[9]="1";
							}
//							paras[9]=(String) rru_table.getValueAt(i, 7);//活跃状态，0：关闭，1：打开
							paras[10]=(String) rru_table.getValueAt(i, 6);//载频
							paras[11]=(String) rru_table.getValueAt(i, 8);//天线Id
							paras[12]=null;//设备能耗
							String sql="exec Add_Rru ?,?,?,?,?,?,?,?,?,?,?,?,?";//还是要一行一行的添加exec Add_Rru ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
							b=rru_table.add_delete_change_to_sql(sql, paras);
							}
						//刷新展示的表格
						String sql1="select * from Rru where ServiceBbuId=?";//再从Bbupool中调出bbu的数据	
						if(Bbu_num=="-1")//如果选中“独立RRU”
						{
							String []paras1={"-1"};
							rru_table=new Rru_Table_module();
							rru_table.table_search(sql1,paras1);
						}else//如果选择其他BBU
						{
							String []paras1={Bbu_num};
							rru_table=new Rru_Table_module();
							rru_table.table_search(sql1,paras1);
						}
						rru_searchrow=rru_table.getRowCount();//bbu表格查询数据库，自动生成数据行数，当前bbupool下面对应几个bbu
						jt.setModel(rru_table);
						
						//刷新显示rru总数的textfield
						String sql="exec RowNum ?";
						String []paras={"Rru"};
						String string_bbu_num=Update_totalnum(sql,paras);////更新了数据库中rru总数
						lableRRU.setText(string_bbu_num);
						//刷新树形结构
						panel_1.removeAll();
						panel_1.repaint();
						build_tree();
						if(b==true)
						{
							//保存成功
							JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
						}
						rru_add_rownum=0;
					}
					
			}
				}
			/***************修改rru******************/
			else if(arg0.getSource()==button)
			{
				int rowNum=this.jt.getSelectedRow();//返回用户点中的行
				if(rowNum==-1)//如果用户没有选择某一行
				{
					//提示
					JOptionPane.showMessageDialog(this,"请选择一行");
					return;
				}
				rru_all_row=rru_searchrow+rru_add_rownum;
				if(rru_all_row==0)
				{
					//提示
					JOptionPane.showMessageDialog(this,"此bbu池中没有数据");
					return;
				}
				if(rru_all_row>0)
				{
					new Rru_modify(this, "修改", true, rru_table, rowNum);
					String sql1="select * from Rru where ServiceBbuId=?";//再从Rru中调出某个bbu对应的数据		
					String []paras1={Bbu_num};
					rru_table=new Rru_Table_module();
					rru_table.table_search(sql1,paras1);
					
					jt.setModel(rru_table);		
				}
			}
			/***************删除rru******************/
			else if(arg0.getSource()==button_1)
			{
				int rowNum=this.jt.getSelectedRow();//返回用户点中的行
				if(rowNum==-1)//如果用户没有选择某一行
				{
					//提示
					JOptionPane.showMessageDialog(this,"请选择一行");
					return;
				}		
				rru_all_row=rru_searchrow+rru_add_rownum;
				if(rru_all_row==0)
				{
					//提示
					JOptionPane.showMessageDialog(this,"此BBU中没有RRU");
					return;
				}
				if(rru_all_row>0)
				{
					int a=JOptionPane.showConfirmDialog(null, "是否确定删除RRU", "", JOptionPane.YES_NO_OPTION);
					if(a==JOptionPane.NO_OPTION)
					{
						return;
					}
					if(a==JOptionPane.YES_OPTION)
					{
						String ID=(String) rru_table.getValueAt(rowNum,0);
						String sql="exec Drop_Rru ?";
						String []paras={ID};
						b=rru_table.add_delete_change_to_sql(sql, paras);
						String sql1="select * from Rru where ServiceBbuId=?";//再从Bbu中调出bbu的数据		
						String []paras1={Bbu_num};
						rru_table=new Rru_Table_module();
						rru_table.table_search(sql1,paras1);
						rru_searchrow=rru_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
						jt.setModel(rru_table);
						
						//刷新显示rru总数的texrfield
						String sql2="exec RowNum ?";
						String []paras2={"Rru"};
						String string_bbu_num=Update_totalnum(sql2,paras2);////更新了数据库中bbu总数
						lableRRU.setText(string_bbu_num);
						//刷新树形结构
						panel_1.removeAll();
						panel_1.repaint();
						build_tree();
						if(b==true)
						{
							//删除成功
							JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
						}
					}
				}
			}
			/***************BBU表格修改******************/
			else if(arg0.getSource()==button_change)
			{
				int rowNum=this.jt_BBU.getSelectedRow();//返回用户点中的行
				if(rowNum==-1)//如果用户没有选择某一行
				{
					//提示
					JOptionPane.showMessageDialog(this,"请选择一行");
					return;
				}
				else
				{
					new Bbu_modify(this, "修改", true, bbu_table, rowNum);
					String sql1="select * from Bbu where 1=?";//再从Bbu中调出bbu的数据		
					String []paras1={"1"};
					bbu_table=new Bbu_table_module();
					bbu_table.bbu_table_search(sql1,paras1);
					jt_BBU.setModel(bbu_table);		
				}
			}
			/***************退出按钮******************/
			else if(arg0.getSource()==cancelButton)
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
		private String Update_totalnum(String sql, String[] paras) {
			sql_allnum_model=new Rru_Table_module();
			ResultSet allnum_sql=sql_allnum_model.total_num_sql(sql,paras);
			try {
				allnum_sql.next();
				total_num=allnum_sql.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			int temp_num=total_num;
			String string_num_total=Integer.toString(temp_num);//数据库中bbu总数
			return string_num_total;
		}

		/**************************************
		 * 功能：创建左侧的目录树
		 ***************************************/
		public void build_tree() {
			//树形列表
			//根节点
			root = new DefaultMutableTreeNode("根节点");
			
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
//				String BbuPoolId=Integer.toString(i);//int类型转换成string类型
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
			DefaultMutableTreeNode node_first = new DefaultMutableTreeNode("独立RRU");
			root.add(node_first);
			//利用根节点直接创建树Treeroot
			Treeroot=new JTree(root);
			TreeSelectionModel treeSelectionModel;//获得树的选择模式
			treeSelectionModel=Treeroot.getSelectionModel();
			//设置树的选择模式为单选
//			treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//////////////////////////////////////////////
			Treeroot.addTreeSelectionListener(new SelListener());
			
			panel_1.add(Treeroot, BorderLayout.CENTER);
			//定制树
			Treeroot.setRootVisible(false);//无根节点
			Treeroot.setRowHeight(20);//树节点的行高
			DefaultTreeCellRenderer treeCellRenderer=new DefaultTreeCellRenderer();//获得树节点的绘制对象
			treeCellRenderer=(DefaultTreeCellRenderer)Treeroot.getCellRenderer();
			treeCellRenderer.setLeafIcon(new ImageIcon("image"+File.separator +"LeafIcon.png"));
			treeCellRenderer.setClosedIcon(new ImageIcon("image"+File.separator +"ClosedIcon.png"));
			treeCellRenderer.setOpenIcon(new ImageIcon("image"+File.separator +"OpenIcon.png"));
			Treeroot.setFont(new Font("宋体", Font.PLAIN, 20));
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
		 * 功能：监听相应鼠标点击的函数
		 ***************************************/
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO 自动生成的方法存根
	/***********************双击BBU参数表格某行BBU，左侧树形列表对应BBU状态变为被选中，同时下面表格显示此BBU下包含的所有RRU*********************/
			if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e))
			{
				int rowselected=this.jt_BBU.getSelectedRow();//返回用户点中的行
				Bbu_num=(String) jt_BBU.getValueAt(rowselected, 0);
				//rru_panel显示rru的表格
				String sql1="select * from Rru where ServiceBbuId=?";//再从Bbu中调出bbu的数据		
				String []paras1={Bbu_num};
				rru_table=new Rru_Table_module();
				rru_table.table_search(sql1,paras1);
				rru_searchrow=rru_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
				jt.setModel(rru_table);
				//JTree树显示相应BBU节点选中状态
				Enumeration enumeration;
				enumeration=root.preorderEnumeration();
				while(enumeration.hasMoreElements())
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement();
					String selNodename=(String) node.getUserObject();
					if(node.isLeaf())
					{
						String s[]=selNodename.split("U");
						System.out.println(Bbu_num);
						if(Bbu_num.equals(s[1]))//选中BBU的编号
						{
							//根据 node 获取 TreePath
							TreePath treePath = new TreePath(node.getPath());
//							Treeroot.addSelectionPath(treePath);
							Treeroot.getSelectionModel().setSelectionPath(treePath);
							break;
						}
					}
					
				}
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自动生成的方法存根
			
		}

		}
		
		