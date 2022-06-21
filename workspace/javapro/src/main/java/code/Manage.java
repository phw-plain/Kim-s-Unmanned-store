package code;

import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.*;

public class Manage extends Setting {
	private Frame mainFrame;
	private JPanel subpanel;

	private JPanel menu; 				// 메뉴
	private JPanel pWest;
	private JPanel pEast;
	
	private Loading loading;
	private Sales sales; 				// 매출 & 지출 기록 및 그래프
	private NetIncome netincome;	 	// 실수령액 그래프
	private Inventory inventory; 		// 재고 관리
	private Customer customer; 			// 고객 관리
	private MyPage mypage; 				// 마이페이지

	public Manage(String id, String pw) {		
		setId(id);
		setPw(pw);
		
		i = new ImageIcon("src/img/benner.png");
		i = imageSetSize(i, width, height/3);
		
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게");
		mainFrame.setSize(width, height);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(resizable);
		mainFrame.setVisible(true);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		mainFrame.setIconImage(img.getImage());		// Icon 변경

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
		customer = new Customer();
		
		// 홈 버튼 이벤트 적용
		homeevt();
		
		sales.setVisible(false);
		netincome.setVisible(false);
		inventory.setVisible(false);
		customer.setVisible(false);
		mypage.setVisible(false);

		// 로딩 화면
		loading = new Loading();
		loading.setVisible(true);
		mainFrame.add(loading.panel);

		subpanel.add(loading.panel);
		subpanel.add(menu);
		subpanel.add(sales.panel);
		subpanel.add(netincome.panel);
		subpanel.add(inventory.panel);
		subpanel.add(customer.panel);
		subpanel.add(mypage.panel);

		mainFrame.add(subpanel);
		
		new java.util.Timer().schedule( 
	        new java.util.TimerTask() {
	            @Override
	            public void run() {
			    	loading.setVisible(false);
	            	menu.setVisible(true);
	            }
	        }, 
	        7000
		);
	}

	private void menu() {
		menu = new JPanel(new GridLayout(3, 1));

		// 상단 배너
		JPanel benner = new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {
                g.drawImage(i.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
		menu.add(benner);
		
		pWest = new JPanel(new GridLayout(1, 3, 25, 0));
		pWest.setBorder(BorderFactory.createEmptyBorder(width/25, 150, 10, 150));
		pEast = new JPanel(new GridLayout(1, 3, 25, 0));
		pEast.setBorder(BorderFactory.createEmptyBorder(10, 150, width/25, 150));

		pWest.setBackground(background);
		pEast.setBackground(background);

		ImageIcon mBtn1_img[] = new ImageIcon[6];
		ImageIcon mBtn2_img[] = new ImageIcon[6];
		ImageIcon mBtn3_img[] = new ImageIcon[6];
		
		mBtn1_img[0] = new ImageIcon("src/img/btn1_1.png");
		mBtn1_img[1] = new ImageIcon("src/img/btn2_1.png");
		mBtn1_img[2] = new ImageIcon("src/img/btn3_1.png");
		mBtn1_img[3] = new ImageIcon("src/img/btn4_1.png");
		mBtn1_img[4] = new ImageIcon("src/img/btn5_1.png");
		mBtn1_img[5] = new ImageIcon("src/img/btn6_1.png");

		mBtn2_img[0] = new ImageIcon("src/img/btn1_2.png");
		mBtn2_img[1] = new ImageIcon("src/img/btn2_2.png");
		mBtn2_img[2] = new ImageIcon("src/img/btn3_2.png");
		mBtn2_img[3] = new ImageIcon("src/img/btn4_2.png");
		mBtn2_img[4] = new ImageIcon("src/img/btn5_2.png");
		mBtn2_img[5] = new ImageIcon("src/img/btn6_2.png");

		mBtn3_img[0] = new ImageIcon("src/img/btn1_3.png");
		mBtn3_img[1] = new ImageIcon("src/img/btn2_3.png");
		mBtn3_img[2] = new ImageIcon("src/img/btn3_3.png");
		mBtn3_img[3] = new ImageIcon("src/img/btn4_3.png");
		mBtn3_img[4] = new ImageIcon("src/img/btn5_3.png");
		mBtn3_img[5] = new ImageIcon("src/img/btn6_3.png");
		
		// 아이콘 사이즈 변경
		int size = (height > 1000) ? 200 : 150;
		for(int i = 0; i < mBtn1_img.length; i++) {
			mBtn1_img[i] = imageSetSize(mBtn1_img[i], size, size);
			mBtn2_img[i] = imageSetSize(mBtn2_img[i], size, size);
			mBtn3_img[i] = imageSetSize(mBtn3_img[i], size, size);
		}

		// btn1 : 매출 & 지출 기록 및 그래프
		JPanel p1 = new JPanel(new BorderLayout());
		p1.setBackground(Color.white);
		p1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn1 = new JButton("", mBtn1_img[0]);
		btn1.setRolloverIcon(mBtn2_img[0]); 	// rolloverIcon용 이미지 등록
		btn1.setPressedIcon(mBtn3_img[0]); 	// pressedIcon용 이미지 등록
		btn1.setContentAreaFilled(false); 	// 배경 채우기
		btn1.setBorderPainted(false); 		// 외각선
		btn1.setFocusPainted(false); 		// 선택 외각선
		
		JLabel txt1 = new JLabel("매출그래프");
		txt1.setFont(font3);
		txt1.setHorizontalAlignment(JLabel.CENTER);

		p1.add(btn1, BorderLayout.CENTER);
		p1.add(txt1, BorderLayout.SOUTH);
		
		// btn2 : 실수령액 그래프
		JPanel p2 = new JPanel(new BorderLayout());
		p2.setBackground(Color.white);
		p2.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn2 = new JButton("", mBtn1_img[1]);
		btn2.setRolloverIcon(mBtn2_img[1]);
		btn2.setPressedIcon(mBtn3_img[1]);
		btn2.setContentAreaFilled(false);
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false);
		
		JLabel txt2 = new JLabel("실수령액그래프");
		txt2.setFont(font3);
		txt2.setHorizontalAlignment(JLabel.CENTER);

		p2.add(btn2, BorderLayout.CENTER);
		p2.add(txt2, BorderLayout.SOUTH);
		
		// btn3 : 마이페이지
		JPanel p3 = new JPanel(new BorderLayout());
		p3.setBackground(Color.white);
		p3.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn3 = new JButton("", mBtn1_img[2]);
		btn3.setRolloverIcon(mBtn2_img[2]);
		btn3.setPressedIcon(mBtn3_img[2]);
		btn3.setContentAreaFilled(false);
		btn3.setBorderPainted(false);
		btn3.setFocusPainted(false);
		
		JLabel txt3 = new JLabel("마이페이지");
		txt3.setFont(font3);
		txt3.setHorizontalAlignment(JLabel.CENTER);

		p3.add(btn3, BorderLayout.CENTER);
		p3.add(txt3, BorderLayout.SOUTH);

		// btn4 : 재고관리
		JPanel p4 = new JPanel(new BorderLayout());
		p4.setBackground(Color.white);
		p4.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn4 = new JButton("", mBtn1_img[3]);
		btn4.setRolloverIcon(mBtn2_img[3]);
		btn4.setPressedIcon(mBtn3_img[3]);
		btn4.setContentAreaFilled(false);
		btn4.setBorderPainted(false);
		btn4.setFocusPainted(false);
		
		JLabel txt4 = new JLabel("재고관리");
		txt4.setFont(font3);
		txt4.setHorizontalAlignment(JLabel.CENTER);

		p4.add(btn4, BorderLayout.CENTER);
		p4.add(txt4, BorderLayout.SOUTH);

		// btn5 : 고객관리
		JPanel p5 = new JPanel(new BorderLayout());
		p5.setBackground(Color.white);
		p5.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn5 = new JButton("", mBtn1_img[4]);
		btn5.setRolloverIcon(mBtn2_img[4]);
		btn5.setPressedIcon(mBtn3_img[4]);
		btn5.setContentAreaFilled(false);
		btn5.setBorderPainted(false);
		btn5.setFocusPainted(false);
		
		JLabel txt5 = new JLabel("고객관리");
		txt5.setFont(font3);
		txt5.setHorizontalAlignment(JLabel.CENTER);

		p5.add(btn5, BorderLayout.CENTER);
		p5.add(txt5, BorderLayout.SOUTH);
		
		// btn6 : 로그아웃
		JPanel p6 = new JPanel(new BorderLayout());
		p6.setBackground(Color.white);
		p6.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		JButton btn6 = new JButton("", mBtn1_img[5]);
		btn6.setRolloverIcon(mBtn2_img[5]);
		btn6.setPressedIcon(mBtn3_img[5]);
		btn6.setContentAreaFilled(false);
		btn6.setBorderPainted(false);
		btn6.setFocusPainted(false);
		
		JLabel txt6 = new JLabel("로그아웃");
		txt6.setFont(font3);
		txt6.setHorizontalAlignment(JLabel.CENTER);

		p6.add(btn6, BorderLayout.CENTER);
		p6.add(txt6, BorderLayout.SOUTH);

		// 버튼 이벤트
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				sales.setVisible(true);
				netincome.setVisible(false);
				inventory.setVisible(false);
				customer.setVisible(false);
				mypage.setVisible(false);
			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(true);
				inventory.setVisible(false);
				customer.setVisible(false);
				mypage.setVisible(false);
			}
		});
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mypage.reLoad();
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(false);
				inventory.setVisible(false);
				customer.setVisible(false);
				mypage.setVisible(true);
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.reLoad();
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(false);
				inventory.setVisible(true);
				customer.setVisible(false);
				mypage.setVisible(false);
			}
		});
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.reLoad();
				menu.setVisible(false);
				sales.setVisible(false);
				netincome.setVisible(false);
				inventory.setVisible(false);
				customer.setVisible(true);
				mypage.setVisible(false);
			}
		});
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Start();
				
				// 프레임 전환
	    		mainFrame.dispose();
			}
		});

		pWest.add(p1);
		pWest.add(p2);
		pWest.add(p3);
		pEast.add(p4);
		pEast.add(p5);
		pEast.add(p6);

		menu.add(pWest);
		menu.add(pEast);

		menu.setVisible(false);
	}

	public void homeevt() {
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
		sales.homebtn3.addActionListener(new ActionListener() {
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
		mypage.homebtn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				mypage.setVisible(false);
			}
		});	
		mypage.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				mypage.setVisible(false);
			}
		});	
		mypage.homebtn2.addActionListener(new ActionListener() {
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
		inventory.homebtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				inventory.setVisible(false);
			}
		});
		inventory.homebtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				inventory.setVisible(false);
			}
		});
		customer.homebtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				customer.setVisible(false);
			}
		});
		customer.homebtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				customer.setVisible(false);
			}
		});
		customer.homebtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(true);
				customer.setVisible(false);
			}
		});
	}
	
}