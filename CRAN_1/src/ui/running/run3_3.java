package ui.running;

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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.JTextField;

import ui.Share_bag.BackgroundPanel;

import com.config.ConfigJavaControler;
import com.database.ReadConfig;
import com.test.StreamToTextArea;
import com.test.ThreadTaskMain;
import com.test.main;
import javax.swing.JTextArea;

public class run3_3 extends JDialog implements ActionListener{
	String path="image"+File.separator+"running"+File.separator+"running.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
	public static JTextField textField = new JTextField();
	public static JTextField textField_1 = new JTextField();
	public static JTextField textField_2 = new JTextField();
	StreamToTextArea blah = StreamToTextArea.getInstance();//文本框打印运行情况*****采用单例模式
	JButton button1;
	JButton button2;
	JButton button3;
	public static Long startTime;
	public static Long endTime;
	public static JProgressBar bar;
	JPanel panel_23;
	JComboBox jcb1,jcb2,jcb3,jcb4;
	String []jg1={"1","2"};
	String []jg2={"1","2"};
	String []jg3={"1","2"};
	String []jg4={"1","2"};
	public static ThreadTaskMain main_thread = new ThreadTaskMain(com.test.main.vMyGroup,"main procedure");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//textField.setText("");
//			System.loadLibrary("NativeTestJni");//加载库1
//			System.loadLibrary("NativeProCase");//加载库2
			run3_3 dialog = new run3_3();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	
//	
//	public run3(Dialog owner,String title,boolean modal) {
//		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
	public run3_3() {

//		main();
		this.addWindowListener(new WindowListener(){
		@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				main_thread.stopThread();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}); 		
		setTitle("仿真运行");
		System.setOut(blah.getPs());
		
		setBounds(200, 50, 773, 684);
		getContentPane().setLayout(new BorderLayout());
		//this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(blah, BorderLayout.SOUTH);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(1, 2, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(3, 1, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					
					{
						JLabel label = new JLabel("运行状态：");
						panel_2.add(label);
					}
					{
						panel_2.add(textField);
						textField.setColumns(5);
					}
					{
						panel_2.add(textField_1);
						textField_1.setColumns(5);
					}
				
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JLabel label = new JLabel("运行时间：");
						panel_2.add(label);
					}
					{
						textField_2 = new JTextField();
						panel_2.add(textField_2);
						textField_2.setColumns(10);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1);
				panel_1.setLayout(new GridLayout(2, 1, 0, 0));
				{

					bar = new JProgressBar();
					bar.setMinimum(0);
					bar.setMaximum(100);
					bar.setValue(0);
					bar.setStringPainted(true);
					//bar.addChangeListener((ChangeListener) this);
					bar.setPreferredSize(new Dimension(200, 30));
					panel_23 = new JPanel();
					panel_23.setOpaque(false);
					panel_23.setLayout(new FlowLayout(FlowLayout.RIGHT));
					panel_23.add(bar);
					panel_1.add(panel_23);
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					{
						button1 = new JButton("开始");
						button1.addActionListener(this);
						panel_2.add(button1);
					}
					{
						button2 = new JButton("暂停");
						button2.addActionListener(this);
						panel_2.add(button2);
					}
					{
						button3 = new JButton("停止");
						button3.addActionListener(this);
						panel_2.add(button3);
					}
				}
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setPreferredSize(new Dimension(700,40));
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.NORTH);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel label = new JLabel("数据导出");
					panel_1.add(label);
				}
			}
			{
				JPanel panel_1 = new JPanel();
//				panel_1.add(blah);
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
//			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.NORTH);
				{
					JLabel label = new JLabel("关注数据");
					panel_1.add(label);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setOpaque(false);
				panel.add(panel_1, BorderLayout.CENTER);
				panel_1.setLayout(new GridLayout(2, 2, 0, 0));
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3, BorderLayout.NORTH);
						panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						{
							jcb1=new JComboBox<>(jg1);
							jcb1.setPreferredSize(new Dimension(100,20));
							panel_3.add(jcb1);
						}
					}
					{
						JPanel panel_3 = new JPanel();
//						panel_3.setOpaque(false);
						panel_3.setBackground(Color.pink);
						panel_2.add(panel_3, BorderLayout.CENTER);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3, BorderLayout.NORTH);
						panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						{
							
							jcb2=new JComboBox<>(jg2);
							jcb2.setPreferredSize(new Dimension(100,20));
							panel_3.add(jcb2);
						}
					}
					{
						JPanel panel_3 = new JPanel();
//						panel_3.setOpaque(false);
						panel_3.setBackground(Color.green);
						panel_2.add(panel_3, BorderLayout.CENTER);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3, BorderLayout.NORTH);
						panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						{
							jcb3=new JComboBox<>(jg3);
							jcb3.setPreferredSize(new Dimension(100,20));
							panel_3.add(jcb3);
						}
					}
					{
						JPanel panel_3 = new JPanel();
//						panel_3.setOpaque(false);
						panel_3.setBackground(Color.blue);
						panel_2.add(panel_3, BorderLayout.CENTER);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setOpaque(false);
					panel_1.add(panel_2);
					panel_2.setLayout(new BorderLayout(0, 0));
					{
						JPanel panel_3 = new JPanel();
						panel_3.setOpaque(false);
						panel_2.add(panel_3, BorderLayout.NORTH);
						panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
						{
							jcb4=new JComboBox<>(jg4);
							jcb4.setPreferredSize(new Dimension(100,20));
							panel_3.add(jcb4);
						}
					}
					{
						JPanel panel_3 = new JPanel();
//						panel_3.setOpaque(false);
						panel_3.setBackground(Color.black);
						panel_2.add(panel_3, BorderLayout.CENTER);
					}
				}
			}
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// TODO 自动生成的方法存根
		if(arg0.getSource()==button1)
		{
			startTime = System.currentTimeMillis(); 
			//创建任务线程，建议做成按钮之前要判断线程是否是已存在，否则多次点击会创建多次线程
			
			if(!main_thread.isAlive())
			{
				main_thread=new ThreadTaskMain(com.test.main.vMyGroup,"main procedure");
				main_thread.start();
				System.out.println("线程启动成功！！！！！！！！");
			}
			
		}
		if(arg0.getSource()==button3)
		{
			Thread mythread[]=new Thread[main.vMyGroup.activeCount()];
			main.vMyGroup.enumerate(mythread);
			try {
				if (mythread.length>0) {
					for (int i = 0; i < mythread.length; i++) {
						if (mythread[i].getName().equals("main procedure")) {
							System.out.println("准备系统暂停");
							//main.vMyGroup.interrupt();
							System.out.println("---------"+mythread[i].getState());
							synchronized(mythread[i]){
									((ThreadTaskMain)mythread[i]).stopThread();									
							}
						}
					}
				}
				else {
					System.out.println("线程组为空");
				}
				
			} catch (Exception e2) {
				
				//System.out.println(e2.toString());// TODO: handle exception
			}
		
			System.out.println("系统暂停");
		}
		if(arg0.getSource()==button2)
		{
			ConfigJavaControler.vFlagPause=ConfigJavaControler.vFlagPause?false:true;// 设置暂停
			if(!ConfigJavaControler.vFlagPause)
			{
				button2.setText("继续");
			}else{
				button2.setText("暂停");
			}
		}
	}

}
