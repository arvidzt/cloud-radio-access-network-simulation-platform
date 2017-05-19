/*
 * 类名：Bbu_table_module
 * 功能：此类是用来在Panel中显示查询数据库后当前BBU的表格情况的模板
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

import ui.Share_bag.*;
import ui.CRAN_excel.*;
public class Bbu_table_module extends AbstractTableModel{
	//rowData用来存放行数据 
	//columNames用来存放列名
	Vector rowData,columNames;
	Vector hang;
	JTextField jtf1,jtf5,jtf6;
	int search_rownum;
	Bbu_table_module bbu_table;
	/***************构造BBU表格，列名******************/	
	public Bbu_table_module()
	{
		columNames=new Vector<>();
		//设置列名
		//columNames.add("");复选框预留列
		columNames.add("BBU ID");
		columNames.add("X坐标(m)");
		columNames.add("Y坐标(m)");
		columNames.add("Z坐标(m)");
		columNames.add("传输功率(dBm)");
		columNames.add("资源量");
		columNames.add("RRU的调度方式");
		columNames.add("接入环");
		rowData=new Vector<>();
	}
	/***************查询表格用的显示每行对应数据库参数的函数******************/
	public void bbu_table_search(String sql,String[]paras)
	{
		BBU_sql sqlh=null;
			try {
				sqlh=new BBU_sql();
				ResultSet rs=sqlh.search(sql, paras);
				while(rs.next())
				{
					//rowData可以存放多行
					Vector hang=new Vector<>();
					hang.add(rs.getString(1));//BBU的编号
					hang.add(rs.getString(3));//X坐标
					hang.add(rs.getString(4));//Y坐标
					hang.add(rs.getString(5));//Z坐标
					hang.add(rs.getString(8));//传输功率dbm形式
					hang.add(rs.getString(11));//资源量
					if(rs.getString(7)==null)
					{
						hang.add(rs.getString(7));//RRU资源调度的方式
					}else
					{
						if(rs.getString(7).equals("0"))
						{
							hang.add("RRS");//RRU资源调度的方式:0：RRS 1:CIS 2:PFS
						}else if(rs.getString(7).equals("1"))
						{
							hang.add("CIS");//RRU资源调度的方式
						}else if(rs.getString(7).equals("2"))
						{
							hang.add("PFS");//RRU资源调度的方式
						}
					}
//					hang.add(rs.getString(7));//RRU资源调度的方式
					hang.add(rs.getString(12));//接入环
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

	/***************清空表格******************/
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
	/**************返回表格第几行第几列数值 ******************/
	@Override
	public Object getValueAt(int row, int col) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(row)).get(col);//不转成string也没有关系
	}
	/**************设置表格数据是否可以修改 ******************/	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		//return super.isCellEditable(rowIndex, columnIndex);
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
	/**************返回表格列名******************/
	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}
	/**************表格添加一行 ******************/
	public void addRows(int bbu_add_rownum,String buuc_X,String buuc_Y,String buuc_Z) {
		// TODO 自动生成的方法存根
		
		String sql="exec RowMax ?";
		String []paras={"Bbu"};
		bbu_table=new Bbu_table_module();
		ResultSet Bbu_max_id=bbu_table.total_num_sql(sql, paras);//Bbu_max_id返回bbu当前最大id号
		
		Vector hang=new Vector<>();
		try {
			Bbu_max_id.next();
			int temp=Bbu_max_id.getInt(1)+bbu_add_rownum;
			String bbuid=Integer.toString(temp);
			hang.add(bbuid);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}//BBU的编号
		hang.add(buuc_X);//X坐标
		
		hang.add(buuc_Y);//Y坐标
		
		hang.add(buuc_Z);//Z坐标
		
		hang.add(jtf5);//传输功率dbm形式
		hang.add(jtf6);//资源量
		hang.add("");//RRU资源调度的方式
		hang.add("");//接入环
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
	public ResultSet total_num_sql(String sql,String []paras)
	{
		BBU_sql sqlh=new BBU_sql();
		return sqlh.search(sql, paras);
	}


}