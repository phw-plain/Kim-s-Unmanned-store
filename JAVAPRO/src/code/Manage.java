package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.util.RelativeDateFormat;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

public class Manage {
	private Frame mainFrame;
	private JPanel subpanel;
	
	private JPanel panel0;		// menu
	private JPanel panel1;		// 매출 & 지출 기록 및 그래프 
	
	private JPanel panel0_W;
	private JPanel panel0_E;
	private JPanel p1_Today;
	private JPanel p1_Month;
	
	// 기본 정보
	int width;
	int height;
	
	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(255, 255, 255);
	
	// Font
	Font font1 = new Font("배달의민족 주아", Font.PLAIN, 50);
	Font font2 = new Font("배달의민족 주아", Font.PLAIN, 26);
	Font font3 = new Font("배달의민족 주아", Font.PLAIN, 16);
	
	// Image
	ImageIcon logo = new ImageIcon("src/img/logo.png");
	ImageIcon logo_over = new ImageIcon("src/img/logo_over.png");
	ImageIcon i = new ImageIcon("src/img/benner.png");
	Image im=i.getImage();
	
	public Manage() {
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게"); 
	    mainFrame.setSize(1280 ,1024);
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
	    
	    // Icon 변경
	    URL imageURL = Start.class.getClassLoader().getResource("apple.png");
    	ImageIcon img = new ImageIcon(imageURL);
    	mainFrame.setIconImage(img.getImage());
    	
		// 화면 크기
		width = mainFrame.getWidth();
		height = mainFrame.getWidth();
		  
	    // subpanel 세팅
		subpanel = new JPanel(new CardLayout());
		subpanel.setBackground(background);
		
		// panel0 세팅
		panel0 = new JPanel();
		panel0.setBackground(background);
		panel0.setLayout(new GridLayout(3, 1));
		
		// 상단 배너
		MyPanel benner = new MyPanel();
		panel0.add(benner, BorderLayout.EAST);
		
		// 메뉴 화면 생성
		menu();
		
		// 매출 & 지출 기록 및 그래프 화면 생성	
		panel1 = new JPanel(new CardLayout());
		panel1.setBackground(background);
		TodaySales();
		MonthSales();
		panel1.setVisible(false);

		subpanel.add(panel0);
		subpanel.add(panel1);
		
		mainFrame.add(subpanel);
	}
	
	public void menu() {
		panel0_W = new JPanel();
		panel0_E = new JPanel();
		panel0_W.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
	    
		panel0_W.setBackground(background);  
		panel0_E.setBackground(background);  
		
		ImageIcon mBtn_img1 = new ImageIcon("src/img/btn1_1.png");
		ImageIcon mBtn_img2 = new ImageIcon("src/img/btn1_2.png");
		ImageIcon mBtn_img3 = new ImageIcon("src/img/btn1_3.png");
		
		ImageIcon mBtn5_img1 = new ImageIcon("src/img/btn5_1.png");
		ImageIcon mBtn5_img2 = new ImageIcon("src/img/btn5_2.png");
		ImageIcon mBtn5_img3 = new ImageIcon("src/img/btn5_3.png");
		
		// btn1 : 매출 & 지출 기록 및 그래프
		JButton btn1 = new JButton("", mBtn_img1);
		btn1.setRolloverIcon(mBtn_img2); 	// rolloverIcon용 이미지 등록
		btn1.setPressedIcon(mBtn_img3); 	// pressedIcon용 이미지 등록
		btn1.setContentAreaFilled(false);	// 배경 채우기
		btn1.setBorderPainted(false);		// 외각선
		btn1.setFocusPainted(false);		// 선택 외각선
		
		// btn2 : 실수령액 그래프
		JButton btn2 = new JButton("", mBtn_img1);
		btn2.setRolloverIcon(mBtn_img2); 
		btn2.setPressedIcon(mBtn_img3); 
		btn2.setContentAreaFilled(false);
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false);
		
		// btn3 : 재고관리
		JButton btn3 = new JButton("", mBtn_img1);
		btn3.setRolloverIcon(mBtn_img2); 
		btn3.setPressedIcon(mBtn_img3);
		btn3.setContentAreaFilled(false);
		btn3.setBorderPainted(false);
		btn3.setFocusPainted(false);
		
		// btn4 : 마이페이지
		JButton btn4 = new JButton("", mBtn_img1);
		btn4.setRolloverIcon(mBtn_img2); 
		btn4.setPressedIcon(mBtn_img3);
		btn4.setContentAreaFilled(false);	
		btn4.setBorderPainted(false);
		btn4.setFocusPainted(false);
		
		// btn5 : 로그아웃
		JButton btn5 = new JButton("", mBtn5_img1);
		btn5.setRolloverIcon(mBtn5_img2); 
		btn5.setPressedIcon(mBtn5_img3); 
		btn5.setContentAreaFilled(false);	
		btn5.setBorderPainted(false);
		btn5.setFocusPainted(false);
		
		// 버튼 이벤트
		btn1.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           panel0.setVisible(false);
		           panel1.setVisible(true);
		       }
		});
		btn5.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           new Start(); //프레임 전환
		           mainFrame.setVisible(false);
		       }
		});
		
		panel0_W.add(btn1);
		panel0_W.add(btn2);
		panel0_W.add(btn3);
		panel0_E.add(btn4);
		panel0_E.add(btn5);
		
		panel0.add(panel0_W, BorderLayout.EAST);
		panel0.add(panel0_E, BorderLayout.EAST);
		
		panel0.setVisible(true);
	}
	
	public void TodaySales() {
		// p1_Today 세팅
		p1_Today = new JPanel();
		p1_Today.setBackground(background);
		p1_Today.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		
		// home 버튼 생성
		JButton homebtn = new JButton("", logo);
		homebtn.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn.setContentAreaFilled(false);	// 배경 채우기
		homebtn.setBorderPainted(false);		//외각선
		homebtn.setFocusPainted(false);		// 선택 외각선
		
		// home 버튼 이벤트
		homebtn.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   panel0.setVisible(true);
		           panel1.setVisible(false);
		       }
		});
		
		JLabel title = new JLabel("매출 및 지출");
		title.setFont(font2);

		header.add(homebtn, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		HalfRoundedButton daybtn = new HalfRoundedButton("   일       ");
		HalfRoundedButton monthbtn = new HalfRoundedButton("   월       ");
		
		daybtn.setFont(font3);
		monthbtn.setFont(font3);
		
		// 버튼 이벤트
		monthbtn.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           p1_Today.setVisible(false); // 화면 전환
		           p1_Month.setVisible(true);
		       }
		});
		
		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);
		
		// sales
		JPanel rightpanel = new JPanel(new GridLayout(50, 1, 0, 0));
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));	// 위 왼 아 오
		JLabel stitle = new JLabel("Today");
		stitle.setFont(font3);
		JLabel sales = new JLabel("▲ 350,000");
		sales.setFont(new Font("돋움체", Font.PLAIN, 16));
		sales.setForeground(Color.red);
		
		rightpanel.add(stitle);
		rightpanel.add(sales);
		
		// graph
		JPanel chartPanel = createDemoPanel(1);
	    
		p1_Today.add(header, BorderLayout.NORTH);
		p1_Today.add(leftpanel, BorderLayout.WEST);
		p1_Today.add(rightpanel, BorderLayout.EAST);
		p1_Today.add(chartPanel, BorderLayout.CENTER);
		
		p1_Today.setVisible(true);
		panel1.add(p1_Today);
	}
	
	public void MonthSales() {
		// p1_Month 세팅
		p1_Month = new JPanel();
		p1_Month.setBackground(background);
		p1_Month.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		JButton homebtn = new JButton("", logo);
		homebtn.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn.setContentAreaFilled(false);	// 배경 채우기
		homebtn.setBorderPainted(false);		//외각선
		homebtn.setFocusPainted(false);		// 선택 외각선
		
		// home 버튼 이벤트
		homebtn.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   panel0.setVisible(true);
		           panel1.setVisible(false);
		       }
		});
		
		JLabel title = new JLabel("매출 및 지출");
		title.setFont(font2);

		header.add(homebtn, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		HalfRoundedButton daybtn = new HalfRoundedButton("   일       ");
		HalfRoundedButton monthbtn = new HalfRoundedButton("   월       ");
		
		daybtn.setFont(font3);
		monthbtn.setFont(font3);
		
		// 버튼 이벤트
		daybtn.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		           p1_Today.setVisible(true); // 화면 전환
		           p1_Month.setVisible(false);
		       }
		});
		
		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);
		
		// sales
		JPanel rightpanel = new JPanel(new GridLayout(50, 1, 0, 0));
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 100));	// 위 왼 아 오
		JLabel stitle = new JLabel("Month");
		stitle.setFont(font3);
		JLabel sales = new JLabel("▲ 780,000");
		sales.setFont(new Font("돋움체", Font.PLAIN, 16));
		sales.setForeground(Color.red);
		
		rightpanel.add(stitle);
		rightpanel.add(sales);
		
		// graph
		JPanel chartPanel = createDemoPanel(2);
	    
		p1_Month.add(header, BorderLayout.NORTH);
		p1_Month.add(leftpanel, BorderLayout.WEST);
		p1_Month.add(rightpanel, BorderLayout.EAST);
		p1_Month.add(chartPanel, BorderLayout.CENTER);
		
		p1_Month.setVisible(false);
		panel1.add(p1_Month);
	}
	
	public static JPanel createDemoPanel(int idx) {
		JFreeChart chart = createChart(createDataset(idx));
        return new ChartPanel(chart);
	}
	
	private static String str1 = "";
	private static String str2 = "";
	private static String str3 = "";
	
	private static XYDataset createDataset(int idx) {
		
		switch(idx) {
		case 1:	{
				str1 = "이번주";
				str2 = "지난주";
				str3 = "TodaySales";
			}	break;
		case 2:	{
				str1 = "이번달";
				str2 = "지난달";
				str3 = "MonthSales";
			}	break;
		}
		TimeSeries s1 = new TimeSeries(str1);
        s1.add(new Second(45, 6, 9, 1, 10, 2006), 143);
        s1.add(new Second(33, 8, 9, 1, 10, 2006), 167);
        s1.add(new Second(10, 10, 9, 1, 10, 2006), 189);
        s1.add(new Second(19, 12, 9, 1, 10, 2006), 156);
        s1.add(new Second(5, 15, 9, 1, 10, 2006), 176);
        s1.add(new Second(12, 16, 9, 1, 10, 2006), 183);
        s1.add(new Second(6, 18, 9, 1, 10, 2006), 138);
        s1.add(new Second(11, 20, 9, 1, 10, 2006), 102);

		TimeSeries s2 = new TimeSeries(str2);
        s2.add(new Second(6, 18, 9, 1, 10, 2006), 143);
        s2.add(new Second(11, 20, 9, 1, 10, 2006), 167);
        s2.add(new Second(12, 16, 9, 1, 10, 2006), 189);
        s2.add(new Second(5, 15, 9, 1, 10, 2006), 156);
        s2.add(new Second(19, 12, 9, 1, 10, 2006), 176);
        s2.add(new Second(10, 10, 9, 1, 10, 2006), 183);
        s2.add(new Second(33, 8, 9, 1, 10, 2006), 102);
        s2.add(new Second(45, 6, 9, 1, 10, 2006), 138);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);

        return dataset;
    }
	
	 private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            str3,        	// title
            "",       	// x-axis label
            "",  	// y-axis label
            dataset,            	// data
            true,               	// create legend?
            true,               	// generate tooltips?
            false              		// generate URLs?
        );

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        Minute base = new Minute(0, 9, 1, 10, 2006);
        RelativeDateFormat rdf = new RelativeDateFormat(base.getFirstMillisecond());
        rdf.setSecondFormatter(new DecimalFormat("00"));
        axis.setDateFormatOverride(rdf);

        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }
	
	class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}