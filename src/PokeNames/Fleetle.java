package PokeNames;

import main.Pokemon;

public class Fleetle extends Pokemon
{
	private static String name = "Fleetle";
	
	protected static int hpIV = 30; // health IV
	protected static int atkIV = 20; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Fleetle(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
