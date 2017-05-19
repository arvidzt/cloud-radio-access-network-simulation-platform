/*
 * 类名：Bbu_modify
 * 功能：BBU参数修改界面
 */
package ui.Add_bbu_rru_ue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.Share_bag.BBU_sql;
import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;



public class Bbu_modify extends JDialog implements ActionListener {
	String path="image"+File.separator+"ADD_Link"+File.separator+"modify.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7;
	JButton okButton,cancelButton;
	Bbu_table_module bbu_table;
	String bbu_id;
	JComboBox jcb1,jcb2;//下拉列表
	String []jg1={"RRS","CIS","PFS"};//RRU的调度方式，不填写默认是0,0：RRS 1:CIS 2:PFS
	Vector jg2;//存储接入环id
	/**************************************
	 * 功能：构造修改界面
	 ***************************************/
	//owner它的父窗口
	//title窗口名
	//modal指定模态窗口，还是非模态窗口
	public Bbu_modify(Dialog owner,String title,boolean modal,Bbu_table_module bbu_table,int rowNum) {
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
				JLabel lblBbu = new JLabel("BBU"+(String) bbu_table.getValueAt(rowNum, 0));
				lblBbu.setFont(MyTools.f1);
				bbu_id=(String) bbu_table.getValueAt(rowNum, 0);
				panel.add(lblBbu);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false); 
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(7, 1, 0, 0));//加了一行LinkID
				JLabel lblX = new JLabel("   X坐标：");
				lblX.setFont(MyTools.f14);
				panel.add(lblX);
				JLabel lblY = new JLabel("   Y坐标：");
				lblY.setFont(MyTools.f14);
				panel.add(lblY);
				JLabel lblZ = new JLabel("   Z坐标：");
				lblZ.setFont(MyTools.f14);
				panel.add(lblZ);
				JLabel label1 = new JLabel(" 传输功率：");
				label1.setFont(MyTools.f14);
				panel.add(label1);
				JLabel label2 = new JLabel("  资源量：");
				label2.setFont(MyTools.f14);
				panel.add(label2);
				JLabel label3 = new JLabel(" 调度方式：");
				label3.setFont(MyTools.f14);
				panel.add(label3);
				JLabel lblink = new JLabel(" 接入环ID：");
				lblink.setFont(MyTools.f14);
				panel.add(lblink);
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false); 
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(7, 1, 0, 0));//加了一行LinkID
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
					jcb1=new JComboBox<>(jg1);
					jcb1.setBackground(Color.white);
					jcb1.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel6.add(jcb1);
					
				panel7 = new JPanel();
				panel7.setOpaque(false); 
				panel.add(panel7);
				panel7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					jg2=new Vector<>();
					String sql1="select * from LinkCircle where 1=?";//再从UE中调出添加的数据		
					String []paras1={"1"};//可以显示所有已经添加的UE
					BBU_sql circlid_sql=new BBU_sql();
					ResultSet rs=circlid_sql.search(sql1, paras1);
					try {
						while(rs.next())
						{
							jg2.add(rs.getString(1));//LinkCircleId
						}
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}finally
					{
						//关闭
						circlid_sql.close();
					}		
					jcb2=new JComboBox<>(jg2);
					jcb2.setBackground(Color.white);
					jcb2.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel7.add(jcb2);
					//展现
					setBounds(500, 150, 300, 300);
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
		}
		String TransPower=textField_3.getText();//传输功率
		String Res=textField_4.getText();//资源量
		boolean temp=isNumeric(X)&isNumeric(Y)&isNumeric(Z)&isNumeric(TransPower)&isNumeric(Res);
		if(!(temp))
		{
			judge=false;
			//提示
			JOptionPane.showMessageDialog(this,"请填写数字！");
			return;
		}
		if(judge==true)
		{
			String SchedualRruMode="0";
			if(jcb1.getSelectedItem().equals("RRS"))//0：RRS 1:CIS 2:PFS
			{
				SchedualRruMode="0";
			}else if(jcb1.getSelectedItem().equals("CIS"))
			{
				SchedualRruMode="1";
			}else if(jcb1.getSelectedItem().equals("PFS"))
			{
				SchedualRruMode="2";
			}
			String sql="update Bbu set X=?,Y=?,Z=?,TransPower=?,Res=?,SchedualRruMode=?,LinkId=?  where BbuId=?";
			String []paras={textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),SchedualRruMode,(String) jcb2.getSelectedItem(),bbu_id};
			bbu_table=new Bbu_table_module();
			bbu_table.add_delete_change_to_sql(sql, paras);
			
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
	if(index<0)
	{
		for (int i = str.length();--i>=0;)
		  {    
			   if (!Character.isDigit(str.charAt(i)))
			   {  
			    return false;  
			   }
		   }
	}
	else{
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
