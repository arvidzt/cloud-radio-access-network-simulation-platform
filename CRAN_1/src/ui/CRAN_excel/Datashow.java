/**
 * 点击firstpage上的UE，可以弹出excel数据框，显示UE数据库信息，并进行查询
 */
package ui.CRAN_excel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.awt.event.*;//事件响应

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;


public class Datashow extends JDialog implements ActionListener,TreeSelectionListener{
	//定义一些控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField  jtf;
	Tablemodule tm;

	public Datashow(Frame owner,String title,boolean modal)
	{
		super(owner,title,modal);
		jp1=new JPanel();
		jl1=new JLabel("UE ID");
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);//注册监听
		//把各个控件加入到jp1，自动流布局
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		//把各个控件加入jp2,自动流布局
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		
		//创建一个数据模型对象，表模型对象
		Tablemodule tm=new Tablemodule();
		String sql="select * from UE where 1=?";
		String []paras={"1"};
		tm.search(sql,paras);
		jt=new JTable(tm);
		//初始化jsp
		jsp=new JScrollPane(jt);
		//把jsp加入到JFrame中
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(500, 400);
		this.setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//查询
		if(arg0.getSource()==jb1)
		{
			//因为把对表的数据封装到一个类中，我们就可以比较简单的完成查询
			String ID=this.jtf.getText().trim();
			System.out.print(ID);
			if("".equals(ID))//解决了如何处理textfild没有输入数据，查询显示全部信息的问题
			{	
				String sql="select * from UE where 1=?";
				String []paras={"1"};
				//System.out.print("null");
				//构建新的数据模型类，并更新
				tm=new Tablemodule();
				tm.search(sql, paras);
			}
			else
			{
				//写一个sql语句
				String sql="select * from UE where ID=?";
				String []paras={ID};
				//System.out.print(paras.length);
				//构建新的数据模型类，并更新
				tm=new Tablemodule();
				tm.search(sql, paras);

			}
			//更新JTable
			jt.setModel(tm);
		}
		//当用户点击添加时
		else if(arg0.getSource()==jb2)
		{
			Dataadd dataadd=new Dataadd(this, "添加UE数据", true);//记得对jb2注册监听，TRUE 模式对话框
			//重新再获得新的数据模型
			//构建新的数据模型类，并更新
			tm=new Tablemodule();
			//更新JTable
			String sql="select * from UE where 1=?";
			String []paras={"1"};
			tm.search(sql,paras);
			jt.setModel(tm);
		}
//		else if(arg0.getSource()==jb3)
//		{
//			//修改记录
//			int rowNum=this.jt.getSelectedRow();//返回用户点中的行
//			if(rowNum==-1)//如果用户没有选择某一行
//			{
//				//提示
//				JOptionPane.showMessageDialog(this,"请选择一行");
//				return;
//			}
//			//显示修改对话框
//			new Modifystu(this, "修改", true, stm, rowNum);
//			//构建新的数据模型类，并更新
//			stm=new StutableModule();
//			//更新JTable
//			jt.setModel(stm);
//		}
		else if(arg0.getSource()==jb4)
		{
			//说明用户希望删除记录
			//得到学生ID号
			int rowNum=this.jt.getSelectedRow();//返回用户点钟的行
			if(rowNum==-1)//如果用户没有选择某一行
			{
				//提示
				JOptionPane.showMessageDialog(this,"请选择一行");
				return;
			}
			//得到学生编号
			String ID=(String) tm.getValueAt(rowNum,0);
			//连接数据库完成删除任务
			//创建一个SQL语句
			String sql="delete from UE where ID=?";
			String []paras={ID};
			Tablemodule temp=new Tablemodule();
			temp.change(sql, paras);
			
			//更新数据库 
			tm=new Tablemodule();
			String []paras2={"1"};
			tm.search("select * from UE where 1=?",paras2);
			jt.setModel(tm);
			
		}
	}
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}}

