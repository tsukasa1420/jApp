package enquete.Java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enquete.DAO.DAO_enquete;
/*
	＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
	｜主　　	｜アンケ名	｜Q1			｜Q2			｜Q3			｜Q4			｜Q5			｜
	＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
	｜food	｜食べ物系	｜肉好き？	｜魚好き？	｜野菜好き？	｜チョコ好き？	｜ミント好き？	｜
	｜anim	｜動物系		｜犬好き？	｜猫好き？	｜ウサギ好き？	｜鳥好き？	｜魚好き？	｜
	｜		｜			｜			｜			｜			｜			｜			｜
	＋―――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋―――――	＋
	【 food, 食べ物系, /jApp/Answer?jump=food 】
*/
public class Enquete {
	// JSPで呼び出すときにコンストラクタがないと怒られる
	public Enquete() {}

	/**
	マイページで回答できるアンケート一覧を表示させる。
	*/
	public void table_list(HttpServletRequest request) {
		// DBから持ってきた、ID、アンケート名、URLを受け取っている。
		DAO_enquete daoE = new DAO_enquete();
		List<BeanEnquete> beList = daoE.getEnqName();

		// DBからとってきたアンケート一覧を表示させる。
		HttpSession session = request.getSession(false);
		session.setAttribute("enquete_list", beList);
	}

	/**
	マイページ内のアンケートリンクを踏んだら実行される処理。
		アンケートの質問をDBからとってくる。
	*/
	public void getQuestion(HttpServletRequest request, HttpServletResponse response, String pri){
		UsefulFuncs uf = new UsefulFuncs();
		try {
			// DBから質問一覧を取得
			DAO_enquete daoE = new DAO_enquete();
			List<String> qList =  daoE.getQuestion(pri);

			// セッションに質問リストを格納（回答格納の為に主キーも）
			HttpSession session = request.getSession(false);
			session.setAttribute("qList", qList);
			session.setAttribute("pri", pri);

			// アンケート回答画面に入るための準備
			request.setAttribute("answerEnquete", "Start");

			// 指定のページに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/enquete.jsp");
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			uf.fw_EM( "getQuestion");
		}
	}

	/**
	アンケートの回答確認ページに行く
	*/
	public void checkProsess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// セッションスコープから質問数を取得
		HttpSession session = request.getSession(false);
		int qNum = (int)session.getAttribute("qNum");

		for (int i = 1; i <= qNum; i++) {
			session.setAttribute( "qAnswer" + i, request.getParameter("qAnswer" + i) );
		}

		// 指定のページに飛ぶ
		request.setAttribute("checkEnquete", "Start");
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteCheck.jsp");
		rd.forward(request, response);
	}

	/**
	アンケートの回答確認ページで送信ボタンを押した場合の処理
	*/
	public void setEnquete(HttpServletRequest request, HttpServletResponse response){
		UsefulFuncs uf = new UsefulFuncs();
		HttpSession session = request.getSession(false);
		DAO_enquete daoE = new DAO_enquete();

		try {
			// セッションを利用して、質問数（qNum）・回答（ansList）を取得している。
			int qNum = (int)session.getAttribute("qNum");

			List<String> ansList = new ArrayList<>();
			for (int i = 1; i <= qNum; i++) {
				ansList.add( (String)session.getAttribute("qAnswer" + i) );
			}

			// DBに格納する際の情報の取得
			String pri = (String) session.getAttribute("pri");
			String userName = (String) session.getAttribute("userName");

			// DBに格納
			daoE.addAnswer( userName, pri, ansList );

			// 指定のページに飛ぶ
			request.setAttribute("finishEnquete", "Finish");
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteFinish.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) {
			System.out.println( "" );
			uf.fw_EM( "setEnquete" );
		}
		catch (IOException e) {
			uf.fw_EM( "setEnquete" );
		}
	}

	/**
	アンケートの回答確認ページで送信ボタンを押した場合の処理
	*/
	public void enqueteMake(HttpServletRequest request, HttpServletResponse response){
		DAO_enquete daoE = new DAO_enquete();
		UsefulFuncs uf = new UsefulFuncs();

		String enqID = uf.noneID( request.getParameter("enqID") );
		String enqName = uf.noneID( request.getParameter("enqName") );
		List<String> qList = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			qList.add( uf.noneID(request.getParameter( "q" + i )) );
		}

		try {
			// 正常終了
			if( daoE.enqueteMake_DB(enqID, enqName, qList) ) {
				request.setAttribute("finishEnqueteMake", "move");
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteMakeFinish.jsp");
				rd.forward(request, response);
			}
			// 入力値が無い、DBエラーなど
			else{
				uf.req_EM(request, "入力エラーが起きました。<br>もう一度入力してください。");
				RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteMake.jsp");
				rd.forward(request, response);
			}

		} catch (ServletException e) {
			uf.fw_EM("アンケートクラス、アンケート作成関数");
			e.printStackTrace();
		} catch (IOException e) {
			uf.fw_EM("アンケートクラス、アンケート作成関数");
			e.printStackTrace();
		}
	}
}