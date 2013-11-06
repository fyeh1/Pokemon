package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BattleScene {

	private PokeMain game;
	private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);

	public boolean playerTurn;
	public int elapsedTurns;
	public boolean inMain = true;
	public boolean inAttack = false;
	public boolean inHeal = false;
	public boolean inPokemon = false;
	public boolean inRun = false;
	public boolean playerWon = false;
	public boolean pokemonfainted = false;
	public boolean confirmBattleEnd = false;
	public int currentSelectionMainX;
	public int currentSelectionMainY;

	public int currentSelectionAttack;

	public Pokemon playerPokemon;
	public Pokemon enemyPokemon;

	private Image BG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images\\BG.png"));
	private Image battleMainBG = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images\\Battle.png"));
	private Image arrow = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images\\Arrow.png"));

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

		currentSelectionAttack = 0;

		inMain = true;
	}

	public void Attack() {
		inMain = false;
		inAttack = true;
		System.out.println("Attack");
	}

	public void Heal() {
		// inMain = false;
		inHeal = true;
		System.out.println("Heal");
	}

	public void Dodge() {
		// inMain = false;
		inPokemon = true;
		System.out.println("Dodge");
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
			g.drawString("Attack", 290, 260);
			g.drawString("Dodge", 400, 260);
			g.drawString("Heal", 290, 290);
			g.drawString("Run", 400, 290);
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

		// Battle Attack Interface
		if (inAttack == true) {
			
			ScrollGrid skillz = new ScrollGrid();
			
			int factor = playerPokemon.getLevel();
			for(int x = 0; x<=factor; x++)
			{
				if(Move.name(x)!=null)
					skillz.create(Move.name(x));
			}
			boolean finished = false;
			while(finished ==false)
			{
				try {
					wait(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finished = skillz.getState();
			}
			
			int result = skillz.getIndex();
			
			currentSelectionAttack = result;

		}
		/*
		 * if (inRun == true) { g.drawString("Got away successfully!", 30, 260);
		 * } if (pokemonfainted == true) { g.drawString(game.gold.getName() +
		 * " is all out of usable Pokemon!", 30, 260); }
		 */
	}
}