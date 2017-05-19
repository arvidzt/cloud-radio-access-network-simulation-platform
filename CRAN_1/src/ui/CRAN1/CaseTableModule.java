package ui.CRAN1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ui.Add_bbu_rru_ue.Bbu_table_module;
import ui.Share_bag.BBU_sql;

public class CaseTableModule extends AbstractTableModel{
	//rowData用来存放行数据 
			//columNames用来存放列名
			Vector rowData,columNames;
			Vector hang;
			int search_rownum;
			/***************构造表格,列名******************/	
			public CaseTableModule()
			{
				columNames=new Vector<>();
				//设置列名
				//columNames.add("");复选框预留列
				columNames.add("实例名");
				columNames.add("备注");
				rowData=new Vector<>();
			}
			/***************查询表格用的显示每行对应数据库参数的函数******************/
			public void case_table_search(String sql,String[]paras)
			{
				BBU_sql sqlh=null;
					try {
						sqlh=new BBU_sql();
						ResultSet rs=sqlh.search(sql, paras);
						while(rs.next())
						{
							//rowData可以存放多行
							Vector hang=new Vector<>();
							hang.add(rs.getString(1));//实例名
							hang.add(rs.getString(2));//备注
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
	public String getColumnName(int column) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(column);
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根//暂时设为不能编辑，之后调整
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
//	/**************表格添加一行 ******************/
//	public void addRows() {
//		// TODO 自动生成的方法存根
//		
//		Vector hang=new Vector<>();
//		hang.add("");//
//		hang.add("");//
//		//加入到rowData
//		rowData.add(hang);
//	}
	/**************对表格进行添加 删除 修改操作******************/
	public boolean add_delete_change_to_sql(String sql,String []paras) {
		// TODO 自动生成的方法存根
		BBU_sql sqlh=new BBU_sql();
		return sqlh.add_delete_change(sql, paras);
	}
}
