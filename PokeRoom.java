import java.util.ArrayList;

public class PokeRoom {
	/** Handle to the server controller class */
	private PokeServer server;
	/**
	 * List of users in the chat room. This could have been a list of
	 * AccountHandlers we will use String
	 */
	private ArrayList<String> users;
	/** Chat room name */
	private String name;
	/** Chat room password - not used yet */
	private String pass;

	/**
	 * Constructor No empty constructor allowed
	 * 
	 * @param server
	 *            : handle to the main server
	 * @param name
	 *            : PokeRoom name
	 * @param users
	 *            : list of users to add
	 */
	private PokeRoom() {
	}

	public PokeRoom(PokeServer server, String name) {
		this.server = server;
		this.name = name;
		users = new ArrayList<String>();
	}

	public PokeRoom(PokeServer server, String name, ArrayList<String> users) {
		this(server, name);
		this.users = users;
	}

	/**
	 * Send a message to all users
	 * 
	 * @param msg
	 *            : message to send
	 */
	public void sendToAll(String msg) {
		// System.out.println("Users in room: " + users.size());
		for (Object u : users.toArray()) {
			sendTo(msg, (String) u);
		}
	}

	/**
	 * Send a message to one user
	 * 
	 * @param msg
	 *            : message to send
	 * @param user
	 *            : username to send to
	 */
	public void sendTo(String msg, String user) {
		server.getAcct(user).sendData(msg);
	}

	/**
	 * Add a user to the list if it doesn't already exist
	 * 
	 * @param user
	 *            : username
	 * @return true if added, false if already exists
	 */
	public boolean addUser(String user) {
		if (!users.contains(user)) {
			users.add(user);
			return true;
		}
		return false;
	}

	/**
	 * Remove a user from the list.
	 * 
	 * @param user
	 *            : username
	 * @return true if removed, false if it did not exist
	 */
	public boolean removeUser(String user) {
		return users.remove(user);
	}

	public void changeName(String original, String newname) {
		removeUser(original);
		addUser(newname);
	}
}