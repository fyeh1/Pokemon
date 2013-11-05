package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;


public class BattleScene {

	private PokeMain game;
	private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);
	private Random r = new Random();

	public boolean playerTurn;
	public int elapsedTurns;
	public boolean inMain = true;
	public boolean inFight = false;
	public boolean inItem = false;
	public boolean inPokemon = false;
	public boolean inRun = false;
	public boolean playerWon = false;
	public boolean pokemonfainted = false;
	public boolean confirmBattleEnd = false;
	public int currentSelectionMainX;
	public int currentSelectionMainY;

	public int currentSelectionFight;

	public Pokemon playerPokemon;
	public Pokemon enemyPokemon;

	private Image BG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/BG.png"));
	private Image battleMainBG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Battle.png"));
	private Image battleFightBG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Battle2.png"));
	private Image arrow = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Arrow.png"));

	private Image statusPAR = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusPAR.png"));
	private Image statusBRN = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusBRN.png"));
	private Image statusPSN = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusPSN.png"));
	private Image statusSLP = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusSLP.png"));
	private Image statusFRZ = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/StatusFRZ.png"));

	public BattleScene(PokeMain pkmn, Pokemon[] playerparty, Pokemon wild) {
		game = pkmn;
		playerPokemon = playerparty[0];
		enemyPokemon = wild;
		playerTurn = true;
		Start();
	}

	public void Start() {
		System.out.println("Player's Pokemon: " + playerPokemon.getName() + " Level: " + playerPokemon.getLevel() + " HP: " + playerPokemon.getCurrentHP()
				+ " / " + playerPokemon.getHP());
		System.out.println("Wild Pokemon: " + enemyPokemon.getName() + " Level: " + enemyPokemon.getLevel() + " HP: " + enemyPokemon.getCurrentHP() + " / "
				+ enemyPokemon.getHP());
		playerPokemon.reset();
		enemyPokemon.reset();
		currentSelectionMainX = 0;
		currentSelectionMainY = 0;

		currentSelectionFight = 0;

		inMain = true;
	}

	public void Fight() {
		inMain = false;
		inFight = true;
		System.out.println("Fight");
	}

	public void Item() {
		// inMain = false;
		inItem = true;
		System.out.println("Item");
	}

	public void Pokemon() {
		// inMain = false;
		inPokemon = true;
		System.out.println("Pokemon");
	}

	public void giveEXP() {
		playerPokemon.gainExp(50);
		if (playerPokemon.getExp() >= 100) {
			playerPokemon.levelUp();
		}
		System.out.println("Current EXP: " + playerPokemon.getExp() + " / " + 100);
	}

	public void Run() {
		inMain = false;
		inRun = true;
		game.inBattle = false;
		System.out.println("Got away safely!");
	}

	public void Win() {
		giveEXP();
		inMain = false;
		inRun = true;
		game.inBattle = false;
	}

	public void Lose() {
		inMain = false;
		inRun = true;
		game.inBattle = false;
	}

	public void whiteOut() {
		pokemonfainted = true;
		Lose();
	}

	public void enemyTurn() {
		if (playerWon == false) {
			int i = 0;

			int factor = enemyPokemon.getLevel() / 5;

			i = (int) (Math.random() * factor + 1) * 5;

			System.out.println("Enemy uses " + Move.name(i));
			playerPokemon.hit(enemyPokemon, i);

			System.out.println("Enemy's turn is over");

			playerTurn = true;
		}
	}

	public void paint(Graphics g) {
		g.setFont(pokefont);
		g.setColor(Color.BLACK);
		g.drawImage(BG, 0, 0, null);
		// HUD
		g.drawString(playerPokemon.getName(), 316, 174);
		g.drawString("" + playerPokemon.getLevel(), 401, 174);
		g.drawString("" + playerPokemon.getCurrentHP(), 361, 207);
		g.drawString("" + playerPokemon.getHP(), 403, 207);
		g.drawImage(playerPokemon.getBackSprite(), 30, 113, null);
		g.drawString(enemyPokemon.getName(), 24, 26);
		g.drawString("" + enemyPokemon.getLevel(), 144, 26);
		g.drawString("" + enemyPokemon.getCurrentHP(), 70, 45);
		g.drawString("" + enemyPokemon.getHP(), 112, 45);
		g.drawImage(enemyPokemon.getFrontSprite(), 300, 25, null);

		// Battle Main Interface
		if (inMain == true) {
			g.drawImage(battleMainBG, 0, 0, null);
			g.drawString("Wild " + enemyPokemon.getName() + " Appeared!", 30, 260);
			g.drawString("FIGHT", 290, 260);
			g.drawString("PKMN", 400, 260);
			g.drawString("ITEM", 290, 290);
			g.drawString("RUN", 400, 290);
			if (currentSelectionMainX == 0 && currentSelectionMainY == 0) {
				g.drawImage(arrow, 274, 240, null);
			} else if (currentSelectionMainX == 0 && currentSelectionMainY == 1) {
				g.drawImage(arrow, 274, 270, null);
			} else if (currentSelectionMainX == 1 && currentSelectionMainY == 0) {
				g.drawImage(arrow, 384, 240, null);
			} else if (currentSelectionMainX == 1 && currentSelectionMainY == 1) {
				g.drawImage(arrow, 384, 270, null);
			}
		}

		// Battle Fight Interface
		if (inFight == true) {

			int i = playerPokemon.getLevel();

			g.drawImage(battleFightBG, 0, 0, null);
			g.drawString("Select a Move", 30, 260);
			g.drawString(playerPokemon.move1, 200, 260);
			g.drawString(playerPokemon.move2, 345, 260);
			g.drawString(playerPokemon.move3, 200, 290);
			g.drawString(playerPokemon.move4, 345, 290);

			g.drawImage(arrow, 184, 240, null);

		}
		/*
		 * if (inRun == true) { g.drawString("Got away successfully!", 30, 260);
		 * } if (pokemonfainted == true) { g.drawString(game.gold.getName() +
		 * " is all out of usable Pokemon!", 30, 260); }
		 */
	}
}