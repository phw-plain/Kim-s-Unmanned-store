package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

class login2 {
	private Frame mainFrame;
	private JPanel subpanel;
	private MyPanel panelLeft;
	private JPanel panelRight;
	private JLabel headerLabel;
	private Button b1;
	
	// �⺻ ����
	int width;
	int height;
	
	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(255, 255, 255);

	// Font
	Font font1 = new Font("����ü", Font.PLAIN, 50);
	Font font2 = new Font("����ü", Font.PLAIN, 16);
	
	// Image
    ImageIcon i = new ImageIcon("src/img/login_img.png");
    Image im=i.getImage();
	
	public login2() {
	        prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame �⺻ ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���");
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
	    
	    // Icon ����
	    URL imageURL = Start.class.getClassLoader().getResource("apple.png");
    	ImageIcon img = new ImageIcon(imageURL);
    	mainFrame.setIconImage(img.getImage());
		
		// ȭ�� ũ��
		width = mainFrame.getWidth();
		height = mainFrame.getHeight();

		// Left image
		panelLeft = new MyPanel();
	    
		// Left title
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setVerticalAlignment(JLabel.EAST);
		headerLabel.setText("�α���");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		// Right content
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
	     
		panelRight = new JPanel();
		panelRight.setBackground(background);
		panelRight.setLayout(new GridLayout(3, 1));
		panelRight.add(headerLabel);
		panelRight.add(p);
		
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(1, 2));
		subpanel.add(panelLeft, BorderLayout.SOUTH);
		subpanel.add(panelRight, BorderLayout.NORTH);
	     
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}