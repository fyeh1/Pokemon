package PokeNames;

import Model.Pokemon;

public class Diamacard extends Pokemon
{
	public Diamacard()
	{
		super("Diamacard");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(2);
		super.defUp(1);
		super.speUp(1);
	}
}
