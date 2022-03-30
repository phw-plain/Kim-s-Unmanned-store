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
	 
	// ���̵� �ߺ� üũ�� ����
	private boolean idcheck = false;
	
	public join() {
		prepareGUI();
	}
	
	private void prepareGUI() {
		// Frame �� ���� ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���");
		mainFrame.setSize(1280 ,1024);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon ����
		
		// ��� ����
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setText("ȸ������");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		// ȸ������ ���� �Է�
		form = new Panel(new GridLayout(7,4,0,5));
		form.setPreferredSize(new Dimension(800,300));
		
		JLabel blankL1 = new JLabel();
		JLabel blankR1 = new JLabel();
		JLabel label1 = new JLabel("�̸�");
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
		JButton b1 = new JButton("�ߺ�Ȯ��");
		b1.setFont(font2);
		b1.setBackground(Color.white);
		btnlabel.add(b1, BorderLayout.WEST);
		JLabel label2 = new JLabel("���̵�");
		label2.setFont(font2);
		final TextField tf2 = new TextField("", 15);
		tf2.selectAll();
		form.add(blankL2);
		form.add(label2);
		form.add(tf2);
		form.add(btnlabel);
		
		JLabel blankL3 = new JLabel();
		JLabel blankR3 = new JLabel();
		JLabel label3 = new JLabel("��й�ȣ");
		label3.setFont(font2);
		final TextField tf3 = new TextField("", 15);
		tf3.selectAll(); // tf2.setEchoChar('*');
		form.add(blankL3);
		form.add(label3);
		form.add(tf3);
		form.add(blankR3);

		JLabel blankL4 = new JLabel();
		JLabel blankR4 = new JLabel();
		JLabel label4 = new JLabel("������");
		label4.setFont(font2);
		final TextField tf4 = new TextField("", 15);
		tf4.selectAll(); 
		form.add(blankL4);
		form.add(label4);
		form.add(tf4);
		form.add(blankR4);

		JLabel blankL5 = new JLabel();
		JLabel blankR5 = new JLabel();
		JLabel label5 = new JLabel("���������޾�(%)");
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
		JLabel label6 = new JLabel("����");
		label6.setFont(font2);
		CheckboxGroup g = new CheckboxGroup();
		final JRadioButton ra1 = new JRadioButton("��", true);
		ra1.setFont(font2);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("��", false);
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
		final JLabel label7 = new JLabel("���� ����");
		label7.setFont(font2);
		final TextField tf7 = new TextField("", 15);
		tf7.selectAll(); 
		form.add(blankL7);
		form.add(label7);
		form.add(tf7);
		form.add(blankR7);

		// ��ư ����
		JPanel btns = new JPanel(new FlowLayout());
		
		RoundedButton check = new RoundedButton("Ȯ��");
		RoundedButton cancel = new RoundedButton("���");
		
		// ��ư ����
		check.setFocusPainted(false); 
		check.setFont(font3);
		cancel.setFocusPainted(false); 
		cancel.setFont(font3);
		
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // ���̵� �ߺ� üũ
	    b1.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
		    	String id = tf2.getText();
		    	
		    	if(tf2.getText().length() == 0) {
		    		JOptionPane.showMessageDialog(null
							, "���̵� �Է����ּ���."
							, "DSMS"
							, JOptionPane.ERROR_MESSAGE
					);
	                idcheck = false;
				} else if(/*m.select(tf2.getText())==false*/false) {
					JOptionPane.showMessageDialog(null
							, "���Ұ����� ���̵� �Դϴ�."
		                    , "DSMS"
		                    , JOptionPane.ERROR_MESSAGE
		            );
		            idcheck = false;
	            } else if(!is.isString1(tf2.getText())) {            // ���� ó�� ���̵�
		            JOptionPane.showMessageDialog(null
		            		, "���Ұ����� ���̵� �Դϴ�."
		            		, "DSMS"
		                   	, JOptionPane.ERROR_MESSAGE
		           	);
		            idcheck = false;
		        } else {
					JOptionPane.showMessageDialog(null
							, "��밡���� ���̵� �Դϴ�."
							, "DSMS"
							, JOptionPane.PLAIN_MESSAGE
					);
					idcheck = true;
				}
		    }
		});
	    
	    // ���� ���� radio �̺�Ʈ
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
	    
	    // Ȯ�� ��ư Ŭ�� �̺�Ʈ
 		check.addActionListener(new ActionListener() {
	 		public void actionPerformed(ActionEvent e) {
	 			// ������ ���� ���� ����
	 			String name = tf1.getText();
	 			String id = tf2.getText();
	 			String pw = tf3.getText();
	 			String brand = tf4.getText();
	 			float money;
	 			int employee = (ra1.isSelected() == true) ? 1 : 0;
	 				

	 				
	 			// ȸ������ ���� ó��
	 			if(name.length() == 0) {					// ���� ó�� �̸�
	 				JOptionPane.showMessageDialog(null
	 					, "�̸��� �Է����ּ���."
	 					, "�ڸ��ٸ�"
	 					, JOptionPane.ERROR_MESSAGE
	 				);
	 			} else if(name.length() > 12) {
	 				JOptionPane.showMessageDialog(null
	 					, "�̸��� �ʹ� ��ϴ�. 12�� �̳��� �Է��� �ּ���."
	 					, "�ڸ��ٸ�"
	 					, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString2(name)) {           
 					JOptionPane.showMessageDialog(null
	 		            , "�̸��� Ư������ �Ǵ� ������ �����ϰ� �ֽ��ϴ�.\n�ش� ���ڸ� �����ϰ� �ٽ� �Է��� �ּ���."
	 		            , "�ڸ��ٸ�"
	 		            , JOptionPane.ERROR_MESSAGE
	                );
 	            } else if(id.length() == 0) {				// ���� ó�� ���̵�
 					JOptionPane.showMessageDialog(null
 						, "���̵� �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 					idcheck = false;
 				} else if(id.length() < 5) {
 					JOptionPane.showMessageDialog(null
						, "���̵� �ʹ� ª���ϴ�. 5~12�� �̳��� �Է��� �ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(id.length() > 12) {
 					JOptionPane.showMessageDialog(null
						, "���̵� �ʹ� ��ϴ�. 5~12�� �̳��� �Է��� �ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(!is.isString1(id)) {           
 					JOptionPane.showMessageDialog(null
                        , "���̵�� �����ڸ� ����Ͽ� �Է��� �ּ���."
                        , "�ڸ��ٸ�"
                       	, JOptionPane.ERROR_MESSAGE
					);
	               	idcheck = false;
 	            } else if(!idcheck) {
 	            	JOptionPane.showMessageDialog(null
						, "���̵� �ߺ� üũ�� ���ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 	                idcheck = false;
 				} else if(pw.length() == 0) {				// ���� ó�� ��й�ȣ
 					JOptionPane.showMessageDialog(null
						, "��й�ȣ�� �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(pw.length() < 8) {
 					JOptionPane.showMessageDialog(null
						, "��й�ȣ�� �ʹ� ª���ϴ�. 8~16�� �̳��� �Է��� �ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(pw.length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "��й�ȣ�� �ʹ� ��ϴ�. 8~16�� �̳��� �Է��� �ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString1(pw)) {         
 					JOptionPane.showMessageDialog(null
 						, "��й�ȣ�� �����ڸ� ����Ͽ� �Է��� �ּ���."
 						, "�ڸ��ٸ�"
 						, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(brand.length() == 0) {			// ���� ó�� �귻��
 					JOptionPane.showMessageDialog(null
						, "�귣�带 �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isString2(brand)) {           
 					JOptionPane.showMessageDialog(null
                        , "������ Ư�����ڸ� �����Ͽ� �Է��� �ּ���."
                        , "�ڸ��ٸ�"
                       	, JOptionPane.ERROR_MESSAGE
					);
 	            } else if(brand.length() > 16) {
 					JOptionPane.showMessageDialog(null
						, "�������� �ʹ� ��ϴ�. 16�� �̳��� �Է��� �ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if((tf5.getText()).length() == 0) {	// ���� ó�� ���������޾�
 					JOptionPane.showMessageDialog(null
						, "���������޾��� �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(!is.isNum(tf5.getText())) {
 					JOptionPane.showMessageDialog(null
						, "���������޾��� ���ڸ� �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
 				} else if(employee == 1 && (tf7.getText()).length() == 0) {		// ���� ó�� ���� ����
					JOptionPane.showMessageDialog(null
						, "���� ������ �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
					);
 				} else if(employee == 1 && !is.isNum(tf7.getText())) {
 					JOptionPane.showMessageDialog(null
						, "���� ������ ���ڸ� �Է����ּ���."
						, "�ڸ��ٸ�"
						, JOptionPane.ERROR_MESSAGE
 					);
	 			} else {
	 				money = Float.parseFloat(tf5.getText());
	 				if(employee == 1)employee = Integer.parseInt(tf7.getText());
	 		
	 				System.out.println("ȸ������ ����!!");
	 				System.out.println("�̸� : " + name);
	 				System.out.println("���̵� : " + id);
	 				System.out.println("��й�ȣ : " + pw);
	 				System.out.println("������ : " + brand);
	 				if(employee != 0) {
	 					System.out.println("���� ���� : " + employee);
	 					System.out.println("���������޾�(%) : " + money);
	 				}
	 				
	 				JOptionPane.showMessageDialog(null
	 						, "ȸ�������� �����մϴ�!"
	 						, "�ڸ��ٸ�"
	 						, JOptionPane.PLAIN_MESSAGE
	 				);
	 				new Start();
	 		        mainFrame.setVisible(false);
	 			}
	 		}
	 	});
 		
	    // ��� ��ư Ŭ�� �̺�Ʈ
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new Start(); // ������ ��ȯ
	           mainFrame.setVisible(false);
	       }
	    });
	    
	    p.add(form);
	    p.add(btns);
	      
	    subpanel = new JPanel();
	    subpanel.setBackground(background);
	    subpanel.setLayout(new BorderLayout(0, 100));
	    subpanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));	// �� �� �� ��
	    subpanel.add(headerLabel, BorderLayout.NORTH);
	    subpanel.add(p);
	      
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
}