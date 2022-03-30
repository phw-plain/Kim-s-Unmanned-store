package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class Setting {
	// �⺻ ����
	public int width;
	public int height;

	// color
	public Color title = new Color(0, 0, 0);
	public Color background = new Color(255, 255, 255);
	public Color header_back = new Color(254, 235, 182);
	public Color menu_back = new Color(214, 174, 242);
	public Color menu_over = new Color(253, 206, 83);
	
	// Font
	public Font font1 = new Font("����ǹ��� �־�", Font.PLAIN, 50);
	public Font font2 = new Font("����ǹ��� �־�", Font.PLAIN, 26);
	public Font font3 = new Font("����ǹ��� �־�", Font.PLAIN, 18);
	public Font font4 = new Font("����ǹ��� �־�", Font.PLAIN, 16);
	public Font font5 = new Font("����ǹ��� �־�", Font.PLAIN, 14);
	public Font font6 = new Font("����ǹ��� �־�", Font.PLAIN, 11);

	// Image
	public ImageIcon logo = new ImageIcon("src/img/logo.png");
	public ImageIcon logo_over = new ImageIcon("src/img/logo_over.png");
	public ImageIcon i;
	public Image im;
	public URL imageURL = Start.class.getClassLoader().getResource("apple.png");
	public ImageIcon img = new ImageIcon(imageURL);
	
    // Ÿ�� üũ
    final isType is = new isType();
	
	// ȸ�� ����
	public static String name;
	public static String id;
	public static String pw;
	public static String brand;
	public static double percent;
	public static boolean emp;
	public static int empsal;
	
	public Setting(){
		UIManager.put("OptionPane.messageFont", font5);
		UIManager.put("OptionPane.buttonFont", font5);
	}
	
	public void print() {
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println("brand : " + brand);
		System.out.println("percent : " + percent);
	}
}