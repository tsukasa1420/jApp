package enquete.Java;

import java.io.Serializable;

public class BeanEnquete  implements Serializable{
	private String enqID;
	private String enqName;
	private String enqURL;
	private String q1, q2, q3, q4, q5;

	private int priNo;

	// 回答結果を全表示する際に利用する
	public BeanEnquete(int priNo, String enqID) {
		this.priNo = priNo;
		this.enqID = enqID;
	}

	//
	public BeanEnquete(String enqID, String nameEnq, String enqURL) {
		this.enqName = nameEnq;
		this.enqID = enqID;
		this.enqURL = enqURL;
	}

	//
	public BeanEnquete(String enqName, String q1, String q2, String q3, String q4, String q5) {
		this.enqName = enqName;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
		this.q5 = q5;
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
	public String getQ1() {
		return q1;
	}
	public String getQ2() {
		return q2;
	}
	public String getQ3() {
		return q3;
	}
	public String getQ4() {
		return q4;
	}
	public String getQ5() {
		return q5;
	}


	public void setPriNo(int priNo) {
		this.priNo = priNo;
	}
	public void setEnqID(String enqID) {
		this.enqID = enqID;
	}
	public void setEnqName(String nameEnq) {
		this.enqName = nameEnq;
	}
	public void setEnqURL(String enqURL) {
		this.enqURL = enqURL;
	}
	public void setQ1(String q1) {
		this.q1 = q1;
	}
	public void setQ2(String q2) {
		this.q2 = q2;
	}
	public void setQ3(String q3) {
		this.q3 = q3;
	}
	public void setQ4(String q4) {
		this.q4 = q4;
	}
	public void setQ5(String q5) {
		this.q5 = q5;
	}
}