package PokeNames;

import Model.Pokemon;

public class Fieldgod extends Pokemon
{
	public Fieldgod()
	{
		super("Fieldgod");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(3);
		super.atkUp(3);
		super.defUp(3);
		super.speUp(3);
	}
}
