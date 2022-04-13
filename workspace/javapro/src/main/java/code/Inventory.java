package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Choice;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
	
	private JLabel btnView[] = new JLabel[3];
	private JLabel btnModify[] = new JLabel[3];
	private JLabel btnAdd[] = new JLabel[3];
	
	private DefaultTableModel model;
	private Vector<Vector> dataSet = new Vector<Vector>();
	private Vector<String> colNames = new Vector<String>();

	private Vector<String> code = new Vector<String>();
	private Vector<String> name = new Vector<String>();
	private Vector<String> category = new Vector<String>();
	private Vector<String> standard = new Vector<String>();
	private Vector<Integer> cnt = new Vector<Integer>();
	private Vector<Integer> price = new Vector<Integer>();
	private Vector<Integer> cost = new Vector<Integer>();
	private Vector<Integer> amount = new Vector<Integer>();
	private Vector<String> explain = new Vector<String>();
	private Vector<String> picture = new Vector<String>();

	int spacing;
	int margin1;
	int margin2;
	
	public Inventory() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		
		spacing = (height < 1000) ? 15 : 30;
		margin1 = (height < 1000) ? 25 : 50;
		margin2 = (height < 1000) ? 15 : 30;
		
		// 제고관리 cloumn 설정
		colNames.add("제품코드");
		colNames.add("상품명");
		colNames.add("분 류");
		colNames.add("규 격");
		colNames.add("수 량");
		colNames.add("단 가");
		colNames.add("원 가");
		colNames.add("판매량");
		colNames.add("제품설명");
		
		// 데이터 불러오기
		code.add("AD1004");
		code.add("BC2075");
		code.add("TR1200");
		name.add("초코송이");
		name.add("칠성사이다");
		name.add("허니버터칩");
		category.add("스낵");
		category.add("음료");
		category.add("스낵");
		standard.add("240g");
		standard.add("1.5L");
		standard.add("600g");
		cnt.add(3);
		cnt.add(15);
		cnt.add(7);
		price.add(1200);
		price.add(2700);
		price.add(1600);
		cost.add(1000);
		cost.add(2500);
		cost.add(1300);
		amount.add(10);
		amount.add(5);
		amount.add(3);
		explain.add("가나다라마바사아자차카타파하");
		explain.add("/");
		explain.add("/");
		picture.add("C:\\Users\\user\\Pictures\\Saved Pictures1");
		picture.add("C:\\Users\\user\\Pictures\\Saved Pictures2");
		picture.add("C:\\Users\\user\\Pictures\\Saved Pictures3");
		
		for(int i=0; i<100; i++) {
			code.add("test");
			name.add("test2");
			category.add("test3");
			standard.add("test4");
			cnt.add(1);
			price.add(1);
			cost.add(1);
			amount.add(1);
			explain.add("/");
			picture.add("이미지 없음");
		}
		
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
		title.setForeground(Setting.title);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[0] = new JLabel("재고 보기");
		btnView[0].setForeground(menu_over);
		btnModify[0] = new JLabel("재고 편집");
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
		double margin = (height < 1000) ? 0.12 : 0.2;
		search.setBorder(BorderFactory.createEmptyBorder(50, (int)(width*margin), 0, 0));
		final TextField input = new TextField(" ", 20);
		HalfRoundedButton check = new HalfRoundedButton("🔍");

		search.add(input);
		search.add(check);
		
		// search 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(input.getText().length() != 0) {
					System.out.println("입력된 문자열 : " + input.getText());
					
					dataSearch(input.getText());
					model.fireTableDataChanged();
					
					System.out.println("데이터 수정 완료!");
				} else {
					System.out.println("입력된 문자열 없음");
				}
			}
		});

		// list
		JPanel list = new JPanel();
		
		dataLoad();

		model = new DefaultTableModel(dataSet, colNames);
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
		title.setForeground(Setting.title);

		header.add(homebtn2, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[1] = new JLabel("재고 보기");
		btnModify[1] = new JLabel("재고 편집");
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
		
		// inventory modify
		final JPanel inventory = new JPanel(new BorderLayout());
		inventory.setBackground(background);
		
		JLabel subtitle1 = new JLabel("재고 편집");
		subtitle1.setFont(font2);
		subtitle1.setForeground(fontcolor);
		subtitle1.setHorizontalAlignment(JLabel.CENTER);
		subtitle1.setBorder(BorderFactory.createEmptyBorder(margin1, 0, 0, 0));
		
		// 재고 선택
		JPanel choose = new JPanel();
		choose.setBackground(background);
		choose.setBorder(BorderFactory.createEmptyBorder(margin2, 0, 0, 0));

		final Choice ch = new Choice();
		
		for(int i=0; i<name.size(); i++) {
			ch.add(name.get(i));
		}
		
		choose.add(ch);
		
		// 수정 & 삭제
		JPanel btns1 = new JPanel();
		btns1.setBackground(background);
		btns1.setBorder(BorderFactory.createEmptyBorder(0, 0, height/10, 0));
		RoundedButton rp = new RoundedButton("재고수정");
		rp.setFont(font3);
		RoundedButton rm = new RoundedButton("재고삭제");
		rm.setFont(font3);
		
		btns1.add(rp);
		btns1.add(rm);
		
		inventory.add(subtitle1, BorderLayout.NORTH);
		inventory.add(choose, BorderLayout.CENTER);
		inventory.add(btns1, BorderLayout.SOUTH);
		
		// inventory replace
		final JPanel replace = new JPanel(new BorderLayout());
		replace.setBackground(background);

		JPanel btns2 = new JPanel();
		btns2.setBackground(background);
		btns2.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(height*0.06), 0));
		RoundedButton check1 = new RoundedButton("확인");
		check1.setFont(font3);
		RoundedButton cancel1 = new RoundedButton("취소");
		cancel1.setFont(font3);
		
		btns2.add(check1);
		btns2.add(cancel1);
		
		JPanel datas = new JPanel();
		datas.setBackground(background);
		datas.setBorder(BorderFactory.createEmptyBorder((int)(height*0.08), 0, 0, 0));
		
		JPanel data = new JPanel(new GridLayout(10, 2, 0, spacing));
		data.setBackground(background);
		JLabel L[] = new JLabel[9];
		for(int i=0; i<L.length; i++) {
			L[i] = new JLabel(colNames.get(i));
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
		}
		JLabel L10 = new JLabel("이미지");
		L10.setFont(font3);
		L10.setForeground(fontcolor);
		
		final JLabel R1 = new JLabel("");
		R1.setFont(font6);
		R1.setForeground(fontcolor);
		final TextField R2 = new TextField("", 20);
		R2.setFont(font6);
		final TextField R3 = new TextField("", 20);
		R3.setFont(font6);
		final TextField R4 = new TextField("", 20);
		R4.setFont(font6);
		final TextField R5 = new TextField("", 20);
		R5.setFont(font6);
		final TextField R6 = new TextField("", 20);
		R6.setFont(font6);
		final TextField R7 = new TextField("/", 20);
		R7.setFont(font6);
		final TextField R8 = new TextField("/", 20);
		R8.setFont(font6);
		final TextField R9 = new TextField("/", 20);
		R9.setFont(font6);

		JPanel imglayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		TextField R10 = new TextField("", 20);
		R10.setFont(font6);
		JButton btnR10 = new JButton("파일찾기");
		btnR10.setFont(font6);
		imglayer.add(R10);
		imglayer.add(btnR10);
		
		data.add(L[0]);
		data.add(R1);
		data.add(L[1]);
		data.add(R2);
		data.add(L[2]);
		data.add(R3);
		data.add(L[3]);
		data.add(R4);
		data.add(L[4]);
		data.add(R5);
		data.add(L[5]);
		data.add(R6);
		data.add(L[6]);
		data.add(R7);
		data.add(L[7]);
		data.add(R8);
		data.add(L[8]);
		data.add(R9);
		data.add(L10);
		data.add(imglayer);
		
		datas.add(data);
			
		replace.add(datas, BorderLayout.CENTER);
		replace.add(btns2, BorderLayout.SOUTH);
		
		// 버튼 이벤트
		rp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.setVisible(false);
				replace.setVisible(true);
				
				int index = ch.getSelectedIndex();
				R1.setText(code.get(index));
				R2.setText(name.get(index));
				R3.setText(category.get(index));
				R4.setText(standard.get(index));
				R5.setText(Integer.toString(cnt.get(index)));
				R6.setText(Integer.toString(price.get(index)));
				R7.setText(Integer.toString(cost.get(index)));
				R8.setText(Integer.toString(amount.get(index)));
				R9.setText(explain.get(index));
				R10.setText(picture.get(index));
			}
		});
		rm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 삭제 기능 구현
				int n = JOptionPane.showConfirmDialog(
						null
						, "해당 상품을 삭제하시겠습니까?"
						, "박리다매 무인가게"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.WARNING_MESSAGE
				);
				if(n == 0) {
					// 데이터 삭제
					int index = ch.getSelectedIndex();
					dataSet.remove(index);
					code.remove(index);
					name.remove(index);
					category.remove(index);
					standard.remove(index);
					cnt.remove(index);
					price.remove(index);
					cost.remove(index);
					amount.remove(index);
					explain.remove(index);
					picture.remove(index);
					
					// repaint
					ch.remove(index);
					dataLoad();		
					model.fireTableDataChanged();

					JOptionPane.showMessageDialog(null
							, "정상적으로 재고 삭제 완료!"
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
					);
				}
			}
		});
		check1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = ch.getSelectedIndex();
				int n = 0;
				// 예외 처리
				if(R2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "상품명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "분류를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "규격을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R5.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "수량을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R5.getText())) {
					JOptionPane.showMessageDialog(null
							, "수량은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R6.getText().length() == 0) {	
					JOptionPane.showMessageDialog(null
							, "금액을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R6.getText())) {
					JOptionPane.showMessageDialog(null
							, "금액은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R7.getText().length() == 0) {
					R7.setText("/");
				} else if( R2.getText().equals(name.get(index))
						&&  R3.getText().equals(category.get(index))
						&&  R4.getText().equals(standard.get(index))
						&&  Integer.parseInt(R5.getText()) == cnt.get(index)
						&&  Integer.parseInt(R6.getText()) == price.get(index)
						&&  R7.getText().equals(explain.get(index))) {
					JOptionPane.showMessageDialog(null
							, "변경사항이 없습니다!"
							, "박리다매 무인가게"
							, JOptionPane.INFORMATION_MESSAGE
					);
					n = -1;
				}
				if (n == -1) {
					inventory.setVisible(true);
					replace.setVisible(false);
				} else {
					// 수정 기능 구현
					n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
					if(n == 0) {
						// 데이터 삭제
						name.set(index, R2.getText());
						category.set(index, R3.getText());
						standard.set(index, R4.getText());
						cnt.set(index, Integer.parseInt(R5.getText()));
						price.set(index, Integer.parseInt(R6.getText()));
						explain.set(index, R7.getText());
						
						// repaint
						dataLoad();		
						model.fireTableDataChanged();
	
						JOptionPane.showMessageDialog(null
								, "정상적으로 재고 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);
						inventory.setVisible(true);
						replace.setVisible(false);
					}
				}
			}
		});
		cancel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventory.setVisible(true);
				replace.setVisible(false);
			}
		});
		
		inventory.setVisible(true);
		replace.setVisible(false);
		
		JPanel layout = new JPanel(new CardLayout());
		layout.add(inventory);
		layout.add(replace);
		
		Modify.add(nav, BorderLayout.NORTH);
		Modify.add(layout, BorderLayout.CENTER);

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
		title.setForeground(Setting.title);

		header.add(homebtn3, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);

		// menubar
		btnView[2] = new JLabel("재고 보기");
		btnModify[2] = new JLabel("재고 편집");
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
		
		// inventory add
		JPanel inventory = new JPanel(new BorderLayout());
		inventory.setBackground(background);
		
		JLabel subtitle = new JLabel("재고 추가");
		subtitle.setFont(font2);
		subtitle.setForeground(fontcolor);
		subtitle.setHorizontalAlignment(JLabel.CENTER);
		subtitle.setBorder(BorderFactory.createEmptyBorder(margin1, 0, 0, 0));

		JPanel btns = new JPanel();
		btns.setBackground(background);
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(height*0.04), 0));
		RoundedButton add = new RoundedButton("추가");
		add.setFont(font3);
		btns.add(add);
		
		JPanel datas = new JPanel();
		datas.setBackground(background);
		datas.setBorder(BorderFactory.createEmptyBorder((int)(height*0.04), 0, 0, 0));
		
		
		JPanel data = new JPanel(new GridLayout(10, 2, 0, spacing));
		data.setBackground(background);
		JLabel L[] = new JLabel[9];
		for(int i=0; i<L.length; i++) {
			L[i] = new JLabel(colNames.get(i));
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
		}
		JLabel L10 = new JLabel("이미지");
		L10.setFont(font3);
		L10.setForeground(fontcolor);
		
		final TextField R1 = new TextField("", 20);
		R1.setFont(font6);
		final TextField R2 = new TextField("", 20);
		R2.setFont(font6);
		final TextField R3 = new TextField("", 20);
		R3.setFont(font6);
		final TextField R4 = new TextField("", 20);
		R4.setFont(font6);
		final TextField R5 = new TextField("", 20);
		R5.setFont(font6);
		final TextField R6 = new TextField("", 20);
		R6.setFont(font6);
		final TextField R7 = new TextField("", 20);
		R7.setFont(font6);
		final TextField R8 = new TextField("", 20);
		R8.setFont(font6);
		final TextField R9 = new TextField("/", 20);
		R9.setFont(font6);
		
		JPanel imglayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		TextField R10 = new TextField("", 20);
		R10.setFont(font6);
		JButton btnR10 = new JButton("파일찾기");
		btnR10.setFont(font6);
		imglayer.add(R10);
		imglayer.add(btnR10);
		
		data.add(L[0]);
		data.add(R1);
		data.add(L[1]);
		data.add(R2);
		data.add(L[2]);
		data.add(R3);
		data.add(L[3]);
		data.add(R4);
		data.add(L[4]);
		data.add(R5);
		data.add(L[5]);
		data.add(R6);
		data.add(L[6]);
		data.add(R7);
		data.add(L[7]);
		data.add(R8);
		data.add(L[8]);
		data.add(R9);
		data.add(L10);
		data.add(imglayer);
		
		datas.add(data);
		
		// 버튼 이벤트
		btnR10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R8.setText(FileUpload());
			}
		});
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 예외 처리
				if(R1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "제품코드를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(/* 중복인지 확인 */R1.getText().equals("AD1004")) {
					JOptionPane.showMessageDialog(null
							, "이미 등록된 제품코드입니다."
		                    , "박리다매 무인가게"
		                    , JOptionPane.ERROR_MESSAGE
		            );
	            } else if(!is.isString2(R1.getText())) {           
 					JOptionPane.showMessageDialog(null
 		 		            , "제품코드에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
 		 		            , "박리다매"
 		 		            , JOptionPane.ERROR_MESSAGE
 		                );
 	            } else if(R2.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "상품명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "분류를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isString2(R3.getText())) {           
 					JOptionPane.showMessageDialog(null
 		 		            , "분류에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
 		 		            , "박리다매"
 		 		            , JOptionPane.ERROR_MESSAGE
 		                );
 	            } else if(R4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "규격을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isString2(R4.getText())) {           
 					JOptionPane.showMessageDialog(null
 		 		            , "규격에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
 		 		            , "박리다매"
 		 		            , JOptionPane.ERROR_MESSAGE
 		                );
 	            } else if(R5.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "수량을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R5.getText())) {
					JOptionPane.showMessageDialog(null
							, "수량은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R6.getText().length() == 0) {	
					JOptionPane.showMessageDialog(null
							, "금액을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R6.getText())) {
					JOptionPane.showMessageDialog(null
							, "금액은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					if(R7.getText().length() == 0) {
						R7.setText("/");
					}
					// 수정 기능 구현
					int n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
					if(n == 0) {
						// 데이터 추가
						code.add(R1.getText());
						name.add(R2.getText());
						category.add(R3.getText());
						standard.add(R4.getText());
						cnt.add(Integer.parseInt(R5.getText()));
						price.add(Integer.parseInt(R6.getText()));
						cost.add(Integer.parseInt(R7.getText()));
						amount.add(Integer.parseInt(R8.getText()));
						explain.add(R9.getText());
						picture.add(R10.getText());
						
						// 데이터 변경 사항 저장
						
						// repaint
						dataLoad();		
						model.fireTableDataChanged();

						Add();
						panel.remove(2);
						Add.setVisible(true);
						
						JOptionPane.showMessageDialog(null
								, "정상적으로 재고 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);

						Add.setVisible(true);
					}
				}
			}
		});
		
		inventory.add(subtitle, BorderLayout.NORTH);
		inventory.add(datas, BorderLayout.CENTER);
		inventory.add(btns, BorderLayout.SOUTH);
		
		Add.add(nav, BorderLayout.NORTH);
		Add.add(inventory, BorderLayout.CENTER);

		Add.setVisible(false);
		panel.add(Add);
	}

	public void reLoad() {
		View.setVisible(true);
		Modify.setVisible(false);
		Add.setVisible(false);
	}
	
	private void dataLoad() {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 데이터 입력
		for (int i = 0; i < code.size(); i++) {
			rows = new Vector<String>();
			rows.add(code.get(i));
			rows.add(name.get(i));
			rows.add(category.get(i));
			rows.add(standard.get(i));
			rows.add(Integer.toString(cnt.get(i)));
			rows.add(Integer.toString(price.get(i)));
			rows.add(Integer.toString(cost.get(i)));
			rows.add(Integer.toString(amount.get(i)));
			rows.add(explain.get(i));
			dataSet.add(rows);
		}
	}
	
	private void dataSearch(String str) {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 검색 데이터 입력
		for (int i = 0; i < code.size(); i++) {
			rows = new Vector<String>();
			rows.add(code.get(i));
			rows.add(name.get(i));
			rows.add(category.get(i));
			rows.add(standard.get(i));
			rows.add(Integer.toString(cnt.get(i)));
			rows.add(Integer.toString(price.get(i)));
			rows.add(Integer.toString(cost.get(i)));
			rows.add(Integer.toString(amount.get(i)));
			rows.add(explain.get(i));

			if(name.get(i).indexOf(str) != -1)
				dataSet.add(rows);
		}
	}
	
	private String FileUpload() {
		JFileChooser jfc = new JFileChooser();
        int returnVal = jfc.showSaveDialog(null);
        if(returnVal == 0) {
            File file = jfc.getSelectedFile();
            try {
                String tmp, str = null;
                BufferedReader br = new BufferedReader(new FileReader(file));
                while((tmp = br.readLine()) != null)
                {
                    str += tmp;
                }
                return jfc.getSelectedFile().getPath();
            }catch(Exception e) {
                e.printStackTrace();
            }
             
        }
        else
        {
            System.out.println("파일 열기를 취소하였습니다.");
        }
        return null;

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