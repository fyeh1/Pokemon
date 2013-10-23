package PokeNames;

import Model.Pokemon;

public class Backumon extends Pokemon
{
	public Backumon()
	{
		super("Backumon");
	}
	
	public void levelUp()
	{
		super.levelUp();
		super.hpUp(1);
		super.atkUp(1);
		super.defUp(1);
		super.speUp(1);
	}
}
