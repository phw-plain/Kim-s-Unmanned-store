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
	Font font3 = new Font("����ǹ��� �־�", Font.PLAIN, 30);
    
	public Start(){
		prepareGUI();
    }
	
	private void prepareGUI() {
		// �۾� ��� Ȯ�ο�
		// String path = System.getProperty("user.dir"); 
		// System.out.println("���� �۾� ���: " + path);
        
		// Frame �⺻ ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���"); 
	    mainFrame.setSize(1280 ,1024);
		mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
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
		int width = mainFrame.getWidth();
		int height = mainFrame.getHeight();
	    
		//System.out.println(width + " " + height);
		
	    MyPanel panel = new MyPanel();
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, width+500, 10));
	   
	    // ��ư ����
	    RoundedButton loginbtn = new RoundedButton("  �α���  ");
	    RoundedButton joinbtn = new RoundedButton("ȸ������");
	    
	    // ��ư ����
	    loginbtn.setFocusPainted(false); 
	    loginbtn.setFont(font3);
	    joinbtn.setFocusPainted(false); 
	    joinbtn.setFont(font3);
	    
	    // ��ư Ŭ�� �̺�Ʈ
	    loginbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new login(); //������ ��ȯ
	           mainFrame.setVisible(false);
	       }
	    });
	      
	    joinbtn.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	           new join(); //������ ��ȯ
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