package Model;

import java.net.Socket;
import java.util.Observable;

import Server.PokeServer;

public class PokeModel extends Observable {

	int[][] map = new int[20][8];
	// 0 = nothing 1 = player 2 = other players 3 = healer 4 = trainer
	// grass is not assigned a number

	int xCor;
	int yCor;
	int dir = 1; // 1 = East 2 = South 3 = West 4 = North

	Pokemon[] owned = new Pokemon[3];
	int activePoke = 0; // the one pokemon currently active
	int useableDrug = 3;

	boolean inBattle = false;

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
			int[] command = Helper.separate(s);
			if (command[1] == 0) {
				sync();
			} else if (command[1] == 1) {
				challenge(command);
			} else if (command[1] == 2) {
				attack(command);
			} else if (command[1] == 3) {
				hit(command);
			} else if (command[1] == 4) {
				heal(command);
			}
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

	private void heal(int[] command) {
		owned[activePoke].heal();
		useableDrug--;
		healAnimation(); //view stuff
	}

	private void hit(int[] command) {
		owned[activePoke].hit(command[1]);
		hitAnimation(); //view stuff
	}

	private void attack(int[] command) {
		attackAnimation(); //view stuff
	}

	private void challenge(int[] command) {
		enterBattle(); //view stuff
		inBattle = true;
	}
	
	private void dodge(int[] command) {
		dodgeAnimation(); //view stuff
	}

	public void challengeRequest() {
		out.println("1," + xCor + "," + yCor + "," + dir);
	}

	public void attackRequest(int pokemon, int move) {
		out.println("2," + pokemon + "," + move);
	}

	public void hitRequest(int pokemon, int move) {
		out.println("3," + "," + pokemon + "," + move);
	}

	public void healRequest() {
		if (useableDrug > 0)
			out.println("4");
		else {
			rejectHeal();
		}
	}
	
	public void dodgeRequest() {
		out.println("5");
	}

	public void move(int direction) {
		int xNew = xCor;
		int yNew = yCor;
		if (direction == dir) {
			if (dir == 1)
				xNew++;
			else if (dir == 2)
				yNew--;
			else if (dir == 3)
				xNew--;
			else if (dir == 4)
				yNew++;
		} else {
			dir = direction;
		}
		if (xNew >= 20 || yNew >= 8 || xNew < 0 || yNew < 0) {
			xNew = xCor;
			yNew = yCor;
		}
		xCor = xNew;
		yCor = yNew;
		out.println("5," + xCor + "," + yCor + "," + dir);
	}

	public void sync(int[][] serverMap) {
		for (int x = 0; x < map.length; x++)
			for (int y = 0; y < map[0].length; y++)
				map[x][y] = serverMap[x][y];

	}
}
