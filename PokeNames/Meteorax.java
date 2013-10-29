package PokeNames;

import Model.Pokemon;

public class Meteorax extends Pokemon
{
	private static String name = "Meteorax";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 30; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Meteorax(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
