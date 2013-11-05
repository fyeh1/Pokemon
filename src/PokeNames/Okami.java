package PokeNames;

import main.Pokemon;

public class Okami extends Pokemon
{
	private static String name = "Okami";
	
	protected static int hpIV = 20; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 30; // defense IV
	protected static int speIV = 10; // speed IV

	public Okami(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
