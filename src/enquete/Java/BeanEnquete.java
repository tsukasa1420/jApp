package enquete.Java;

import java.io.Serializable;

public class BeanEnquete  implements Serializable{













	private String EnqId;
	private String nameEnq;
	private String q1, q2, q3, q4, q5;


	public BeanEnquete(String enqId, String nameEnq) {
		this.nameEnq = nameEnq;
		EnqId = enqId;
	}


	public BeanEnquete(String nameEnq, String q1, String q2, String q3, String q4, String q5) {
		this.nameEnq = nameEnq;
		this.q1 = q1;
		this.q2 = q2;
		this.q3 = q3;
		this.q4 = q4;
		this.q5 = q5;
	}


	public String getEnqId() {
		return EnqId;
	}
	public String getNameEnq() {
		return nameEnq;
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


	public void setEnqId(String enqId) {
		EnqId = enqId;
	}
	public void setNameEnq(String nameEnq) {
		this.nameEnq = nameEnq;
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