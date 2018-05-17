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
		List<DumBean> dbList = table_list_dum();


		// DBからとってきた
		HttpSession session = request.getSession(false);
		session.setAttribute("enquete_list", dbList);
	}
	protected List<DumBean> table_list_dum() {
		// DBから持ってきた体で
		List<DumBean> dbList = new ArrayList<>();
		String pri, url = "/jApp/Answer?jump=";
		pri = "food";
		dbList.add( new DumBean(pri, "食べ物アンケート", url + pri) );

		pri = "anml";
		dbList.add( new DumBean(pri, "動物アンケート", url + pri) );

		pri = "food2";
		dbList.add( new DumBean(pri, "ごはんアンケート", url + pri) );

		return dbList;
	}

	/**
		マイページ内のアンケートリンクを踏んだら実行される処理。
		アンケートの質問をDBからとってくる。
	*/
	public void getQuestion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();

		String pri = request.getParameter("jump");

		// DBのデータを格納したリスト
		List<String> dbList = dumDB();
		request.setAttribute("qList", dbList);

		// 指定のページに飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/answer.jsp");
		rd.forward(request, response);
	}
	protected List<String> dumDB(){
		// DBから持ってきた体で
		List<String> list = new ArrayList<String>();
		list.add("Q1<br>好きな食べ物は？");
		list.add("Q2<br>嫌いな食べ物は？");
		list.add("Q3<br>最後の晩餐は？");
		list.add("Q4<br>料理はする？");
		list.add("Q5<br>得意料理はあああああああああああああああああああああああああああああああああああああああああああああああ？");
		return list;
	}

	/**
		アンケートの回答確認ページに行く
	*/
	public void checkProsess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// 指定のページに飛ぶ
		RequestDispatcher rd = request.getRequestDispatcher("/enquete/answerCheck.jsp");
		rd.forward(request, response);
	}
}