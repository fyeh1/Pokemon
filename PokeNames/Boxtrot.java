package PokeNames;

import Model.Pokemon;

public class Boxtrot extends Pokemon
{
	public Boxtrot()
	{
		super("Boxtrot");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(3);
		super.defUp(1);
		super.speUp(1);
	}
}
