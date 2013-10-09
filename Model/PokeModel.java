package Model;
import java.util.ArrayList;
import java.util.Observable;



public class PokeModel extends Observable
{
	PokeServer server = new PokeServer();
	int[][] map = new int[20][8];
	static long counter = 0;
	
	public PokeModel()
	{
		
	}
	
	public void attack(int playerOneID, int playerTwoID, int pokemon, int move)
	{
		server.attack(playerOneID, playerTwoID, pokemon, move);
	}
	
	public void hit(int playerOneID, int playerTwoID, int pokemon, int move)
	{
		server.hit(playerOneID, playerTwoID, pokemon, move);
	}
	
	public void heal(int playerID)
	{
		server.heal(playerID);
	}
	
	public void move(int playerID)
	{
		server.move(playerID);
	}
}
