package Commands;

public class MakeFriendsCommand {
	private String type;
	private String user1;
	private String user2;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MakeFriendsCommand() {
	}
	public String getUser1Name() {
		return user1;
	}
	public void setUser1Name(String user1Name) {
		this.user1 = user1Name;
	}
	public String getUser2Name() {
		return user2;
	}
	public void setUser2Name(String user2Name) {
		this.user2 = user2Name;
	}
	@Override
	public String toString() {
		return "MakeFriendsCommand [type=" + type + ", user1=" + user1 + ", user2=" + user2 + "]";
	}
	

	
}
