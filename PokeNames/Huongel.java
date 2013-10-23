package PokeNames;

import Model.Pokemon;

public class Huongel extends Pokemon
{
	public Huongel()
	{
		super("Huongel");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(3);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(2);
	}
}
