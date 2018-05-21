package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enquete.Java.BeanEnquete;

public class DAO_enquete {
	/**
		↓のテーブルを取得する。
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
		｜主　　	｜アンケ名	｜Q1			｜Q2			｜Q3			｜Q4			｜Q5			｜
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
		｜food	｜食べ物系	｜肉好き？	｜魚好き？	｜野菜好き？	｜チョコ好き？	｜ミント好き？	｜
		｜anim	｜動物系		｜犬好き？	｜猫好き？	｜ウサギ好き？	｜鳥好き？	｜魚好き？	｜
		｜		｜			｜			｜			｜			｜			｜			｜
		＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
	*/
	public BeanEnquete getQuestion(String key) {
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
			ps.setString(1, key);
			rs = ps.executeQuery();

			rs.next();
			return new BeanEnquete( rs.getString("nameenq"), rs.getString("q1"), rs.getString("q2"),rs.getString("q3"), rs.getString("q4"), rs.getString("q5") );
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error" );
		}
		return null;
	}

	/**
		レコードの数（行数）とアンケート名を取得する
		リストのサイズ＝レコード数
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
			while( rs.next() ) {

				list.add( new BeanEnquete( rs.getString("id"), rs.getString("nameenq") ) );
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error" );
		}
		finally {
			dm.closePreparedStatement(ps);
			dm.closeResultSet(rs);
		}
		return null;
	}


	/**
		DBに回答を記録する。
	*/
	public void addAnswer() {
//		HttpSession session = request.getSession(false);
//		int qNum = (int)session.getAttribute("qNum");
//
//
//		for (int i = 1; i <= qNum; i++) {
//			session.setAttribute( "qAnswer" + i, request.getParameter("qAnswer" + i) );
//			System.out.println( "# " + request.getParameter("qAnswer" + i) );
//		}

		int qNum = 5;
		List<String> qAnswer = new ArrayList<>();
		for (int i = 0; i < qNum; i++) {
			qAnswer.add( "A" + (i + 1) );
		}
		for (String string : qAnswer) {
			System.out.println("& " + string);
		}



		// DBを使えるようにしている
		DAO_manager dm = new DAO_manager();
		if(dm.cone == null) dm.useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;

		try {
			// SQL文を指定して実行
			sql = "INSERT INTO test_ans( q1, q2, q3, q4, q5 ) VALUES( ?, ?, ?, ?, ? )";
			ps = dm.cone.prepareStatement(sql);

			System.out.println("check01");

//			for ( int i = 1; i <= qAnswer.size(); i++ ) {
//				ps.setString( i, qAnswer.get(i - 1) );
//			}

			ps.setString( 1, "a1" );
			ps.setString( 2, "a2" );
			ps.setString( 3, "a3" );
			ps.setString( 4, "a4" );
			ps.setString( 5, "a5" );

			System.out.println("check02");

			ps.executeUpdate();

			System.out.println("check03");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println( "sql error" );
		}
		finally {
			dm.closePreparedStatement(ps);
		}
	}

	public void testMain() {
		BeanEnquete be = getQuestion("food");
		System.out.println( "#" + be.getNameEnq() );
		System.out.println( "#" + be.getQ1() );
		System.out.println( "#" + be.getQ2() );
		System.out.println( "#" + be.getQ3() );
		System.out.println( "#" + be.getQ4() );
		System.out.println( "#" + be.getQ5() );
		System.out.println();

		List<BeanEnquete> list = getEnqName();
		for (BeanEnquete string : list) {
			System.out.print ( "%" + string.getEnqId() );
			System.out.println ( "%" + string.getNameEnq());
		}
		System.out.println( "%" + list.size() );
		System.out.println();

		addAnswer();
	}
}
