package ui.CRAN_excel;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class Tablemodule extends AbstractTableModel{

	//rowData用来存放行数据 
	//columNames用来存放列名
	Vector rowData,columNames;
	//添加学生(增删改)
		public boolean change(String sql,String []paras)//添加有没有成功返回一个布尔值
		{
			//创建一个Sqlhelper,如果程序并发性不考虑，可以把Sqlhelper做成并发的
			Data_operation sqlh=new Data_operation();
			return sqlh.add_delete_change(sql, paras);

		}

	//查询本质就是初始化
		public void search(String sql,String[]paras)
		{
			Data_operation sqlh=null;
			//中间
					columNames=new Vector<>();
					//设置列名
					columNames.add("ID");
					columNames.add("3维位置信息");
					columNames.add("室内外信息");
					columNames.add("无线类型");
					rowData=new Vector<>();
					try {
						sqlh=new Data_operation();
						ResultSet rs=sqlh.search(sql, paras);
						while(rs.next())
						{
							//rowData可以存放多行
							Vector hang=new Vector<>();
							hang.add(rs.getString(1));
							hang.add(rs.getString(2));
							hang.add(rs.getString(3));
							hang.add(rs.getString(4));
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
	public String getColumnName(int arg0) {
		// TODO 自动生成的方法存根
		return (String) this.columNames.get(arg0);
	}
	@Override
	public Object getValueAt(int row, int col) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(row)).get(col);//不转成string也没有关系
	}

}
