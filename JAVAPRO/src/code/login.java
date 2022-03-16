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
	Font font1 = new Font("����ü", Font.PLAIN, 50);
	Font font2 = new Font("����ü", Font.PLAIN, 16);
	
	public login() {
	        prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame �⺻ ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		// ��� ����
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("�α���");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		Panel form = new Panel(new GridLayout(3,2,0,5));
		
		JLabel label1 = new JLabel("���̵�");
		label1.setFont(font2);
		TextField tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("��й�ȣ");
		label2.setFont(font2);
		TextField tf2 = new TextField("", 15);
		tf2.setEchoChar('��');
		form.add(label2);
		form.add(tf2);

		// ���� ���̾ƿ�
		JLabel blank = new JLabel();      
		JLabel blank2 = new JLabel();   
		JLabel blank3 = new JLabel();
		  
		form.add(blank2);
		form.add(blank3);

		// ��ư ����
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("Ȯ��");
		RoundedButton cancel = new RoundedButton("���");
	      
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // ��ư Ŭ�� �̺�Ʈ
	    check.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		    	   // ������ ���� ���� ����
		    	   String input_id = tf1.getText();
		    	   String input_pw = tf2.getText();
					
		    	   // ������ �ҷ�����
		    	   String id = "admin";
		    	   String pw = "admin1234";
		    	   
		    	   if(input_id.length() == 0) {
		    		   JOptionPane.showMessageDialog(null
		    				   , "���̵� �Է����ּ���."
		    				   , "�ڸ��ٸ� ���ΰ���"
		    				   , JOptionPane.ERROR_MESSAGE
		    				   );
					} else if(input_pw.length() == 0) {
						JOptionPane.showMessageDialog(null
								, "��й�ȣ�� �Է����ּ���."
								, "�ڸ��ٸ� ���ΰ���"
								, JOptionPane.ERROR_MESSAGE
								);
					} else if(id == null|| pw == null) {
						JOptionPane.showMessageDialog(null
								, "���̵� �Ǵ� ��й�ȣ �Է� ����. �ٽ� �ѹ� Ȯ�� ���ּ���."
								, "�ڸ��ٸ� ���ΰ���"
								, JOptionPane.ERROR_MESSAGE
								);
					} else {
						JOptionPane.showMessageDialog(null
								, "ȯ���մϴ�!"
								, "�ڸ��ٸ� ���ΰ���"
								, JOptionPane.PLAIN_MESSAGE
								);
						new Manage();
						mainFrame.setVisible(false);
					}
		       }
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // ������ ��ȯ
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