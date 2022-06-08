package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NetIncome extends Setting{
	public JPanel panel; // 실수령액 그래프

	private JPanel Week;
	private JPanel Month;
	private JPanel chartPanel1;
	private JPanel chartPanel2;
	
	public JButton homebtn1;
	public JButton homebtn2;
	
	DotGraph todayGraph = new DotGraph();
	DotGraphYear yearGraph = new DotGraphYear();

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

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(margin, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // 위 왼 아 오
		HalfRoundedButton weekbtn = new HalfRoundedButton(blank1 + " 주 "+ blank2, Color.orange);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 연 " + blank2);
		
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
		int[][] data1 = new int[12][4]; // 일, 월, 연, sales
		int[][] data2 = new int[12][4];

		for (int i = 0; i < 12; i++) {
			data1[i][0] = 20 + i;
			data1[i][1] = 3;
			data1[i][2] = 2022;
			data1[i][3] = 100 + (20 * i);

			data2[i][0] = 20 + i;
			data2[i][1] = 3;
			data2[i][2] = 2022;
			data2[i][3] = 240 - (20 * i);
		}

		JPanel chartPanel3 = todayGraph.createDemoPanel(2, data1, data2);

		// footer (공백)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // 위 왼 아 오

		Week.add(header, BorderLayout.NORTH);
		Week.add(leftpanel, BorderLayout.WEST);
		Week.add(rightpanel, BorderLayout.EAST);
		Week.add(chartPanel3, BorderLayout.CENTER);
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

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(margin, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // 위 왼 아 오
		HalfRoundedButton weekbtn = new HalfRoundedButton(blank1 + " 주 "+ blank2);
		HalfRoundedButton monthbtn = new HalfRoundedButton(blank1 + " 연 " + blank2, Color.orange);

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

		// right
		JPanel rightpanel = new JPanel(new GridLayout(27, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // 위 왼 아 오

		// 그래프 날짜 버튼 추가
		int year = 2022; // 지금 년도 가져오기
		int[] years = new int[10];

		JComboBox yearCh = new JComboBox();
		JButton yearbtn = new JButton("확인");
		yearbtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 13));
		yearbtn.setBackground(Color.white);

		for (int i = 0; i < 10; i++) {
			years[i] = year - i;
			yearCh.addItem(year - i + "년");
		}

		JPanel chbox = new JPanel(new BorderLayout());
		chbox.setBackground(background);
		JPanel chp = new JPanel();
		chp.add(yearCh);
		chp.setBorder(BorderFactory.createEmptyBorder(-4, 0, 0, 0)); // 위 왼 아 오
		chp.setBackground(background);

		chbox.add(chp, BorderLayout.WEST);
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
		int[][] data1 = new int[12][4]; // 일, 월, 연, sales
		int[][] data2 = new int[12][4];

		for (int i = 0; i < 12; i++) {
			data1[i][0] = 1;
			data1[i][1] = i+1;
			data1[i][2] = 2022;
			data1[i][3] = 100 + (20 * i);

			data2[i][0] = 1;
			data2[i][1] = i+1;
			data2[i][2] = 2022;
			data2[i][3] = 240 - (20 * i);
		}

		chartPanel2 = yearGraph.createDemoPanel(2, data1, data2);

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
}