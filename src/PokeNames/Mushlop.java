package PokeNames;

import main.Pokemon;

public class Mushlop extends Pokemon
{
	private static String name = "Mushlop";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 30; // attack IV
	protected static int defIV = 20; // defense IV
	protected static int speIV = 10; // speed IV

	public Mushlop(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
