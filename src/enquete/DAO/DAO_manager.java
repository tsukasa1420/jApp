package enquete.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_manager {
	Connection cone;

	/**
		DB connection
	*/
	public void useDB() {
		try {
			// connect JDBC
			Class.forName("com.mysql.cj.jdbc.Driver");

			// login DB
			String url = "jdbc:mysql://localhost/jApp?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";
			cone = DriverManager.getConnection(url, user, pass);
		}
		catch (ClassNotFoundException e) {
			// Class.forName ERROR
			System.out.println("Class.forName ERROR");
		}
		catch (SQLException e) {
			// Connection ERROR
			System.out.println("Connection ERROR");
		}
	}
	/**
		coneを閉じる
	*/
	public void closeConnection(Connection cone) {
			try {
				if(cone == null)cone.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("コネクションクローズ時のエラー");
			}
	}
	/**
		psを閉じる
	*/
	public void closePreparedStatement(PreparedStatement ps) {
			try {
				if(ps == null)ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("プリペアードステートメントクローズ時のエラー");
			}
	}
	/**
		rsを閉じる
	*/
	public void closeResultSet(ResultSet rs) {
			try {
				if(rs == null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("プリペアードステートメントクローズ時のエラー");
			}
	}
}
