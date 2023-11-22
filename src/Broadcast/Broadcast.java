package Broadcast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import Commands.UpdateCommand;
import Players.Player;

public class Broadcast {
	private List<Player> players = new ArrayList<Player>();
	private List<Update> broadcasts = new CopyOnWriteArrayList<Update>();
	
	public Broadcast() {
	}
	
	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (Player player : players) {
			names.add(player.getName());
		}
		return names;
	}
	
	public Player getPlayerWithName(String name) {
		for (Player player : players) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null; 
	}
	
	public void addPlayer(Player newPlayer) {
		players.add(newPlayer);
	}
	
	
	public List<Update> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(List<Update> broadcasts) {
		this.broadcasts = broadcasts;
	}

	/**
	 * Adds both players to each others friend-list. Don't add if their name is already there.
	 * @param name1 player 1
	 * @param name2 player 2
	 */
	public void makeFriends(String name1, String name2){
		Player player1 = getPlayerWithName(name1);
		Player player2 = getPlayerWithName(name2);
		String[] friends1 = getFriends(player1);
		String[] friends2 = getFriends(player2);
		
		if (!hasFriendAlready(friends1, name2)) {
			player1.addFriend(player2);			
		}
		if (!hasFriendAlready(friends2, name1)) {
			player2.addFriend(player1);			
		}
	}
	
	public boolean hasFriendAlready(String[] friends, String name){
		for (String friend : friends) {
			if (friend.equals(name)) return true;
		}
		return false;
	}
	
	public void addUpdate(UpdateCommand update) {
		Player updatedPlayer = getPlayerWithName(update.getUser());

		String[] friends = getFriends(updatedPlayer);
		boolean addToBroadCast = false;
		boolean addToPlayerList = false;
		
		Update copy = new Update(friends, updatedPlayer.getName(), update.getTimestamp());
		
		if (updatedPlayer.getUpdates().size() == 0) {
			copy.setValues(update.getValues());
			addToPlayerList = true;
			addToBroadCast = true;
		}
		else {
			for (Update prevUpdate : updatedPlayer.getUpdates()) {
				for(Map.Entry<String,String> entry : update.getValues().entrySet()) {
					if (prevUpdate.getValues().containsKey(entry.getKey()) && update.getTimestamp() <= prevUpdate.getTimestamp()) {
					//Skip: not broadcasting this value
						continue;
					} else {
						copy.addValue(entry);
						addToBroadCast = true;
						addToPlayerList = true;
						break;
					}
				}
			}			
		}
		
		if (addToPlayerList) {
			updatedPlayer.addUpdate(copy);
		}
		
		if (updatedPlayer.getFriends().size() > 0 && addToBroadCast) {
			broadcasts.add(copy);						
		}			
	}
	
	public String[] getFriends(Player player){
		List<Player> friends = player.getFriends();
		String[] names = new String[friends.size()];
		for (int i=0; i<friends.size();i++) {
			names[i] = friends.get(i).getName();
		}
		return names;
	}
	
	public void deleteFriends(String user1, String user2) {
		Player player1 = getPlayerWithName(user1);
		Player player2 = getPlayerWithName(user2);
		player1.deleteFriend(player2);
		player2.deleteFriend(player1);
	}
}
