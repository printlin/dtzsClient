package cn.doutu;

import javax.swing.JLabel;
import javax.swing.JTextField;

import cn.doutu.dao.ImgDao;

public class PanelData {
	public JLabel jl,up,down,jump,first,page;
	public JTextField jt;
	public JLabel[] img;
	public String[] url;
	public String[] buff;//将当前显示的图片地址保存
	public String keyWord;
	public ImgDao dao;
	public int nowPage,allPage=-1;
	
	public PanelData(){
		
	}
	public PanelData(JLabel up,JLabel down,JLabel first,JLabel jump,JLabel page,JLabel[] img,String[] buff,ImgDao dao){
		this.up=up;
		this.down=down;
		this.first=first;
		this.jump=jump;
		this.page=page;
		this.img=img;
		this.buff=buff;
		this.dao=dao;
	}
	public void init(){
		nowPage=1;
		allPage=dao.getImgAllPage();
		url=dao.getImgByPage(1);
		page.setText(nowPage+"/"+allPage);
		up.setEnabled(false);
		first.setEnabled(true);
		down.setEnabled(true);
		jump.setEnabled(true);
		
	}
	public ImgDao getDao() {
		return dao;
	}
	public void setDao(ImgDao dao) {
		this.dao = dao;
	}
	public JTextField getJt() {
		return jt;
	}
	public void setJt(JTextField jt) {
		this.jt = jt;
	}
	public JLabel getUp() {
		return up;
	}
	public void setUp(JLabel up) {
		this.up = up;
	}
	public JLabel getDown() {
		return down;
	}
	public void setDown(JLabel down) {
		this.down = down;
	}
	public JLabel getJump() {
		return jump;
	}
	public void setJump(JLabel jump) {
		this.jump = jump;
	}
	public JLabel getFirst() {
		return first;
	}
	public void setFirst(JLabel first) {
		this.first = first;
	}
	public JLabel getPage() {
		return page;
	}
	public void setPage(JLabel page) {
		this.page = page;
	}
	public JLabel[] getImg() {
		return img;
	}
	public void setImg(JLabel[] img) {
		this.img = img;
	}
	public String[] getUrl() {
		return url;
	}
	public void setUrl(String[] url) {
		this.url = url;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getAllPage() {
		return allPage;
	}
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
}
