package PokeNames;

import Model.Pokemon;

public class Tiki extends Pokemon
{
	private static String name = "Tiki";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 30; // speed IV

	public Tiki(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
