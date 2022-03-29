package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class Inventory extends Setting {
	public JPanel panel;

	private JPanel View;
	private JPanel Modify;
	private JPanel Add;

	public JButton homebtn1;
	public JButton homebtn2;
	public JButton homebtn3;
	
	JLabel btnView[] = new JLabel[3];
	JLabel btnModify[] = new JLabel[3];
	JLabel btnAdd[] = new JLabel[3];

	Vector<String> colNames = new Vector<>();
	
	public Inventory() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);

		// 제고관리 cloumn 설정
		colNames.add("제품코드");
		colNames.add("상품명");
		colNames.add("분 류");
		colNames.add("규 격");
		colNames.add("수 량");
		colNames.add("금 액");
		colNames.add("비 고");
		
		View();
		Modify();
		Add();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}

	private void View() {
		// View 세팅
		View = new JPanel();
		View.setBackground(background);
		View.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over);
		homebtn1.setContentAreaFilled(false);
		homebtn1.setBorderPainted(false);
		homebtn1.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[0] = new JLabel("재고 보기");
		btnView[0].setForeground(menu_over);
		btnModify[0] = new JLabel("재고 수정");
		btnAdd[0] = new JLabel("재고 추가");
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[0].setFont(font4);
		btnModify[0].setFont(font4);
		btnAdd[0].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[0].addMouseListener(listener1);
		btnModify[0].addMouseListener(listener2);
		btnAdd[0].addMouseListener(listener3);

		menubar.add(btnView[0]);
		menubar.add(btnModify[0]);
		menubar.add(btnAdd[0]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);

		// inventory view
		JPanel inventory = new JPanel(new BorderLayout());

		// search
		JPanel search = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		search.setBackground(background);
		search.setBorder(BorderFactory.createEmptyBorder(50, 240, 0, 0));
		TextField input = new TextField("", 20);
		HalfRoundedButton check = new HalfRoundedButton("🔍");

		search.add(input);
		search.add(check);

		// list
		JPanel list = new JPanel();
		JTable tableView;

		Vector<String> rows;
		Vector<Vector> dataSet = new Vector<>();

		// 데이터 불러오기
		String[] code = { "AD1004", "BC2075", "TR1200"};
		String[] name = { "초코송이", "칠성사이다", "허니버터칩"};
		String[] category = { "스낵", "음료", "스낵"};
		String[] standard = { "240g", "1.5L", "600g"};
		int[] cnt = { 3, 15, 7};
		int[] price = { 1200, 2700, 1600};
		
		// 데이터 입력
		for (int i = 0; i < code.length; i++) {
			rows = new Vector<>();
			rows.add(code[i]);
			rows.add(name[i]);
			rows.add(category[i]);
			rows.add(standard[i]);
			rows.add(Integer.toString(cnt[i]));
			rows.add(Integer.toString(price[i]));
			// rows.add("/");
			dataSet.add(rows);
		}

		tableView = new JTable(dataSet, colNames);
		tableView.setFont(font4);
		tableView.setRowHeight(30);								// 행간 조절
		tableView.setGridColor(Color.gray);						// 격자색
		tableView.getTableHeader().setReorderingAllowed(false); // 이동 불가
		tableView.getTableHeader().setResizingAllowed(false); 	// 크기 조절 불가
		tableView.setEnabled(false);							// 셀 선택 불가
		
		JScrollPane scrollList = new JScrollPane(tableView);
		scrollList.setFont(font4);
		scrollList.setPreferredSize(new Dimension(800, 600));	// 테이블 사이즈 조절

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

		inventory.add(search, BorderLayout.NORTH);
		inventory.add(list, BorderLayout.CENTER);

		View.add(nav, BorderLayout.NORTH);
		View.add(inventory, BorderLayout.CENTER);

		View.setVisible(true);
		panel.add(View);
	}

	private void Modify() {
		// Modify 세팅
		Modify = new JPanel();
		Modify.setBackground(background);
		Modify.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn2 = new JButton("", logo);
		homebtn2.setRolloverIcon(logo_over);
		homebtn2.setContentAreaFilled(false);
		homebtn2.setBorderPainted(false);
		homebtn2.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[1] = new JLabel("재고 보기");
		btnModify[1] = new JLabel("재고 수정");
		btnModify[1].setForeground(menu_over);
		btnAdd[1] = new JLabel("재고 추가");
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[1].setFont(font4);
		btnModify[1].setFont(font4);
		btnAdd[1].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[1].addMouseListener(listener1);
		btnModify[1].addMouseListener(listener2);
		btnAdd[1].addMouseListener(listener3);

		menubar.add(btnView[1]);
		menubar.add(btnModify[1]);
		menubar.add(btnAdd[1]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);
		
		Modify.add(nav, BorderLayout.NORTH);

		Modify.setVisible(false);
		panel.add(Modify);
	}

	private void Add() {
		// Modify 세팅
		Add = new JPanel();
		Add.setBackground(background);
		Add.setLayout(new BorderLayout());

		// navigation
		JPanel nav = new JPanel(new BorderLayout());
		nav.setBackground(background);
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		JPanel menubar = new JPanel(new GridLayout(1, 10, 0, 0));
		menubar.setBackground(menu_back);

		// header
		homebtn3 = new JButton("", logo);
		homebtn3.setRolloverIcon(logo_over);
		homebtn3.setContentAreaFilled(false);
		homebtn3.setBorderPainted(false);
		homebtn3.setFocusPainted(false);

		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn3, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[2] = new JLabel("재고 보기");
		btnModify[2] = new JLabel("재고 수정");
		btnAdd[2] = new JLabel("재고 추가");
		btnAdd[2].setForeground(menu_over);
		JLabel blank = new JLabel();
		JLabel blank2 = new JLabel();

		btnView[2].setFont(font4);
		btnModify[2].setFont(font4);
		btnAdd[2].setFont(font4);

		MouseExitedListener1 listener1 = new MouseExitedListener1(); // 이벤트객체
		MouseExitedListener2 listener2 = new MouseExitedListener2();
		MouseExitedListener3 listener3 = new MouseExitedListener3();

		btnView[2].addMouseListener(listener1);
		btnModify[2].addMouseListener(listener2);
		btnAdd[2].addMouseListener(listener3);

		menubar.add(btnView[2]);
		menubar.add(btnModify[2]);
		menubar.add(btnAdd[2]);
		menubar.add(blank);
		menubar.add(blank2);

		menubar.setBorder(BorderFactory.createEmptyBorder(15, 50, 15, 0));

		nav.add(header, BorderLayout.CENTER);
		nav.add(menubar, BorderLayout.SOUTH);
		
		Add.add(nav, BorderLayout.NORTH);

		Add.setVisible(false);
		panel.add(Add);
	}

	class MouseExitedListener1 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(true);
			Modify.setVisible(false);
			Add.setVisible(false);
		}
		public void mouseEntered(MouseEvent e) {
			for(int i=0; i<3; i++)
				btnView[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	class MouseExitedListener2 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(false);
			Modify.setVisible(true);
			Add.setVisible(false);
		}
		public void mouseEntered(MouseEvent e ) {
			for(int i=0; i<3; i++)
				btnModify[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}

	class MouseExitedListener3 extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			View.setVisible(false);
			Modify.setVisible(false);
			Add.setVisible(true);
		}
		public void mouseEntered(MouseEvent e ) {
			for(int i=0; i<3; i++)
				btnAdd[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
}
