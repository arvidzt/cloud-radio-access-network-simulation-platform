/*
 * 类名：BbuPool_Link_modify
 * 功能：BBU池间链路参数修改界面
 */
package ui.Add_link;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;

public class BbuPool_Link_modify extends JDialog implements ActionListener{
	String path="image"+File.separator+"ADD_Link"+File.separator+"modify.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
//	private final JPanel contentPanel = new JPanel();
	JLabel lblid;
	JPanel panel,buttonPane;
	JButton okButton,cancelButton;
	private JTextField textField,textField_1,textField_2,textField_3;	
	BbuPool_Link_Module BbuPool_Link_table;
	String LinkPathId;
	JComboBox jcb1;//下拉列表
	String []jg1={"环型","总线型"};///链路类型，不填写默认是0，0：环型，1：总线型

	/**************************************
	 * 功能：构造修改界面
	 ***************************************/
	public BbuPool_Link_modify(Dialog owner,String title,boolean modal,BbuPool_Link_Module BbuPool_Link_table,int rowNum) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		setBounds(400, 200, 400, 280);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				lblid = new JLabel("光纤链路ID："+(String) BbuPool_Link_table.getValueAt(rowNum, 0));
				lblid.setFont(MyTools.f1);
				LinkPathId=(String) BbuPool_Link_table.getValueAt(rowNum, 0);
				panel.add(lblid);
			}
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setOpaque(false); 
			contentPanel.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new GridLayout(4, 2, 0, 0));
			{
				{
					JPanel panel_3 = new JPanel();
					panel_3.setOpaque(false); 
					panel_1.add(panel_3);
					panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					{
						JLabel lblid_1 = new JLabel("端点池ID：");
						lblid_1.setFont(MyTools.f14);
						panel_3.add(lblid_1);
					}
				}
				{
					JPanel panel_3 = new JPanel();
					panel_3.setOpaque(false); 
					panel_1.add(panel_3);
					panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					{
						textField = new JTextField();
						textField.setText((String) BbuPool_Link_table.getValueAt(rowNum, 1));
						panel_3.add(textField);
						textField.setColumns(10);
					}
				}
				{
					JPanel panel_3 = new JPanel();
					panel_3.setOpaque(false); 
					panel_1.add(panel_3);
					panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					{
						JLabel lblid_2 = new JLabel("端点池ID：");
						lblid_2.setFont(MyTools.f14);
						panel_3.add(lblid_2);
					}
				}
				{
					JPanel panel_3 = new JPanel();
					panel_3.setOpaque(false); 
					panel_1.add(panel_3);
					panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					{
						textField_1 = new JTextField();
						textField_1.setText((String) BbuPool_Link_table.getValueAt(rowNum, 2));
						panel_3.add(textField_1);
						textField_1.setColumns(10);
					}
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				panel_2.setOpaque(false); 
				panel_1.add(panel_2);
				{
					JLabel label = new JLabel("最大传输速度：");
					label.setFont(MyTools.f14);
					panel_2.add(label);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				panel_2.setOpaque(false); 
				panel_1.add(panel_2);
				{
					textField_2 = new JTextField();
					textField_2.setText((String) BbuPool_Link_table.getValueAt(rowNum, 2));
					panel_2.add(textField_2);
					textField_2.setColumns(10);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_1.add(panel_2);
				panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					JLabel label = new JLabel(" 链路类型：");
					label.setFont(MyTools.f14);
					panel_2.add(label);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_1.add(panel_2);
				panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					jcb1=new JComboBox<>(jg1);
					jcb1.setBackground(Color.white);
					jcb1.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel_2.add(jcb1);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.white);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确认");
				okButton.setFont(MyTools.f14);
				okButton.setActionCommand("确认");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setFont(MyTools.f14);
				cancelButton.setActionCommand("取消");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
	/**************************************
	 * 功能：界面上各个按钮的监听响应功能
	 ***************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==okButton)
		{
			//判断端点池ID不能不填写，否则，不能保存
			boolean judge=true;
			String POOL1=textField.getText();//端点池ID
			String POOL2=textField_1.getText();//端点池ID
			if(POOL1.length()==0||POOL2.length()==0)
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"端点池ID不能为空");
			}
			String MaxTransRate=textField_2.getText();//最大传输速度
			boolean temp=isNumeric(POOL1)&isNumeric(POOL2)&isNumeric(MaxTransRate);
			if(!(temp))
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"请填写数字！");
				return;
			}
			if(judge==true)
			{
				String LinkType="0";
				if(jcb1.getSelectedItem().equals("环型"))//0：环型，1：总线型
				{
					LinkType="0";
				}else if(jcb1.getSelectedItem().equals("总线型"))
				{
					LinkType="1";
				}
				String sql="update LinkBbuPool2BbuPool set LinkEnd1=?,LinkEnd2=?,LinkType=?,MaxTransRate=? where LinkPathId=?";
				String []paras={textField.getText(),textField_1.getText(),LinkType,textField_2.getText(),LinkPathId};
				BbuPool_Link_table=new BbuPool_Link_Module();
				BbuPool_Link_table.add_delete_change_to_sql(sql, paras);
				this.dispose();//关闭对话框
			}
			
			
		}
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
}
