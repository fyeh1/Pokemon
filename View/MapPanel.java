package View;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

public class MapPanel extends JPanel
{
	private Image character;
	private int direction;
	
	public MapPanel(String charImage)
	{
		super();
		if (charImage.contains("Up"))
			direction=1;
		else if (charImage.contains("Left"))
			direction=2;
		else if (charImage.contains("Right"))
			direction=3;
		else
			direction=0;
		ImageIcon charIcon = new ImageIcon(charImage);
		character = charIcon.getImage();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(character, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public int getDirection()
	{
		return direction;
	}
}
