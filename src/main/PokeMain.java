package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokeMain extends JPanel implements KeyListener, ActionListener {

	// DEBUG Variables

	private boolean noClip = false;
	private boolean noBattle = false;

	// Window and Window Accessories

	public static JFrame jf;
	public Toolkit tk = jf.getToolkit();

	private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);

	private int offsetX = 0, offsetY = 0;
	private int TILE_WIDTH_PIXELS = 32;
	private int TILE_HEIGHT_PIXELS = 32;

	// Player Variables

	public String name = "Steven";

	public int trainerID;

	private Image player = tk.createImage(getClass().getResource("images/Player_Down.png"));
	private Image playerUp = tk.createImage(getClass().getResource("images/Player_Up.png"));
	private Image playerDown = tk.createImage(getClass().getResource("images/Player_Down.png"));
	private Image playerLeft = tk.createImage(getClass().getResource("images/Player_Left.png"));
	private Image playerRight = tk.createImage(getClass().getResource("images/Player_Right.png"));

	public Player steven = new Player(10, 9, name, player);
	private boolean running = false;
	private int lastdir = 1;
	private int movespritepixels = 0;
	private boolean walking = false;
	private boolean moving = false;
	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	private boolean movable_up = true;
	private boolean movable_down = true;
	private boolean movable_left = true;
	private boolean movable_right = true;
	private boolean talkable = false;
	private boolean disable_talk = true;
	private boolean collision = false;
	private boolean footsprite = false;
	private String text = "";
	private int posX = 224; // Multiple of 32
	private int posY = 118; // -10 because height is 42, not 32.
	private int currentX_loc; // Starting Location of player in terms of rows
	private int currentY_loc; // Starting Location of player in terms of columns
	private int posX_tile; // Location of player in terms of rows
	private int posY_tile; // Location of player in terms of columns
	private boolean movable = true;
	private static Random randGen = new Random();
	private int stepscount = 0;
	public int badges = 0;
	public int money = 2000;
	public long timePlayed = 0;
	public long currentTime = 0;
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Map Variables
	// -----------------------------------------------------------------
	private Image[] tileset = new Image[1112];
	private String currentMapName = "C:/Users/Perdre/Documents/GitHub/Fakemon/src/Data/Johto.map";
	
	private int[] currentMap0 = new int[20];
	private int[] currentMap1 = new int[8];

	private int mapTilesX;
	private int mapTilesY;
	private int x_coor = 0;
	private int y_coor = 0;
	private int tile_number = 0;
	private boolean showmessagebox = false;
	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// NPC's
	// -----------------------------------------------------------------
	private Image healerI = tk.createImage(getClass().getResource("images/Healer_Down.png"));
	private Image trainerI = tk.createImage(getClass().getResource("images/Trainer_Down.png"));

	private NPC trainer = new NPC(83, 108, "Citizen", "Wanna battle? Sorry, the 'No' function failed to be implemented.", trainerI, null);
	private NPC healer = new NPC(78, 104, "Citizen", "我只是想试试中文行不行……啥？你要治疗？", healerI, null);

	private NPC[] currentMapNPC = new NPC[] { trainer, healer };

	// Message box

	private Image messagebox = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Graphics/Pictures/Message_Text.png"));

	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Battle Variables
	// -----------------------------------------------------------------
	private TrainerBattleScene trainerencounter;
	public Pokemon[] trainerparty = new Pokemon[3];
	Pokemon playerPokemon1;
	Pokemon playerPokemon2;
	Pokemon playerPokemon3;
	private BattleScene encounter;
	public boolean inBattle = false;
	private int r;
	private int rndwildmodify = 15;

	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Pokemon Constructor
	// -----------------------------------------------------------------

	public PokeMain() {
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(480, 320));
		addKeyListener(this);

		startgame();
	}

	// -----------------------------------------------------------------
	// ActionListener
	// -----------------------------------------------------------------

	public void actionPerformed(ActionEvent e) {
		currentTime = java.lang.System.currentTimeMillis();

		// -----------------------------------------------------------------
		// Battle Scene
		// -----------------------------------------------------------------
		if (inBattle == true) {
			if (encounter.playerPokemon.getCurrentHP() <= 0) {
				// Whited Out
				System.out.println("Player Pokemon has fainted");
				System.out.println(steven.getName() + " is all out of usable Pokemon!");
				System.out.println(steven.getName() + " whited out.");
				encounter.whiteOut();
				currentX_loc += 42 - posX_tile;
				currentY_loc += 107 - posY_tile;
				posX_tile = 42;
				posY_tile = 107;
				steven.setSprite(playerUp);
				lastdir = 1;
				playerPokemon1.heal();
			}
			if (encounter.enemyPokemon.getCurrentHP() <= 0) {
				encounter.playerWon = true;
				System.out.println("Wild Pokemon has fainted");
				encounter.Win();
			}
			if (encounter.playerTurn == false) {
				wait(1);
				encounter.enemyTurn();
			}
		}
		// -----------------------------------------------------------------

		steven.setCurrentX(posX_tile);
		steven.setCurrentY(posY_tile);
		// Random Encounter Variables
		r = (int) ((5 - 1) * Math.random() + 1);
		rndwildmodify = randGen.nextInt(22) + 11;
		// Wild Pokemon Encounter Check
		checkBattle();
		// Can't walk outside of the Map Array
		if (posX_tile <= 0) {
			movable_left = false;
		}
		if (posX_tile >= mapTilesX - 1) {
			movable_right = false;
		}
		if (posY_tile <= 0) {
			movable_up = false;
		}
		if (posY_tile >= mapTilesY - 1) {
			movable_down = false;
		}
		// Crashtesting with NPC's
		movable_up = true;
		movable_down = true;
		movable_left = true;
		movable_right = true;
		if (noClip == false) {
			for (int i = 0; i < currentMapNPC.length; i++) {
				if (steven.crashTest(currentMapNPC[i]) == 1) {
					movable_up = false;
					disable_talk = false;
				} else if (steven.crashTest(currentMapNPC[i]) == 2) {
					movable_down = false;
					disable_talk = false;
				} else if (steven.crashTest(currentMapNPC[i]) == 3) {
					movable_left = false;
					disable_talk = false;
				} else if (steven.crashTest(currentMapNPC[i]) == 4) {
					movable_right = false;
					disable_talk = false;
				}
				if (steven.crashTest(currentMapNPC[i]) != 0) {
					if (collision == true) {
						collision = false;
					}
				}
			}
			
		}
		// Movement Scrolling
		if (walking == true) {
			movespritepixels++;
			if (up == true && movable_up == true) {
				offsetY += 2;
			}
			if (down == true && movable_down == true) {
				offsetY -= 2;
			}
			if (left == true && movable_left == true) {
				offsetX += 2;
			}
			if (right == true && movable_right == true) {
				offsetX -= 2;
			}
		}
		// Movement Reset
		if (movespritepixels >= 16) {
			movespritepixels = 0;
			walking = false;
			if (up == true && movable_up == true)
				posY_tile -= 1;
			if (down == true && movable_down == true)
				posY_tile += 1;
			if (left == true && movable_left == true)
				posX_tile -= 1;
			if (right == true && movable_right == true)
				posX_tile += 1;
			up = false;
			down = false;
			left = false;
			right = false;
			footsprite = !footsprite;
			if (playerPokemon1.getCurrentHP() <= 0) {
				System.out.println(playerPokemon1.getName() + " has fainted.");
				System.out.println(steven.getName() + " is all out of usable Pokemon.");
				System.out.println(steven.getName() + " whited out.");
				currentX_loc += 42 - posX_tile;
				currentY_loc += 107 - posY_tile;
				posX_tile = 42;
				posY_tile = 107;
				playerPokemon1.heal();
				steven.setSprite(playerUp);
			}
		}

		repaint();
	}

	// -----------------------------------------------------------------
	// Paint Code
	// -----------------------------------------------------------------

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		g2.setTransform(at);

		if (inBattle == false) {
			// Draw the Map
			g2.setClip(new Rectangle(posX - 240, posY - 160, posX + 480, posY + 320));
			g2.translate(offsetX - (currentX_loc * 32), offsetY - (currentY_loc * 32));
			for (int y = 1; y <= mapTilesY; y++) {
				for (int x = 1; x <= mapTilesX; x++) {
					// Layer 0
					if (currentMap0[tile_number] != 0) {
						g.drawImage(tileset[currentMap0[tile_number] - 1], x_coor, y_coor, null);
					}
					// Layer 1
					if (currentMap1[tile_number] != 0) {
						g.drawImage(tileset[currentMap1[tile_number] - 1], x_coor, y_coor, null);
					}

					x_coor = x_coor + 32;
					tile_number = tile_number + 1;
				}
				x_coor = 0;
				y_coor = y_coor + 32;
			}

			tile_number = 0;
			x_coor = 0;
			y_coor = 0;
			// NPC Sprites
			for (int i = 0; i < currentMapNPC.length; i++) {
				g.drawImage(currentMapNPC[i].getSprite(), currentMapNPC[i].getCurrentX() * TILE_WIDTH_PIXELS, currentMapNPC[i].getCurrentY()
						* TILE_HEIGHT_PIXELS - 10, null);
				/*
				 * g.drawImage(currentMapNPC[i].getSprite(),
				 * currentMapNPC[i].getCurrentX()*32,
				 * currentMapNPC[i].getCurrentY()*32,
				 * currentMapNPC[i].getWidth(), currentMapNPC[i].getHeight(),
				 * currentMapNPC[i].getWidth(), currentMapNPC[i].getHeight(),
				 * null);
				 */
			}

			// Reset to 0,0
			g2.translate(-offsetX, -offsetY);
			// Player Sprites
			g2.setTransform(at);
			g.drawImage(steven.getSprite(), posX, posY, null);
			g.setFont(pokefont);
			g.setColor(Color.WHITE);
			g.drawString("" + posX_tile + "," + posY_tile, 10, 25);
			showMessageBox(g);
		} else {
			encounter.paint(g);
		}
	}

	// -----------------------------------------------------------------
	// KeyListener Code
	// -----------------------------------------------------------------

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (movable == true && inBattle == false) {
			if (walking == false) {
				if (keyCode == KeyEvent.VK_UP) {
					crashTest(currentMap0);
					crashTest(currentMap1);
					collision = true;
					if (movable_up == true) {
						up = true;
						walking = true;
					} else {
						steven.setSprite(playerUp);
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					crashTest(currentMap0);
					crashTest(currentMap1);
					collision = true;
					if (movable_down == true) {
						down = true;
						walking = true;
					} else {
						steven.setSprite(playerDown);
					}
				} else if (keyCode == KeyEvent.VK_LEFT) {
					crashTest(currentMap0);
					crashTest(currentMap1);
					collision = true;
					if (movable_left == true) {
						left = true;
						walking = true;
					} else {
						steven.setSprite(playerLeft);
					}
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					crashTest(currentMap0);
					crashTest(currentMap1);
					collision = true;
					if (movable_right == true) {
						right = true;
						walking = true;
					} else {
						steven.setSprite(playerRight);
					}
				}

				if (keyCode == KeyEvent.VK_Z) {
					System.out.println("Action Button");
					if (disable_talk == false) {
						talkable = !talkable;
						movable_up = !movable_up;
						movable_down = !movable_down;
						movable_left = !movable_left;
						movable_right = !movable_right;
					}
				} else if (keyCode == KeyEvent.VK_X) {
					System.out.println("Cancel Button");
				}
			}
		}

		// -----------------------------------------------------------------
		// Battle Scene
		// -----------------------------------------------------------------
		if (inBattle == true) {
			if (encounter.inMain == true) {
				if (encounter.playerTurn == true) {
					if (keyCode == KeyEvent.VK_UP) {
						encounter.currentSelectionMainY = 0;

					} else if (keyCode == KeyEvent.VK_DOWN) {
						encounter.currentSelectionMainY = 1;

					} else if (keyCode == KeyEvent.VK_LEFT) {
						encounter.currentSelectionMainX = 0;

					} else if (keyCode == KeyEvent.VK_RIGHT) {
						encounter.currentSelectionMainX = 1;

					}
					if (keyCode == KeyEvent.VK_Z) {
						if (encounter.currentSelectionMainX == 0 && encounter.currentSelectionMainY == 0) {
							encounter.Fight();
						}
						if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 0) {
							encounter.Pokemon();
						}
						if (encounter.currentSelectionMainX == 0 && encounter.currentSelectionMainY == 1) {
							encounter.Item();
						}
						if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 1) {
							encounter.Run();
						}

					}
				}
			}
			if (encounter.inFight == true) {
				if (encounter.playerTurn == true) {

					if (keyCode == KeyEvent.VK_Z) {

						int i = encounter.currentSelectionFight;

						System.out.println("Attack " + i + " Selected");

						encounter.enemyPokemon.hit(encounter.playerPokemon, i);
						System.out.println(encounter.enemyPokemon.getCurrentHP());

						encounter.playerTurn = false;
						encounter.inMain = true;
						encounter.inFight = false;
						encounter.currentSelectionMainX = 0;
						encounter.currentSelectionMainY = 0;
						encounter.currentSelectionFight = 0;
					} else {
						System.out.println("Can't Attack");
					}

					if (keyCode == KeyEvent.VK_X) {
						encounter.currentSelectionMainX = 0;
						encounter.currentSelectionMainY = 0;
						encounter.currentSelectionFight = 0;
						encounter.inFight = false;
						encounter.inMain = true;

					}

					if (encounter.inRun == true) {
						if (keyCode == KeyEvent.VK_Z) {
							encounter.confirmBattleEnd = true;
						}
					}
				}
			}
		}
	}

	// -----------------------------------------------------------------

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			lastdir = 1;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			lastdir = 2;
		} else if (keyCode == KeyEvent.VK_LEFT) {
			lastdir = 3;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			lastdir = 4;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void crashTest(int[] map) {
		// Wild Pokemon Grass
		if (map[(posY_tile * mapTilesX) + posX_tile] == 17) {
			stepscount++;
		}
	}

	public void collision() {
		if (collision == true) {
			collision = false;
		}
	}

	public void showMessageBox(Graphics g) {
		g.setColor(Color.BLACK);
		// Trainer Pokemon Battles
		/*
		 * if (talkable == true) { for (int i = 0; i < currentMapNPC.length;
		 * i++) { if (currentMapNPC[i].getTalkable(gold) == true) { lastBGM =
		 * currentBGM; currentBGM.stop(); currentBGM = battleBGM;
		 * currentBGM.start(); //Trainer if (currentMapNPC[i] == bird_keeper1) {
		 * wildPokemon.create(198); trainerparty[0] = wildPokemon; } //Trainer
		 * wait(1); inBattle = true; disable_start = true; trainerencounter =
		 * new
		 * TrainerBattleScene(this,currentMapNPC[i],trainerparty,trainerparty
		 * ,items); try{Thread.sleep(500);} catch(InterruptedException e){} } }
		 * }
		 */
		// NPC Talking
		if (talkable == true) {
			for (int i = 0; i < currentMapNPC.length; i++) {
				if (currentMapNPC[i].getTalkable(steven) == true) {
					text = currentMapNPC[i].getText(steven);
				}
			}
		}
		if (talkable == true && movable_up == false) {
			if (steven.getSprite() == playerUp) {
				g.drawImage(messagebox, 0, 0, null);
				g.drawString(text, 25, 255);
			}
		}
		if (talkable == true && movable_down == false) {
			if (steven.getSprite() == playerDown) {
				g.drawImage(messagebox, 0, 0, null);
				g.drawString(text, 25, 255);
			}
		}
		if (talkable == true && movable_left == false) {
			if (steven.getSprite() == playerLeft) {
				g.drawImage(messagebox, 0, 0, null);
				g.drawString(text, 25, 255);
			}
		}
		if (talkable == true && movable_right == false) {
			if (steven.getSprite() == playerRight) {
				g.drawImage(messagebox, 0, 0, null);
				g.drawString(text, 25, 255);
			}
		}
	}

	// -----------------------------------------------------------------
	// Battle Code
	// -----------------------------------------------------------------

	public void checkBattle() {
		if (noBattle == false) {
			if (stepscount >= rndwildmodify) {

				int lvlcurrent = trainerparty[0].getLevel() + (int) (Math.random() * 2) - (int) (Math.random() * 2);

				Pokemon wildPokemon = Pokemon.newPokemon(lvlcurrent);

				wait(1);

				inBattle = true;
				encounter = new BattleScene(this, trainerparty, wildPokemon);
				stepscount = 0;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	public void startgame() {

		loadTileSet();
		loadMap(currentMapName);

		name = "Steven";
		steven.setName(name);
		steven.createTrainerID();
		steven.createSecretID();
		currentX_loc = 6 - 7;
		currentY_loc = 67 - 4;
		posX_tile = currentX_loc + 7;
		posY_tile = currentY_loc + 4;
		Pokemon playerPokemon1 = Pokemon.newPokemon(5);
		Pokemon playerPokemon2 = Pokemon.newPokemon(5);
		Pokemon playerPokemon3 = Pokemon.newPokemon(5);
		trainerparty[0] = playerPokemon1;
		trainerparty[1] = playerPokemon2;
		trainerparty[2] = playerPokemon3;

		player = playerDown;

		movable = true;
		timePlayed = java.lang.System.currentTimeMillis();
	}

	public void loadTileSet() {

		for (int i = 0; i < tileset.length; i++) {
			tileset[i] = tk.createImage(getClass().getResource("images/Grass"));
		}

	}

	public static void wait(int n) {
		long t0, t1;
		t0 = System.currentTimeMillis();
		do {
			t1 = System.currentTimeMillis();
		} while ((t1 - t0) < (n * 1000));
	}

	public static void main(String[] Args) {
		// Create the window
		jf = new JFrame("Pokemon: Lame can't be tamed");
		// Create an instance of Pokemon and insert into the window
		PokeMain pokemon = new PokeMain();
		jf.add(pokemon);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.pack();
		// Center the Game on the Screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = jf.getSize().width;
		int h = jf.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		jf.setLocation(x, y);
		// Set focus to the Panel
		jf.setVisible(true);
		pokemon.requestFocus(true);
	}
}