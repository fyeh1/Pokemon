package View;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PokeView extends JFrame implements Observer {
	private MapScreen mapScreen;
	private BattleScreen battleScreen;
	private WhiteScreen whiteScreen;
	private int viewState;

	public static void main(String[] args) {
		PokeView hhh = new PokeView();
	}

	public PokeView() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(500, 500);
		setVisible(true);
		mapScreen = new MapScreen();
		battleScreen = new BattleScreen();
		whiteScreen = new WhiteScreen();
		mapMode();
		revalidate();
		repaint();
	}

	public void mapMode() {
		setContentPane(mapScreen);
		viewState = 0;
		revalidate();
		repaint();
	}

	public void battleMode() {
		setContentPane(battleScreen);
		viewState = 1;
		revalidate();
		repaint();
	}

	public void whiteoutMode() {
		setContentPane(whiteScreen);
		viewState = 2;
		revalidate();
		repaint();
	}

	public MapScreen getMapScreen() {
		return mapScreen;
	}

	public BattleScreen getBattleScreen() {
		return battleScreen;
	}

	public WhiteScreen getWhiteScreen() {
		return whiteScreen;
	}

	public void paintComponents(Graphics g) {
		super.paintComponents(g);
	}

	@Override
	public void update(Observable e, Object o) {
		// TODO Auto-generated method stub

	}
}
