package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Userdao extends Basedao{
	public boolean insert(User user) {
		try {
			Connection c = this.getConnection();
			String sql = "insert into userinfo(username,userpwd,hobby,regtime)";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getUserpwd());
			pst.setString(3, user.getHobby());
			pst.setDate(4, user.getRegtime());
			pst.execute();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
