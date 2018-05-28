package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import enquete.Java.BeanUser;

public class DAO_user extends DAO_manager{
	/**
	ログイン時、DBから一致するユーザー情報を取得する
	*/
	public boolean login( String userId, String password ) {
		if( cone == null ) useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "SELECT * FROM user_table WHERE id=? AND pass=?";
			ps = cone.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();

			return rs.next();
		}
		catch (SQLException e) {
			errMesSQL(e, "ログイン時エラー");
			return false;
		}
		finally {
			jAppClose(ps, rs, cone);
		}
	}

	/**
	ユーザー登録処理
	*/
	public boolean userMake( String userId, String password, String mail, int birth ) {
		if( cone == null ) useDB();

		// SQLを使う準備。
		String sql = null;
		PreparedStatement ps =null;

		try {
			sql ="INSERT INTO user_table( id, pass, mail, birthday ) VALUES( ?, ?, ?, ? );";
			ps = cone.prepareStatement(sql);

			// 「?」に置き換えるものを記述している
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, mail);
			ps.setInt(4, birth);

			// SQL命令実行
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			errMesSQL(e, "アカウント作成時のエラー");
			return false;
		}
		finally {
			jAppClose(ps, cone);
		}
	}

	/**
	ユーザー情報ページでユーザー情報を表示
	*/
	public BeanUser userInfoFunc(String userId) {
		if( cone == null ) useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "SELECT * FROM user_table WHERE id=?";
			ps = cone.prepareStatement(sql);
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
			errMesSQL(e, "ユーザー情報ページでの取得エラー");
			return null;
		}
		finally {
			jAppClose(ps, rs, cone);
		}
	}

	/**
	ユーザー情報の変更
	*/
	public boolean userInfoUpdate( String id, String edit, String newData ) {
		if( cone == null ) useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "UPDATE user_table SET " + edit + " = ? WHERE id = ?";
			ps = cone.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, id);
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			errMesSQL(e, "ユーザー情報の変更時のエラー");
			return false;
		}
		finally {
			jAppClose(ps, cone);
		}
	}

	/**
	アカウント削除
	*/
	public boolean userInfoDelete(String id) {
		if( cone == null ) useDB();

		// SQLを使う準備。
		PreparedStatement ps =null;
		String sql = null;

		try {
			// SQL命令実行
			sql = "DELETE FROM user_table WHERE id=?";
			ps = cone.prepareStatement(sql);

			ps.setString(1, id);
			ps.executeUpdate();

			return true;
		}
		catch (SQLException e) {
			errMesSQL(e, "アカウント削除時のエラー");
			return false;
		}
		finally {
			jAppClose(ps, cone);
		}
	}
}