package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enquete.Java.BeanUser;

public class DAO_user {
	/**
	ログイン時、DBから一致するユーザー情報を取得する
	*/
	public boolean login( String userId, String password ) {
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "SELECT * FROM user_table WHERE id=? AND pass=?";
			ps = dm.cone.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();

// いらない？↓↓↓
			// DBの選択した1行を読む。
//			rs.next();

			// DBの1行のデータを取得する。
//			String name	= rs.getString("id");
//			String pass	= rs.getString("pass");


//			// 取得したデータを豆に入れる。
//			BeanUser mame = new BeanUser(name, pass);
//
//			/* ◆◆◆ */System.out.println("DB名\t\t：" + mame.getUserName() + "<DAO_user.login>");
//			/* ◆◆◆ */System.out.println("DBパス\t：" + mame.getPassword() + "<DAO_user.login>");
// いらない？↑↑↑


			return rs.next();
		}
		catch (SQLException e) {
			System.out.println("login ERROR");
			return false;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	ユーザー登録処理
	*/
	public boolean userMake( String userId, String password, String mail, int birth ) {
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		String sql = null;
		PreparedStatement ps =null;

		try {
			sql ="INSERT INTO user_table( id, pass, mail, birthday ) VALUES( ?, ?, ?, ? );";
			ps = dm.cone.prepareStatement(sql);

			// 「?」に置き換えるものを記述している
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, mail);
			ps.setInt(4, birth);

			// SQL命令実行
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			System.out.println("SQL ERROR・userMake");
			return false;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	ユーザー情報ページでユーザー情報を表示
	*/
	public BeanUser userInfoFunc(String userId) {
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "SELECT * FROM user_table WHERE id=?";
			ps = dm.cone.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			// DBの選択した1行を読む。
			rs.next();

			// DBの1行のデータを取得する。
			String name	= rs.getString("id");
			String pass	= rs.getString("pass");
			String mail	= rs.getString("mail");
			String birth	= rs.getString("birthday");

			return new BeanUser(name, pass, mail, birth);
		}
		catch (SQLException e) {
			System.out.println("login ERROR");
			return null;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	ユーザー情報の変更
	*/
	public boolean userInfoUpdate( String id, String edit, String newData ) {
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "UPDATE user_table SET " + edit + " = ? WHERE id = ?";
			ps = dm.cone.prepareStatement(sql);

			/* ◆◆◆ */System.out.println( edit + " : userInfoUpdate()" );
			/* ◆◆◆ */System.out.println( newData + " : userInfoUpdate()" );
			/* ◆◆◆ */System.out.println( id + " : userInfoUpdate()" );

			ps.setString(1, newData);
			ps.setString(2, id);
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			System.out.println("UPDATE ERROR");
			return false;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	アカウント削除
	*/
	public boolean userInfoDelete(String id) {
		DAO_manager dm = new DAO_manager();
		if( dm.cone == null ) dm.useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "DELETE FROM user_table WHERE id=?";
			ps = dm.cone.prepareStatement(sql);

			/* ◆◆◆ */System.out.println( id + " : DAO_user.userInfoDelete()" );

			ps.setString(1, id);
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			System.out.println("DELETE ERROR");
			return false;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeConnection(dm.cone);
		}
	}
}