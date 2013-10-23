package PokeNames;

import Model.Pokemon;

public class Eyelingual extends Pokemon
{
	public Eyelingual()
	{
		super("Eyelingual");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(2);
	}
}
