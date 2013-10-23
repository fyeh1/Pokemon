package PokeNames;

import Model.Pokemon;

public class Chronosaur extends Pokemon
{
	public Chronosaur()
	{
		super("Chronosaur");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(1);
		super.defUp(3);
		super.speUp(1);
	}
}
