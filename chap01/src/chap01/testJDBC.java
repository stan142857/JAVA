package chap01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class testJDBC {
	public static void main(String args[]) throws Exception{
		Connection connection = null;
		try {
			//1.加载驱动，其实就是加载某个类到内存
			//执行该类的static代码段，该段代码向DriverManager注册该驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2.建立连接，三个参数，url地址(协议+子协议+数据源)、用户名、密码
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/infomanage?","root","17712245617yuan");
			
			//3.通过Connection建立一个查询statement
			Statement st = connection.createStatement();
			
			//4.将插叙结果存放到数据集ResultSet，executeQuery执行查询
			String sql = "SELECT"
					+ "	username,"
					+ "	userpwd,"
					+ "	regtime,"
					+ "	hobby"
					+ " FROM"
					+ "	userinfo";
			ResultSet rs = st.executeQuery(sql);
			
			//5.获取查询结果
			while(rs.next()) {
				System.out.println(rs.getString("username")+","+rs.getString("userpwd"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//关闭连接
			connection.close();
		}
	}
}
