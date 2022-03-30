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
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NetIncome extends Setting{
	public JPanel panel; // �Ǽ��ɾ� �׷���

	private JPanel Week;
	private JPanel Month;
	private JPanel chartPanel1;
	private JPanel chartPanel2;
	
	public JButton homebtn1;
	public JButton homebtn2;
	
	DotGraph graph = new DotGraph();
	
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
		// Week ����
		Week = new JPanel();
		Week.setBackground(background);
		Week.setLayout(new BorderLayout());

		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);

		// home ��ư ����
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over); // rolloverIcon�� �̹��� ���
		homebtn1.setContentAreaFilled(false); // ��� ä���
		homebtn1.setBorderPainted(false); // �ܰ���
		homebtn1.setFocusPainted(false); // ���� �ܰ���

		JLabel title = new JLabel("�Ǽ��ɾ� �׷���");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��
		HalfRoundedButton weekbtn = new HalfRoundedButton("   ��       ", Color.orange);
		HalfRoundedButton monthbtn = new HalfRoundedButton("   ��       ");

		weekbtn.setFont(font3);
		monthbtn.setFont(font3);

		// ��ư �̺�Ʈ
		monthbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Week.setVisible(false); // ȭ�� ��ȯ
				Month.setVisible(true);
			}
		});

		leftpanel.add(weekbtn);
		leftpanel.add(monthbtn);

		// right
		JPanel rightpanel = new JPanel(new GridLayout(0, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 182)); // �� �� �� ��

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

		JPanel chartPanel3 = graph.createDemoPanel(3, data1, data2);

		// footer (����)
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(background);
		footer.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0)); // �� �� �� ��

		Week.add(header, BorderLayout.NORTH);
		Week.add(leftpanel, BorderLayout.WEST);
		Week.add(rightpanel, BorderLayout.EAST);
		Week.add(chartPanel3, BorderLayout.CENTER);
		Week.add(footer, BorderLayout.SOUTH);

		Week.setVisible(true);
		panel.add(Week);
	}

	private void MonthGrahp() {
		// Month ����
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

		JLabel title = new JLabel("�Ǽ��ɾ� �׷���");
		title.setFont(font2);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menu bar
		JPanel leftpanel = new JPanel(new GridLayout(20, 1, 0, 5));
		leftpanel.setBackground(background);
		leftpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��
		HalfRoundedButton weekbtn = new HalfRoundedButton("   ��       ");
		HalfRoundedButton monthbtn = new HalfRoundedButton("   ��       ", Color.orange);

		weekbtn.setFont(font3);
		monthbtn.setFont(font3);

		// ��ư �̺�Ʈ
		weekbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Week.setVisible(true); // ȭ�� ��ȯ
				Month.setVisible(false);
			}
		});

		leftpanel.add(weekbtn);
		leftpanel.add(monthbtn);

		// right
		JPanel rightpanel = new JPanel(new GridLayout(27, 1, 0, 0));
		rightpanel.setBackground(background);
		rightpanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50)); // �� �� �� ��

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
		rightpanel.add(chbox);

		yearbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Graph �ٽ� �׸���
				Month.setVisible(false);
				MonthGrahp();
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

		chartPanel2 = graph.createDemoPanel(4, data1, data2);

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