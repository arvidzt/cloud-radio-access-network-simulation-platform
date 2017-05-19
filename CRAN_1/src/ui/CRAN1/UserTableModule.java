package ui.CRAN1;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;

public class UserTableModule extends AbstractTableModel{
	//rowData用来存放行数据 
		//columNames用来存放列名
		Vector rowData,columNames;
		Vector hang;
		int search_rownum;
		/***************构造表格，列名******************/	
		public UserTableModule()
		{
			columNames=new Vector<>();
			//设置列名
			//columNames.add("");复选框预留列
			columNames.add("帐户名");
			columNames.add("权限");
			rowData=new Vector<>();
		}
		/***************查询表格用的显示每行对应数据库参数的函数******************/
		public void user_table_search(String sql,String[]paras)
		{
			BBU_sql sqlh=null;
				try {
					sqlh=new BBU_sql();
					ResultSet rs=sqlh.search(sql, paras);
					while(rs.next())
					{
						//rowData可以存放多行
						Vector hang=new Vector<>();
						hang.add(rs.getString(2));//帐户名
						hang.add(rs.getString(3));//权限
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
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

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
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		((Vector) rowData.get(rowIndex)).remove(columnIndex);
		  ((Vector) rowData.get(rowIndex)).add(columnIndex, aValue);
		  this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	@Override
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}

}
