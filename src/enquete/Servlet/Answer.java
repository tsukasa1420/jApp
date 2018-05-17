package enquete.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.Enquete;

@WebServlet("/Answer")
public class Answer extends HttpServlet {
	/**
		アンケートのリンク用意したり、
		アンケートの回答を受け取ってDBへ格納するために動いている。
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 何の操作を受け取ったかを判定
		String ans = request.getParameter("ans");

		System.out.println(ans);


		Enquete enq = new Enquete();


		if( ans != null && ans.equals("check") ) {

			enq.checkProsess(request, response);
			System.out.println("チェック");
		}
		else {
			// アンケートに答えるページで質問を表示させている
			enq.getQuestion(request, response);
			System.out.println("回答");
		}

		out.println("アンケートページ正常終了");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}