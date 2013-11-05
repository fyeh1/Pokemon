package PokeNames;

import main.Pokemon;

public class Towl extends Pokemon
{
	private static String name = "Towl";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 20; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 30; // speed IV

	public Towl(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
