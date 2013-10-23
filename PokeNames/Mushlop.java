package PokeNames;

import Model.Pokemon;

public class Mushlop extends Pokemon
{
	public Mushlop()
	{
		super("Mushlop");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(3);
		super.defUp(2);
		super.speUp(1);
	}
}
