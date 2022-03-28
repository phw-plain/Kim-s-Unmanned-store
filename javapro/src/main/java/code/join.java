package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

class join extends Setting{
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Panel form;
	private Button b1;
	 
	// 아이디 중복 체크용 변수
	private boolean idcheck = false;
	
	public join() {
		prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame 에 대한 셋팅
		mainFrame = new Frame("박리다매 무인가게");
		mainFrame.setSize(1280 ,1024);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경
		
		// 상단 제목
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("회원가입");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		// 회원가입 정보 입력
		form = new Panel(new GridLayout(7,4,0,5));
		form.setPreferredSize(new Dimension(800,300));
		
		JLabel blankL1 = new JLabel();
		JLabel blankR1 = new JLabel();
		JLabel label1 = new JLabel("이름");
		label1.setFont(font2);
		final TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(blankL1);
		form.add(label1);
		form.add(tf1);
		form.add(blankR1);
		
		JLabel blankL2 = new JLabel();
		Panel btnlabel = new Panel();
		btnlabel.setLayout(new BorderLayout());
		JButton b1 = new JButton("중복확인");
		b1.setFont(font2);
		b1.setBackground(Color.white);
		btnlabel.add(b1, BorderLayout.WEST);
		JLabel label2 = new JLabel("아이디");
		label2.setFont(font2);
		final TextField tf2 = new TextField("", 15);
		tf2.selectAll();
		form.add(blankL2);
		form.add(label2);
		form.add(tf2);
		form.add(btnlabel);
		
		JLabel blankL3 = new JLabel();
		JLabel blankR3 = new JLabel();
		JLabel label3 = new JLabel("비밀번호");
		label3.setFont(font2);
		final TextField tf3 = new TextField("", 15);
		tf3.selectAll(); // tf2.setEchoChar('*');
		form.add(blankL3);
		form.add(label3);
		form.add(tf3);
		form.add(blankR3);

		JLabel blankL4 = new JLabel();
		JLabel blankR4 = new JLabel();
		JLabel label4 = new JLabel("지점명");
		label4.setFont(font2);
		final TextField tf4 = new TextField("", 15);
		tf4.selectAll(); 
		form.add(blankL4);
		form.add(label4);
		form.add(tf4);
		form.add(blankR4);

		JLabel blankL5 = new JLabel();
		JLabel blankR5 = new JLabel();
		JLabel label5 = new JLabel("매출대비지급액(%)");
		label5.setFont(font2);
		final TextField tf5 = new TextField("", 15);
		tf5.selectAll(); 
		form.add(blankL5);
		form.add(label5);
		form.add(tf5);
		form.add(blankR5);

		JLabel blankL6 = new JLabel();
		JLabel blankR6 = new JLabel();
		Panel staff = new Panel();
		JLabel label6 = new JLabel("직원");
		label6.setFont(font2);
		CheckboxGroup g = new CheckboxGroup();
		final JRadioButton ra1 = new JRadioButton("유", true);
		ra1.setFont(font2);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("무", false);
		ra2.setFont(font2);
		ra2.setBackground(background);
		ButtonGroup group = new ButtonGroup();
		
		group.add(ra1);
		group.add(ra2);

		staff.add(ra1);
		staff.add(ra2);
		form.add(blankL6);
		form.add(label6);
		form.add(staff);
		form.add(blankR6);

		JLabel blankL7 = new JLabel();
		JLabel blankR7 = new JLabel();
		final JLabel label7 = new JLabel("직원 월급");
		label7.setFont(font2);
		final TextField tf7 = new TextField("", 15);
		tf7.selectAll(); 
		form.add(blankL7);
		form.add(label7);
		form.add(tf7);
		form.add(blankR7);

		// 버튼 생성
		JPanel btns = new JPanel(new FlowLayout());
		
		RoundedButton check = new RoundedButton("확인");
		RoundedButton cancel = new RoundedButton("취소");
		
		// 버튼 설정
		check.setFocusPainted(false); 
		check.setFont(font3);
		cancel.setFocusPainted(false); 
		cancel.setFont(font3);
		
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // 아이디 중복 체크
	    b1.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
		    	String id = tf2.getText();
		    	
		    	if(tf2.getText().length() == 0) {
		    		JOptionPane.showMessageDialog(null
							, "아이디를 입력해주세요."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                idcheck = false;
				} else if(/*m.select(tf2.getText())==false*/false) {
					JOptionPane.showMessageDialog(null
							, "사용불가능한 아이디 입니다."
		                    , "DSMS"
		                    , JOptionPane.ERROR_MESSAGE
		            );
		            idcheck = false;
	            } else if(!is.isString1(tf2.getText())) {            // 예외 처리 아이디
		            JOptionPane.showMessageDialog(null
		            		, "사용불가능한 아이디 입니다."
		            		, "DSMS"
		                   	, JOptionPane.ERROR_MESSAGE
		           	);
		            idcheck = false;
		        } else {
					JOptionPane.showMessageDialog(null
							, "사용가능한 아이디 입니다."
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					idcheck = true;
				}
		    }
		});
	    
	    // 직원 유무 radio 이벤트
	    ra1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(true);
				tf7.setVisible(true);
			}
		});
		ra2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label7.setVisible(false);
				tf7.setVisible(false);
			}
		});
	    
	    // 확인 버튼 클릭 이벤트
 		check.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			// 데이터 저장 변수 선언
	 			String name = tf1.getText();
	 			String id = tf2.getText();
	 			String pw = tf3.getText();
	 			String brand = tf4.getText();
	 			float money;
	 			int employee = (ra1.isSelected() == true) ? 1 : 0;
	 				

	 				
	 			// 회원가입 예외 처리
	 			if(name.length() == 0) {					// 예외 처리 이름
	 				JOptionPane.showMessageDialog(null
	 					, "이름을 입력해주세요."
	 					, "박리다매"
	 					, JOptionPane.ERROR_MESSAGE
	 				);
	 			} else if(name.length() > 12) {
	 				JOptionPane.showMessageDialog(null
	 					, "이름이 너무 깁니다. 12자 이내로 입력해 주세요."
	 					, "박리다매"
	 					, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString2(name)) {           
 					JOptionPane.showMessageDialog(null
	 		            , "이름에 특수문자 또는 공백을 포함하고 있습니다.\n해당 문자를 제외하고 다시 입력해 주세요."
	 		            , "박리다매"
	 		            , JOptionPane.ERROR_MESSAGE
	                );
 	            } else if(id.length() == 0) {				// 예외 처리 아이디
 					JOptionPane.showMessageDialog(null
 						, "아이디를 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 					idcheck = false;
 				} else if(id.length() < 5) {
 					JOptionPane.showMessageDialog(null
						, "아이디가 너무 짧습니다. 5~12자 이내로 입력해 주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(id.length() > 12) {
 					JOptionPane.showMessageDialog(null
						, "아이디가 너무 깁니다. 5~12자 이내로 입력해 주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(!is.isString1(id)) {           
 					JOptionPane.showMessageDialog(null
                        , "아이디는 영숫자를 사용하여 입력해 주세요."
                        , "박리다매"
                       	, JOptionPane.ERROR_MESSAGE
					);
	               	idcheck = false;
 	            } else if(!idcheck) {
 	            	JOptionPane.showMessageDialog(null
						, "아이디 중복 체크를 해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(pw.length() == 0) {				// 예외 처리 비밀번호
 					JOptionPane.showMessageDialog(null
						, "비밀번호를 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(pw.length() < 8) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 짧습니다. 8~16자 이내로 입력해 주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(pw.length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "비밀번호가 너무 깁니다. 8~16자 이내로 입력해 주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString1(pw)) {         
 					JOptionPane.showMessageDialog(null
 						, "비밀번호는 영숫자를 사용하여 입력해 주세요."
 						, "박리다매"
 						, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(brand.length() == 0) {			// 예외 처리 브렌드
 					JOptionPane.showMessageDialog(null
						, "브랜드를 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString2(brand)) {           
 					JOptionPane.showMessageDialog(null
                        , "지점명에 특수문자를 제외하여 입력해 주세요."
                        , "박리다매"
                       	, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(brand.length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "지점명이 너무 깁니다. 16자 이내로 입력해 주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if((tf5.getText()).length() == 0) {	// 예외 처리 매출대비지급액
 					JOptionPane.showMessageDialog(null
						, "매출대비지급액을 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isNum(tf5.getText())) {
 					JOptionPane.showMessageDialog(null
						, "매출대비지급액은 숫자만 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(employee == 1 && (tf7.getText()).length() == 0) {		// 예외 처리 직원 월급
					JOptionPane.showMessageDialog(null
						, "직원 월급을 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
					);
 				} else if(employee == 1 && !is.isNum(tf7.getText())) {
 					JOptionPane.showMessageDialog(null
						, "직원 월급은 숫자만 입력해주세요."
						, "박리다매"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else {
	 				money = Float.parseFloat(tf5.getText());
	 				if(employee == 1)employee = Integer.parseInt(tf7.getText());
	 		
	 				System.out.println("회원가입 성공!!");
	 				System.out.println("이름 : " + name);
	 				System.out.println("아이디 : " + id);
	 				System.out.println("비밀번호 : " + pw);
	 				System.out.println("지점명 : " + brand);
	 				if(employee != 0) {
	 					System.out.println("직원 월급 : " + employee);
	 					System.out.println("매출대비지급액(%) : " + money);
	 				}
	 				
	 				JOptionPane.showMessageDialog(null
	 						, "회원가입을 축하합니다!"
	 						, "박리다매"
	 						, JOptionPane.PLAIN_MESSAGE
	 				);
	 				new Start();
	 		        mainFrame.setVisible(false);
	 			}
	 		}
	 	});
 		
	    // 취소 버튼 클릭 이벤트
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // 프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });
	    
	    p.add(form);
	    p.add(btns);
	      
	    subpanel = new JPanel();
	    subpanel.setBackground(background);
	    subpanel.setLayout(new BorderLayout(0, 100));
	    subpanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));	// 위 왼 아 오
	    subpanel.add(headerLabel, BorderLayout.NORTH);
	    subpanel.add(p);
	      
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
}