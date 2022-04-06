package code;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

public class Start{
	private Frame mainFrame;
	private JPanel controlPanel;
	
    ImageIcon i = new ImageIcon("src/img/main.png");
    Image im=i.getImage();
    
    // Font
    // Font font1 = new Font("esamanru Bold", Font.PLAIN, 50);
    // Font font2 = new Font("esamanru Medium", Font.PLAIN, 25);
	Font font3 = new Font("배달의민족 주아", Font.PLAIN, 30);
    
	public Start(){
		prepareGUI();
    }
	
	private void prepareGUI() {
		// 작업 경로 확인용
		// String path = System.getProperty("user.dir"); 
		// System.out.println("현재 작업 경로: " + path);
        
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게"); 
	    mainFrame.setSize(1280 ,1024);
		mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
	    
	    // Icon 변경
	    URL imageURL = Start.class.getClassLoader().getResource("apple.png");
    	ImageIcon img = new ImageIcon(imageURL);
    	mainFrame.setIconImage(img.getImage());
	    
		// 화면 크기
		int width = mainFrame.getWidth();
		int height = mainFrame.getHeight();
	    
		//System.out.println(width + " " + height);
		
	    MyPanel panel = new MyPanel();
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, width+500, 10));
	   
	    // 버튼 생성
	    RoundedButton loginbtn = new RoundedButton("  로그인  ");
	    RoundedButton joinbtn = new RoundedButton("회원가입");
	    
	    // 버튼 설정
	    loginbtn.setFocusPainted(false); 
	    loginbtn.setFont(font3);
	    joinbtn.setFocusPainted(false); 
	    joinbtn.setFont(font3);
	    
	    // 버튼 클릭 이벤트
	    loginbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new login(); //프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });
	      
	    joinbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new join(); //프레임 전환
	           mainFrame.setVisible(false);
	       }
	    });

	    panel.add(loginbtn);
	    panel.add(joinbtn);
	    panel.setBorder(BorderFactory.createEmptyBorder((int)(height/1.8), 0, 0, 0));
	    
	    mainFrame.add(panel);
	    mainFrame.setVisible(true);
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}