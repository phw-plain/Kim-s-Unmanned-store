package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class MyPage extends Setting {
	public JPanel panel;

	private JPanel Check;
	public JPanel Read;
	private JPanel Write;

	public JButton homebtn0;
	public JButton homebtn1;
	public JButton homebtn2;
	
	public MyPage() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		homebtn0 = new JButton("", logo);
		homebtn1 = new JButton("", logo);
		homebtn2 = new JButton("", logo);
		Check();
		Read();
		Write();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	public void reLoad() {
		Check.setVisible(true);
		Read.setVisible(false);
		Write.setVisible(false);
	}

	public void Check() {
		// Read 세팅
		Check = new JPanel();
		Check.setBackground(background);
		Check.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn0.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn0.setContentAreaFilled(false); 	// 배경 채우기
		homebtn0.setBorderPainted(false); 		// 외각선
		homebtn0.setFocusPainted(false); 		// 선택 외각선

		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		title.setForeground(Setting.title);

		text.add(title);
		
		header.add(homebtn0, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 본인 확인
		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(background);
		
		JLabel subTitle = new JLabel("본인확인");
		subTitle.setFont(font2);
		subTitle.setForeground(Setting.title);
		subTitle.setHorizontalAlignment(JLabel.CENTER);
		int margin = (height < 1000) ? height/6 : height/5;
		subTitle.setBorder(BorderFactory.createEmptyBorder(100, 0, margin, 0));
		
		JPanel subCenter = new JPanel();
		subCenter.setBackground(background);
		JPanel textbox = new JPanel(new GridLayout(1,2,5,0));
		textbox.setBackground(background);
		JLabel password = new JLabel("비밀번호");
		password.setFont(font3);
		password.setForeground(Setting.title);
		final TextField pw = new TextField("", 15);
		pw.setEchoChar('●');
		pw.setFont(font4);

		textbox.add(password);
		textbox.add(pw);
		subCenter.add(textbox);

		JPanel btn = new JPanel();
		btn.setBackground(background);
	    btn.setBorder(BorderFactory.createEmptyBorder(0, 0, (height > 1000) ? 200 : 100, 0));
		RoundedButton check = new RoundedButton("확인");
	    check.setFont(font3);
		btn.add(check);
		
		// 본인확인 체크 버튼 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pw.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
						, "비밀번호를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else if(!pw.getText().equals(getPw())) {
					JOptionPane.showMessageDialog(null
						, "비밀번호가 틀렸습니다! 다시 한번 확인 해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
						, "환영합니다!"
						, "박리다매 무인가게"
						, JOptionPane.PLAIN_MESSAGE
					);
					Check.setVisible(false);
					Read.setVisible(true);
					pw.setText("");
				}
			}
		});
		
		center.add(subTitle, BorderLayout.NORTH);
		center.add(subCenter, BorderLayout.CENTER);
		center.add(btn, BorderLayout.SOUTH);
		
		Check.add(header, BorderLayout.NORTH);
		Check.add(center, BorderLayout.CENTER);

		Check.setVisible(true);
		panel.add(Check);

	}
	
	public void Read() {
		// Read 세팅
		Read = new JPanel();
		Read.setBackground(background);
		Read.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); 	// 배경 채우기
		homebtn1.setBorderPainted(false); 		// 외각선
		homebtn1.setFocusPainted(false); 		// 선택 외각선

		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		title.setForeground(Setting.title);
		JLabel subtitle = new JLabel(getBrand() + "점 " + getName() + "님 환영합니다.");
		subtitle.setFont(font3);
		subtitle.setForeground(Setting.title);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn1, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel();
		center.setBackground(background);
		JPanel datas = new JPanel();
		datas.setBackground(background);
	    int margin = (height < 1000) ? 50+(height/30) : 100+(height/30);
		datas.setBorder(BorderFactory.createEmptyBorder(margin, 0, 0, 0));
		int spacing = (height < 1000) ? 25 : 27;
		JPanel data = new JPanel(new GridLayout(7, 2, 180, spacing));
		data.setBackground(background);
		JPanel btn = new JPanel();
		btn.setBackground(background);

		JLabel L[] = new JLabel[7];
		JLabel R[] = new JLabel[7];
		
		L[0] = new JLabel("이름");
		L[1] = new JLabel("아이디");
		L[2] = new JLabel("비밀번호");
		L[3] = new JLabel("지점명");
		L[4] = new JLabel("위치");
		L[5] = new JLabel("직원");
		L[6] = new JLabel("직원 월급");
		
		R[0] = new JLabel(getName());
		R[1] = new JLabel(getId());
		R[2] = new JLabel(getPw());
		R[3] = new JLabel(getBrand());
		R[4] = new JLabel(getLocation());
		R[5] = new JLabel((emp)?"유":"무");
		R[6] = new JLabel(Integer.toString(getEmpsal()));
		
		for(int i=0; i<L.length; i++ ) {
			R[i].setFont(font3);
			R[i].setForeground(fontcolor);
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
			data.add(L[i]);
			data.add(R[i]);
		}
		
		datas.add(data);
		
		RoundedButton change = new RoundedButton("정보수정");
		change.setFont(font3);
		btn.add(change);
		int margin2 = (height < 1000) ? 10 : 5;
		btn.setBorder(BorderFactory.createEmptyBorder(0, 0, height/margin2, 0));
		
		// 버튼 이벤트
		change.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read.setVisible(false); // 화면 전환
				
				// reloading
				Write.setVisible(false);
				Write();

				Write.setVisible(true);
			}
		});
		
		center.add(datas, BorderLayout.NORTH);
		
		Read.add(header, BorderLayout.NORTH);
		Read.add(center, BorderLayout.CENTER);
		Read.add(btn, BorderLayout.SOUTH);

		Read.setVisible(false);
		panel.add(Read);

	}

	private void Write() {
		// Write 세팅
		Write = new JPanel();
		Write.setBackground(background);
		Write.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn2.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn2.setContentAreaFilled(false); 	// 배경 채우기
		homebtn2.setBorderPainted(false); 		// 외각선
		homebtn2.setFocusPainted(false); 		// 선택 외각선
		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		title.setForeground(Setting.title);
		JLabel subtitle = new JLabel(getBrand() + "점 " + getName() + "님 환영합니다.");
		subtitle.setFont(font3);
		subtitle.setForeground(Setting.title);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn2, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel();
		center.setBackground(background);
		JPanel datas = new JPanel();
		datas.setBackground(background);
		JPanel data = new JPanel(new GridLayout(9, 2, 100, 15));
		data.setBackground(background);

	    int margin = (height < 1000) ? 50+(height/30) : 100+(height/30);
		datas.setBorder(BorderFactory.createEmptyBorder(margin, 0, 0, 0));
		
		JPanel btns = new JPanel();
		btns.setBackground(background);
		JPanel btn = new JPanel(new GridLayout(1, 2, 30, 0));
		btn.setBackground(background);

		final JLabel L[] = new JLabel[7];	
		L[0] = new JLabel("이름");
		L[1] = new JLabel("아이디");
		L[2] = new JLabel("비밀번호");
		L[3] = new JLabel("지점명");
		L[4] = new JLabel("위치");
		L[5] = new JLabel("직원");
		L[6] = new JLabel("직원 월급");
		
		for(int i=0; i<L.length; i++ ) {
			L[i].setFont(font3);
			L[i].setForeground(fontcolor);
		}
		
		final JTextField R1 = new JTextField(getName(), 15);
		R1.setFont(font6);
		final JLabel R2 = new JLabel(getId());
		R2.setFont(font6);
		R2.setForeground(fontcolor);
		final JTextField R3 = new JTextField(getPw(), 15);
		R3.setFont(font6);
		final JTextField R4 = new JTextField(getBrand(), 15);
		R4.setFont(font6);
		final JTextField R5 = new JTextField(getLocation(), 15);
		R5.setFont(font6);
		final JTextField R7 = new JTextField(Integer.toString(getEmpsal()), 15);
		R7.setFont(font6);
		
		Panel staff = new Panel();
		CheckboxGroup g = new CheckboxGroup();
		final JRadioButton ra1 = new JRadioButton("유", (emp)? true:false);
		ra1.setFont(font6);
		ra1.setForeground(fontcolor);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("무", (emp) ? false:true);
		ra2.setFont(font6);
		ra2.setForeground(fontcolor);
		ra2.setBackground(background);
		ButtonGroup group = new ButtonGroup();
		if(emp == false) {
			L[6].setVisible(false);
			R7.setVisible(false);
		}
		
		group.add(ra1);
		group.add(ra2);
		staff.add(ra1);
		staff.add(ra2);
		
		// 직원 유무 radio 이벤트
	    ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[6].setVisible(true);
				R7.setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L[6].setVisible(false);
				R7.setVisible(false);
			}
		});
		
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
		data.add(staff);
		data.add(L[6]);
		data.add(R7);
		
		datas.add(data);
		
		RoundedButton check = new RoundedButton("확인");
		check.setFont(font3);
		RoundedButton cancel = new RoundedButton("취소");
		cancel.setFont(font3);
		btn.add(check);
		btn.add(cancel);
		int margin2 = (height < 1000) ? 10 : 5;
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, height/margin2, 0));
		btns.add(btn);
		
		// 버튼 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 예외 처리
				if(R1.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이름을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R1.getText().length() > 12) {
	 				JOptionPane.showMessageDialog(null
		 					, "이름이 너무 깁니다. 12자 이내로 입력해 주세요."
		 					, "박리다매 무인가게"
		 					, JOptionPane.ERROR_MESSAGE
	 					);
 				} else if(!is.isString2(R1.getText())) {           
 					JOptionPane.showMessageDialog(null
 							, "이름에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
 							, "박리다매 무인가게"
 							, JOptionPane.ERROR_MESSAGE
	                );
 	            } else if(R3.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.getText().length() < 8) {
 					JOptionPane.showMessageDialog(null
							, "비밀번호가 너무 짧습니다. 8~16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(R3.getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
							, "비밀번호가 너무 깁니다. 8~16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString1(R3.getText())) {         
 					JOptionPane.showMessageDialog(null
	 						, "비밀번호는 영숫자를 사용하여 입력해 주세요."
	 						, "박리다매 무인가게"
	 						, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(R4.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "지점명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R4.getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
							, "지점명이 너무 깁니다. 16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(R5.getText().length() == 0) {
					JOptionPane.showMessageDialog(null
							, "위치를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R5.getText().length() > 16) {
 					JOptionPane.showMessageDialog(null
							, "위치가 너무 깁니다. 16자 이내로 입력해 주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(ra1.isSelected() && (R7.getText()).length() == 0) {		// 예외 처리 직원 월급
					JOptionPane.showMessageDialog(null
							, "직원 월급을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(ra1.isSelected() && !is.isNum(R7.getText())) {
					JOptionPane.showMessageDialog(null
							, "직원 월급은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					int n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
					
					if(n == 0) {
						// 변경 데이터 저장
						setName(R1.getText());
						setPw(R3.getText());
						setBrand(R4.getText());
						setLocation(R5.getText());
						emp = (ra1.isSelected() == true) ? true : false;
						setEmpsal((R7.getText().length() != 0) ? Integer.parseInt(R7.getText()) : 0);

						// reloading
						panel.remove(1);
						Read.setVisible(true);
						Read();
						
						JOptionPane.showMessageDialog(null
								, "정상적으로 정보 수정 완료!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
						);

						Write.setVisible(false);
						Read.setVisible(true);
					}
				}
			}
		});
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read.setVisible(true); // 화면 전환
				Write.setVisible(false);
			}
		});
		
		center.add(datas, BorderLayout.NORTH);
		
		Write.add(header, BorderLayout.NORTH);
		Write.add(center, BorderLayout.CENTER);
		Write.add(btns, BorderLayout.SOUTH);

		Write.setVisible(false);
		panel.add(Write);
	}
}