package enquete.Java;

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
}
