package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Manage {
	private Frame mainFrame;
	private JPanel panel0;
	
	// color
	Color title = new Color(0, 0, 0);
	Color background = new Color(235, 222, 207);
	
	// 기본 정보
	int width;
	int height;
	
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
		
		// 메뉴 화면 생성
		menu();
		
		mainFrame.add(panel0);
	}
	
	public void menu() {
		panel0 = new JPanel();
	    
		panel0.setBackground(background);  
	    
	    panel0.setVisible(true);
	}
}