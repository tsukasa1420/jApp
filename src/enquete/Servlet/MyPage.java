package enquete.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.LogProcess;

/**
	ログイン機構等の処理を請け負う
*/
@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();

		LogProcess log = new LogProcess();

		// ログイン処理かログアウト処理かを取得し、判断する
		String logStr = request.getParameter("log");
		if( logStr.equals("out") ) log.outFunc(request, response);
		if( logStr.equals("in") ) log.inFunc(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
//+ "<br>"
//"<br>" +