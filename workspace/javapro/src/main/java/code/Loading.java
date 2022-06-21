package code;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Loading extends Setting {
	public JPanel panel;
	private Image image;

	public Loading() {
		panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(245,245,245));
		
		Icon icon = new ImageIcon("src/img/load.gif");
        panel.add(new JLabel(icon), BorderLayout.CENTER);
		
        JPanel subpanel = new JPanel(new BorderLayout());
		subpanel.setBackground(maincolor);
        
		JPanel text = new JPanel(new BorderLayout());
		text.setBackground(new Color(245,245,245));
		text.setBackground(maincolor);
		text.setBorder(BorderFactory.createEmptyBorder(50, 100, 100, 0));
		JLabel title = new JLabel("Loading...");
		title.setForeground(Color.white);
		JLabel subtitle = new JLabel("잠시만 기다려주세요.");
		subtitle.setForeground(Color.white);
		
		JButton Logo = new JButton("", logo);
		Logo.setContentAreaFilled(false); 
		Logo.setBorderPainted(false); 		
		Logo.setFocusPainted(false); 	
        
		text.add(title, BorderLayout.CENTER);
		text.add(subtitle, BorderLayout.SOUTH);

		subpanel.add(text, BorderLayout.CENTER);
		subpanel.add(Logo, BorderLayout.EAST);
		
		panel.add(subpanel, BorderLayout.SOUTH);
        panel.setVisible(true);
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);	        
	}
}