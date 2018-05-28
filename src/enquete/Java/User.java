package enquete.Java;

import java.io.IOException;

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
		UsefulFuncs uf = new UsefulFuncs();

		try {
			String userName = uf.noneID( request.getParameter("userName") );
			String password = uf.noneID( request.getParameter("password") );
			String mail = uf.noneID( request.getParameter("mail") );
			int birth = uf.noneID( 0, request.getParameter("birth") );

			// アカウント作成画面での入力が一つでも欠けていた場合の処理
			if(		userName == null		|| password == null ||
					mail == null			|| birth == 0
			/* if条件文ここまで */) {
				// エラーメッセージをリクエストスコープに格納
				uf.req_EM(request, "未入力項目があります。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMake.jsp");
				rd.forward(request, response);
			}

			// SQLでINSERT命令を実行・ユーザー名被りははじく
			DAO_user useDAO = new DAO_user();
			if( !useDAO.userMake(userName, password, mail, birth) ) {
				// エラーメッセージをリクエストスコープに格納
				uf.req_EM(request, "このユーザーIDは既に使われています。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userMake.jsp");
				rd.forward(request, response);
				return;
			}

			// アカウント作成完了画面に入るための準備
			request.setAttribute("userMakeFinish", "Finish");

			// アカウント作成完了画面で表示する項目の準備
			request.setAttribute("userName", userName);
			request.setAttribute("password", uf.hiddenPass(password));
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
			uf.fw_EM( "サーブレット面・userMakeFunc");
		}
		catch (IOException e) {
			uf.fw_EM( "入出力面・userMakeFunc");
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
		UsefulFuncs uf = new UsefulFuncs();
		try {
			HttpSession session = request.getSession(false);
			session.setAttribute("edit", edit);

			request.setAttribute("toUserUpdateCheck", "GO");

			// 指定のリンクに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/userUpdateCheck.jsp");
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			uf.fw_EM( "userEditFunc");
		}
	}

	/**
	ユーザーの本人確認
	*/
	public void userSelfCheck(HttpServletRequest request, HttpServletResponse response) {
		UsefulFuncs uf = new UsefulFuncs();
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
				uf.req_EM(request, "パスワードが間違っています。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}

			// ユーザーIDが間違ってる（パスワードは合ってる）
			else if( !userID.equals(userID_in) && daoU.login(userID, password_in) ){
				// エラーメッセージをリクエストスコープに格納
				uf.req_EM(request, "ユーザーIDが間違っています。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
			else {
				// エラーメッセージをリクエストスコープに格納
				uf.req_EM(request, "ユーザー名かパスワードが間違っています。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		}
		catch (ServletException e) {
			uf.fw_EM( "S・userSelfCheck");
			e.printStackTrace();
		}
		catch (IOException e) {
			uf.fw_EM( "IO・userSelfCheck");
			e.printStackTrace();
		}
	}

	/**
	ユーザー情報変更
		UPDATE命令を使う。
	*/
	public void userUpdateFunc(HttpServletRequest request, HttpServletResponse response) {
		UsefulFuncs uf = new UsefulFuncs();
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
				// エラーメッセージをリクエストスコープに格納
				uf.req_EM(request, "新しく入力されたデータに誤りがありました。<br>もう一度やり直してください。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		} catch (ServletException e) {
			uf.fw_EM( "User.userUpdateFunc()・S" );
			e.printStackTrace();
		} catch (IOException e) {
			uf.fw_EM( "User.userUpdateFunc()・IO" );
			e.printStackTrace();
		}
	}

	/**
	アカウント削除
		DELETE命令を使う。
	*/
	public void userDeleteFunc(HttpServletRequest request, HttpServletResponse response) {
		UsefulFuncs uf = new UsefulFuncs();
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
				uf.req_EM(request, "何らかのエラーが発生しました。");

				// 指定のリンクに飛ぶ
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/userInfo.jsp");
				rd.forward(request, response);
			}
		} catch (ServletException e) {
			uf.fw_EM( "User.userUpdateFunc()・S" );
			e.printStackTrace();
		} catch (IOException e) {
			uf.fw_EM( "User.userUpdateFunc()・IO" );
			e.printStackTrace();
		}
	}
}