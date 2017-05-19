/*
 * 类名：BBU_sql
 * 功能：操作数据库的模型类
 */
package ui.Share_bag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class BBU_sql {
	//定义操作数据库需要的东东
	
		public static final String name = "sa";
		public static final String pswd = "zhangtao";
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//关闭数据库资源
		public void close()
		{
			//关闭
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(ct!=null)ct.close();		
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception

			}
		}
		public ResultSet search(String sql,String []paras){
			try {
				Class.forName(driver);//加载驱动
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=C-RAN",name,pswd);//得到连接
				ps=ct.prepareStatement(sql);//创建ps
				//给ps的？赋值
				if(paras!=null)
				{
					for(int i=0;i<paras.length;i++)
					{ 
						ps.setString(i+1, paras[i]);
						}
				}
				
				//执行操作
				rs=ps.executeQuery();
					
			} catch (Exception e) {
				e.printStackTrace();
					// TODO: handle exception
			}finally
			{
//					this.close();//此处不能关闭资源，因为rs要返回数据模型层
			}
			return rs;
			
		}
		public ResultSet search1(String sql){//专门用于没有参数的SQL语句的查询
			try {
				Class.forName(driver);//加载驱动
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=C-RAN",name,pswd);//得到连接
				ps=ct.prepareStatement(sql);//创建ps
				
				rs=ps.executeQuery();
					
			} catch (Exception e) {
				e.printStackTrace();
					// TODO: handle exception
			}finally
			{
//					this.close();//此处不能关闭资源，因为rs要返回数据模型层
			}
			return rs;
			
		}
		public boolean add_delete_change(String sql, String[] paras) {
			// TODO 自动生成的方法存根
			boolean b=true;
			try {
				Class.forName(driver);//加载驱动
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=C-RAN",name,pswd);//得到连接
				ps=ct.prepareStatement(sql);//创建ps
				//给ps的？赋值
				for(int i=0;i<paras.length;i++)
				{ 
					ps.setString(i+1, paras[i]);
				}
				//执行操作
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
//				JOptionPane.showMessageDialog(null,"存在重复添加的链路、或者属性中存在汉字，未成功保存到数据库。");
				b=false;
			}finally
			{
				this.close();
			}
			return b;
		}

}
