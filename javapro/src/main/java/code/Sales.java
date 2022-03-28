package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sales extends Setting{
	public JPanel panel; // �Ǽ��ɾ� �׷���

	private JPanel Today;
	private JPanel Month;
	private JPanel chartPanel1;
	private JPanel chartPanel2;
	
	public JButton homebtn1;
	public JButton homebtn2;
	
	DotGraph graph = new DotGraph();
	
	public Sales() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		TodaySales();
		MonthSales();
	}
	
	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void TodaySales() {
		// Today ����
		Today = new JPanel();
		Today.setBackground(background);
		Today.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home ��ư ����
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon�� �̹��� ���
		homebtn1.setContentAreaFilled(false); 	// ��� ä���
		homebtn1.setBorderPainted(false); 		// �ܰ���
		homebtn1.setFocusPainted(false); 		// ���� �ܰ���

//		// home ��ư �̺�Ʈ
//		homebtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panel0.setVisible(true);
//				panel1.setVisible(false);
//			}
//		});

		JLabel title = new JLabel("���� �� ����");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��
		HalfRoundedButton daybtn = new HalfRoundedButton("   ��       ", Color.orange);
		HalfRoundedButton monthbtn = new HalfRoundedButton("   ��       ");

		daybtn.setFont(font3);
		monthbtn.setFont(font3);

		// ��ư �̺�Ʈ
		monthbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Today.setVisible(false); // ȭ�� ��ȯ
				Month.setVisible(true);
			}
		});

		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);

		// sales
		JPanel rightpanel = new JPanel(new GridLayout(27, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 102)); // �� �� �� ��
		JLabel stitle = new JLabel("Today");
		stitle.setFont(font3);
		JLabel sales = new JLabel("�� 350,000");
		sales.setFont(new Font("����ü", Font.PLAIN, 16));
		sales.setForeground(Color.red);

		rightpanel.add(stitle);
		rightpanel.add(sales);

		// graph
		int[][] data1 = new int[7][4]; // ��, ��, ��, sales
		int[][] data2 = new int[7][4];

		for (int i = 0; i < 7; i++) {
			data1[i][0] = 20 + i;
			data1[i][1] = 3;
			data1[i][2] = 2022;
			data1[i][3] = 100 + (20 * i);

			data2[i][0] = 20 + i;
			data2[i][1] = 3;
			data2[i][2] = 2022;
			data2[i][3] = 240 - (20 * i);
		}

		chartPanel1 = graph.createDemoPanel(1, data1, data2);

		// footer (����)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // �� �� �� ��

		Today.add(header, BorderLayout.NORTH);
		Today.add(leftpanel, BorderLayout.WEST);
		Today.add(rightpanel, BorderLayout.EAST);
		Today.add(chartPanel1, BorderLayout.CENTER);
		Today.add(footer, BorderLayout.SOUTH);

		Today.setVisible(true);
		panel.add(Today);
	}

	private void MonthSales() {
		// p1_Month ����
		Month = new JPanel();
		Month.setBackground(background);
		Month.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home ��ư ����
		homebtn2 = new JButton("", logo);
		homebtn2.setRolloverIcon(logo_over); // rolloverIcon�� �̹��� ���
		homebtn2.setContentAreaFilled(false); // ��� ä���
		homebtn2.setBorderPainted(false); // �ܰ���
		homebtn2.setFocusPainted(false); // ���� �ܰ���

//		// home ��ư �̺�Ʈ
//		homebtn2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				panel0.setVisible(true);
//				panel1.setVisible(false);
//			}
//		});

		JLabel title = new JLabel("���� �� ����");
		title.setFont(font2);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��
		HalfRoundedButton daybtn = new HalfRoundedButton("   ��       ");
		HalfRoundedButton monthbtn = new HalfRoundedButton("   ��       ", Color.orange);

		daybtn.setFont(font3);
		monthbtn.setFont(font3);

		// ��ư �̺�Ʈ
		daybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Today.setVisible(true); // ȭ�� ��ȯ
				Month.setVisible(false);
			}
		});

		leftpanel.add(daybtn);
		leftpanel.add(monthbtn);

		// sales
		JPanel rightpanel = new JPanel(new GridLayout(27, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��

		JLabel stitle = new JLabel("Month");
		stitle.setFont(font3);
		JLabel sales = new JLabel("�� 780,000");
		sales.setFont(new Font("����ü", Font.PLAIN, 16));
		sales.setForeground(Color.red);

		// �׷��� ��¥ ��ư �߰�
		int year = 2022; // ���� �⵵ ��������
		int[] years = new int[10];

		Choice yearCh = new Choice();
		JButton yearbtn = new JButton("Ȯ��");
		yearbtn.setFont(new Font("����ǹ��� �־�", Font.PLAIN, 13));
		yearbtn.setBackground(Color.white);

		for (int i = 0; i < 10; i++) {
			years[i] = year - i;
			yearCh.add(year - i + "��");
		}

		JPanel chbox = new JPanel(new BorderLayout());
		chbox.setBackground(Color.white);
		JPanel chp = new JPanel();
		chp.add(yearCh);
		chp.setBorder(BorderFactory.createEmptyBorder(-4, 0, 0, 0)); // �� �� �� ��
		chp.setBackground(Color.white);

		chbox.add(chp, BorderLayout.WEST);
		chbox.add(yearbtn, BorderLayout.EAST);

		rightpanel.add(stitle);
		rightpanel.add(sales);
		rightpanel.add(chbox);

		yearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph �ٽ� �׸���
				Month.setVisible(false);
				MonthSales();
				Month.setVisible(true);
			}
		});

		// graph
		int[][] data1 = new int[7][4]; // ��, ��, ��, sales
		int[][] data2 = new int[7][4];

		for (int i = 0; i < 7; i++) {
			data1[i][0] = 20 + i;
			data1[i][1] = 3;
			data1[i][2] = 2022;
			data1[i][3] = 100 + (20 * i);

			data2[i][0] = 20 + i;
			data2[i][1] = 3;
			data2[i][2] = 2022;
			data2[i][3] = 240 - (20 * i);
		}

		chartPanel2 = graph.createDemoPanel(2, data1, data2);

		// footer (����)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // �� �� �� ��

		Month.add(header, BorderLayout.NORTH);
		Month.add(leftpanel, BorderLayout.WEST);
		Month.add(rightpanel, BorderLayout.EAST);
		Month.add(chartPanel2, BorderLayout.CENTER);
		Month.add(footer, BorderLayout.SOUTH);

		Month.setVisible(false);
		panel.add(Month);
	}
}