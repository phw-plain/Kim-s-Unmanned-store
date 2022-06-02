package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Setting {
	// 기본 정보
//	public static int width = 1280;
//	public static int height = 1024;
	public static int width = 1024;
	public static int height = 786;
	public boolean resizable = false;

	// color
	public static Color title;
	public static Color background;
	public static Color header_back;
	public static Color menu_back;
	public static Color menu_over;
	public static Color fontcolor;
	public static Color maincolor;
	
	// Font
	static String font = "배달의민족 주아";
	public Font font1 = new Font(font, Font.PLAIN, 50);
	public Font font2 = new Font(font, Font.PLAIN, 26);
	public Font font3 = new Font(font, Font.PLAIN, 18);
	public Font font4 = new Font(font, Font.PLAIN, 16);
	public Font font5 = new Font(font, Font.PLAIN, 14);
	public Font font6 = new Font(font, Font.PLAIN, 11);

	// Image
	public ImageIcon logo = new ImageIcon("src/img/logo.png");
	public ImageIcon logo_over = new ImageIcon("src/img/logo_over.png");
	public ImageIcon i;
	public Image im;
	public URL imageURL = Start.class.getClassLoader().getResource("apple.png");
	public ImageIcon img = new ImageIcon(imageURL);
	
	public ImageIcon mBtn_img1 = new ImageIcon("src/img/btn1_1.png");
	public ImageIcon mBtn_img2 = new ImageIcon("src/img/btn1_2.png");
	public ImageIcon mBtn_img3 = new ImageIcon("src/img/btn1_3.png");
	public ImageIcon mBtn5_img1 = new ImageIcon("src/img/btn5_1.png");
	public ImageIcon mBtn5_img2 = new ImageIcon("src/img/btn5_2.png");
	public ImageIcon mBtn5_img3 = new ImageIcon("src/img/btn5_3.png");
	
    // 타입 체크
    final isType is = new isType();
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Setting.name = name;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		Setting.id = id;
	}

	public static String getPw() {
		return pw;
	}

	public static void setPw(String pw) {
		Setting.pw = pw;
	}

	public static String getBrand() {
		return brand;
	}

	public static void setBrand(String brand) {
		Setting.brand = brand;
	}

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		Setting.location = location;
	}

	public static boolean isEmp() {
		return emp;
	}

	public static void setEmp(boolean emp) {
		Setting.emp = emp;
	}

	public static int getEmpsal() {
		return empsal;
	}

	public static void setEmpsal(int empsal) {
		Setting.empsal = empsal;
	}

	// 회원 정보
	private static String name;
	protected static String id = "sdf";
	protected static String pw;
	private static String brand;
	private static String location;
	private static boolean emp;
	private static int empsal;
	public static boolean existence;

	public static Frame startFrame;
	
	
	public Setting(){
		header_back = new Color(254, 235, 182);
		menu_back = new Color(214, 174, 242);
		menu_over = new Color(253, 206, 83);
		maincolor = new Color(254, 235, 182);
		UIManager.put("OptionPane.messageFont", font5);
		UIManager.put("OptionPane.buttonFont", font5);
	}
	
	public void print() {
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println("brand : " + brand);
		System.out.println("location : " + location);
	}
	
	public static void darkMode() {
		title = new Color(255, 255, 255);
		background = new Color(58, 58, 58);
		fontcolor = new Color(255, 255, 255);
	}
	
	public static void lightMode() {
		title = new Color(0, 0, 0);
		background = new Color(255, 255, 255);
		fontcolor = new Color(0, 0, 0);
	}
}