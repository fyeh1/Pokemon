package PokeNames;

import main.Pokemon;

public class Continumon extends Pokemon
{
	private static String name = "Continumon";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 30; // speed IV

	public Continumon(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
