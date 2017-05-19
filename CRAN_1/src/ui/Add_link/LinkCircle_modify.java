/*
 * 类名：LinkCircle_modify
 * 功能：环链路参数修改界面
 */
package ui.Add_link;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;

public class LinkCircle_modify extends JDialog implements ActionListener{
	String path="image"+File.separator+"ADD_Link"+File.separator+"modify.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
//	private final JPanel contentPanel = new JPanel();
	private JTextField textField,textField_7,textField_8;
	private JTextField textField_1,textField_2,textField_3,textField_4,textField_5,textField_6;
	JLabel lblUe,lblUe_1,lblxm,lblym,lblxm2,lblym2,lblzm,label1,label2,label3,label4;
	JPanel panel1;
	JComboBox jcb1;//下拉列表
	String []jg1={"环型","总线型"};///链路类型，不填写默认是0，0：环型，1：总线型
	JButton okButton,cancelButton;
	LinkCircle_module LinkCircle_table;
	String LinkCircleId;
	/**************************************
	 * 功能：构造修改界面
	 ***************************************/
	public LinkCircle_modify(Dialog owner,String title,boolean modal,LinkCircle_module LinkCircle_table,int rowNum) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		setBounds(450, 200, 400, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel1 = new JPanel();
			panel1.setOpaque(false); 
			contentPanel.add(panel1, BorderLayout.NORTH);			
				lblUe = new JLabel("接入环ID"+(String) LinkCircle_table.getValueAt(rowNum, 0));
				lblUe.setFont(MyTools.f1);
				LinkCircleId=(String) LinkCircle_table.getValueAt(rowNum, 0);
				panel1.add(lblUe);			
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 2, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					lblUe_1 = new JLabel("接入环类型：");
					lblUe_1.setFont(MyTools.f14);
					panel_1.add(lblUe_1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					jcb1=new JComboBox<>(jg1);
					jcb1.setBackground(Color.white);
					jcb1.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel_1.add(jcb1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					lblxm = new JLabel("X1坐标(m)：");
					lblxm.setFont(MyTools.f14);
					panel_1.add(lblxm);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_1 = new JTextField();
					textField_1.setText((String) LinkCircle_table.getValueAt(rowNum, 2));
					panel_1.add(textField_1);
					textField_1.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					lblym = new JLabel("Y1坐标(m)：");
					lblym.setFont(MyTools.f14);
					panel_1.add(lblym);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_2 = new JTextField();
					textField_2.setText((String) LinkCircle_table.getValueAt(rowNum, 3));
					panel_1.add(textField_2);
					textField_2.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					lblxm2 = new JLabel("X2坐标(m)：");
					lblxm2.setFont(MyTools.f14);
					panel_1.add(lblxm2);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_7 = new JTextField();
					textField_7.setText((String) LinkCircle_table.getValueAt(rowNum, 4));
					panel_1.add(textField_7);
					textField_7.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					lblym2 = new JLabel("Y2坐标(m)：");
					lblym2.setFont(MyTools.f14);
					panel_1.add(lblym2);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_8 = new JTextField();
					textField_8.setText((String) LinkCircle_table.getValueAt(rowNum, 5));
					panel_1.add(textField_8);
					textField_8.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					label1 = new JLabel("长半径：");
					label1.setFont(MyTools.f14);
					panel_1.add(label1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_4 = new JTextField();
					textField_4.setText((String) LinkCircle_table.getValueAt(rowNum, 6));
					panel_1.add(textField_4);
					textField_4.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					label2 = new JLabel("短半径：");
					label2.setFont(MyTools.f14);
					panel_1.add(label2);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_5 = new JTextField();
					textField_5.setText((String) LinkCircle_table.getValueAt(rowNum, 7));
					panel_1.add(textField_5);
					textField_5.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					label4 = new JLabel("备选接入点个数：");
					label4.setFont(MyTools.f14);
					panel_1.add(label4);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					textField_6 = new JTextField();
					textField_6.setText((String) LinkCircle_table.getValueAt(rowNum, 8));
					panel_1.add(textField_6);
					textField_6.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.white);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.setFont(MyTools.f14);
				okButton.setActionCommand("确定");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
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
			//判断X,Y,Z坐标不能不填写，否则，不能保存
			boolean judge=true;
			String X1=textField_1.getText();//X1
			String Y1=textField_2.getText();//Y1
			String X2=textField_7.getText();//X2
			String Y2=textField_8.getText();//Y2
			if(X1.length()==0||Y1.length()==0)
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"中心X,Y坐标不能为空");
			}
			String LongRadius=textField_4.getText();//LongRadius
			String ShortRadius=textField_5.getText();//ShortRadius
			String AccesspointNum=textField_6.getText();//AccesspointNum
			boolean temp=isNumeric(X1)&isNumeric(Y1)&isNumeric(X2)&isNumeric(Y2)&isNumeric(LongRadius)&isNumeric(ShortRadius)&isNumeric(AccesspointNum);
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
				//修改表格1
				String sql="update Link set LinkType=?,X1=?,Y1=?,X2=?,Y2=?,LongRadius=?,ShortRadius=?,AccesspointNum=? where LinkId=?";
				String []paras={LinkType,textField_1.getText(),textField_2.getText(),textField_7.getText(),textField_8.getText(),textField_4.getText(),textField_5.getText(),
						textField_6.getText(),LinkCircleId};
				LinkCircle_table=new LinkCircle_module();
				LinkCircle_table.add_delete_change_to_sql(sql, paras);						
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
