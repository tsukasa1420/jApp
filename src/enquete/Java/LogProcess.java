package enquete.Java;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enquete.DAO.DAO_user;

/**
ログイン・ログアウト・アカウント作成を担うクラス
*/
public class LogProcess {
	/**
	ログイン処理
	*/
	public void inFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 入力された値を受け取る
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			// DBで入力値と一致するユーザー情報を探す
			DAO_user daoUser = new DAO_user();
			boolean judgeLogin = daoUser.login(userName, password);


//			/* ◆◆◆ */System.out.println("入名\t\t：" + userName + "<loginFunc>");
//			/* ◆◆◆ */System.out.println("入パス\t：" + password + "<loginFunc>");


			// ログイン判定
			if(judgeLogin == true) {
				// セッションを開始したことをセッションに格納している
				HttpSession session = request.getSession();
				session.setAttribute("ログイン", "できた");

				// ユーザー名をセッション格納してマイページで表示できるようにしている。
				session.setAttribute("userName", userName);

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/myPage.jsp");
				rd.forward(request, response);
			}
			else {
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">ユーザー名かパスワードが間違っています。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー（サーブレット面・loginFunc）");
		}
		catch (IOException e) {
			System.out.println("forward()のエラー（サーブレット面・loginFunc）");
		}
		catch (Exception e) {
			System.out.println("何かしらにエラー（loginFunc）");
		}
	}

	/**
	ログイン処理（アカウント作成時）
	*/
	public void inFunc( HttpServletRequest request, String userName ) {
		// セッションを開始したことをセッションに格納している
		HttpSession session = request.getSession();
		session.setAttribute("ログイン", "できた");

		// ユーザー名をセッション格納してマイページで表示できるようにしている。
		session.setAttribute("userName", userName);
	}

	/**
	ログアウト処理
	*/
	public void outFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);

			/* ◆◆◆ */System.out.println("ログアウト処理 " + session+ "<br>");

			if( session != null ) {
				session.invalidate();

				// ログアウトメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: blue;\">ログアウトしました。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー（サーブレット面・logoutFunc）");
		}
		catch (IOException e) {
			System.out.println("forward()のエラー（入出力面・logoutFunc）");
		}
	}
	/**
	ログアウト処理（アカウント削除時）
	*/
	public void outFunc(HttpServletRequest request, HttpServletResponse response, String message) {
		try {
			HttpSession session = request.getSession(false);
			if( session != null ) {
				session.invalidate();

				// ログアウトメッセージを表示させないための処理
				request.setAttribute("message", message);

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー（サーブレット面・logoutFunc）");
		}
		catch (IOException e) {
			System.out.println("forward()のエラー（入出力面・logoutFunc）");
		}
	}
}