package boomzy.entity;

public class UserBought {
	private String username;
	private String tno;

	public UserBought(String username, String tno) {
		this.username = username;
		this.tno = tno;
	}
	
	public UserBought(String username) {
		this.username = username;
	}
	public UserBought() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

}
