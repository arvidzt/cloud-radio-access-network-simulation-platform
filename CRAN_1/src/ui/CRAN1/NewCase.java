package ui.CRAN1;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import ui.Add_bbu_rru_ue.Bbu_table_module;
import ui.Add_bbu_rru_ue.Rru_Table_module;
import ui.Share_bag.BBU_sql;

public class NewCase extends JDialog implements ActionListener{
	static String name = BBU_sql.name;
	static String passWord = BBU_sql.pswd;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_name;
	JButton okButton,cancelButton;
	JTextArea textArea_remark;
	CaseTableModule case_table;
	/*
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			NewCase dialog = new NewCase();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public NewCase(Dialog owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public NewCase() {
		setBounds(100, 100, 423, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(4, 1, 0, 0));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, BorderLayout.WEST);
					{
						JLabel lblNewLabel = new JLabel("实例名称：");
						panel_2.add(lblNewLabel);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_1.add(panel_2, BorderLayout.CENTER);
					panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					{
						textField_name = new JTextField();
						panel_2.add(textField_name);
						textField_name.setColumns(16);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.WEST);
				{
					JLabel label = new JLabel("实例备注：");
					panel_1.add(label);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textArea_remark = new JTextArea(4,25);
					textArea_remark.setLineWrap(true);//自动换行
					textArea_remark.setWrapStyleWord(true);//断行不断字i
					panel_1.add(textArea_remark);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.setActionCommand("确定");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setActionCommand("取消");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==okButton)//新建
		{
			boolean b=true;
			//清空当前数据库
			ClearDatebase();
			//PCase表格新建数据
			String name=textField_name.getText();
			String remark=textArea_remark.getText();
			String []paras=new String[2];
			paras[0]=name;//实例名称
			paras[1]=remark;//实例备注
			String sql="insert into PCase values(?,?)";//还是要一行一行的添加exec Add_Bbu ?,?,?,?,?,?,?,?,?,?,?    insert into Bbu values(?,?,?,?,?,?,?,?,?,?,?)
			case_table=new CaseTableModule();
			b=case_table.add_delete_change_to_sql(sql, paras);
			if(b==true)
			{
				JOptionPane.showMessageDialog(this, "新建实例成功");//弹出对话框
			}else
			{
				JOptionPane.showMessageDialog(this, "新建实例失败");//弹出对话框
			}
			//更新CurrentUser表格
			String sql1="update CurrentUser set caseName=? where ID=?";	
			String []paras1={name,"1"};
			BBU_sql sqlh=new BBU_sql();
			sqlh.add_delete_change(sql1, paras1);
			this.dispose();//关闭对话框
		}
		else if(arg0.getSource()==cancelButton)//取消
		{
			this.dispose();//关闭对话框
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
}
