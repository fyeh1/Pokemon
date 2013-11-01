package Model;

public class Pokemon {
	protected String name;
	protected int level;
	protected int hp; // base health
	protected int atk; // base attack
	protected int def; // base defense
	protected int spe; // base speed
	protected int hpIV; // health IV
	protected int atkIV; // attack IV
	protected int defIV; // defense IV
	protected int speIV; // speed IV
	protected int hpcur; // current health
	protected int atkcur; // current attack
	protected int defcur; // current defense
	protected int specur; // current speed

	protected Move moves = new Move();

	protected int ID;

	static int counter = 0;

	public Pokemon(String species, int lvl, int hpInc, int atkInc, int defInc,
			int speInc) {
		name = species;
		level = lvl;

		// with a random factor
		hpIV = hpInc + (int) (Math.random() * 2) - (int) (Math.random() * 2);
		atkIV = atkInc + (int) (Math.random() * 2) - (int) (Math.random() * 2);
		defIV = defInc + (int) (Math.random() * 2) - (int) (Math.random() * 2);
		speIV = speInc + (int) (Math.random() * 2) - (int) (Math.random() * 2);

		// base + increase in stats due to levels
		hp = 0 + hpIV * lvl;
		hpcur = hp;
		atk = 0 + atkIV * lvl;
		def = 0 + defIV * lvl;
		spe = 0 + speIV * lvl;

	}

	public void levelUp() {
		level++;
		hp += hpIV;
		hpcur += hpIV;
		atk += atkIV;
		def += defIV;
		spe += speIV;
	}

	public boolean equals(Pokemon other) {
		return this.ID == other.ID;
	}

	public void heal() {
		hpcur = hp;
	}

	public boolean hit(Pokemon opponent, int move) {
		int[] attributes = Move.select(move);
		// calcs

		if (hpcur < 0) {
			return false;
		}
		return true;
	}

	public static int assignID() {
		counter++;
		return counter - 1;
	}
}
