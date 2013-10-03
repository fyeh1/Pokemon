package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class PokeView extends JFrame implements Observer
{
	private MapScreen mapScreen;
	private BattleScreen battleScreen;
	private WhiteScreen whiteScreen;
	

	public PokeView()
	{
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) 
	{
		// TODO Auto-generated method stub
		
	}

}
