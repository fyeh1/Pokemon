package Model;

public class Pokemon 
{
	protected String name;
	protected int level;
	protected int hp; //health
	protected int atk; //attack
	protected int def; //defense
	protected int spe; //speed
	protected long ID;
	
	public Pokemon()
	{
		this ("Pikachu");
	}
	
	public Pokemon(String species)
	{
		this (species, 0, 0, 0, 0, 0);
	}
	
	public Pokemon (String species, int lvl, int health, int attack, int defense, int speed)
	{
		name = species;
		level = lvl;
		hp = health;
		atk = attack;
		def = defense;
		spe = speed;
		ID = PokeModel.counter;
		PokeModel.counter++;
	}
	
	public void levelUp()
	{
		if (level<100)
			level++;
	}
	
	public boolean equals(Pokemon other)
	{
		return this.ID == other.ID;
	}
}
