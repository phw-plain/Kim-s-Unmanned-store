package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Manage {
	private Frame mainFrame;
	private JPanel subpanel;
	private JPanel panel0;
	private JPanel panel1;
	
	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(255, 255, 255);
	
	// 기본 정보
	int width;
	int height;
	
	// Image
	ImageIcon i = new ImageIcon("src/img/benner.png");
	Image im=i.getImage();
	
	public Manage() {
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게"); 
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
	    
		// 화면 크기
		width = mainFrame.getWidth();
		height = mainFrame.getWidth();
		
		// subpanel 세팅
		subpanel = new JPanel();
		subpanel.setBackground(background);
		subpanel.setLayout(new GridLayout(3, 1));
		
		// 상단 배너
		MyPanel benner = new MyPanel();
		subpanel.add(benner, BorderLayout.EAST);
		
		// 메뉴 화면 생성
		menu();
		subpanel.add(panel0, BorderLayout.EAST);
		subpanel.add(panel1, BorderLayout.EAST);
		
		mainFrame.add(subpanel);
	}
	
	public void menu() {
		panel0 = new JPanel();
		panel1 = new JPanel();
		panel0.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
	    
		panel0.setBackground(background);  
		panel1.setBackground(background);  
		
		ImageIcon mBtn_img1 = new ImageIcon("src/img/btn1_1.png");
		ImageIcon mBtn_img2 = new ImageIcon("src/img/btn1_2.png");
		ImageIcon mBtn_img3 = new ImageIcon("src/img/btn1_3.png");
		
		ImageIcon mBtn2_img1 = new ImageIcon("src/img/btn2_1.png");
		
		// btn1 : 
		JButton btn1 = new JButton("", mBtn2_img1);
		btn1.setRolloverIcon(mBtn_img2); 	// rolloverIcon용 이미지 등록
		btn1.setPressedIcon(mBtn_img3); 	// pressedIcon용 이미지 등록
		btn1.setContentAreaFilled(true);	// 배경 채우기
		btn1.setBackground(Color.gray);
		btn1.setBorderPainted(false);		// 외각선
		btn1.setFocusPainted(false);		// 선택 외각선
		
		// btn2 : 
		JButton btn2 = new JButton("", mBtn2_img1);
		btn2.setRolloverIcon(mBtn_img2); 
		btn2.setPressedIcon(mBtn_img3); 
		btn2.setContentAreaFilled(true);	
		btn2.setBackground(Color.gray);
		btn2.setBorderPainted(false);
		btn2.setFocusPainted(false);
		
		// btn3 : 
		JButton btn3 = new JButton("", mBtn2_img1);
		btn3.setRolloverIcon(mBtn_img2); 
		btn3.setPressedIcon(mBtn_img3);
		btn3.setContentAreaFilled(true);	
		btn3.setBackground(Color.gray);
		btn3.setBorderPainted(false);
		btn3.setFocusPainted(false);
		
		// btn4 : 
		JButton btn4 = new JButton("", mBtn_img1);
		btn4.setRolloverIcon(mBtn_img2); 
		btn4.setPressedIcon(mBtn_img3);
		btn4.setContentAreaFilled(false);	
		btn4.setBorderPainted(false);
		btn4.setFocusPainted(false);
		
		// btn5 : 
		JButton btn5 = new JButton("", mBtn_img1);
		btn5.setRolloverIcon(mBtn_img2); 
		btn5.setPressedIcon(mBtn_img3); 
		btn5.setContentAreaFilled(false);	
		btn5.setBorderPainted(false);
		btn5.setFocusPainted(false);
		
		// btn6 : 
		JButton btn6 = new JButton("", mBtn_img1);
		btn6.setRolloverIcon(mBtn_img2);
		btn6.setPressedIcon(mBtn_img3); 
		btn6.setContentAreaFilled(false);	
		btn6.setBorderPainted(false);
		btn6.setFocusPainted(false);
		
		panel0.add(btn1);
		panel0.add(btn2);
		panel0.add(btn3);
		panel1.add(btn4);
		panel1.add(btn5);
		panel1.add(btn6);
	    
	}
	
	class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}