package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enquete.Java.BeanUser;

public class DAO_user {

	/**
		ログイン時、DBから一致するユーザー情報を取得する
	*/
	public BeanUser login( String userName, String password ) {
		DAO_manager dm = new DAO_manager();

		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			sql = "SELECT * FROM user_table WHERE name='" + userName + "' AND pass='" + password + "';";
			ps = dm.cone.prepareStatement(sql);
			rs = ps.executeQuery();

			// DBの選択した1行を読む。
			rs.next();

			// DBの1行のデータを取得する。
			String name	= rs.getString("name");
			String pass	= rs.getString("pass");

			// 取得したデータを豆に入れる。
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
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// prepare SQL
		String sql = null;
		PreparedStatement ps =null;


		try {
			sql ="INSERT INTO user_table( name, pass, mail, birthday ) VALUES( ?, ?, ?, ? );";
			ps = dm.cone.prepareStatement(sql);


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
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();
	}


	/**
		？？？
	*/
}