package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Basedao {
	//基础dao文件，获取数据库连接
	protected static final String classname = "com.mysql.cj.jdbc.Driver";
	protected static final String url = "jdbc:mysql://127.0.0.1:3306/infomanage?";
	protected static final String username = "root";
	protected static final String userpwd = "17712245617yuan";
	
	protected Connection getConnection() {
		Connection c = null;
		try {
			Class.forName(classname);
			c = DriverManager.getConnection(url,username,userpwd);
			return c;
		}catch(Exception e){
			e.printStackTrace();
			return c;
		}
	}
	protected void closeConnection(Connection c) {
		try {
			if(c!=null) {
				c.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
