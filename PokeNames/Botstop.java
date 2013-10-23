package PokeNames;

import Model.Pokemon;

public class Botstop extends Pokemon
{
	public Botstop()
	{
		super("Botstop");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(3);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(1);
	}
}
