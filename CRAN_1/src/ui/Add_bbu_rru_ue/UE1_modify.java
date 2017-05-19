/*
 * 类名：UE1_modify
 * 功能：UE群参数修改界面
 */
package ui.Add_bbu_rru_ue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.JTextField;

import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;

public class UE1_modify extends JDialog implements ActionListener{
	String path="image"+File.separator+"ADD_Link"+File.separator+"modify.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
//	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1,textField_2,textField_3,textField_4,textField_5;
	JLabel lblUe,lblUe_1,lblxm,lblym,lblzm,label1,label2,label3,label4;
	JPanel panel1;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"室外","室内"};//室内外信息，不填写默认是0,1：室内，0：室外
	String []jg2={"0","1","2"};//天线类型，不填写默认是0
	JButton okButton,cancelButton;
	UE_table_module1 ue1_table;
	UE_table_module2 ue2_table;
	String UeGroup_id;
	/**************************************
	 * 功能：构造修改界面
	 ***************************************/
	public UE1_modify(Dialog owner,String title,boolean modal,UE_table_module1 ue1_table,int rowNum) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		setBounds(450, 150, 362, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel1 = new JPanel();
			panel1.setOpaque(false);
			contentPanel.add(panel1, BorderLayout.NORTH);			
				lblUe = new JLabel("UE群"+(String) ue1_table.getValueAt(rowNum, 0));
				lblUe.setFont(MyTools.f1);
				UeGroup_id=(String) ue1_table.getValueAt(rowNum, 0);
				panel1.add(lblUe);			
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false); 
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(7, 1, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					lblUe_1 = new JLabel("UE个数：        ");
					lblUe_1.setFont(MyTools.f14);
					panel_1.add(lblUe_1);
				}
				{
					textField = new JTextField();
					textField.setText((String) ue1_table.getValueAt(rowNum, 1));
					panel_1.add(textField);
					textField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					lblxm = new JLabel("中心X坐标(m):");
					lblxm.setFont(MyTools.f14);
					panel_1.add(lblxm);
				}
				{
					textField_1 = new JTextField();
					textField_1.setText((String) ue1_table.getValueAt(rowNum, 2));
					panel_1.add(textField_1);
					textField_1.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					lblym = new JLabel("中心Y坐标(m):");
					lblym.setFont(MyTools.f14);
					panel_1.add(lblym);
				}
				{
					textField_2 = new JTextField();
					textField_2.setText((String) ue1_table.getValueAt(rowNum, 3));
					panel_1.add(textField_2);
					textField_2.setColumns(10);
				}
			}
//			{
//				JPanel panel_1 = new JPanel();
//				panel.add(panel_1);
//				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//				{
//					lblzm = new JLabel("中心Z坐标(m):");
//					panel_1.add(lblzm);
//				}
//				{
//					textField_3 = new JTextField();
//					textField_3.setText((String) ue1_table.getValueAt(rowNum, 4));
//					panel_1.add(textField_3);
//					textField_3.setColumns(10);
//				}
//			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					label1 = new JLabel("分布方式：     ");
					label1.setFont(MyTools.f14);
					panel_1.add(label1);
				}
				{
					textField_4 = new JTextField();
					textField_4.setText((String) ue1_table.getValueAt(rowNum, 5));
					panel_1.add(textField_4);
					textField_4.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					label2 = new JLabel("区域半径：     ");
					label2.setFont(MyTools.f14);
					panel_1.add(label2);
				}
				{
					textField_5 = new JTextField();
					textField_5.setText((String) ue1_table.getValueAt(rowNum, 5));
					panel_1.add(textField_5);
					textField_5.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false); 
				panel.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					label3 = new JLabel("室内外信息： ");
					label3.setFont(MyTools.f14);
					panel_1.add(label3);
				}
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
				panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					label4 = new JLabel("天线类型：     ");
					label4.setFont(MyTools.f14);
					panel_1.add(label4);
				}
				{
					jcb2=new JComboBox<>(jg2);
					jcb2.setBackground(Color.white);
					jcb2.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel_1.add(jcb2);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
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
		/***************确定按钮******************/
		if(arg0.getSource()==okButton)
		{
			//判断X,Y,Z坐标不能不填写，否则，不能保存
			boolean judge=true;
			String X=textField.getText();//X
			String Y=textField_1.getText();//Y
			String Z=textField_2.getText();//Z
			if(X.length()==0||Y.length()==0||Z.length()==0)
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"中心X,Y,Z坐标不能为空");
			}
			String r=textField_5.getText();//R
			boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z)&isNumeric(r);
			if(!(temp))
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"请填写数字！");
				return;
			}
			if(judge==true)
			{
				String UeType="0";
				if(jcb1.getSelectedItem().equals("室外"))//1：室内，0：室外
				{
					UeType="0";
				}else if(jcb1.getSelectedItem().equals("室内"))
				{
					UeType="1";
				}
				//修改表格1
				String sql="update UeGroup set Radius=?,X=?,Y=?,UeDistribute=?,UeType=?,UeAntennaId=? where UeGroupId=?";
				String []paras={textField_5.getText(),textField_1.getText(),textField_2.getText(),"5",
						UeType,(String) jcb2.getSelectedItem(),UeGroup_id};
				ue1_table=new UE_table_module1();
				ue1_table.add_delete_change_to_sql(sql, paras);				
				//修改表格2
				String sql2="exec Alter_UeGroup ?,?,?,?,?,?,?";//当修改UE1的表格信息时，同时会修改UE2的表格信息。
				String []paras2={UeGroup_id,textField_5.getText(), textField_1.getText(),textField_2.getText(),
						"5",UeType,(String) jcb2.getSelectedItem()};
				ue2_table=new UE_table_module2();
				ue2_table.add_delete_change_to_sql(sql2, paras2);			
				this.dispose();//关闭对话框
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

}
