package Broadcast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Update {
	private String[] broadcast;
	private String user;
	private int timestamp;
	private Map<String,String> values = new HashMap<String, String>();
	
	
	public Update(String[] broadcast, String user, int timestamp) {
		this.broadcast = broadcast;
		this.user = user;
		this.timestamp = timestamp;
	}
	
	public String[] getBroadcast() {
		return broadcast;
	}
	public void setBroadcast(String[] broadcast) {
		this.broadcast = broadcast;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public Map<String,String> getValues() {
		return values;
	}
	public void setValues(Map<String, String> values) {
		this.values = values;
	}
	
	public void addValue(Map.Entry<String,String> entry) {
		this.values.put(entry.getKey(), entry.getValue());
	}

	@Override
	public String toString() {
		return "BroadcastObject [broadcast=" + Arrays.toString(broadcast) + ", user=" + user + ", timestamp="
				+ timestamp + ", values=" + values.toString() + "]";
	}
}
