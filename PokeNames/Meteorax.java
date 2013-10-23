package PokeNames;

import Model.Pokemon;

public class Meteorax extends Pokemon
{
	public Meteorax()
	{
		super("Meteorax");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(3);
		super.defUp(1);
		super.speUp(1);
	}
}
