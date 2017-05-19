/*
 * 类名：LinkCircle_module
 * 功能：此类是用来在Panel中显示查询数据库后当前环链路的表格情况的模板
 */
package ui.Add_link;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;


public class LinkCircle_module extends AbstractTableModel{
	//rowData用来存放行数据 
			//columNames用来存放列名
			Vector rowData,columNames;
			Vector hang;
			int search_rownum;
			LinkCircle_module LinkCircle_table;
			//JTextField jtf1,jtf2,jtf3;
			/***************构造链路表格，列名******************/	
			public LinkCircle_module()
			{
				columNames=new Vector<>();
				//设置列名
				columNames.add("环ID");//
				columNames.add("环类型");//
				columNames.add("X1坐标(m)");//
				columNames.add("Y1坐标(m)");//
				columNames.add("X2坐标(m)");//
				columNames.add("Y2坐标(m)");//
				columNames.add("长半径(m)");//
				columNames.add("短半径(m)");//
				columNames.add("备选接入点个数");//
				rowData=new Vector<>();
			}
			/***************查询表格用的显示每行对应数据库参数的函数******************/
			public void LinkCircle_search(String sql,String[]paras)
			{
				BBU_sql sqlh=null;
					try {
						sqlh=new BBU_sql();
						ResultSet rs=sqlh.search(sql, paras);
						while(rs.next())
						{
							//rowData可以存放多行
							Vector hang=new Vector<>();
							hang.add(rs.getString(1));//LinkId
							if(rs.getString(2)==null)
							{
								hang.add(rs.getString(2));//链路类型,0：环型，1：总线型
							}else
							{
								if(rs.getString(2).equals("0"))//链路类型LinkType,0：环型，1：总线型
								{
									hang.add("环型");
								}else if(rs.getString(2).equals("1"))
								{
									hang.add("总线型");
								}
							}
//							hang.add(rs.getString(2));//LinkType
							hang.add(rs.getString(3));//X1
							hang.add(rs.getString(4));//Y1
							hang.add(rs.getString(6));//X2
							hang.add(rs.getString(7));//Y2
							hang.add(rs.getString(9));//LongRadius
							hang.add(rs.getString(10));//ShortRadius
							hang.add(rs.getString(11));//AccesspointNum
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
			/**************设置表格第几行第几列数值 ******************/
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
			/**************设置表格数据是否可以修改 ******************/	
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				// TODO 自动生成的方法存根
				if(rowIndex<search_rownum)
				{
					return false;
				}
				else 
				{
					return true;
				}
			}
			/**************表格添加一行 ******************/
			public void addRows(int LinkCircle_add_rownum) {
				// TODO 自动生成的方法存根
				String sql="exec CircleRowMax";//链路的查找最大ID号用此函数
				//String []paras={null};//LinkBbuPool2BbuPool
				LinkCircle_table=new LinkCircle_module();
				ResultSet LinkCircle_max_id=LinkCircle_table.RowMax_sql(sql);//BbuPool_Link_max_id返回BbuPool_Link当前最大id号
				
				Vector hang=new Vector<>();
				try {
					LinkCircle_max_id.next();
						int temp=LinkCircle_max_id.getInt(1)+LinkCircle_add_rownum;
						String LinkCircle_id=Integer.toString(temp);
//					}
					hang.add(LinkCircle_id);//BbuPool_Link的编号
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				hang.add("");//LinkType
				hang.add("");//X1
				hang.add("");//Y1
				hang.add("");//X2
				hang.add("");//Y2
				hang.add("");//LongRadius
				hang.add("");//ShortRadius
				hang.add("");//AccesspointNum
				//加入到rowData
				rowData.add(hang);
			}
			private ResultSet RowMax_sql(String sql) {
				// TODO 自动生成的方法存根
				BBU_sql sqlh=new BBU_sql();
				return sqlh.search1(sql);
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
				return sqlh.search(sql,paras);
			}
			
}
