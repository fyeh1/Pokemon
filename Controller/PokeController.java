package Controller;

import Model.PokeModel;
import View.PokeView;

public class PokeController 
{
	private PokeModel model;
	private PokeView view;
	
	public PokeController ()
	{
		view = new PokeView();
	}
	
	public static void main (String[] args)
	{
		new PokeController();
	}
}
