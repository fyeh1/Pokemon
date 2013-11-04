package Model;

import java.util.ArrayList;
import java.util.Observable;

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

public class PokeModel extends Observable {

	int[][] map = new int[20][8];
	// 0 = nothing 1 = player 2 = other players 3 = healer 4 = trainer
	// grass is not assigned a number

	int xCor;
	int yCor;
	int dir = 3; // 1 = East 2 = South 3 = West 4 = North

	ArrayList<Pokemon> allPokes = new ArrayList<Pokemon>();

	Pokemon[] owned = new Pokemon[3];
	int activePoke = 0; // the one pokemon currently active
	int useableDrug = 3;

	boolean inBattle = false;

	public PokeModel() {

		map[16][6] = 3;
		map[16][2] = 4;

		xCor = 16;
		yCor = 4;
		map[xCor][yCor] = 1;

	}

	private void attack() {
		// view stuff
	}

	private boolean heal() {
		if (useableDrug <= 0)
			return false;
		owned[activePoke].heal();
		useableDrug--;
		return true;
		// view stuff
	}

	private synchronized void hit(int opponentID, int move) {
		owned[activePoke].hit(allPokes.get(opponentID), move);
		// view stuff
	}
	
	private boolean dodge(){
		double chance = Math.random();
		if(chance<0.5)
			return true;
		return false;
	}

	private void wildEncounter() {
		// view stuff
		initializeBattle();
		allPokes.add(newWildPokemon());
	}

	private void initializeBattle() {
		inBattle = true;

	}

	private void win() {
		inBattle = false;
	}

	public void move(int direction) {
		int xNew = xCor;
		int yNew = yCor;
		if (direction == dir) {
			if (dir == 1)
				xNew++;
			else if (dir == 2)
				yNew--;
			else if (dir == 3)
				xNew--;
			else if (dir == 4)
				yNew++;
		} else {
			dir = direction;
		}
		if (xNew >= 20 || yNew >= 8 || xNew < 0 || yNew < 0) {
			xNew = xCor;
			yNew = yCor;
		}
		xCor = xNew;
		yCor = yNew;
	}

	public Pokemon newWildPokemon() {

		int lvlcurrent = owned[0].level + (int) (Math.random() * 2)
				- (int) (Math.random() * 2);

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

	public int action() {
		if(inBattle == false)
			return speak();
		else
			return 0;
	}
	
	private int speak()
	{
		int xNew = xCor;
		int yNew = yCor;
			
		if (dir == 1)
			xNew++;
		else if (dir == 2)
			yNew--;
		else if (dir == 3)
			xNew--;
		else if (dir == 4)
			yNew++;
		
		if(xNew == 16 && yNew == 6)
			return 1;
		else if(xNew == 16 && yNew == 2)
			return 2;
		
		return 0;
	}

	public int cancel() {
		
		
		
		return 0;
	}
}
