package PokeNames;

import Model.Pokemon;

public class Porchap extends Pokemon
{
	private static String name = "Porchap";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 30; // defense IV
	protected static int speIV = 20; // speed IV

	public Porchap(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
