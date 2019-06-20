package boomzy.entity;
//用于登陆、注册
public class User {
	private String username;
	private String password;
	private int money;
	public User() {

	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(String username, int money) {
		super();
		this.username = username;
		this.money = money;
	}
	
	public User(String username, String password, int money) {
		super();
		this.username = username;
		this.password = password;
		this.money = money;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	
	
	
	
	
}
