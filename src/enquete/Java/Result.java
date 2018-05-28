package enquete.Java;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enquete.DAO.DAO_enquete;
import enquete.DAO.DAO_result;

/**
アンケート結果を全表示させる。
*/
public class Result {
	public Result() {}
	public void resultViewAll(HttpServletRequest request, HttpServletResponse response) {
		UsefulFuncs uf = new UsefulFuncs();
		DAO_enquete daoE = new DAO_enquete();
		HttpSession session = request.getSession(false);

		String userID = (String) session.getAttribute("userName");
		List<BeanEnquete> beList = daoE.resultViewAll(userID);
		session.setAttribute("resultList", beList);

		try {
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/resultViewAll.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			uf.fw_EM("サーブレット面・resultViewAll");
			e.printStackTrace();
		}
		catch(IOException e) {
			uf.fw_EM("入出力面・resultViewAll");
			e.printStackTrace();
		}
	}

	/**
	アンケート結果を個別表示させる。
	*/
	public void resultView(HttpServletRequest request, HttpServletResponse response, int priNo) {
		UsefulFuncs uf = new UsefulFuncs();
		DAO_result daoR = new DAO_result();
		String enqID = daoR.getEnqID(priNo);

		try {
			// 対応する質問と回答がそれぞれ5つずつ入ったリストをJSPで使えるようにしている。
			List<BeanEnquete> qaList = daoR.getQAList(enqID, priNo);
			request.setAttribute("qaList", qaList);

			// 指定のページに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/resultView.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			uf.fw_EM("サーブレット面・resultView");
			e.printStackTrace();
		}
		catch(IOException e) {
			uf.fw_EM("入出力面・resultView");
			e.printStackTrace();
		}
	}
}