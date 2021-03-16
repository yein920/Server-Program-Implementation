package vo;

public class Member {
	
	protected int user_no;
	protected String user_pw;
	protected String user_email;
	protected String user_pic;
	protected String user_nick;
	
	public int getUser_no() {
		return user_no;
	}
	public Member setUser_no(int user_no) {
		this.user_no = user_no;
		return this;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public Member setUser_pw(String user_pw) {
		this.user_pw = user_pw;
		return this;
	}
	public String getUser_email() {
		return user_email;
	}
	public Member setUser_email(String user_email) {
		this.user_email = user_email;
		return this;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public Member setUser_pic(String user_pic) {
		this.user_pic = user_pic;
		return this;
	}
	public String getUser_nick() {
		return user_nick;
	}
	public Member setUser_nick(String user_nick) {
		this.user_nick = user_nick;
		return this;
	}
}
