package enquete.Java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		// ここをDB化
		List<BeanDum> dbList = table_list_dum();


		// DBからとってきた
		HttpSession session = request.getSession(false);
		session.setAttribute("enquete_list", dbList);
	}
	protected List<BeanDum> table_list_dum() {
		// DBから持ってきた体で
		List<BeanDum> dbList = new ArrayList<>();
		String pri, url = "/jApp/EnqueteOperate?jump=";
		pri = "food";
		dbList.add( new BeanDum(pri, "食べ物アンケート", url + pri) );

		pri = "anml";
		dbList.add( new BeanDum(pri, "動物アンケート", url + pri) );

		pri = "food2";
		dbList.add( new BeanDum(pri, "ごはんアンケート", url + pri) );

		return dbList;
	}

	/**
		マイページ内のアンケートリンクを踏んだら実行される処理。
		アンケートの質問をDBからとってくる。
	*/
	public void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		PrintWriter out = response.getWriter();

		String pri = request.getParameter("jump");

		// DBのデータを格納したリスト
		List<String> dbList = dumDB(pri);
		HttpSession session = request.getSession(false);
		session.setAttribute("qList", dbList);

		// 指定のページに飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/enquete.jsp");
		rd.forward(request, response);
	}
	protected List<String> dumDB(String pri){
		String[] quest = new String[5];

		if( pri.equals("food") ) {
			quest[0] = "好きな食べ物は？";
			quest[1] = "嫌いな食べ物は？";
			quest[2] = "最後の晩餐は？";
			quest[3] = "料理はする？";
			quest[4] = "得意料理はあああああああああああああああああああああああああああああああああああああああああああああああ？";
		}
		else if( pri.equals("anml") ) {
			quest[0] = "犬好き？";
			quest[1] = "猫大好き？";
			quest[2] = "ウサギ好き？";
			quest[3] = "鳥好き？";
			quest[4] = "アメリカ原産で日本の侵略的外来種の選ばれるミシシッピアカミミガメ好き？";
		}
		else {
			for (int i = 0; i < quest.length; i++)  quest[i] = "エラー" + i;
		}
		// DBから持ってきた体で
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < quest.length; i++) list.add( quest[i] );
		return list;
	}

	/**
		アンケートの回答確認ページに行く
	*/
	public void checkProsess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		int qNum = (int)session.getAttribute("qNum");


		System.out.println(qNum);

//		List<String> answers = new ArrayList<>();
		for (int i = 1; i <= qNum; i++) {
			session.setAttribute( "qAnswer" + i, request.getParameter("qAnswer" + i) );
			System.out.println( "# " + request.getParameter("qAnswer" + i) );
		}




		// 指定のページに飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteCheck.jsp");
		rd.forward(request, response);
	}


	/**
		アンケートの回答確認ページでOKした場合の処理
	*/
	public void setEnquete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			PrintWriter out =  response.getWriter();

			// セッションを利用して、質問数（qNum）・回答（ansList）を取得している。
			HttpSession session = request.getSession(false);
			int qNum = (int)session.getAttribute("qNum");
			List<String> ansList = new ArrayList<>();
			for (int i = 0; i < qNum; i++) {
				ansList.add( request.getParameter("qAnswer" + i) );
			}
			for (String string : ansList) {
				System.out.println("% : " + string);
			}

			// DBに格納する処理




			// 指定のページに飛ぶ
			RequestDispatcher rd = request.getRequestDispatcher("/enquete/enqueteFinish.jsp");
			rd.forward(request, response);
		} catch (IOException e) {
			System.out.println( "" );
			System.out.println( "プリントライターエラー" );
		}
		catch (ServletException e) {
			System.out.println( "フォワードエラー" );
		}
	}

}