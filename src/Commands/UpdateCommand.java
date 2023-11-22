package Commands;

import java.util.Map;

public class UpdateCommand {
	private String type;
	private String user;
	private int timestamp;
	private Map<String, String> values;
	
	public UpdateCommand(String type, String user, int timestamp) {
		this.type = type;
		this.user = user;
		this.timestamp = timestamp;
	}
	
	public UpdateCommand(String type, String user, int timestamp, Map<String, String> values) {
		this.type = type;
		this.user = user;
		this.timestamp = timestamp;
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public String getUser() {
		return user;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public Map<String, String> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "UpdateCommand [type=" + type + ", user=" + user + ", timestamp=" + timestamp + ", values=" + values
				+ "]";
	}



}
