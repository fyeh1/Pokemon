package Model;

import java.util.ArrayList;

public class Trainer 
{
	private ArrayList<Pokemon> pokemon;
	
	public Trainer()
	{
		pokemon = new ArrayList<Pokemon>(6);
	}
	
	public Pokemon getPokemon(int i)
	{
		return pokemon.get(i);
	}
}
