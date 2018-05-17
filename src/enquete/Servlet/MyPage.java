package enquete.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.Login;

/**
	ログイン機構等の処理を請け負う
*/
@WebServlet("/MyPage")
public class MyPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();

		Login useL = new Login();

		// ログイン処理かログアウト処理かを取得し、判断する
		String log = request.getParameter("log");
		if( log.equals("out") )useL.logoutFunc(request, response);
		if( log.equals("in") ) useL.loginFunc(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
//+ "<br>"
//"<br>" +