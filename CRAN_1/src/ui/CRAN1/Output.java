package ui.CRAN1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ui.Share_bag.*;

public class Output extends JDialog implements ActionListener {
	String path="image"+File.separator+"Add_BBU_RRU_UE"+File.separator+"input.png";
	Image image=new ImageIcon(path).getImage(); 
	private final JPanel contentPanel = new BackgroundPanel(image);
	JLabel label,label_2,label_1;
	private JPanel panel,buttonPane,panel_1,panel_2,panel_3,panel_4,panel_5;
	private JButton button,btnexcel,cancelButton;
	private JTextField textField;
	JComboBox jcb;//下拉列表
	String []jg={"BBU池","BBU","RRU","UE","天线","Link","LinkBbuPool2BbuPool","LinkBbu2Bbu","LinkBbu2Rru"};//RRU状态属性数组，不填写默认是0
	String Path;
//	public static void main(String[] args) {
//		try {
//			Output dialog = new Output();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public Output(Frame owner,String title,boolean modal){
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
//	public Output(){
		setBounds(450, 150, 400, 250);
		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			panel.setOpaque(false);
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			{
				label = new JLabel("参数文件导出");
				label.setFont(MyTools.f15);
				label.setFont(MyTools.f1);
				panel.add(label);
			}
		}
		{
			panel_1 = new JPanel();
			panel_1.setBackground(Color.white);
			contentPanel.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new GridLayout(2, 1, 0, 0));
			{
				panel_2 = new JPanel();
				panel_2.setOpaque(false);
				panel_1.add(panel_2);
				panel_2.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
				{
					label_1 = new JLabel(" 参数文件导出类型：");
					label_1.setFont(MyTools.f15);
					panel_2.add(label_1);
					jcb=new JComboBox<>(jg);
					jcb.setBackground(Color.white);
					jcb.setEditable(false);
					jcb.setPreferredSize(new Dimension(113,20));//设置下拉框的高和宽
					panel_2.add(jcb);
				}
				
				
			}
			{
				panel_4 = new JPanel();
				panel_4.setOpaque(false);
				panel_1.add(panel_4);
				panel_4.setLayout(new GridLayout(2, 1, 0, 0));
				{
					panel_3 = new JPanel();
					panel_3.setOpaque(false);
					panel_4.add(panel_3);
					panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
					{
						label_2 = new JLabel(" 选择导出文件夹：");
						label_2.setFont(MyTools.f15);
						panel_3.add(label_2);
					}
					{
						textField = new JTextField();
						panel_3.add(textField);
						textField.setColumns(20);
					}
				}
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			contentPanel.add(buttonPane, BorderLayout.SOUTH);
			
				
			{
				button = new JButton("浏览");
				button.setFont(MyTools.f14);
				button.addActionListener(this);
				buttonPane.add(button);
			}
			{
				btnexcel = new JButton("确认导出成Excel");
				btnexcel.setFont(MyTools.f14);
				btnexcel.addActionListener(this);
				buttonPane.add(btnexcel);
			}
			{
				cancelButton = new JButton("退出");
				cancelButton.setFont(MyTools.f14);
				cancelButton.setActionCommand("退出");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);	
			}
				
		}
	}
	public static void oneKeyOutput(String Path)
	{
		Export_excel excel_out1=new Export_excel();
		try {
//				String Path = "D:";
				String BbuPool_Path=Path+"/BbuPool.xls";
				excel_out1.Export("BbuPool",BbuPool_Path);
				
				String Bbu_Path=Path+"/Bbu.xls";
				excel_out1.Export("Bbu",Bbu_Path);
				
				String RRU_Path=Path+"/Rru.xls";
				excel_out1.Export("Rru",RRU_Path);
				
				String UE_Path=Path+"/Ue.xls";
				excel_out1.Export("Ue",UE_Path);
				
				String Antenna_Path=Path+"/Antenna.xls";
				excel_out1.Export("Antenna",Antenna_Path);
				
				String LinkCircle_Path=Path+"/Link.xls";
				excel_out1.Export("Link",LinkCircle_Path);
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			//e1.printStackTrace();
		}	
	}
	public static void caseOutput(String Path)
	{
		Export_excel excel_out1=new Export_excel();
		try {
			String case_Path=Path+"/PCase.xls";
			excel_out1.Export("PCase",case_Path);
		}catch (Exception e1) {
			// TODO 自动生成的 catch 块
			//e1.printStackTrace();
		}	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==button)
		{
			JFileChooser filechooser=new JFileChooser();
			filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // 只显示目录
			//显示文件选择对话框
			int i=filechooser.showOpenDialog(getContentPane());//this.getContentPane()的作用是初始化一个容器，用来在容器上添加一些控件
			//判断用户单击的是否为打开按钮
			if(i==JFileChooser.APPROVE_OPTION)
			{
				//获得选中的文件对象
				File selectedFile=filechooser.getSelectedFile();
				//显示选中文件的路径
				Path=selectedFile.getAbsolutePath();
				textField.setText(Path);//BBU的textField
				
			}	
		}
		else if(arg0.getSource()==btnexcel)
		{
			if(jcb.getSelectedIndex()==0)//BBU池
			{
				Export_excel excel_out1=new Export_excel();
				try {
						String BbuPool_Path=Path+"\\BbuPool.xls";
					excel_out1.Export("BbuPool",BbuPool_Path);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					//e1.printStackTrace();
				}
			}
			else if(jcb.getSelectedIndex()==1)//BBU
			{
				Export_excel excel_out2=new Export_excel();
				try {
						String Bbu_Path=Path+"\\Bbu.xls";
					excel_out2.Export("Bbu",Bbu_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==2)//RRU
			{
				Export_excel excel_out3=new Export_excel();
				try {
						String RRU_Path=Path+"\\Rru.xls";
					excel_out3.Export("Rru",RRU_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==3)//UE
			{
				Export_excel excel_out4=new Export_excel();
				try {
						String UE_Path=Path+"\\Ue.xls";
					excel_out4.Export("Ue",UE_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==4)//天线
			{
				Export_excel excel_out5=new Export_excel();
				try {
						String Antenna_Path=Path+"\\Antenna.xls";
					excel_out5.Export("Antenna",Antenna_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==5)//Link
			{
				Export_excel excel_out5=new Export_excel();
				try {
						String LinkCircle_Path=Path+"\\Link.xls";
					excel_out5.Export("Link",LinkCircle_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==6)//LinkBbuPool2BbuPool
			{
				Export_excel excel_out5=new Export_excel();
				try {
						String LinkBbuPool2BbuPool_Path=Path+"\\LinkBbuPool2BbuPool.xls";
					excel_out5.Export("LinkBbuPool2BbuPool",LinkBbuPool2BbuPool_Path);
				} catch (Exception e1) {

				}
			}
			else if(jcb.getSelectedIndex()==7)//LinkBbu2Bbu
			{
				Export_excel excel_out5=new Export_excel();
				try {
						String LinkBbu2Bbu_Path=Path+"\\LinkBbu2Bbu.xls";
					excel_out5.Export("LinkBbu2Bbu",LinkBbu2Bbu_Path);
				} catch (Exception e1) {

				}
				System.out.println("ok");
			}
			else if(jcb.getSelectedIndex()==8)//LinkBbu2Rru
			{
				Export_excel excel_out5=new Export_excel();
				try {
						String LinkBbu2Rru_Path=Path+"\\LinkBbu2Rru.xls";
					excel_out5.Export("LinkBbu2Rru",LinkBbu2Rru_Path);
				} catch (Exception e1) {

				}
			}
			
			
		}
		//退出
		else if(arg0.getSource()==cancelButton)
		{
			this.dispose();
		}
	}

}
