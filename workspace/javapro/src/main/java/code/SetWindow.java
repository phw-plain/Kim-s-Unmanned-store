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
    	center.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

    	// 해상도
    	JPanel resolution = new JPanel();
    	resolution.setBackground(background);
    	JPanel reso_sub = new JPanel(new GridLayout(1,2,58,0));
    	reso_sub.setBackground(background);
    	JLabel reso_title =  new JLabel("해상도");
    	final Choice reso = new Choice();
    	
    	for(int i=0; i<resoArr.length; i++) {
    		reso.add(resoArr[i][0]+"X"+resoArr[i][1]);
    	}
    	
    	reso_sub.add(reso_title);
    	reso_sub.add(reso);
    	resolution.add(reso_sub);

    	// 글꼴
    	JPanel font = new JPanel();
    	font.setBackground(background);
    	JPanel font_sub = new JPanel(new GridLayout(1,2,0,0));
    	font_sub.setBackground(background);
    	JLabel font_title =  new JLabel("글꼴");
    	final Choice fonts = new Choice();
    	
    	addFont();
    	
    	for(int i=0; i<fontArr.size(); i++) {
    		fonts.add(fontArr.get(i));
    	}

    	font_sub.add(font_title);
    	font_sub.add(fonts);
    	font.add(font_sub);
    	
    	// 테마
    	JPanel theme = new JPanel();
    	theme.setBackground(background);
    	JPanel theme_sub = new JPanel(new GridLayout(1,2,0,0));
    	theme_sub.setBackground(background);
    	JLabel theme_title =  new JLabel("테마");
		JPanel themes = new JPanel();
    	
    	ButtonGroup group = new ButtonGroup();
		final JRadioButton ra1 = new JRadioButton("light", true);
		ra1.setBackground(background);
		JRadioButton ra2 = new JRadioButton("dark", false);
		ra2.setBackground(background);
		
		group.add(ra1);
		group.add(ra2);

		themes.add(ra1);
		themes.add(ra2);
    	
    	theme_sub.add(theme_title);
    	theme_sub.add(themes);
    	theme.add(theme_sub);
    	
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
	    		System.out.println(fontArr.get(fonts.getSelectedIndex()));
	    		if(ra1.isSelected() == true) {
	    			lightMode();
	    		} else {
	    			darkMode();
	    		}
	    		
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
	    
    	center.add(resolution);
    	center.add(font);
    	center.add(theme);
    	center.add(btns);
    	
    	panel.add(title, BorderLayout.NORTH);
    	panel.add(center, BorderLayout.CENTER);
    	
    	mainFrame.add(panel);
	}

	private void addFont() {
		// font 추가
    	fontArr.add("굴림");
    	fontArr.add("굴림체");
    	fontArr.add("궁서");
    	fontArr.add("궁서체");
    	fontArr.add("돋움");
    	fontArr.add("돋움체");
    	fontArr.add("맑은 고딕");
    	fontArr.add("바탕");
    	fontArr.add("바탕체");
    	fontArr.add("새굴림");
    	fontArr.add("한컴 고딕");
    	fontArr.add("한컴산뜻돋움");
    	fontArr.add("함초롬돋움");
    	fontArr.add("함초롬돋움 확장");
    	fontArr.add("함초롬바탕");
    	fontArr.add("함초롬바탕 확장");
    	fontArr.add("함초롬바탕 확장B");
    	fontArr.add("휴먼둥근헤드라인");
    	fontArr.add("휴먼매직체");
    	fontArr.add("휴먼모음T");
    	fontArr.add("휴먼아미체");
    	fontArr.add("휴먼엑스포");
    	fontArr.add("휴먼옛체");
    	fontArr.add("휴먼편지체");
	}
	
}