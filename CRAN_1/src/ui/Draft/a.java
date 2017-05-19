package ui.Draft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;


public class a extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	JSplitPane splitPane;
	JPanel panel1,panel_1,panel2,panel2_1,panel2_2,panel2_2_1;
	JButton button1,button2;
	JTextField textField,textField_1,textField_2,textField_3;
	
	int bbupool_num;
	DefaultMutableTreeNode node_second2_1,node_second2_2,node_second2_3,node_second2_4,node_second3_1,node_second3_2,node_second3_3,
	node_second4_1,node_second4_2,node_second4_3,node_second4_4,node_second4_5;
	JTree Treeroot=null;
	JTable jt1,jt2;
	JScrollPane jsp1,jsp2;
//	JCheckBox jc1,jc2,jc;//复选框
//	JComboBox jcb1,jcb2,jcb3;//下拉列表
	private JPanel panel;
	private JPanel panel_2, panel_3,panel_4,panel_5,panel_6,panel_7,panel_8,panel_9,panel_10,panel_11;
	private JButton button,button_1,button_2,btnNewButton,btnNewButton_1,btnNewButton_2;
//	String []jg1={"分布0","分布1","分布2"};//数组
//	String []jg2={"室内","室外"};//数组
//	String []jg3={"天线0","天线1","天线2"};//数组

	public static void main(String[] args) {
		try {
			//Ue_add dialog = new Ue_add();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public a() {
		//设置窗体属性
		setTitle("UE参数输入");
		setBounds(100, 100, 910, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		splitPane = new JSplitPane();
		contentPanel.add(splitPane);
		panel1 = new JPanel();
//左栏
		splitPane.setLeftComponent(panel1);
		panel1.setLayout(new BorderLayout(0, 0));
		{
			JLabel label = new JLabel("网元列表");
			label.setFont(new Font("黑体", Font.BOLD, 20));
			panel1.add(label, BorderLayout.NORTH);
		}
		{
			panel_1 = new JPanel();
			panel1.add(panel_1, BorderLayout.CENTER);
			panel_1.setBackground(Color.white);
			//build_tree();
			panel_1.add(Treeroot, BorderLayout.CENTER);
			
		}
//右栏
		panel2 = new JPanel();
		splitPane.setRightComponent(panel2);
		panel2.setLayout(new BorderLayout(0, 0));
		
		
		panel2_2 = new JPanel();
		panel2.add(panel2_2, BorderLayout.NORTH);
		panel2_2.setLayout(new GridLayout(2, 1, 0, 0));//4行1列的表格
		//第1行
		JLabel lblBbu = new JLabel("UE参数录入");
		panel2_2.add(lblBbu);
		lblBbu.setFont(new Font("黑体", Font.BOLD, 20));
		//第2行
		panel2_2_1 = new JPanel();
		panel2_2.add(panel2_2_1);
		{
			JLabel lblBbu_1 = new JLabel("BBU池个数：");//可以利用生成左栏时的数据bbupool_num
//			panel2_2_1.add(lblBbu_1);
//			int temp_bbupool_num=bbupool_num;
//			String string_bbupool_num=Integer.toString(temp_bbupool_num);
//			textField = new JTextField(string_bbupool_num,5);
			panel2_2_1.add(textField);	
		}
		{
			JLabel lblBbu_2 = new JLabel("BBU个数：");//需要遍历数据库
			panel2_2_1.add(lblBbu_2);
//			//需要遍历数据库
//			bbu_module=new bbu_module().bbumodule();;
//			bbu_num=new bbu_module().bbumodule().getRowCount();//bbupool_num一共有几个bbu池
//			int temp_bbu_num=bbu_num;
//			String string_bbu_num=Integer.toString(temp_bbu_num);
//			textField_1 = new JTextField(string_bbu_num,5);
			panel2_2_1.add(textField_1);
		}
		{
			JLabel lblRru = new JLabel("RRU个数：");
			panel2_2_1.add(lblRru);
//			//需要遍历数据库
//			rru_module=new rru_module().rrumodule();;
//			rru_num=new rru_module().rrumodule().getRowCount();//bbupool_num一共有几个bbu池
//			int temp_rru_num=rru_num;
//			String string_rru_num=Integer.toString(temp_rru_num);
//			textField_2 = new JTextField(string_rru_num,5);
			panel2_2_1.add(textField_2);
		}
		{
			JLabel lblUe = new JLabel("UE个数：");
			panel2_2_1.add(lblUe);
//			//需要遍历数据库
//			ue_module=new ue_module().uemodule();;
//			ue_num=new ue_module().uemodule().getRowCount();//bbupool_num一共有几个bbu池
//			int temp_ue_num=ue_num;
//			String string_ue_num=Integer.toString(temp_ue_num);
//			textField_3 = new JTextField(string_ue_num,5);
			panel2_2_1.add(textField_3);
		}
//添加信息的部分		
		panel = new JPanel();
		panel2.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		//上半部分
		panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//		
//		jc1=new JCheckBox("全选");//复选框
//		jc1.addActionListener(this);
//		panel_8.add(jc1);
		button_1 = new JButton("添加");
		button_1.addActionListener(this);
		panel_8.add(button_1);
		
		button_2 = new JButton("保存");
		button_2.addActionListener(this);
		panel_8.add(button_2);
		
		panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		button = new JButton("删除");
		panel_9.add(button);
		panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout());
		panel_3.add(panel_5, BorderLayout.CENTER);
//		uep1=new ue1_add_panel();
//		uep1.ue1_add_panel(0);	
//		jt1=new JTable(uep1);
//		jt1.addMouseListener(this);
////		jt1.setRowSelectionAllowed(false);
//		//jt1.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		jt1.setRowSelectionAllowed(true);//使得表格的选取是以行为单位,而不是以列为单位.若你没有写此行,则在选取表格数据时以整列为单位.
//		selectionMode1=jt1.getSelectionModel();
//		selectionMode1.addListSelectionListener(this);
//		selectionMode1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//多重选择
		//初始化jsp
		jsp1=new JScrollPane(jt1);
		//把jsp加入到JFrame中
		panel_5.add(jsp1);
		//下半部分
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new GridLayout(1, 2, 0, 0));
		
		panel_10 = new JPanel();
		panel_6.add(panel_10);
		panel_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
//		jc2=new JCheckBox("全选");//复选框
//		panel_10.add(jc2);
		btnNewButton_2 = new JButton("添加");
		panel_10.add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("保存");
		panel_10.add(btnNewButton_1);
		
		panel_11 = new JPanel();
		panel_6.add(panel_11);
		panel_11.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnNewButton = new JButton("删除");
		panel_11.add(btnNewButton);
		
		panel_7 = new JPanel();
		panel_7.setLayout(new BorderLayout());
		panel_2.add(panel_7, BorderLayout.CENTER);
		
		
//		jt2=new JTable(uep2);
////		String []jg2={"室内","室外"};//数组
//		jcb2=new JComboBox<>(jg2);
////		String []jg3={"天线0","天线1","天线2"};//数组
//		jcb3=new JComboBox<>(jg3);
//		jt2.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(jcb2));//列是从0开始数的
//		jt2.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcb3));
//		
//		jt2.getColumnModel().getColumn(0).setCellEditor(this.jt2.getDefaultEditor(Boolean.class)); //添加复选框
//		jt2.getColumnModel().getColumn(0).setCellRenderer(this.jt2.getDefaultRenderer(Boolean.class));//设置样式
//		jt2.setRowHeight(21);//设置行高

		//初始化jsp
		jsp2=new JScrollPane(jt2);
		//把jsp加入到JFrame中
		panel_7.add(jsp2);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("退出");
				cancelButton.setActionCommand("退出");
				buttonPane.add(cancelButton);
			}
		}
	}

//	private void build_tree() {
//		// TODO 自动生成的方法存根
//		//树形列表
//		//根节点
//		DefaultMutableTreeNode root = new DefaultMutableTreeNode("根节点");
//		
//		//一级节点
//		String sql="select * from BbuPool where 1=?";
//		String []paras={"1"};
//		bbuc=new Num_search_module();
//		bbuc.search_num(sql, paras,1);//只需要BBU池的第一列数据，获取BBU池的ID号,因为bbu池的id号是不连续的。
//		bbupool_num=bbuc.getRowCount();
//		for(int i=0;i<bbupool_num;i++)//i这里表示第几行，但是行数不等于ID号
//		{
//			DefaultMutableTreeNode node_first = new DefaultMutableTreeNode("BBU池"+bbuc.getValueAt(i, 0));
//			//二级节点
//			String sql2="select * from Bbu where BbuPoolId=?";
//			//int temp=i;
//			//String BbuPoolId=String.valueOf(temp);//转换后int temp——>String temp了，所以不能直接用int i，要用一个临时变量temp
////			String BbuPoolId=Integer.toString(i);//int类型转换成string类型
//			String BbuPoolId=(String) bbuc.getValueAt(i, 0);
//			String []paras2={BbuPoolId};
//			bbu=new Num_search_module();
//			bbu.search_num(sql2, paras2,2);//需要BBU的两列数据，获取BBU的ID号，和BBU池的ID号
//			int bbu_num=bbu.getRowCount();
//			for(int j=0;j<bbu_num;j++)
//			{
//				DefaultMutableTreeNode node_second = new DefaultMutableTreeNode("BBU"+bbu.getValueAt(j, 0),false);
//				
//				node_first.add(node_second);
//			}
//			root.add(node_first);
//		}
//		//利用根节点直接创建树Treeroot
//		Treeroot=new JTree(root);
//		TreeSelectionModel treeSelectionModel;//获得树的选择模式
//		treeSelectionModel=Treeroot.getSelectionModel();
//		//设置树的选择模式为单选
//		treeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
//		
//		panel_1.add(Treeroot, BorderLayout.CENTER);
//		//定制树
//		Treeroot.setRootVisible(false);//无根节点
//		Treeroot.setRowHeight(20);//树节点的行高
//		DefaultTreeCellRenderer treeCellRenderer=new DefaultTreeCellRenderer();//获得树节点的绘制对象
//		treeCellRenderer=(DefaultTreeCellRenderer)Treeroot.getCellRenderer();
//		treeCellRenderer.setLeafIcon(null);
//		treeCellRenderer.setClosedIcon(null);
//		treeCellRenderer.setOpenIcon(null);
//		Treeroot.setFont(new Font("宋体", Font.PLAIN, 20));
//	}
//	
//
//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		// TODO 自动生成的方法存根
//		if(arg0.getSource()==button_1)//ue1的添加
//		{
//			ue1_row_num=ue1_row_num+1;//表格里面添加新数据的行数
//			System.out.println(ue1_row_num);
//			uep1=new ue1_add_panel();
//			uep1.ue1_add_panel(ue1_row_num);;
//			jt1.setModel(uep1);
//			
//////			String []jg1={"分布0","分布1","分布2"};//数组
////			jcb1=new JComboBox<>(jg1);
//////			String []jg2={"室内","室外"};//数组
////			jcb2=new JComboBox<>(jg2);
//////			String []jg3={"天线0","天线1","天线2"};//数组
////			jcb3=new JComboBox<>(jg3);
////			jt1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jcb1));
////			jt1.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(jcb2));
////			jt1.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(jcb3));
////			
////			jt1.getColumnModel().getColumn(0).setCellEditor(this.jt1.getDefaultEditor(Boolean.class)); //添加复选框
////			jt1.getColumnModel().getColumn(0).setCellRenderer(this.jt1.getDefaultRenderer(Boolean.class));//设置样式
////			jt1.setRowHeight(21);//设置行高
//		}else if(arg0.getSource()==button_2)//ue1的删除
//		{
//
//			if(select_row_num.length==0)//如果用户没有选择某一行
//			{
//				//提示
//				JOptionPane.showMessageDialog(this,"请选择一行");
//				return;
//			}
//			
////			for(int i=0;i<select_row_num.length;i++)
////			{
////			System.out.println(select_row_num[i]+1);
////			}
//			
//			if(ue1_row_num>0)
//			{
//				uep1.removeRows(select_row_num, select_row_num.length);
//				ue1_row_num=ue1_row_num-select_row_num.length;//ue1_row_num删除后表格剩余几行
//				System.out.println("ok"+ue1_row_num);
//				uep1.fireTableRowsDeleted(select_row_num[0], select_row_num[select_row_num.length-1]);
//				uep1.ue1_add_panel(ue1_row_num);;
//				jt1.setModel(uep1);
//			}
//			if(ue1_row_num==0)
//			{
//				//如果删除到一行都没有，怎么办
//			}
//		}
//		else if(arg0.getSource()==button)//ue1的生成
//		{
//			
//		}else if(arg0.getSource()==btnNewButton)//ue2的生成
//		{
//			
//		}if(arg0.getSource()==btnNewButton_1)//ue2的添加
//		{
//			
//		}else if(arg0.getSource()==btnNewButton_2)//ue2的删除
//		{
//			
//		}
//		
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
	}


	
		
	
}

