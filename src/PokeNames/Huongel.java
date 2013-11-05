package PokeNames;

import main.Pokemon;

public class Huongel extends Pokemon
{
	private static String name = "Huongel";
	
	protected static int hpIV = 30; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 20; // speed IV

	public Huongel(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
