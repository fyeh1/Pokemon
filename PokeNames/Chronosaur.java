package PokeNames;

import Model.Pokemon;

public class Chronosaur extends Pokemon
{
	private static String name = "Chronosaur";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 30; // defense IV
	protected static int speIV = 10; // speed IV

	public Chronosaur(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
