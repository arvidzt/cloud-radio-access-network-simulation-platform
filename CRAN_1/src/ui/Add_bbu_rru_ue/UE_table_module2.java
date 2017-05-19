/*
 * 类名：UE_table_module2
 * 功能：此类是用来在Panel中显示查询数据库后当前UE的表格情况的模板
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
public class UE_table_module2 extends AbstractTableModel{
	//rowData用来存放行数据 
	//columNames用来存放列名
	Vector rowData,columNames;
	Vector hang;
	JTextField jtf2,jtf3,jtf4;
	//int cannot_modify_row;
	int search_rownum;
	UE_table_module2 ue_table;
	/***************构造UE表格，列名******************/	
	public  UE_table_module2()
	{
		columNames=new Vector<>();
		//设置列名
		//columNames.add("");复选框预留列
		columNames.add("UE ID");
		columNames.add("X坐标(m)");
		columNames.add("Y坐标(m)");
		columNames.add("Z坐标(m)");
		columNames.add("室内外信息");
		columNames.add("天线类型");
		columNames.add("UE群 ID");
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
					hang.add(rs.getString(1));//UE ID0
					hang.add(rs.getString(3));//X坐标1
					hang.add(rs.getString(4));//Y坐标2
					hang.add(rs.getString(5));//Z坐标3
					if(rs.getString(2)==null)
					{
						hang.add(rs.getString(2));//RRU资源调度的方式
					}else
					{
						if(rs.getString(2).equals("0"))//室内外信息4，1：室内，0：室外
						{
							hang.add("室外");
						}else if(rs.getString(2).equals("1"))
						{
							hang.add("室内");
						}
					}
					hang.add(rs.getString(9));//天线类型5
					hang.add(rs.getString(11));//UE群 ID
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

	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columNames.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}

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

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		((Vector) rowData.get(rowIndex)).remove(columnIndex);
		  ((Vector) rowData.get(rowIndex)).add(columnIndex, aValue);
		  this.fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);//不转成string也没有关系
	}
	/**************表格添加一行 ******************/
	public void addRows(int ue_add_rownum) {
		String sql="exec RowMax ?";
		String []paras={"Ue"};
		ue_table=new UE_table_module2();
		ResultSet Ue_max_id=ue_table.total_num_sql(sql, paras);//ue_max_id返回bbu当前最大id号
		
		Vector hang=new Vector<>();
		try {
			Ue_max_id.next();
			int temp=Ue_max_id.getInt(1)+ue_add_rownum;
			String ueid=Integer.toString(temp);
			hang.add(ueid);//UE的编号
		} catch (SQLException e) {
			e.printStackTrace();
		}
		hang.add(jtf2);//X坐标
		hang.add(jtf3);//Y坐标
		hang.add(jtf4);//Z坐标	
		hang.add("");//室内外信息
		hang.add("");//天线类型
		hang.add("");//UE群 ID
		//加入到rowData
		rowData.add(hang);
		
	}
	
	//删除
		public void remove_row(int rownum)
		{
			  if (rowData.size()> rownum) {
				  rowData.remove(rownum);
	          }
			
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

//	public void For_modify(int ue_add_row_alreaday2) {
//		// TODO 自动生成的方法存根
//		cannot_modify_row=ue_add_row_alreaday2;
//	}
}
