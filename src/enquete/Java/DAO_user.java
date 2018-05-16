package enquete.Java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_user {
	protected Connection cone;
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
		ログイン時、DBから一致するユーザー情報を取得する
	*/
	public BeanUser login( String userName, String password ) {
		if( cone == null ) useDB();

		// prepare SQL
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			sql = "SELECT * FROM user_table WHERE name='" + userName + "' AND pass='" + password + "';";
			ps = cone.prepareStatement(sql);
			rs = ps.executeQuery();

			// DB 1 line read.
			rs.next();

			// DB 1 line datas get.
			String name = rs.getString("name");
			String pass	= rs.getString("pass");

			// DB 1 line data recode.
			BeanUser mame = new BeanUser(name, pass);

			return mame;
		}
		catch (SQLException e) {
			System.out.println("login ERROR");
		}
		return null;
	}

	/**
		ユーザー登録処理
	*/
	public void userMake( String userName, String password, String mail, int birth ) {
		if( cone == null ) useDB();

		// prepare SQL
		String sql = null;
		PreparedStatement ps =null;


		try {
			sql ="INSERT INTO user_table( name, pass, mail, birthday ) VALUES( ?, ?, ?, ? );";
			ps = cone.prepareStatement(sql);


			// Set "?" SQL statement.
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setString(3, mail);
			ps.setInt(4, birth);


			// SQL statement Run!
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL ERROR");
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				System.out.println("PreparedStatement close ERROR");
			}
		}
	}

	/**
		ユーザー情報の変更
	*/
	public void userInfoEdit( String userName, String password ) {
		if( cone == null ) useDB();
	}


	/**
		？？？
	*/
}