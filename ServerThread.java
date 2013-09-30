import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerThread implements Runnable {

	/** Username */
	private String name;

	/** Password */
	private String pass;

	/** Socket to the client */
	private Socket sock;

	/** Output stream */
	private DataOutputStream output;

	/** Buffer of messages to send */
	private ArrayList<String> messages;

	/** Handle to the server controller */
	private PokeServer server;

	/** Running or closed, Running by default, closed when finished/disconnected */
	private boolean running;

	/** Input stream reader as BufferedReader */
	private BufferedReader input;

	/** Closed or not */
	private boolean closed;

	/** List of PokeRooms */
	private ArrayList<PokeRoom> PokeRooms;

	/** No empty account handlers */
	private ServerThread() {
	}

	/** Color of text */
	private Color userTextColor = null;

	/** Initializing */
	public ServerThread(String name, String pass, Socket s, PokeServer server) {

		messages = new ArrayList<String>();
		PokeRooms = new ArrayList<PokeRoom>();
		closed = false;
		this.name = name;
		this.pass = pass;
		this.sock = s;
		this.server = server;

		System.out.println("Server has connected to " + name);

		try {
			output = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			close();
			System.out.println("Can't get outpustream");
			e.printStackTrace();
		}
	}

	/**
	 * does what is needed (current: sends messages) closes when done
	 */
	public void run() {
		running = true;
		while (running) {

			write();

			try {
				input = new BufferedReader(new InputStreamReader(
						sock.getInputStream()));
				String line = "";
				while ((line = input.readLine()) != null) {
					System.out.print("Receiving: '");
					System.out.print(line); // Read one line and output it
					System.out.println("'");

				}
			} catch (SocketException e) {
				System.out.println("SocketException. Means bad socket close.");
				close();
			} catch (Exception e) {
				e.printStackTrace();
				close();
			}
		}
		if (!closed) {
			close();
		}
	}

	private void write() {
		while (messages.size() > 0) {
			sendData(messages.remove(0));
		}
	}

	/**
	 * @param data
	 *            : Add data to the buffer
	 */

	public void addData(String data) {
		messages.add(data);
	}

	/**
	 * @param data
	 *            : Send data into the stream
	 */

	public synchronized void sendData(String data) {
		if (output == null || running == false) {
			messages.add(data);
			return;
		}
		try {
			System.out.println("Sending " + name + " '" + data + "'");
			output.writeBytes(data + "\n");
		} catch (IOException e) {
			System.out.println("Can't write bytes");
			e.printStackTrace();
			close();
		}
	}

	/**
	 * Close the account thread. Close all streams and the socket and request to
	 * be removed.
	 */
	private void close() {
		this.close("");
	}

	private void close(String message) {
		setRunning(false);
		for (int i = 0; i < PokeRooms.size(); i++) {
			PokeRooms.get(i).sendToAll("/LeaveSound");
		}
		try {
			System.out.println("Closing socket to " + name);
			output.close();
			input.close();
			sock.close();
		} catch (IOException e) {
			System.out.println("Error closing datastream");
			e.printStackTrace();
		} finally {
			server.remove(this, message);
		}
		closed = true;

	}

	/** Set running or not */
	public void setRunning(boolean running) {
		this.running = running;
	}

	/** Add a PokeRoom, add this to the PokeRoom */
	public void addRoom(PokeRoom room) {
		PokeRooms.add(room);
		room.addUser(name);
	}

	/** Remove a PokeRoom, remove this from room */
	public void removeRoom(PokeRoom room) {
		PokeRooms.remove(room);
		room.removeUser(name);
	}

	/** Accessor methods - get name, password, socket */
	public String getAccName() {
		return name;
	}

	public String getPass() {
		return pass;
	}

	public Socket getSock() {
		return sock;
	}

	/** String representation */
	public String toString() {
		return name + ", " + pass;
	}
}
