/*
 * 类名：BbuPool_Link_Module
 * 功能：此类是用来在Panel中显示查询数据库后当前BBU池间链路的表格情况的模板
 */
package ui.Add_link;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;


public class BbuPool_Link_Module extends AbstractTableModel{
	//rowData用来存放行数据 
		//columNames用来存放列名
		Vector rowData,columNames;
		Vector hang;
		int search_rownum;
		BbuPool_Link_Module BbuPool_Link_table;
		JTextField jtf1,jtf2,jtf3;
		/***************构造链路表格，列名******************/	
		public BbuPool_Link_Module()
		{
			columNames=new Vector<>();
			//设置列名
			columNames.add("光纤链路 ID");//LinkPathId
			columNames.add("端点池ID");//LinkEnd1
			columNames.add("端点池ID");//LinkEnd2
			columNames.add("最大传输速度(Mbps)");//MaxTransRate
			columNames.add("链路类型");//LinkType
			rowData=new Vector<>();
		}
		/***************查询表格用的显示每行对应数据库参数的函数******************/
		public void BbuPool_Link_search(String sql,String[]paras)
		{
			BBU_sql sqlh=null;
				try {
					sqlh=new BBU_sql();
					ResultSet rs=sqlh.search(sql, paras);
					while(rs.next())
					{
						//rowData可以存放多行
						Vector hang=new Vector<>();
						hang.add(rs.getString(1));//光纤链路 ID
						hang.add(rs.getString(2));//端点池ID
						hang.add(rs.getString(3));//端点池ID
						hang.add(rs.getString(6));//最大传输速度
						hang.add(rs.getString(5));//链路类型

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
		/**************表格添加一行 ******************/
		public void addRows(int bbuPool_Link_add_rownum) {
			// TODO 自动生成的方法存根
			String sql="exec PathRowMax ?";
			String []paras={"LinkBbuPool2BbuPool"};//LinkBbuPool2BbuPool
			BbuPool_Link_table=new BbuPool_Link_Module();
			ResultSet BbuPool_Link_max_id=BbuPool_Link_table.total_num_sql(sql, paras);//BbuPool_Link_max_id返回BbuPool_Link当前最大id号
			
			Vector hang=new Vector<>();
			try {
				BbuPool_Link_max_id.next();
					int temp=BbuPool_Link_max_id.getInt(1)+bbuPool_Link_add_rownum;
					String BbuPool_Link_id=Integer.toString(temp);
//				}
				hang.add(BbuPool_Link_id);//BbuPool_Link的编号
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			hang.add(jtf1);//端点池ID
			hang.add(jtf2);//端点池ID
			hang.add(jtf3);//最大传输速度
			hang.add("");//链路类型
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
