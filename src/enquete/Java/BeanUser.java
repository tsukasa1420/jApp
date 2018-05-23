package enquete.Java;

import java.io.Serializable;

public class BeanUser implements Serializable{
	private String userName;
	private String password;
	private String mail;
	private String birth;

	public BeanUser() {}

	public BeanUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public BeanUser(String userName, String password, String mail, String birth) {
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.birth = birth;
	}


	public String getMail() {
		return mail;
	}
	public String getBirth() {
		return birth;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}