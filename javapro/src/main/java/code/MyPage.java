package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.CheckboxGroup;
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

public class MyPage extends Setting {
	public JPanel panel;

	private JPanel Read;
	private JPanel Write;

	public JButton homebtn1;
	public JButton homebtn2;

	public MyPage() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		Read();
		Write();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}

	private void Read() {
		// Read 세팅
		Read = new JPanel();
		Read.setBackground(background);
		Read.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(header_back);
		
		// home 버튼 생성
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); 	// 배경 채우기
		homebtn1.setBorderPainted(false); 		// 외각선
		homebtn1.setFocusPainted(false); 		// 선택 외각선

		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		JLabel subtitle = new JLabel(brand + "점 " + name + "님 환영합니다.");
		subtitle.setFont(font3);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn1, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setBackground(background);
		center.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		JPanel datas = new JPanel();
		datas.setBackground(background);
		JPanel data = new JPanel(new GridLayout(7, 2, 180, 27));
		data.setBackground(background);
		JPanel btn = new JPanel();
		btn.setBackground(background);

		JLabel L1 = new JLabel("이름");
		L1.setFont(font3);
		JLabel L2 = new JLabel("아이디");
		L2.setFont(font3);
		JLabel L3 = new JLabel("비밀번호");
		L3.setFont(font3);
		JLabel L4 = new JLabel("지점명");
		L4.setFont(font3);
		JLabel L5 = new JLabel("매출대비지급액(%)");
		L5.setFont(font3);
		JLabel L6 = new JLabel("직원");
		L6.setFont(font3);
		JLabel L7 = new JLabel("직원 월급");
		L7.setFont(font3);
		
		JLabel R1 = new JLabel(name);
		R1.setFont(font3);
		JLabel R2 = new JLabel(id);
		R2.setFont(font3);
		JLabel R3 = new JLabel(pw);
		R3.setFont(font3);
		JLabel R4 = new JLabel(brand);
		R4.setFont(font3);
		JLabel R5 = new JLabel(Double.toString(percent));
		R5.setFont(font3);
		JLabel R6 = new JLabel((emp == 0) ? "무" : "유");
		R6.setFont(font3);
		JLabel R7 = new JLabel(Integer.toString(empsal));
		R7.setFont(font3);
		
		data.add(L1);
		data.add(R1);
		data.add(L2);
		data.add(R2);
		data.add(L3);
		data.add(R3);
		data.add(L4);
		data.add(R4);
		data.add(L5);
		data.add(R5);
		data.add(L6);
		data.add(R6);
		data.add(L7);
		data.add(R7);
		
		datas.add(data);
		
		RoundedButton change = new RoundedButton("정보수정");
		change.setFont(font3);
		btn.add(change);
		btn.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
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
		center.add(btn, BorderLayout.CENTER);
		
		Read.add(header, BorderLayout.NORTH);
		Read.add(center, BorderLayout.CENTER);

		Read.setVisible(true);
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
		homebtn2 = new JButton("", logo);
		homebtn2.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn2.setContentAreaFilled(false); 	// 배경 채우기
		homebtn2.setBorderPainted(false); 		// 외각선
		homebtn2.setFocusPainted(false); 		// 선택 외각선

		JPanel text = new JPanel(new GridLayout(2, 1, 0, -50));
		text.setBackground(header_back);
		
		JLabel title = new JLabel("마이페이지");
		title.setFont(font2);
		JLabel subtitle = new JLabel(brand + "점 " + name + "님 환영합니다.");
		subtitle.setFont(font3);

		text.add(title);
		text.add(subtitle);
		
		header.add(homebtn2, BorderLayout.WEST);
		header.add(text, BorderLayout.CENTER);
		
		// 정보 보기
		JPanel center = new JPanel(new GridLayout(2, 1));
		center.setBackground(background);
		center.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
		JPanel datas = new JPanel();
		datas.setBackground(background);
		JPanel data = new JPanel(new GridLayout(9, 2, 180, 15));
		data.setBackground(background);
		JPanel btns = new JPanel();
		btns.setBackground(background);
		JPanel btn = new JPanel(new GridLayout(1, 2, 30, 0));
		btn.setBackground(background);

		JLabel L1 = new JLabel("이름");
		L1.setFont(font3);
		JLabel L2 = new JLabel("아이디");
		L2.setFont(font3);
		JLabel L3 = new JLabel("비밀번호");
		L3.setFont(font3);
		JLabel L4 = new JLabel("지점명");
		L4.setFont(font3);
		JLabel L5 = new JLabel("매출대비지급액(%)");
		L5.setFont(font3);
		JLabel L6 = new JLabel("직원");
		L6.setFont(font3);
		JLabel L7 = new JLabel("직원 월급");
		L7.setFont(font3);
		
		TextField R1 = new TextField(name);
		R1.setFont(font5);
		JLabel R2 = new JLabel(id);
		R2.setFont(font5);
		TextField R3 = new TextField(pw);
		R3.setFont(font5);
		TextField R4 = new TextField(brand);
		R4.setFont(font5);
		TextField R5 = new TextField(Double.toString(percent));
		R5.setFont(font5);
		TextField R7 = new TextField(Integer.toString(empsal));
		R7.setFont(font5);
		
		Panel staff = new Panel();
		CheckboxGroup g = new CheckboxGroup();
		final JRadioButton ra1 = new JRadioButton("유", true);
		ra1.setFont(font5);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("무", false);
		ra2.setFont(font5);
		ra2.setBackground(background);
		ButtonGroup group = new ButtonGroup();
		
		group.add(ra1);
		group.add(ra2);
		staff.add(ra1);
		staff.add(ra2);
		
		// 직원 유무 radio 이벤트
	    ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L7.setVisible(true);
				R7.setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				L7.setVisible(false);
				R7.setVisible(false);
			}
		});
		
		data.add(L1);
		data.add(R1);
		data.add(L2);
		data.add(R2);
		data.add(L3);
		data.add(R3);
		data.add(L4);
		data.add(R4);
		data.add(L5);
		data.add(R5);
		data.add(L6);
		data.add(staff);
		data.add(L7);
		data.add(R7);
		
		datas.add(data);
		
		RoundedButton check = new RoundedButton("확인");
		check.setFont(font3);
		RoundedButton cancel = new RoundedButton("취소");
		cancel.setFont(font3);
		btn.add(check);
		btn.add(cancel);
		btn.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		btns.add(btn);
		
		// 버튼 이벤트
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(R1.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "이름을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R3.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "비밀번호를 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R4.length() == 0) {
					JOptionPane.showMessageDialog(null
							, "지점명을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(ra1.isSelected() == true && R5.getText().length() == 0) {	// 예외 처리 매출대비지급액
					JOptionPane.showMessageDialog(null
							, "매출대비지급액을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(!in.isNum(pl7Tf5.getText())) {
					JOptionPane.showMessageDialog(null
							, "매출대비지급액은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R7 == 1 && (pl7Tf7.getText()).length() == 0) {		// 예외 처리 직원 월급
					JOptionPane.showMessageDialog(null
							, "직원 월급을 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else if(R7 == 1 && !in.isNum(pl7Tf7.getText())) {
					JOptionPane.showMessageDialog(null
							, "직원 월급은 숫자만 입력해주세요."
							, "박리다매 무인가게"
							, JOptionPane.ERROR_MESSAGE
					);
				} else {
					JOptionPane.showMessageDialog(null
							, "정상적으로 정보 수정 완료!"
							, "박리다매 무인가게"
							, JOptionPane.PLAIN_MESSAGE
					);
					
					int n = JOptionPane.showConfirmDialog(
							null
							, "변경사항을 저장하시겠습니까?"
							, "박리다매 무인가게"
							, JOptionPane.YES_NO_OPTION
							, JOptionPane.WARNING_MESSAGE
					);
				
					if(n == 0) {
						// 변경 데이터 저장
						name = R1.getText();
						id = R2.getText();
						pw = R3.getText();
						brand = R4.getText();
						percent = Double.parseDouble(R5.getText());
						emp = (ra1.isSelected() == true) ? 1 : 0;
						empsal = (R7.getText().length() != 0) ? Integer.parseInt(R7.getText()) : 0;
						
						// reloading
						Read.setVisible(false);
						Read();
	
						Read.setVisible(true); // 화면 전환
						Write.setVisible(false);
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
		center.add(btns, BorderLayout.CENTER);
		
		Write.add(header, BorderLayout.NORTH);
		Write.add(center, BorderLayout.CENTER);

		Write.setVisible(false);
		panel.add(Write);
	}
}