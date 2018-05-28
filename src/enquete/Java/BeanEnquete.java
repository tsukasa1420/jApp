package enquete.Java;

import java.io.Serializable;

public class BeanEnquete  implements Serializable{
	private String enqID;
	private String enqName;
	private String que;
	private String ans;

	private int priNo;
	private String enqURL;

	// 回答結果を全表示する際に利用する
	public BeanEnquete(int priNo, String enqName) {
		this.priNo = priNo;
		this.enqName = enqName;
	}

	// 回答結果を個別表示する際に利用する
	public BeanEnquete(String que, String ans) {
		this.que = que;
		this.ans = ans;
	}

	// マイページのアンケートリンク
	public BeanEnquete(String enqID, String enqName, String enqURL) {
		this.enqName = enqName;
		this.enqID = enqID;
		this.enqURL = enqURL;
	}

	public int getPriNo() {
		return priNo;
	}
	public String getEnqID() {
		return enqID;
	}
	public String getEnqName() {
		return enqName;
	}
	public String getEnqURL() {
		return enqURL;
	}
	public String getQue() {
		return que;
	}
	public String getAns() {
		return ans;
	}

	public void setPriNo(int priNo) {
		this.priNo = priNo;
	}
	public void setEnqID(String enqID) {
		this.enqID = enqID;
	}
	public void setEnqName(String enqName) {
		this.enqName = enqName;
	}
	public void setEnqURL(String enqURL) {
		this.enqURL = enqURL;
	}
	public void setQue(String que) {
		this.que = que;
	}
	public void setAns(String ans) {
		this.ans = ans;
	}
}