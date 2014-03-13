package org.willprice.scotlandyard.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.List;

import org.willprice.scotlandyard.gamelogic.GameState;
import org.willprice.scotlandyard.gamelogic.player.Detective;
import org.willprice.scotlandyard.gamelogic.player.MrX;
import org.willprice.scotlandyard.gamelogic.player.Player;

import com.thoughtworks.xstream.XStream;

public class PersistentStore {
	private GameState state;

	public PersistentStore(GameState state) {
		this.state = state;
	}

	public Boolean loadGame(GameState state, String filename) {
		return loadGame(filename);
	}

	@SuppressWarnings("unchecked")
	public Boolean loadGame(String filename) {
		XStream xstream = new XStream();
		try {
			FileReader reader = new FileReader(filename);
			ObjectInputStream in = xstream.createObjectInputStream(reader);
			state.setDetectives((List<Detective>) in.readObject());
			state.setMrX((MrX) in.readObject());
			state.setMrXIdList((List<Integer>) in.readObject());
			state.setPlayers((List<Player>) in.readObject());
			state.setNumberOfDetectives((Integer) in.readObject());
			readPreviousPlayerIdAsCurrentPlayer(in);
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Could not load save game file");
			return false;
		}
		return true;
	}

	private void readPreviousPlayerIdAsCurrentPlayer(ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		state.setCurrentPlayerId((int) in.readObject() - 1);
	}

	public Boolean saveGame(GameState state, String filename) {
		return saveGame(filename);
	}

	public Boolean saveGame(String filename) {
		XStream xstream = new XStream();
		try {
			Writer writer = new FileWriter(filename);
			ObjectOutputStream out = xstream.createObjectOutputStream(writer);
			out.writeObject(state.getDetectives());
			out.writeObject(state.getMrX());
			out.writeObject(state.getMrXIdList());
			out.writeObject(state.getPlayers());
			out.writeObject(state.getNumberOfDetectives());
			out.writeObject(state.getCurrentPlayerId());
			out.close();
		} catch (Exception e) {
			System.err.println("Could not write save game file");
			return false;
		}
		return true;
	}

}