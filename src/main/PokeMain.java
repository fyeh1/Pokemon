package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PokeMain extends JPanel implements KeyListener, ActionListener {

	// DEBUG Variables

	private boolean noClip = false;
	private boolean noBattle = false;

	// Window and Window Accessories

	public static JFrame jf;
	public Toolkit tk = jf.getToolkit();

	private Font pokefont = new Font("pokesl1", Font.PLAIN, 18);

	// Player Variables

	public String name = "Steven";

	public int trainerID;

	private Image player = tk.createImage(getClass().getResource("images\\Player_Down.png"));
	private Image playerUp = tk.createImage(getClass().getResource("images\\Player_Up.png"));
	private Image playerDown = tk.createImage(getClass().getResource("images\\Player_Down.png"));
	private Image playerLeft = tk.createImage(getClass().getResource("images\\Player_Left.png"));
	private Image playerRight = tk.createImage(getClass().getResource("images\\Player_Right.png"));

	public Player steven = new Player(10, 9, name, player);

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

	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Map Variables
	// -----------------------------------------------------------------

	private int[] currentMap0 = new int[20];
	private int[] currentMap1 = new int[8];

	private int mapTilesX = 20;
	private int mapTilesY = 8;
	private int x_coor = 0;
	private int y_coor = 0;

	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// NPC's
	// -----------------------------------------------------------------
	private Image healerI = tk.createImage(getClass().getResource("images\\Healer_Down.png"));
	private Image trainerI = tk.createImage(getClass().getResource("images\\Trainer_Down.png"));

	private NPC trainer = new NPC(83, 108, "Citizen", "Wanna battle? Sorry, the 'No' function failed to be implemented.", trainerI, null);
	private NPC healer = new NPC(78, 104, "Citizen", "我只是想试试中文行不行……啥？你要治疗？", healerI, null);

	private NPC[] currentMapNPC = new NPC[] { trainer, healer };

	// Message box

	private Image messagebox = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images\\Message_Sign.png"));

	// -----------------------------------------------------------------

	// -----------------------------------------------------------------
	// Battle Variables
	// -----------------------------------------------------------------

	// private TrainerBattleScene trainerencounter;
	public Pokemon[] trainerparty = new Pokemon[3];
	Pokemon playerPokemon1;
	Pokemon playerPokemon2;
	Pokemon playerPokemon3;
	private BattleScene encounter;
	public boolean inBattle = false;

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

		repaint();
	}

	// -----------------------------------------------------------------
	// Paint Code
	// -----------------------------------------------------------------

	private void drawMap() {

		ImageIcon grassIcon = new ImageIcon("images\\Grass.jpg");
		JPanel panel = new JPanel(new GridLayout(mapTilesX, mapTilesY, 0, 0));

		JLabel labels[] = new JLabel[(mapTilesX * mapTilesY)];

		for (int i = 0; i < mapTilesX * mapTilesY; i++) {
			labels[i] = new JLabel(grassIcon);
			panel.add(labels[i]);
		}
		this.add(panel);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		g2.setTransform(at);

		if (inBattle == false) {
			drawMap();
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
					if (movable_up == true) {
						up = true;
						walking = true;
					} else {
						steven.setSprite(playerUp);
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					if (movable_down == true) {
						down = true;
						walking = true;
					} else {
						steven.setSprite(playerDown);
					}
				} else if (keyCode == KeyEvent.VK_LEFT) {
					if (movable_left == true) {
						left = true;
						walking = true;
					} else {
						steven.setSprite(playerLeft);
					}
				} else if (keyCode == KeyEvent.VK_RIGHT) {
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
							encounter.Attack();
						}
						if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 0) {
							encounter.Dodge();
						}
						if (encounter.currentSelectionMainX == 0 && encounter.currentSelectionMainY == 1) {
							encounter.Heal();
						}
						if (encounter.currentSelectionMainX == 1 && encounter.currentSelectionMainY == 1) {
							encounter.Run();
						}

					}
				}
			}
			if (encounter.inAttack == true) {
				if (encounter.playerTurn == true) {

					if (keyCode == KeyEvent.VK_Z) {

						int i = encounter.currentSelectionAttack;

						i = (i + 1) * 5;

						System.out.println("Attack " + Move.name(i) + " Selected");

						encounter.enemyPokemon.hit(encounter.playerPokemon, i);
						System.out.println(encounter.enemyPokemon.getCurrentHP());

						encounter.playerTurn = false;
						encounter.inMain = true;
						encounter.inAttack = false;
						encounter.currentSelectionMainX = 0;
						encounter.currentSelectionMainY = 0;
						encounter.currentSelectionAttack = 0;
					} else {
						System.out.println("Can't Attack");
					}

					if (keyCode == KeyEvent.VK_X) {
						encounter.currentSelectionMainX = 0;
						encounter.currentSelectionMainY = 0;
						encounter.currentSelectionAttack = 0;
						encounter.inAttack = false;
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
			if (Math.random() < 0.25) {

				int lvlcurrent = trainerparty[0].getLevel() + (int) (Math.random() * 2) - (int) (Math.random() * 2);

				Pokemon wildPokemon = Pokemon.newPokemon(lvlcurrent);

				wait(1);

				inBattle = true;
				encounter = new BattleScene(this, trainerparty, wildPokemon);

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

		drawMap();

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