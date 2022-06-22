package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.XChartPanel;

public class NetIncome extends Setting{
	public JPanel panel; // 실수령액 그래프

	private JPanel Week;
	private JPanel Month;
	private JPanel chartPanel1;
	private JPanel chartPanel2;
	
	public JButton homebtn1;
	public JButton homebtn2;

	public int margin = (height > 1000) ? 20 : 15;
	public String blank1 = (height > 1000) ? "  " : "";
	public String blank2 = (height > 1000) ? "   " : " ";
	
	public NetIncome() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		WeekGrahp();
		MonthGrahp();
	}
	
	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void WeekGrahp() {
		// Week 세팅
		Week = new JPanel();
		Week.setBackground(background);
		Week.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over); // rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); // 배경 채우기
		homebtn1.setBorderPainted(false); // 외각선
		homebtn1.setFocusPainted(false); // 선택 외각선

		JLabel title = new JLabel("실수령액 그래프");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		double mar1 = (height > 1000) ? 0.645 : 0.5;
		
		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(2, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(-1, 0, (int)(height*mar1), 40)); // 위 왼 아 오
		HalfRoundedButton weekbtn = new HalfRoundedButton(blank1 + " 일 "+ blank2, Color.orange);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 월 " + blank2);
		
		weekbtn.setFont(font3);
		monthbtn.setFont(font3);

		// 버튼 이벤트
		monthbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Week.setVisible(false); // 화면 전환
				Month.setVisible(true);
			}
		});

		leftpanel.add(weekbtn);
		leftpanel.add(monthbtn);

		// right
		JPanel rightpanel = new JPanel(new GridLayout(0, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 182)); // 위 왼 아 오

		// graph
		drawChart(1);

		// footer (공백)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // 위 왼 아 오

		Week.add(header, BorderLayout.NORTH);
		Week.add(leftpanel, BorderLayout.WEST);
		Week.add(rightpanel, BorderLayout.EAST);
		Week.add(chartPanel1, BorderLayout.CENTER);
		Week.add(footer, BorderLayout.SOUTH);

		Week.setVisible(true);
		panel.add(Week);
	}

	private void MonthGrahp() {
		// Month 세팅
		Month = new JPanel();
		Month.setBackground(background);
		Month.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home 버튼 생성
		homebtn2 = new JButton("", logo);
		homebtn2.setRolloverIcon(logo_over); // rolloverIcon용 이미지 등록
		homebtn2.setContentAreaFilled(false); // 배경 채우기
		homebtn2.setBorderPainted(false); // 외각선
		homebtn2.setFocusPainted(false); // 선택 외각선

		JLabel title = new JLabel("실수령액 그래프");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		double mar1 = (height > 1000) ? 0.645 : 0.5;
		
		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(2, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(-1, 0, (int)(height*mar1), 40)); // 위 왼 아 오
		HalfRoundedButton weekbtn = new HalfRoundedButton(blank1 + " 일 "+ blank2);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 월 " + blank2, Color.orange);

		weekbtn.setFont(font3);
		monthbtn.setFont(font3);

		// 버튼 이벤트
		weekbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Week.setVisible(true); // 화면 전환
				Month.setVisible(false);
			}
		});

		leftpanel.add(weekbtn);
		leftpanel.add(monthbtn);

		double mar2 = (height > 1000) ? 0.67 : 0.53;
		
		// right
		JPanel rightpanel = new JPanel(new GridLayout(2, 1));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 40, (int)(height*mar2), 10)); // 위 왼 아 오

		// 그래프 날짜 버튼 추가
		int year = 2022; // 지금 년도 가져오기
		int[] years = new int[10];

		JComboBox yearCh = new JComboBox();
		JButton yearbtn = new JButton("확인");
		yearbtn.setFont(font5);
		yearbtn.setBackground(Color.white);

		for (int i = 0; i < 10; i++) {
			years[i] = year - i;
			yearCh.addItem(year - i + "년");
		}

		JPanel chbox = new JPanel(new BorderLayout());
		chbox.setBackground(background);

		chbox.add(yearCh, BorderLayout.WEST);
		chbox.add(yearbtn, BorderLayout.EAST);
		rightpanel.add(chbox);

		yearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph 다시 그리기
				Month.setVisible(false);
				MonthGrahp();
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
	
	private void drawChart(int idx) {
		String title = null;
		String subTitle1 = null;
		String subTitle2 = null;

		 if(idx == 1) {
			 title = "주 실수령액 그래프";
			 subTitle1 = "이번주";
			 subTitle2 = "저번주";
		 } else if(idx == 2) {
			 title = "월 실수령액 그래프";
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
		
		// 그래프 데이터 가져오기
		// idx: (1, 주 실수령액) (2, 달 실수령액)
		ArrayList<Integer> data1 = new ArrayList<Integer>();
		ArrayList<Integer> data2 = new ArrayList<Integer>();

		for (int i = 0; i < day.size(); i++) {
			data1.add(100+(100*i));
			
			data2.add(1000-(100*i));
		}

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