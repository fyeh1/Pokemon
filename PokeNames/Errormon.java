package PokeNames;

import Model.Pokemon;

public class Errormon extends Pokemon
{
	public Errormon()
	{
		super("Errorman");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(1);
		super.defUp(2);
		super.speUp(1);
	}
}
