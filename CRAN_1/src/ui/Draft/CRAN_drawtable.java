package ui.Draft;

import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ui.Share_bag.BBU_sql;

public class CRAN_drawtable extends AbstractTableModel{
	Vector rowData,columNames;
	public  CRAN_drawtable()
	{
		columNames=new Vector<>();
		
		//设置列名
		rowData=new Vector<>();
	}
	public void table_search_Bbupool(String sql, String[] paras) {
		// TODO 自动生成的方法存根
		BBU_sql sqlh=null;
		try {
			sqlh=new BBU_sql();
			ResultSet rs=sqlh.search(sql, paras);
			while(rs.next())
			{
				//rowData可以存放多行
				Vector hang=new Vector<>();
				hang.add(rs.getString(1));//BbuPoolId
				hang.add(rs.getString(2));//X
				hang.add(rs.getString(3));//Y
				hang.add(rs.getString(4));//Z
				hang.add(rs.getString(5));//AllRes
				hang.add(rs.getString(6));//ResLeft
				hang.add(rs.getString(7));//DynamicEnengy
				hang.add(rs.getString(8));//StaticEnengy
				hang.add(rs.getString(9));//BbuPoolInfo
				//加入到rowData
				rowData.add(hang);
				
			}
			//search_rownum=this.getRowCount();
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);//不转成string也没有关系
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO 自动生成的方法存根
		((Vector) rowData.get(rowIndex)).remove(columnIndex);
		  ((Vector) rowData.get(rowIndex)).add(columnIndex, aValue);
		  this.fireTableCellUpdated(rowIndex, columnIndex);
	}
	public void table_search_Bbupoollink(String sql4, String[] paras4) {
		// TODO 自动生成的方法存根
		// TODO 自动生成的方法存根
				BBU_sql sqlh=null;
				try {
					sqlh=new BBU_sql();
					ResultSet rs=sqlh.search(sql4, paras4);
					while(rs.next())
					{
						//rowData可以存放多行
						Vector hang=new Vector<>();
						hang.add(rs.getString(1));//LinkPathId
						hang.add(rs.getString(2));//LinkEnd1
						hang.add(rs.getString(3));//LinkEnd2
						hang.add(rs.getString(4));//RealLength
						hang.add(rs.getString(5));//LinkType
						hang.add(rs.getString(6));//MaxTransRate
						hang.add(rs.getString(7));//Attenuation
						hang.add(rs.getString(8));//Delay
						hang.add(rs.getString(9));//ErrorRate
						hang.add(rs.getString(10));//Cost
						hang.add(rs.getString(11));//LinkCircleId
						hang.add(rs.getString(12));//FibersNum
						//加入到rowData
						rowData.add(hang);
						
					}
					//search_rownum=this.getRowCount();
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}finally
				{
					//关闭
					sqlh.close();
				}	
	}
}
