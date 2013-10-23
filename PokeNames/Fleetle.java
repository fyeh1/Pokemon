package PokeNames;

import Model.Pokemon;

public class Fleetle extends Pokemon
{
	public Fleetle()
	{
		super("Fleetle");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(3);
		super.atkUp(2);
		super.defUp(1);
		super.speUp(1);
	}
}
