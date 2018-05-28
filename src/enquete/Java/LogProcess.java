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
		UsefulFuncs uf = new UsefulFuncs();

		try {
			// 入力された値を受け取る
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");

			// DBで入力値と一致するユーザー情報を探す
			DAO_user daoUser = new DAO_user();
			boolean judgeLogin = daoUser.login(userName, password);

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
				uf.req_EM(request, "ユーザー名かパスワードが間違っています。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			uf.fw_EM("サーブレット面・inFunc");
		}
		catch (IOException e) {
			uf.fw_EM("IO面・inFunc");
		}
		catch (Exception e) {
			System.out.println("何かしらにエラー（inFunc）");
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
		UsefulFuncs uf = new UsefulFuncs();

		try {
			HttpSession session = request.getSession(false);

			if( session != null ) {
				session.invalidate();

				// ログアウトメッセージをリクエストスコープに格納
				uf.req_FM(request, "ログアウトしました。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/login.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			uf.fw_EM("サーブレット面・outFunc");
		}
		catch (IOException e) {
			uf.fw_EM("入出力面・outFunc");
		}
	}
	/**
	ログアウト処理（アカウント削除時）
	*/
	public void outFunc(HttpServletRequest request, HttpServletResponse response, String message) {
		UsefulFuncs uf = new UsefulFuncs();

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
			uf.fw_EM("サーブレット面・outFunc");
		}
		catch (IOException e) {
			uf.fw_EM("入出力面・outFunc");
		}
	}
}