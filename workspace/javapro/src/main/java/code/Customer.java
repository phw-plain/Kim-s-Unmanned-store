package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import firebase.Firebase_Customer;

public class Customer extends Setting {
	public JPanel panel;

	public JPanel View;
	private JPanel Modify;
	private JPanel Permute; 

	public JButton homebtn1;
	public JButton homebtn2;
	public JButton homebtn3;

	// 고객 보기
	private DefaultTableModel model;
	private JTable tableView;
	private Vector<Vector> dataSet = new Vector<Vector>();
	private Vector<String> colNames = new Vector<String>();
	
	// 고객의 환불 & 교환 신청 정보
	private DefaultTableModel model2;
	private JTable tableView2;
	private Vector<Vector> dataSet2 = new Vector<Vector>();
	private Vector<String> colNames2 = new Vector<String>();
	
	int index;
	JTextField R[];
	JLabel R0;
	
	// 고객 DB 참조

	int spacing;
	int margin1;
	int margin2;
	
	Firebase_Customer firebase = new Firebase_Customer();
	
	private String permute_id;
	private JLabel permute_text;
	
	public Customer() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		homebtn3 = new JButton("", logo);
		
		// 변수 초기화
		permute_id = null;
		permute_text = new JLabel("");
		
		// 고객관리 cloumn 설정
		colNames.add("이름");
		colNames.add("전화번호");
		colNames.add("이메일");
		colNames.add("포인트");
		colNames.add("교환 신청");
		colNames.add("환불 신청");
		
		// 데이터 불러오기
		customer_name.add("왕경태");
		customer_telephone.add("010-1234-5678");
		customer_email.add("a123@gmail.com");
		customer_point.add(50);
		customer_exchange.add(2);
		customer_refund.add(1);

		customer_name.add("고영희");
		customer_telephone.add("010-1111-1111");
		customer_email.add("cat456@gmail.com");
		customer_point.add(3000);
		customer_exchange.add(1);
		customer_refund.add(0);
		
		Firebase_Customer customer = new Firebase_Customer();
		customer.show_customer();
		
		// 고객 환불 및 교환 cloumn 설정
		colNames2.add("제품명");
		colNames2.add("수량");
		colNames2.add("구매 일자");
		colNames2.add("신청 일자");
		colNames2.add("환불&교환");
		colNames2.add("신청 유형");
		colNames2.add("신청 사유");
		
		View();
		Modify();
		Permute();
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
		title.setForeground(Setting.title);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);
		
		// navigation: search, button
		JPanel navigation = new JPanel(new BorderLayout());
		navigation.setBackground(background);
		JPanel navLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		navLeft.setBackground(background);
		JPanel navRight = new JPanel(new GridLayout(1, 3, 10, 0));
		navRight.setBackground(background);
		
		JPanel search = new JPanel();
		search.setBackground(background);
		double margin = (height < 1000) ? 0.12 : 0.2;
		search.setBorder(BorderFactory.createEmptyBorder(50, (int)(width*margin), 0, 0));
		final JTextField input = new JTextField(" ", 20);
		HalfRoundedButton check = new HalfRoundedButton("🔍");

		// search 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(input.getText().length() != 0) {
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
		RoundedButton btnPermute = new RoundedButton("환불&교환");
		navRight.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, (int)(width*margin)));

		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
							, customer_name.get(index) + "님의 정보를 수정하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.QUESTION_MESSAGE
					);

					if(n == 0) {
						tableView.clearSelection();
						
						R[0].setText(customer_name.get(index));
						R0.setText(customer_telephone.get(index));
						R[1].setText(customer_email.get(index));
						R[2].setText(Integer.toString(customer_point.get(index)));
						R[3].setText(Integer.toString(customer_exchange.get(index)));
						R[4].setText(Integer.toString(customer_refund.get(index)));
						
						View.setVisible(false);
						Modify.setVisible(true);
						Permute.setVisible(false);
					}
				}
			}
		});
		btnPermute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = tableView.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null
						, "환불 및 교환 처리를 할 고객을 선택해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else if(false /* 환불 & 교환 신청이 없는 경우 */) {
					JOptionPane.showMessageDialog(null
						, customer_name.get(index) + "님의 환불 및 교환 신청이 없습니다!"
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(null
						, customer_name.get(index) + "님의 환불 및 교환 신청 정보를 확인 하시겠습니까?"
						, "박리다매 무인가게"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE
					);

					if(n == 0) {
						tableView.clearSelection();
						
						// 해당 고객의 환불 교환 신청 목록 가져오기
						permuteDataLoad(customer_telephone.get(index));
						model2.fireTableDataChanged();
						permute_id = customer_telephone.get(index);
						permute_text.setText(customer_name.get(index) + "님의 환불 및 교환 신청 리스트");
						
						View.setVisible(false);
						Modify.setVisible(false);
						Permute.setVisible(true);
					}
				}
			}
		});
		
		navLeft.add(search);
		navRight.add(btnView);
		navRight.add(btnModify);
		navRight.add(btnPermute);
		
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
		scrollList.setPreferredSize(new Dimension(800, (int)(height*((height > 1000) ? 0.6 : 0.5))));	// 테이블 사이즈 조절

		list.setBackground(background);
		list.add(scrollList);

		DefaultTableCellRenderer dtcr;
		for (int i = 0; i < tableView.getColumnCount(); i++) { 
			dtcr = new DefaultTableCellRenderer();	// 셀 내용 정렬 
			if(i < 3) {
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			}
			else {
				dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			TableColumnModel tcm = tableView.getColumnModel();
			tcm.getColumn(i).setCellRenderer(dtcr);
			tableView.getColumnModel().getColumn(i).setPreferredWidth(100);	// JTable 의 컬럼 길이 조절
		}

		tableView.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableView.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableView.getColumnModel().getColumn(2).setPreferredWidth(250);
		
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
		title.setForeground(Setting.title);

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
		RoundedButton btnPermute = new RoundedButton("환불&교환");
		navRight.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, (int)(width*margin)));

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(true);
				Modify.setVisible(false);
				Permute.setVisible(false);
			}
		});
		btnPermute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null
					, "보기에서 선택한 후 이용할 수 있습니다!"
					, "박리다매 무인가게"
					, JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		
		navRight.add(btnView);
		navRight.add(btnModify);
		navRight.add(btnPermute);
		
		navigation.add(navLeft, BorderLayout.WEST);
		navigation.add(navRight, BorderLayout.EAST);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(background);
		
		// 회원 정보 입력
		JPanel imformation = new JPanel();
		imformation.setBackground(background);
		JPanel form = new JPanel(new GridLayout(6,2,0,20));
		form.setBackground(background);
		form.setPreferredSize(new Dimension(800,(int)(height*0.5)));
		form.setBorder(BorderFactory.createEmptyBorder((int)(height*0.01), 100, 0, 100));
		
		JLabel L[] = new JLabel[6];
		R = new JTextField[5];
		
		for(int i=0; i<L.length; i++) {
			L[i] = new JLabel();
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
			if(i<R.length) {
				R[i] = new JTextField();
				R[i].setFont(font3);
			}
		}
		
		L[0].setText("이름");
		L[1].setText("전화번호");
		L[2].setText("이메일");
		L[3].setText("포인트");
		L[4].setText("교환신청");
		L[5].setText("환불신청");

		R0 = new JLabel();
		R0.setFont(font3);
		R0.setForeground(fontcolor);
		
		form.add(L[0]);
		form.add(R[0]);
		form.add(L[1]);
		form.add(R0);
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
		double margin2 = (height < 1000) ? 0.05 : 0.1;
		btns.setBorder(BorderFactory.createEmptyBorder(10, 0, (int)(height*margin2), 0));
		RoundedButton change = new RoundedButton("확인");
		change.setFont(font3);
		RoundedButton cancel = new RoundedButton("취소");
		cancel.setFont(font3);
		
		btns.add(change);
		btns.add(cancel);
	
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
				} else if( R[0].getText().equals(customer_name.get(index))
						&&  R[1].getText().equals(customer_email.get(index))
						&&  Integer.parseInt(R[2].getText()) == customer_point.get(index)
						&&  Integer.parseInt(R[3].getText()) == customer_exchange.get(index)
						&&  Integer.parseInt(R[4].getText()) == (customer_refund.get(index))) {
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
							, JOptionPane.QUESTION_MESSAGE
					);
					if(n == 0) {
						// 데이터 저장
						customer_name.set(index, R[0].getText());
						customer_email.set(index, R[1].getText());
						customer_point.set(index, Integer.parseInt(R[2].getText()));
						customer_exchange.set(index, Integer.parseInt(R[3].getText()));
						customer_refund.set(index, Integer.parseInt(R[4].getText()));
						
						
						try {
							firebase.update_cutomer(customer_id.get(index),customer_pw.get(index),customer_name.get(index), customer_telephone.get(index),customer_email.get(index),customer_point.get(index));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// repaint
						dataLoad();		
						model.fireTableDataChanged();
	
						JOptionPane.showMessageDialog(null
								, "정상적으로 정보 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);
						View.setVisible(true);
						Modify.setVisible(false);
					}
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(true);
				Modify.setVisible(false);
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
	
	private void Permute() {
		// Permute 세팅
		Permute = new JPanel();
		Permute.setBackground(background);
		Permute.setLayout(new BorderLayout());
		
		// header
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		homebtn3.setRolloverIcon(logo_over); 	
		homebtn3.setContentAreaFilled(false); 	
		homebtn3.setBorderPainted(false); 		
		homebtn3.setFocusPainted(false); 		
		
		JLabel title = new JLabel("고객 관리");
		title.setFont(font2);
		title.setForeground(Setting.title);

		header.add(homebtn3, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);
		
		double margin = (height < 1000) ? 0.12 : 0.2;
		
		// navigation: text, button
		JPanel navigation = new JPanel(new BorderLayout());
		navigation.setBackground(background);
		JPanel navLeft = new JPanel();
		navLeft.setBackground(background);
		System.out.println(margin2);
		navLeft.setBorder(BorderFactory.createEmptyBorder(55, (int)(width*margin), 0, 0));
		JPanel navRight = new JPanel(new GridLayout(1, 3, 10, 0));
		navRight.setBackground(background);

		// navLeft
		permute_text.setFont(font3);
		permute_text.setForeground(Setting.title);
		navLeft.add(permute_text);
		
		// navRight
		RoundedButton btnView = new RoundedButton("고객 보기");
		RoundedButton btnModify = new RoundedButton("고객 수정");
		RoundedButton btnPermute = new RoundedButton("환불&교환");
		btnPermute.nomal = new Color(120, 120, 120);
		btnPermute.setForeground(Color.white);
		btnPermute.setEnabled(false);
		navRight.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, (int)(width*margin)));

		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				View.setVisible(true);
				Modify.setVisible(false);
			}
		});
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null
					, "보기에서 선택한 후 이용할 수 있습니다!"
					, "박리다매 무인가게"
					, JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		
		navRight.add(btnView);
		navRight.add(btnModify);
		navRight.add(btnPermute);
		
		navigation.add(navLeft, BorderLayout.WEST);
		navigation.add(navRight, BorderLayout.EAST);

		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(background);
		
		// list
		JPanel list = new JPanel();

		model2 = new DefaultTableModel(dataSet2, colNames2) {
            // Jtable 내용 편집 x
            public boolean isCellEditable(int i, int c) {
                return false;
            }
        };
		tableView2 = new JTable(model2);
		tableView2.setFont(font4);
		tableView2.setRowHeight(30);								// 행간 조절
		tableView2.setGridColor(Color.gray);						// 격자색
		tableView2.getTableHeader().setReorderingAllowed(false); 	// 이동
		tableView2.getTableHeader().setResizingAllowed(true); 		// 크기 조절
		tableView2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		// 가로 스크롤
		
		JScrollPane scrollList = new JScrollPane(tableView2);
		scrollList.setFont(font4);
		scrollList.setPreferredSize(new Dimension(800, (int)(height*((height > 1000) ? 0.6 : 0.5))));	// 테이블 사이즈 조절

		list.setBackground(background);
		list.add(scrollList);

		DefaultTableCellRenderer dtcr;
		for (int i = 0; i < tableView2.getColumnCount(); i++) { 
			dtcr = new DefaultTableCellRenderer();	// 셀 내용 정렬 
			if(i != 1) {
				dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			}
			else {
				dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			TableColumnModel tcm = tableView2.getColumnModel();
			tcm.getColumn(i).setCellRenderer(dtcr);
			tableView2.getColumnModel().getColumn(i).setPreferredWidth(60);	// JTable 의 컬럼 길이 조절
		}

		tableView2.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableView2.getColumnModel().getColumn(2).setPreferredWidth(120);
		tableView2.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableView2.getColumnModel().getColumn(5).setPreferredWidth(120);	
		tableView2.getColumnModel().getColumn(6).setPreferredWidth(500);	
		
		center.add(navigation, BorderLayout.NORTH);
		center.add(list, BorderLayout.CENTER);
		
		JPanel btns = new JPanel();
		RoundedButton approval = new RoundedButton("승인");
		RoundedButton withdrawal = new RoundedButton("철회");
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(250*margin), 0));
		btns.setBackground(background);
		btns.add(approval);
		btns.add(withdrawal);
		
		
		approval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = tableView2.getSelectedRow();
				System.out.println(tableView2.getSelectedRow());
				if(index == -1) {
					JOptionPane.showMessageDialog(null
						, "환불 및 교환을 승인할 사항을 선택해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(null
						, dataSet2.get(index).get(0) + " " + dataSet2.get(index).get(1)  + "개를 " + dataSet2.get(index).get(4) + "승인하시겠습니까?"
						, "박리다매 무인가게"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE
					);

					if(n == 0) {
						JOptionPane.showMessageDialog(null
							, "승인하였습니다!"
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
						);
						
						// 데이터베이스에서 해당 신청 목록 삭제하기
						// permute_id변수 이용 : 전화번호
						
						// 다시 그리기
						permuteDataLoad(permute_id);
						model2.fireTableDataChanged();
					}
				}
			}
		});
		
		withdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = tableView2.getSelectedRow();
				System.out.println(tableView2.getSelectedRow());
				if(index == -1) {
					JOptionPane.showMessageDialog(null
						, "환불 및 교환을 철회할 사항을 선택해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(null
						, dataSet2.get(index).get(0) + " " + dataSet2.get(index).get(1)  + "개를 " + dataSet2.get(index).get(4) + "을 철회하시겠습니까?"
						, "박리다매 무인가게"
						, JOptionPane.YES_NO_OPTION
						, JOptionPane.QUESTION_MESSAGE
					);

					if(n == 0) {
						JOptionPane.showMessageDialog(null
							, "철회하였습니다!"
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
						);
						
						// 데이터베이스에서 해당 신청 목록 삭제하기
						// permute_id변수 이용 : 전화번호
						
						// 다시 그리기
						permuteDataLoad(permute_id);
						model2.fireTableDataChanged();
					}
				}
			}
		});
		
		Permute.add(header, BorderLayout.NORTH);
		Permute.add(center, BorderLayout.CENTER);
		Permute.add(btns, BorderLayout.SOUTH);

		Permute.setVisible(false);
		panel.add(Permute);
	}
	
	public void reLoad() {
		tableView.clearSelection();
		
		View.setVisible(true);
		Modify.setVisible(false);
		Permute.setVisible(false);
	}
	
	private void dataLoad() {
		dataSet.removeAllElements();
		Vector<String> rows;
		
		// 데이터 입력
		for (int i = 0; i < customer_name.size(); i++) {
			rows = new Vector<String>();
			rows.add(customer_name.get(i));
			rows.add(customer_telephone.get(i));
			rows.add(customer_email.get(i));
			rows.add(Integer.toString(customer_point.get(i)));
			rows.add(Integer.toString(customer_exchange.get(i)));
			rows.add(Integer.toString(customer_refund.get(i)));
			dataSet.add(rows);
		}
	}
	
	private void permuteDataLoad(String telephone) {
		dataSet2.removeAllElements();
		Vector<String> rows = null;
		
		// 전화번호 이용해서 데이터 가져오기
		
		
		// 프론트엔드용 데이터
		pdt_name.add("토종 햇 당근");
		pdt_cnt.add(3);
		buy.add("2022-5-1");
		apply.add("2022-5-3");
		permute.add("환불");
		reasons.add("단순 변심");
		grounds.add("당근이 먹고 싶은 줄 알았는데 생각해보니 집에 당근이 있어서 환불하고 싶어졌어요.");
		
		pdt_name.add("아이셔 레몬");
		pdt_cnt.add(3);
		buy.add("2022-5-22");
		apply.add("2022-5-23");
		permute.add("교환");
		reasons.add("상품 불량");
		grounds.add("분명 시다고 해서 구매했는데 단맛이나더라구요? 신맛나는 레몬으로 교환해주세요.");
		
		// 데이터 입력
		for (int i = 0; i < pdt_name.size(); i++) {
			rows = new Vector<String>();
			rows.add(pdt_name.get(i));
			rows.add(Integer.toString(cnt.get(i)));
			rows.add(buy.get(i));
			rows.add(apply.get(i));
			rows.add(permute.get(i));
			rows.add(reasons.get(i));
			rows.add(grounds.get(i));
			dataSet2.add(rows);
		}
	}
	
	private void dataSearch(String str) {
		dataSet.removeAllElements();
		Vector<String> rows;
		// 검색 데이터 입력
		for (int i = 0; i < customer_name.size(); i++) {
			rows = new Vector<String>();
			rows.add(customer_name.get(i));
			rows.add(customer_telephone.get(i));
			rows.add(customer_email.get(i));
			rows.add(Integer.toString(customer_point.get(i)));
			rows.add(Integer.toString(customer_exchange.get(i)));
			rows.add(Integer.toString(customer_refund.get(i)));
			
			if(customer_name.get(i).indexOf(str) != -1) {
				dataSet.add(rows);
			} else if(str.equals(" ")) {
				dataSet.add(rows);
			} else {
				System.out.println(str);
			}
		}
	}
}