package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enquete.Java.BeanEnquete;

public class DAO_enquete extends DAO_manager{
	/**
	レコードの数（行数）とアンケート名を取得する
		リストのサイズ＝レコード数
		リストに全レコードのアンケート名を入れる
		その数がレコード数
	*/
	public List<BeanEnquete> getEnqName(){
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM enq_table";
			ps = cone.prepareStatement(sql);
			rs = ps.executeQuery();

			List<BeanEnquete> list = new ArrayList<>();

			String url = "/jApp/EnqueteOperate?enq=";
			while( rs.next() ) {
				list.add( new BeanEnquete( rs.getString("id"), rs.getString("enq_name"), url + rs.getString("id") ) );
			}
			return list;
		}
		catch (SQLException e) {
			errMesSQL(e, "マイページにアンケート名を表示する時のエラー");
		}
		finally {
			jAppClose(ps, rs, cone);
		}
		return null;
	}

	/**
	主キーから質問（5つ）を取得する
		主キーを引数にして主キーのレコードのデータを取得してリストで返している
		↓のテーブルを取得する。
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
		｜主　　	｜アンケ名	｜Q1			｜Q2			｜Q3			｜Q4			｜Q5			｜
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
		｜food	｜食べ物系	｜肉好き？	｜魚好き？	｜野菜好き？	｜チョコ好き？	｜ミント好き？	｜
		｜anim	｜動物系		｜犬好き？	｜猫好き？	｜ウサギ好き？	｜鳥好き？	｜魚好き？	｜
		｜		｜			｜			｜			｜			｜			｜			｜
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
	*/
	public List<String> getQuestion(String pri) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM enq_table WHERE id=?";
			ps = cone.prepareStatement(sql);
			ps.setString(1, pri);
			rs = ps.executeQuery();

			rs.next();

			List<String> setQuestion = new ArrayList<>();
			setQuestion.add( rs.getString("q1") );
			setQuestion.add( rs.getString("q2") );
			setQuestion.add( rs.getString("q3") );
			setQuestion.add( rs.getString("q4") );
			setQuestion.add( rs.getString("q5") );

			return setQuestion;
		}
		catch (SQLException e) {
			errMesSQL(e, "アンケート回答時のエラー");
		}
		finally {
			jAppClose(ps, rs, cone);
		}
		return null;
	}

	/**
	DBに回答を記録する。
	*/
	public void addAnswer( String userName, String enqPri, List<String> ansList ) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;

		try {
			// SQL文を指定して実行
//			sql = "INSERT INTO test_ans( user_name, enq_name, a1, a2, a3, a4, a5 ) VALUES( ?, ?, ?, ?, ?, ?, ? )";
			sql = "INSERT INTO ans_table( user_id, enq_id, a1, a2, a3, a4, a5 ) VALUES( ?, ?, ?, ?, ?, ?, ? )";
			ps = cone.prepareStatement(sql);
			ps.setString( 1, userName );
			ps.setString( 2, enqPri );
			for ( int i = 3; i < ( ansList.size() + 3 ); i++ ) {
				ps.setString( i, ansList.get(i - 3) );
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			errMesSQL(e, "DBに回答を記録する時のエラー");
		}
		finally {
			jAppClose(ps, cone);
		}
	}

	/**
	解答済みアンケートを全表示
	*/
	public List<BeanEnquete> resultViewAll(String userID) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
//			sql = "SELECT * FROM test_ans WHERE user_name = ?";
			sql = "SELECT * FROM ans_table WHERE user_id = ?";
			ps = cone.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();

			List<BeanEnquete> beList = new ArrayList<>();
			while ( rs.next() ) {
				int priNo = rs.getInt("id");
				String enqID = rs.getString("enq_id");
				String enqName = resultViewAll_getEnqName(enqID);
				beList.add( new BeanEnquete(priNo, enqName) );
			}
			return beList;
		} catch (SQLException e) {
			errMesSQL(e, "解答済みアンケートを全表示時のエラー");
			return null;
		}
		finally {
			jAppClose(ps, rs, cone);
		}
	}

	/**
	アンケートのID（xxxx）受け取って、対応するアンケートの名前を返す
	*/
	public String resultViewAll_getEnqName(String enqID) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM enq_table WHERE id = ?";
			ps = cone.prepareStatement(sql);
			ps.setString(1, enqID);
			rs = ps.executeQuery();

			rs.next();

			return rs.getString("enq_name");
		} catch (SQLException e) {
			errMesSQL(e, "解答済みアンケート質問リストを返す時のエラー");
			return null;
		}
		finally {
			jAppClose(ps, rs, cone);
		}
	}

	/**
	DBに回答を記録する
	*/
	public boolean enqueteMake_DB( String enqID, String enqName, List<String> qList ) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;

		try {
			// SQL文を指定して実行
			sql = "INSERT INTO enq_table( id, enq_name, q1, q2, q3, q4, q5 ) VALUES( ?, ?, ?, ?, ?, ?, ? )";
			ps = cone.prepareStatement(sql);
			ps.setString( 1, enqID );
			ps.setString( 2, enqName );
			for ( int i = 3; i < ( qList.size() + 3 ); i++ ) {
				ps.setString( i, qList.get(i - 3) );
			}
			ps.executeUpdate();

			return true;
		} catch (SQLException e) {
			errMesSQL(e, "DBに回答を記録する時のエラー");
			return false;
		}
		finally {
			jAppClose(ps, cone);
		}
	}
}