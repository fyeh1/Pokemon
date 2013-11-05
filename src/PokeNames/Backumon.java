package PokeNames;

import main.Pokemon;

public class Backumon extends Pokemon {

	private static String name = "Backumon";

	protected static int hpIV = 10; // health IV
	protected static int atkIV = 10; // attack IV
	protected static int defIV = 10; // defense IV
	protected static int speIV = 10; // speed IV

	public Backumon(int lvl) {
		super(name, lvl, hpIV, atkIV, defIV, speIV);
	}

}
