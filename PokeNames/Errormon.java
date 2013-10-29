package PokeNames;

import Model.Pokemon;

public class Errormon extends Pokemon
{
	private static String name = "Errormon";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 20; // defense IV
	protected static int speIV = 10; // speed IV

	public Errormon(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
