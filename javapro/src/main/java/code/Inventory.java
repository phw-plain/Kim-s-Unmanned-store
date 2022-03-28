package code;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventory extends Setting {
	public JPanel panel;

	private JPanel View;
	private JPanel Modify;
	private JPanel Add;

	public JButton homebtn1;
	public JButton homebtn2;
	public JButton homebtn3;
	
	public Inventory() {
		panel = new JPanel(new CardLayout());
		panel.setBackground(background);
		View();
		Modify();
		Add();
	}

	public void setVisible(boolean tf) {
		panel.setVisible(tf);
	}
	
	private void View() {
		// View 세팅
		View = new JPanel();
		View.setBackground(background);
		View.setLayout(new BorderLayout());
		
		// navigation
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(background);
		
		// home 버튼 생성
		homebtn1 = new JButton("", logo);
		homebtn1.setRolloverIcon(logo_over); 	// rolloverIcon용 이미지 등록
		homebtn1.setContentAreaFilled(false); 	// 배경 채우기
		homebtn1.setBorderPainted(false); 		// 외각선
		homebtn1.setFocusPainted(false); 		// 선택 외각선
		
		JLabel title = new JLabel("재고 관리");
		title.setFont(font2);

		header.add(homebtn1, BorderLayout.WEST);
		header.add(title, BorderLayout.CENTER);
		
		View.add(header, BorderLayout.NORTH);
		
		View.setVisible(true);
		panel.add(View);
	}
	
	private void Modify() {
			
	}
	
	private void Add() {
		
	}
}
