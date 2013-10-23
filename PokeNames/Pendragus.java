package PokeNames;

import Model.Pokemon;

public class Pendragus extends Pokemon
{
	public Pendragus()
	{
		super("Pendragus");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(2);
		super.defUp(3);
		super.speUp(1);
	}
}
