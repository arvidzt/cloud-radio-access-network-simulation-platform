/*
 * 类名：Rru_modify
 * 功能：RRU参数修改界面
 */
package ui.Add_bbu_rru_ue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;

public class Rru_modify extends JDialog implements ActionListener{
	String path="image"+File.separator+"ADD_Link"+File.separator+"modify.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5;
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	JButton okButton,cancelButton;
	Rru_Table_module rru_table;
	String rru_id;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"关闭","打开"};//RRU状态属性，不填写默认是0,0：关闭，1：打开
	String []jg2={"0","1","2"};//天线类型，不填写默认是0
	/**************************************
	 * 功能：构造修改界面
	 ***************************************/
	//owner它的父窗口
	//title窗口名
	//modal指定模态窗口，还是非模态窗口
	public Rru_modify(Dialog owner,String title,boolean modal,Rru_Table_module bbu_table,int rowNum) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		getContentPane().setLayout(new BorderLayout());
		{
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new BorderLayout(0, 0));
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("确定");
				okButton.setFont(MyTools.f14);
				okButton.addActionListener(this);
				okButton.setActionCommand("确定");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("取消");
				cancelButton.setFont(MyTools.f14);
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("取消");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false); 
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblBbu = new JLabel("RRU"+(String) bbu_table.getValueAt(rowNum, 0));
				lblBbu.setFont(MyTools.f1);
				rru_id=(String) bbu_table.getValueAt(rowNum, 0);
				panel.add(lblBbu);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false); 
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(8, 1, 0, 0));
				JLabel lblX = new JLabel("   X坐标：");
				lblX.setFont(MyTools.f14);
				panel.add(lblX);
				JLabel lblY = new JLabel("   Y坐标：");
				lblY.setFont(MyTools.f14);
				panel.add(lblY);
				JLabel lblZ = new JLabel("   Z坐标：");
				lblZ.setFont(MyTools.f14);
				panel.add(lblZ);
				JLabel label1 = new JLabel(" 发射功率：");
				label1.setFont(MyTools.f14);
				panel.add(label1);
				JLabel label2 = new JLabel(" 最大用户量：");
				label2.setFont(MyTools.f14);
				panel.add(label2);
				JLabel label3 = new JLabel(" 载频：");
				label3.setFont(MyTools.f14);
				panel.add(label3);
				JLabel label4 = new JLabel(" RRU状态：");
				label4.setFont(MyTools.f14);
				panel.add(label4);
				JLabel label5 = new JLabel(" 天线类型：");
				label5.setFont(MyTools.f14);
				panel.add(label5);
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 1, 0, 0));
				panel1 = new JPanel();
				panel1.setOpaque(false); 
				panel.add(panel1);
				panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField = new JTextField();
					textField.setText((String) bbu_table.getValueAt(rowNum, 1));
					panel1.add(textField);
					textField.setColumns(10);
				panel2 = new JPanel();
				panel2.setOpaque(false); 
				panel.add(panel2);
				panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField_1 = new JTextField();
					textField_1.setText((String) bbu_table.getValueAt(rowNum, 2));
					panel2.add(textField_1);
					textField_1.setColumns(10);
				panel3 = new JPanel();
				panel3.setOpaque(false); 
				panel.add(panel3);
				panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField_2 = new JTextField();
					textField_2.setText((String) bbu_table.getValueAt(rowNum, 3));
					panel3.add(textField_2);
					textField_2.setColumns(10);
				panel4= new JPanel();
				panel4.setOpaque(false); 
				panel.add(panel4);
				panel4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField_3 = new JTextField();
					textField_3.setText((String) bbu_table.getValueAt(rowNum, 4));
					panel4.add(textField_3);
					textField_3.setColumns(10);
				panel5 = new JPanel();
				panel5.setOpaque(false); 
				panel.add(panel5);
				panel5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField_4 = new JTextField();
					textField_4.setText((String) bbu_table.getValueAt(rowNum, 5));
					panel5.add(textField_4);
					textField_4.setColumns(10);
				panel6 = new JPanel();
				panel6.setOpaque(false); 
				panel.add(panel6);
					panel6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					textField_5 = new JTextField();
					textField_5.setText((String) bbu_table.getValueAt(rowNum, 6));
					panel6.add(textField_5);
					textField_5.setColumns(10);
				panel7 = new JPanel();
				panel7.setOpaque(false); 
				panel.add(panel7);
					panel7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));				
					jcb1=new JComboBox<>(jg1);
					jcb1.setBackground(Color.white);
					jcb1.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel7.add(jcb1);
				panel8 = new JPanel();
				panel8.setOpaque(false);
				panel.add(panel8);
					panel8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					jcb2=new JComboBox<>(jg2);
					jcb2.setBackground(Color.white);
					jcb2.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel8.add(jcb2);
					//展现
					setBounds(450, 150, 300, 300);
					this.setVisible(true);
	}
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
				JOptionPane.showMessageDialog(this,"X,Y,Z坐标不能为空");
				return;
			}
			String RruTransPower=textField_3.getText();//RRU 发射功率
			String user_num=textField_4.getText();//RRU覆盖范围内的用户数量
			String CarrierFrequent=textField_5.getText();//载频
			boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z)&isNumeric(RruTransPower)&isNumeric(user_num)&isNumeric(CarrierFrequent);
			if(!(temp))
			{
				judge=false;
				//提示
				JOptionPane.showMessageDialog(this,"请填写数字！");
				return;
			}
			if(judge==true)
			{
				String IsActivity="0";
				if(jcb1.getSelectedItem().equals("关闭"))//0：关闭，1：打开
				{
					IsActivity="0";
				}else if(jcb1.getSelectedItem().equals("打开"))
				{
					IsActivity="1";
				}
				String sql="update Rru set X=?,Y=?,Z=?,RruTransPower=?,UeNum=?,CarrierFrequent=?,IsActivity=?,RruAntennaId=? where RruId=?";
				String []paras={textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),
						textField_4.getText(),textField_5.getText(),IsActivity,(String) jcb2.getSelectedItem(),rru_id};
				rru_table=new Rru_Table_module();
				rru_table.add_delete_change_to_sql(sql, paras);
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
