package PokeNames;

import main.Pokemon;

public class Botstop extends Pokemon
{
	private static String name = "Botstop";
	
	protected static int hpIV = 30; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Botstop(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
