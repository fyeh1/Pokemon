package Model;
import java.util.ArrayList;
import java.util.Observable;



public class PokeModel extends Observable
{
	ArrayList <PokeThread> trainers = new ArrayList();
	PokeServer server = new PokeServer();
	int[][] map = new int[20][8];
	static long counter = 0;
	
	public PokeModel()
	{
		
	}
	
	public void attack(int playerOneID, int playerTwoID, int pokemon, int move)
	{
		trainers.get(playerOneID).getTrainer().getPokemon(pokemon).getMove(move);
	}
	
	public void heal(int playerID)
	{
		
	}
	
	public void move(int playerID)
	{
		
	}
}
