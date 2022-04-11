package code;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;

public class SetWindow extends Setting {
	private Frame mainFrame;
	private JPanel panel;

	private int[][] resoArr = new int[][] {
		{ 1280, 1024 }
		, { 1024, 786 }
	};
	private Vector<String> fontArr = new Vector<String>();
	
	public SetWindow() {
		// Frame 기본 세팅
		mainFrame = new Frame("박리다매 무인가게"); 
	    mainFrame.setSize(450, 400);
		mainFrame.setLocationRelativeTo(null);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	    mainFrame.addWindowListener(new WindowAdapter() {
	       public void windowClosing(WindowEvent windowEvent) {
	    	   mainFrame.dispose();
	       }
	    });

		URL imageURL = Start.class.getClassLoader().getResource("apple.png");
	    ImageIcon img = new ImageIcon(imageURL);
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경
	    
    	panel = new JPanel(new BorderLayout());
    	
    	JLabel title = new JLabel("화면 설정");
    	title.setFont(font4);
    	
    	JPanel center = new JPanel(new GridLayout(4, 1, 0 ,0));
    	center.setBackground(background);

    	// 해상도
    	JPanel resolution = new JPanel();
    	JPanel reso_sub = new JPanel(new GridLayout(1,2,0,0));
    	JLabel reso_title =  new JLabel("해상도");
    	Choice reso = new Choice();
    	
    	for(int i=0; i<resoArr.length; i++) {
    		reso.add(resoArr[i][0]+"X"+resoArr[i][1]);
    	}
    	reso_sub.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 200));
    	
    	reso_sub.add(reso_title);
    	reso_sub.add(reso);
    	resolution.add(reso_sub);

    	// 글꼴
    	JPanel font = new JPanel();
    	JPanel font_sub = new JPanel(new GridLayout(1,2,0,0));
    	JLabel font_title =  new JLabel("글꼴");
    	Choice fonts = new Choice();

    	fontArr.add("배달의민족 주아");
    	fontArr.add("돋움체");
    	
    	for(int i=0; i<fontArr.size(); i++) {
    		fonts.add(fontArr.get(i));
    	}
    	font_sub.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 180));
    	

    	font_sub.add(font_title);
    	font_sub.add(fonts);
    	font.add(font_sub);
    	
    	// 버튼
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("확인");
		check.setFocusPainted(false); 
	    check.setFont(font3);
		RoundedButton cancel = new RoundedButton("취소");
	    cancel.setFocusPainted(false); 
	    cancel.setFont(font3);

	    btns.add(check);
	    btns.add(cancel);
	    
	    // 버튼 이벤트
	    check.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		width = resoArr[reso.getSelectedIndex()][0];
	    		height = resoArr[reso.getSelectedIndex()][1];
	    		Setting.font = fontArr.get(fonts.getSelectedIndex());
				startFrame.dispose();
				new Start();
	    		mainFrame.dispose();
			}
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   	mainFrame.dispose();
	       }
	    });
	    
    	center.add(reso_sub);
    	center.add(font);
    	center.add(btns);
    	
    	panel.add(title, BorderLayout.NORTH);
    	panel.add(center, BorderLayout.CENTER);
    	
    	mainFrame.add(panel);
	}
	
}