package ui.CRAN1;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ui.Share_bag.BBU_sql;
public class Export_excel {
	public static final String[] tableHeaderBbu = 
		{"BbuId","BbuPoolId","X","Y","Z","RruNum","SchedualRruMode","TransPower","EquipPower","IsActivity","Res","LinkId","Optime"};
	public static final String[] tableHeaderRru =
		{"RruId","ServiceBbuId","Radius","X","Y","Z","RruTransPower","RruBandwidth","UeNum","IsActivity","CarrierFrequent","RruAntennaId","EquipPower","Optime","Rate"};
	public static final String[] tableHeaderUe =
		{"UeId","UeType","X","Y","Z","ServiceRruId","RbNum","UeTransPower","UeAntennaId","IsActivity","UeGroupId","ModelType","SNR","TotalBit","TTISent","AverageRate"};
	public static final String[] tableHeaderBbuPool =
		{"BbuPoolId","X","Y","Z","AllRes","ResLeft","DynamicEnengy","StaticEnengy","BbuPoolInfo"};
	public static final String[] tableHeaderAntenna =
		{"AntennaId","AngleCoverages","M","N","DisHori","DisVert","AntennaMode","VertGain","HoriGain","RadiationLobe","TiltAngle"};
	public static final String[] tableHeaderLink = 
		{"LinkPathId","LinkEnd1","LinkEnd2","RealLength","LinkType","MaxTransRate","Attenuation","Delay","ErrorRate","Cost","LinkCircleId","FibersNum"};
	public static final String[] tableHeaderCircle =
		{"LinkId","LinkType","X1","Y1","Z1","X2","Y2","Z2","LongRadius","ShortRadius","AccesspointNum","Cost"};
	public static final String[] tableHeadercase=
		{"caseName","caseRemark"};
	//创建工作本
	//创建表
	static String nameString = BBU_sql.name;
	static String pswdString = BBU_sql.pswd;

	public static void Export(String tableName,String Path) throws Exception {
		//------连接数据库-------
		Connection conn = null;
		try {
			//反射机制，创建数据库Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//System.out.println("数据库连接成功");
			conn = java.sql.DriverManager.getConnection(  
	                "jdbc:sqlserver://localhost:1433; DatabaseName=C-RAN", nameString, pswdString); //数据库database--test,username--root，password--root 
			conn.setAutoCommit(false); 
			Statement stmt = conn.createStatement();
            String sql =  "select * from "+tableName ;
            
            String[]  Header = {};
            String outputFile=Path;//"D:/game/BbuPool.xls"
            HSSFSheet demoSheet = null;
            switch(tableName){
            case "BbuPool":
            	short cellNumber1 = (short)tableHeaderBbuPool.length;
            	int columNumber1 = tableHeaderBbuPool.length;
            	Header = tableHeaderBbuPool;
            	HSSFWorkbook demoWorkBook = new HSSFWorkbook();
            	demoSheet = demoWorkBook.createSheet("BbuPool");
                ResultSet rs1 = stmt.executeQuery(sql); 
                createExcelSheet(rs1,cellNumber1,columNumber1,Header,outputFile,demoSheet,demoWorkBook);
                rs1.close();
                break;
            case "Bbu":
            	short cellNumber2 = (short)tableHeaderBbu.length;
            	int columNumber2 = tableHeaderBbu.length;
            	Header = tableHeaderBbu;
            	HSSFWorkbook demoWorkBook2 = new HSSFWorkbook();
            	demoSheet = demoWorkBook2.createSheet("Bbu");
                ResultSet rs2 = stmt.executeQuery(sql); 
                createExcelSheet(rs2,cellNumber2,columNumber2,Header,outputFile,demoSheet,demoWorkBook2);
                rs2.close();
                break;
            case "Rru":
            	short cellNumber3 = (short)tableHeaderRru.length;
            	int columNumber3 = tableHeaderRru.length;
            	Header = tableHeaderRru;
            	HSSFWorkbook demoWorkBook3 = new HSSFWorkbook();
            	demoSheet = demoWorkBook3.createSheet("Rru");
                ResultSet rs3 = stmt.executeQuery(sql); 
                createExcelSheet(rs3,cellNumber3,columNumber3,Header,outputFile,demoSheet,demoWorkBook3);
                rs3.close();
                break;
            case "Ue":
            	short cellNumber4 = (short)tableHeaderUe.length;
            	int columNumber4 = tableHeaderUe.length;
            	Header = tableHeaderUe;
            	HSSFWorkbook demoWorkBook4 = new HSSFWorkbook();
            	demoSheet = demoWorkBook4.createSheet("Ue");
                ResultSet rs4 = stmt.executeQuery(sql); 
                createExcelSheet(rs4,cellNumber4,columNumber4,Header,outputFile,demoSheet,demoWorkBook4);
                rs4.close();
                break;
            case "Antenna":
            	short cellNumber5 = (short)tableHeaderAntenna.length;
            	int columNumber5 = tableHeaderAntenna.length;
            	Header = tableHeaderAntenna;
            	HSSFWorkbook demoWorkBook5 = new HSSFWorkbook();
            	demoSheet = demoWorkBook5.createSheet("Antenna");
                ResultSet rs5 = stmt.executeQuery(sql); 
                createExcelSheet(rs5,cellNumber5,columNumber5,Header,outputFile,demoSheet,demoWorkBook5);
                rs5.close();
                break;
            case "LinkBbuPool2BbuPool":
            case "LinkBbu2Bbu":
            case "LinkBbu2Rru":
            	short cellNumber6 = (short)tableHeaderLink.length;
            	int columNumber6 = tableHeaderLink.length;
            	Header = tableHeaderLink;
            	HSSFWorkbook demoWorkBook6 = new HSSFWorkbook();
            	demoSheet = demoWorkBook6.createSheet(tableName);
                ResultSet rs6 = stmt.executeQuery(sql); 
                createExcelSheet(rs6,cellNumber6,columNumber6,Header,outputFile,demoSheet,demoWorkBook6);
                rs6.close();
                break;
            case "Link":
            	short cellNumber7 = (short)tableHeaderCircle.length;
            	int columNumber7 = tableHeaderCircle.length;
            	Header = tableHeaderCircle;
            	HSSFWorkbook demoWorkBook7 = new HSSFWorkbook();
            	demoSheet = demoWorkBook7.createSheet(tableName);
                ResultSet rs7 = stmt.executeQuery(sql); 
                createExcelSheet(rs7,cellNumber7,columNumber7,Header,outputFile,demoSheet,demoWorkBook7);
                rs7.close();
                break;
            case "PCase":
            	short cellNumber8= (short)tableHeadercase.length;
            	int columNumber8 = tableHeadercase.length;
            	Header = tableHeadercase;
            	HSSFWorkbook demoWorkBook8 = new HSSFWorkbook();
            	demoSheet = demoWorkBook8.createSheet("PCase");
                ResultSet rs8 = stmt.executeQuery(sql); 
                createExcelSheet(rs8,cellNumber8,columNumber8,Header,outputFile,demoSheet,demoWorkBook8);
                rs8.close();
                break;
            default:
            	System.out.println("Wrong input!!");
            }
        JOptionPane.showMessageDialog(null,"数据库导出成功!");
        conn.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"数据库导出失败");
			return;// 也许是return null;具体看你该函数的返回类型咯！
		}  finally{
			//关闭Connection
			
			if(conn != null) {
				conn.close();
			}
		}
		
//		//-----结束时间------
//		Long endTime = System.currentTimeMillis(); 
//		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}
	

	
	public static void createTableHeader(short cellNumber,String[] Header,HSSFSheet demoSheet){
		HSSFRow headerRow = demoSheet.createRow((short) 0);
		for(int i = 0;i < cellNumber;i++){
			HSSFCell headerCell = headerRow.createCell((short) i);
			headerCell.setCellValue(new HSSFRichTextString(Header[i]));
		}
	}
	

	public static void createTableRow(List<String> cells,short rowIndex,String[] Header,HSSFSheet demoSheet){
		//创建第rowIndex行
		HSSFRow row = demoSheet.createRow((short) rowIndex);
		row.setHeight((short) (15.625*20));
		System.out.println("<"+cells.size());
		for(short i = 0;i < cells.size();i++)
		{
			System.out.println(i);
			//创建第i个单元格
			HSSFCell cell = row.createCell((short) i);
        	if(Header[i] == "BbuPoolInfo")
        		cell.setCellValue(new HSSFRichTextString(cells.get(i)));
        	else if(Header[i] == "caseRemark")
        		cell.setCellValue(new HSSFRichTextString(cells.get(i)));//识别text格式
        	else if(Header[i] == "caseName")
        		cell.setCellValue(new HSSFRichTextString(cells.get(i)));
        	else if(i == 0)
        		cell.setCellValue(Integer.parseInt(cells.get(i)));
        	else if(cells.get(i) != null){
        		//System.out.println(cells.get(i));
        		cell.setCellValue(Float.parseFloat(cells.get(i)));
        		
        	}
        	else
        		cell.setCellValue(0);
		}
	}
	
	/**
	 * 创建整个Excel表
	 * @throws SQLException 
	 *
	 */
	public static void createExcelSheet(ResultSet rs,short cellNumber,int columNumber,String[] Header,String outputFile,HSSFSheet demoSheet,HSSFWorkbook demoWorkBook) throws SQLException, IOException{
		createTableHeader(cellNumber,Header,demoSheet);
		
		int rowIndex = 1;
		while(rs.next()){
			List<String> list = new ArrayList<String>();
			for(int i = 1;i <= columNumber;i++)
			{
				list.add(rs.getString(i));
			}
			createTableRow(list,(short)rowIndex,Header,demoSheet);
			rowIndex++;
		}
		expord(outputFile,demoSheet,demoWorkBook);
	}
	public static void expord(String outputFile,HSSFSheet demoSheet,HSSFWorkbook demoWorkBook) throws IOException{
		demoSheet.setGridsPrinted(true);
        HSSFFooter footer = demoSheet.getFooter();
        footer.setRight("Page " + HSSFFooter.page() + " of " +
        HSSFFooter.numPages());
        FileOutputStream os = new FileOutputStream(outputFile);
        demoWorkBook.write(os);
        os.close();
	}
	
}
