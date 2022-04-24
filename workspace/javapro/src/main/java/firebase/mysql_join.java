package firebase;
import java.sql.DriverManager; 
import java.sql.SQLException;  

public class mysql_join extends mysql_main{
			public boolean select(String ID) {
				String query = "select ID from user where ID = '"+ID+"';";
				try {
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
					super.stmt = conn.createStatement();
					super.rs = stmt.executeQuery(query);
					while (rs.next()){ 
						if(rs!=null)
							return false;
						} 
					stmt.close(); 
					conn.close(); 
					} catch (ClassNotFoundException e) { 
						System.out.println("Class Not Found Exection");
						return true;
					} catch (SQLException e) { 
							System.out.println("SQL Exception : " + e.getMessage()); 
							return true;
					}
				return true;
		    }
			public String checkID(String ID, String PW) {
				String query = "select ID from user where ID = '"+ID+ "' and PW = '"+PW+"';"; // table에 모든 레코드를 출력하는 query문입니다. 
				try { // 데이터베이스에 접속합니다. 
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(super.DB_URL,super.USERNAME,super.PASSWORD); 
					super.stmt = conn.createStatement();
					super.rs = stmt.executeQuery(query);
					while (rs.next()){ 
						if(rs!=null)
							return ID;
						} 
					stmt.close(); 
					conn.close(); 
					} catch (ClassNotFoundException e) { 
						System.out.println("Class Not Found Exection");
						return null;
					} catch (SQLException e) { 
							System.out.println("SQL Exception : " + e.getMessage()); 
							return null;
					}
				return null;
				} 

				
			public void tableInsert(String ID, String PW, String name, String brand, String location, int salary){ 
				System.out.println("-------");
				Table_name tn = new Table_name(ID,PW, name, brand, location, salary); //외부에서 매개변수로 데이터를 받아 Qurey문을 만들어 줍니다. 
				String query = "INSERT INTO user(cl_id,cl_password, cl_name, cl_brand, cl_location, cl_salary)values('" + tn.getName() + "', '" + tn.getID() +"','"+tn.getPW() +"','"+tn.getBrand() +"','"+tn.getLocation() +"',"+tn.getSalary()+");";
				System.out.println(query); // Qurey문 확인 
				try { // 데이터베이스에 접속합니다. 
					Class.forName(super.JDBC_DRIVER); 
					super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
					super.stmt = conn.createStatement(); //데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
					super.stmt.executeUpdate(query); // query를 실행시킵니다.
				stmt.close(); 
				conn.close(); 
				createTable(ID);
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
					} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
						} 
				} 	
			public void createTable(String ID){ 
				System.out.println("------------");
					String query = "CREATE TABLE "+ID+"_sales(day int primary key,sales int not null default 0, today_expenses int not null);";
					System.out.println(query); // Qurey문 확인 
					try { // 데이터베이스에 접속합니다. 
						Class.forName(JDBC_DRIVER); 
						Class.forName(super.JDBC_DRIVER); 
						super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
						super.stmt = conn.createStatement(); //데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
						super.stmt.executeUpdate(query); // query를 실행시킵니다.
						stmt.close(); 
						conn.close();
						createInventory(ID);
					} catch (ClassNotFoundException e) { 
						System.out.println("Class Not Found Exection"); 
					} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
					} 
			} 
			public void createInventory(String ID){ 
				System.out.println("------------");
					String query = "CREATE TABLE "+ID+"_inventory(inv_code INT PRIMARY KEY"
							+ "inv_name VALCHAR"
							+ "inv_cnt INT"
							+ "inv_price INT"
							+ "inv_picture VALCHAR"
							+ "inv_explain TEXT"
							+ "inv_category INT"
							+ "inv_standard VALCHAR"
							+ "inv_amount INT);";
					System.out.println(query); // Qurey문 확인 
					try { // 데이터베이스에 접속합니다. 
						
						Class.forName(JDBC_DRIVER); 
						Class.forName(super.JDBC_DRIVER); 
						super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
						super.stmt = conn.createStatement(); //데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
						super.stmt.executeUpdate(query); // query를 실행시킵니다.
						stmt.close(); 
						conn.close(); 
						for(int i = 0; i<=6; i++) {
							insertToday(ID, i);
						}
						
					} catch (ClassNotFoundException e) { 
						System.out.println("Class Not Found Exection"); 
					} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
					} 
			}
			private void insertToday(String id, int day) {
				// TODO Auto-generated method stub
				String query = "INSERT INTO "+id+"_today(day, today_sale, today_expenses, today_total)values("+day+",0,0,0);"; 
				System.out.println(query); // Qurey문 확인 
				try { // 데이터베이스에 접속합니다. 
				Class.forName(super.JDBC_DRIVER); 
				super.conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD); 
				super.stmt = conn.createStatement(); //데이터베이스에 query문을 넘길 Statement를 만들어줍니다. 
				super.stmt.executeUpdate(query); // query를 실행시킵니다. 
				stmt.close(); 
				conn.close(); 
				} catch (ClassNotFoundException e) { 
					System.out.println("Class Not Found Exection"); 
					} catch (SQLException e) { 
						System.out.println("SQL Exception : " + e.getMessage()); 
					} 
			}

}
