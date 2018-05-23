package enquete.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.Enquete;


@WebServlet("/EnqueteOperate")
public class EnqueteOperate extends HttpServlet {
	/**
	アンケート類の処理
		アンケートのリンク用意したり、
		アンケートの回答を受け取ってDBへ格納するために動いている。
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();

		// 何の操作を受け取ったかを判定
		String ans = request.getParameter("ans");


		Enquete enq = new Enquete();


		if (ans == null) {
			// アンケートに答えるページで質問を表示させている
			enq.getQuestion(request, response);
			System.out.println("NULLPro");
		}
		else if(ans.equals("check") ) {
			enq.checkProsess(request, response);
			System.out.println("チェック");
		}
		else if(ans.equals("send") ) {
			enq.setEnquete(request, response);
			System.out.println("送信済み");
		}
		else {
			// エラーメソッドを呼ぶ
			System.out.println("ERROR");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}