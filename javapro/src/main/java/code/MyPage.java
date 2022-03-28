package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPage extends Setting{
	public JPanel panel;
	
	private JPanel Read;
	private JPanel Write;

	public JButton homebtn1;
	public JButton homebtn2;

	public MyPage() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		Read();
		Write();
	}
	
	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void Read() {
		// Today ¼¼ÆÃ
		Read = new JPanel();
		Read.setBackground(background);
		Read.setLayout(new BorderLayout());
	}
	
	private void Write() {
		
	}
}