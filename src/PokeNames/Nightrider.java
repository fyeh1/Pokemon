package PokeNames;

import main.Pokemon;

public class Nightrider extends Pokemon
{
	private static String name = "Nightrider";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 30; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 20; // speed IV

	public Nightrider(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
