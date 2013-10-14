package Model;

import java.net.Socket;
import java.util.Observable;

public class PokeModel extends Observable {
	PokeServer server = new PokeServer();
	int[][] map = new int[20][8];
	static long counter = 0;

	// socket for connection to chat server
	private Socket socket;

	// for writing to and reading from the server
	private Out out;
	private In in;

	public PokeModel(String hostName, String port) {
		// connect to server
		try {
			socket = new Socket(hostName, Integer.parseInt(port));
			out = new Out(socket);
			in = new In(socket);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void listen() {
		String s;
		while ((s = in.readLine()) != null) {
			
		}
		out.close();
		in.close();
		try {
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println("Closed client socket");
	}

	public void challenge(int playerOneID, int playerTwoID) {
		server.challenge(playerOneID, playerTwoID);
	}

	public void attack(int playerOneID, int playerTwoID, int pokemon, int move) {
		server.attack(playerOneID, playerTwoID, pokemon, move);
	}

	public void hit(int playerOneID, int playerTwoID, int pokemon, int move) {
		server.hit(playerOneID, playerTwoID, pokemon, move);
	}

	public void heal(int playerID) {
		server.heal(playerID);
	}

	public void move(int playerID) {
		server.move(playerID);
	}
}
