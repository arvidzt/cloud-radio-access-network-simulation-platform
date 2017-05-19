package ui.Draft;


import java.awt.*;

import javax.swing.*;


public class NE_deploy01 extends JDialog {

//	private final JPanel contentPanel = new JPanel();

	JPanel jp1, jp2, jp3,jp1_1,jp1_2,jp1_3,jp1_4,jp1_5,jp1_6;
	JButton jb1;
	JLabel jl2;
	JTabbedPane jtp;
	private JLabel lblBbu;
	private JComboBox comboBox;
	private JButton button;
	private JButton button_1;
	private JLabel lblBbux;
	private JTextField textField;
	private JLabel lblBbuy;
	private JTextField textField_1;
	private JLabel label;
	private JTextField textField_2;
	private JLabel lblBbu_1;
	private JComboBox comboBox_1;
	private JButton button_2;
	private JButton button_3;
	private JLabel label_1;
	private JComboBox comboBox_2;
	private JButton button_4;
	private JLabel lblBbu_2;
	private JTextField textField_3;
	private JSplitPane splitPane;
	private JPanel panel;
	private JLabel lblBbu_3;
	private JPanel panel_1;
	private JLabel lblBburru;
	public static void main(String[] args) {
	
			NE_deploy01 dialog = new NE_deploy01();
			
	}

	/**
	 * Create the dialog.
	 */
	public NE_deploy01() {
		//设置窗体属性
				this.setTitle("网元及用户添加设置");
				this.setSize(800, 600);//窗体大小
				this.setLocation(300, 100);//窗体位置
				//禁止用户改变窗体大小
				this.setResizable(false);
				//this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//??????????????????????????
				this.setVisible(true);
		//组件
				jb1=new JButton();

				jl2=new JLabel("   参数文件导入");
		//JTabbedPane new部分
				jtp=new JTabbedPane();
				jp1=new JPanel();
				jp1_1=new JPanel();
				jp1_2=new JPanel();
				jp1_3=new JPanel();
				jp1_4=new JPanel();
				jp1_5=new JPanel();
				jp1_6=new JPanel();

				jp2=new JPanel();
				jp3=new JPanel();

				//jp1
				jp1.setLayout(new GridLayout(4,2));
				jp1.add(jp1_1);
				jp1_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				lblBbu = new JLabel("  BBU池编号：  ");
				jp1_1.add(lblBbu);

//				String []bbuc_num={"1","2","3","4","5"};//数组
//				comboBox = new JComboBox<>(bbuc_num);
//				jp1_1.add(comboBox);
				
				button = new JButton("添加");
				jp1_1.add(button);
				
				button_1 = new JButton("删除");
				jp1_1.add(button_1);
				jp1.add(jl2);
				jp1.add(jp1_2);
				jp1_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				lblBbux = new JLabel("  BBU池X坐标： ");
				jp1_2.add(lblBbux);
				
				textField = new JTextField();
				jp1_2.add(textField);
				textField.setColumns(5);
				
				lblBbuy = new JLabel("  BBU池Y坐标： ");
				jp1_2.add(lblBbuy);
				
				textField_1 = new JTextField();
				jp1_2.add(textField_1);
				textField_1.setColumns(5);
				jp1.add(jp1_3);
				jp1_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				label = new JLabel("  文件地址：");
				jp1_3.add(label);
				
				textField_2 = new JTextField(30);
				jp1_3.add(textField_2);
				textField_2.setColumns(10);
				jp1.add(jp1_4);
				jp1_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				lblBbu_1 = new JLabel("  BBU编号：      ");
				jp1_4.add(lblBbu_1);
				
//				String []bbu_num={"1","2","3","4","5"};//数组
//				comboBox_1 = new JComboBox(bbu_num);
//				jp1_4.add(comboBox_1);
				
				button_2 = new JButton("添加");
				jp1_4.add(button_2);
				
				button_3 = new JButton("删除");
				jp1_4.add(button_3);
				jp1.add(jp1_5);
				jp1_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				label_1 = new JLabel("  文件名称：  ");
				jp1_5.add(label_1);
				
//				comboBox_2 = new JComboBox();
//				jp1_5.add(comboBox_2);
				
				button_4 = new JButton("导入");
				jp1_5.add(button_4);
				jp1.add(jp1_6);
				jp1_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				lblBbu_2 = new JLabel("  BBU的容量：  ");
				jp1_6.add(lblBbu_2);
				
				textField_3 = new JTextField(10);
				jp1_6.add(textField_3);
				
				
				//jp2
				jp2.setBackground(Color.black);
				//jp3

				
				
				panel = new JPanel();
				lblBbu_3 = new JLabel("BBU池列表");
				panel.add(lblBbu_3);
				
				panel_1 = new JPanel();
				lblBburru = new JLabel("BBU及RRU基本信息");
				panel_1.add(lblBburru, BorderLayout.NORTH);
				JSplitPane jsp2=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel,panel_1);
				
				jtp.add("BBU",jp1);
				jtp.add("RRU",jp2);
				jtp.add("UE",jp3);
				getContentPane().add(jtp,BorderLayout.NORTH);
//下半部分				
//				
//				panel = new JPanel();
//				lblBbu_3 = new JLabel("BBU池列表");
//				panel.add(lblBbu_3);
//				
//				panel_1 = new JPanel();
//				lblBburru = new JLabel("BBU及RRU基本信息");
//				panel_1.add(lblBburru, BorderLayout.NORTH);
//				JSplitPane jsp2=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panel,panel_1);
				getContentPane().add(jsp2,BorderLayout.CENTER);

	



		
		
		
		
//		setBounds(100, 100, 450, 300);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setLayout(new FlowLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		{
//			JPanel buttonPane = new JPanel();
//			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//			getContentPane().add(buttonPane, BorderLayout.SOUTH);
//			{
//				JButton okButton = new JButton("OK");
//				okButton.setActionCommand("OK");
//				buttonPane.add(okButton);
//				getRootPane().setDefaultButton(okButton);
//			}
//			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
//			}
//		}
	}

}
