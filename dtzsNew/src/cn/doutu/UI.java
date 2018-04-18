package cn.doutu;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog.ModalityType;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.doutu.dao.ImgDaoEdit;
import cn.doutu.dao.ImgDaoFind;
import cn.doutu.dao.ImgDaoImg;
import cn.doutu.dao.ImgDaoTtImg;
import cn.doutu.dao.SystemDao;

public class UI{
	private static final float VERSION=2.0f;
	private JFrame jf;
	private JDialog jd_zz_text,jd_pao,jd_pay;
	//JDialog jd1;
	private JPanel jp_top,jp_center,jp_botton,jp_ss,jp_tt,jp_bj,jp_sz,jp_zz,jp_updata;
	private JLabel jl_pay,jl_paoB,jl_paoT,jl_back,jl_load,jl_close,jl_small,jl_updateT,jl_update,jl_joinT,jl_join,jl_notice;
	private JLabel jl_ss,jl_ss_search_back,jl_ss_search_button,jl_ss_allNub;
	private JLabel jl_tt,jl_tt_title1,jl_tt_title2;
	private JLabel jl_bj,jl_bj_title1,jl_bj_title2;
	private JLabel jl_sz,jl_sz_scml,jl_sz_openSC,jl_sz_setSC,jl_sz_pay,jl_sz_payCode;
	private JLabel jl_zz_text_back,jl_zz_text_font,jl_zz_text_color,jl_zz_text_colorT,jl_zz_text_ok,jl_zz_text_not,jl_zz_view0,jl_zz_view1,jl_zz_view2,jl_zz_new,jl_zz_font,jl_zz_del,jl_zz_finish,jl_zz_return;
	private JLabel jl_updata_img1,jl_updata_img2,jl_updata_text,jl_updata_version,jl_updata_nowVersion,jl_updata_up,jl_updata_passT,jl_updata_pass;
	private JTextField jt_zz_font,jt_ss_search,jt_sz_scml;
	private JTextArea jt_zz_input,jt_updata_inf;
	//private JProgressBar jpb_ss,jpb_tt,jpb_bj;
	private Font font,zz_font;
	private Color color,color_mr,color_xz,color_dj,zz_fontColor=new Color(0,0,0);
	private MouseEventDo mouseEventDo;
	private KeyEventDo keyEventDo;
	private ImgEvent imgEvent;
	private ButtonEvent buttonEvent;
	private ImageIcon password,clickThis,pao,loading,joinGroup,openSC,setSC,pay,payCode,search,back_ss,back_tt,back_bj,back_sz,do_closeNull,do_closeClick,do_smallNull,do_smallClick;
	private static Point origin = new Point();//点：窗口拖动处使用
	private MyUse mu=new MyUse();//自用模块
	private PanelData ssData,ttData,bjData,findData;//将四个页面都有的组件和参数封装在此类中，方便后续调用
	private Properties prop;
	public String seveSrc,t_INF,t_updataUrl,t_passText,t_join,t_notice,SRC_DTL_SS="http://www.doutula.com/search?type=photo&more=1&keyword=";//http://www.doutula.com/search?type=photo&more=1&keyword=%E6%88%91&page=1
	public float nowVersion=1.0f;
	public boolean isLoad=false;
	
	
	public UI() {
		jf=new JFrame();
		jl_back=new JLabel(new ImageIcon("images/UI.jpg"));
		jl_back.setSize(651,651);
		jl_back.setLayout(null);
		int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;// 获得屏幕尺寸的宽
		int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;// 获得屏幕尺寸的高
		jf.setSize(651,651);
		jf.setLocation((screen_width - jf.getWidth()) / 2,
				(screen_height - jf.getHeight()) / 2);// 窗口位置
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
				
			}
		});
		jf.setUndecorated(true);
		jf.add(jl_back);
		jf.setVisible(true);
		initUI();
	}
	
	private void initUI(){
		font=new Font("宋体",0,12);
		color=new Color(51,204,255);
		color_mr=new Color(245,245,245);
		color_xz=new Color(225,225,225);
		color_dj=new Color(240,240,240);
		mouseEventDo=new MouseEventDo();
		keyEventDo=new KeyEventDo();
		imgEvent=new ImgEvent();
		buttonEvent=new ButtonEvent();
		back_ss=new ImageIcon("images/back_ss.jpg");
		back_tt=new ImageIcon("images/back_tt.jpg");
		back_bj=new ImageIcon("images/back_bj.jpg");
		back_sz=new ImageIcon("images/back_sz.jpg");
		do_closeNull=new ImageIcon("images/do_closeNull.jpg");
		do_closeClick=new ImageIcon("images/do_closeClick.jpg");
		do_smallNull=new ImageIcon("images/do_smallNull.jpg");
		do_smallClick=new ImageIcon("images/do_smallClick.jpg");
		search=new ImageIcon("images/search.png");
		openSC=new ImageIcon("images/openSC.png");
		setSC=new ImageIcon("images/setSC.png");
		//openFile=new ImageIcon("images/openFile.png");
		pay=new ImageIcon("images/pay.png");
		payCode=new ImageIcon("images/payCode.png");
		joinGroup=new ImageIcon("images/joinGroup.png");
		loading=new ImageIcon("images/loading.gif");
		pao=new ImageIcon("images/pao.jpg");
		password=new ImageIcon("images/password.jpg");
		clickThis=new ImageIcon("images/clickThis.jpg");
		seveSrc="e:/dtzs/";
		

		
		/*jd=new JDialog(jf);
		jd.setUndecorated(true);
		jd.setSize(128,128);
		jd.setLocation(jf.getX()+(jf.getWidth()-jd.getWidth())/2, jf.getY()+(jf.getHeight()-jd.getHeight())/2);
		//jd.setModalityType(ModalityType.APPLICATION_MODAL);*/
		jd_pao=new JDialog(jf);
		jd_pao.setUndecorated(true);
		jd_pao.setSize(120,30);
		jd_pao.setLayout(null);
		jl_paoB=new JLabel(pao);
		jl_paoB.setLayout(null);
		jl_paoB.setSize(120,30);
		jl_paoT=new JLabel("已复制",JLabel.CENTER);
		jl_paoT.setFont(font);
		jl_paoT.setSize(120,30);
		jl_paoB.add(jl_paoT);
		jd_pao.add(jl_paoB);
		jd_pao.setVisible(false);
		
		jd_pay=new JDialog(jf);
		jd_pay.setSize(472,415);
		jd_pay.setUndecorated(true);
		jd_pay.setLayout(null);
		jl_pay=new JLabel(payCode);
		jl_pay.setBounds(0,0,472,415);
		jl_pay.addMouseListener(mouseEventDo);
		jd_pay.add(jl_pay);
		
		jd_zz_text=new JDialog(jf);
		jd_zz_text.setUndecorated(true);
		jd_zz_text.setSize(224,120);
		jd_zz_text.setLayout(null);
		jd_zz_text.setModalityType(ModalityType.APPLICATION_MODAL);
		jl_zz_text_back=new JLabel();
		jl_zz_text_back.setBounds(0,0,224,120);
		jl_zz_text_back.setOpaque(true);
		jl_zz_text_back.setBackground(Color.WHITE);
		jl_zz_text_back.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_text_back.setLayout(null);
		
		
		jp_top=new JPanel();
		jp_top.setLayout(null);
		jp_top.setBounds(0,0,651,48);
		jp_top.addMouseListener(mouseEventDo);
		jp_top.setOpaque(false);
		jp_top.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		jp_top.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = jf.getLocation();
				jf.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
		jp_center=new JPanel();
		jp_center.setLayout(null);
		jp_center.setBounds(6,56,640,534);
		jp_center.setOpaque(false);
		jp_botton=new JPanel();
		jp_botton.setLayout(null);
		jp_botton.setBounds(0,590,651,63);
		jp_botton.setOpaque(false);
		jp_ss=new JPanel();
		jp_ss.setLayout(null);
		jp_ss.setBounds(0,0,640,534);
		jp_ss.setOpaque(false);
		jp_ss.setVisible(true);
		jp_tt=new JPanel();
		jp_tt.setLayout(null);
		jp_tt.setBounds(0,0,640,534);
		jp_tt.setOpaque(false);
		jp_tt.setVisible(false);
		jp_bj=new JPanel();
		jp_bj.setLayout(null);
		jp_bj.setBounds(0,0,640,534);
		jp_bj.setOpaque(false);
		jp_bj.setVisible(false);
		jp_sz=new JPanel();
		jp_sz.setLayout(null);
		jp_sz.setBounds(0,0,640,534);
		jp_sz.setOpaque(false);
		jp_sz.setVisible(false);
		jp_zz=new JPanel();
		jp_zz.setLayout(null);
		jp_zz.setBounds(0,0,640,534);
		jp_zz.setOpaque(false);
		jp_zz.setVisible(false);
		jp_updata=new JPanel();
		jp_updata.setLayout(null);
		jp_updata.setBounds(0,0,640,534);
		jp_updata.setOpaque(false);
		jp_updata.setVisible(false);
		
		
		findData=new PanelData();
		ssData=new PanelData();
		ttData=new PanelData();
		bjData=new PanelData();
		


		jl_ss=new JLabel("",JLabel.CENTER);
		jl_ss.setBounds(117,5,57,45);
		jl_ss.setFont(font);
		jl_ss.setIcon(back_ss);
		jl_ss.addMouseListener(mouseEventDo);
		jl_ss.setOpaque(false);
		jl_ss_search_back=new JLabel(search);
		jl_ss_search_back.setLayout(null);
		jl_ss_search_back.setBounds(185,8,283,40);
		jl_ss_search_back.setOpaque(false);
		jl_ss_search_button=new JLabel();
		jl_ss_search_button.setBounds(249,8,24,24);
		jl_ss_search_button.addMouseListener(mouseEventDo);
		jl_ss_search_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jl_ss_search_button.setOpaque(false);
		/*jl_ss_load=new JLabel("载入中..");
		jl_ss_load.setBounds(18,459,48,16);
		jl_ss_load.setOpaque(false);
		jl_ss_load.setFont(font);*/
		jl_ss_allNub=new JLabel("找到图片数：10000");
		jl_ss_allNub.setBounds(18,480,130,32);
		jl_ss_allNub.setFont(font);
		jl_ss_allNub.setOpaque(false);
		ssData.page=new JLabel("1000/1000",JLabel.CENTER);
		ssData.page.setBounds(226,480,80,32);
		ssData.page.setOpaque(false);
		ssData.page.setFont(font);
		ssData.up=new JLabel("上一页",JLabel.CENTER);
		ssData.up.setBounds(154,480,64,32);
		ssData.up.setFont(font);
		ssData.up.addMouseListener(buttonEvent);
		ssData.up.setBackground(color_mr);
		ssData.up.setOpaque(true);
		ssData.up.setBorder(BorderFactory.createLineBorder(color));
		ssData.up.setEnabled(false);
		ssData.down=new JLabel("下一页",JLabel.CENTER);
		ssData.down.setBounds(314,480,64,32);
		ssData.down.setFont(font);
		ssData.down.addMouseListener(buttonEvent);
		ssData.down.setBackground(color_mr);
		ssData.down.setOpaque(true);
		ssData.down.setBorder(BorderFactory.createLineBorder(color));
		ssData.down.setEnabled(false);
		ssData.jump=new JLabel("跳页",JLabel.CENTER);
		ssData.jump.setBounds(426,480,64,32);
		ssData.jump.setFont(font);
		ssData.jump.addMouseListener(buttonEvent);
		ssData.jump.setBackground(color_mr);
		ssData.jump.setOpaque(true);
		ssData.jump.setBorder(BorderFactory.createLineBorder(color));
		ssData.jump.setEnabled(false);
		ssData.first=new JLabel("首页",JLabel.CENTER);
		ssData.first.setBounds(514,480,64,32);
		ssData.first.setFont(font);
		ssData.first.addMouseListener(buttonEvent);
		ssData.first.setBackground(color_mr);
		ssData.first.setOpaque(true);
		ssData.first.setBorder(BorderFactory.createLineBorder(color));
		ssData.first.setEnabled(false);
		ssData.buff=new String[8];
		ssData.dao=new ImgDaoImg();
		
		findData.page=ssData.page;
		findData.up=ssData.up;
		findData.down=ssData.down;
		findData.jump=ssData.jump;
		findData.first=ssData.first;
		findData.buff=ssData.buff;
		findData.dao=new ImgDaoFind();
		
		
		jl_tt=new JLabel("套图",JLabel.CENTER);
		jl_tt.setBounds(174,5,57,45);
		jl_tt.setFont(font);
		jl_tt.addMouseListener(mouseEventDo);
		jl_tt.setOpaque(false);
		jl_tt_title1=new JLabel("",JLabel.CENTER);//套图标题（待拓展）
		jl_tt_title1.setBounds(2,8,632,16);
		jl_tt_title1.setFont(font);
		jl_tt_title2=new JLabel("",JLabel.CENTER);//套图标题（待拓展）
		jl_tt_title2.setBounds(2,240,632,16);
		jl_tt_title2.setFont(font);
		/*jl_tt_load=new JLabel("载入中..");
		jl_tt_load.setBounds(18,456,48,16);
		jl_tt_load.setFont(font);*/
		ttData.page=new JLabel("1000/1000",JLabel.CENTER);
		ttData.page.setBounds(194,480,80,32);
		ttData.page.setOpaque(false);
		ttData.page.setFont(font);
		ttData.up=new JLabel("上一页",JLabel.CENTER);
		ttData.up.setBounds(114,480,64,32);
		ttData.up.setFont(font);
		ttData.up.addMouseListener(buttonEvent);
		ttData.up.setBackground(color_mr);
		ttData.up.setOpaque(true);
		ttData.up.setBorder(BorderFactory.createLineBorder(color));
		ttData.up.setEnabled(false);
		ttData.down=new JLabel("下一页",JLabel.CENTER);
		ttData.down.setBounds(290,480,64,32);
		ttData.down.setFont(font);
		ttData.down.addMouseListener(buttonEvent);
		ttData.down.setBackground(color_mr);
		ttData.down.setOpaque(true);
		ttData.down.setBorder(BorderFactory.createLineBorder(color));
		ttData.down.setEnabled(false);
		ttData.jump=new JLabel("跳页",JLabel.CENTER);
		ttData.jump.setBounds(410,480,64,32);
		ttData.jump.setFont(font);
		ttData.jump.addMouseListener(buttonEvent);
		ttData.jump.setBackground(color_mr);
		ttData.jump.setOpaque(true);
		ttData.jump.setBorder(BorderFactory.createLineBorder(color));
		ttData.jump.setEnabled(false);
		ttData.first=new JLabel("首页",JLabel.CENTER);
		ttData.first.setBounds(514,480,64,32);
		ttData.first.setFont(font);
		ttData.first.addMouseListener(buttonEvent);
		ttData.first.setBackground(color_mr);
		ttData.first.setOpaque(true);
		ttData.first.setBorder(BorderFactory.createLineBorder(color));
		ttData.first.setEnabled(false);
		ttData.buff=new String[8];
		ttData.dao=new ImgDaoTtImg();
		
		jl_bj=new JLabel("编辑",JLabel.CENTER);
		jl_bj.setBounds(231,5,57,45);
		jl_bj.setFont(font);
		jl_bj.addMouseListener(mouseEventDo);
		jl_bj.setOpaque(false);
		jl_bj_title1=new JLabel("标题1",JLabel.CENTER);
		jl_bj_title1.setBounds(2,8,632,16);
		jl_bj_title2=new JLabel("标题2",JLabel.CENTER);
		jl_bj_title2.setBounds(2,240,632,16);
		/*jl_bj_load=new JLabel("载入中..");
		jl_bj_load.setBounds(18,456,48,16);
		jl_bj_load.setOpaque(false);
		jl_bj_load.setFont(font);*/
		bjData.page=new JLabel("1000/1000",JLabel.CENTER);
		bjData.page.setBounds(194,480,80,32);
		bjData.page.setOpaque(false);
		bjData.page.setFont(font);
		bjData.up=new JLabel("上一页",JLabel.CENTER);
		bjData.up.setBounds(114,480,64,32);
		bjData.up.setFont(font);
		bjData.up.addMouseListener(buttonEvent);
		bjData.up.setBackground(color_mr);
		bjData.up.setOpaque(true);
		bjData.up.setBorder(BorderFactory.createLineBorder(color));
		bjData.up.setEnabled(false);
		bjData.down=new JLabel("下一页",JLabel.CENTER);
		bjData.down.setBounds(290,480,64,32);
		bjData.down.setFont(font);
		bjData.down.addMouseListener(buttonEvent);
		bjData.down.setBackground(color_mr);
		bjData.down.setOpaque(true);
		bjData.down.setBorder(BorderFactory.createLineBorder(color));
		bjData.down.setEnabled(false);
		bjData.jump=new JLabel("跳页",JLabel.CENTER);
		bjData.jump.setBounds(410,480,64,32);
		bjData.jump.setFont(font);
		bjData.jump.addMouseListener(buttonEvent);
		bjData.jump.setBackground(color_mr);
		bjData.jump.setOpaque(true);
		bjData.jump.setBorder(BorderFactory.createLineBorder(color));
		bjData.jump.setEnabled(false);
		bjData.first=new JLabel("首页",JLabel.CENTER);
		bjData.first.setBounds(514,480,64,32);
		bjData.first.setFont(font);
		bjData.first.addMouseListener(buttonEvent);
		bjData.first.setBackground(color_mr);
		bjData.first.setOpaque(true);
		bjData.first.setBorder(BorderFactory.createLineBorder(color));
		bjData.first.setEnabled(false);
		bjData.buff=new String[8];
		bjData.dao=new ImgDaoEdit();
		
		
		jl_sz=new JLabel("设置",JLabel.CENTER);
		jl_sz.setBounds(288,5,57,45);
		jl_sz.setFont(font);
		jl_sz.addMouseListener(mouseEventDo);
		jl_sz.setOpaque(false);
		jl_sz_openSC=new JLabel(openSC);
		jl_sz_openSC.setBounds(98,64,150,40);
		jl_sz_openSC.addMouseListener(mouseEventDo);
		jl_sz_openSC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jl_sz_setSC=new JLabel(setSC);
		jl_sz_setSC.setBounds(394,64,150,40);
		jl_sz_setSC.addMouseListener(mouseEventDo);
		jl_sz_setSC.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jl_sz_scml=new JLabel("收藏目录：");
		jl_sz_scml.setBounds(98,16,80,32);
		jl_sz_scml.setFont(font);
		jl_sz_pay=new JLabel(pay);
		jl_sz_pay.setBounds(98,152,424,288);
		jl_sz_pay.addMouseListener(mouseEventDo);
		jl_sz_payCode=new JLabel(payCode);
		jl_sz_payCode.setSize(472,415);
		jl_sz_payCode.setLocation(jf.getX()+(jf.getWidth()-jl_sz_payCode.getWidth()/2),jf.getY()+(jf.getHeight()-jl_sz_payCode.getHeight()/2));
		/*System.out.println(jf.getX()+(jf.getWidth()-jl_sz_payCode.getWidth()/2));
		System.out.println(jf.getY()+(jf.getHeight()-jl_sz_payCode.getHeight()/2));*/
		jt_sz_scml=new JTextField();
		jt_sz_scml.setText(seveSrc);
		jt_sz_scml.setBounds(178,16,368,32);
		jt_sz_scml.setBorder(BorderFactory.createLineBorder(color));
		
		jl_zz_view0=new JLabel();
		jl_zz_view0.setBounds(34,96,280,304);
		jl_zz_view0.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_view0.setLayout(null);
		jl_zz_view1=new JLabel();
		jl_zz_view1.setBounds(0,0,280,304);
		jl_zz_view1.setVerticalAlignment(JLabel.TOP);
		jl_zz_view1.setHorizontalAlignment(JLabel.LEFT);
		jl_zz_view1.setFont(new Font("宋体",0,24));
		jl_zz_view2=new JLabel();
		jl_zz_view2.setBounds(0,0,280,304);
		jl_zz_view2.addMouseListener(mouseEventDo);
		jl_zz_new=new JLabel("新建文字",JLabel.CENTER);
		jl_zz_new.setBounds(338,296,88,32);
		jl_zz_new.addMouseListener(buttonEvent);
		jl_zz_new.setOpaque(true);
		jl_zz_new.setFont(font);
		jl_zz_new.setBackground(color_mr);
		jl_zz_new.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_font=new JLabel("文字设置",JLabel.CENTER);
		jl_zz_font.setBounds(442,296,88,32);
		jl_zz_font.addMouseListener(buttonEvent);
		jl_zz_font.setOpaque(true);
		jl_zz_font.setFont(font);
		jl_zz_font.setBackground(color_mr);
		jl_zz_font.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_del=new JLabel("重置",JLabel.CENTER);
		jl_zz_del.setBounds(546,296,56,32);
		jl_zz_del.addMouseListener(buttonEvent);
		jl_zz_del.setOpaque(true);
		jl_zz_del.setFont(font);
		jl_zz_del.setBackground(color_mr);
		jl_zz_del.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_finish=new JLabel("完成",JLabel.CENTER);
		jl_zz_finish.setBounds(426,368,88,32);
		jl_zz_finish.addMouseListener(buttonEvent);
		jl_zz_finish.setOpaque(true);
		jl_zz_finish.setFont(font);
		jl_zz_finish.setBackground(color_mr);
		jl_zz_finish.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_return=new JLabel("返回",JLabel.CENTER);
		jl_zz_return.setBounds(34,16,88,32);
		jl_zz_return.addMouseListener(buttonEvent);
		jl_zz_return.setOpaque(true);
		jl_zz_return.setFont(font);
		jl_zz_return.setBackground(color_mr);
		jl_zz_return.setBorder(BorderFactory.createLineBorder(color));
		jt_zz_input=new JTextArea();
		jt_zz_input.setBounds(338,96,264,184);
		jt_zz_input.addKeyListener(keyEventDo);
		jt_zz_input.setFont(new Font("宋体",0,24));
		jt_zz_input.setBorder(BorderFactory.createLineBorder(color));
		
		jl_zz_text_font=new JLabel("字体：");
		jl_zz_text_font.setFont(font);
		jl_zz_text_font.setBounds(16,16,40,40);
		jl_zz_text_color=new JLabel();
		jl_zz_text_color.setBounds(180,18,36,36);
		jl_zz_text_color.setOpaque(true);
		jl_zz_text_color.setBackground(Color.BLACK);
		jl_zz_text_color.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_text_color.addMouseListener(mouseEventDo);
		jl_zz_text_colorT=new JLabel("颜色：");
		jl_zz_text_colorT.setFont(font);
		jl_zz_text_colorT.setBounds(138,16,40,40);
		jl_zz_text_ok=new JLabel("确定",JLabel.CENTER);
		jl_zz_text_ok.setBounds(32,72,56,32);
		jl_zz_text_ok.addMouseListener(buttonEvent);
		jl_zz_text_ok.setOpaque(true);
		jl_zz_text_ok.setFont(font);
		jl_zz_text_ok.setBackground(color_mr);
		jl_zz_text_ok.setBorder(BorderFactory.createLineBorder(color));
		jl_zz_text_not=new JLabel("取消",JLabel.CENTER);
		jl_zz_text_not.setBounds(136,72,56,32);
		jl_zz_text_not.addMouseListener(buttonEvent);
		jl_zz_text_not.setOpaque(true);
		jl_zz_text_not.setFont(font);
		jl_zz_text_not.setBackground(color_mr);
		jl_zz_text_not.setBorder(BorderFactory.createLineBorder(color));
		jt_zz_font=new JTextField();
		jt_zz_font.setText("24");
		jt_zz_font.setBounds(56,18,36,36);
		jt_zz_font.addKeyListener(keyEventDo);
		jt_zz_font.setFont(font);
		jt_zz_font.setBorder(BorderFactory.createLineBorder(color));
		
		
		jl_updata_version=new JLabel("",JLabel.CENTER);
		jl_updata_version.setBounds(106,64,160,40);
		//jl_updata_version.setAlignmentX(JLabel.CENTER);
		jl_updata_version.setFont(font);
		jl_updata_version.setBorder(BorderFactory.createLineBorder(color));
		jl_updata_nowVersion=new JLabel("",JLabel.CENTER);
		//jl_updata_nowVersion.setAlignmentX(JLabel.CENTER);
		jl_updata_nowVersion.setBounds(370,64,160,40);
		jl_updata_nowVersion.setFont(font);
		jl_updata_nowVersion.setBorder(BorderFactory.createLineBorder(color));
		jl_updata_text=new JLabel("说出来你可能不信，我发现新版本了！",JLabel.CENTER);
		jl_updata_text.setBounds(106,8,424,40);
		jl_updata_text.setFont(new Font("宋体",0,16));
		jl_updata_text.setForeground(new Color(156,215,120));
		jl_updata_text.setBorder(BorderFactory.createLineBorder(color));
		jl_updata_pass=new JLabel("");
		jl_updata_pass.setBounds(166,368,96,40);
		jl_updata_pass.setFont(new Font("宋体",0,16));
		jl_updata_pass.setForeground(Color.RED);
		jl_updata_passT=new JLabel("访问密码：",JLabel.CENTER);
		jl_updata_passT.setBounds(106,368,60,40);
		jl_updata_passT.setFont(font);
		
		Image img=null;
		float len_hei;
		jl_updata_img1=new JLabel();
		jl_updata_img1.setBounds(106,416,112,88);
		img =password.getImage();
		len_hei = (float)img.getWidth(jl_updata_img1)/(float)img.getWidth(jl_updata_img1);
		img= img.getScaledInstance((int) (jl_updata_img1.getWidth()*len_hei),jl_updata_img1.getHeight(),0);//设置图片尺寸（与图片框一样大小）
		jl_updata_img1.setIcon(new ImageIcon(img));
		jl_updata_img1.setFont(font);
		
		jl_updata_img2=new JLabel();
		jl_updata_img2.setBounds(418,416,112,88);
		img =clickThis.getImage();
		len_hei = (float)img.getWidth(jl_updata_img2)/(float)img.getWidth(jl_updata_img2);
		img= img.getScaledInstance((int) (jl_updata_img2.getWidth()*len_hei),jl_updata_img2.getHeight(),0);//设置图片尺寸（与图片框一样大小）
		jl_updata_img2.setIcon(new ImageIcon(img));
		jl_updata_img2.setFont(font);
		
		jt_updata_inf=new JTextArea();
		jt_updata_inf.setBounds(106,120,424,232);
		jt_updata_inf.setFont(new Font("宋体", 0, 16));
		jt_updata_inf.setForeground(new Color(58,58,58));
		jt_updata_inf.setLineWrap(true);
		jt_updata_inf.setBorder(BorderFactory.createLineBorder(color));
		jl_updata_up=new JLabel("更新",JLabel.CENTER);
		jl_updata_up.setBounds(418,368,112,40);
		jl_updata_up.setFont(font);
		jl_updata_up.addMouseListener(buttonEvent);
		jl_updata_up.setOpaque(true);
		jl_updata_up.setBackground(color_mr);
		jl_updata_up.setBorder(BorderFactory.createLineBorder(color));
		
		
		
		jl_close=new JLabel(do_closeNull);
		jl_close.setBounds(615,16,23,23);
		jl_close.addMouseListener(mouseEventDo);
		jl_small=new JLabel(do_smallNull);
		jl_small.setBounds(577,16,23,23);
		jl_small.addMouseListener(mouseEventDo);
		jl_updateT=new JLabel("版本状态：");
		jl_updateT.setBounds(22,10,72,16);
		jl_updateT.setFont(font);
		jl_update=new JLabel("已是最新版");
		jl_update.setBounds(94,10,72,16);
		jl_update.setFont(font);
		jl_update.addMouseListener(mouseEventDo);
		jl_joinT=new JLabel("官方交流");
		jl_joinT.setBounds(472,10,72,16);
		jl_joinT.setFont(font);
		jl_join=new JLabel(joinGroup);
		jl_join.setBounds(544,7,96,24);
		jl_join.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jl_join.addMouseListener(mouseEventDo);
		jl_notice=new JLabel("公告：");
		jl_notice.setBounds(22,36,616,16);
		jl_notice.setFont(font);
		jl_load=new JLabel(loading);
		jl_load.setBounds(0,0,128,128);

		
		ssData.img=new JLabel[8];
		ttData.img=new JLabel[8];
		bjData.img=new JLabel[8];
		for(int i=0;i<8;i++){
			ssData.img[i]=new JLabel();
			ssData.img[i].setBorder(BorderFactory.createLineBorder(color));
			ssData.img[i].addMouseListener(imgEvent);
			ssData.img[i].setText("0-"+i);
			ssData.img[i].setForeground(Color.WHITE);
			ssData.img[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jp_ss.add(ssData.img[i]);
			ttData.img[i]=new JLabel();
			ttData.img[i].setBorder(BorderFactory.createLineBorder(color));
			ttData.img[i].addMouseListener(imgEvent);
			ttData.img[i].setText("1-"+i);
			ttData.img[i].setForeground(Color.WHITE);
			ttData.img[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jp_tt.add(ttData.img[i]);
			bjData.img[i]=new JLabel();
			bjData.img[i].setBorder(BorderFactory.createLineBorder(color));
			bjData.img[i].addMouseListener(imgEvent);
			bjData.img[i].setText("2-"+i);
			bjData.img[i].setForeground(Color.WHITE);
			bjData.img[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jp_bj.add(bjData.img[i]);
			if(i<4){
				ssData.img[i].setBounds(2+160*i,72,150,180);
				ttData.img[i].setBounds(2+160*i,32,150,180);
				bjData.img[i].setBounds(2+160*i,32,150,180);
			}else{
				ssData.img[i].setBounds(2+160*(i-4),272,150,180);
				ttData.img[i].setBounds(2+160*(i-4),264,150,180);
				bjData.img[i].setBounds(2+160*(i-4),248,150,180);
			}
			
		}
		
		
		jt_ss_search=new JTextField();
		jt_ss_search.setBounds(12,8,224,24);
		jt_ss_search.setBorder(null);
		jt_ss_search.setBackground(new Color(249,249,249));
		jt_ss_search.setForeground(new Color(0,221,231));
		jt_ss_search.addKeyListener(keyEventDo);
		ssData.jt=new JTextField();
		ssData.jt.setBounds(385,480,40,32);
		ssData.jt.setBorder(BorderFactory.createLineBorder(color));
		ssData.jt.addKeyListener(keyEventDo);
		ttData.jt=new JTextField();
		ttData.jt.setBounds(369,480,40,32);
		ttData.jt.setBorder(BorderFactory.createLineBorder(color));
		ttData.jt.addKeyListener(keyEventDo);
		bjData.jt=new JTextField();
		bjData.jt.setBounds(369,480,40,32);
		bjData.jt.setBorder(BorderFactory.createLineBorder(color));
		bjData.jt.addKeyListener(keyEventDo);

		
		/*jpb_ss=new JProgressBar(SwingConstants.HORIZONTAL,0,100);
		jpb_ss.setBounds(71, 466, 556, 3);
		jpb_tt=new JProgressBar(SwingConstants.HORIZONTAL,0,100);
		jpb_tt.setBounds(71, 460, 556, 3);
		jpb_bj=new JProgressBar(SwingConstants.HORIZONTAL,0,100);
		jpb_bj.setBounds(71, 460, 556, 3);*/
		
		
		
		//jd.add(jl_load);
		jp_top.add(jl_ss);
		jp_top.add(jl_tt);
		jp_top.add(jl_bj);
		jp_top.add(jl_sz);
		jp_top.add(jl_close);
		jp_top.add(jl_small);
		jp_center.add(jp_ss);
		jp_center.add(jp_tt);
		jp_center.add(jp_bj);
		jp_center.add(jp_sz);
		jp_center.add(jp_zz);
		jp_center.add(jp_updata);
		jp_botton.add(jl_updateT);
		jp_botton.add(jl_update);
		jp_botton.add(jl_joinT);
		jp_botton.add(jl_join);
		jp_botton.add(jl_notice);
		
		jp_ss.add(jl_ss_search_back);
		//jp_ss.add(jl_ss_load);
		//jp_ss.add(jpb_ss);
		jp_ss.add(ssData.jump);
		jp_ss.add(jl_ss_allNub);
		jp_ss.add(ssData.up);
		jp_ss.add(ssData.down);
		jp_ss.add(ssData.jt);
		jp_ss.add(ssData.first);
		jp_ss.add(ssData.page);
		
		jp_tt.add(jl_tt_title1);
		jp_tt.add(jl_tt_title2);
		//jp_tt.add(jl_tt_load);
		//jp_tt.add(jpb_tt);
		jp_tt.add(ttData.jump);
		jp_tt.add(ttData.up);
		jp_tt.add(ttData.down);
		jp_tt.add(ttData.jt);
		jp_tt.add(ttData.first);
		jp_tt.add(ttData.page);
				
		//jp_bj.add(jl_bj_load);
		//jp_bj.add(jpb_bj);
		jp_bj.add(bjData.jump);
		jp_bj.add(bjData.up);
		jp_bj.add(bjData.down);
		jp_bj.add(bjData.jt);
		jp_bj.add(bjData.first);
		jp_bj.add(bjData.page);
		
		jp_sz.add(jl_sz_scml);
		jp_sz.add(jl_sz_openSC);
		jp_sz.add(jl_sz_setSC);
		jp_sz.add(jl_sz_payCode);
		jp_sz.add(jl_sz_pay);
		jp_sz.add(jt_sz_scml);
		
		jp_zz.add(jl_zz_del);
		jp_zz.add(jl_zz_new);
		jp_zz.add(jl_zz_font);
		jp_zz.add(jl_zz_finish);
		jp_zz.add(jl_zz_return);
		jp_zz.add(jt_zz_input);
		jp_zz.add(jl_zz_view0);
		jl_zz_view0.add(jl_zz_view1);
		jl_zz_view0.add(jl_zz_view2);
		jd_zz_text.add(jl_zz_text_back);
		jl_zz_text_back.add(jl_zz_text_color);
		jl_zz_text_back.add(jl_zz_text_font);
		jl_zz_text_back.add(jl_zz_text_not);
		jl_zz_text_back.add(jl_zz_text_ok);
		jl_zz_text_back.add(jt_zz_font);
		jl_zz_text_back.add(jl_zz_text_colorT);
		
		jp_updata.add(jl_updata_img1);
		jp_updata.add(jl_updata_img2);
		jp_updata.add(jl_updata_nowVersion);
		jp_updata.add(jl_updata_pass);
		jp_updata.add(jl_updata_passT);
		jp_updata.add(jl_updata_text);
		jp_updata.add(jl_updata_up);
		jp_updata.add(jl_updata_version);
		jp_updata.add(jt_updata_inf);
		
		
		
		jl_back.add(jp_top);
		jl_back.add(jp_center);
		jl_back.add(jp_botton);
		
		jl_ss_search_back.add(jl_ss_search_button);
		jl_ss_search_back.add(jt_ss_search);
		

		jf.repaint();
		startDo();
	}
	
	private void startDo() {//页面创建完毕时执行的动作
		//loading();
		File wjj=new File(seveSrc);
		File file=new File("e:/dtzs/"+"config.properties");
		prop = new Properties();
		if  (!wjj .exists()  && !wjj .isDirectory()){//如果文件夹不存在则创建
			wjj .mkdir();    
		}
		if(!file.exists()){//如果文件不存在则创建
		    try {
		        file.createNewFile();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
			try {
				FileOutputStream out = new FileOutputStream (file);
				prop.setProperty("seveSrc", seveSrc);
				prop.store(out, "SeveSrc");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			InputStreamReader in;
			try {
				in = new InputStreamReader (new FileInputStream(file));
				prop.load(in);
				seveSrc=prop.getProperty("seveSrc");
				jt_sz_scml.setText(seveSrc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		/*获取系统信息*/
		Map<String, String> map=SystemDao.getClientInfo();
		nowVersion=Integer.parseInt(map.get("version"));
		t_join=map.get("qqlink");
		t_notice=map.get("notice");
		t_updataUrl=map.get("uploadurl");
		t_passText=map.get("uploadpwd");
		

		jl_notice.setText("公告:"+t_notice);
		if(VERSION<nowVersion){
			jl_update.setText("发现新版本");
			jl_update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jl_update.setForeground(Color.BLUE);
			jl_updata_version.setText("当前版本："+VERSION);
			jl_updata_nowVersion.setText("最新版本："+nowVersion);
			jt_updata_inf.setText(t_INF);
			jl_updata_pass.setText(t_passText);
			jl_ss.setText("搜索");
			jl_tt.setText("套图");
			jl_bj.setText("编辑");
			jl_sz.setText("设置");
			jl_ss.setIcon(null);
			jl_tt.setIcon(null);
			jl_bj.setIcon(null);
			jl_sz.setIcon(null);
			jp_tt.setVisible(false);
			jp_bj.setVisible(false);
			jp_sz.setVisible(false);
			jp_zz.setVisible(false);
			jp_ss.setVisible(false);
			jp_updata.setVisible(true);
		}
		
		
		/*try {
			mu.net_getText("http://www.fengdu.ccoo.cn/forum/thread-2833224-1-1.html", "utf-8");
		} catch (IOException e) {
			System.out.println("访问统计网页失败");
		}*/
		jl_ss_allNub.setText("找到图片数："+ssData.dao.getImgCount());
		ssData.init();
		displayImg(ssData.img, ssData.url, ssData.buff, 0);
		//notLoad();
	}

	public String formatStr(String str){
		return str.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("<span>", "").replaceAll("</span>", "");
	}
	
	
	public int displayImg(JLabel[] jl,String[] urlText,String[] buff,int start){//用于显示图片displayImg(图片框,图片地址,从图片地址哪里开始显示)
		//loading();
		int result=0;
		if(jl==null || urlText==null){
			return -1;
		}
		for(int i=0;i<jl.length;i++){
			if(urlText.length-start>i){
				//System.out.println(urlText[start+i]);
				if(urlText[start+i].indexOf("http")==-1){//由于套图获取的地址没有http前缀，此处补全
					urlText[start+i]="http:"+urlText[start+i];
				}
				System.out.println(urlText[start+i]);
				Image img=null;
				try {
					img = new ImageIcon(new URL(urlText[start+i])).getImage();
				} catch (IOException e) {
					System.out.println("displayImg读取图片数据异常");
				}//读取图片
				float len_hei = (float)img.getWidth(jl[i])/(float)img.getWidth(jl[i]);
				img= img.getScaledInstance((int) (jl[i].getWidth()*len_hei),jl[i].getHeight(),0);//设置图片尺寸（与图片框一样大小）
				jl[i].setIcon(new ImageIcon(img));
				buff[i]=urlText[start+i];
				//jpb_ss.setValue((i+1)*20);
				result++;
			}else{
				break;
			}
		}
		//notLoad();
		return result;
	}

	public void downClick(PanelData pd){//下一页被点击执行的操作
		loading();
		pd.nowPage+=1;
		pd.first.setEnabled(true);
		pd.up.setEnabled(true);
		if(pd.nowPage==pd.allPage){
			pd.down.setEnabled(false);
		}
		displayImg(pd.img,pd.dao.getImgByPage(pd.nowPage),pd.buff,0);
		pd.page.setText(String.valueOf(pd.nowPage)+"/"+String.valueOf(pd.allPage));
		notLoad();
	}
	public void upClick(PanelData pd){//上一页被点击执行的操作
		loading();
		pd.nowPage-=1;
		pd.down.setEnabled(true);
		if(pd.nowPage==1){
			pd.first.setEnabled(false);
			pd.up.setEnabled(false);
		}
		displayImg(pd.img,pd.dao.getImgByPage(pd.nowPage),pd.buff,0);
		pd.page.setText(String.valueOf(pd.nowPage)+"/"+String.valueOf(pd.allPage));
		notLoad();
	}
	public void firstClick(PanelData pd){//首页被点击执行的操作
		loading();
		pd.nowPage=1;
		pd.first.setEnabled(false);
		pd.up.setEnabled(false);
		pd.down.setEnabled(true);
		pd.jump.setEnabled(true);
		displayImg(pd.img,pd.dao.getImgByPage(pd.nowPage),pd.buff,0);
		pd.page.setText(String.valueOf(pd.nowPage)+"/"+String.valueOf(pd.allPage));
		notLoad();
	}
	public void jumpClick(PanelData pd,int toPage){//跳页被点击执行的操作
		loading();
		if(toPage>pd.allPage){
			toPage=pd.allPage-1;
		}
		if(toPage<1){
			toPage=1;
		}
		pd.nowPage=toPage;
		if(pd.nowPage==1){
			pd.first.setEnabled(false);
			pd.up.setEnabled(false);
			pd.down.setEnabled(true);
		}else if(pd.nowPage==pd.allPage){
			pd.first.setEnabled(true);
			pd.up.setEnabled(true);
			pd.down.setEnabled(false);
		}else{
			pd.first.setEnabled(true);
			pd.up.setEnabled(true);
			pd.down.setEnabled(true);
		}
		displayImg(pd.img,pd.dao.getImgByPage(pd.nowPage),pd.buff,0);
		pd.page.setText(String.valueOf(pd.nowPage)+"/"+String.valueOf(pd.allPage));
		notLoad();
	}



	
	private void loading(){
		/*new Thread(){
			public void run(){
				isLoad=true;
				jd1=new JDialog(jf);
				jd1.setSize(128,128);
				jd1.setUndecorated(true);
				//jd1.add(jl_load);
				jd1.setLocation(jf.getX()+(jf.getWidth()-jd1.getWidth())/2, jf.getY()+(jf.getHeight()-jd1.getHeight())/2);
				jd1.setVisible(true);
				while(isLoad){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						System.out.println("loading()休眠异常");
					}
				}
				jd1.setVisible(false);jd1=null;
			}
		}.start();*/
	}
	private void notLoad(){
		//isLoad=false;
	}
	
	class KeyEventDo extends KeyAdapter{

		public void keyPressed(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyTyped(KeyEvent e) {
			if(e.getSource() == ssData.jt || e.getSource() == jt_zz_font || e.getSource() == ttData.jt || e.getSource() == bjData.jt){
				//System.out.println("in");
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				}
			}
			if(e.getSource()==jt_zz_input){
				jl_zz_view1.setText(jt_zz_input.getText());
			}
			if(e.getSource()==jt_ss_search && !jt_ss_search.getText().equals("")  && e.getKeyChar()==KeyEvent.VK_ENTER){
				findData.keyWord=jt_ss_search.getText();
				findData.init();
				displayImg(findData.img,findData.url,findData.buff,0);
			}
			if(e.getSource()==ssData.jt && !ssData.jt.getText().equals("") && e.getKeyChar()==KeyEvent.VK_ENTER){
				jumpClick(ssData,Integer.valueOf(ssData.jt.getText()));
				ssData.jt.setText("");
			}
			if(e.getSource()==ttData.jt && !ttData.jt.getText().equals("") && e.getKeyChar()==KeyEvent.VK_ENTER){
				jumpClick(ttData,Integer.valueOf(ttData.jt.getText()));
				ttData.jt.setText("");
			}
			if(e.getSource()==bjData.jt && !bjData.jt.getText().equals("") && e.getKeyChar()==KeyEvent.VK_ENTER){
				jumpClick(bjData,Integer.valueOf(bjData.jt.getText()));
				bjData.jt.setText("");
			}
		}
		
	}
	
	class ImgEvent extends MouseAdapter {
		JLabel jl;
		JPanel jp=null;
		String[] str;
		String url=null,time;
		Image img=null;
		Date nowTime;
		SimpleDateFormat ft;
		public void mouseClicked(MouseEvent e) {//单击
			nowTime=new Date();
			ft=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			time=ft.format(nowTime);
			jl=(JLabel) e.getSource();
			str=jl.getText().split("-");
			if(str[0].equals("0")){
				url=ssData.buff[Integer.valueOf(str[1])];
				jp=jp_ss;
			}else if(str[0].equals("1")){
				url=ttData.buff[Integer.valueOf(str[1])];
				jp=jp_tt;
			}else{
				url=bjData.buff[Integer.valueOf(str[1])];
				jp=jp_bj;
			}
			if(e.getButton() == MouseEvent.BUTTON3){//是否是鼠标右键
				if(url.substring(url.lastIndexOf(".")+1).equals("gif")){
					try {
						mu.seve_img(url, seveSrc+time+".gif");
						jl_paoT.setText("已保存");
					} catch (IOException e1) {
						jl_paoT.setText("保存失败");
						System.out.println("保存图片失败");
						e1.printStackTrace();
					}
					jd_pao.setLocation(jf.getX()+jp_center.getX()+jp.getX()+jl.getX()+e.getX()+12, jf.getY()+jp_center.getY()+jp.getY()+jl.getY()+e.getY());
					jd_pao.setVisible(true);
					new Thread(){
						public void run(){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							jd_pao.setVisible(false);
						}
					}.start();
				}else{
					try {
						mu.seve_img(url, seveSrc+time+".jpg");
						jl_paoT.setText("已保存");
					} catch (IOException e1) {
						jl_paoT.setText("保存失败");
						System.out.println("保存图片失败");
						e1.printStackTrace();
					}
					jd_pao.setLocation(jf.getX()+jp_center.getX()+jp.getX()+jl.getX()+e.getX()+12, jf.getY()+jp_center.getY()+jp.getY()+jl.getY()+e.getY());
					jd_pao.setVisible(true);
					new Thread(){
						public void run(){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							jd_pao.setVisible(false);
						}
					}.start();
				}
			}else{
				if(str[0].equals("2")){//是否是编辑图片
					jp_ss.setVisible(false);
					jp_tt.setVisible(false);
					jp_bj.setVisible(false);
					jp_sz.setVisible(false);
					jp_zz.setVisible(true);
					
					Image img=null;
					try {
						img = new ImageIcon(new URL(url)).getImage();
					} catch (IOException e1) {
						System.out.println("bj_zz读取图片数据异常");
					}//读取图片
					float len_hei = (float)img.getWidth(jl_zz_view0)/(float)img.getWidth(jl_zz_view0);
					img= img.getScaledInstance((int) (jl_zz_view0.getWidth()*len_hei),jl_zz_view0.getHeight(),0);//设置图片尺寸（与图片框一样大小）
					jl_zz_view0.setIcon(new ImageIcon(img));
					jl_zz_view0.setText(url);
					jl_zz_view0.setForeground(Color.WHITE);
				}else{
					if(url!=null){
						if(url.substring(url.lastIndexOf(".")+1).equals("gif")){
							mu.setText(url);
							jl_paoT.setText("已复制");
							jd_pao.setLocation(jf.getX()+jp_center.getX()+jp.getX()+jl.getX()+e.getX()+12, jf.getY()+jp_center.getY()+jp.getY()+jl.getY()+e.getY());
							jd_pao.setVisible(true);
							new Thread(){
								public void run(){
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									jd_pao.setVisible(false);
								}
							}.start();
						}else{
							try {
								mu.setImage(new ImageIcon(new URL(url)).getImage());
								jd_pao.setLocation(jf.getX()+jp_center.getX()+jp.getX()+jl.getX()+e.getX()+12, jf.getY()+jp_center.getY()+jp.getY()+jl.getY()+e.getY());
								jd_pao.setVisible(true);
								new Thread(){
									public void run(){
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										jd_pao.setVisible(false);
									}
								}.start();
							} catch (MalformedURLException e1) {
								System.out.println("复制JPG出错");
							}
						}
					}
			}
			
			}
			
		}
		
	}
	
	class ButtonEvent extends MouseAdapter{
		JLabel jl=null;
		Robot robot;
		public ButtonEvent(){
			try {
				robot=new Robot();
			} catch (AWTException e) {
				System.out.println("robot创建异常");
			}
		}
		public void mouseClicked(MouseEvent e) {
			jl=(JLabel) e.getSource();
			if(jl.isEnabled()){
				if(e.getSource()==ssData.up){
					upClick(ssData);
				}
				else if(e.getSource()==ssData.down){
					downClick(ssData);
				}
				else if(e.getSource()==ssData.jump && !ssData.jt.getText().equals("")){
					jumpClick(ssData,Integer.valueOf(ssData.jt.getText()));
					ssData.jt.setText("");
				}
				else if(e.getSource()==ssData.first){
					firstClick(ssData);
				}
				else if(e.getSource()==ttData.up){
					upClick(ttData);
				}
				else if(e.getSource()==ttData.down){
					downClick(ttData);
				}
				else if(e.getSource()==ttData.jump && !ttData.jt.getText().equals("")){
					jumpClick(ttData,Integer.valueOf(ttData.jt.getText()));
					ttData.jt.setText("");
				}
				else if(e.getSource()==ttData.first){
					firstClick(ttData);
				}
				else if(e.getSource()==bjData.up){
					upClick(bjData);
				}
				else if(e.getSource()==bjData.down){
					downClick(bjData);
				}
				else if(e.getSource()==bjData.jump && !bjData.jt.getText().equals("")){
					jumpClick(bjData,Integer.valueOf(bjData.jt.getText()));
					bjData.jt.setText("");
				}
				else if(e.getSource()==bjData.first){
					firstClick(bjData);
				}
				else if(e.getSource()==jl_zz_return){
					jp_zz.setVisible(false);
					jp_bj.setVisible(true);
				}
				else if(e.getSource()==jl_zz_finish){
					int x=jl_zz_view0.getX()+jp_zz.getX()+jp_center.getX()+jf.getX()+1;
					int y= jl_zz_view0.getY()+jp_zz.getY()+jp_center.getY()+jf.getY();
					BufferedImage bImg;
					bImg=robot.createScreenCapture(new Rectangle(x,y, jl_zz_view0.getWidth(), jl_zz_view0.getHeight()));
					jl_zz_view0.setIcon(new ImageIcon(bImg));
					mu.setImage(bImg);
				}
				else if(e.getSource()==jl_updata_up){
					try {
						mu.net_browser("https://pan.baidu.com/s/"+t_updataUrl);
					} catch (IOException e1) {
						System.out.println("打开浏览器访问更新页面失败");
						e1.printStackTrace();
					}
					jp_tt.setVisible(false);
					jp_bj.setVisible(false);
					jp_sz.setVisible(false);
					jp_zz.setVisible(false);
					jp_updata.setVisible(false);
					jp_ss.setVisible(true);
				}
				else if(e.getSource()==jl_zz_new){
					int x=jl_zz_view0.getX()+jp_zz.getX()+jp_center.getX()+jf.getX()+1;
					int y= jl_zz_view0.getY()+jp_zz.getY()+jp_center.getY()+jf.getY();
					BufferedImage bImg;
					bImg=robot.createScreenCapture(new Rectangle(x,y, jl_zz_view0.getWidth(), jl_zz_view0.getHeight()));
					jl_zz_view0.setIcon(new ImageIcon(bImg));
					mu.setImage(bImg);
					jl_zz_view1.setLocation(0,0);
					jl_zz_view1.setText("");
					jt_zz_input.setText("");
				}
				else if(e.getSource()==jl_zz_font){
					jd_zz_text.setLocation(jf.getX()+(jf.getWidth()-jd_zz_text.getWidth())/2, jf.getY()+(jf.getHeight()-jd_zz_text.getHeight())/2);
					jd_zz_text.setVisible(true);
				}
				else if(e.getSource()==jl_zz_del){
					Image img=null;
					try {
						img = new ImageIcon(new URL(jl_zz_view0.getText())).getImage();
					} catch (IOException e1) {
						System.out.println("bj_zz读取图片数据异常");
					}//读取图片
					float len_hei = (float)img.getWidth(jl_zz_view0)/(float)img.getWidth(jl_zz_view0);
					img= img.getScaledInstance((int) (jl_zz_view0.getWidth()*len_hei),jl_zz_view0.getHeight(),0);//设置图片尺寸（与图片框一样大小）
					jl_zz_view0.setIcon(new ImageIcon(img));
					jl_zz_view1.setLocation(0,0);
					jl_zz_view1.setText("");
					jt_zz_input.setText("");
				}
				else if(e.getSource()==jl_zz_text_ok){
					zz_font=new Font("宋体", 0, Integer.valueOf(jt_zz_font.getText()));
					jl_zz_view1.setFont(zz_font);
					jl_zz_view1.setForeground(zz_fontColor);
					jt_zz_input.setFont(zz_font);
					jt_zz_input.setForeground(zz_fontColor);
					jd_zz_text.setVisible(false);
				}
				else if(e.getSource()==jl_zz_text_not){
					jd_zz_text.setVisible(false);
				}
			}
			
		}
		public void mouseEntered(MouseEvent e) {
			jl=(JLabel) e.getSource();
			if(jl.isEnabled()){
				jl.setBackground(color_xz);
			}
		}
		public void mouseExited(MouseEvent e) {
			jl=(JLabel) e.getSource();
			if(jl.isEnabled()){
				jl.setBackground(color_mr);
			}
		}
		public void mousePressed(MouseEvent e) {
			jl=(JLabel) e.getSource();
			if(jl.isEnabled()){
				jl.setBackground(color_dj);
			}
		}
		public void mouseReleased(MouseEvent e) {
			jl=(JLabel) e.getSource();
			if(jl.isEnabled()){
				jl.setBackground(color_xz);
			}
		}
	}
	
	class MouseEventDo extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {//单击
			if(e.getSource()==jl_close){
				File file=new File("e:/dtzs/"+"config.properties");
				if(file.isFile() && file.exists()){//如果文件不存在则创建
				    file.delete();
					try {
				        file.createNewFile();
				    } catch (IOException e1) {
				        e1.printStackTrace();
				    }
					try {
						FileOutputStream out = new FileOutputStream (file);
						prop.setProperty("seveSrc", seveSrc);
						prop.store(out, "SeveSrc");
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				System.exit(0);
			}
			else if(e.getSource()==jl_small){
				jf.setExtendedState(JFrame.ICONIFIED);
			}
			else if(e.getSource()==jl_ss){
				jl_ss.setText("");
				jl_tt.setText("套图");
				jl_bj.setText("编辑");
				jl_sz.setText("设置");
				jl_ss.setIcon(back_ss);
				jl_tt.setIcon(null);
				jl_bj.setIcon(null);
				jl_sz.setIcon(null);
				jp_tt.setVisible(false);
				jp_bj.setVisible(false);
				jp_sz.setVisible(false);
				jp_zz.setVisible(false);
				jp_updata.setVisible(false);
				jp_ss.setVisible(true);
			}
			else if(e.getSource()==jl_tt){
				jl_ss.setText("搜索");
				jl_tt.setText("");
				jl_bj.setText("编辑");
				jl_sz.setText("设置");
				jl_ss.setIcon(null);
				jl_tt.setIcon(back_tt);
				jl_bj.setIcon(null);
				jl_sz.setIcon(null);
				jp_ss.setVisible(false);
				jp_bj.setVisible(false);
				jp_sz.setVisible(false);
				jp_zz.setVisible(false);
				jp_updata.setVisible(false);
				jp_tt.setVisible(true);
				if(ttData.allPage==-1){
					ttData.init();
					displayImg(ttData.img,ttData.url,ttData.buff,0);
				}
			}
			else if(e.getSource()==jl_bj){
				jl_ss.setText("搜索");
				jl_tt.setText("套图");
				jl_bj.setText("");
				jl_sz.setText("设置");
				jl_ss.setIcon(null);
				jl_tt.setIcon(null);
				jl_bj.setIcon(back_bj);
				jl_sz.setIcon(null);
				jp_ss.setVisible(false);
				jp_tt.setVisible(false);
				jp_sz.setVisible(false);
				jp_zz.setVisible(false);
				jp_updata.setVisible(false);
				jp_bj.setVisible(true);
				if(bjData.allPage==-1){
					bjData.init();
					displayImg(bjData.img,bjData.url,bjData.buff,0);
				}
			}
			else if(e.getSource()==jl_sz){
				jl_ss.setText("搜索");
				jl_tt.setText("套图");
				jl_bj.setText("编辑");
				jl_sz.setText("");
				jl_ss.setIcon(null);
				jl_tt.setIcon(null);
				jl_bj.setIcon(null);
				jl_sz.setIcon(back_sz);
				jp_ss.setVisible(false);
				jp_tt.setVisible(false);
				jp_bj.setVisible(false);
				jp_zz.setVisible(false);
				jp_updata.setVisible(false);
				jp_sz.setVisible(true);
			}
			
			else if(e.getSource()==jl_join){//加群按钮点击
				try {
					mu.net_browser("http://shang.qq.com/wpa/qunwpa?idkey="+t_join);
				} catch (IOException e1) {
					System.out.println("访问加群链接失败");
				}
			}
			
			else if(e.getSource()==jl_ss_search_button && !jt_ss_search.getText().equals("")){//搜索按钮点击
				findData.keyWord=jt_ss_search.getText();
				findData.init();
				displayImg(findData.img,findData.url,findData.buff,0);
			}
			else if(e.getSource()==jl_zz_view2){
				jl_zz_view1.setLocation(e.getX(),e.getY());
			}
			else if(e.getSource()==jl_update && jl_update.getText().equals("发现新版本")){
				jl_ss.setText("搜索");
				jl_tt.setText("套图");
				jl_bj.setText("编辑");
				jl_sz.setText("设置");
				jl_ss.setIcon(null);
				jl_tt.setIcon(null);
				jl_bj.setIcon(null);
				jl_sz.setIcon(null);
				jp_ss.setVisible(false);
				jp_tt.setVisible(false);
				jp_bj.setVisible(false);
				jp_zz.setVisible(false);
				jp_sz.setVisible(false);
				jp_updata.setVisible(true);
			}
			else if(e.getSource()==jl_zz_text_color){
				Color c;
				c=JColorChooser.showDialog(jd_zz_text, "选择颜色", Color.BLACK);
				if(c!=null){
					zz_fontColor=c;
					jl_zz_text_color.setBackground(zz_fontColor);
				}

			}
			else if(e.getSource()==jl_sz_openSC){
				try {
					java.awt.Desktop.getDesktop().open(new File(seveSrc));
				} catch (IOException e1) {
					System.out.println("打开文件夹失败");
					e1.printStackTrace();
				}
			}
			else if(e.getSource()==jl_sz_setSC){
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int r = chooser.showOpenDialog(null);
				File f;
				if(r==JFileChooser.APPROVE_OPTION){
		            f=chooser.getSelectedFile();
		            seveSrc=f.getPath()+"\\";
		            jt_sz_scml.setText(seveSrc);
		         }
			}
			else if(e.getSource()==jl_sz_pay){
				if(e.getX()<141 && e.getX()>10 && e.getY()<273 && e.getY()>220){
					jd_pay.setLocation(jf.getX()+(jf.getWidth()-jd_pay.getWidth())/2, jf.getY()+(jf.getHeight()-jd_pay.getHeight())/2);
					jd_pay.setVisible(true);
				}
				
			}
		}
		public void mouseEntered(MouseEvent e) {//进入
			if(e.getSource()==jl_close){
				jl_close.setIcon(do_closeClick);
			}
			if(e.getSource()==jl_small){
				jl_small.setIcon(do_smallClick);
			}
			if(e.getSource()==jl_ss){
				jl_ss.setForeground(color);
			}
			if(e.getSource()==jl_tt){
				jl_tt.setForeground(color);
			}
			if(e.getSource()==jl_bj){
				jl_bj.setForeground(color);
			}
			if(e.getSource()==jl_sz){
				jl_sz.setForeground(color);
			}
		}
		public void mouseExited(MouseEvent e) {//移出
			if(e.getSource()==jl_close){
				jl_close.setIcon(do_closeNull);
			}
			if(e.getSource()==jl_small){
				jl_small.setIcon(do_smallNull);
			}
			if(e.getSource()==jl_ss){
				jl_ss.setForeground(Color.BLACK);
			}
			if(e.getSource()==jl_tt){
				jl_tt.setForeground(Color.BLACK);
			}
			if(e.getSource()==jl_bj){
				jl_bj.setForeground(Color.BLACK);
			}
			if(e.getSource()==jl_sz){
				jl_sz.setForeground(Color.BLACK);
			}
			else if(e.getSource()==jl_pay){
				jd_pay.setVisible(false);
			}
		}

		public void mouseReleased(MouseEvent e) {
			if(e.getSource()==ssData.up && ssData.up.isEnabled()){
				ssData.up.setBackground(color_xz);
			}
			if(e.getSource()==ssData.down && ssData.down.isEnabled()){
				ssData.down.setBackground(color_xz);
			}
			if(e.getSource()==ssData.jump && ssData.jump.isEnabled()){
				ssData.jump.setBackground(color_xz);
			}
			if(e.getSource()==ssData.first && ssData.first.isEnabled()){
				ssData.first.setBackground(color_xz);
			}
			if(e.getSource()==ttData.up && ttData.up.isEnabled()){
				ttData.up.setBackground(color_xz);
			}
			if(e.getSource()==ttData.down && ttData.down.isEnabled()){
				ttData.down.setBackground(color_xz);
			}
			if(e.getSource()==ttData.jump && ttData.jump.isEnabled()){
				ttData.jump.setBackground(color_xz);
			}
			if(e.getSource()==ttData.first && ttData.first.isEnabled()){
				ttData.first.setBackground(color_xz);
			}
			if(e.getSource()==bjData.up && bjData.up.isEnabled()){
				bjData.up.setBackground(color_xz);
			}
			if(e.getSource()==bjData.down && bjData.down.isEnabled()){
				bjData.down.setBackground(color_xz);
			}
			if(e.getSource()==bjData.jump && bjData.jump.isEnabled()){
				bjData.jump.setBackground(color_xz);
			}
			if(e.getSource()==bjData.first && bjData.first.isEnabled()){
				bjData.first.setBackground(color_xz);
			}
		}
		
	}

	public static void main(String[] args) {
		new UI();
		/*MyUse mu=new MyUse();
		try {
			System.out.println(mu.net_getText("http://www.doutula.com/zz/list?page=2", "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//ui.text();ui.text();
	}
	void text(){
		/*try {
			System.out.println(mu.net_getText("http://www.doutula.com/photo/list/", "utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		loading();
		String[] str={"http://ww4.sinaimg.cn/bmiddle/9150e4e5ly1fd097njsqwj20g40g40u0.jpg","http://ww2.sinaimg.cn/bmiddle/9150e4e5ly1fd097porbej20g40g40tx.jpg","http://ww3.sinaimg.cn/bmiddle/9150e4e5ly1fd097rzq54j20g40g40u8.jpg","http://ww3.sinaimg.cn/bmiddle/9150e4e5ly1fd097ue6nwj20g40gbjt5.jpg","http://ww2.sinaimg.cn/bmiddle/6af89bc8gw1f8qdf4zw08g205i05i3yb.gif","http://ww4.sinaimg.cn/bmiddle/9150e4e5ly1fctlkzeq49g2046046aj6.gif","http://ww1.sinaimg.cn/bmiddle/9150e4e5ly1fctlknz06tg203r046aq3.gif","http://ww1.sinaimg.cn/bmiddle/9150e4e5ly1fctlknz06tg203r046aq3.gif"};
		System.out.println(displayImg(ssData.img, str,ssData.buff,0));
		notLoad();
	}
}
