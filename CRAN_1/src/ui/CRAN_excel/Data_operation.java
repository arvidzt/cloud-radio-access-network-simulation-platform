/**
 * 这是一个对数据库进行操作的类
 */
package ui.CRAN_excel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Data_operation {

	//定义操作数据库需要的东东
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
	//查询数据库的操作
	public ResultSet search(String sql,String []paras)
		{
		try {
				Class.forName(driver);//加载驱动
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=DATA","sa","zhangtao");//得到连接
				ps=ct.prepareStatement(sql);//创建ps
				//给ps的？赋值
				for(int i=0;i<paras.length;i++)
				{ 
					ps.setString(i+1, paras[i]);
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
	

//把增删改合在一起
		//添加学生(增删改)
		public boolean add_delete_change(String sql,String []paras)//添加有没有成功返回一个布尔值
		{
			 boolean b=true;

			try {
				Class.forName(driver);//加载驱动
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=DATA","sa","zhangtao");//得到连接
				ps=ct.prepareStatement(sql);//创建ps
				//给ps的？赋值
				for(int i=0;i<paras.length;i++)
				{ 
					ps.setString(i+1, paras[i]);
				}
				//执行操作
				ps.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally
			{
				this.close();
			}
			return b;

		}
}

	
