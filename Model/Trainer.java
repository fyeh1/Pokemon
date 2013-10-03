package Model;

import java.util.ArrayList;

public class Trainer 
{
	private ArrayList<Pokemon> pokemon;
	
	public Trainer()
	{
		pokemon = new ArrayList<Pokemon>(6);
		pokemon.add(new Pokemon());
	}
	
	public Pokemon getPokemon(int i)
	{
		return pokemon.get(i);
	}
}
