package enquete.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import enquete.Java.BeanEnquete;

public class DAO_result extends DAO_manager {
	/**
	◆回答済みアンケートから、質問リストと回答結果リストを返す
	*/
	public List<BeanEnquete> getQAList(String enqID, int priNo) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement psQ = null, psA = null;
		ResultSet rsQ = null, rsA = null;

		List<BeanEnquete> qaList = new ArrayList<>();

		try {
			// 質問リストをDBから持ってくる
			sql = "SELECT * FROM enq_table WHERE id = ?";
			psQ = cone.prepareStatement(sql);
			psQ.setString(1, enqID);
			rsQ = psQ.executeQuery();

			// 回答結果リストをDBから持ってくる
//			sql = "SELECT * FROM test_ans WHERE id = ?";
			sql = "SELECT * FROM ans_table WHERE id = ?";
			psA = cone.prepareStatement(sql);
			psA.setInt(1, priNo);
			rsA = psA.executeQuery();

			rsQ.next(); rsA.next();

			for (int i = 1; i <= 5; i++) {
				qaList.add( new BeanEnquete(rsQ.getString("q" + i), rsA.getString("a" + i)) );
			}

			return qaList;
		} catch (SQLException e) {
			errMesSQL(e, "質問リストと回答結果リストを返す時のエラー");
			return null;
		}
		finally {
			jAppClose(psQ);
			jAppClose(rsQ);
			jAppClose(psA, rsA, cone);
		}
	}

	/**
	回答番号（priNo）を受け取って、その回答のアンケートのID（enqID）を返す
	*/
	public String getEnqID(int priNo) {
		// DBを使えるようにしている
		if(cone == null) useDB();

		// SQLを使う準備
		String sql = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// SQL文を指定して実行
//			sql = "SELECT * FROM test_ans WHERE id = ?";
			sql = "SELECT * FROM ans_table WHERE id = ?";
			ps = cone.prepareStatement(sql);
			ps.setInt(1, priNo);
			rs = ps.executeQuery();

			rs.next();

			return rs.getString("enq_id");
		} catch (SQLException e) {
			errMesSQL(e, "なんのアンケート(enqID)かを返す時のエラー");
			return null;
		}
		finally {
			jAppClose(ps, rs, cone);
		}
	}
}
