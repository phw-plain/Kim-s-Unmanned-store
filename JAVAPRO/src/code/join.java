package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class join {
	private Frame mainFrame;
	private JPanel subpanel;
	private JLabel headerLabel;
	private Panel form;
	private Button b1;
	
	// 색깔
	Color title = new Color(0, 0, 0);
	Color background = new Color(235, 222, 207);
	 
	public join() {
		prepareGUI();
	}
	
	private void prepareGUI() {
		// Font
		Font font1 = new Font("esamanru Bold", Font.PLAIN, 50);
		Font font2  = new Font("esamanru Light",Font.PLAIN, 16); 
		
		// Frame 에 대한 셋팅
		mainFrame = new Frame("DSMS");
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
		TextField tf1 = new TextField("", 15);
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
		TextField tf2 = new TextField("", 15);
		tf2.selectAll();
		form.add(blankL2);
		form.add(label2);
		form.add(tf2);
		form.add(btnlabel);
		
		JLabel blankL3 = new JLabel();
		JLabel blankR3 = new JLabel();
		JLabel label3 = new JLabel("비밀번호");
		label3.setFont(font2);
		TextField tf3 = new TextField("", 15);
		tf3.selectAll(); // tf2.setEchoChar('*');
		form.add(blankL3);
		form.add(label3);
		form.add(tf3);
		form.add(blankR3);

		JLabel blankL4 = new JLabel();
		JLabel blankR4 = new JLabel();
		JLabel label4 = new JLabel("지점명");
		label4.setFont(font2);
		TextField tf4 = new TextField("", 15);
		tf4.selectAll(); 
		form.add(blankL4);
		form.add(label4);
		form.add(tf4);
		form.add(blankR4);

		JLabel blankL5 = new JLabel();
		JLabel blankR5 = new JLabel();
		JLabel label5 = new JLabel("매출대비지급액(%)");
		label5.setFont(font2);
		TextField tf5 = new TextField("", 15);
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
		JRadioButton ra1 = new JRadioButton("유", true);
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
		JLabel label7 = new JLabel("직원 월급");
		label7.setFont(font2);
		TextField tf7 = new TextField("", 15);
		tf7.selectAll(); 
		form.add(blankL7);
		form.add(label7);
		form.add(tf7);
		form.add(blankR7);

		// 버튼 생성
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("확인");
		RoundedButton cancel = new RoundedButton("취소");
		
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // 버튼 클릭 이벤트
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
	    subpanel.setBorder(BorderFactory.createEmptyBorder(100 , 0, 0 , 0));	// 위 왼 아 오
	    subpanel.add(headerLabel, BorderLayout.NORTH);
	    subpanel.add(p);
	      
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
}