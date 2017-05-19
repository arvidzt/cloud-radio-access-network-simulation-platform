package ui.Draft;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.tree.*;

import org.apache.poi.hwpf.usermodel.BorderCode;

import ui.Add_bbu_rru_ue.Bbu_modify;
import ui.Add_bbu_rru_ue.Bbu_table_module;
import ui.Share_bag.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

public class CopyOfBBU_add extends JDialog implements ActionListener{
	private  JPanel contentPanel;
	
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2,textField_3;
	private JTextField textField_4,textField_5,textField_6,textField_7,textField_8;
	JSplitPane splitPane;
	JPanel panel1,panel_1;
	JPanel panel2,panel2_1,panel2_2,panel2_2_1,panel2_2_2,panel2_2_3,panel2_3;
	JPanel panel3_1,panel3_1_2,panel3_2,panel5,panel6,panel7,panel8,panel9,buttonPane,panel_bbuc,panel_10;
	JButton button5,okButton,cancelButton;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	Num_search_module rru,ue,bbu,bbuc;
	Bbu_table_module bbu_table,sql_allnum_model,bbuc_table,Bbupool_maxid_model;//添加bbu的，专门用来得到bbu rru ue 总数的，添加bbu池,找到bbu池最大的当前ID 
	int bbu_num_total,rru_num_total,ue_num_total,total_num;//数据库中一共有多少个bbu
	int bbu_max_id;//bbupool当前最大id
	int bbupool_num;//一共有多少个bbu池
	int bbu_add_rownum;//每次一共添加了多少个bbu
	int bbu_searchrow;//通过查询数据库得到的每个bbu池对应的bbu行数
	int bbu_all_row;//一个bbu池下，一共有多少个bbu。
	JLabel lableBBUPool,lableBBU,lableRRU,lableUE;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"0","1"};//RRU的调度方式数组，不填写默认是0
	Vector jg2=null;//LinkID BBU接入环
	String Bbupool_num;//选中BBUPOOL的编号
	JTable jt;
	JScrollPane scrollPane,jsp;
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

	public static void main(String[] args) {
		try {
			CopyOfBBU_add dialog = new CopyOfBBU_add();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public BBU_add(Frame owner,String title,boolean modal) {
//		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
	public CopyOfBBU_add() {
		//设置窗体属性
		setSize(1000, 620);
		setLocation(150, 50);
		setTitle("BBU参数输入");
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout());

		{
			splitPane = new JSplitPane();
//			splitPane.setDividerSize(0);

			splitPane.setDividerLocation(130);
			contentPanel.add(splitPane);
			{
				panel1 = new JPanel();
//				panel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
//左栏
				splitPane.setLeftComponent(panel1);
				panel1.setLayout(new BorderLayout(0, 0));
				{
					String path_10="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Left.png";
					Image image_10=new ImageIcon(path_10).getImage(); 
					panel_10= new BackgroundPanel(image_10);
					
					JLabel label = new JLabel("网元列表");
					label.setFont(MyTools.f1);
					panel_10.add(label, BorderLayout.CENTER);
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
			
//右栏
			{
				String path_2="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Right3.png";
				Image image_2=new ImageIcon(path_2).getImage(); 
				panel2= new BackgroundPanel(image_2);
//				panel2 = new JPanel();
				splitPane.setRightComponent(panel2);
				panel2.setLayout(new BorderLayout(0, 0));
				{
					panel2_1 = new JPanel();
					panel2_1.setOpaque(false);
					panel2_1.setLayout(new GridLayout(1, 2, 0, 0));
					panel2.add(panel2_1, BorderLayout.SOUTH);
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
				{
					panel2_2 = new JPanel();
					panel2_2.setOpaque(false);
					panel2.add(panel2_2, BorderLayout.NORTH);
					panel2_2.setLayout(new GridLayout(4, 1, 0, 0));//4行1列的表格
						//第1行
								JPanel panel1=new JPanel();
								panel1.setOpaque(false);
								panel1.setLayout(new BorderLayout());
								panel2_2.add(panel1);

								{
									JPanel panel_BBU_RRU_UE=new JPanel();
									panel_BBU_RRU_UE.setOpaque(false);
									panel_BBU_RRU_UE.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
									panel1.add(panel_BBU_RRU_UE,BorderLayout.EAST);
									{
										JLabel lblBbu_1 = new JLabel("BBU池个数:");//可以利用生成左栏时的数据bbupool_num
										lblBbu_1.setFont(MyTools.f15);
//										lblBbu_1.setForeground(Color.white);
										panel_BBU_RRU_UE.add(lblBbu_1);
										int temp_bbupool_num=bbupool_num;//bbupool_num一共有多少个bbupool
										String string_bbupool_num=Integer.toString(temp_bbupool_num);
										lableBBUPool = new JLabel(string_bbupool_num);
										lableBBUPool.setFont(MyTools.f15);
										panel_BBU_RRU_UE.add(lableBBUPool);
										
										JLabel lable_blank=new JLabel("  ");//空格
										panel_BBU_RRU_UE.add(lable_blank);
										
									}
									{
										JLabel lblBbu_2 = new JLabel("BBU个数:");//需要遍历数据库
										lblBbu_2.setFont(MyTools.f15);
//										lblBbu_2.setForeground(Color.white);
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
										
										JLabel lable_blank=new JLabel("  ");//空格
										panel_BBU_RRU_UE.add(lable_blank);
									}
									{
										JLabel lblRru = new JLabel("RRU个数:");
										lblRru.setFont(MyTools.f15);
//										lblRru.setForeground(Color.white);
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
										
										JLabel lable_blank=new JLabel("  ");//空格
										panel_BBU_RRU_UE.add(lable_blank);
									}
									{
										JLabel lblUe = new JLabel("UE个数:");
										lblUe.setFont(MyTools.f15);
//										lblUe.setForeground(Color.white);
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
										
										JLabel lable_blank=new JLabel("  ");//空格
										panel_BBU_RRU_UE.add(lable_blank);
									}
								}
								
								panel_12 = new JPanel();
								panel_12.setOpaque(false);
								panel1.add(panel_12, BorderLayout.WEST);
								
								lblBbu = new JLabel("BBU参数录入");
								lblBbu.setFont(MyTools.f1);
								panel_12.add(lblBbu);
						//第2行
						{
							panel2_2_2 = new JPanel();
							panel2_2_2.setOpaque(false);
							panel2_2.add(panel2_2_2);
							panel2_2_2.setLayout(new GridLayout(1, 7, 0, 0));
								JLabel lblBbuid = new JLabel("BBU池ID", null, JLabel.CENTER);
								lblBbuid.setFont(MyTools.f15);
//								lblBbuid.setForeground(Color.white);
								panel2_2_2.add(lblBbuid);
								JLabel lblX = new JLabel("X坐标(m)", null, JLabel.CENTER);
								lblX.setFont(MyTools.f15);
//								lblX.setForeground(Color.white);
								panel2_2_2.add(lblX);
								JLabel lblY = new JLabel("Y坐标(m)", null, JLabel.CENTER);
								lblY.setFont(MyTools.f15);
//								lblY.setForeground(Color.white);
								panel2_2_2.add(lblY);
								JLabel lblZ = new JLabel("Z坐标(m)", null, JLabel.CENTER);
								lblZ.setFont(MyTools.f15);
//								lblZ.setForeground(Color.white);
								panel2_2_2.add(lblZ);
								JLabel label = new JLabel("备注", null, JLabel.CENTER);
								label.setFont(MyTools.f15);
//								label.setForeground(Color.white);
								panel2_2_2.add(label);
							{
								JPanel panel_3 = new JPanel();
								panel_3.setOpaque(false);
								panel2_2_2.add(panel_3);
							}
							{
								JPanel panel_3 = new JPanel();
								panel_3.setOpaque(false);
								panel2_2_2.add(panel_3);
							}
							{
								JPanel panel_3 = new JPanel();
								panel_3.setOpaque(false);
								panel2_2_2.add(panel_3);
							}
						}
						//第三行
						{
							panel2_2_3 = new JPanel();
							panel2_2_3.setOpaque(false);
							panel2_2.add(panel2_2_3);
							panel2_2_3.setLayout(new GridLayout(1, 2, 0, 0));
							{
								panel5 = new JPanel();
								panel5.setOpaque(false);
								panel2_2_3.add(panel5);
								panel5.setLayout(new GridLayout(1, 4, 0, 0));
								{
									panel6 = new JPanel();
									panel6.setOpaque(false);
									panel5.add(panel6);
										textField_4 = new JTextField();
										panel6.add(textField_4);
										textField_4.setColumns(5);
								}
								{
									panel7 = new JPanel();
									panel7.setOpaque(false);
									panel5.add(panel7);
										textField_5 = new JTextField();
										panel7.add(textField_5);
										textField_5.setColumns(7);
								}
								{
									panel8 = new JPanel();
									panel8.setOpaque(false);
									panel5.add(panel8);
										textField_6 = new JTextField();
										panel8.add(textField_6);
										textField_6.setColumns(7);
								}
								{
									panel9 = new JPanel();
									panel9.setOpaque(false);
									panel5.add(panel9);
										textField_7 = new JTextField();
										panel9.add(textField_7);
										textField_7.setColumns(7);
								}
							}
							{
								panel2_3 = new JPanel();
								panel2_3.setOpaque(false);
								panel2_2_3.add(panel2_3);
								panel2_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
									textField_8 = new JTextField(20);
									panel2_3.add(textField_8);
									
							}
						}
						
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
						panel2_2.add(panel_4);
				}
//显示每个bbu池对应bbu的列表
				{
					panel3_1 = new JPanel();
					panel3_1.setOpaque(false);
					panel2.add(panel3_1, BorderLayout.CENTER);
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
				}
			}
		}
		{
			String path_button="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"Add_BBU_RRU_UE_Bottom3.png";
			Image image_button=new ImageIcon(path_button).getImage(); 
			buttonPane= new BackgroundPanel(image_button);
//			buttonPane = new JPanel();
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
				show_bbupool_textfield(Bbupool_num);
	//bbu_panel显示bbu的表格
				//bbu_table.clean_all();//bbu的JTable清空，重写BBU的JTable
				String sql1="select * from Bbu where BbuPoolId=?";//再从Bbu中调出bbu的数据		
				String []paras1={s[1]};
				bbu_table=new Bbu_table_module();
				bbu_table.bbu_table_search(sql1,paras1);
				bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数
				jt.setModel(bbu_table);

				
			}
			
		}
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==button)//添加//////////////////////////////////////////////////////////////////
		{
			
			bbu_add_rownum=bbu_add_rownum+1;//表格里面添加新数据的行数
			
			bbu_table.addRows(bbu_add_rownum,textField_5.getText(),textField_6.getText(),textField_7.getText());
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
		else if(arg0.getSource()==button_1)//保存///////////////////////////////////////////////////////////
		{
			if(bbu_add_rownum!=0)
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
					paras[6]=(String) bbu_table.getValueAt(i, 6);//RRU资源调度的方式
					paras[7]=(String) bbu_table.getValueAt(i, 4);//传输功率dbm形式
					paras[8]=null;//s设备能耗
					paras[9]=null;//bbu状态是否活跃
					paras[10]=(String) bbu_table.getValueAt(i, 5);//资源量
					paras[11]=(String) bbu_table.getValueAt(i, 7);//接入环
					String sql="exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?,?";//还是要一行一行的添加exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
					bbu_table.add_delete_change_to_sql(sql, paras);
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
				JOptionPane.showMessageDialog(this, "保存成功");//弹出对话框
				bbu_add_rownum=0;
			}
			

			}
		else if(arg0.getSource()==button_2)//删除bbu//////////////////////////////////////////////////////////
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
					bbu_table.add_delete_change_to_sql(sql, paras);
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
					//删除成功
					JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
				}
			}
		}
		else if(arg0.getSource()==button_3)//修改//////////////////////////////////////////////////////////
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
		else if(arg0.getSource()==btnbbu_3)//转移BBU池//////////////////////////////////////////////////////////
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
				bbu_table.add_delete_change_to_sql(sql,paras);
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
				//删除成功
				JOptionPane.showMessageDialog(this, "转移成功");//弹出对话框
				
				}
			
			
		}
		else if(arg0.getSource()==btnbbu)//添加bbu池//////////////////////////////////////////////////////////
		{
			//bbu池是一行一行添加的，在数据库里面添加一行新的bbu池数据
			bbuc_table=new Bbu_table_module();
			
			//找到bbupool最大的当前ID
			String sql1="exec RowMax ?";
			String []paras1={"BbuPool"};//找到bbupool最大的当前ID
			Bbupool_maxid_model=new Bbu_table_module();
			ResultSet Bbu_maxid=Bbupool_maxid_model.total_num_sql(sql1, paras1);
			try {
				Bbu_maxid.next();
				bbu_max_id=Bbu_maxid.getInt(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			int temp_rru_num=bbu_max_id;
			String string_bbu_max_id=Integer.toString(bbu_max_id+1);//数据库中bbu池总数，添加的要+1
			//添加bbu池
			String []paras=new String[9];
			paras[0]=string_bbu_max_id;//添加的bbu池 id——》代替选中的bbupool的ID：Bbupool_num
			for(int i=1;i<=9;i++)//因为bbu池是第0列到第8列,第8列是备注
			{
				paras[i]=null;
			}
			String sql="exec Add_BbuPool ?,?,?,?,?,?,?,?,?";
			bbuc_table.add_delete_change_to_sql(sql, paras);
			//刷新显示bbu池总数的textfield
			String sql2="exec RowNum ?";
			String []paras2={"BbuPool"};
			String string_bbuc_num=Update_totalnum(sql2,paras2);////更新了数据库中bbu池总数
			lableBBUPool.setText(string_bbuc_num);
			
			Bbupool_num=string_bbu_max_id;
		
			show_bbupool_textfield(Bbupool_num);
			//刷新树形结构
			panel_1.removeAll();
			panel_1.repaint();
			build_tree();
			
			//刷新展示的表格
			String sql3="select * from Bbu where BbuPoolId=?";//再从Bbupool中调出bbu的数据		
			String []paras3={Bbupool_num};
			bbu_table=new Bbu_table_module();
			bbu_table.bbu_table_search(sql3,paras3);
			bbu_searchrow=bbu_table.getRowCount();//bbu表格查询数据库，自动生成数据行数，当前bbupool下面对应几个bbu
			jt.setModel(bbu_table);
			
			

		}
		else if(arg0.getSource()==btnbbu_1)//保存bbu池//////////////////////////////////////////////////////////
		{
			//将textfield数据存到sql中
			String sql="update BbuPool set X=?,Y=?,Z=?,BbuPoolInfo=? where BbuPoolId=?";
			String []paras={textField_5.getText(),textField_6.getText(),textField_7.getText(),textField_8.getText(),textField_4.getText()};
			bbuc_table=new Bbu_table_module();
			bbuc_table.add_delete_change_to_sql(sql, paras);
			//刷新textfield
			JOptionPane.showMessageDialog(this,"保存bbu池数据成功");
			return;
			
		}
		else if(arg0.getSource()==btnbbu_2)//删除bbu池//////////////////////////////////////////////////////////
		{
			if(Bbupool_num.isEmpty())//如果用户没有选择一个BBU池
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一个BBU池");
				return;
			}else
			{
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除BBU池,会同时删除BBU池下面的BBU", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					delete_bbuc(Bbupool_num);//选中BBUPOOL的编号
				}
				
			}
			
		}
		else if(arg0.getSource()==cancelButton)//取消//////////////////////////////////////////////////////////
		{
			this.dispose();//关闭对话框
		}
		
	}
public void delete_bbuc(String tempBbupool_num)//删除BBU池的函数//////////////////////
{
	
	String sql="exec Drop_BbuPool ?";
	String []paras={tempBbupool_num};
	bbu_table.add_delete_change_to_sql(sql, paras);
	//刷新bbu池的textfield
	textField_4.setText("");
	textField_5.setText("");
	textField_6.setText("");
	textField_7.setText("");
	textField_8.setText("");
	//刷新jtable
	bbu_table=new Bbu_table_module();
	jt.setModel(bbu_table);
	//刷新显示bbu池总数的texrfield
	String sql2="exec RowNum ?";
	String []paras2={"BbuPool"};
	String string_bbuc_num=Update_totalnum(sql2,paras2);
	lableBBUPool.setText(string_bbuc_num);
	//刷新显示bbu总数的texrfield
	String sql3="exec RowNum ?";
	String []paras3={"Bbu"};
	String string_bbu_num=Update_totalnum(sql3,paras3);
	lableBBU.setText(string_bbu_num);
	//刷新树形结构
	panel_1.removeAll();
	panel_1.repaint();
	build_tree();
	//删除成功
	JOptionPane.showMessageDialog(this, "删除成功");//弹出对话框
}
//	@Override
//	public void valueChanged(ListSelectionEvent arg0) {
//		// TODO 自动生成的方法存根
//		select_row_num= jt.getSelectedRows();
//	}
	public void build_tree()
	{
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
	public void show_bbupool_textfield(String Bbupool_num)//显示bbuPool的ID，X,Y,Z,坐标，备注textfield的函数
	{
		String sql="select * from BbuPool where BbuPoolId=?";//先从BbuPool中调出数据		
		String []paras={Bbupool_num};
		bbuc.clean_all();//bbuc的JTable清空，重写BBUC的JTable
		bbuc.search_num(sql, paras,9);//这次需要显示数据库BBUc的前4列的数据
		textField_4.setText((String) bbuc.getValueAt(0, 0));
		textField_5.setText((String) bbuc.getValueAt(0, 1));
		textField_6.setText((String) bbuc.getValueAt(0, 2));
		textField_7.setText((String) bbuc.getValueAt(0, 3));
		textField_8.setText((String) bbuc.getValueAt(0, 8));
	}
	public String Update_totalnum(String sql,String[]paras)//更新总数的函数
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



}

