package enquete.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.Result;

@WebServlet("/ResultOperate")
public class ResultOperate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Result re = new Result();

		// アンケート回答を閲覧する操作を受け取る。
		int priNo = 0;
		if( request.getParameter("priNo") == null ) priNo = 0;
		else priNo = Integer.parseInt( request.getParameter("priNo") );

		// 指定があれば個別表示
		// 指定なしの場合は、アンケート結果一覧表示
		if( priNo != 0 ) re.resultView(request, response, priNo);
		else re.resultViewAll(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
