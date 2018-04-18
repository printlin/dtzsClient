package cn.doutu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class SystemDao {
	private static Connection conn=null;
	static{
		InputStream is = ImgDao.class.getClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			Class.forName(p.getProperty("drivername"));
			conn = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static Map<String,String> getClientInfo(){
		String sql=null;
		sql="select * from clientinfo";
		Map<String,String> map=new HashMap<String,String>();
		try {
			Statement st=conn.createStatement();
			ResultSet re=st.executeQuery(sql);
			if(re.next()){
				map.put("version", re.getString("version"));
				map.put("qqlink", re.getString("qqlink"));
				map.put("notice", re.getString("notice"));
				map.put("uploadurl", re.getString("uploadurl"));
				map.put("uploadpwd", re.getString("uploadpwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
