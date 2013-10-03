package Model;

public class Pokemon 
{
	private String name;
	private int level;
	private int atk; //attack
	private int def; //defense
	private int spa; //special attack
	private int spd; //special defense
	private int spe; //speed
	private long ID;
	
	public Pokemon()
	{
		this ("Pikachu");
	}
	
	public Pokemon(String species)
	{
		name = species;
		level = 5;
		ID = PokeModel.counter;
		PokeModel.counter++;
	}
	
	public boolean equals(Pokemon other)
	{
		return this.ID == other.ID;
	}
}
