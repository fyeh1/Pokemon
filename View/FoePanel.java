package View;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FoePanel extends JPanel
{
	private Image pokemon;
	
	public FoePanel (String pokeImage)
	{
		super();
		ImageIcon pokeIcon = new ImageIcon(pokeImage);
		pokemon = pokeIcon.getImage();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(pokemon, 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
