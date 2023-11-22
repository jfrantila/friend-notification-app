import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

import Broadcast.Broadcast;
import Commands.MakeFriendsCommand;
import Commands.UpdateCommand;
import Players.Player;
import io.ReadInput;
import io.WriteOutput;

public class FriendNotification {
	private static Logger LOGGER = Logger.getLogger(FriendNotification.class.getName());
	private static List<String> input = new ArrayList<>();
	private static Broadcast broadcast;

	public static void main(String[] args) { 
		init();
		if (args.length > 0) {
			String fileName = args[0];
			LOGGER.info((String.format("Starting the app with the inputfile: %s", fileName)));
			
			input = ReadInput.readFile(fileName);
			if (input.size() > 0) {
				goThroughCommands(input);
			}
			WriteOutput.writeOutput(broadcast.getBroadcasts());
		}
		else LOGGER.log(Level.WARNING, "Necessary filename is missing.");
	}
	
	/**
	 * Go through every input line and execute proper actions regarding
	 * @param input list of input lines
	 * @throws Exception if player not found
	 */
	public static void goThroughCommands(List<String> input) {
		Gson g = new Gson();
		for (String line : input) {
			if (line.contains("make_friends")) {
				MakeFriendsCommand friends = g.fromJson(line, MakeFriendsCommand.class);
				broadcast.makeFriends(friends.getUser1Name(), friends.getUser2Name());
			}
			if (line.contains("update")) {
				UpdateCommand update = g.fromJson(line, UpdateCommand.class);
				broadcast.addUpdate(update);
			}
			if (line.contains("del_friends")) {
				MakeFriendsCommand unFriend = g.fromJson(line, MakeFriendsCommand.class);
				broadcast.deleteFriends(unFriend.getUser1Name(), unFriend.getUser2Name());
			}
		}
	}
	
	/**
	 * Initialize the app with three players
	 */
	private static void init() {
		broadcast = new Broadcast();
		Player ab = new Player("ab");
		Player cd = new Player("cd");
		Player ef = new Player("ef");
		broadcast.addPlayer(ab); broadcast.addPlayer(cd); broadcast.addPlayer(ef);
	}

}
