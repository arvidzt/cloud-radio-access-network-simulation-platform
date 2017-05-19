//注册
package ui.CRAN1;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;

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
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;

import javax.swing.JTextField;

public class Register extends JDialog implements ActionListener{
	private static String Account;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	JButton okButton,cancelButton;
	int register_result;//判断即将注册的账户名是否已存在
	int PUser_id;//PUser中自动生成新注册的账户id
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Register dialog = new Register();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
public Register(Dialog owner,String title,boolean modal) {
	super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public Register() {
		setBounds(500, 200, 323, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("用户注册");
				lblNewLabel.setFont(MyTools.f1);
				panel.add(lblNewLabel);
			}
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
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_1 = new JLabel("       用户名：");
					panel_1.add(lblNewLabel_1, BorderLayout.EAST);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_2 = new JLabel("         密码：");
					panel_1.add(lblNewLabel_2, BorderLayout.EAST);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel_3 = new JLabel("     确认密码：");
					panel_1.add(lblNewLabel_3, BorderLayout.CENTER);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
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
					textField = new JTextField();
					panel_1.add(textField);
					textField.setColumns(16);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					textField_1 = new JTextField();
					panel_1.add(textField_1);
					textField_1.setColumns(16);
				}
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
				{
					JLabel lblNewLabel_5 = new JLabel("密码：字母/数字/下划线组成");
					panel_1.add(lblNewLabel_5);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JLabel lblNewLabel_4 = new JLabel("长度不超过6位");
					panel_1.add(lblNewLabel_4);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确认");
				okButton.addActionListener(this);
				okButton.setActionCommand("确认");
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
		if(arg0.getSource()==okButton)//注册
		{
			//先判断账户和密码textfield不能为空
			if(textField.getText().length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"帐户名不能为空");
				return;
			}else if(textField_1.getText().length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"密码不能为空");
				return;
			}else if(textField_2.getText().length()==0)
			{
				//提示
				JOptionPane.showMessageDialog(this,"确认密码不能为空");
				return;
			}else if(!textField_1.getText().equals(textField_2.getText()))
			{
				//提示
				JOptionPane.showMessageDialog(this,"两次密码输入不一致，请重新输入");
				return;
			}else
			{
				//需要遍历数据库
				String sql="exec FindUser ?,?";//@userName,@PassWord
				String []paras={textField.getText(),textField_1.getText()};
				BBU_sql sql_register=new BBU_sql();
				ResultSet sql_register_ResultSet=sql_register.search(sql, paras);
				try {
					sql_register_ResultSet.next();
					register_result=sql_register_ResultSet.getInt(1);			
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(register_result==0)//账户名没有找到，可以注册
				{
					Account=textField.getText().trim();
					//PUser表格自动ID号分配
					String sql_id="exec RowNum ?";
					String []paras_id={"PUser"};
					sql_register=new BBU_sql();
					sql_register_ResultSet=sql_register.search(sql_id, paras_id);
					try {
						sql_register_ResultSet.next();
						PUser_id=sql_register_ResultSet.getInt(1);			
					} catch (Exception e) {
						// TODO: handle exception
					}
					String string_PUser_id=Integer.toString(PUser_id);////更新了数据库中bbu总数
					//插入新的账户数据
					String sql_add="insert into PUser values(?,?,?,?,?,?)";
					String []paras_add={string_PUser_id,Account,"0",textField_1.getText(),"无","无"};
					BBU_sql sqlh=new BBU_sql();
					sqlh.add_delete_change(sql_add, paras_add);
					//指定路径下新建该账户文件夹
					CreatFile();
					//提示
					JOptionPane.showMessageDialog(this, "注册成功");//弹出对话框
					//关闭注册窗口
					this.dispose();
				}else//帐户名已存在
				{
					//提示
					JOptionPane.showMessageDialog(this,"该帐户名已存在，请重新起名");
					return;
				}
			}
		}
		else if(arg0.getSource()==cancelButton)//取消
		{
			this.dispose();
		}
		
	}
public void CreatFile()//创建以帐户名命名的文件夹
{
	String directory = "D:\\CRAN\\"+Account;
	File f = new File(directory);
	if(f.exists()) {
		  // 文件已经存在，输出文件的相关信息
	}
	else
	{            
		f.mkdirs();//创建文件所在的目录   
	}
}
}
