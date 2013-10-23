package PokeNames;

import Model.Pokemon;

public class Okami extends Pokemon
{
	public Okami()
	{
		super("Okami");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(1);
		super.defUp(3);
		super.speUp(1);
	}
}
