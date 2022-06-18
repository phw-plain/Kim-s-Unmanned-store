package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.io.FileReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
	ImageIcon img = new ImageIcon("src/img/icon2.png");
	
    // 타입 체크
    final isType is = new isType();
	
	// 회원 정보
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
   	private static String name;
   	private static String id = "sdf";
   	private static String pw;
   	private static String brand;
   	private static String location;
   	public static boolean emp;
   	public static int empsal;
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
	
	// 고객 DB 참조
	// Customer
	protected static Vector<String> customer_id = new Vector<String>();
	protected static Vector<String> customer_pw = new Vector<String>();
	protected static Vector<String> customer_name = new Vector<String>();
	protected static Vector<String> customer_telephone = new Vector<String>();
	protected static Vector<String> customer_email = new Vector<String>();
	protected static Vector<Integer> customer_point = new Vector<Integer>();
	protected static Vector<Integer> customer_exchange = new Vector<Integer>();
	protected static Vector<Integer> customer_refund = new Vector<Integer>();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();        
	public int now = (Integer.parseInt(dateFormat.format(date)));
	
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMM");
	Date date1 = new Date();        
	public int Year = (Integer.parseInt(dateFormat.format(date1)));

	public static Frame startFrame;
	
	public Setting(){
		width = Integer.parseInt(jsonEdit.get("x"));
		height = Integer.parseInt(jsonEdit.get("y"));
		theme = jsonEdit.get("theme");
		
		maincolor = new Color(3, 60, 89);
		
		if(theme.equals("light")) lightMode();
		else darkMode();
		
		img = imageSetSize(img, 1000, 1000);
		logo = imageSetSize(logo, 150, 150);
		logo_over = imageSetSize(logo_over, 150, 150);
		
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
		
		header_back = new Color(3, 60, 89);
		menu_back = new Color(255, 211, 91);
		menu_over = new Color(235, 76, 2);
	}
	
	public static void lightMode() {
		title = new Color(0, 0, 0);
		background = new Color(255, 255, 255);
		fontcolor = new Color(0, 0, 0);
		
		header_back = new Color(253, 206, 83);
		menu_back = new Color(255, 211, 91);
		menu_over = new Color(235, 76, 2);
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
	
	public ImageIcon imageSetSize(ImageIcon icon, int i, int j) { // image Size Setting
		Image ximg = icon.getImage();  //ImageIcon을 Image로 변환.
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg); 
		return xyimg;
	}
}