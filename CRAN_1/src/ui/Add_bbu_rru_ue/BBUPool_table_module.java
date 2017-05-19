/*
 * 类名：BBUPool_table_module
 * 功能：此类是用来在Panel中显示查询数据库后当前BBUPOOL的表格情况的模板
 */
package ui.Add_bbu_rru_ue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;

public class BBUPool_table_module extends AbstractTableModel{
		//rowData用来存放行数据 
		//columNames用来存放列名
		Vector rowData,columNames;
		Vector hang;
		int search_rownum;
		BBUPool_table_module BBUPool_table;
		//panel用的查询显示的函数
		public BBUPool_table_module()
		{
			columNames=new Vector<>();
			//设置列名
			//columNames.add("");复选框预留列
			columNames.add("BbuPool ID");
			columNames.add("X坐标(m)");
			columNames.add("Y坐标(m)");
			columNames.add("Z坐标(m)");
			columNames.add("备注");
			rowData=new Vector<>();
		}
		public void bbupool_table_search(String sql,String[]paras)
		{
			BBU_sql sqlh=null;
				try {
					sqlh=new BBU_sql();
					ResultSet rs=sqlh.search(sql, paras);
					while(rs.next())
					{
						//rowData可以存放多行
						Vector hang=new Vector<>();
						hang.add(rs.getString(1));//BBUpool的ID
						hang.add(rs.getString(2));//X坐标
						hang.add(rs.getString(3));//Y坐标
						hang.add(rs.getString(4));//Z坐标
						hang.add(rs.getString(9));//备注
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
	/***************得到表格行数******************/
	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}
	/***************得到第几行第几列的数据******************/
	@Override
	public Object getValueAt(int row, int col) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(row)).get(col);//不转成string也没有关系
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
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		((Vector) rowData.get(rowIndex)).remove(columnIndex);
		  ((Vector) rowData.get(rowIndex)).add(columnIndex, aValue);
		  this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	/***************添加一行******************/
		public void addRows(int bbu_add_rownum) {
			// TODO 自动生成的方法存根
			
			String sql="exec RowMax ?";
			String []paras={"BbuPool"};
			BBUPool_table=new BBUPool_table_module();
			ResultSet Bbu_max_id=BBUPool_table.total_num_sql(sql, paras);//Bbu_max_id返回bbu当前最大id号
			
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
			hang.add("");//X坐标
			
			hang.add("");//Y坐标
			
			hang.add("");//Z坐标
			hang.add("");//备注
			//加入到rowData
			rowData.add(hang);
			
		}
		/***************调用后台调用数据库函数实现：添加 删除 修改******************/
		public boolean add_delete_change_to_sql(String sql,String []paras) {
			// TODO 自动生成的方法存根
			BBU_sql sqlh=new BBU_sql();
			return sqlh.add_delete_change(sql, paras);
		}
		/***************此函数返回数据库每个表格所有数据总行数，目的是得到参数总数、、自动生成ID******************/
		public ResultSet total_num_sql(String sql,String []paras)
		{
			BBU_sql sqlh=new BBU_sql();
			return sqlh.search(sql, paras);
		}

}
