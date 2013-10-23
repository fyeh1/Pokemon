package PokeNames;

import Model.Pokemon;

public class Nightrider extends Pokemon
{
	public Nightrider()
	{
		super("Nightrider");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(3);
		super.defUp(1);
		super.speUp(2);
	}
}
