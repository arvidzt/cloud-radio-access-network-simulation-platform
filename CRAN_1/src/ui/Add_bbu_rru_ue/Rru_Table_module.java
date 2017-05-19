/*
 * 类名：Rru_Table_module
 * 功能：此类是用来在Panel中显示查询数据库后当前RRU的表格情况的模板
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
public class Rru_Table_module extends AbstractTableModel{
	//rowData用来存放行数据 
		//columNames用来存放列名
		Vector rowData,columNames;
		Vector hang;
		JTextField jtf2,jtf3,jtf4,jtf5,jtf6,jtf7;
		int search_rownum;
		Rru_Table_module rru_table;
		/***************构造RRU表格，列名******************/
		public Rru_Table_module()
		{
			columNames=new Vector<>();
			//设置列名
			//columNames.add("");复选框预留列
			columNames.add("RRU ID");
			columNames.add("X坐标(m)");
			columNames.add("Y坐标(m)");
			columNames.add("Z坐标(m)");
			columNames.add("发射功率(dBm)");
			columNames.add("最大用户量");
			columNames.add("载频(Hz)");
			columNames.add("RRU的状态");
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
						hang.add(rs.getString(1));//RRU ID0
						hang.add(rs.getString(4));//X坐标1
						hang.add(rs.getString(5));//Y坐标2
						hang.add(rs.getString(6));//Z坐标3
						hang.add(rs.getString(7));//发射功率4
						hang.add(rs.getString(9));//最大用户量5
						hang.add(rs.getString(11));//载频6
						if(rs.getString(10)==null)
						{
							hang.add(rs.getString(10));//RRU资源调度的方式
						}else
						{
							if(rs.getString(10).equals("0"))
							{
								hang.add("关闭");//RRU的状态属性7,0：关闭，1：打开
							}else if(rs.getString(10).equals("1"))
							{
								hang.add("打开");//RRU的状态属性7
							}
						}
						hang.add(rs.getString(12));//RRU的天线8
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
			//使得jtable可修改后，就可以显示下拉列表了。
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
		/**************表格添加一行 ******************/
		public void addRows(int rru_add_rownum) {
			// TODO 自动生成的方法存根
			
			String sql="exec RowMax ?";
			String []paras={"Rru"};
			rru_table=new Rru_Table_module();
			ResultSet Rru_max_id=rru_table.total_num_sql(sql, paras);//Bbu_max_id返回bbu当前最大id号
			
			Vector hang=new Vector<>();
			try {
				Rru_max_id.next();
				int temp=Rru_max_id.getInt(1)+rru_add_rownum;
				String rruid=Integer.toString(temp);
				hang.add(rruid);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}//BBU的编号
			hang.add(jtf2);//X坐标
			
			hang.add(jtf3);//Y坐标
			
			hang.add(jtf4);//Z坐标
			
			hang.add(jtf5);//发射功率
			hang.add(jtf6);//最大用户量
			hang.add(jtf7);//载频
			hang.add("");//RRU的状态属性
			hang.add("");//RRU的天线
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

	