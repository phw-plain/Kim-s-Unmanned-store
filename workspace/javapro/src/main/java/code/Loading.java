package code;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Loading extends Setting {
	public JPanel panel;
	private Image image;

	public Loading() {
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		
		Icon icon = new ImageIcon("src/img/load.gif");
        panel.add(new JLabel(icon), BorderLayout.CENTER);
     
		
		JPanel text = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Loading...");
		JLabel subtitle = new JLabel("잠시만 기다려주세요.");
        
		text.add(title, BorderLayout.CENTER);
		text.add(subtitle, BorderLayout.SOUTH);
		
		
		panel.add(text, BorderLayout.SOUTH);
        panel.setVisible(true);
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);	        
	}
}