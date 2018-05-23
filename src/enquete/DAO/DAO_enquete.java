package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enquete.Java.BeanEnquete;

public class DAO_enquete {
	/**
	レコードの数（行数）とアンケート名を取得する
		リストのサイズ＝レコード数
		リストに全レコードのアンケート名を入れる
		その数がレコード数
	*/
	public List<BeanEnquete> getEnqName(){
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_enq";
			ps = dm.cone.prepareStatement(sql);
			rs = ps.executeQuery();

			List<BeanEnquete> list = new ArrayList<>();

			String url = "/jApp/EnqueteOperate?jump=";
			while( rs.next() ) {
				list.add( new BeanEnquete( rs.getString("id"), rs.getString("nameenq"), url + rs.getString("id") ) );
			}
			return list;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error" );
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
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
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_enq WHERE id=?";
			ps = dm.cone.prepareStatement(sql);
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
			e.printStackTrace();
			System.out.println( "SQL getQuestion()メソッドでエラー" );
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
		return null;
	}

	/**
	DBに回答を記録する。
	*/
	public void addAnswer( String userName, String enqPri, List<String> ansList ) {
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;

		try {
			// SQL文を指定して実行
			sql = "INSERT INTO test_ans( user_name, enq_name, a1, a2, a3, a4, a5 ) VALUES( ?, ?, ?, ?, ?, ?, ? )";
			ps = dm.cone.prepareStatement(sql);
			ps.setString( 1, userName );
			ps.setString( 2, enqPri );
			for ( int i = 3; i < ( ansList.size() + 3 ); i++ ) {
				ps.setString( i, ansList.get(i - 3) );
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error・addAnswer" );
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	解答済みアンケートを全表示
	*/
	public List<BeanEnquete> resultViewAll(String userID) {
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_ans WHERE user_name = ?";
			ps = dm.cone.prepareStatement(sql);
			ps.setString(1, userID);
			rs = ps.executeQuery();

			List<BeanEnquete> beList = new ArrayList<>();
			while ( rs.next() ) {
				int priNo = rs.getInt("id");
				String enqID = rs.getString("enq_name");
				beList.add( new BeanEnquete(priNo, enqID) );
			}
			return beList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error・addAnswer" );
			return null;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	解答済みアンケート、回答結果リストを返す
	*/
	public List<String> resultView_getAnswer(int priNo) {
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_ans WHERE id = ?";
			ps = dm.cone.prepareStatement(sql);
			ps.setInt(1, priNo);
			rs = ps.executeQuery();

			rs.next();

			List<String> aList = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				aList.add( rs.getString("a" + i) );
			}
			return aList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "error・アンケート個別表示の回答5つをとるところ" );
			return null;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	解答済みアンケート、なんのアンケート(enqID)かを返す
	*/
	public String resultView_getEnqID(int priNo) {
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_ans WHERE id = ?";
			ps = dm.cone.prepareStatement(sql);
			ps.setInt(1, priNo);
			rs = ps.executeQuery();

			rs.next();

			return rs.getString("enq_name");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "error・アンケートIDをとるところ" );
			return null;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}

	/**
	解答済みアンケート質問リストを返す
	*/
	public List<String> resultView_getQuestion(String enqID) {
		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
			sql = "SELECT * FROM test_enq WHERE id = ?";
			ps = dm.cone.prepareStatement(sql);
			ps.setString(1, enqID);
			rs = ps.executeQuery();

			rs.next();

			List<String> qList = new ArrayList<>();
			for (int i = 1; i <= 5; i++) {
				qList.add( rs.getString("q" + i) );
			}
			return qList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "error・アンケート個別表示の質問5つをとるところ" );
			return null;
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
			dm.closeConnection(dm.cone);
		}
	}
}
