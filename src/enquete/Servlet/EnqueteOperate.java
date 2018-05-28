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
		request.setCharacterEncoding("UTF-8");
		Enquete enq = new Enquete();

		// 何の操作を受け取ったかを判定
		String enqOpe = request.getParameter("enq");

		if (enqOpe == null) {
			// エラーメソッドを呼ぶ
			System.out.println("ERROR - enqOpe");
		}

		// 回答の確認
		else if( enqOpe.equals("check") ) {
			enq.checkProsess(request, response);
		}
		else if( enqOpe.equals("send") ) {
			enq.setEnquete(request, response);
		}
		else if( enqOpe.equals("make") ) {
			enq.enqueteMake(request, response);
		}
		// アンケート回答ページで質問を表示させる
		else {
			enq.getQuestion(request, response, enqOpe);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}