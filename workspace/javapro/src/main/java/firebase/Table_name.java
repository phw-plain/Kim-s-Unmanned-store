package firebase;

public class Table_name {
	/*데이터베이스에 속성부분을 변수로 만들어 줍니다.*/ 
	String ID;
	String PW;
	String name;
	String brand;
	String location;
	int salary;
	
	/*생성자입니다.*/ 
	public Table_name() {} 
	public Table_name(String ID) {
		super(); 
		this.ID = ID;
	}
	public Table_name(String ID, String PW) {
		super(); 
		this.ID = ID;
		this.PW = PW;
	} 
	public Table_name(String ID, String PW, String name, String brand, String location, int salary) { 
		super(); 
		this.name = name; 
		this.ID = ID;
		this.PW = PW;
		this.brand = brand;
		this.location = location;
		this.salary = salary;
	} /*각 변수에 getter와 setter입니다.*/ 
	public String getName() { return name; } 
	public void setName(String name) { this.name = name; } 
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
