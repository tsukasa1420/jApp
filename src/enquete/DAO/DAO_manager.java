package enquete.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_manager {
	Connection cone;

	/**
	DBに接続する
	*/
	public void useDB() {
		try {
			// JDBCに接続する
			Class.forName("com.mysql.cj.jdbc.Driver");

			// MySQLにログインする
			String url = "jdbc:mysql://localhost/jApp?serverTimezone=UTC&useSSL=false";
			String user = "student";
			String pass = "himitu";
			cone = DriverManager.getConnection(url, user, pass);
		}
		catch (ClassNotFoundException e) {
			// Class.forName()のエラー
			System.out.println("Class.forName()のエラー");
		}
		catch (SQLException e) {
			// Connectionクラスのエラー
			System.out.println("Connectionクラスのエラー");
		}
		finally {
			jAppClose(cone);
		}
	}

	/**
	coneを閉じる
	*/
	public void jAppClose(Connection cone) {
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
	public void jAppClose(PreparedStatement ps) {
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
	public void jAppClose(ResultSet rs) {
		try {
			if(rs == null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("プリペアードステートメントクローズ時のエラー");
		}
	}

	/**
	coneとpsを閉じる
	*/
	public void jAppClose( PreparedStatement ps, Connection cone ) {
		jAppClose(ps);
		jAppClose(cone);
	}

	/**
	coneとpsとrsを閉じる
	*/
	public void jAppClose( PreparedStatement ps, ResultSet rs, Connection cone ) {
		jAppClose(ps);
		jAppClose(rs);
		jAppClose(cone);
	}

	/**
	SQLエラー時のメッセージ、エラーログの表示
	*/
	public void errMesSQL( SQLException e, String errPlace ) {
		e.printStackTrace();
		System.out.println( errPlace + " : SQL" );
	}







}