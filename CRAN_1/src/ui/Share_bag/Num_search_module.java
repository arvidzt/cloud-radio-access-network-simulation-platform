/*
 * 类名：Num_search_module
 * 功能：查询,统计bbuc,bbu,rru,ue的数量模型类
 */
package ui.Share_bag;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class Num_search_module extends AbstractTableModel{
	//rowData用来存放行数据 
		//columNames用来存放列名
		Vector rowData,columNames;
	//查询,为的是统计bbuc,bbu,rru,ue的数量
				public void search_num(String sql,String[]paras,int num)//num为传进来的我想要几列的数据
				{
					BBU_sql sqlh=null;
					rowData=new Vector<>();
					try {
						sqlh=new BBU_sql();
						ResultSet rs=sqlh.search(sql, paras);
						while(rs.next())
						{
							//rowData可以存放多行
							Vector hang=new Vector<>();
							for(int i=1;i<=num;i++)
							{
								hang.add(rs.getString(i));//rs.getString(i)是从1开始的
							}
							//加入到rowData
							rowData.add(hang);
							
						}
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}finally
					{
						//关闭
						sqlh.close();
					}	
				}

	//清空临时表格
				public void  clean_all() {
//					columNames.clear();
					try {
						rowData.clear();
					} catch (Exception e) {
						// TODO: handle exception
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

}
