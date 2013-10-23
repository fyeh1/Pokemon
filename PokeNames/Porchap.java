package PokeNames;

import Model.Pokemon;

public class Porchap extends Pokemon
{
	public Porchap()
	{
		super("Porchap");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(1);
		super.defUp(3);
		super.speUp(2);
	}
}
