package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Broadcast.Broadcast;
import Broadcast.Update;
import Commands.UpdateCommand;
import Players.Player;

class BroadcastTests {
	private static Broadcast bc = new Broadcast();
	private static final String NAME1 = "Jade";
	private static final String NAME2 = "Janika";
	private static Player player1;
	private static Player player2;
	
	@BeforeAll
	static void setup() {
		player1 = new Player(NAME1);
		player2 = new Player(NAME2);
		bc.addPlayer(player1);
		bc.addPlayer(player2);
	}
	
	@Test
	void testGetNames_foundAll(){
		List<String> names = bc.getNames();
		assertEquals(NAME1, names.get(0));
		assertEquals(NAME2, names.get(1));
	}
	
	@Test
	void testGetNames_notFound() {
		Broadcast emptyBc = new Broadcast();
		List<String> emptyNames = emptyBc.getNames();
		assertEquals(0, emptyNames.size());
	}
	
	@Test
	void testGetPlayerByName_found() {
		Player found = bc.getPlayerWithName(NAME1);
		assertEquals(NAME1, found.getName());
	}
	
	@Test
	void testGetPlayerByName_notFound() {
		Player found = bc.getPlayerWithName("CZ");
		assertNull(found);
	}
	
	@Test
	void testMakeFriends() {
		bc.makeFriends(NAME1, NAME2);
		assertEquals(1, player1.getFriends().size());
		assertEquals(NAME2, player1.getFriends().get(0).getName());
		assertEquals(NAME1, player2.getFriends().get(0).getName());
	}
	
	@Test
	void testUpdate_player_notFound() {
		UpdateCommand updatecmnd = new UpdateCommand("update", "CZ", 10);
		Exception thrown = assertThrows(Exception.class, () -> {
			bc.addUpdate(updatecmnd);
		});
		assertEquals("Couldn't find a player with the name CZ", thrown.getMessage());
	}
	
	@Test
	void testUpdate_withoutFriends() throws Exception {
		bc.deleteFriends(NAME1, NAME2);
		UpdateCommand update = new UpdateCommand("update", NAME1, 10);
		bc.addUpdate(update);
		//Not added to the broadcast-list
		assertEquals(0, bc.getBroadcasts().size());
		//Still added to the users list
		assertEquals(10, player1.getUpdates().get(0).getTimestamp());
	}
	
	@Test
	void testUpdate_withDuplicatesAndSameValues() throws Exception {
		bc.makeFriends(NAME1, NAME2);
		Map<String,String> values = Map.of("foo", "bar");
		bc.setBroadcasts(new ArrayList<Update>());
		
		UpdateCommand update = new UpdateCommand("update", NAME2, 10, values);
		bc.addUpdate(update);
		UpdateCommand sameUpdate = new UpdateCommand("update", NAME2, 10, values);
		bc.addUpdate(sameUpdate);
		
		//Shouldn't add sameUpdate
		assertEquals(1, bc.getBroadcasts().size());
		
		UpdateCommand anotherUpdate = new UpdateCommand("update", NAME2, 20, values);

		bc.addUpdate(anotherUpdate);
		//Should add
		assertEquals(2, bc.getBroadcasts().size());
		
		UpdateCommand oldUpdate = new UpdateCommand("update", NAME2, 1, values);
		bc.addUpdate(oldUpdate);
		//Shouldn't add
		assertEquals(2, bc.getBroadcasts().size());
	}
}
