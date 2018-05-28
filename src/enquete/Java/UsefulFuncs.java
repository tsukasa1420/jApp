package enquete.Java;

import javax.servlet.http.HttpServletRequest;

public class UsefulFuncs {
	/**
	パスワードを隠して表示するための処理
	*/
	public String hiddenPass(String pass) {
		String hiddenPass = "";
		for (int i = 0; i < pass.length(); i++) {
			hiddenPass += "●";
		}
		return hiddenPass;
	}

	/**
	入力文字がなかった場合にNULLを返す
		none Input Data = noneID
		戻り値が整数型の「noneID」の第1引数は何でもいい（オバーロード用）
	*/
	public int noneID(int integer, String data) {
		if( data.length() == 0 || data == null ) return 0;
		else return Integer.parseInt(data);
	}
	/**
	入力文字がなかった場合にNULLを返す
		none Input Data = noneID
	*/
	public String noneID(String data) {
		if( data.length() == 0 || data == null ) return null;
		else return data;
	}

	/**
	入力文字がなかった場合にNULLを返す
		Forward Error Message = f_EM
		引数にはエラーが発生しているクラスや関数を記述
	*/
	public void fw_EM(String errPlace) {
		System.out.println( "フォワードエラー : " + errPlace );
	}

	/**
	エラーメッセージをリクエストスコープに格納
		request Error Message
	*/
	public void req_EM(HttpServletRequest request, String message) {
		request.setAttribute("message", "<br><span style=\"color: red;\">" + message + "</span>");
	}

	/**
	ある処理の完了メッセージをリクエストスコープに格納
		request Finish Message
	*/
	public void req_FM(HttpServletRequest request, String message) {
		request.setAttribute("message", "<br><span style=\"color: blue;\">" + message + "</span>");
	}
}
