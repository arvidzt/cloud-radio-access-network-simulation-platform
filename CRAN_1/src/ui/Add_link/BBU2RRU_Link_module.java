/*
 * 类名：BBU2RRU_Link_module
 * 功能：此类是用来在Panel中显示查询数据库后当前BBU和RRU间链路的表格情况的模板
 */
package ui.Add_link;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;


public class BBU2RRU_Link_module extends AbstractTableModel{
	//rowData用来存放行数据 
			//columNames用来存放列名
			Vector rowData,columNames;
			Vector hang;
			int search_rownum;
			BBU2RRU_Link_module BBU2RRU_Link_table;
			JTextField jtf1,jtf2,jtf3;
			/***************构造链路表格，列名******************/	
			public BBU2RRU_Link_module()
			{
				columNames=new Vector<>();
				//设置列名
				columNames.add("光纤链路 ID");//LinkPathId
				columNames.add("BBU端点ID");//LinkEnd1
				columNames.add("RRU端点ID");//LinkEnd2
				columNames.add("最大传输速度(Mbps)");//MaxTransRate
				columNames.add("链路类型");//LinkType
//				columNames.add("接入环");//
				rowData=new Vector<>();
			}
			/***************查询表格用的显示每行对应数据库参数的函数******************/
			public void BBU2RRU_Link_search(String sql,String[]paras)
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
							if(rs.getString(5)==null)
							{
								hang.add(rs.getString(5));//链路类型,0：环型，1：总线型
							}else
							{
								if(rs.getString(5).equals("0"))//链路类型,0：环型，1：总线型
								{
									hang.add("环型");
								}else if(rs.getString(5).equals("1"))
								{
									hang.add("总线型");
								}
							}
//							hang.add(rs.getString(5));//链路类型
//							hang.add(rs.getString(11));//接入环
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
			public Object getValueAt(int rowIndex, int columnIndex) {
				// TODO 自动生成的方法存根
				return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);//不转成string也没有关系
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
			@Override
			public String getColumnName(int column) {
				// TODO 自动生成的方法存根
				return (String) this.columNames.get(column);
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
			public void addRows(int Bbu2Bbu_Link_add_rownum) {
				// TODO 自动生成的方法存根
				String sql="exec PathRowMax ?";//链路的查找最大ID号用此函数
				String []paras={"LinkBbu2Rru"};//LinkBbuPool2BbuPool
				BBU2RRU_Link_table=new BBU2RRU_Link_module();
				ResultSet Bbu2Bbu_Link_max_id=BBU2RRU_Link_table.total_num_sql(sql, paras);//BbuPool_Link_max_id返回BbuPool_Link当前最大id号
				
				Vector hang=new Vector<>();
				try {
					Bbu2Bbu_Link_max_id.next();
						int temp=Bbu2Bbu_Link_max_id.getInt(1)+Bbu2Bbu_Link_add_rownum;
						String Bbu2Bbu_Link_id=Integer.toString(temp);
//					}
					hang.add(Bbu2Bbu_Link_id);//BbuPool_Link的编号
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				hang.add(jtf1);//端点池ID
				hang.add(jtf2);//端点池ID
				hang.add(jtf3);//最大传输速度
				hang.add("");//链路类型
//				hang.add("");//接入环
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
