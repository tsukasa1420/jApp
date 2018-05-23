package enquete.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enquete.Java.User;

	/**
	ユーザー情報変更・削除
	*/
@WebServlet("/UserEdit")
public class UserEdit extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();

		String edit = request.getParameter("Edit");

		// 本人確認の処理を受け取る
		if( edit.equals("checkUser") ) {
			user.userSelfCheck(request, response);
		}

		// 変更処理を実行
		else if ( edit.equals("UPDATE") ) {
			user.userUpdateFunc(request, response);
		}

		// 削除処理を実行
		else if ( edit.equals("DELETE") ) {
			user.userDeleteFunc(request, response);
		}

		// 変更・削除処理を受け取る
		else {
			user.userGetOrder(request, response, edit);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}