package enquete.Java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
	ユーザー情報を管理するクラス
*/
public class Login {
	/**
		ログイン処理
	*/
	public void loginFunc(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();

			// Get input data.
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			// Get DB data.
			DAO_user daoUser = new DAO_user();

			BeanUser mameUser =  daoUser.login(userName, password);

			out.println("入名\t\t：" + userName + "<br>");
			out.println("入パス\t：" + password + "<br>");

			out.println("DB名\t\t：" + mameUser.getUserName() + "<br>");
			out.println("DBパス\t：" + mameUser.getPassword() + "<br>");


			// login check PASS or BLOCK
			if(		userName.equals(mameUser.getUserName()) &&
					password.equals(mameUser.getPassword())
			/* if_finish */) {
				out.println("<span style=\"color: red;\">Login Success!</span>");

				// Make session. "session info" recod into session.
				HttpSession session = request.getSession();
				session.setAttribute("ログイン", "できた");

				// throw user_name to session.
				session.setAttribute("userName", userName);

				// jump jsp. and prepare.
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
				rd.forward(request, response);
			}
			else {
				out.println("<span style=\"color: blue;\">Login False...</span>");

				// throw user_name to session.
				request.setAttribute("message", "<br><span style=\"color: red;\">ユーザー名かパスワードが間違っています。</span>");

				// jump jsp. and prepare.
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (IOException e) {
			System.out.println("PrintWriterクラスのエラー<br>");
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー<br>");
		}
		catch (Exception e) {
			out.println("何かしらにエラー<br>");
		}

	}

	/**
		ログイン処理（アカウント作成時）
	*/
	public void loginFunc( HttpServletRequest request, String userName ) {
		// Make session. "session info" recod into session.
		HttpSession session = request.getSession();
		session.setAttribute("ログイン", "できた");

		// throw user_name to session.
		session.setAttribute("userName", userName);
	}
	/**
		ログアウト処理
	*/
	public void logoutFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();

			String log = request.getParameter("log");
			out.println(log+ "<br>");

			HttpSession session = request.getSession(false);
			out.println(session+ "<br>");
			if( session != null ) {
				session.invalidate();

				// throw user_name to session.
				request.setAttribute("message", "<br><span style=\"color: blue;\">ログアウトしました。</span>");

				// jump jsp. and prepare.
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (IOException e) {
			System.out.println("PrintWriterクラスのエラー");
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー");
		}
	}
	/**
		アカウント作成処理
	*/
	public void userMakeFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			out.println("check login.servlet.userMakeFunc()01<br>");

			String userName = null;
			String password = null;
			String mail = null;
			int birth = 0;


			// Get input datas. if input nothing.
			if(		request.getParameter("userName").length() == 0 ||
					request.getParameter("password").length() == 0 ||
					request.getParameter("mail").length() == 0 ||
					request.getParameter("birth").length() == 0
			/* if条件文ここまで */) {
				out.println("none data<br>");
				request.setAttribute("message", "<br><span style=\"color: red;\">未入力項目があります。</span>");

				// jump jsp. and prepare.
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMake.jsp");
				rd.forward(request, response);
			}
			else {
				userName	= request.getParameter("userName");
				password	= request.getParameter("password");
				mail			= request.getParameter("mail");
				birth		= Integer.parseInt( request.getParameter("birth") );
			}


			// test output
			out.println(userName+ "<br>");
			out.println(password+ "<br>");
			out.println(mail+ "<br>");
			out.println(birth+ "<br>");

			// Get DB data.
			DAO_user useDAO = new DAO_user();

			// insert SQL Run!
			useDAO.userMake(userName, password, mail, birth);

			out.println("チェック<br>");


			String hiddenPass = "";
			for (int i = 0; i < password.length(); i++) {
				hiddenPass += "●";
			}


			// アカウント作成完了画面に入るための準備
			request.setAttribute("userMakeFinish", "Finish");

			// アカウント作成完了画面で表示する項目の準備
			request.setAttribute("userName", userName);
			request.setAttribute("password", hiddenPass);
			request.setAttribute("mail", mail);
			request.setAttribute("birth", birth);


			// アカウント作成完了画面にとぶ。
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMakeFinish.jsp");
			rd.forward(request, response);

			// ログイン状態にする
			loginFunc(request, userName);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("PrintWriterクラスのエラー");
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー");
		}
	}
}