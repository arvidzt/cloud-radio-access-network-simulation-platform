package ui.CRAN_excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import ui.Share_bag.BBU_sql;

public class ExcelOperate {
	private static String name = BBU_sql.name;
	private static String pswd = BBU_sql.pswd;
	public static void LinkCircleImport(String PathLinkCircle) throws Exception {
		//------连接数据库-------
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
			pst = (PreparedStatement) conn.prepareStatement("insert into Link (LinkId,LinkType,X1,Y1,Z1,X2,Y2,Z2,LongRadius,ShortRadius,AccesspointNum,Cost) values (?,?,?,?,?,?,?,?,?,?,?,?)" );
			
			
			File file = new File(PathLinkCircle);//"D:/game/new.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);
						pst.setString(2, result[i][1]);
						pst.setString(3, result[i][2]);
						pst.setString(4, result[i][3]);
						pst.setString(5, result[i][4]);
						pst.setString(6, result[i][5]);
						pst.setString(7, result[i][6]);
						pst.setString(8, result[i][7]);
						pst.setString(9, result[i][8]);
						pst.setString(10, result[i][9]);
						pst.setString(11, result[i][10]);
						pst.setString(12, result[i][11]);
						pst.addBatch(); //事务整体添加
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (Exception e) {
			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复");
			return;// 也许是return null;具体看你该函数的返回类型咯！
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
	}
	/**zt
	 * Link批量导入
	 */
	public static void LinkImport(String tableName,String PathLink) throws Exception {
		//------连接数据库-------
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
			pst = (PreparedStatement) conn.prepareStatement("insert into "+tableName+"(LinkPathId,LinkEnd1,LinkEnd2,RealLength,LinkType,MaxTransRate,Attenuation,Delay,ErrorRate,Cost,LinkCircleId,FibersNum) values (?,?,?,?,?,?,?,?,?,?,?,?)" );
			
			
			File file = new File(PathLink);//"D:/game/new.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);
						pst.setString(2, result[i][1]);
						pst.setString(3, result[i][2]);
						pst.setString(4, result[i][3]);
						pst.setString(5, result[i][4]);
						pst.setString(6, result[i][5]);
						pst.setString(7, result[i][6]);
						pst.setString(8, result[i][7]);
						pst.setString(9, result[i][8]);
						pst.setString(10, result[i][9]);
						pst.setString(11, result[i][10]);
						pst.setString(12, result[i][11]);
						pst.addBatch(); //事务整体添加
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (Exception e) {
			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复");
			return;// 也许是return null;具体看你该函数的返回类型咯！
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
	}
	/**zt
	 * Bbu批量导入
	 */
	public static void BbuImport(String Pathbbu) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
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
			pst = (PreparedStatement) conn.prepareStatement("insert into Bbu(BbuId, BbuPoolId,X,Y,Z,RruNum,SchedualRruMode,TransPower,EquipPower,IsActivity,Res,LinkId,Optime) values (?,?,?,?,?,?,?,?,?,?,?,?,?)" );
			
			
			File file = new File(Pathbbu);
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//BbuId
						pst.setString(2, result[i][1]);//BbuPoolId
						pst.setString(3, result[i][2]);//x
						pst.setString(4, result[i][3]);//y
						pst.setString(5, result[i][4]);//z
						pst.setString(6, result[i][5]);//RruNum
						pst.setString(7, result[i][6]);//srm
						pst.setString(8, result[i][7]);//transpower
						pst.setString(9, result[i][8]);//ep
						pst.setString(10, result[i][9]);//ia
						pst.setString(11, result[i][10]);//res
						pst.setString(12, result[i][11]);//lk
						pst.setString(13, result[i][12]);//op
//						pst.setString(14, result[i][13]);//Eff
//						pst.setString(15, result[i][14]);//res
//						pst.setString(16, result[i][15]);//cons
						pst.addBatch(); //事务整体添加
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (Exception e) {
			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"数据库导入失败");
			return;// 也许是return null;具体看你该函数的返回类型咯！
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
//		//-----结束时间------
//		Long endTime = System.currentTimeMillis(); 
//		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}
	/**
	 * Bbu池批量导入
	 */
	public static void BbuPoolImport(String PathBbuPool) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库连接成功");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, pswd); //数据库database--test,username--root，password--root 
			conn.setAutoCommit(false); 
			//Dictionary_Geology -- 表名称 
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO BbuPool(BbuPoolId,X,Y,Z,AllRes,ResLeft,DynamicEnengy,StaticEnengy,BbuPoolInfo)VALUES(?,?,?,?,?,?,?,?,?)" );
			File file = new File(PathBbuPool);//"D:/game/BbuPool.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//BbuPoolId
						pst.setString(2, result[i][1]);//X
						pst.setString(3, result[i][2]);//Y
						pst.setString(4, result[i][3]);//Z
						pst.setString(5, result[i][4]);//AllRes
						pst.setString(6, result[i][5]);//ResLeft
						pst.setString(7, result[i][6]);//DynamicEnengy
						pst.setString(8, result[i][7]);//StaticEnengy
						pst.setString(9, result[i][8]);//BbuPoolInfo
						pst.addBatch(); //事务整体添加
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复");
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
//		//-----结束时间------
//		Long endTime = System.currentTimeMillis(); 
//		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}
	/**
	 * Rru批量导入
	 */
	public static void RruImport(String Pathrru) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
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
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO Rru(RruId,ServiceBbuId,Radius,X,Y,Z,RruTransPower,RruBandwidth,UeNum,IsActivity,CarrierFrequent,RruAntennaId,EquipPower,Optime,Rate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
			File file = new File(Pathrru);//"D:/game/Rru.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//RruId
						pst.setString(2, result[i][1]);//ServiceBbuId
						pst.setString(3, result[i][2]);//Radius
						pst.setString(4, result[i][3]);//X
						pst.setString(5, result[i][4]);//Y
						pst.setString(6, result[i][5]);//Z
						pst.setString(7, result[i][6]);//RruTransPower
						pst.setString(8, result[i][7]);//RruBandWith
						pst.setString(9, result[i][8]);//UeNum
						pst.setString(10, result[i][9]);//IsActivity
						pst.setString(11, result[i][10]);//CarrierFrequent
						pst.setString(12, result[i][11]);//RruAntennaId
						pst.setString(13, result[i][12]);//EquipPower
						pst.setString(14, result[i][13]);//op
						pst.setString(15, result[i][14]);//rate
						pst.addBatch(); //事务整体添加
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (Exception e) {
			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复");
			return;// 也许是return null;具体看你该函数的返回类型咯！
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
//		
//		//-----结束时间------
//		Long endTime = System.currentTimeMillis(); 
//		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}
	
	/**
	 * Ue批量导入
	 */
	public static void UeImport(String Pathue) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库连接成功");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, pswd); //数据库database--test,username--root，password--root 
			conn.setAutoCommit(false); 
			//Dictionary_Geology -- 表名称 
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO Ue(UeId,UeType,X,Y,Z,ServiceRruId,RbNum,UeTransPower,UeAntennaId,IsActivity,UeGroupId,ModelType,SNR,TotalBit,TTISent,AverageRate)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
			File file = new File(Pathue);//"D:/game/Ue.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//UeId
						pst.setString(2, result[i][1]);//UeType
						pst.setString(3, result[i][2]);//X
						pst.setString(4, result[i][3]);//Y
						pst.setString(5, result[i][4]);//Z
						pst.setString(6, result[i][5]);//ServiceRruId
						pst.setString(7, result[i][6]);//RbNum
						pst.setString(8, result[i][7]);//UeTransPower
						pst.setString(9, result[i][8]);//UeAntennaId
						pst.setString(10, result[i][9]);//IsActivity
						pst.setString(11, result[i][10]);//UeGroupId
						pst.setString(12, result[i][11]);//ModelType
						pst.setString(13, result[i][12]);//SNR
						pst.setString(14, result[i][13]);//TotalBit
						pst.setString(15, result[i][14]);//TTIsent
						pst.setString(16, result[i][15]);//AverageRate
						pst.addBatch(); //事务整体添加
						//System.out.println( result[i][10]);
						
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复",,JOptionPane.ERROR_MESSAGE);
			return;// 也许是return null;具体看你该函数的返回类型咯！
		}finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
//		//-----结束时间------
//		Long endTime = System.currentTimeMillis(); 
//		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}
	/**
	 * 天线批量导入
	 */
	
	public static void EstBusinessImport(String PathEstBusiness) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库连接成功");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, pswd); //数据库database--test,username--root，password--root 
			conn.setAutoCommit(false); 
			//Dictionary_Geology -- 表名称 
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO EstBusiness(TTI,RruId,Business)VALUES(?,?,?)" );
			File file = new File(PathEstBusiness);//"D:/game/Antenna.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//AntennaId
						pst.setString(2, result[i][1]);//AngleCoverages
						pst.setString(3, result[i][2]);//m
						pst.addBatch(); //事务整体添加
						//System.out.println( result[i][10]);
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
			System.out.println("数据库连接失败");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
				
			}
		}
	}
	
	public static void AntennaImport(String PathAntenna) throws Exception {
		//-----设置开始时间以及时间格式----
		//------连接数据库-------
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库连接成功");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", name, pswd); //数据库database--test,username--root，password--root 
			conn.setAutoCommit(false); 
			//Dictionary_Geology -- 表名称 
			pst = (PreparedStatement) conn.prepareStatement("INSERT INTO Antenna(AntennaId,AngleCoverages,M,N,DisHori,DisVert,AntennaMode,VertGain,HoriGain,RadiationLobe,TiltAngle)VALUES(?,?,?,?,?,?,?,?,?,?,?)" );
			File file = new File(PathAntenna);//"D:/game/Antenna.xls"
			String[][] result = getData(file, 1);
			int rowLength = result.length;
			for (int i = 0; i < rowLength; i++) {
						//根据数据库表字段的不同可以做修改
						pst.setString(1, result[i][0]);//AntennaId
						pst.setString(2, result[i][1]);//AngleCoverages
						pst.setString(3, result[i][2]);//m
						pst.setString(4, result[i][3]);//n
						pst.setString(5, result[i][4]);//DisHori
						pst.setString(6, result[i][5]);//DisVert
						pst.setString(7, result[i][6]);//AntennaMode
						pst.setString(8, result[i][7]);//VertGain
						pst.setString(9, result[i][8]);//HoriGain
						pst.setString(10, result[i][9]);//RadiationLobe
						pst.setString(11, result[i][10]);//TiltAngle
						pst.addBatch(); //事务整体添加
						//System.out.println( result[i][10]);
			}
			//事务整体提交
			pst.executeBatch(); 
			conn.commit(); 
			//System.out.println("数据库写入成功"); 
			JOptionPane.showMessageDialog(null,"数据库导入成功!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
			System.out.println("数据库连接失败");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复","ERROR",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
				
			}
		}
	}
		/**
		 * case批量导入
		 */
		public static void caseImport(String Pathcase) throws Exception {
			//-----设置开始时间以及时间格式----
			//------连接数据库-------
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
				pst = (PreparedStatement) conn.prepareStatement("INSERT INTO PCase(caseName,caseRemark)VALUES(?,?)" );
				File file = new File(Pathcase);//"D:/game/case.xls"
				if(file.exists())//如果文件case.xls实例表格存在,因为新注册的用户不存在实例表格。
				{
					String[][] result = getData(file, 1);
					int rowLength = result.length;
					for (int i = 0; i < rowLength; i++) {
								//根据数据库表字段的不同可以做修改
								pst.setString(1, result[i][0]);//caseName
								pst.setString(2, result[i][1]);//caseRemark
								pst.addBatch(); //事务整体添加
					}
					//事务整体提交
					pst.executeBatch(); 
					conn.commit(); 
					JOptionPane.showMessageDialog(null,"数据库导入成功!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"数据库导入失败，ID重复");
				return;// 也许是return null;具体看你该函数的返回类型咯！
			} finally{
				//关闭PreparedStatement
				if(pst != null) {
					pst.close();
					pst = null;
				}
				//关闭Connection
				if(conn != null) {
					conn.close();
					pst = null;
				}
			}	
	}
	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static String[][] getData(File file, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			
			// 第一行为标题，不取
			System.out.println("<=lastrow"+st.getLastRowNum()); 
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getPhysicalNumberOfCells();
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				//System.out.println(rowSize); 
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				//System.out.println("rowindex"+rowIndex); 
				System.out.println("<lastcolumn"+row.getPhysicalNumberOfCells()); 
				for (short columnIndex = 0; columnIndex < row.getPhysicalNumberOfCells(); columnIndex++) {
					//System.out.println(columnIndex); 
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// 注意：一定要设成这个，否则可能会出现乱码
						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
					//System.out.println(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}
