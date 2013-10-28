package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

	private int port = 1214;

	/** Map of usernames to accounts */
	private HashMap<Integer, PokeThread> accounts;

	/** List of usernames */
	private ArrayList<Integer> users;

	/** Socket acceptor */
	private ServerSocket socketAcceptor;

	/** Max threads that are able to connect, not used */
	private final static int MAX_THREADS = 2;

	/** Pool of Threads */
	private final ExecutorService pool;

	/** Main PokeRoom **/
	private PokeRoom PokeRoom;

	private int assignNumber = 1;

	/**
	 * Constructor Create a map & list of users Initialize socket listener
	 * Initialize thread pool executor Initialize PokeRoom Accept connections
	 * Close on error
	 */
	public Server() {
		accounts = new HashMap<Integer, PokeThread>();
		users = new ArrayList<Integer>();

		PokeRoom = new PokeRoom(this, "Main Room");

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

				PokeThread userThread = new PokeThread(assignNumber, "", sock, this);
				accounts.put(assignNumber, userThread);
				assignNumber++;
				
				userThread.addRoom(PokeRoom);

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
	public synchronized void remove(Integer acctID) {
		users.remove(Integer.valueOf(acctID));
		accounts.remove(Integer.valueOf(acctID));
		PokeRoom.removeUser(Integer.valueOf(acctID));
	}

	/**
	 * @param name
	 *            username of account we need
	 * @return AccountHandler with username name
	 */
	public PokeThread getAcct(Integer ID) {
		return accounts.get(ID);
	}
}
