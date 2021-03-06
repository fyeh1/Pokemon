package PokeNames;

import main.Pokemon;

public class Pendragus extends Pokemon
{
	private static String name = "Pendragus";
	
	protected static int hpIV = 10; // health IV
	protected static int atkIV = 20; // attack IV
	protected static int defIV = 30; // defense IV
	protected static int speIV = 10; // speed IV

	public Pendragus(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}
}
