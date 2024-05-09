package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
		 public static Connection connectdb() {
			 try {
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 
				 String URL ="jdbc:mysql://localhost:3306/studentdata";
				 String username ="root";
				 String password ="W@2915djkq#";
				 Connection connect = DriverManager.getConnection(URL,username,password);
				 return connect;
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			 return null;
		 }
}
