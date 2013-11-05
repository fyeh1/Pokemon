package PokeNames;

import main.Pokemon;

public class Eyelingual extends Pokemon
{
	private static String name = "Eyelingual";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 20; // speed IV

	public Eyelingual(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
