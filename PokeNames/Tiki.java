package PokeNames;

import Model.Pokemon;

public class Tiki extends Pokemon
{
	public Tiki()
	{
		super("Tiki");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(2);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(3);
	}
}
