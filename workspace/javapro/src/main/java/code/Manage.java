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
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RefineryUtilities;

import code.Manage.MyPanel;

public class Manage extends Setting {
	private Frame mainFrame;
	private JPanel subpanel;

	private JPanel menu; 				// 메뉴
	private JPanel pWest;
	private JPanel pEast;
	
	private Sales sales; 				// 매출 & 지출 기록 및 그래프
	private NetIncome netincome;	 	// 실수령액 그래프
	private Inventory inventory; 		// 재고 관리
	private MyPage mypage; 				// 마이페이지

	public Manage() {
		i = new ImageIcon("src/img/benner.png");
		im = i.getImage();
		
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게");
		mainFrame.setSize(1280, 1024);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.setIconImage(img.getImage());		// Icon 변경

		// 화면 크기
		width = mainFrame.getWidth();
		height = mainFrame.getWidth();

		// subpanel 세팅
		subpanel = new JPanel(new CardLayout());
		subpanel.setBackground(background);

		// 메뉴 화면 생성
		menu();

		// 객체 할당
		sales = new Sales(); 
		netincome = new NetIncome();
		mypage = new MyPage();
		inventory = new Inventory();
		
		// 홈 버튼 이벤트 적용
		homeevt();
		
		sales.setVisible(false);
		netincome.setVisible(false);
		inventory.setVisible(false);
		mypage.setVisible(false);

		subpanel.add(menu);
		subpanel.add(sales.panel);
		subpanel.add(netincome.panel);
		subpanel.add(inventory.panel);
		subpanel.add(mypage.panel);

		mainFrame.add(subpanel);
	}

	private void menu() {
		menu = new JPanel();
		menu.setBackground(background);
		menu.setLayout(new GridLayout(3, 1));

		// 상단 배너
		MyPanel benner = new MyPanel();
		menu.add(benner, BorderLayout.EAST);
		
		pWest = new JPanel();
		pEast = new JPanel();
		pWest.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		pWest.setBackground(background);
		pEast.setBackground(background);

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
		btn1.setContentAreaFilled(false); 	// 배경 채우기
		btn1.setBorderPainted(false); 		// 외각선
		btn1.setFocusPainted(false); 		// 선택 외각선

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
				menu.setVisible(false);
				sales.setVisible(true);
				netincome.setVisible(false);
				inventory.setVisible(false);
				mypage.setVisible(false);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(true);
				inventory.setVisible(false);
				mypage.setVisible(false);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(false);
				inventory.setVisible(true);
				mypage.setVisible(false);
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(false);
				inventory.setVisible(false);
				mypage.setVisible(true);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Start(); // 프레임 전환
				mainFrame.setVisible(false);
			}
		});

		pWest.add(btn1);
		pWest.add(btn2);
		pWest.add(btn3);
		pEast.add(btn4);
		pEast.add(btn5);

		menu.add(pWest, BorderLayout.EAST);
		menu.add(pEast, BorderLayout.EAST);

		menu.setVisible(true);
	}

	private void homeevt() {
		// home 버튼 이벤트
		sales.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				sales.setVisible(false);
			}
		});
		sales.homebtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				sales.setVisible(false);
			}
		});
		netincome.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				netincome.setVisible(false);
			}
		});
		netincome.homebtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				netincome.setVisible(false);
			}
		});
		mypage.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				mypage.setVisible(false);
			}
		});
		inventory.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				inventory.setVisible(false);
			}
		});
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(im, 0, 0, getWidth(), getHeight(), this);
		}
	}
}