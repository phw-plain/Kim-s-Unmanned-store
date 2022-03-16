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
	
	// �⺻ ����
	int width;
	int height;
	
	public Manage() {
		// Frame �⺻ ����
		mainFrame = new Frame("�ڸ��ٸ� ���ΰ���"); 
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
	    mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	          System.exit(0);
	       }
	    });
	    
		// ȭ�� ũ��
		width = mainFrame.getWidth();
		height = mainFrame.getWidth();
		
		// �޴� ȭ�� ����
		menu();
		
		mainFrame.add(panel0);
	}
	
	public void menu() {
		panel0 = new JPanel();
	    
		panel0.setBackground(background);  
	    
	    panel0.setVisible(true);
	}
}