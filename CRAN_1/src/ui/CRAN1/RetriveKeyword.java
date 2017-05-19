//密码找回
package ui.CRAN1;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import ui.Share_bag.BBU_sql;
import ui.Share_bag.MyTools;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JTextField;

public class RetriveKeyword extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	JButton okButton,cancelButton,btnNewButton;
	String Question,Answer,PassWord;
	private JTextField textField_2;
	String Account;
	int count=0;
	JPanel panel_11,panel_12;
	JLabel lable_password,lblPassword;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			RetriveKeyword dialog = new RetriveKeyword();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
public RetriveKeyword(Dialog owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public RetriveKeyword() {
		setBounds(500, 200, 344, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("密码找回");
			lblNewLabel.setFont(MyTools.f1);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(6, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel_3 = new JLabel("   账户名：");
					panel_1.add(lblNewLabel_3);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel_1 = new JLabel("    密保问题：");
					panel_1.add(lblNewLabel_1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblNewLabel_2 = new JLabel("    密保答案：");
					panel_1.add(lblNewLabel_2);
				}
			}
			{
				panel_11 = new JPanel();
				panel.add(panel_11);
				{
					lable_password = new JLabel("       密码是：");
					lable_password.setVisible(false);
					panel_11.add(lable_password);
				}
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(6, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					textField_2 = new JTextField();
					panel_1.add(textField_2);
					textField_2.setColumns(16);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				btnNewButton = new JButton("找回密码");
				btnNewButton.addActionListener(this);
				panel_1.add(btnNewButton);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					textField = new JTextField();
					panel_1.add(textField);
					textField.setColumns(16);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					textField_1 = new JTextField();
					panel_1.add(textField_1);
					textField_1.setColumns(16);
				}
			}
			{
				panel_12 = new JPanel();
				panel.add(panel_12);
				{

				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.addActionListener(this);
				okButton.setActionCommand("确定");
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
		if(arg0.getSource()==btnNewButton)//输入账号，找回密码
		{
			int flage=1;
			Account=textField_2.getText().trim();//账户名
			if(Account.length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"帐户名不能为空");
				return;
			}else
			{
				String sql="select passWord,security1,security2 from PUser where userName=?";//再从Bbu中调出bbu的数据		
				String []paras={Account};
				BBU_sql sql_1=new BBU_sql();
				ResultSet sql_qusetion_ResultSet=sql_1.search(sql, paras);
				try {
					sql_qusetion_ResultSet.next();
					PassWord=sql_qusetion_ResultSet.getString(1);
					Question=sql_qusetion_ResultSet.getString(2);
					Answer=sql_qusetion_ResultSet.getString(3);
				} catch (Exception e) {
					// TODO: handle exception
					flage=0;
					//提示
					JOptionPane.showMessageDialog(this,"帐户名不存在");
					return;
				}
				if(flage==1)//
				{
					textField.setText(Question);
				}
			}
		}
		else if(arg0.getSource()==okButton)//输入密保问题，找回密码
		{
			panel_12.removeAll();
			panel_12.repaint();
			if(textField_1.getText().equals(Answer))//密保输入正确
			{

				lable_password.setVisible(true);
				
				lblPassword = new JLabel(PassWord);
				panel_12.add(lblPassword);
				panel_12.repaint();
			}else//密保输入错误
			{
				if(count==4)//输入3次密保问题错误，提示密码找回失败
				{
					//提示
					JOptionPane.showMessageDialog(this,"密码找回失败，请联系管理员");
					this.dispose();
				}else
				{
					count=count+1;
					//提示
					JOptionPane.showMessageDialog(this,"密保答案输入错误，请重新输入");
					return;
				}
			}
			
		}else if(arg0.getSource()==cancelButton)//取消
		{
			this.dispose();
		}
	}

}
