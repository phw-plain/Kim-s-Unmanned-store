package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

import firebase.firebase_sales;

public class Sales extends Setting{
	public JPanel panel; // 실수령액 그래프

	private JPanel Today;
	private JPanel Month;
	private JPanel Recode;
	private JPanel chartPanel1;
	private JPanel chartPanel2;
	
	public JButton homebtn1;
	public JButton homebtn2;
	public JButton homebtn3;
	
	public int margin = (height > 1000) ? 20 : 13;
	public int margin2 = (height > 1000) ? 20 : 16;
	public String blank1 = (height > 1000) ? "  " : "";
	public String blank2 = (height > 1000) ? "   " : " ";
	
	public Sales() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);

		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		homebtn3 = new JButton("", logo);
		
		TodaySales();
		MonthSales();
		Recode();
	}
	
	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void TodaySales() {
		// Today 세팅
		Today = new JPanel();
		Today.setBackground(background);
		Today.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); 	// 배경 채우기
		homebtn1.setBorderPainted(false); 		// 외각선
		homebtn1.setFocusPainted(false); 		// 선택 외각선

		JLabel title = new JLabel("매출 및 지출");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(3, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(-1, 0, (int)(height*0.445), 40)); // 위 왼 아 오
		HalfRoundedButton daybtn = new HalfRoundedButton(blank1 + " 일 "+ blank2, Color.orange);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 월 " + blank2);
		HalfRoundedButton recode = new HalfRoundedButton(blank1 + "기록" + blank2);

		daybtn.setFont(font3);
		monthbtn.setFont(font3);
		recode.setFont(font3);

		// 버튼 이벤트
		monthbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Month.setVisible(false);
				MonthSales();
				Month.setVisible(true);
				
				// 화면 전환
				Today.setVisible(false); 
				Month.setVisible(true);
				Recode.setVisible(false);
			}
		});
		recode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Today.setVisible(false); // 화면 전환
				Month.setVisible(false);
				Recode.setVisible(true);
			}
		});

		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);
		leftpanel.add(recode);

		// sales
		JPanel rightpanel = new JPanel(new GridLayout(25, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 67)); // 위 왼 아 오
		JLabel stitle = new JLabel("Today");
		stitle.setFont(font3);
		stitle.setForeground(fontcolor);
		JLabel sales = new JLabel("▲ 350,000");
		sales.setFont(new Font("돋움체", Font.PLAIN, 16));
		sales.setForeground(Color.red);

		rightpanel.add(stitle);
		rightpanel.add(sales);

		// graph
		drawChart(1);

		// footer (공백)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // 위 왼 아 오

		chartPanel1.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // 위 왼 아 오
		
		Today.add(header, BorderLayout.NORTH);
		Today.add(leftpanel, BorderLayout.WEST);
		Today.add(rightpanel, BorderLayout.EAST);
		Today.add(chartPanel1, BorderLayout.CENTER);
		Today.add(footer, BorderLayout.SOUTH);

		Today.setVisible(true);
		panel.add(Today);
	}

	private void MonthSales() {
		// Month 세팅
		Month = new JPanel();
		Month.setBackground(background);
		Month.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		homebtn2.setRolloverIcon(logo_over);	
		homebtn2.setContentAreaFilled(false); 
		homebtn2.setBorderPainted(false);
		homebtn2.setFocusPainted(false);

		JLabel title = new JLabel("매출 및 지출");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(3, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(-1, 0, (int)(height*0.445), 40)); // 위 왼 아 오
		HalfRoundedButton daybtn = new HalfRoundedButton(blank1 + " 일 "+ blank2);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 월 " + blank2, Color.orange);
		HalfRoundedButton recode = new HalfRoundedButton(blank1 + "기록" + blank2);

		daybtn.setFont(font3);
		monthbtn.setFont(font3);
		recode.setFont(font3);

		// 버튼 이벤트
		daybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Today.setVisible(false);
				TodaySales();
				Today.setVisible(true);
				
				// 화면 전환
				Today.setVisible(true); 
				Month.setVisible(false);
				Recode.setVisible(false);
			}
		});
		recode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Today.setVisible(false); // 화면 전환
				Month.setVisible(false);
				Recode.setVisible(true);
			}
		});

		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);
		leftpanel.add(recode);
		
		// sales
		JPanel rightpanel = new JPanel(new GridLayout(2, 1));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(height*0.53), 0)); // 위 왼 아 오

		JLabel stitle = new JLabel("Month");
		stitle.setFont(font3);
		stitle.setForeground(fontcolor);
		JLabel sales = new JLabel("▲ 780,000");
		sales.setFont(new Font("돋움체", Font.PLAIN, 16));
		sales.setForeground(Color.red);

		// 그래프 날짜 버튼 추가
		int year = 2022; // 지금 년도 가져오기
		int[] years = new int[10];

		Vector<String> y = new Vector<String>();
		JButton yearbtn = new JButton("확인");
		yearbtn.setFont(font5);
		yearbtn.setBackground(Color.white);

		for (int i = 0; i < 10; i++) {
			years[i] = year - i;
			y.add(year - i + "년");
		}
		
		JComboBox yearCh = new JComboBox(y);

		JPanel chbox = new JPanel(new BorderLayout());
		chbox.setBackground(background);

		chbox.add(yearCh, BorderLayout.WEST);
		chbox.add(yearbtn, BorderLayout.EAST);
		chbox.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10)); 

		JPanel texts = new JPanel();
		texts.add(stitle);
		texts.add(sales);
		texts.setBackground(background);
		
		rightpanel.add(texts);
		rightpanel.add(chbox);

		yearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Month.setVisible(false);
				panel.remove(1);
				MonthSales();
				Month.setVisible(true);
			}
		});

		// graph
		drawChart(2);

		// footer (공백)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // 위 왼 아 오

		Month.add(header, BorderLayout.NORTH);
		Month.add(leftpanel, BorderLayout.WEST);
		Month.add(rightpanel, BorderLayout.EAST);
		Month.add(chartPanel2, BorderLayout.CENTER);
		Month.add(footer, BorderLayout.SOUTH);

		Month.setVisible(false);
		panel.add(Month);
	}
	
	private void Recode() {
		// Recode 세팅
		Recode = new JPanel();
		Recode.setBackground(background);
		Recode.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		homebtn3.setRolloverIcon(logo_over);	
		homebtn3.setContentAreaFilled(false); 
		homebtn3.setBorderPainted(false);
		homebtn3.setFocusPainted(false);

		JLabel title = new JLabel("매출 및 지출");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn3, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(3, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(height*0.57), 40)); // 위 왼 아 오
		HalfRoundedButton daybtn = new HalfRoundedButton(blank1 + " 일 "+ blank2);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 월 " + blank2);
		HalfRoundedButton recode = new HalfRoundedButton(blank1 + "기록" + blank2, Color.orange);

		daybtn.setFont(font3);
		monthbtn.setFont(font3);
		recode.setFont(font3);

		// 버튼 이벤트
		daybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Today.setVisible(false);
				TodaySales();
				Today.setVisible(true);
				
				// 화면 전환
				Today.setVisible(true); 
				Month.setVisible(false);
				Recode.setVisible(false);
			}
		});
		monthbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Month.setVisible(false);
				MonthSales();
				Month.setVisible(true);
				
				// 화면 전환
				Today.setVisible(false); 
				Month.setVisible(true);
				Recode.setVisible(false);
			}
		});

		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);
		leftpanel.add(recode);
		
		// 공백
		JPanel rightpanel = new JPanel(new GridLayout(27, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 130)); 
		
		// recode
		JPanel centerpanel = new JPanel(new BorderLayout());
		centerpanel.setBackground(background);
		
		JLabel subTitle = new JLabel("오늘 지출 기록");
		subTitle.setFont(font2);
		subTitle.setForeground(Setting.title);
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		subTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, height/12, 0)); 
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(background);
		
		int margin = (height > 1000) ? 50 : 27;
		
		JPanel gridPanel = new JPanel(new GridLayout(6, 2, 25, margin));
		gridPanel.setBackground(background);
		
		final JLabel L[] = new JLabel[6];
		for(int i=0; i<L.length; i++) {
			L[i] = new JLabel();
			L[i].setFont(font3);
			L[i].setForeground(Setting.title);
		}
		
		L[0].setText("기타 지출비");
		L[2].setText("인건비(알바)");
		L[3].setText("알바 시급");
		L[4].setText("알바 시간");
		
		final JTextField R[] = new JTextField[2];
		for(int i=0; i<R.length; i++) {
			R[i] = new JTextField("", 20);
			R[i].setFont(font5);
		}
		
		JPanel radiobtn1 = new JPanel();
		ButtonGroup group1 = new ButtonGroup();
		final JRadioButton ra1 = new JRadioButton("유", true);
		ra1.setFont(font3);
		ra1.setForeground(fontcolor);
		ra1.setBackground(background);
		final JRadioButton ra2 = new JRadioButton("무", false);
		ra2.setFont(font3);
		ra2.setForeground(fontcolor);
		ra2.setBackground(background);
		group1.add(ra1);
		group1.add(ra2);
		radiobtn1.add(ra1);
		radiobtn1.add(ra2);
		radiobtn1.setBackground(background);
		
		JPanel radiobtn2 = new JPanel();
		ButtonGroup group2 = new ButtonGroup();
		final JRadioButton ra3 = new JRadioButton("유", true);
		ra3.setFont(font3);
		ra3.setForeground(fontcolor);
		ra3.setBackground(background);
		final JRadioButton ra4 = new JRadioButton("무", false);
		ra4.setFont(font3);
		ra4.setForeground(fontcolor);
		ra4.setBackground(background);
		group2.add(ra3);
		group2.add(ra4);
		radiobtn2.add(ra3);
		radiobtn2.add(ra4);
		radiobtn2.setBackground(background);

		final JComboBox ch = new JComboBox();
		
		for(int i=1; i<=24; i++) {
			ch.addItem(i+"시간");
		}
		
		gridPanel.add(L[0]);	// 기타 지출비
		gridPanel.add(radiobtn1);	
		gridPanel.add(L[1]);	// 공백
		gridPanel.add(R[0]);	
		gridPanel.add(L[2]);	// 인건비(알바)
		gridPanel.add(radiobtn2);	
		gridPanel.add(L[3]);	// 알바 시급
		gridPanel.add(R[1]);
		gridPanel.add(L[4]);	// 알바 시간
		gridPanel.add(ch);
		
		subPanel.add(gridPanel);

		// 기타 지출비 유무 이벤트
		ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[1].setVisible(true);
				R[0].setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[1].setVisible(false);
				R[0].setVisible(false);
			}
		});
		// 알바 유무 이벤트
		ra3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[3].setVisible(true);
				R[1].setVisible(true);
				L[4].setVisible(true);
				ch.setVisible(true);
			}
		});
		ra4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[3].setVisible(false);
				R[1].setVisible(false);
				L[4].setVisible(false);
				ch.setVisible(false);
			}
		});

		JPanel btns = new JPanel();
		btns.setBackground(background);
		int margin1 = (height > 1000) ? 120 : 70;
		double margin2 = (height < 1000) ? 0.03 : 0.1;
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, margin1, 0));
		
		RoundedButton check = new RoundedButton("확인");
		check.setFont(font3);
		
		btns.add(check);
		
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ra1.isSelected() == true && R[0].getText().equals("")) {
 					JOptionPane.showMessageDialog(null
						, "기타 지출비를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else if(ra1.isSelected() == true && !is.isNum(R[0].getText())) {
 					JOptionPane.showMessageDialog(null
						, "기타 지출비는 숫자만 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else if(ra3.isSelected() == true && R[1].getText().equals("")) {
 					JOptionPane.showMessageDialog(null
						, "알바 시급을 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else if(ra3.isSelected() == true && !is.isNum(R[1].getText())) {
 					JOptionPane.showMessageDialog(null
						, "알바 시급은 숫자만 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else { 
	 				// 데이터 저장
					System.out.println("기타 지출비 유무 : " + ((ra1.isSelected() == true) ? "유" : "무"));
					System.out.println("기타 지출비 : " + R[0].getText());
					System.out.println("알바 유무 : " + ((ra3.isSelected() == true) ? "유" : "무"));
					System.out.println("알바 시급 : " + R[1].getText());
					System.out.println("알바 시간 : " + (ch.getSelectedIndex() + 1) + "시간");
					
					JOptionPane.showMessageDialog(null
	 						, "오늘 지출 기록 완료!"
	 						, "박리다매"
	 						, JOptionPane.PLAIN_MESSAGE
	 				);
					
					// 다시 그리기
					L[1].setVisible(true);
					R[0].setVisible(true);
					L[3].setVisible(true);
					R[1].setVisible(true);
					L[4].setVisible(true);
					ch.setVisible(true);
					
					ra1.setSelected(true);
					ra2.setSelected(false);
					ra3.setSelected(true);
					ra4.setSelected(false);
					
					R[0].setText("");
					R[1].setText("");
					
					ch.setSelectedIndex(0);
	 			}
			}
		});
		
		centerpanel.add(subTitle, BorderLayout.NORTH);
		centerpanel.add(subPanel, BorderLayout.CENTER);
		centerpanel.add(btns, BorderLayout.SOUTH);
		
		Recode.add(header, BorderLayout.NORTH);
		Recode.add(leftpanel, BorderLayout.WEST);
		Recode.add(centerpanel, BorderLayout.CENTER);
		Recode.add(rightpanel, BorderLayout.EAST);
		
		Recode.setVisible(false);
		panel.add(Recode);
	}
	
	private void drawChart(int idx) {
		String title = null;
		String subTitle1 = null;
		String subTitle2 = null;

		 if(idx == 1) {
			 title = "주 매출 그래프";
			 subTitle1 = "이번주";
			 subTitle2 = "저번주";
		 } else if(idx == 2) {
			 title = "월 매출 그래프";
			 subTitle1 = "이번달";
			 subTitle2 = "저번달";
		 }
		
		final CategoryChart chart = new CategoryChartBuilder().width(width/3).height(100).title(title).xAxisTitle("").yAxisTitle("원").build();
		
		// 오늘 기준으로 가져오는 데이터 바탕으로 요일 정렬 해야함
		ArrayList<String> day = new ArrayList<String>();
		day.add("월");
		day.add("화");
		day.add("수");
		day.add("목");
		day.add("금");
		day.add("토");
		day.add("일");
		
		ArrayList<String> month = new ArrayList<String>();
		month.add("1월");
		month.add("2월");
		month.add("3월");
		month.add("4월");
		month.add("5월");
		month.add("6월");
		month.add("7월");
		month.add("8월");
		month.add("9월");
		month.add("10월");
		month.add("11월");
		month.add("12월");
		
		// 그래프 데이터 가져오기
		// idx: (1, 주 매출) (2, 달 매출)
		ArrayList<Integer> data1 = new ArrayList<Integer>();
		ArrayList<Integer> data2 = new ArrayList<Integer>();
		
	    firebase_sales sales = new firebase_sales();
	    
		int date = (idx == 1) ? now : Year;
		
		if(idx==1) {
			for (int i = 6; i >= 0; i--) {
				int a[] = null;
				try {
					int realdate = 0;
					if(date-i%100>30||date-i%100==0) {
						realdate = date-70-(100-(date-i%100));
					}else {
						realdate = date-i;
					}
					a = sales.show_Daysales(realdate, 7);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				data1.add(a[0]);
				data2.add(a[1]);
			}
		}else {
			for (int i = 0; i < 12; i++) {
				int a[] = null;
				try {
					int realdate = 0;
					if(date-i%100<=0) {
						realdate = date-100+11;  //202101
					}else {
						realdate = date-i;
					}
					a = sales.show_Monthsales(Year*10+i, 12);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				data1.add(a[0]);
				data2.add(a[1]);
			}
		}
		
		
		System.out.println(data1);
		System.out.println(data2);

		// 그래프 값 넣기        
        if(idx == 1) {
    		chart.addSeries(subTitle1, day, data1);
            chart.addSeries(subTitle2, day, data2);
            chartPanel1 = new XChartPanel<CategoryChart>(chart);
        } else if(idx == 2) {
    		chart.addSeries(subTitle1, month, data1);
            chart.addSeries(subTitle2, month, data2);
            
        	chartPanel2 = new XChartPanel<CategoryChart>(chart);
        }
        
	}
}