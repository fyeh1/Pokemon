package Model;

public class Move {

	public static String name;
	public static int lvl;
	public static int atk;
	public static int cc;
	public static int hpC;
	public static int atkC;
	public static int defC;
	public static int speC;
	public static int hit;
	public static int turn;
	public static int[] attributes = new int[9];

	public Move() {
		reset();
	}

	private static void reset() {
		name = "";
		lvl = 0;
		atk = 0;
		cc = 0;
		hpC = 0;
		atkC = 0;
		defC = 0;
		speC = 0;
		hit = 1;
		turn = 1;
	}

	private static int[] update() {
		attributes[0] = lvl;
		attributes[1] = atk;
		attributes[2] = cc;
		attributes[3] = hpC;
		attributes[4] = atkC;
		attributes[5] = defC;
		attributes[6] = speC;
		attributes[7] = hit;
		attributes[8] = turn;
		reset();
		return attributes;
	}

	public static int[] select(int i) {
		if (i == 5)
			Flail();
		else if (i == 10)
			SteelBulk();
		else if (i == 15)
			TraumaCannon();
		else if (i == 20)
			SporeCannon();
		else if (i == 25)
			CrackRock();
		else if (i == 30)
			NorturnalScreen();
		else if (i == 35)
			FuryFire();
		else if (i == 40)
			CrouchSwipe();
		else if (i == 42)
			BlazeIt();
		else if (i == 45)
			FieldBlessing();
		else if (i == 50)
			MaryJane();
		else if (i == 55)
			GoogleThis();
		else if (i == 60)
			TimeCapsule();
		else if (i == 65)
			CrayonBlast();
		else if (i == 70)
			SwipeStakes();
		else if (i == 75)
			HangYang();
		else if (i == 80)
			FrightTrain();
		else if (i == 85)
			GodsDimension();
		else if (i == 90)
			GodsRage();
		else if (i == 95)
			GodsDisappointment();
		else if (i == 100)
			TheManfield();
		else
			reset();

		return update();
	}

	private static void Flail() {
		lvl = 5;
		atk = 1;
		cc = 10;
	}

	private static void SteelBulk() {
		lvl = 10;
		defC = statChange(2, 100);
	}

	private static void TraumaCannon() {
		lvl = 15;
		atk = 1;
		hit = hitRandom(1, 10);
	}

	private static void SporeCannon() {
		lvl = 20;
		atk = 3;
		hit = hitRandom(1, 3);
	}

	private static void CrackRock() {
		lvl = 25;
		atk = 2;
		cc = 50;
		defC = statChange(-2, 50);
	}

	private static void NorturnalScreen() {
		lvl = 30;
		atk = statChange(1, 20);
		defC = 3;
	}

	private static void FuryFire() {
		lvl = 35;
		atk = statChange(25, 25);
		hpC = statChange(Integer.MIN_VALUE, 50);
	}

	private static void CrouchSwipe() {
		lvl = 40;
		atk = 2;
		defC = 1;
		hit = hitRandom(2, 3);
	}

	private static void BlazeIt() {
		lvl = 42;
		defC = 3;
		speC = -1;
	}

	private static void FieldBlessing() {
		lvl = 45;
		atk = 5;
		defC = statChange(2, 25);
	}

	private static void MaryJane() {
		lvl = 50;
		atk = statChange(Integer.MAX_VALUE, 25);
		turn = 3;
	}

	private static void GoogleThis() {
		lvl = 55;
		atk = 4;
		defC = 1;
		speC = -4;
	}

	private static void TimeCapsule() {
		lvl = 60;
		speC = 2;
		defC = 2;
		hpC = -2;
	}

	private static void CrayonBlast() {
		lvl = 65;
		atk = hitRandom(4, 10);
	}

	private static void SwipeStakes() {
		lvl = 70;
		hpC = 7;
		atk = 2;
	}

	private static void HangYang() {
		lvl = 75;
		speC = 10;
	}

	private static void FrightTrain() {
		lvl = 80;
		atk = hitRandom(1, 20);
	}

	private static void GodsDimension() {
		lvl = 85;
		speC = -10;
		cc = statChange(Integer.MAX_VALUE, 50);
	}

	private static void GodsRage() {
		lvl = 90;
		cc = statChange(Integer.MAX_VALUE, 75);
		turn = 4;
	}

	private static void GodsDisappointment() {
		lvl = 95;
		atk = 3;
		defC = 3;
		turn = 2;
	}

	private static void TheManfield() {
		lvl = 100;
		cc = statChange(Integer.MIN_VALUE, 25);
		turn = 2;
	}

	private static int hitRandom(int lowerBound, int upperBound) {
		int hitc = (int) (Math.random() * (upperBound - lowerBound + 1) + lowerBound);
		return hitc;
	}

	private static int statChange(int value, int chance) {
		double c = (double) (chance / 100);
		double cc = Math.random();
		if (cc < c)
			return value;
		return 0;
	}
}
