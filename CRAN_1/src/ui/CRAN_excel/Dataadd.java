package ui.CRAN_excel;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
public class Dataadd extends JDialog implements ActionListener{
	//定义我需要的控件
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2,jl3,jl4,jl5;
	JButton jb1,jb2;
	JTextField  jtf1, jtf2, jtf3, jtf4, jtf5;
	//owner它的父窗口
	//title窗口名
	//modal指定模态窗口，还是非模态窗口
	public Dataadd(Dialog owner,String title,boolean modal)
	
	{
		super(owner,title,modal);//调用父类JDialog构造方法，达到模式对话框效果
		jl1=new JLabel("ID");
		jl2=new JLabel("3D message");
		jl3=new JLabel("door");
		jl4=new JLabel("wireless");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();

		
		jb1=new JButton("添加");
		jb1.addActionListener(this);//点添加没反应，注册监听
		jb2=new JButton("取消");
		jb2.addActionListener(this);//点取消没反应，注册监听
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		//设置布局
		jp1.setLayout(new GridLayout(5,1));
		jp2.setLayout(new GridLayout(5,1));
		//添加组件
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);

		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);

		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);

		//展现
		this.setSize(400, 300);
		
		this.setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==jb1)
		{
//			//移植到module中成为一个方法，module实现增删改查。
//			//连接数据库
//			//对用户点击添加按钮后的响应动作
			boolean s=true;
			//希望添加
			Tablemodule tm=new Tablemodule();
			String sql="insert into UE values(?,?,?,?)";
			String []paras={jtf1.getText(), jtf2.getText(), jtf3.getText(), jtf4.getText()};
			for(int i=0;i<paras.length;i++)//添加时每个属性都不允许为空
			{ 
				boolean t=paras[i].equals(""); //有空，b=true;全填b=false
				if(t)
				 {
					 System.out.println(paras[i]);
					 System.out.println(paras[i].equals(""));
					 s=false;
					 JOptionPane.showMessageDialog(this, "添加失败");//弹出对话框
					 break;
				 }
			}
				if(s==true)
				{
				tm.change(sql, paras);//因为返回时布尔值
				this.dispose();//关闭对话框
			}

	}
		if(arg0.getSource()==jb2)
		{
			this.dispose();//关闭对话框
		}
	}}