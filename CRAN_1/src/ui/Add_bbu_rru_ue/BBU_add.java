/*
 * 类名：BBU_add
 * 功能：BBU池与BBU部署界面
 */
package ui.Add_bbu_rru_ue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.tree.*;

import org.apache.poi.hwpf.usermodel.BorderCode;

import ui.Share_bag.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class BBU_add extends JDialog implements ActionListener,MouseListener{
	private  JPanel contentPanel;
	boolean b;
	private JTextField textField_4,textField_5,textField_6,textField_7,textField_8;
	JSplitPane splitPane;
	JPanel panel1,panel_1;
	JPanel panel2,panel2_1,panel2_2,panel2_2_1,panel2_2_2;
	JPanel panel3,panel3_1,panel3_2,buttonPane,panel_bbuc,panel_10;
	JButton button5,okButton,cancelButton;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	Num_search_module rru,ue,bbu,bbuc;
	Bbu_table_module bbu_table,sql_allnum_model,bbuc_table,Bbupool_maxid_model;//添加bbu的，专门用来得到bbu rru ue 总数的，添加bbu池,找到bbu池最大的当前ID 
	BBUPool_table_module BBUPool_table ;
	int bbu_num_total,rru_num_total,ue_num_total,total_num;//数据库中一共有多少个bbu
	int bbu_max_id;//bbupool当前最大id
	int bbupool_num;//一共有多少个bbu池
	int bbu_add_rownum;//每次一共添加了多少个bbu
	int bbupool_add_rownum;//每次一共添加了多少个bbupool
	int bbu_searchrow;//通过查询数据库得到的每个bbu池对应的bbu行数
	int bbu_all_row;//一个bbu池下，一共有多少个bbu。
	int bbupool_all_row;//所有BBU池，总数
	int bbupool_alreadyrow=0;//已经添加了的BBUPool个数
	int already_exist_rowbbupool;
	int bbupool_row;//选中BBUPool是BBUPool表格JTable的第几行
	JLabel lableBBUPool,lableBBU,lableRRU,lableUE;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"RRS","CIS","PFSS"};//RRU的调度方式数组，不填写默认是0,0：RRS 1:CIS 2:PFSS///////////////???????????????????//////////////////
	Vector jg2=null;//LinkID BBU接入环
	String Bbupool_num;//选中BBUPOOL的编号
	JTable jt,jt_bbupool;
	JScrollPane scrollPane,scrollPaneBBUPool,jsp;
	private JTable table;
	private JPanel panel,panel_2,panel_4,panel_5,panel_6;
	private JButton button,button_2,button_1,button_3;
	private JButton btnbbu,btnbbu_1,btnbbu_2,btnbbu_3;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_11;
	private JPanel panel_12;
	private JLabel lblBbu;
	DefaultMutableTreeNode root;

//	public static void main(String[] args) {
//		try {
//			BBU_add dialog = new BBU_add();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	/**************************************
	 * 功能：构造链路部署界面
	 ***************************************/
	public BBU_add(Frame owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public BBU_add() {
		//设置窗体属性
		setSize(1000, 620);
		setLocation(150, 50);
		setTitle("BBU参数输入");
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());
		{
			//分割线，区分左右界面
			splitPane = new JSplitPane();
			splitPane.setDividerLocation(130);
			contentPanel.add(splitPane);
			{
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
					label.setFont(MyTools.f1);
					panel_10.add(label);
					panel1.add(panel_10, BorderLayout.NORTH);
				}
				{
					panel_1 = new JPanel();
					panel_1.setBackground(Color.white);
					build_tree();
					panel_1.add(Treeroot, BorderLayout.CENTER);
					jsp=new JScrollPane(panel_1);
					panel1.add(jsp, BorderLayout.CENTER);
				}
			}
			
/*****************************************右栏************************************/
			{
				String path_2="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Right3.png";
				Image image_2=new ImageIcon(path_2).getImage(); 
				panel2= new BackgroundPanel(image_2);
				splitPane.setRightComponent(panel2);
				panel2.setLayout(new BorderLayout(0, 0));
				/***************************************
				 * 右栏标题行
				 * 功能：实时读取数据库中BBUPOOL,BBU,RRU,UE的个数，显示出来
				 * ************************************/
				{
					panel2_2 = new JPanel();
					panel2_2.setOpaque(false);
					panel2.add(panel2_2, BorderLayout.NORTH);
					panel2_2.setLayout(new BorderLayout());
						{
							JPanel panel_BBU_RRU_UE=new JPanel();
							panel_BBU_RRU_UE.setOpaque(false);
							panel_BBU_RRU_UE.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
							panel2_2.add(panel_BBU_RRU_UE,BorderLayout.EAST);
							{//BBUPOOL
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
							{//BBU
								String path_BBU="image"+File.separator+"public"+File.separator+"bbu个数按钮.png";
								Icon icon_BBU=new ImageIcon(path_BBU);
								JLabel lblBbu_2 = new JLabel(icon_BBU);//可以利用生成左栏时的数据bbupool_num
								lblBbu_2.setPreferredSize(new Dimension(icon_BBU.getIconWidth(), icon_BBU.getIconHeight()));
								panel_BBU_RRU_UE.add(lblBbu_2);
								//需要遍历数据库
								String sql="exec RowNum ?";
								String []paras={"Bbu"};
								sql_allnum_model=new Bbu_table_module();
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
							{//RRU
								String path_RRU="image"+File.separator+"public"+File.separator+"rru个数按钮.png";
								Icon icon_RRU=new ImageIcon(path_RRU);
								JLabel lblRru = new JLabel(icon_RRU);//可以利用生成左栏时的数据bbupool_num
								lblRru.setPreferredSize(new Dimension(icon_RRU.getIconWidth(), icon_RRU.getIconHeight()));
								panel_BBU_RRU_UE.add(lblRru);
								String sql="exec RowNum ?";
								String []paras={"Rru"};
								sql_allnum_model=new Bbu_table_module();
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
							{//UE
								String path_UE="image"+File.separator+"public"+File.separator+"ue个数按钮.png";
								Icon icon_UE=new ImageIcon(path_UE);
								JLabel lblUe = new JLabel(icon_UE);//可以利用生成左栏时的数据bbupool_num
								lblUe.setPreferredSize(new Dimension(icon_UE.getIconWidth(), icon_UE.getIconHeight()));
								panel_BBU_RRU_UE.add(lblUe);
								String sql="exec RowNum ?";
								String []paras={"Ue"};
								sql_allnum_model=new Bbu_table_module();
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
						panel2_2.add(panel_12, BorderLayout.WEST);
						
						lblBbu = new JLabel("BBU参数录入");
						lblBbu.setFont(MyTools.f1);
						panel_12.add(lblBbu);
				}
			/***************************************
			 * 两个JTable表格
			 * ************************************/
				panel3 = new JPanel();
				panel3.setOpaque(false);
				panel2.add(panel3, BorderLayout.CENTER);
				panel3.setLayout(new GridLayout(2, 1, 0, 0));
				/***********显示每个BBU池的列表**********/
				{
					//BBUPool池JTable表格
					panel3_2 = new JPanel();
					panel3_2.setOpaque(false);
					panel3.add(panel3_2);
					panel3_2.setLayout(new BorderLayout(0, 0));
					BBUPool_table=new BBUPool_table_module();
					jt_bbupool=new JTable(BBUPool_table);
					jt_bbupool.addMouseListener(this);
					jt_bbupool.setBackground(new Color(240, 245, 250));//JTable的背景颜色设置
					jt_bbupool.setRowHeight(20);//修改表格宽度
					//获得表头
					JTableHeader tableH = jt_bbupool.getTableHeader();
					tableH.setFont(MyTools.f12);
					tableH.setBackground(new Color(204, 208, 213));
					  
					DefaultTableCellRenderer r =   new   DefaultTableCellRenderer();   
					r.setHorizontalAlignment(JLabel.CENTER);   
					r.setFont(MyTools.f12);
					jt_bbupool.setDefaultRenderer(Object.class,r);
					
					jt_bbupool.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					scrollPaneBBUPool = new JScrollPane(jt_bbupool);
					scrollPaneBBUPool.getViewport().setBackground(Color.white);
					panel3_2.add(scrollPaneBBUPool, BorderLayout.CENTER);
					//刷新展示的表格jt_bbupool
					String sql2="select * from BbuPool where 1=?";//再从UeGroup中调出添加的数据	
					String []paras2={"1"};//可以显示所有的UeGroup  
					BBUPool_table=new BBUPool_table_module();
					BBUPool_table.bbupool_table_search(sql2,paras2);
					jt_bbupool.setModel(BBUPool_table);
					already_exist_rowbbupool=jt_bbupool.getRowCount();
				/**************************************
				 * BBUPool添加/保存/修改/删除按钮
				 * ************************************/	
					panel_4 = new JPanel();
					panel_4.setOpaque(false);
					panel_4.setLayout(new GridLayout(1, 2, 0, 0));
					
					panel_5 = new JPanel();
					panel_5.setOpaque(false);
					panel_4.add(panel_5);
					panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					
					btnbbu = new JButton("添加BBU池");
					btnbbu.setFont(MyTools.f14);
					btnbbu.addActionListener(this);
					panel_5.add(btnbbu);
					
					btnbbu_1 = new JButton("保存BBU池");
					btnbbu_1.setFont(MyTools.f14);
					btnbbu_1.addActionListener(this);
					panel_5.add(btnbbu_1);
					
					panel_6 = new JPanel();
					panel_6.setOpaque(false);
					panel_4. add(panel_6);
					panel_6.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					
					btnbbu_2 = new JButton("删除BBU池");
					btnbbu_2.setFont(MyTools.f14);
					btnbbu_2.addActionListener(this);
					panel_6.add(btnbbu_2);
					panel3_2.add(panel_4, BorderLayout.SOUTH);
				}
				/***********显示每个bbu池对应bbu的列表**********/
				{
					//BBU对应JTable
					panel3_1 = new JPanel();
					panel3_1.setOpaque(false);
					panel3.add(panel3_1);
					panel3_1.setLayout(new BorderLayout(0, 0));
						//bbu_panel显示BBU表格的
						bbu_table=new Bbu_table_module();
						jt=new JTable(bbu_table);
//						jt.setRowSelectionAllowed(true);//使得表格的选取是以行为单位,而不是以列为单位.若你没有写此行,则在选取表格数据时以整列为单位.
//						selectionMode=jt.getSelectionModel();
//						selectionMode.addListSelectionListener(this);
//						selectionMode.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//多重选择
						
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
						scrollPane = new JScrollPane(jt);
						scrollPane.getViewport().setBackground(Color.white);
						panel3_1.add(scrollPane, BorderLayout.CENTER);
						//BBU对应添加/保存/修改/删除
						{
							panel2_1 = new JPanel();
							panel2_1.setOpaque(false);
							panel2_1.setLayout(new GridLayout(1, 2, 0, 0));
							panel3_1.add(panel2_1, BorderLayout.SOUTH);
								panel = new JPanel(); 
								panel.setOpaque(false);
								panel2_1.add(panel);
								panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
									button = new JButton("添加");
									button.setFont(MyTools.f14);
									button.addActionListener(this);
									panel.add(button);
									button_1 = new JButton("保存");
									button_1.setFont(MyTools.f14);
									button_1.addActionListener(this);
									panel.add(button_1);
								panel_2 = new JPanel();
								panel_2.setOpaque(false);
								panel2_1.add(panel_2);
								panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
									btnbbu_3 = new JButton("转移BBU池");
									btnbbu_3.setFont(MyTools.f14);
									btnbbu_3.addActionListener(this);
									panel_2.add(btnbbu_3);	
									
									button_3 = new JButton("修改");
									button_3.setFont(MyTools.f14);
									button_3.addActionListener(this);
									panel_2.add(button_3);
									
									button_2 = new JButton("删除");
									button_2.setFont(MyTools.f14);
									button_2.addActionListener(this);
									panel_2.add(button_2);				
						}
				}
			}
		}
		/**************************************
		 * 添加退出按钮
		 ***************************************/
		{
			String path_button="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Bottom3.png";
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
	 * 功能：添加左侧树形列表监听
	 ***************************************/
	class SelListener implements TreeSelectionListener
	{
		@Override
		public void valueChanged(TreeSelectionEvent se) {
			// TODO 自动生成的方法存根
			bbu_add_rownum=0;
			JTree tree = (JTree)se.getSource();
			TreePath []selecttionpath=tree.getSelectionPaths();
			int get_pathcount=selecttionpath[0].getPathCount();
			if(get_pathcount==2)
			{
				DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				String selNodename=(String) selNode.getUserObject();
				String s[]=selNodename.split("池");
				Bbupool_num=s[1];//选中BBUPOOL的编号
//				System.out.println(s[0]+s[1]);//完美的将字符串拆开了
//				show_bbupool_textfield(Bbupool_num);
	//bbu_panel显示bbu的表格
				//bbu_table.clean_all();//bbu的JTable清空，重写BBU的JTable
				String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
				String []paras1={s[1]};
				bbu_table=new Bbu_table_module();
				bbu_table.bbu_table_search(sql1,paras1);
				bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
				jt.setModel(bbu_table);		
				//如果选中某个BBUPool，BBUPool表格高亮该BBUPool所在行。
				//需要遍历数据库
				String sql="select rn from(select rn = row_number()over(order by BbuPoolId),* from BbuPool) t Where BbuPoolId =  ?";
				String []paras={Bbupool_num};
				sql_allnum_model=new Bbu_table_module();
				ResultSet Bbu_allnum_sql=sql_allnum_model.total_num_sql(sql, paras);
				try {
					Bbu_allnum_sql.next();
					bbupool_row=Bbu_allnum_sql.getInt(1)-1;//BBU池0对应第1行，所以要减1
				} catch (Exception e) {
					// TODO: handle exception
				}
				jt_bbupool.setRowSelectionInterval(bbupool_row,bbupool_row);
			}
			if(get_pathcount==3)
			{
				jt_bbupool.clearSelection();//BBUPOOL表格取消任意行选中状态
				//BBU表格显示选中BBU
				DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				String selNodename=(String) selNode.getUserObject();
				String s[]=selNodename.split("U");
				String Bbu_num;
				Bbu_num=s[1];//选中BBU的编号
				String sql1="select * from Bbu where BbuId=?";//再从Bbu中调出bbu的数据		
				String []paras1={Bbu_num};
				bbu_table=new Bbu_table_module();
				bbu_table.bbu_table_search(sql1,paras1);
				jt.setModel(bbu_table);
			}
			
		}
		
	}
	/**************************************
	 * 功能：界面上各个按钮的监听响应功能
	 ***************************************/
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		/***************添加按钮******************/
		if(arg0.getSource()==button)
		{
			
			bbu_add_rownum=bbu_add_rownum+1;//表格里面添加新数据的行数
			String X;
			String Y;
			String Z;
			String sql="select * from BbuPool where BbuPoolId=?";//先从BbuPool中调出数据		
			String []paras={Bbupool_num};
			bbuc.clean_all();//bbuc的JTable清空，重写BBUC的JTable
			bbuc.search_num(sql, paras,9);//这次需要显示数据库BBUc的前4列的数据
			int rowcount=bbuc.getRowCount();
			System.out.println(rowcount);
			if(rowcount>=1)
			{
				X=(String) bbuc.getValueAt(0, 1);
				Y=(String) bbuc.getValueAt(0, 2);
				Z=(String) bbuc.getValueAt(0, 3);
				bbu_table.addRows(bbu_add_rownum,X,Y,Z);
				bbu_table.fireTableRowsInserted(bbu_searchrow, bbu_searchrow+1);	
				jt.setModel(bbu_table);
				
				jcb1=new JComboBox<>(jg1);			
				jcb1.setEditable(false);			
				jt.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcb1)); //列是从0开始数的			
				jt.setRowHeight(21);
				//接入环
				jg2=new Vector<>();
					String sql1="select * from LinkCircle where 1=?";//	
					String []paras1={"1"};//
					BBU_sql circlid_sql=new BBU_sql();
					ResultSet rs=circlid_sql.search(sql1, paras1);
					try {
						while(rs.next())
						{
							jg2.add(rs.getString(1));//LinkCircleId
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}finally
					{
						//关闭
						circlid_sql.close();
					}				
				jcb2=new JComboBox<>(jg2);			
				jcb2.setEditable(false);			
				jt.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jcb2)); //列是从0开始数的
			}	
		}
		/***************保存按钮******************/
		else if(arg0.getSource()==button_1)
		{
			if(bbu_add_rownum!=0)
			{
				
				boolean judge=true;
				//判断X,Y,Z坐标不能不填写，否则，不能保存
				for(int j=bbu_searchrow;j<bbu_searchrow+bbu_add_rownum;j++)
				{
					String X=(String) bbu_table.getValueAt(j, 1);//X
					String Y=(String) bbu_table.getValueAt(j, 2);//Y
					String Z=(String) bbu_table.getValueAt(j, 3);//Z
					if(X==null||Y==null||Z==null)
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"X,Y,Z坐标不能为空");
						return;
					}
					String TransPower=(String) bbu_table.getValueAt(j, 4);//传输功率
					String Res=(String) bbu_table.getValueAt(j, 5);//资源量	
					boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z)&isNumeric(TransPower)&isNumeric(Res);
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
					} catch (Exception e) {
						// TODO: handle exception
					}
					for(int i=bbu_searchrow;i<bbu_searchrow+bbu_add_rownum;i++)
					{
						String []paras=new String[12];
						paras[0]=(String) bbu_table.getValueAt(i, 0);//bbu id
						paras[1]=Bbupool_num;//选中BBUpool的编号
						paras[2]=(String) bbu_table.getValueAt(i, 1);//X
						paras[3]=(String) bbu_table.getValueAt(i, 2);//Y
						paras[4]=(String) bbu_table.getValueAt(i, 3);//Z
						paras[5]=null;
						if(bbu_table.getValueAt(i, 6).equals("RRS"))//RRU资源调度的方式:0：RRS 1:CIS 2:PFS
						{
							paras[6]="0";
						}else if(bbu_table.getValueAt(i, 6).equals("CIS"))
						{
							paras[6]="1";
						}else if(bbu_table.getValueAt(i, 6).equals("PFS"))
						{
							paras[6]="2";
						}else
						{
							paras[6]=null;
						}
//						paras[6]=(String) bbu_table.getValueAt(i, 6);//RRU资源调度的方式
						paras[7]=(String) bbu_table.getValueAt(i, 4);//传输功率dbm形式
						paras[8]=null;//s设备能耗
						paras[9]=null;//bbu状态是否活跃
						paras[10]=(String) bbu_table.getValueAt(i, 5);//资源量
						paras[11]=(String) bbu_table.getValueAt(i, 7);//接入环
						String sql="exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?,?";//还是要一行一行的添加exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
						b=bbu_table.add_delete_change_to_sql(sql, paras);
						}
					//刷新展示的表格
					String sql1="select * from Bbu where BbuPoolId=?";//再从Bbupool中调出bbu的数据		
					String []paras1={Bbupool_num};
					bbu_table=new Bbu_table_module();
					bbu_table.bbu_table_search(sql1,paras1);
					bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数，当前bbupool下面对应几个bbu
					jt.setModel(bbu_table);
					
					//刷新显示bbu总数的texrfield
					String sql="exec RowNum ?";
					String []paras={"Bbu"};
					String string_bbu_num=Update_totalnum(sql,paras);////更新了数据库中bbu总数
					lableBBU.setText(string_bbu_num);
					//刷新树形结构
					panel_1.removeAll();
					panel_1.repaint();
					build_tree();
					//保存成功
					if(b==true)
					{
						JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
					}	
					bbu_add_rownum=0;
				}
				
			}
			}
		/***************删除BBU按钮******************/
		else if(arg0.getSource()==button_2)
		{
			
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}		
			bbu_all_row=bbu_searchrow+bbu_add_rownum;
			if(bbu_all_row==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"此bbu池中没有数据");
				return;
			}
			if(bbu_all_row>0)
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除BBU", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					String ID=(String) bbu_table.getValueAt(rowNum,0);
					String sql="exec Drop_Bbu ?";
					String []paras={ID};
					b=bbu_table.add_delete_change_to_sql(sql, paras);
					String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
					String []paras1={Bbupool_num};
					bbu_table=new Bbu_table_module();
					bbu_table.bbu_table_search(sql1,paras1);
					bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
					jt.setModel(bbu_table);
					
					//刷新显示bbu总数的texrfield
					String sql2="exec RowNum ?";
					String []paras2={"Bbu"};
					String string_bbu_num=Update_totalnum(sql2,paras2);////更新了数据库中bbu总数
					lableBBU.setText(string_bbu_num);
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
		/***************修改按钮******************/
		else if(arg0.getSource()==button_3)
		{
			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			bbu_all_row=bbu_searchrow+bbu_add_rownum;
			if(bbu_all_row==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"此bbu池中没有数据");
				return;
			}
			if(bbu_all_row>0)
			{
				new Bbu_modify(this, "修改", true, bbu_table, rowNum);
				String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
				String []paras1={Bbupool_num};
				bbu_table=new Bbu_table_module();
				bbu_table.bbu_table_search(sql1,paras1);
				
				jt.setModel(bbu_table);		
			}
		}
		/***************转移BBU池******************/
		else if(arg0.getSource()==btnbbu_3)
		{
			int rowNum=jt.getSelectedRow();
			if (rowNum==-1)
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else
				{
				//转移
				String bbu_id=(String) bbu_table.getValueAt(rowNum, 0);
				String input_bbupool_num=JOptionPane.showInputDialog("将BBU"+bbu_id+"   转移到BBU池:");//input_bbupool_num用户输入希望转移到的bbu池
				String sql="ReConnectBbu2BbuPool ?,?";//再从Bbu中调出bbu的数据		
				String []paras={bbu_id,input_bbupool_num};
				bbu_table=new Bbu_table_module();
				b=bbu_table.add_delete_change_to_sql(sql,paras);
				//刷新jtable
				String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
				String []paras1={Bbupool_num};
				bbu_table=new Bbu_table_module();
				bbu_table.bbu_table_search(sql1,paras1);
				bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
				jt.setModel(bbu_table);
				//刷新树形结构
				panel_1.removeAll();
				panel_1.repaint();
				build_tree();
				if(b==true)
				{
					//删除成功
					JOptionPane.showMessageDialog(this, "转移成功");//弹出对话框
				}
				}
			
			
		}
		/***************添加bbu池按钮******************/
		else if(arg0.getSource()==btnbbu)
		{
			bbupool_add_rownum=bbupool_add_rownum+1;//表格里面添加新数据的行数
			
			BBUPool_table.addRows(bbupool_add_rownum);
			BBUPool_table.fireTableRowsInserted(bbupool_alreadyrow, bbupool_alreadyrow+1);	
			jt_bbupool.setModel(BBUPool_table);		
		}
		/***************保存bbu池按钮******************/
		else if(arg0.getSource()==btnbbu_1)
		{
			if(bbupool_add_rownum!=0)
			{
				//判断X,Y坐标不能不填写，否则，不能保存
				boolean judge=true;
				for(int j=(already_exist_rowbbupool+bbupool_alreadyrow);j<(already_exist_rowbbupool+bbupool_alreadyrow)+bbupool_add_rownum;j++)
				{
					String X=(String) BBUPool_table.getValueAt(j, 1);//X
					String Y=(String) BBUPool_table.getValueAt(j, 2);//Y
					if(X==null||Y==null||X.length()==0||Y.length()==0)
					{
						judge=false;
						//提示
						JOptionPane.showMessageDialog(this,"中心X坐标、中心Y坐标不能为空");
						return;
					}
					boolean temp=isNumeric(X)&isNumeric(Y);
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
					jt_bbupool.getCellEditor().stopCellEditing();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				for(int i=already_exist_rowbbupool+bbupool_alreadyrow;i<already_exist_rowbbupool+bbupool_alreadyrow+bbupool_add_rownum;i++)
				{
					String []paras=new String[9];
					paras[0]=(String) BBUPool_table.getValueAt(i, 0);//bbupool id
					paras[1]=(String) BBUPool_table.getValueAt(i, 1);//X
					paras[2]=(String) BBUPool_table.getValueAt(i, 2);//Y
					paras[3]=(String) BBUPool_table.getValueAt(i, 3);//Z
					paras[4]=null;
					paras[5]=null;
					paras[6]=null;
					paras[7]=null;
					paras[8]=(String) BBUPool_table.getValueAt(i, 4);//备注
					String sql="exec Add_BbuPool ?,?,?,?,?,?,?,?,?";//还是要一行一行的添加exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
					b=BBUPool_table.add_delete_change_to_sql(sql, paras);
					}
				//刷新展示的表格
				String sql1="select * from BbuPool where 1=?";//再从Bbupool中调出所有BBUPool数据		
				String []paras1={"1"};
				BBUPool_table=new BBUPool_table_module();
				BBUPool_table.bbupool_table_search(sql1,paras1);
				bbupool_alreadyrow=BBUPool_table.getRowCount();//bbu表格查询数据库，自动生成数据行数，当前bbupool下面对应几个bbu
				jt_bbupool.setModel(BBUPool_table);
				
				//刷新显示bbupool总数的texrfield
				String sql="exec RowNum ?";
				String []paras={"BbuPool"};
				String string_bbupool_num=Update_totalnum(sql,paras);////更新了数据库中bbu总数
				lableBBUPool.setText(string_bbupool_num);
				//刷新树形结构
				panel_1.removeAll();
				panel_1.repaint();
				build_tree();
				if(b=true)
				{
					//保存成功
					JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
				}
				bbupool_add_rownum=0;
			}
			}
			
		}
		/***************删除bbu池按钮******************/
		else if(arg0.getSource()==btnbbu_2)
		{
			int rowNum=this.jt_bbupool.getSelectedRow();//返回用户点中的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}		
			bbupool_all_row=bbupool_alreadyrow+bbupool_add_rownum;
			if(bbupool_all_row==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"尚未存在BBU池");
				return;
			}
			if(bbupool_all_row>0)
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除BBU池", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					String ID=(String) BBUPool_table.getValueAt(rowNum,0);
					String sql="exec Drop_BbuPool ?";
					String []paras={ID};
					b=BBUPool_table.add_delete_change_to_sql(sql, paras);
					String sql1="select * from BbuPool where 1=?";//再从Bbu中调出bbu的数据		
					String []paras1={"1"};
					BBUPool_table=new BBUPool_table_module();
					BBUPool_table.bbupool_table_search(sql1,paras1);
					bbupool_alreadyrow=BBUPool_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
					jt_bbupool.setModel(BBUPool_table);
					
					//刷新显示bbu池总数的texrfield
					String sql2="exec RowNum ?";
					String []paras2={"BbuPool"};
					String string_bbupool_num=Update_totalnum(sql2,paras2);////更新了数据库中bbu总数
					lableBBUPool.setText(string_bbupool_num);
					//刷新树形结构
					panel_1.removeAll();
					panel_1.repaint();
					build_tree();
					if(b==true)
					{
						//删除成功
						JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
					}
					//
					bbu_table=new Bbu_table_module();
					bbu_table.clean_all();
					jt.setModel(bbu_table);
					//BBU表格刷新
					String sql4="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
					String []paras4={ID};
					bbu_table=new Bbu_table_module();
					bbu_table.bbu_table_search(sql4,paras4);
					bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
					jt.setModel(bbu_table);
					
					//刷新显示bbu总数的texrfield
					String sql3="exec RowNum ?";
					String []paras3={"Bbu"};
					String string_bbu_num=Update_totalnum(sql3,paras3);////更新了数据库中bbu总数
					lableBBU.setText(string_bbu_num);
				}
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
	//public void delete_bbuc(String tempBbupool_num)//删除BBU池的函数//////////////////////
//{
//	
//	String sql="exec Drop_BbuPool ?";
//	String []paras={tempBbupool_num};
//	bbu_table.add_delete_change_to_sql(sql, paras);
//	//刷新bbu池的textfield
//	textField_4.setText("");
//	textField_5.setText("");
//	textField_6.setText("");
//	textField_7.setText("");
//	textField_8.setText("");
//	//刷新jtable
//	bbu_table=new Bbu_table_module();
//	jt.setModel(bbu_table);
//	//刷新显示bbu池总数的texrfield
//	String sql2="exec RowNum ?";
//	String []paras2={"BbuPool"};
//	String string_bbuc_num=Update_totalnum(sql2,paras2);
//	lableBBUPool.setText(string_bbuc_num);
//	//刷新显示bbu总数的texrfield
//	String sql3="exec RowNum ?";
//	String []paras3={"Bbu"};
//	String string_bbu_num=Update_totalnum(sql3,paras3);
//	lableBBU.setText(string_bbu_num);
//	//刷新树形结构
//	panel_1.removeAll();
//	panel_1.repaint();
//	build_tree();
//	//删除成功
//	JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
//}
//	@Override
//	public void valueChanged(ListSelectionEvent arg0) {
//		// TODO 自动生成的方法存根
//		select_row_num= jt.getSelectedRows();
//	}
	/**************************************
	 * 功能：创建左侧的目录树
	 ***************************************/
	public void build_tree()
	{
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
//	public void show_bbupool_textfield(String Bbupool_num)//显示bbuPool的ID，X,Y,Z,坐标，备注textfield的函数
//	{
//		String sql="select * from BbuPool where BbuPoolId=?";//先从BbuPool中调出数据		
//		String []paras={Bbupool_num};
//		bbuc.clean_all();//bbuc的JTable清空，重写BBUC的JTable
//		bbuc.search_num(sql, paras,9);//这次需要显示数据库BBUc的前4列的数据
//		textField_4.setText((String) bbuc.getValueAt(0, 0));
//		textField_5.setText((String) bbuc.getValueAt(0, 1));
//		textField_6.setText((String) bbuc.getValueAt(0, 2));
//		textField_7.setText((String) bbuc.getValueAt(0, 3));
//		textField_8.setText((String) bbuc.getValueAt(0, 8));
//	}
	/***************实时读取数据库中对象个数，更新总数的函数******************/
	public String Update_totalnum(String sql,String[]paras)
	{
		sql_allnum_model=new Bbu_table_module();
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
	 * 功能：监听相应鼠标点击的函数
	 ***************************************/
@Override
public void mouseClicked(MouseEvent e) {
	// TODO 自动生成的方法存根
	if(e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e))
	{
		int rowselected=this.jt_bbupool.getSelectedRow();//返回用户点中的行
		Bbupool_num=(String) jt_bbupool.getValueAt(rowselected, 0);
		//rru_panel显示rru的表格
		String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu池中调出bbu的数据		
		String []paras1={Bbupool_num};
		bbu_table=new Bbu_table_module();
		bbu_table.bbu_table_search(sql1,paras1);
		bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
		jt.setModel(bbu_table);
		//JTree树显示相应BBU节点选中状态
		Enumeration enumeration;
		enumeration=root.preorderEnumeration();
		while(enumeration.hasMoreElements())
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement();
			String selNodename=(String) node.getUserObject();
			TreePath treePath0 = new TreePath(node.getPath());
			int num=treePath0.getPathCount();
			if(num==2)
			{
				String s[]=selNodename.split("池");
				if(Bbupool_num.equals(s[1]))//选中BBU的编号
				{
					//根据 node 获取 TreePath
					TreePath treePath = new TreePath(node.getPath());
//					Treeroot.addSelectionPath(treePath);
					Treeroot.getSelectionModel().setSelectionPath(treePath);

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

