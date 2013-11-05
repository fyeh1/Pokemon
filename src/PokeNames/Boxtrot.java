package PokeNames;

import main.Pokemon;

public class Boxtrot extends Pokemon
{
	private static String name = "Boxtrot";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 30; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Boxtrot(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
