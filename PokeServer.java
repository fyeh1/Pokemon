import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PokeServer {

	private int port = 1214;

	/** Map of usernames to accounts */
	private HashMap<String, ServerThread> accounts;

	/** List of usernames */
	private ArrayList<String> users;

	/** Socket acceptor */
	private ServerSocket socketAcceptor;

	/** Max threads that are able to connect */
	private final static int MAX_THREADS = 2;

	/** Pool of Threads */
	private final ExecutorService pool;

	/** Main PokeRoom - temp */
	private PokeRoom PokeRoom;

	private int assignNumber = 1;

	/**
	 * Constructor Create a map & list of users Initialize socket listener
	 * Initialize thread pool executor Initialize PokeRoom Accept connections
	 * Close on error
	 */
	public PokeServer() {
		accounts = new HashMap<String, ServerThread>();
		users = new ArrayList<String>();

		PokeRoom = new PokeRoom(this, "Temp Name");

		String data = "Testing.";
		System.out.println("Initializing server.");

		try {
			socketAcceptor = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("socketAcceptor wrong");
			e.printStackTrace();
		}

		pool = Executors.newCachedThreadPool();

		acceptConnections(data);

		try {
			socketAcceptor.close();
		} catch (Exception e) {
		} finally {
			pool.shutdown();
		}
	}

	/**
	 * Accept connections
	 * 
	 * @param data
	 *            temp variable, will be removed, currently to send a message on
	 *            connect
	 */

	private void acceptConnections(String data) {
		while (socketAcceptor != null && !socketAcceptor.isClosed()) {
			try {
				Socket sock = socketAcceptor.accept();

				String username = "Guest" + " [" + assignNumber + "]";
				assignNumber++;
				users.add(username);
				ServerThread userThread = new ServerThread(username, "", sock,
						this);
				accounts.put(username, userThread);

				userThread.addRoom(PokeRoom);

				String num = " users";
				if (users.size() == 1)
					num = " user";

				PokeRoom.sendTo(
						"Welcome to the Three Musketeers' Chat Server! You are "
								+ username + ". There are " + users.size()
								+ num + " in the room.", username);
				PokeRoom.sendTo("Functions: \n\tChange username: /nick + space + username \n\tDisconnect: /disconnect or /disconnect + space + message \n\tMurmur to someone: /murmur + space + username + message \n\tChange your background color: /color + color (red, blue, green, yellow, or gray) \n\tMessage the PokeRoom anonymously: /anon + message \n\tMessage the same thing multiple times: /mult + # + message \n\n", username);
				PokeRoom.sendToAll(username + " has connected.");
				PokeRoom.sendToAll("/EnterSound");

				pool.execute(userThread);

			} catch (Exception e) {
				System.out.println("Something's wrong with connections");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Remove an account from the account list and map
	 * 
	 * @param acctName
	 *            : Account name, as String
	 * @param acct
	 *            : Account, as AccountHandler
	 */
	public synchronized void remove(String acctName, String message) {
		users.remove(acctName);
		accounts.remove(acctName);
		PokeRoom.removeUser(acctName);
		if (message.equals(""))
			PokeRoom.sendToAll(acctName + " has disconnected. ");
		else
			PokeRoom.sendToAll(acctName + " has disconnected. " + "(" + message
					+ ")");
	}

	public void remove(ServerThread st, String message) {
		this.remove(st.getAccName(), message);
	}

	/**
	 * @param name
	 *            username of account we need
	 * @return AccountHandler with username name
	 */
	public ServerThread getAcct(String name) {
		return accounts.get(name);
	}

	public void changeName(String original, String newname) {
		users.remove(original);
		users.add(newname);
		ServerThread tempThread = accounts.get(original);
		accounts.remove(original);
		accounts.put(newname, tempThread);
	}

}
