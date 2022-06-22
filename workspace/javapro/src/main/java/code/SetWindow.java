package code;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.*;

import javapro.JsonFileEdit;

public class SetWindow extends Setting {
	private Frame mainFrame;
	private JPanel panel;

	private Vector<Integer> resoX = new Vector<Integer>();
	private Vector<Integer> resoY = new Vector<Integer>();
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

		URL imageURL = Start.class.getClassLoader().getResource("icon.png");
	    ImageIcon img = new ImageIcon(imageURL);
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경
	    
    	panel = new JPanel(new BorderLayout());
    	panel.setBackground(Color.white);
    	
    	JLabel title = new JLabel("화면 설정");
    	title.setFont(font4);
    	title.setHorizontalAlignment(JLabel.CENTER);
    	title.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
    	
    	JPanel center = new JPanel(new GridLayout(3, 2, 0, 50));
    	center.setBackground(Color.white);
    	center.setBorder(BorderFactory.createEmptyBorder(40, 90, 25, 90));

    	// 해상도
    	JPanel resolution = new JPanel();
    	resolution.setBackground(Color.white);
    	JLabel reso_title =  new JLabel("해상도");

    	resoX.add(1600);
    	resoY.add(1200);
    	
    	resoX.add(1280);
    	resoY.add(1024);
    	
    	resoX.add(1024);
    	resoY.add(786);
    	
    	Vector<String> r = new Vector<String>();
    	
    	for(int i=0; i<resoX.size(); i++) {
    		if(resoX.get(i) == width && resoY.get(i) == height) {
        		r.add(0, resoX.get(i)+"X"+resoY.get(i));
        		
        		int tempx = resoX.get(i);
        		int tempy = resoY.get(i);
        		
        		resoX.remove(i);
        		resoY.remove(i);
        		
        		resoX.add(0, tempx);
        		resoY.add(0, tempy);
    		} else {
        		r.add(resoX.get(i)+"X"+resoY.get(i));
    		}
    	}
    	
    	final JComboBox reso = new JComboBox(r);
    	
    	center.add(reso_title);
    	center.add(reso);

    	// 글꼴
    	JPanel fontPanel = new JPanel();
    	fontPanel.setBackground(Color.white);
    	JLabel font_title =  new JLabel("글꼴");
    	
    	Vector<String> f = new Vector<String>();
    	addFont();

    	final JComboBox fonts = new JComboBox();
    	
    	for(int i=0; i<fontArr.size(); i++) {
    		fonts.addItem(fontArr.get(i));
    	}
    	
    	center.add(font_title);
    	center.add(fonts);
    	
    	// 테마
    	JPanel theme = new JPanel();
    	theme.setBackground(Color.white);
    	JLabel theme_title =  new JLabel("테마");
		JPanel themes = new JPanel();
    	themes.setBackground(Color.white);
    	
    	ButtonGroup group = new ButtonGroup();
		final JRadioButton ra1 = new JRadioButton("light", false);
		ra1.setBackground(Color.white);
		JRadioButton ra2 = new JRadioButton("dark", false);
		ra2.setBackground(Color.white);
		
		if(Setting.theme.equals("light")) {
			ra1.setSelected(true);
		} else {
			ra2.setSelected(true);
		}
		
		group.add(ra1);
		group.add(ra2);

		themes.add(ra1);
		themes.add(ra2);
    	
    	center.add(theme_title);
    	center.add(themes);
    	
    	// 버튼
		JPanel btns = new JPanel(new FlowLayout());
		btns.setBackground(Color.white);
		btns.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));

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
	    		width = resoX.get(reso.getSelectedIndex());
	    		height =  resoY.get(reso.getSelectedIndex());
	    		String font = fontArr.get(fonts.getSelectedIndex());
	    		setFonts(font);
	    		
	    		String theme;
	    		if(ra1.isSelected() == true) {
	    			lightMode();
	    			theme = "light";
	    		} else {
	    			darkMode();
	    			theme = "dark";
	    		}
	    		
	    		try {
					jsonEdit.writeJSonFile(Integer.toString(width), Integer.toString(height), font, theme);
				} catch (IOException e1) {
					e1.printStackTrace();
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
	    
    	panel.add(title, BorderLayout.NORTH);
    	panel.add(center, BorderLayout.CENTER);
    	panel.add(btns, BorderLayout.SOUTH);
    	
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
    	
    	for(int i=0; i<fontArr.size(); i++) {
    		if(fontArr.get(i).equals(font)) {
    			fontArr.remove(i);
    			break;
    		}
    	}
    	
    	fontArr.add(0, font);
	}
	
}