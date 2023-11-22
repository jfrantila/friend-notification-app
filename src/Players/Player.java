package Players;

import java.util.ArrayList;
import java.util.List;

import Broadcast.Update;

public class Player {
	private String name;
	private List<Player> friends = new ArrayList<Player>();
	private List<Update> updates = new ArrayList<Update>();
	
	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, List<Player> friends) {
		this.name = name;
		this.friends = friends;
	}
	
	public void deleteFriend(Player friendToDelete) {
		List<Player> copy = new ArrayList<Player>(friends.size());
		for (Player friend : friends) {
			if (!friend.getName().equals(friendToDelete.getName())){
				copy.add(friend);
			}
		}
		friends = copy;
	}
	
	public void addFriend(Player player) {
		friends.add(player);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Player> getFriends() {
		return friends;
	}

	public void setFriends(List<Player> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", friends=" + friends + "]";
	}

	public List<Update> getUpdates() {
		return updates;
	}

	public void setUpdates(List<Update> updates) {
		this.updates = updates;
	}
	
	public void addUpdate(Update update) {
		this.updates.add(update);
	}


	
	

}
