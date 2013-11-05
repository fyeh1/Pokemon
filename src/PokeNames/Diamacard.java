package PokeNames;

import main.Pokemon;

public class Diamacard extends Pokemon
{
	private static String name = "Diamacard";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 20; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Diamacard(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
