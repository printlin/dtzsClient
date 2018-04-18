package cn.doutu.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class ImgDao {
	protected Connection conn=null;
	public ImgDao(){
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
	public abstract String[] getImgByPage(int page);
	public abstract int getImgCount();
	public abstract int getImgAllPage();
	
}
