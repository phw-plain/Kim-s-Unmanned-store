package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class login {
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Button b1;
	
	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(235, 222, 207);

	// Font
	Font font1 = new Font("돋움체", Font.PLAIN, 50);
	Font font2 = new Font("돋움체", Font.PLAIN, 16);
	
	public login() {
	        prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame 기본 셋팅
		mainFrame = new Frame("박리다매 무인가게");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		// 상단 제목
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("로그인");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		Panel form = new Panel(new GridLayout(3,2,0,5));
		
		JLabel label1 = new JLabel("아이디");
		label1.setFont(font2);
		TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("비밀번호");
		label2.setFont(font2);
		TextField tf2 = new TextField("", 15);
		tf2.setEchoChar('●');
		form.add(label2);
		form.add(tf2);

		// 투명 레이아웃
		JLabel blank = new JLabel();      
		JLabel blank2 = new JLabel();   
		JLabel blank3 = new JLabel();
		  
		form.add(blank2);
		form.add(blank3);

		// 버튼 생성
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("확인");
		RoundedButton cancel = new RoundedButton("취소");
	      
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // 버튼 클릭 이벤트
	    check.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   // 데이터 저장 변수 선언
		    	   String input_id = tf1.getText();
		    	   String input_pw = tf2.getText();
					
		    	   // 데이터 불러오기
		    	   String id = "admin";
		    	   String pw = "admin1234";
		    	   
		    	   if(input_id.length() == 0) {
		    		   JOptionPane.showMessageDialog(null
		    				   , "아이디를 입력해주세요."
		    				   , "박리다매 무인가게"
		    				   , JOptionPane.ERROR_MESSAGE
		    				   );
					} else if(input_pw.length() == 0) {
						JOptionPane.showMessageDialog(null
								, "비밀번호를 입력해주세요."
								, "박리다매 무인가게"
								, JOptionPane.ERROR_MESSAGE
								);
					} else if(id == null|| pw == null) {
						JOptionPane.showMessageDialog(null
								, "아이디 또는 비밀번호 입력 오류. 다시 한번 확인 해주세요."
								, "박리다매 무인가게"
								, JOptionPane.ERROR_MESSAGE
								);
					} else {
						JOptionPane.showMessageDialog(null
								, "환영합니다!"
								, "박리다매 무인가게"
								, JOptionPane.PLAIN_MESSAGE
								);
						new Manage();
						mainFrame.setVisible(false);
					}
		       }
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // 프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });
	
	    p.add(form);
	    p.add(btns);
	    p.setBackground(background);
	      
	    JPanel test = new JPanel(new GridLayout(3,1,0,0));
	    test.setBackground(background);
	    test.add(blank);
	    test.add(headerLabel);
	    test.add(p);
	      
	    subpanel = new JPanel();
	    subpanel.setBackground(background);
	    subpanel.setLayout(new GridLayout(2, 1));
	    subpanel.add(test, BorderLayout.NORTH);
	     
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
}