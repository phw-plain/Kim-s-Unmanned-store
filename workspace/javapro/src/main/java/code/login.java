package code;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.gvt.GVTTreeRendererAdapter;
import org.apache.batik.swing.gvt.GVTTreeRendererEvent;
import org.apache.batik.swing.svg.SVGDocumentLoaderAdapter;
import org.apache.batik.swing.svg.SVGDocumentLoaderEvent;
import org.apache.batik.swing.svg.GVTTreeBuilderAdapter;
import org.apache.batik.swing.svg.GVTTreeBuilderEvent;

class login extends Setting {
	private Frame mainFrame;
	private JPanel subpanel;
	private JPanel panelLeft;
	private JPanel panelRight;
	private JLabel headerLabel;
	private Button b1;
	
	private TextField tf1;
	private TextField tf2;
	
	public login() throws MalformedURLException {
	    prepareGUI();
	}
	
	private void prepareGUI() throws MalformedURLException {
		// Frame 기본 셋팅
		mainFrame = new Frame("박리다매 무인가게");
	    mainFrame.setSize(width ,height);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(resizable);
	    mainFrame.setVisible(true);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
    	mainFrame.setIconImage(img.getImage());	    // Icon 변경

		// Left image
    	// SVG 이미지 불러오기
    	String path = "file:///" + System.getProperty("user.dir") + "/src/img/";
    	path += "login_img.svg";
    	
    	JSVGCanvas svgCanvas = new JSVGCanvas();
    	svgCanvas.setURI(new URL(path).toString());
    	svgCanvas.setBackground(new Color(3,60,89));
	    
		// Left title
		headerLabel = new JLabel();
		headerLabel.setHorizontalAlignment(JLabel.CENTER);
		headerLabel.setVerticalAlignment(JLabel.EAST);
		headerLabel.setText("로그인");
		headerLabel.setFont(font1);
		headerLabel.setForeground(title);

		// Right content
		Panel p = new Panel();
		p.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 10));

		Panel form = new Panel(new GridLayout(3,2,0,5));
		
		JLabel label1 = new JLabel("아이디");
		label1.setFont(font3);
		label1.setForeground(fontcolor);
		tf1 = new TextField("", 15);
		tf1.selectAll();
		form.add(label1);
		form.add(tf1);

		JLabel label2 = new JLabel("비밀번호");
		label2.setFont(font3);
		label2.setForeground(fontcolor);
		tf2 = new TextField("", 15);
		tf2.setEchoChar('●');
		form.add(label2);
		form.add(tf2);

		// 버튼 생성
		JPanel btns = new JPanel(new FlowLayout());

		RoundedButton check = new RoundedButton("확인");
		RoundedButton cancel = new RoundedButton("취소");
	      
		// 버튼 설정
		check.setFocusPainted(false); 
	    check.setFont(font3);
	    cancel.setFocusPainted(false); 
	    cancel.setFont(font3);
		
	    btns.add(check);
	    btns.add(cancel);
	    btns.setBackground(background);
	    
	    // 버튼 클릭 이벤트
	    tf1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		tf2.requestFocus();
	    	}
	    });
	    tf2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		event();
	    	}
	    });
	    check.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		event();
			}
	    });
	    cancel.addActionListener(new ActionListener() {
	       public void actionPerformed(ActionEvent e) {
	    	   new Start();
	    	   
	    	   // 프레임 전환
	    	   mainFrame.setVisible(false);
	       }
	    });
	
	    p.add(form);
	    p.add(btns);
	    p.setBackground(background);
	     
		panelRight = new JPanel();
		panelRight.setBackground(background);
		panelRight.setLayout(new GridLayout(3, 1));
		panelRight.add(headerLabel);
		panelRight.add(p);
		
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(1, 2));
		subpanel.add(svgCanvas, BorderLayout.SOUTH);
		subpanel.add(panelRight, BorderLayout.NORTH);
	     
	    mainFrame.add(subpanel);
	    mainFrame.setVisible(true);
	}
	
	private void event() {
		// 데이터 저장 변수 선언
 	   	String input_id = tf1.getText();
 	   	String input_pw = tf2.getText();
 	   
 	   // 데이터 불러오기
 	   	String id = "1";
 	   	String pw = "1";
 	   
 	   if(input_id.length() == 0) {
 		   	JOptionPane.showMessageDialog(null
 				   , "아이디를 입력해주세요."
 				   , "박리다매 무인가게"
 				   , JOptionPane.ERROR_MESSAGE
 				   );
			} else if(input_pw.length() == 0) {
				JOptionPane.showMessageDialog(null
						, "비밀번호를 입력해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
						);
			} else if(!id.equals(input_id) || !pw.equals(input_pw)) {
				JOptionPane.showMessageDialog(null
						, "아이디 또는 비밀번호 입력 오류. 다시 한번 확인 해주세요."
						, "박리다매 무인가게"
						, JOptionPane.ERROR_MESSAGE
						);
			} else {
				JOptionPane.showMessageDialog(null
						, "환영합니다!"
						, "박리다매 무인가게"
						, JOptionPane.PLAIN_MESSAGE
						);
				
				// 로그인 정보 저장
				setName("고고곡");
				setBrand("신림사거리점");
				setLocation("관악구");
				emp = false;
				setEmpsal(0);
				
				// 테스트 계정 확인용
		    	print();
						
				new Manage(id, pw);
	    		mainFrame.dispose();
			}
	}
	
    class MyPanel extends JPanel{   
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(im,0,0,getWidth(),getHeight(),this);
        }
    }
}