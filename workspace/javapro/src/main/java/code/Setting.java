package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.io.FileReader;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javapro.JsonFileEdit;

public class Setting {
	// 기본 정보
	public static int width;
	public static int height;
	public boolean resizable = false;

	public static JsonFileEdit jsonEdit = new JsonFileEdit();
	// color
	public static String theme;
	public static Color title;
	public static Color background;
	public static Color header_back;
	public static Color menu_back;
	public static Color menu_over;
	public static Color fontcolor;
	public static Color maincolor;
	
	// Font
	public static String font;
	public static Font font1;
	public static Font font2;
	public static Font font3;
	public static Font font4;
	public static Font font5;
	public static Font font6;

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
	
	// 회원 정보
    private static String name;
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Setting.name = name;
	}

	public static String getId() {
		return id;
	}

	public static boolean isEmp() {
		return emp;
	}

	public static void setEmp(boolean emp) {
		Setting.emp = emp;
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

	public static int getEmpsal() {
		return empsal;
	}

	public static void setEmpsal(int empsal) {
		Setting.empsal = empsal;
	}

	public static boolean isExistence() {
		return existence;
	}

	public static void setExistence(boolean existence) {
		Setting.existence = existence;
	}

	private static String id = "sdf";
	private static String pw;
	private static String brand;
	private static String location;
	public static boolean emp;
	private static int empsal;
	public static boolean existence;


	
	// Inventory
	protected static Vector<String> code = new Vector<String>();
	protected static Vector<String> product_name = new Vector<String>();
	protected static Vector<String> category = new Vector<String>();
	protected static Vector<String> standard = new Vector<String>();
	protected static Vector<Integer> cnt = new Vector<Integer>();
	protected static Vector<Integer> price = new Vector<Integer>();
	protected static Vector<Integer> cost = new Vector<Integer>();
	protected static Vector<Integer> amount = new Vector<Integer>();
	protected static Vector<String> explain = new Vector<String>();
	protected static Vector<String> picture = new Vector<String>();

	public static Frame startFrame;
	
	public Setting(){
		width = Integer.parseInt(jsonEdit.get("x"));
		height = Integer.parseInt(jsonEdit.get("y"));
		theme = jsonEdit.get("theme");
		
		header_back = new Color(254, 235, 182);
		menu_back = new Color(214, 174, 242);
		menu_over = new Color(253, 206, 83);
		maincolor = new Color(254, 235, 182);
		
		if(theme.equals("light")) lightMode();
		else darkMode();
		
		UIManager.put("OptionPane.messageFont", font5);
		UIManager.put("OptionPane.buttonFont", font5);
	}
	
	public static void print() {
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
	
	public static void setFonts(String f) {
		font = f;
		font1 = new Font(font, Font.PLAIN, 50);
		font2 = new Font(font, Font.PLAIN, 26);
		font3 = new Font(font, Font.PLAIN, 18);
		font4 = new Font(font, Font.PLAIN, 16);
		font5 = new Font(font, Font.PLAIN, 14);
		font6 = new Font(font, Font.PLAIN, 11);
	}
}