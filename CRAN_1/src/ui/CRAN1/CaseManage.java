package ui.CRAN1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;

import ui.Add_bbu_rru_ue.Input2;
import ui.Share_bag.BBU_sql;
import ui.Share_bag.MyTools;

import javax.swing.JTextField;

public class CaseManage extends JDialog implements ActionListener,MouseListener{
	static String name = BBU_sql.name;
	static String passWord = BBU_sql.pswd;
	private final JPanel contentPanel = new JPanel();
	JButton button_new,Button_modify,Button_delete,Button_import,Button_search,Button_all;
	private JTextField textField;
	private JPanel panel_2;
	CaseTableModule case_table;
	JTable jt_case;
	JScrollPane scrollPaneCase;
	String caseName; //当前实例名称
	String Account;//当前账户名称
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			CaseManage dialog = new CaseManage();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
//	public CaseManage() {
	public CaseManage(Frame owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		setBounds(100, 100, 613, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				JLabel label = new JLabel("实例管理");
				label.setFont(MyTools.f1);
				panel.add(label);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				button_new = new JButton("新建");
				button_new.addActionListener(this);
				panel.add(button_new);
			}
			{
				Button_modify = new JButton("修改");
				Button_modify.addActionListener(this);
				panel.add(Button_modify);
			}
			{
				Button_delete = new JButton("删除");
				Button_delete.addActionListener(this);
				panel.add(Button_delete);
			}
			{
				Button_import = new JButton("导入实例");
				Button_import.addActionListener(this);
				panel.add(Button_import);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JLabel lblNewLabel = new JLabel("实例名：");
					panel_1.add(lblNewLabel);
				}
				{
					textField = new JTextField();//查询textfield
					panel_1.add(textField);
					textField.setColumns(16);
				}
				{
					Button_search = new JButton("查询");
					Button_search.addActionListener(this);
					panel_1.add(Button_search);
				}
				{
					Button_all = new JButton("全部显示");
					Button_all.addActionListener(this);
					panel_1.add(Button_all);
				}
			}
			//获取数据库CurrentUser表中数据
			String sql="select userName,caseName from CurrentUser where ID=?";
			String []paras={"1"};
			BBU_sql sql_case=new BBU_sql();
			ResultSet sql_case_ResultSet=sql_case.search(sql, paras);
			try {
				sql_case_ResultSet.next();
				Account=sql_case_ResultSet.getString(1).trim();
				caseName=sql_case_ResultSet.getString(2).trim();//当前实例名称
			} catch (Exception e) {
				// TODO: handle exception
			}
			{
				panel_2 = new JPanel();
				panel.add(panel_2);
				panel_2.setLayout(new BorderLayout(0, 0));
				case_table=new CaseTableModule();
				jt_case=new JTable(case_table);
				jt_case.addMouseListener(this);
				jt_case.setBackground(new Color(240, 245, 250));//JTable的背景颜色设置
				jt_case.setRowHeight(20);//修改表格宽度
				jt_case.getColumnModel().getColumn(0).setPreferredWidth(10);
				//获得表头
				JTableHeader tableH = jt_case.getTableHeader();
				tableH.setFont(MyTools.f12);
				tableH.setBackground(new Color(204, 208, 213));
				  
				DefaultTableCellRenderer r =   new   DefaultTableCellRenderer();   
				r.setHorizontalAlignment(JLabel.CENTER);   
				r.setFont(MyTools.f12);
				jt_case.setDefaultRenderer(Object.class,r);
				
				jt_case.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
				scrollPaneCase = new JScrollPane(jt_case);
				scrollPaneCase.getViewport().setBackground(Color.white);
				panel_2.add(scrollPaneCase, BorderLayout.CENTER);
				//刷新展示的表格PCase
				String sql2="select * from PCase where 1=?";//再从PCase中调出数据	
				String []paras2={"1"};//可以显示所有的UeGroup  
				case_table=new CaseTableModule();
				case_table.case_table_search(sql2,paras2);
				jt_case.setModel(case_table);
			}
			
			

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==button_new)//新建
		{
			int a=JOptionPane.showConfirmDialog(null, "是否确定新建实例？", "", JOptionPane.YES_NO_OPTION);
			if(a==JOptionPane.NO_OPTION)
			{
				return;
			}
			if(a==JOptionPane.YES_OPTION)//新建实例
			{
				int b=JOptionPane.showConfirmDialog(null, "是否保存当前实例?", "", JOptionPane.YES_NO_OPTION);
				if(b==JOptionPane.YES_OPTION)//保存当前实例信息：数据库-----文件夹
				{
					//保存数据库的表格到文件夹
					Save_DatebaseToFile();
				}
				//新建实例
				new NewCase(this,"新建实例", true).setVisible(true);
				//更新实例表格
				String sql2="select * from PCase where 1=?";//再从PCase中调出数据	
				String []paras2={"1"};//可以显示所有的UeGroup  
				case_table=new CaseTableModule();
				case_table.case_table_search(sql2,paras2);
				jt_case.setModel(case_table);
				//获取数据库CurrentUser表中数据
				String sql="select caseName from CurrentUser where ID=?";
				String []paras={"1"};
				BBU_sql sql_case=new BBU_sql();
				ResultSet sql_case_ResultSet=sql_case.search(sql, paras);
				try {
					sql_case_ResultSet.next();
					caseName=sql_case_ResultSet.getString(1).trim();//当前实例名称
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		}
		else if(arg0.getSource()==Button_modify)//修改
		{
			int rowNum_selected=jt_case.getSelectedRow();//返回用户点中的行
			if(rowNum_selected==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else{
				int a=JOptionPane.showConfirmDialog(null, "是否确定修改实例？", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)//修改实例
				{
					//修改实例
					new ModifyCase(this,"修改实例", true,case_table,rowNum_selected).setVisible(true);
					//更新实例表格
					String sql2="select * from PCase where 1=?";//再从PCase中调出数据	
					String []paras2={"1"};//可以显示所有的UeGroup  
					case_table=new CaseTableModule();
					case_table.case_table_search(sql2,paras2);
					jt_case.setModel(case_table);
				}
			}
		}
		else if(arg0.getSource()==Button_delete)//删除
		{
			int rowNum_selected=jt_case.getSelectedRow();//返回用户点中的行
			if(rowNum_selected==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else{
					String name=(String) jt_case.getValueAt(rowNum_selected, 0);//实例名称
					//查看要删除的实例名称是否是当前正在使用的实例名称
					String sql="select caseName from CurrentUser where ID=?";
					String []paras={"1"};
					BBU_sql sql_case=new BBU_sql();
					ResultSet sql_case_ResultSet=sql_case.search(sql, paras);
					try {
						sql_case_ResultSet.next();
						caseName=sql_case_ResultSet.getString(1).trim();//当前实例名称
					} catch (Exception e) {
						// TODO: handle exception
					}
					if(name.equals(caseName))//不可以删除
					{
						//提示
						JOptionPane.showMessageDialog(this,"实例正在使用，不能删除。请先导入其他实例。");
						return;
					}else//可以删除
					{
						int a=JOptionPane.showConfirmDialog(null, "是否确定删除实例？", "", JOptionPane.YES_NO_OPTION);
						if(a==JOptionPane.NO_OPTION)
						{
							return;
						}
						if(a==JOptionPane.YES_OPTION)//删除实例
						{
							boolean b=true;
							//对数据库---存储文件操作
							
							//对实例表格的操作
							String sql1="delete from PCase where caseName=?";
							String []paras1={name};
							b=case_table.add_delete_change_to_sql(sql1, paras1);
							if(b==true)
							{
								//删除默认路径下该账户对应文件夹
								String workspaceRootPath="D:\\CRAN\\"+Account+"\\"+name.trim();
//								System.out.println(workspaceRootPath);
								File file = new File(workspaceRootPath);  
								boolean success = deleteDir(file);
								if (success) {
						            System.out.println("删除实例文件成功" );
						        } else {
						            System.out.println("删除实例文件失败" );
						        }   
							}else
							{
								JOptionPane.showMessageDialog(this, "删除实例失败");//弹出对话框
							}
							//刷新表格
							String sql2="select * from PCase where 1=?";//再从PCase中调出数据	
							String []paras2={"1"};//可以显示所有的UeGroup  
							case_table=new CaseTableModule();
							case_table.case_table_search(sql2,paras2);
							jt_case.setModel(case_table);
						}
					}
				
			}
		}
		else if(arg0.getSource()==Button_import)//导入实例
		{
			int rowNum_selected=jt_case.getSelectedRow();//返回用户点中的行
			if(rowNum_selected==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			else{
				String name=(String) jt_case.getValueAt(rowNum_selected, 0);
				//查看要删除的实例名称是否是当前正在使用的实例名称
				String sql1="select caseName from CurrentUser where ID=?";
				String []paras2={"1"};
				BBU_sql sql_case=new BBU_sql();
				ResultSet sql_case_ResultSet=sql_case.search(sql1, paras2);
				try {
					sql_case_ResultSet.next();
					caseName=sql_case_ResultSet.getString(1).trim();//当前实例名称
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(name.equals(caseName))//不可以导入
				{
					//提示
					JOptionPane.showMessageDialog(this,"实例正在使用，不能导入。请导入其他实例。");
					return;
				}else//可以导入
				{
					int a=JOptionPane.showConfirmDialog(null, "是否确定导入实例？", "", JOptionPane.YES_NO_OPTION);
					if(a==JOptionPane.NO_OPTION)
					{
						return;
					}
					if(a==JOptionPane.YES_OPTION)//导入实例
					{
						int b=JOptionPane.showConfirmDialog(null, "是否保存当前实例", "", JOptionPane.YES_NO_OPTION);
						if(b==JOptionPane.YES_OPTION)//保存当前实例信息：数据库-----文件夹
						{
							//保存数据库的表格到文件夹
							Save_DatebaseToFile();
						}
						//更新CurrentUser表格
						String sql="update CurrentUser set caseName=? where ID=?";	
						String []paras={name,"1"};
						BBU_sql sqlh=new BBU_sql();
						sqlh.add_delete_change(sql, paras);
						//文件夹导入数据库
						String path="D:\\CRAN\\"+Account.trim()+'\\'+name.trim();
						Input2.oneKeyInput(path);//导入实例时会自动清空数据库
						//获取数据库CurrentUser表中数据
						String sql3="select caseName from CurrentUser where ID=?";
						String []paras3={"1"};
						BBU_sql sql_case3=new BBU_sql();
						ResultSet sql_case_ResultSet3=sql_case3.search(sql3, paras3);
						try {
							sql_case_ResultSet3.next();
							caseName=sql_case_ResultSet3.getString(1).trim();//当前实例名称
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}
		}
		else if(arg0.getSource()==Button_search)//查询
		{
			String search_name=textField.getText().trim();

			String sql2="select * from PCase where caseName=?";//再从PCase中调出添加的数据	
			String []paras2={search_name};
			case_table=new CaseTableModule();
			case_table.case_table_search(sql2,paras2);
			jt_case.setModel(case_table);
		}
		else if(arg0.getSource()==Button_all)//全部显示
		{
			String sql2="select * from PCase where 1=?";//再从PCase中调出添加的数据	
			String []paras2={"1"};
			case_table=new CaseTableModule();
			case_table.case_table_search(sql2,paras2);
			jt_case.setModel(case_table);
		}
	}
	//清空数据库所有数据--------------退出当前实例时，清空数据库
		private void ClearDatebase() {
			// TODO 自动生成的方法存根
			Connection conn = null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = java.sql.DriverManager.getConnection(  
		                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
		         conn.setAutoCommit(true);
		         Statement stmt = conn.createStatement();
		         String sql_drop = "exec Drop_All";
				stmt.executeUpdate(sql_drop);
			
			}catch(SQLException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
				}
		}

		private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            //递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删除
	        return dir.delete();
	    }  
		private void CreatFile( String Path) {
			// TODO 自动生成的方法存根
//			String directory = "D:\\CRAN\\"+Account+"\\"+Case_Name;
			String directory = Path;
			File f = new File(directory);
			if(f.exists()) {
				  // 文件已经存在，输出文件的相关信息
			}
			else
			{            
				f.mkdirs();//创建文件所在的目录   
			}
			
		}
		private void Save_DatebaseToFile()//保存数据库的表格到文件夹
		{
			if(caseName.equals("-1"))//新建的实例,还没有实例名和实例备注写入数据库，没有实例文件夹
			{
				//更新PCase账户对应的实例表格
				String Name=(String) JOptionPane.showInputDialog(null,"保存实例名称:","title",JOptionPane.PLAIN_MESSAGE,null,null,""); 
				if(Name == null)
                {
                   return;
                }
               if (Name.equals("") )
               {
                  JOptionPane.showMessageDialog(null,"未输入保存实例名称，保存失败","提示信息",JOptionPane.ERROR_MESSAGE);
                   return;
               }
				String Remark=(String) JOptionPane.showInputDialog(null,"保存实例备注:","title",JOptionPane.PLAIN_MESSAGE,null,null,"");
				if(Remark == null)
                {
                   return;
                }
				//插入新的账户数据
				String sql_add="insert into PCase values(?,?)";
				String []paras_add={Name.trim(),Remark.trim()};
				BBU_sql sqlh1=new BBU_sql();
				sqlh1.add_delete_change(sql_add, paras_add);
				
				//默认路径下新建实例文件夹
				String Path = "D:\\CRAN\\"+Account.trim()+"\\"+Name.trim();
				System.out.println(Path);
				CreatFile(Path);//temp新建实例的名字=当前实例个数+1
				//当前数据库导出成文件夹
				Output.oneKeyOutput(Path);
			}
			else{
				File file=new File("D:\\CRAN\\"+Account.trim()+"\\"+caseName.trim());
				if(file.exists())//过去的实例，有实例文件夹
				{
					//当前数据库导出成文件夹
					String Path = "D:\\CRAN\\"+Account.trim()+"\\"+caseName.trim();
					Output.oneKeyOutput(Path);
				}else
				{
					//默认路径下新建实例文件夹
					String Path = "D:\\CRAN\\"+Account.trim()+"\\"+caseName.trim();
					CreatFile(Path);//temp新建实例的名字=当前实例个数+1
					//当前数据库导出成文件夹
					Output.oneKeyOutput(Path);
				}
			}
			
			
		}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
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
