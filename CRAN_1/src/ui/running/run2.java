/*
 * 类名：run2
 * 功能：控制参数配置界面
 */
package ui.running;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import ui.Draft.run3_3;
import ui.Share_bag.BBU_sql;
import ui.Share_bag.BackgroundPanel;
import ui.Share_bag.MyTools;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;

import com.config.ConfigJavaControler;
import com.config.EnumSamplingFunction;
import com.config.EnumSamplingInterval;
import com.database.ReadConfig;
import com.help.HelpClass;

public class run2 extends JDialog implements ActionListener{
	
	private JPanel contentPanel;
	CardLayout cl;
	JLabel label;
	JPanel panel,panel_1,panel_2,panel_3,panel_4_1,panel_4_2,panel_5,panel_6_1,panel_6_2;
	JPanel panel_2_1,panel_7,panel_8_1,panel_8_2,panel_9,panel_10_1,panel_10_2,panel_2_2;
	JPanel panel_11,panel_12_1,panel_12_2,panel_13,panel_14_1,panel_14_2;
	JPanel panelx;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;
	public static JTextField textField_3;
	public static JTextField textField_4;
	public static JTextField textField_5;
	public static JTextField tf;
	public static JTextArea text;
	public static String Path;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	public static JComboBox jcb1;
	public static JComboBox jcb2;//下拉列表
	public static String []jg1={"TTI","s","m","h","d"};//对应 0，1,2,3,4 
	public static String []jg2={"等间隔抽样","自定义抽样"};//
	private static String name = BBU_sql.name;
	private static String pswd = BBU_sql.pswd;
	/**
	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		try {
//			run2 dialog = new run2();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**************************************
	 * 功能：构造配置界面
	 ***************************************/
	public run2(Frame owner,String title,boolean modal) {
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public run2() {
		setBounds(280, 100, 650, 330);
		setTitle("控制参数配置");
		getContentPane().setLayout(new BorderLayout());
		String path="image"+File.separator+"running"+File.separator+"running.png";
		Image image=new ImageIcon(path).getImage(); 
		contentPanel = new BackgroundPanel(image);
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));//设定panel与panel之间有间距
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				label = new JLabel("控制参数配置");
				label.setFont(new Font("微软雅黑", Font.BOLD, 16));
				panel.add(label);
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBackground(Color.white);
//			panel_1.setOpaque(false);
			contentPanel.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new GridLayout(4, 1, 0, 0));
			{
				panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_1.add(panel_2);
				panel_2.setLayout(new GridLayout(1, 2, 0, 0));
				{
					panel_3 = new JPanel();
					panel_3.setOpaque(false);
					panel_2.add(panel_3);
					panel_3.setLayout(new GridLayout(1, 2, 0, 0));
					{
						panel_4_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						panel_4_1.setOpaque(false);
						panel_3.add(panel_4_1);
						{
							JLabel label_1 = new JLabel("抽样单位：");
							label_1.setFont(MyTools.f14);
							panel_4_1.add(label_1);
						}
					}
					{
						panel_4_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_4_2.setOpaque(false);
						panel_3.add(panel_4_2);
						{
							jcb1=new JComboBox<>(jg1);
							jcb1.setFont(MyTools.f14);
							jcb1.setBackground(Color.white);
							jcb1.addActionListener(this);
							jcb1.setPreferredSize(new Dimension(113,20));
							panel_4_2.add(jcb1);
						}
					}
				}
				{
					panel_5 = new JPanel();
					panel_5.setOpaque(false);
					panel_2.add(panel_5);
					panel_5.setLayout(new GridLayout(1, 2, 0, 0));
					{
						panel_6_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						panel_6_1.setOpaque(false);
						panel_5.add(panel_6_1);
						{
							JLabel lbltti = new JLabel("抽样时刻TTI数：");
							lbltti.setFont(MyTools.f14);
							panel_6_1.add(lbltti);
						}
					}
					{
						panel_6_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_6_2.setOpaque(false);
						panel_5.add(panel_6_2);
						{
							textField_1 = new JTextField();
							panel_6_2.add(textField_1);
							textField_1.setColumns(10);
						}
					}
				}
			}
			{
				panel_2_1 = new JPanel();
				panel_2_1.setOpaque(false);
				panel_1.add(panel_2_1);
				panel_2_1.setLayout(new GridLayout(1, 2, 0, 0));
				{
					panel_7 = new JPanel();
					panel_7.setOpaque(false);
					panel_2_1.add(panel_7);
					panel_7.setLayout(new GridLayout(1, 2, 0, 0));
					{
						panel_8_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						panel_8_1.setOpaque(false);
						panel_7.add(panel_8_1);
						{
							JLabel lblTti = new JLabel("TTI时长(ms)：");
							lblTti.setFont(MyTools.f14);
							panel_8_1.add(lblTti);
						}
					}
					{
						panel_8_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_8_2.setOpaque(false);
						panel_7.add(panel_8_2);
						{
							textField_2 = new JTextField();
							panel_8_2.add(textField_2);
							textField_2.setColumns(10);
						}
					}
				}
				{
					panel_9 = new JPanel();
					panel_9.setOpaque(false);
					panel_2_1.add(panel_9);
					panel_9.setLayout(new GridLayout(1, 2, 0, 0));
					{
						panel_10_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						panel_10_1.setOpaque(false);
						panel_9.add(panel_10_1);
						{
							JLabel label_1 = new JLabel("抽样类型：");
							label_1.setFont(MyTools.f14);
							panel_10_1.add(label_1);
						}
					}
					{
						panel_10_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_10_2.setOpaque(false);
						panel_9.add(panel_10_2);
						{
							jcb2=new JComboBox<>(jg2);
							jcb2.setFont(MyTools.f14);
							jcb2.setBackground(Color.white);
							jcb2.setPreferredSize(new Dimension(113,20));
							jcb2.addActionListener(this);
							panel_10_2.add(jcb2);
						}
					}
				}
			}
			{
				panel_2_2 = new JPanel();
				panel_2_2.setOpaque(false);
				panel_1.add(panel_2_2);
				panel_2_2.setLayout(new GridLayout(1, 2, 0, 0));
				{
					panel_11 = new JPanel();
					panel_11.setOpaque(false);
					panel_2_2.add(panel_11);
					panel_11.setLayout(new GridLayout(1, 2, 0, 0));
					{
						panel_12_1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						panel_12_1.setOpaque(false);
						panel_11.add(panel_12_1);
						{
							JLabel label_1 = new JLabel("仿真总时长(ms)：");
							label_1.setFont(MyTools.f14);
							panel_12_1.add(label_1);
						}
					}
					{
						panel_12_2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
						panel_12_2.setOpaque(false);
						panel_11.add(panel_12_2);
						{
							textField_4 = new JTextField();
							panel_12_2.add(textField_4);
							textField_4.setColumns(10);
						}
					}
				}
				{
					
					cl=new CardLayout();
					panel_13 = new JPanel(cl);
					panel_13.setOpaque(false);
					panel_2_2.add(panel_13);
					//panel_13.setLayout(new CardLayout());  
					panel_14_1 = new JPanel();
					panel_14_1.setOpaque(false);
					panel_14_2 = new JPanel();
					panel_14_2.setOpaque(false);
					panel_13.add("1",panel_14_1);//第一层panel_14_1  
					panel_13.add("2",panel_14_2);//第二层panel_14_2
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_1.add(panel_2);
				panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				{
					JLabel label_1 = new JLabel("                   配置文件： ");
					label_1.setFont(MyTools.f14);
					panel_2.add(label_1);
				}
				{
					textField_5 = new JTextField();
					panel_2.add(textField_5);
					textField_5.setColumns(20);
				}
				{
					button1 = new JButton("打开");
					button1.setFont(MyTools.f14);
					button1.addActionListener(this);
					panel_2.add(button1);
				}
				{
					button2 = new JButton("上传");
					button2.setFont(MyTools.f14);
					button2.addActionListener(this);
					panel_2.add(button2);
				}
			}
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setOpaque(false);
			contentPanel.add(panel_1, BorderLayout.SOUTH);
			panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

			{
				button3 = new JButton("保存");
				button3.setFont(MyTools.f14);
				button3.addActionListener(this);
				panel_1.add(button3);
			}
			{
				button4 = new JButton("导出参数");
				button4.setFont(MyTools.f14);
				button4.setActionCommand("导出参数");
				button4.addActionListener(this);
				panel_1.add(button4);
//				getRootPane().setDefaultButton(button4);
			}
			{
				button5 = new JButton("运行");
				button5.setFont(MyTools.f14);
				button5.setActionCommand("运行");
				button5.addActionListener(this);
				panel_1.add(button5);
			}
		}

	}
	/**************************************
	 * 功能：界面上各个按钮的监听响应功能
	 ***************************************/
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		 TODO 自动生成的方法存根
		if(arg0.getSource()==jcb2)
		{
			panel_14_1.removeAll();
			if(jcb2.getSelectedIndex()==0)//等间隔抽样panel_14_1
			{
				panel_14_1.setLayout(new GridLayout(1, 2, 0, 0));
				{
					JPanel pl1= new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
					pl1.setOpaque(false);
					panel_14_1.add(pl1);
					{
						JLabel label_1 = new JLabel("等间隔抽样间隔：");
						label_1.setFont(MyTools.f14);
						pl1.add(label_1);
					}
				}
				{
					JPanel pl2 = new JPanel();
					pl2.setOpaque(false);
					panel_14_1.add(pl2);
					{
						tf = new JTextField();
						pl2.add(tf);
						tf.setColumns(10);
					}
				}
				cl.show(panel_13,"1");  
				panel_13.validate(); 
			}
			else if(jcb2.getSelectedIndex()==1)//自定义抽样panel_14_2
				{
				panel_14_2.removeAll();	
				panel_14_2.setLayout(new GridLayout(1, 2, 0, 0));
					{
						JPanel pl1= new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						pl1.setOpaque(false);
						panel_14_2.add(pl1);
						{
							JLabel label_1 = new JLabel("抽样间隔(逗号隔开)：");
							label_1.setFont(MyTools.f14);
							pl1.add(label_1);
						}
					}
					{
						JPanel pl2 = new JPanel(new FlowLayout(FlowLayout.LEFT , 5, 5));
						pl2.setOpaque(false);
						panel_14_2.add(pl2);
						{
							text=new JTextArea(); 
							text.setRows(2);
							text.setColumns(10);
							JScrollPane jsp=new JScrollPane(text);
							pl2.add(jsp);
						}
					}
					cl.show(panel_13,"2");  
					panel_13.validate(); 
				}
			
		}
		if(arg0.getSource()==button1)//打开
		{
			JFileChooser filechooser=new JFileChooser();
			//显示文件选择对话框
			int i=filechooser.showOpenDialog(getContentPane());//this.getContentPane()的作用是初始化一个容器，用来在容器上添加一些控件
			//判断用户单击的是否为打开按钮
			if(i==JFileChooser.APPROVE_OPTION)
			{
				//获得选中的文件对象
				File selectedFile=filechooser.getSelectedFile();
				//显示选中文件的名字
				Path=selectedFile.getAbsolutePath();
				textField_5.setText(selectedFile.getName());
				System.out.println(Path);
			}
				
		}
		if(arg0.getSource()==button2)
		{
			ReadConfig vTest=new ReadConfig(Path);
		}
		if(arg0.getSource()==button3)
		{
			Connection conn = null;
			PreparedStatement pst = null;
			try {
				//反射机制，创建数据库Driver
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				//System.out.println("数据库连接成功");
				conn = java.sql.DriverManager.getConnection(  
		                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, pswd); //数据库database--test,username--root，password--root 
				conn.setAutoCommit(false); 
				//Dictionary_Geology -- 表名称 
				pst = (PreparedStatement) conn.prepareStatement("Update ControlParameter Set CaseTTI = ?,vSamplingFunction = ?,TTILength = ?,SamplingUnit = ?,SimuT = ?,SamplingNum = ?,SamplingInterval = ?,TTI = ?" );
			    //pst.setString(1, Integer.toString(1));
				pst.setString(1, Integer.toString(0));
				pst.setString(2, Integer.toString(jcb2.getSelectedIndex()));
				pst.setString(3, textField_2.getText());
				pst.setString(4, Integer.toString(jcb1.getSelectedIndex()));
				pst.setString(5, textField_4.getText());
				pst.setString(6, textField_1.getText());
				if(jcb2.getSelectedIndex() == 1)
				{
					pst.setString(7, text.getText());
				}else if(jcb2.getSelectedIndex() == 0)
				{
					System.out.println(jcb1.getSelectedIndex());
					pst.setString(7, tf.getText());
				}
				pst.setString(8, Integer.toString(0));
				pst.addBatch(); //事务整体添加
				//事务整体提交
				pst.executeUpdate(); 
				conn.commit(); 
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				//关闭PreparedStatement
				if(pst != null) {
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pst = null;
				}
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					pst = null;
				}
			}
		}
		if(arg0.getSource()==button5)
		{

			Connection conn = null;
			PreparedStatement pst = null;
		
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载驱动
				conn=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=C-RAN",name,pswd);//得到连接
				pst = (PreparedStatement) conn.prepareStatement("Update ControlParameter Set TTI = 0");
				//执行操作
				pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally
			{
			}
			
			ConfigJavaControler.vTtiTimeLength = Integer.parseInt(textField_2.getText());
			ConfigJavaControler.vIntervalUnit=EnumSamplingInterval.get_model(jcb1.getSelectedIndex());//抽样方式，是等间隔抽样还是自定
			ConfigJavaControler.vSamplingFunction=EnumSamplingFunction.get_model(jcb2.getSelectedIndex());
            ConfigJavaControler.vSimuT=Double.parseDouble(textField_4.getText());//仿真总时长，单位由仿真抽样单位决定
            
            if (ConfigJavaControler.vSamplingFunction==EnumSamplingFunction.vEqualInterval) {
	            ConfigJavaControler.vSamplingNum=Integer.parseInt(textField_1.getText());//存储抽样的时刻,决定抽样的个数   
	            int interval = Integer.parseInt(tf.getText());
	            ConfigJavaControler.vSamplingTime=new int[(int)ConfigJavaControler.vSimuT/interval+1];
	            for (int i = 0; i < (int)ConfigJavaControler.vSimuT/interval+1; i++) {				
					ConfigJavaControler.vSamplingTime[i]=interval*i;
				}
//				System.out.println("抽样的个数"+ConfigJavaControler.vSimuT);
			}else {
				System.out.println(textField_1.getText());
	            ConfigJavaControler.vSamplingNum=Integer.parseInt(textField_1.getText());//存储抽样的时刻,决定抽样的个数    
	            ConfigJavaControler.vSamplingTime=HelpClass.StringTrans2IntArray(text.getText());//存储抽样的时刻,决定抽样的个数          
	            
			}
            
            JFrame a = new run3(this,"run3",false);
            this.dispose();
            a.setVisible(true);
			//run3.begin();
            
		}
		
		
		
	}

}
