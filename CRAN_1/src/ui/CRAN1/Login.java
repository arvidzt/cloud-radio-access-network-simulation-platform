package ui.CRAN1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.Add_bbu_rru_ue.Bbu_modify;
import ui.Add_bbu_rru_ue.Input2;
import ui.Share_bag.BBU_sql;
import ui.Share_bag.MyTools;

public class Login extends JDialog implements ActionListener{
//	public static int LoginOrNot=0;//LoginOrNot=0没有登录，显示登陆界面；LoginOrNot=1登录，显示用户管理界面
	static String name = BBU_sql.name;
	static String passWord = BBU_sql.pswd;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField;
	private JButton button_register,button_RetriveKeyword,okButton,cancelButton;
	int login_result;//是否成功，返回该账号实例个数
	String login_result_casenum;
	String Account;//帐户名
	String PassWord;//密码
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
//public Login(Frame owner,String title,boolean modal) {
//	super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
	public Login() {
		setBounds(500, 200, 416, 186);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("CRAN");
				lblNewLabel.setFont(MyTools.f1);
				panel.add(lblNewLabel);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.EAST);
				panel_1.setLayout(new GridLayout(2, 1, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						button_register = new JButton("注册账号");
						button_register.addActionListener(this);
						panel_2.add(button_register);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						button_RetriveKeyword = new JButton("找回密码");
						button_RetriveKeyword.addActionListener(this);
						panel_2.add(button_RetriveKeyword);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				panel_1.setLayout(new GridLayout(2, 1, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel label = new JLabel("   账户：   ");
						panel_2.add(label, BorderLayout.CENTER);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JLabel label = new JLabel("   密码：   ");
						panel_2.add(label, BorderLayout.CENTER);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new GridLayout(2, 1, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						textField = new JTextField();
						panel_2.add(textField);
						textField.setColumns(16);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2);
					panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						textField_1 = new JTextField();
						panel_2.add(textField_1);
						textField_1.setColumns(16);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("登录");
				okButton.setActionCommand("登录");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("取消");
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==button_register)//注册
		{
			JDialog a=new Register(this, "Register", true);
			a.setVisible(true);
		}
		else if(arg0.getSource()==button_RetriveKeyword)//找回密码
		{
			JDialog a=new RetriveKeyword(this,"RetriveKeyword",true);
			a.setVisible(true);
		}
		else if(arg0.getSource()==okButton)//确定登录
		{
			Account=textField.getText().trim();
			PassWord=textField_1.getText().trim();
			//先判断账户和密码textfield不能为空
			if(Account.length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"帐户名不能为空");
				return;
			}else if(PassWord.length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"密码不能为空");
				return;
			}else
			{
				//需要遍历数据库
				String sql="exec FindUser ?,?";//@userName,@PassWord
				String []paras={Account,PassWord};
				BBU_sql sql_login=new BBU_sql();
				ResultSet sql_login_ResultSet=sql_login.search(sql, paras);
				try {
					sql_login_ResultSet.next();
					login_result=sql_login_ResultSet.getInt(1);			
					login_result_casenum=sql_login_ResultSet.getString(2);	
					System.out.println(login_result_casenum);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(login_result==0)//账户名没有找到
				{
					//提示
					JOptionPane.showMessageDialog(this,"该帐户名不存在");
					return;
				}else if(login_result==1)//账户名找到，且密码正确
				{
					//先清空数据库所有数据
					ClearDatebase_forAccount();
					//读入当前账号实例表格PCase
					String path="D:\\CRAN\\"+Account;
					Input2.CaseInput(path);//仅仅将case.xls导入数据库，新注册的用户不会导入case.xls，因为新注册的用户还没有case.xls文件
					//CurrentUser表格修改
					String sql_tuopu="update CurrentUser set userName=?,caseName=?  where ID=?";
					String []paras_tuopu={textField.getText(),"-1","1"};
					BBU_sql sqlh=new BBU_sql();
					sqlh.add_delete_change(sql_tuopu, paras_tuopu);
					//提示
					JOptionPane.showMessageDialog(this,"登录成功");
//					LoginOrNot=1;
					//关闭登录窗口
					this.dispose();
					
					JFrame a=new tuoputu(this,"tuoputu",true);
					a.setVisible(true);

				}else if(login_result==2)//帐户名找到，密码不正确
				{
					//提示
					JOptionPane.showMessageDialog(this,"密码错误");
					return;
				}
			}
		}
		else if(arg0.getSource()==cancelButton)//取消---退出程序
		{
			System.exit(0);
		}
	}

	private void ClearDatebase_forAccount() {
		// TODO 自动生成的方法存根
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(true);
             Statement stmt = conn.createStatement();
             String sql_drop = "exec Drop_All_forAccount";
			stmt.executeUpdate(sql_drop);
		
		}catch(SQLException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			}
	}

}
