//edit by tao
package map.network;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.*;

import map.MapPane;
import map.MapUtil;

import org.apache.poi.hssf.record.formula.functions.False;
import org.apache.poi.hssf.record.formula.functions.Int;
import org.apache.poi.hssf.util.HSSFColor.YELLOW;

import java.lang.Math;
import java.nio.channels.NetworkChannel;

import twaver.Link;
import twaver.Node;
import twaver.ResizableFilter;
import twaver.DataBoxQuickFinder;
import twaver.Element;
import twaver.ElementCallbackHandler;
import twaver.MovableFilter;
import twaver.ResizableNode;
import twaver.TDataBox;
import twaver.TUIManager;
import twaver.TWaverConst;
import twaver.TWaverUtil;
import twaver.VisibleFilter;
import twaver.base.A.E.X;
import twaver.base.A.E.b;
import twaver.network.NetworkToolBarFactory;
import twaver.network.TNetwork;
import twaver.network.background.ImageBackground;
import twaver.swing.TableLayout;
import ui.Share_bag.BBU_sql;

import java.awt.Graphics;

public class GraphDemo extends MapPane {
	static {
		TUIManager.registerAttachment("BbuPool", BbuPoolAttachment.class);
		TUIManager.registerAttachment("Bbu", BbuAttachment.class);
		TUIManager.registerAttachment("Rru", RruAttachment.class);
	}
	static String name = BBU_sql.name;
	static String passWord = BBU_sql.pswd;
	TDataBox box = new TDataBox();
	TNetwork network = new TNetwork(box);
	//初始化
	DataBoxQuickFinder finder = box.createJavaBeanFinder(TWaverConst.PROPERTYNAME_NAME);
	ImageBackground background = new ImageBackground("/demo/resource/images/网格改1.png",true);//背景图
	//ImageBackground background2 = new ImageBackground("/demo/resource/images/网格.png",false);
	//记录节点数目，用于后面更新时读取迭代
	int bbuPoolIndex = 0;
	int bbuIndex = 0;
	int rruIndex = 0;
	int ueIndex = 0;
	int accessIndex = 0;
	int circleIndex = 0;
	int linkBusIndex = 0;
	int linkCircleIndex = 0;
	static List<StateNode> bbuPoolList = new ArrayList<StateNode>();
	static List<StateNode> bbuList = new ArrayList<StateNode>();
	static List<StateNode> rruList = new ArrayList<StateNode>();
	static List<StateNode> ueList = new ArrayList<StateNode>();
	static List<StateNode> accessList = new ArrayList<StateNode>();
	static List<StateNode> LinkBusList = new ArrayList<StateNode>();
	static List<ResizableNode> LinkCircleList = new ArrayList<ResizableNode>();
	final JTextField quickSearch = new JTextField();
	final JLabel eLabel = new JLabel("  查找  ");
	final JLabel eLabel2 = new JLabel("更换背景图");
	final JCheckBox movable = new JCheckBox("移动"){};
	final JCheckBox movable1 = new JCheckBox("Bbu池");
	final JCheckBox movable2 = new JCheckBox("Bbu");
	final JCheckBox movable3 = new JCheckBox("Rru");
	final JCheckBox movable4 = new JCheckBox("Ue");
	final JCheckBox movable5 = new JCheckBox("环线");
	//final JSlider zoomSlider = createZoomSlider();
	final JButton saveButton = new JButton("保存"); 
	final JButton clearAllButton = new JButton("全部清除");
	final JPanel panel1 = new JPanel();
	final JPanel panel2 = new JPanel();
	
	public GraphDemo() throws Exception {
		this.add(network, BorderLayout.CENTER);
		String[] ids = new String[] {
			    "Selection",
			    "Magnifier",
			    "Pan",
			    "ZoomIn",
			    "ZoomOut",
			    "ZoomReset",
			    "ZoomToRectangle",
			    "FullScreen",
			    "ExportImage",
		};
		network.setToolbar(NetworkToolBarFactory.getToolBar(ids, network));
		// set background
		ButtonGroup group =(ButtonGroup) network.getToolbar().getClientProperty(TWaverConst.TOOLBAR_BUTTON_GROUP_KEY_PREFIX+TWaverConst.DEFAULT_BUTTON_GROUP_NAME);
		//add it into the default button group
		//add it into the network toolbar
		panel2.add(movable);
		panel2.add(movable1);
		panel2.add(movable2);
		panel2.add(movable3);
		panel2.add(movable4);
		panel2.add(movable5);
		//panel2.add(saveButton);
		//panel2.add(quickSearch);
		//network.getToolbar().add(movable1);
		//network.getToolbar().add(movable2);
		network.getToolbar().add(eLabel);
		network.getToolbar().add(quickSearch);
		network.getToolbar().add(panel2);
		network.getToolbar().add(saveButton);
		network.getToolbar().add(clearAllButton);
		
		network.setBackground(background);

		MapUtil.createInternalFrame("ControlPane", network, createControlPane());
		if(movable5.isSelected()){
			loadLink();
			loadAccessPoint();
		}
		loadBbuPool();
		loadBbu();
		loadRru();
		loadUe();
		box.addElementPropertyChangeListener(new PropertyChangeProcessor(network));
		
		
	}
	//更新拓扑图
	public void clear(){
		box.clear();
		
		if(movable5.isSelected()){
			//先更新接入点再更新链路
			loadAccessPoint();
			loadLink();
		 }
		loadBbuPool();
		loadBbu();
		loadRru();
		loadUe();
		
	}
	public void ShowBbuLink(){
		movable1.setSelected(true);
		movable2.setSelected(true);
		movable3.setSelected(false);
		movable4.setSelected(false);
	}
	public void ShowRruLink(){
		movable1.setSelected(false);
		movable2.setSelected(true);
		movable3.setSelected(true);
		movable4.setSelected(false);
	}
	private JPanel createControlPane() {
		
		
		movable1.setSelected(true);
		movable2.setSelected(true);
		movable3.setSelected(true);
		movable4.setSelected(true);
		movable5.setSelected(true);

		JPanel controlPane = new JPanel();//(layout)
		
		quickSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				box.getSelectionModel().clearSelection();
				final String text = quickSearch.getText();
				if (text != null && !text.trim().equals("")) {
					final List elements = new ArrayList();
					box.iterator(new ElementCallbackHandler() {
						public boolean processElement(Element element) {
							String name = element.getName();
							if (name != null && network.isVisible(element) && name.toLowerCase().indexOf(text.toLowerCase()) >= 0) {
								elements.add(element);
							}
							return true;
						}
					});
					box.getSelectionModel().setSelection(elements);
				}
			}
		});
		//保存操作，主要针对位置
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "是否确定保存所有数据", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
				Connection conn = null;
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn = java.sql.DriverManager.getConnection(  
			                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
		             conn.setAutoCommit(true);
		             Statement stmt = conn.createStatement();
		             for(int i = 0;i < bbuPoolIndex;i++){
			            Point point = new Point();
						point = bbuPoolList.get(i).getCenterLocation();
						String sql = "UPDATE BbuPool SET X = "+point.x+", Y = "+point.y+" WHERE BbuPoolId = "+bbuPoolList.get(i).getId();
						stmt.executeUpdate(sql);
					}
		             for(int i = 0;i < bbuIndex;i++){
				            Point point = new Point();
							point = bbuList.get(i).getCenterLocation();
							String sql = "UPDATE Bbu SET X = "+point.x+", Y = "+point.y+" WHERE BbuId = "+bbuList.get(i).getId();
							stmt.executeUpdate(sql);
					}
		             for(int i = 0;i < rruIndex;i++){
				            Point point = new Point();
							point = rruList.get(i).getCenterLocation();
							String sql = "UPDATE Rru SET X = "+point.x+", Y = "+point.y+" WHERE RruId = "+rruList.get(i).getId();
							stmt.executeUpdate(sql);
					}
		             for(int i = 0;i < ueIndex;i++){
				            Point point = new Point();
							point = ueList.get(i).getCenterLocation();
							String sql = "UPDATE Ue SET X = "+point.x+", Y = "+point.y+" WHERE UeId = "+ueList.get(i).getId();
							stmt.executeUpdate(sql);
					}
		             for(int i = 0;i < linkBusIndex;i=i+2){
			            Point point1 = new Point();
			            Point point2 = new Point();
						point1 = LinkBusList.get(i).getCenterLocation();
						point2 = LinkBusList.get(i+1).getCenterLocation();
						String sql = "exec Update_LinkBusCircle "+ LinkBusList.get(i).getId()+" , 0 ,"+point1.x+","+point1.y+ ","+point2.x+","+point2.y;
						System.out.println(sql);
						stmt.executeUpdate(sql);
		             }
		             for(int i = 0;i < linkCircleIndex;i++){
				            Point point = new Point();
							point = LinkCircleList.get(i).getCenterLocation();
							String sql = "exec Update_LinkBusCircle "+ LinkCircleList.get(i).getName() + " , 1 ,"+point.x+","+point.y+ ",0,0";
							stmt.executeUpdate(sql);
					}
		             
				}catch(SQLException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}finally{
					//关闭Connection
					if(conn != null) {
						//关闭Connection
						if(conn != null) {
							try {
								conn.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							conn = null;
						}
					};
				}
				}
		}});
		clearAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(null, "是否确定删除所有数据", "", JOptionPane.YES_NO_OPTION);
				if(a==JOptionPane.NO_OPTION)
				{
					return;
				}
				if(a==JOptionPane.YES_OPTION)
				{
					int b=JOptionPane.showConfirmDialog(null, "再次确定是否删除所有数据", "", JOptionPane.YES_NO_OPTION);
					if(b==JOptionPane.NO_OPTION)
					{
						return;
					}
					if(b==JOptionPane.YES_OPTION)
					{
						Connection conn = null;
						try {
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							conn = java.sql.DriverManager.getConnection(  
					                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
				             conn.setAutoCommit(true);
				             Statement stmt = conn.createStatement();
				             String sql = "exec Drop_All";
							stmt.executeUpdate(sql);
						
						}catch(SQLException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
							}
					}
				}
			}});
		//节点的隐藏
		network.addVisibleFilter(new VisibleFilter() {
			public boolean isVisible(Element element) {
				if (element instanceof StateNode) {
					StateNode node = (StateNode) element;
//					return node.getAllRes() >= pSlider.getValue()*10 || node.getRes() >= pSlider.getValue()*10;
//				} else {
//					return true;
					if(movable2.isSelected()&&node.getType() == 2){
						return true;
					}
					else if(movable1.isSelected()&&node.getType() == 1){
						return true;
					}
					else if(movable1.isSelected()&&node.getType() == 6){
						return true;
					}
					else if(movable3.isSelected()&&node.getType() == 3){
						return true;
					}
					else if(movable4.isSelected()&&node.getType() == 4){
						return true;
					}
					else if(movable2.isSelected()&&movable3.isSelected()&&node.getType() == 5){
						return true;
					}
					else{
						return false;
					}
					
				}
				else{
					return true;
				}
			}
		});
		network.addMovableFilter(new MovableFilter() {
			public boolean isMovable(Element element) {
				return movable.isSelected();
			}
		});

		return controlPane;
	}
	//几个网络拓扑的读取操作
	public void loadBbuPool(){
		ResultSet rs,rs2;
		Connection conn = null;
		
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             Statement stmt2 = conn.createStatement();
             String sql = "select * from BbuPool";
             rs = stmt.executeQuery(sql);
             StateNode node;
             StateNode node2;
             bbuPoolList.clear();
             bbuPoolIndex = 0;
     		 while(rs.next()){
     			node = new StateNode();
     			node.setName("BbuPool"+rs.getString(1));
     			node.setImage("/demo/resource/images/BbuPool.png");
     			node.setSize(105, 105);
     			
     			node.setCenterLocation(rs.getDouble(2),rs.getDouble(3));
     			//node.putClientProperty("custom.draw.antialias",1);
     			bbuPoolList.add(node);
     			box.addElement(node);
     			initBbuPool("BbuPool"+rs.getString(1),1,rs.getInt(1),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getDouble(8),rs.getString(9));
     			bbuPoolIndex++;
     		}
             String sql2 = "select * from LinkBbuPool2BbuPool";
             rs2 = stmt2.executeQuery(sql2);
             List<Link> linkList = new ArrayList<Link>();
             while(rs2.next()){
            	 node = (StateNode) finder.findFirst("BbuPool"+rs2.getString(2));
            	 node2 = (StateNode) finder.findFirst("BbuPool"+rs2.getString(3));
            	 Link link = new Link(node,node2);
            	 //link.setLinkType(TWaverConst.LINK_TYPE_XSPLIT);
            	 link.putClientProperty("linkname", "link" + rs2.getInt(1));
            	
         		link.putLinkStyle(TWaverConst.LINK_STYLE_DASH);
                link.putLinkWidth(1);
                link.putLinkColor(Color.cyan.darker());
                link.putLinkFlowingColor(Color.blue);
                
            	 linkList.add(link);
            	 box.addElement(link);
     		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}
	
	public void loadBbu(){
		ResultSet rs,rs2;
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             Statement stmt2 = conn.createStatement();
             String sql = "select * from Bbu";
             bbuIndex = 0;
             bbuList.clear();
             rs = stmt.executeQuery(sql);
             StateNode node;
             StateNode node2;
             double minDis = Double.MAX_VALUE,Dis = 0;
             int it;
     		 while(rs.next()){
     			node = new StateNode();
     			node.setName("Bbu"+rs.getString(1));
     			node2 = (StateNode) finder.findFirst("BbuPool"+rs.getString(2));
     			int i = Integer.parseInt(rs.getString(2));
     			Link link = new Link(node,node2);
     			link.putLinkOutlineWidth(0);
                link.putLinkColor(Color.blue);
                link.putLinkWidth(1);
                box.addElement(link);
     			if(rs.getInt(10) == 1)
     			{
     				node.setImage("/demo/resource/images/Bbu.png");
     			}
     			else 
     			{
					node.setImage("/demo/resource/images/Bbu1.png");
				}
     			node.setSize(60, 60);
     			node.setCenterLocation(rs.getDouble(3),rs.getDouble(4));
     			//node.putClientProperty("custom.draw.antialias",1);
     			bbuList.add(node);
     			box.addElement(node);
     			initBbu("Bbu"+rs.getString(1),2,rs.getInt(1),rs.getInt(6),rs.getDouble(8),rs.getDouble(9),rs.getDouble(11),rs.getInt(2));
     			minDis = Double.MAX_VALUE;
     			Dis = 0;
     			if(movable5.isSelected()){
     				if(rs.getInt(12) > 0){
     	 				int itarator = -1;
     	     			for(it = 0;it < accessList.size();it++){
     	     				if(accessList.get(it).getCircleId() == rs.getInt(12)){
     	     					Dis = getDistance(node, accessList.get(it));
     	     					if(Dis < minDis){
     	     						minDis = Dis;
     	     						itarator = it;
     	     						}
     	     				}
     	     			}
     	     			if(itarator != -1){
     	     			System.out.println(itarator);
     	     			Link link2 = new Link(node,accessList.get(itarator));
     					
     	     			link2.putLinkOutlineWidth(0);
     	                link2.putLinkColor(Color.gray);
     	                link2.putLinkWidth(1);
     	                box.addElement(link2);
     	     			}
     	 			}
     			}
     			bbuIndex++;
     		}
             String sql2 = "select * from LinkBbu2Bbu";
             rs2 = stmt2.executeQuery(sql2);
             List<Link> linkList2 = new ArrayList<Link>();
             while(rs2.next()){
            	 node = (StateNode) finder.findFirst("Bbu"+rs2.getString(2));
            	 node2 = (StateNode) finder.findFirst("Bbu"+rs2.getString(3));
            	 Link link = new Link(node,node2);
//            	 link.setLinkType(TWaverConst.LINK_TYPE_XSPLIT);
            	 link.putClientProperty("linkname", "link" + rs2.getInt(1));
            	 //link.putLinkFlowing(true);
            	 link.putLinkOutlineWidth(0);
                 link.putLinkColor(Color.blue);
//                 if(rs2.getDouble(9) > 0.5 )
//                	 link.putLinkFlowingColor(Color.red);
//                 else
//                	 link.putLinkFlowingColor(Color.blue);
//                 link.putLinkFlowingWidth(3);
                 link.putLinkWidth(1);
               	 linkList2.add(link);
            	 box.addElement(link);
     		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}
	
	public void loadRru(){
		ResultSet rs,rs2,rs3;
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             Statement stmt2 = conn.createStatement();
             Statement stmt3 = conn.createStatement();
             String sql = "select * from Rru";
             rruIndex = 0;
             rruList.clear();
             rs = stmt.executeQuery(sql);
             StateNode node;
             StateNode node2;
     		 while(rs.next()){
     			node = new StateNode();
     			node.setName("Rru"+rs.getString(1));
     			//if(rs.getInt(2) >= 0)
   				//node2 = (StateNode) finder.findFirst("Bbu"+rs.getString(2));
     			int i = Integer.parseInt(rs.getString(2));
     			node.setImage("/demo/resource/images/Rru.png");
     			node.setSize(60, 60);
     			node.setCenterLocation(rs.getDouble(4),rs.getDouble(5));
     			//node.putClientProperty("custom.draw.antialias",1);
     			rruList.add(node);
     			box.addElement(node);
     			List<Link> linkList = new ArrayList<Link>();
     			if(movable5.isSelected() == false)
     			{
     				node2 = (StateNode) finder.findFirst("Bbu"+rs.getString(2));
     				 Link link = new Link(node,node2);
     				//            	 link.setLinkType(TWaverConst.LINK_TYPE_XSPLIT);
     				            	 link.putClientProperty("linkname", "link" + rs.getInt(1));
     				            	 //link.putLinkFlowing(true);
     				                 link.putLinkColor(Color.red);
     				//                 if(rs2.getDouble(9) > 0.5 )
     				//                	 link.putLinkFlowingColor(Color.red);
     				//                 else
     				//                	 link.putLinkFlowingColor(Color.blue);
     				                 link.putLinkOutlineWidth(0);
     				                 //link.putLinkFlowingWidth(2);
     				                 link.putLinkWidth(1);
     				               	 linkList.add(link);
     				            	 box.addElement(link);
     			}
     			initRru("Rru"+rs.getString(1),3,rs.getInt(1),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getDouble(11),rs.getDouble(13),rs.getDouble(3));
     			rruIndex++;
     		}
     		 if(movable5.isSelected()){
	             String sql2 = "select * from LinkBbu2Rru";
	             rs2 = stmt2.executeQuery(sql2);
	             List<Link> linkList2 = new ArrayList<Link>();
	             while(rs2.next()){
	            	 node = (StateNode) finder.findFirst("Bbu"+rs2.getString(2));
	            	 node2 = (StateNode) finder.findFirst("Rru"+rs2.getString(3));
	            	 Link link = new Link(node,node2);
	//            	 link.setLinkType(TWaverConst.LINK_TYPE_XSPLIT);
	            	 link.putClientProperty("linkname", "link" + rs2.getInt(1));
	            	 //link.putLinkFlowing(true);
	                 link.putLinkColor(Color.red);
	//                 if(rs2.getDouble(9) > 0.5 )
	//                	 link.putLinkFlowingColor(Color.red);
	//                 else
	//                	 link.putLinkFlowingColor(Color.blue);
	                 link.putLinkOutlineWidth(0);
	                 //link.putLinkFlowingWidth(2);
	                 link.putLinkWidth(1);
	               	 linkList2.add(link);
	            	 box.addElement(link);
	           
	     		}
	             String sql3 = "select * from LinkAccess2Rru";
	             rs3 = stmt3.executeQuery(sql3);
	             while(rs3.next()){
	            	 node = (StateNode) finder.findFirst("AccessPoint"+rs3.getString(2));
	            	 node2 = (StateNode) finder.findFirst("Rru"+rs3.getString(3));
	            	 List<Link> linkList3 = new ArrayList<Link>();
	            	 Link link = new Link(node,node2);
	//            	 link.setLinkType(TWaverConst.LINK_TYPE_XSPLIT);
	            	 link.putClientProperty("linkname", "link" + rs3.getInt(1));
	            	 //link.putLinkFlowing(true);
	                 link.putLinkColor(Color.red);
	//                 if(rs2.getDouble(9) > 0.5 )
	//                	 link.putLinkFlowingColor(Color.red);
	//                 else
	//                	 link.putLinkFlowingColor(Color.blue);
	                 link.putLinkOutlineWidth(0);
	                 //link.putLinkFlowingWidth(2);
	                 link.putLinkWidth(1);
	               	 linkList3.add(link);
	            	 box.addElement(link);
	           
	     		}
     		 }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}
	
	public void loadUe(){
		ResultSet rs;
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             Statement stmt2 = conn.createStatement();
             String sql = "select * from Ue";
             ueList.clear();
             ueIndex= 0;
             rs = stmt.executeQuery(sql);
             StateNode node;
             StateNode node2;
     		 while(rs.next()){
     			node = new StateNode();
     			node.setName("Ue"+rs.getString(1));
     			if(rs.getInt(6)!= -1){
   				node2 = (StateNode) finder.findFirst("Rru"+rs.getString(6));
     			Link link = new Link(node,node2);
     			link.putLinkOutlineWidth(0);
     			link.putLinkWidth(0);
     		//	link.putClientProperty("lineWidth", 0.05f);
     			link.putLinkColor(Color.black);
     			box.addElement(link);
     			}
     			node.setImage("/demo/resource/images/Ue.png");
     			node.setSize(15, 15);
     			node.setCenterLocation(rs.getDouble(3),rs.getDouble(4));
     			//node.putClientProperty("custom.draw.antialias",1);
     			ueList.add(node);
     			if(rs.getInt(6)!= -1 || rs.getInt(21) != -1){
     			box.addElement(node);
     			initUe("Ue"+rs.getString(1),4,rs.getInt(1),rs.getInt(2),rs.getInt(7),rs.getInt(8));
     			}
     			ueIndex++;
     		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}
	
	public void loadAccessPoint(){
		ResultSet rs;
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             String sql = "select * from AccessPoint";
             rs = stmt.executeQuery(sql);
             accessIndex = 0;
             accessList.clear();
             StateNode node;
     		 while(rs.next()){
					node = new StateNode();
    				node.setName("AccessPoint"+rs.getString(1));
    				 
    				node.setImage("/demo/resource/images/AccessPoint.png");
        			node.setSize(2, 2);
        			node.setCenterLocation(rs.getDouble(3),rs.getDouble(4));
        			//node.putClientProperty("custom.draw.antialias",1);
        			accessList.add(node);
        			accessIndex++;
        			box.addElement(node);
        			initAccessPoint("AccessPoint"+rs.getString(1),5,rs.getInt(2),rs.getInt(1));
         			
     		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}
	
	public void loadLink(){
		ResultSet rs;
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, passWord);
             conn.setAutoCommit(false);
             Statement stmt = conn.createStatement();
             String sql = "select * from Link";
             rs = stmt.executeQuery(sql);
             LinkCircleList.clear();
             linkCircleIndex = 0;
             LinkBusList.clear();
             linkBusIndex = 0;
     		 while(rs.next()){
     			 System.out.println(rs.getInt(2));
     			 if(rs.getInt(2) == 1){
        			 ResizableNode shape = new ResizableNode();
        		     shape.setSize(2*rs.getInt(10),2*rs.getInt(10));
        		     shape.putCustomDraw(true);
        		     shape.putCustomDrawFill(false);
        		     shape.putCustomDrawOutlineColor(Color.gray);
        		     shape.putCustomDrawShapeFactory(TWaverConst.SHAPE_CIRCLE);
        		     shape.setLocation(rs.getInt(3)-rs.getInt(10), rs.getInt(4)-rs.getInt(10));
        		     shape.setName(rs.getString(1));
        		     shape.putLabelFont(TWaverUtil.getFont(Font.BOLD, 0));
        		     LinkCircleList.add(shape);
        		     linkCircleIndex++;
        		     
        		     box.addElement(shape);
     			 }
     			 else if(rs.getInt(2) == 0){
     				
     				StateNode node1 = new StateNode();
     				StateNode node2 = new StateNode();
     				
     				node1.setLocation(rs.getDouble(3),rs.getDouble(4));
     				node1.setImage("/demo/resource/images/link.png");
     				node1.setSize(1, 1);
     				node1.setName("LinkBusFrom"+rs.getString(1));
     				LinkBusList.add(node1);
     				linkBusIndex++;
     				box.addElement(node1);
     				initLinkBus("LinkBusFrom"+rs.getString(1), rs.getInt(1), 6);
     				
     				node2.setLocation(rs.getDouble(6),rs.getDouble(7));
     				node2.setSize(1, 1);
     				node2.setName("LinkBusTo"+rs.getString(1));
     				LinkBusList.add(node2);
     			//	linkBusIndex++;
     				node2.setImage("/demo/resource/images/link.png");
     				box.addElement(node2);
     				initLinkBus("LinkBusTo"+rs.getString(1), rs.getInt(1), 6);
     				
     				Link link = new Link(node1,node2);
     				
         			link.putLinkOutlineWidth(0);
         			link.putLinkWidth(1);
         		//	link.putClientProperty("lineWidth", 0.05f);
         			link.putLinkColor(Color.black);
         			box.addElement(link);
     			 }
     			
     		}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
			} catch (SQLException e) {
				e.printStackTrace();
			}  finally{
				//关闭Connection
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}

	}

	public double getDistance(StateNode node,StateNode node2){
		double dis = Math.sqrt(Math.pow((node.getX()-node2.getX()),2.0) + Math.pow((node.getY()-node2.getY()),2.0));
		return dis;
	}
	
//	private JSlider createZoomSlider() {
//		final JSlider slider = new JSlider();
//		slider.setMaximum(500);
//		slider.setMinimum(0);
//		slider.setMajorTickSpacing(100);
//		slider.setPaintLabels(true);
//		slider.setPaintTicks(true);
//		slider.setOpaque(false);
//		int max = slider.getMaximum();
//		int min = slider.getMinimum();
//		int gap = slider.getMajorTickSpacing();
//		int size = (max - min) / gap;
//		Hashtable list = new Hashtable();
//		for (int i = 0; i < size + 1; i++) {
//			JLabel label = new JLabel(i * 100 + "%");
//			label.setFont(new Font("Dialog", Font.ITALIC, 9));
//			list.put(new Integer(i * 100), label);
//		}
//		slider.setLabelTable(list);
//		slider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				network.getZoomer().setZoom(slider.getValue() / 100.0);
//			}
//		});
//		slider.setValue((int) (network.getZoom() * 100));
//		return slider;
//	}
	
	private void initBbuPool(String stateName, int p3, int p4, double p5, double p6, double p7, double p8,String p9) {
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.putChartColor(Color.ORANGE);
		node.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
		node.initBbuPool(p3, p4, p5, p6, p7, p8,p9);
	}
	private void initBbu(String stateName, int p3, int p4, int p5, double p6, double p7, double p8,int p9) {
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.putChartColor(Color.ORANGE);
		node.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
		node.initBbu(p3, p4, p5, p6, p7, p8,p9);
	}
	private void initRru(String stateName, int p2,int p3, int p4, int p5, int p6, double p7, double p8,double p9) {
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.putChartColor(Color.ORANGE);
		node.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
		node.initRru(p2, p3, p4, p5, p6, p7, p8,p9);
	}
	private void initUe(String stateName, int p2,int p3, int p4, int p5,int p6) {
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.putChartColor(Color.ORANGE);
		node.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
		node.initUe(p2, p3, p4, p5, p6);
	}
	private void initAccessPoint(String stateName, int p1,int p2,int p3) {
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.putChartColor(Color.ORANGE);
		node.putChartInflexionStyle(TWaverConst.INFLEXION_STYLE_CIRCLE);
		node.initAccessPoint(p1, p2, p3);
	}
	private void initLinkBus(String stateName,int p1,int p2){
		StateNode node = (StateNode) finder.findFirst(stateName);
		node.initLinkBus(p1, p2);
	}


}