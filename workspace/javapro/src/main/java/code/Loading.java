package code;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Loading extends Setting {
	public JPanel panel;
	private Image image;

	public Loading() {
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		
//		image = Toolkit.getDefaultToolkit().createImage("src/img/load.png"); 
//		
//		JPanel test = new JPanel(new BorderLayout()) {
//            public void paintComponent(Graphics g) {
//                g.drawImage(image, 0, 0, null);
//                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
//                super.paintComponent(g);
//            }
//        };
		
		// add the image label
		Icon icon = new ImageIcon("src/img/load.gif");
        try {
            panel.add(new JLabel(icon), BorderLayout.CENTER);
        } catch (Exception e) {
        }
        
		
		JPanel text = new JPanel(new BorderLayout());
		JLabel title = new JLabel("Loading...");
		JLabel subtitle = new JLabel("잠시만 기다려주세요.");
        
		text.add(title, BorderLayout.CENTER);
		text.add(subtitle, BorderLayout.SOUTH);

        //panel.add(imageLabel, java.awt.BorderLayout.CENTER);
		panel.add(text, BorderLayout.SOUTH);
        panel.setVisible(false);
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	

}
//
//class Loading {
//	Image image;
//	public JPanel panel = new JPanel;
//	
//	Loading() throws MalformedURLException {
//		image = Toolkit.getDefaultToolkit().createImage("src/img/load.gif");  
//	    
//	}
//	
//	public void paintComponent(Graphics g) {  
//		panel.paintComponent(g);  
//	    if (image != null) {  
//	      g.drawImage(image, 0, 0, this);  
//	    }  
//	  } 
//	
//	public void paintComponent(Graphics g) {
//        g.drawImage(i.getImage(), 0, 0, null);
//        setOpaque(false); //그림을 표시하게 설정,투명하게 조절
//        super.paintComponent(g);
//    }
//}