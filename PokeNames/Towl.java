package PokeNames;

import Model.Pokemon;

public class Towl extends Pokemon
{
	public Towl()
	{
		super("Towl");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(2);
		super.defUp(1);
		super.speUp(3);
	}
}
