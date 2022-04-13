package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Customer extends Setting {
	public JPanel panel;

	public JPanel View;
	private JPanel Modify;

	public JButton homebtn1;
	public JButton homebtn2;

	private DefaultTableModel model;
	private Vector<Vector> dataSet = new Vector<Vector>();
	private Vector<String> colNames = new Vector<String>();
	
	// 고객 DB 참조
	private Vector<String> name = new Vector<String>();
	private Vector<String> telephone = new Vector<String>();

	int spacing;
	int margin1;
	int margin2;
	
	public Customer() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		
		// 고객관리 cloumn 설정
		colNames.add("이름");
		colNames.add("전화번호");
		
		// 데이터 불러오기
		name.add("왕경태");
		telephone.add("010-1234-5678");
		
		View();
		Modify();
	}
	
	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void View() {
		// View 세팅
		View = new JPanel();
		View.setBackground(background);
		View.setLayout(new BorderLayout());
		
		// header
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		homebtn1.setRolloverIcon(logo_over); 	
		homebtn1.setContentAreaFilled(false); 	
		homebtn1.setBorderPainted(false); 		
		homebtn1.setFocusPainted(false); 		
		
		JLabel title = new JLabel("고객 관리");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);
		
		// navigation: search, button
		JPanel navigation = new JPanel(new BorderLayout());
		navigation.setBackground(background);
		JPanel navLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		navLeft.setBackground(background);
		JPanel navRight = new JPanel(new GridLayout(1, 2, 10, 0));
		navRight.setBackground(background);
		
		JPanel search = new JPanel();
		search.setBackground(background);
		double margin = (height < 1000) ? 0.12 : 0.2;
		search.setBorder(BorderFactory.createEmptyBorder(50, (int)(width*margin), 0, 0));
		final TextField input = new TextField(" ", 20);
		HalfRoundedButton check = new HalfRoundedButton("🔍");

		search.add(input);
		search.add(check);

		RoundedButton btnView = new RoundedButton("고객 보기");
		btnView.setEnabled(false);
		btnView.nomal = new Color(120, 120, 120);
		btnView.setForeground(Color.white);
		RoundedButton btnModify = new RoundedButton("고객 수정");
		navRight.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, (int)(width*margin)));

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(false);
				Modify.setVisible(true);
			}
		});
		
		navLeft.add(search);
		navRight.add(btnView);
		navRight.add(btnModify);
		
		navigation.add(navLeft, BorderLayout.WEST);
		navigation.add(navRight, BorderLayout.EAST);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(background);
		
		// list
		JPanel list = new JPanel();
		
		dataLoad();

		model = new DefaultTableModel(dataSet, colNames) {
            // Jtable 내용 편집 안되게
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
		JTable tableView = new JTable(model);
		tableView.setFont(font4);
		tableView.setRowHeight(30);								// 행간 조절
		tableView.setGridColor(Color.gray);						// 격자색
		tableView.getTableHeader().setReorderingAllowed(false); // 이동 불가
		tableView.getTableHeader().setResizingAllowed(false); 	// 크기 조절 불가
		tableView.setEnabled(false);							// 셀 선택 불가
		
		JScrollPane scrollList = new JScrollPane(tableView);
		scrollList.setFont(font4);
		scrollList.setPreferredSize(new Dimension(800, (int)(height*0.5)));	// 테이블 사이즈 조절

		list.setBackground(background);
		list.add(scrollList);

		DefaultTableCellRenderer dtcr;
		for (int i = 0; i < tableView.getColumnCount(); i++) { 
			dtcr = new DefaultTableCellRenderer();	// 셀 내용 정렬 
			if(i < 4) 
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			else 
				dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
			TableColumnModel tcm = tableView.getColumnModel();
			tcm.getColumn(i).setCellRenderer(dtcr);
			tableView.getColumnModel().getColumn(i).setPreferredWidth(250);	// JTable 의 컬럼 길이 조절
		}
		
		center.add(navigation, BorderLayout.NORTH);
		center.add(list, BorderLayout.CENTER);
		
		View.add(header, BorderLayout.NORTH);
		View.add(center, BorderLayout.CENTER);

		View.setVisible(true);
		panel.add(View);
		
	}
	
	private void Modify() {
		// Modify 세팅
		Modify = new JPanel();
		Modify.setBackground(background);
		Modify.setLayout(new BorderLayout());
		
		// header
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		homebtn2.setRolloverIcon(logo_over); 	
		homebtn2.setContentAreaFilled(false); 	
		homebtn2.setBorderPainted(false); 		
		homebtn2.setFocusPainted(false); 		
		
		JLabel title = new JLabel("고객 관리");
		title.setFont(font2);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);
		
		// navigation: search, button
		JPanel navigation = new JPanel(new BorderLayout());
		navigation.setBackground(background);
		JPanel navLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 44));
		navLeft.setBackground(background);
		JPanel navRight = new JPanel(new GridLayout(1, 2, 10, 0));
		navRight.setBackground(background);
		
		double margin = (height < 1000) ? 0.12 : 0.2;

		RoundedButton btnView = new RoundedButton("고객 보기");
		RoundedButton btnModify = new RoundedButton("고객 수정");
		btnModify.nomal = new Color(120, 120, 120);
		btnModify.setForeground(Color.white);
		btnModify.setEnabled(false);
		navRight.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, (int)(width*margin)));

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(true);
				Modify.setVisible(false);
			}
		});
		
		navRight.add(btnView);
		navRight.add(btnModify);
		
		navigation.add(navLeft, BorderLayout.WEST);
		navigation.add(navRight, BorderLayout.EAST);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(background);
		
		
		
		center.add(navigation, BorderLayout.NORTH);
//		center.add(list, BorderLayout.CENTER);
		
		Modify.add(header, BorderLayout.NORTH);
		Modify.add(center, BorderLayout.CENTER);

		Modify.setVisible(false);
		panel.add(Modify);
	}
	
	public void reLoad() {
		View.setVisible(true);
		Modify.setVisible(false);
	}
	
	private void dataLoad() {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 데이터 입력
		for (int i = 0; i < name.size(); i++) {
			rows = new Vector<String>();
			rows.add(name.get(i));
			rows.add(telephone.get(i));
			dataSet.add(rows);
		}
	}
}