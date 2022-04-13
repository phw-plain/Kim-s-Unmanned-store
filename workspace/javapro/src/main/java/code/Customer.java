package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JTable tableView;
	private Vector<Vector> dataSet = new Vector<Vector>();
	private Vector<String> colNames = new Vector<String>();
	
	int index;
	TextField R[];
	JLabel R0;
	
	// 고객 DB 참조
	private Vector<String> id = new Vector<String>();
	private Vector<String> pw = new Vector<String>();
	private Vector<String> name = new Vector<String>();
	private Vector<String> telephone = new Vector<String>();
	private Vector<String> email = new Vector<String>();
	private Vector<Integer> point = new Vector<Integer>();

	int spacing;
	int margin1;
	int margin2;
	
	public Customer() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		
		// 고객관리 cloumn 설정
		colNames.add("아이디");
		colNames.add("비밀번호");
		colNames.add("이름");
		colNames.add("전화번호");
		colNames.add("이메일");
		colNames.add("포인트");
		
		// 데이터 불러오기
		id.add("abc123");
		pw.add("abc123");
		name.add("왕경태");
		telephone.add("010-1234-5678");
		email.add("a123@gmail.com");
		point.add(50);

		id.add("qwerr133");
		pw.add("qweerqasd133");
		name.add("고영희");
		telephone.add("010-1111-1111");
		email.add("cat456@gmail.com");
		point.add(3000);
		
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

		// search 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(input.getText().length() != 0) {
					System.out.println("입력된 문자열 :" + input.getText());
					dataSearch(input.getText());
					model.fireTableDataChanged();
				}
			}
		});
		
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
				System.out.println(tableView.getSelectedRow());
				index = tableView.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null
							, "정보를 수정할 고객을 선택해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(
							null
							, name.get(index) + "님의 정보를 수정하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);

					if(n == 0) {
						tableView.clearSelection();
						
						R0.setText(id.get(index));
						R[0].setText(pw.get(index));
						R[1].setText(name.get(index));
						R[2].setText(telephone.get(index));
						R[3].setText(email.get(index));
						R[4].setText(Integer.toString(point.get(index)));
						
						View.setVisible(false);
						Modify.setVisible(true);
					}
				}
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
            // Jtable 내용 편집 x
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
		tableView = new JTable(model);
		tableView.setFont(font4);
		tableView.setRowHeight(30);								// 행간 조절
		tableView.setGridColor(Color.gray);						// 격자색
		tableView.getTableHeader().setReorderingAllowed(false); // 이동 불가
		tableView.getTableHeader().setResizingAllowed(false); 	// 크기 조절 불가
		
		JScrollPane scrollList = new JScrollPane(tableView);
		scrollList.setFont(font4);
		scrollList.setPreferredSize(new Dimension(800, (int)(height*0.5)));	// 테이블 사이즈 조절

		list.setBackground(background);
		list.add(scrollList);

		DefaultTableCellRenderer dtcr;
		for (int i = 0; i < tableView.getColumnCount(); i++) { 
			dtcr = new DefaultTableCellRenderer();	// 셀 내용 정렬 
			if(i < 4) {
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			}
			else {
				dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			TableColumnModel tcm = tableView.getColumnModel();
			tcm.getColumn(i).setCellRenderer(dtcr);
			tableView.getColumnModel().getColumn(i).setPreferredWidth(250);	// JTable 의 컬럼 길이 조절
		}
		
		tableView.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableView.getColumnModel().getColumn(5).setPreferredWidth(100);	
		
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
		
		// 회원가입 정보 입력
		JPanel imformation = new JPanel();
		imformation.setBackground(background);
		JPanel form = new JPanel(new GridLayout(6,2,0,30));
		form.setBackground(background);
		form.setPreferredSize(new Dimension(800,(int)(height*0.5)));
		form.setBorder(BorderFactory.createEmptyBorder((int)(height*0.01), 100, 0, 100));
		
		JLabel L[] = new JLabel[6];
		R = new TextField[5];
		
		for(int i=0; i<L.length; i++) {
			L[i] = new JLabel();
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
			if(i<R.length) {
				R[i] = new TextField();
				R[i].setFont(font3);
				R[i].setForeground(fontcolor);
			}
		}
		
		L[0].setText("아이디");
		L[1].setText("비밀번호");
		L[2].setText("이름");
		L[3].setText("전화번호");
		L[4].setText("이메일");
		L[5].setText("포인트");

		R0 = new JLabel();
		R0.setFont(font3);
		R0.setForeground(fontcolor);
		
		form.add(L[0]);
		form.add(R0);
		form.add(L[1]);
		form.add(R[0]);
		form.add(L[2]);
		form.add(R[1]);
		form.add(L[3]);
		form.add(R[2]);
		form.add(L[4]);
		form.add(R[3]);
		form.add(L[5]);
		form.add(R[4]);

		imformation.add(form);
		
		// btn
		JPanel btns = new JPanel();
		btns.setBackground(background);
		double margin2 = (height < 1000) ? 0.03 : 0.1;
		btns.setBorder(BorderFactory.createEmptyBorder(50, 0, (int)(height*margin2), 0));
		RoundedButton change = new RoundedButton("확인");
		change.setFont(font3);
		
		btns.add(change);
		
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = 0;
				// 예외 처리
				if(R[0].getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R[0].getText().length() < 8) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 짧습니다. 8~16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(R[0].getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 깁니다. 8~16자 이내로 입력해 주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(R[1].getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이름을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R[2].getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "전화번호를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R[3].getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이메일을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isMail(R[3].getText())) {
					JOptionPane.showMessageDialog(null
							, "이메일 형식을 맞춰 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R[4].getText().length() == 0) {	
					JOptionPane.showMessageDialog(null
							, "포인트를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!is.isNum(R[4].getText())) {
					JOptionPane.showMessageDialog(null
							, "포인트를 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if( R[0].getText().equals(pw.get(index))
						&&  R[1].getText().equals(name.get(index))
						&&  R[2].getText().equals(telephone.get(index))
						&&  R[3].getText().equals(email.get(index))
						&&  Integer.parseInt(R[4].getText()) == point.get(index)) {
						JOptionPane.showMessageDialog(null
								, "변경사항이 없습니다!"
								, "박리다매 무인가게"
								, JOptionPane.INFORMATION_MESSAGE
						);
						n = -1;
						
						View.setVisible(true);
						Modify.setVisible(false);
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
						// 데이터 저장
						pw.set(index, R[0].getText());
						name.set(index, R[1].getText());
						telephone.set(index, R[2].getText());
						email.set(index, R[3].getText());
						point.set(index, Integer.parseInt(R[4].getText()));
						
						// repaint
						dataLoad();		
						model.fireTableDataChanged();
	
						JOptionPane.showMessageDialog(null
								, "정상적으로 재고 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);
						View.setVisible(true);
						Modify.setVisible(false);
					}
				}
			}
		});
		
		center.add(navigation, BorderLayout.NORTH);
		center.add(imformation, BorderLayout.CENTER);
		center.add(btns, BorderLayout.SOUTH);
		
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
			rows.add(id.get(i));
			rows.add(pw.get(i));
			rows.add(name.get(i));
			rows.add(telephone.get(i));
			rows.add(email.get(i));
			rows.add(Integer.toString(point.get(i)));
			dataSet.add(rows);
		}
	}
	
	private void dataSearch(String str) {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 검색 데이터 입력
		for (int i = 0; i < name.size(); i++) {
			rows = new Vector<String>();
			rows.add(id.get(i));
			rows.add(pw.get(i));
			rows.add(name.get(i));
			rows.add(telephone.get(i));
			rows.add(email.get(i));
			rows.add(Integer.toString(point.get(i)));
			
			if(name.get(i).indexOf(str) != -1) {
				dataSet.add(rows);
			}
		}
	}
}