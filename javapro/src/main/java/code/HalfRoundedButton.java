package code;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class HalfRoundedButton extends JButton { 

	// color
	Color btn = new Color(120, 120, 120);
	
	public HalfRoundedButton() { 
		super();
		decorate();
	} 
	
	public HalfRoundedButton(String text) { 
		super(text); 
		decorate();
	} 
	
	public HalfRoundedButton(Action action) { 
		super(action); 
		decorate();
	} 
	
	public HalfRoundedButton(Icon icon) { 
		super(icon); 
		decorate();
	}
	
	public HalfRoundedButton(String text, Icon icon) { 
		super(text, icon); 
		decorate();
	} 
	
	protected void decorate() { 
		setBorderPainted(false); 
		setOpaque(false); 
	}
	
	@Override 
	protected void paintComponent(Graphics g) { 
		int width = getWidth(); 
		int height = getHeight(); 
		
		Graphics2D graphics = (Graphics2D) g; 
		
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		if (getModel().isArmed()) { 
			graphics.setColor(btn.darker()); 
		} else if (getModel().isRollover()) {
			graphics.setColor(btn.brighter()); 
		} else { 
			graphics.setColor(btn); 
		} 
		
		graphics.fillRoundRect(-10, 0, width+3, height, 10, 10); 
		
		FontMetrics fontMetrics = graphics.getFontMetrics(); 
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		
		int textX = (width - stringBounds.width) / 2; 
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		
		graphics.setColor(Color.white); 
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY); 
		graphics.dispose(); 
		
		super.paintComponent(g); 
	}

}


