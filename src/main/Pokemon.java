package main;

import java.awt.Image;
import java.awt.Toolkit;

import PokeNames.Backumon;
import PokeNames.Botstop;
import PokeNames.Boxtrot;
import PokeNames.Chronosaur;
import PokeNames.Continumon;
import PokeNames.Diamacard;
import PokeNames.Errormon;
import PokeNames.Eyelingual;
import PokeNames.Fieldgod;
import PokeNames.Fleetle;
import PokeNames.Handokua;
import PokeNames.Huongel;
import PokeNames.Meteorax;
import PokeNames.Mushlop;
import PokeNames.Nightrider;
import PokeNames.Okami;
import PokeNames.Pendragus;
import PokeNames.Porchap;
import PokeNames.Tiki;
import PokeNames.Towl;

public class Pokemon {
	protected String name;
	protected int level;
	protected int exp;
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

	public int waitturn = 0;

	protected Move moves = new Move();

	protected int ID;

	static int counter = 0;

	public Pokemon(String species, int lvl, int hpInc, int atkInc, int defInc, int speInc) {
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

	public void gainExp(int exp) {
		this.exp += exp;
	}

	public void levelUp() {
		level++;
		exp -= 100;
		hp += hpIV;
		hpcur += hpIV;
		atk += atkIV;
		def += defIV;
		spe += speIV;
	}

	public void reset() {
		atkcur = atk;
		defcur = def;
		specur = spe;
	}

	public boolean equals(Pokemon other) {
		return this.ID == other.ID;
	}

	public void heal() {
		hpcur = hp;
	}

	public void attack(Pokemon opponent, int move) {
		int[] attributes = Move.select(move);

		opponent.hit(this, move);

		hpcur += attributes[3];
		atkcur += attributes[4];
		defcur += attributes[5];
		specur += attributes[6];

		waitturn += attributes[8] - 1;
	}

	public boolean hit(Pokemon opponent, int move) {
		int[] attributes = Move.select(move);
		// calcs
		int hit = attributes[7];
		while (hit > 0) {
			int damage = calcDam(opponent, attributes);

			hpcur -= damage;

			String message = name + " is hit by " + opponent.name + "'s " + Move.name(attributes[0]) + " for a damage of " + damage + "!";

			System.out.println(message);

			hit--;

			if (hpcur < 0) {
				return false;
			}
		}
		return true;
	}

	private int calcDam(Pokemon opponent, int[] attributes) {
		int damage = 0;

		double critC = (5 + attributes[2]) / 100.0;
		boolean crit = Math.random() < critC;

		double randomFac = Math.random() * 5 - 5;

		if (!crit)
			damage = (int) (((opponent.atkcur * (1 + attributes[1] / 10.0) - defcur)) * ((100 - randomFac) / 100));
		else
			damage = (int) (((opponent.atkcur * (1 + attributes[1] / 10.0) - def)) * ((100 - randomFac) / 100)) * 2;

		if (damage < 0)
			damage = 0;

		return damage;
	}

	public static int assignID() {
		counter++;
		return counter - 1;
	}

	public String getName() {
		return name;
	}

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public int getCurrentHP() {
		return hpcur;
	}

	public int getHP() {
		return hp;
	}

	public Image getBackSprite() {
		return (Toolkit.getDefaultToolkit().createImage("images\\" + name + "_back.jpg"));
	}

	public Image getFrontSprite() {
		return (Toolkit.getDefaultToolkit().createImage("images\\" + name + ".jpg"));
	}

	public static Pokemon newPokemon(int lvl) {
		int lvlcurrent = lvl;

		int fac = (int) (Math.random() * 100);
		int facInc = 5;
		int facDet = fac;

		// each pokemon has 5% chance to appear

		if (fac < 5)
			return new Backumon(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 10
			return new Botstop(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 15
			return new Boxtrot(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 20
			return new Chronosaur(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 25
			return new Continumon(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 30
			return new Diamacard(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 35
			return new Errormon(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 40
			return new Eyelingual(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 45
			return new Fleetle(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 50
			return new Handokua(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 55
			return new Huongel(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 60
			return new Meteorax(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 65
			return new Mushlop(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 70
			return new Nightrider(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 75
			return new Okami(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 80
			return new Pendragus(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 85
			return new Porchap(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 90
			return new Tiki(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 95
			return new Towl(lvlcurrent);
		facDet += facInc;
		if (fac < facDet) // 100
			return new Fieldgod(lvlcurrent + 5);
		return null;
	}
}
