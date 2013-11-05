package PokeNames;

import main.Pokemon;

public class Fieldgod extends Pokemon
{
	private static String name = "Fieldgod";
	
	protected static int hpIV = 30; // health IV
	protected static int atkIV = 30; // attack IV
	protected static int defIV = 30; // defense IV
	protected static int speIV = 30; // speed IV

	public Fieldgod(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
