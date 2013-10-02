import java.util.ArrayList;
import java.util.Observable;


public class PokeModel extends Observable
{
	ArrayList <PokeThread> threads = new ArrayList();
	PokeServer server = new PokeServer();
}
