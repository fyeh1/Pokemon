package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.PokeModel;
import View.PokeView;

public class PokeController implements KeyListener, ActionListener {

	private PokeModel model;
	private PokeView view;

	public PokeController() {
		model = new PokeModel();
		view = new PokeView();
	}

	public static void main(String[] args) {
		new PokeController();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			model.move(4);
			
		} else if (keyCode == KeyEvent.VK_DOWN) {
			model.move(2);
			} else if (keyCode == KeyEvent.VK_LEFT) {
			model.move(3);
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			model.move(1);
		} 
		if (keyCode == KeyEvent.VK_Z) {
			System.out.println("Action Button");
			int action = model.action();
			
		} else if (keyCode == KeyEvent.VK_X) {
			System.out.println("Cancel Button");
			int cancel = model.cancel();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
