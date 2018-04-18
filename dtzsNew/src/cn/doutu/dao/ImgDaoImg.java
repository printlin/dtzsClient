package cn.doutu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ImgDaoImg extends ImgDao{
	private static final int PAGE_SIZE=8;//一页显示图片个数
	public String[] getImgByPage(int page){
		page-=1;
		String sql=null;
		String[] srcs=new String[PAGE_SIZE];
		int i=0;
		sql="select src from imginfo where src IS NOT NULL and tid is null ORDER BY iid DESC limit "+page*PAGE_SIZE+","+PAGE_SIZE;
		try {
			Statement st=conn.createStatement();
			ResultSet re=st.executeQuery(sql);
			while(re.next()){
				srcs[i]=re.getString("src");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return srcs;
	}
	public int getImgCount(){
		String sql=null;
		sql="select count(*) as c from imginfo where src IS NOT NULL and tid is null";
		try {
			Statement st=conn.createStatement();
			ResultSet re=st.executeQuery(sql);
			if(re.next()){
				return re.getInt("c");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public int getImgAllPage(){
		int count=getImgCount();
		if(count==-1){
			return -1;
		}else{
			return count/PAGE_SIZE;
		}
	}
	
}
