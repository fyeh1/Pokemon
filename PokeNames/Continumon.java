package PokeNames;

import Model.Pokemon;

public class Continumon extends Pokemon
{
	public Continumon()
	{
		super("Continumon");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(3);
	}
}
