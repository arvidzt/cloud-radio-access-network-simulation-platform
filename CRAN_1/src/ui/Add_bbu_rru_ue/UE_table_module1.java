/*
 * 类名：UE_table_module1
 * 功能：此类是用来在Panel中显示查询数据库后当前UE群的表格情况的模板
 */
package ui.Add_bbu_rru_ue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.*;

import ui.Share_bag.BBU_sql;
import ui.CRAN_excel.Data_operation;
public class UE_table_module1 extends AbstractTableModel{
	//rowData用来存放行数据 
	//columNames用来存放列名
	Vector rowData,columNames;
	Vector hang;
	JTextField jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8;
	UE_table_module1 uegroup_table;
//	int cannot_modify_row;
	int search_rownum;
	/***************构造UE群表格，列名******************/
	public  UE_table_module1()
	{
		columNames=new Vector<>();
		//设置列名
		//columNames.add("");复选框预留列
		columNames.add("UE群ID");
		columNames.add("UE个数");	
		columNames.add("中心X坐标(m)");
		columNames.add("中心Y坐标(m)");
		columNames.add("分布方式");
		columNames.add("区域半径(m)");
		columNames.add("室内外信息");
		columNames.add("天线类型");
		rowData=new Vector<>();
	}
	/***************表格用的显示每行对应数据库参数的函数******************/
	public void table_search(String sql,String[]paras)
	{
		BBU_sql sqlh=null;
			try {
				sqlh=new BBU_sql();
				ResultSet rs=sqlh.search(sql, paras);
				while(rs.next())
				{
					//rowData可以存放多行
					Vector hang=new Vector<>();
					hang.add(rs.getString(1));//UE群 ID0
					hang.add(rs.getString(8));//UE个数
					hang.add(rs.getString(3));//中心X坐标1
					hang.add(rs.getString(4));//中心Y坐标2
					hang.add("随机分布");
					hang.add(rs.getString(2));//R
					if(rs.getString(6)==null)
					{
						hang.add(rs.getString(6));//室内外信息4，1：室内，0：室外
					}else{
						if(rs.getString(6).equals("0"))//室内外信息4，1：室内，0：室外
						{
							hang.add("室外");
						}else if(rs.getString(6).equals("1"))
						{
							hang.add("室内");
						}
					}
					hang.add(rs.getString(7));//天线类型5
					//加入到rowData
					rowData.add(hang);
					
				}
				search_rownum=this.getRowCount();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally
			{
				//关闭
				sqlh.close();
			}	
		}

	//清空表格
	public void  clean_all() {
		try {
			rowData.clear();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/***************得到表格列数******************/
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columNames.size();
	}
	/***************得到表格行数 ******************/
	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}
	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}
	/**************设置表格数据是否可以修改 ******************/	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		if(rowIndex<search_rownum)
		{
			return false;
		}else
		{
			return true;
		}
		
	}
	/**************设置表格第几行第几列数值 ******************/
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		 ((Vector) rowData.get(rowIndex)).remove(columnIndex);
		  ((Vector) rowData.get(rowIndex)).add(columnIndex, aValue);
		  this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	/**************返回表格第几行第几列数值 ******************/
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);//不转成string也没有关系
	}
	/**************表格添加一行 ******************/
	public void addRows(int uegroup_add_rownum) {
		String sql="exec RowMax ?";
		String []paras={"UeGroup"};
		uegroup_table=new UE_table_module1();
		ResultSet Uegroup_max_id=uegroup_table.total_num_sql(sql, paras);//ue_max_id返回bbu当前最大id号
		
		Vector hang=new Vector<>();
		try {
			Uegroup_max_id.next();
//			if(Uegroup_max_id.getString(1)==null)
//			{
//				uegroup_id=-1+uegroup_add_rownum;
//			}else
//			{
				int temp=Uegroup_max_id.getInt(1)+uegroup_add_rownum;
				String uegroup_id=Integer.toString(temp);
//			}
			hang.add(uegroup_id);//UE的编号
		} catch (SQLException e) {
			e.printStackTrace();
		}
		hang.add("10");//UE个数			
		hang.add(jtf3);//X坐标
		hang.add(jtf4);//Y坐标
		hang.add("随机分布");//分布方式
		hang.add(jtf6);//区域半径
		hang.add(jtf7);//室内外信息
		hang.add(jtf8);//天线类型
		//加入到rowData
		rowData.add(hang);
		
	}


	/**************对表格进行添加 删除 修改操作******************/
	public boolean add_delete_change_to_sql(String sql,String []paras) {
		// TODO 自动生成的方法存根
		BBU_sql sqlh=new BBU_sql();
		return sqlh.add_delete_change(sql, paras);
	}
	/**************此函数返回数据库每个表格所有数据总行数，目的是得到参数总数、、自动生成ID ******************/
	public ResultSet total_num_sql(String sql,String []paras)//此函数返回数据库每个表格所有数据总行数，目的是得到参数总数、、自动生成ID
	{
		BBU_sql sqlh=new BBU_sql();
		return sqlh.search(sql, paras);
	}

	
	
}
