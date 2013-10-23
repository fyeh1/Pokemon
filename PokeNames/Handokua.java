package PokeNames;

import Model.Pokemon;

public class Handokua extends Pokemon
{
	public Handokua()
	{
		super("Handokua");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(3);
		super.atkUp(1);
		super.defUp(2);
		super.speUp(1);
	}
}
