package PokeNames;

import main.Pokemon;

public class Handokua extends Pokemon
{
	private static String name = "Handokua";
	
	protected static int hpIV = 30; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 20; // defense IV
	protected static int speIV = 10; // speed IV

	public Handokua(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
