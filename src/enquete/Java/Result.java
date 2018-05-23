package enquete.Java;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enquete.DAO.DAO_enquete;

/**
アンケート結果を表示させる。
*/
public class Result {
	public Result() {}
	public void resultViewAll(HttpServletRequest request, HttpServletResponse response) {
		DAO_enquete daoE = new DAO_enquete();
		HttpSession session = request.getSession(false);
		String userID = (String) session.getAttribute("userName");

		/* ◆◆◆ */System.out.println("Result.resultViewAll() Link OK.");
		List<BeanEnquete> beList = daoE.resultViewAll(userID);
//		for (BeanEnquete beanEnquete : beList) {
//			/* ◆◆◆ */System.out.println( "Result.resultViewAll().ID : " + beanEnquete.getPriNo() );
//			/* ◆◆◆ */System.out.println( "Result.resultViewAll().Enq : " + beanEnquete.getEnqID() );
//			/* ◆◆◆ */System.out.println();
//		}
		session.setAttribute("resultList", beList);

		try {
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/resultViewAll.jsp");
			rd.forward(request, response);
		}
		catch(IOException e) {
			/* ◆◆◆ */System.out.println("リザルトクラスのALLのフォワードエラーIO");
			e.printStackTrace();
		}
		catch (ServletException e) {
			/* ◆◆◆ */System.out.println("リザルトクラスのALLのフォワードエラーSer");
			e.printStackTrace();
		}
	}

	/**
	アンケート結果を表示させる。
	*/
	public void resultView(HttpServletRequest request, HttpServletResponse response, int priNo) {
		/* ◆◆◆ */System.out.println("リザルトクラスの個別ページ");

		try {
			DAO_enquete daoE = new DAO_enquete();
			List<String> aList = daoE.resultView_getAnswer(priNo);
			for (String string : aList) {
				/* ◆◆◆ */System.out.println(string);
			}
			/* ◆◆◆ */System.out.println();

			String enqID = daoE.resultView_getEnqID(priNo);
			/* ◆◆◆ */System.out.println(enqID);
			/* ◆◆◆ */System.out.println();
			List<String> qList = daoE.resultView_getQuestion(enqID);
			for (String string : qList) {
				/* ◆◆◆ */System.out.println(string);
			}
			/* ◆◆◆ */System.out.println();

			HttpSession session = request.getSession(false);
			session.setAttribute("res_qList", qList);

			for (int i = 1; i <= aList.size(); i++) {
				session.setAttribute( "res_aList" + i, aList.get(i - 1) );
			}

			// 指定のページに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/resultView.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			System.out.println("アンケート結果個別表示でフォワードエラー");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("アンケート結果個別表示でフォワードエラー");
			e.printStackTrace();
		}
	}
}
