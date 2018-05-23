package enquete.Java;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enquete.DAO.DAO_user;

/**
	ユーザー情報を管理するクラス
*/
public class User {
	public User() {}

	/**
	アカウント作成処理
		INSERT命令を使う。
	*/
	public void userMakeFunc(HttpServletRequest request, HttpServletResponse response) {
		try {
			/* ◆◆◆ */PrintWriter out = response.getWriter();
			/* ◆◆◆ */out.println("check Login(servlet).userMakeFunc()01<br>");

			String userName = null;
			String password = null;
			String mail = null;
			int birth = 0;

			// アカウント作成画面での入力が一つでも欠けていた場合の処理
			if(		request.getParameter("userName").length() == 0 || request.getParameter("userName") == null ||
					request.getParameter("password").length() == 0 || request.getParameter("password") == null ||
					request.getParameter("mail").length() == 0 || request.getParameter("mail") == null ||
					request.getParameter("birth").length() == 0 || request.getParameter("birth") == null
			/* if条件文ここまで */) {
				/* ◆◆◆ */out.println("none data<br>");

				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">未入力項目があります。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMake.jsp");
				rd.forward(request, response);
			}
			else {
				userName	= request.getParameter("userName");
				password	= request.getParameter("password");
				mail			= request.getParameter("mail");
				birth		= Integer.parseInt( request.getParameter("birth") );
			}

			/* ◆◆◆ */out.println(userName+ "<br>");
			/* ◆◆◆ */out.println(password+ "<br>");
			/* ◆◆◆ */out.println(mail+ "<br>");
			/* ◆◆◆ */out.println(birth+ "<br>");

			// SQLでINSERT命令を実行・ユーザー名被りははじく
			DAO_user useDAO = new DAO_user();
			if( !useDAO.userMake(userName, password, mail, birth) ) {
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">このユーザーIDは既に使われています</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMake.jsp");
				rd.forward(request, response);
				return;
			}

			/* ◆◆◆ */out.println("チェック<br>");

			// パスワードを隠して表示するための処理
			UsefulFuncs uf = new UsefulFuncs();
			String hiddenPass = uf.hiddenPass(password);

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
			LogProcess log = new LogProcess();
			log.inFunc(request, userName);
		}
		catch (ServletException e) {
			System.out.println("forward()のエラー（サーブレット面・userMakeFunc）");
		}
		catch (IOException e) {
			System.out.println("PrintWriterクラスのエラー");
			System.out.println("forward()のエラー（入出力面・userMakeFunc）");
		}
	}

	/**
	ユーザー情報表示
		DAOでユーザー情報を取得
	*/
	public BeanUser userInfoFunc(HttpServletRequest request) {
		HttpSession session = request.getSession();
		DAO_user daoU = new DAO_user();
		UsefulFuncs uf = new UsefulFuncs();

		BeanUser beanU = daoU.userInfoFunc( (String)session.getAttribute("userName") );

		String name	=  beanU.getUserName();
		String pass	= uf.hiddenPass(beanU.getPassword());
		String mail	= beanU.getMail();
		String birth	= beanU.getBirth();

		return new BeanUser(name, pass, mail, birth);
	}

	/**
	指示受け取り処理
		デリートなのか、それぞれのユーザー情報のアップデート処理なのかを記憶して本人確認ページに飛ぶ
	*/
	public void userGetOrder(HttpServletRequest request, HttpServletResponse response, String edit) {
		try {
			HttpSession session = request.getSession(false);
			session.setAttribute("edit", edit);

			request.setAttribute("toUserUpdateCheck", "GO");

			// 指定のリンクに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/userUpdateCheck.jsp");
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			System.out.println("エラーuserEditFunc");
		}
	}

	/**
	ユーザーの本人確認
	*/
	public void userSelfCheck(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String userID = (String) session.getAttribute("userName");
		String userID_in = request.getParameter("userID");
		String password_in = request.getParameter("password");

		try {
			DAO_user daoU = new DAO_user();

			// ログイン成功
			if( userID.equals(userID_in) && daoU.login(userID, password_in) ) {
				request.setAttribute("toUserUpdate", "GO");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userUpdate.jsp");
				rd.forward(request, response);
			}

			// ユーザーIDだけ合ってる（パスワードが間違ってる）
			else if( userID.equals(userID_in) && !daoU.login(userID, password_in) ) {
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">パスワードが間違っています。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}

			// ユーザーIDが間違ってる（パスワードは合ってる）
			else if( !userID.equals(userID_in) && daoU.login(userID, password_in) ){
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">ユーザーIDが間違っています。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
			else {
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">ユーザー名かパスワードが間違っています。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			System.out.println("forwardエラー・S・userSelfCheck");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("forwardエラー・IO・userSelfCheck");
			e.printStackTrace();
		}
	}

	/**
	ユーザー情報変更
		UPDATE命令を使う。
	*/
	public void userUpdateFunc(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		DAO_user daoU = new DAO_user();

		// 現在利用中のユーザーID、変更したいユーザー情報、新しく入力された情報を受け取っている
		String id = (String) session.getAttribute("userName");
		String edit = (String) session.getAttribute("edit");
		String newData = request.getParameter("newData");


		try {
			// DB格納が成功したら、userInfoに飛ばす。
			if ( daoU.userInfoUpdate(id, edit, newData) ) {
				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
			else {
				/* ◆◆◆ */System.out.println( "User.userUpdateFunc()でDBのエラー" );
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">新しく入力されたデータに誤りがありました。<br>もう一度やり直してください。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		} catch (ServletException e) {
			/* ◆◆◆ */System.out.println( "User.userUpdateFunc()でフォワードのエラーS" );
			e.printStackTrace();
		} catch (IOException e) {
			/* ◆◆◆ */System.out.println( "User.userUpdateFunc()でフォワードのエラーIO" );
			e.printStackTrace();
		}
	}

	/**
	アカウント削除
		DELETE命令を使う。
	*/
	public void userDeleteFunc(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		DAO_user daoU = new DAO_user();

		// 現在利用中のユーザーIDを受け取る
		String id = (String) session.getAttribute("userName");

		try {
			// DBレコード削除が成功したら、userInfoに飛ばす。
			if ( daoU.userInfoDelete(id) ) {
				// アカウント削除後、ログアウトしてトップページ（ログインページ）へ
				LogProcess log = new LogProcess();
				log.outFunc(request, response, "");
			}
			else {
				/* ◆◆◆ */System.out.println( "User.userDeleteFunc()でDBのエラー" );
				// エラーメッセージをリクエストスコープに格納
				request.setAttribute("message", "<br><span style=\"color: red;\">何らかのエラーが発生しました。</span>");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		} catch (ServletException e) {
			/* ◆◆◆ */System.out.println( "User.userUpdateFunc()でフォワードのエラーS" );
			e.printStackTrace();
		} catch (IOException e) {
			/* ◆◆◆ */System.out.println( "User.userUpdateFunc()でフォワードのエラーIO" );
			e.printStackTrace();
		}
	}
}