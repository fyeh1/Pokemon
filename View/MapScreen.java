package View;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class MapScreen extends JPanel
{
	public MapScreen()
	{
		super();
		JPanel[][] map = new JPanel[][]
				{{new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()}, 
				{new JPanel(), new MapPanel("images\\Grass.jpg"), new MapPanel("images\\Grass.jpg"), new JPanel(), new MapPanel("images\\Healer_Left")}, 
				{new JPanel(), new MapPanel("images\\Grass.jpg"), new MapPanel("images\\Grass.jpg"), new JPanel(), new JPanel()}, 
				{new JPanel(), new MapPanel("images\\Grass.jpg"), new MapPanel("images\\Grass.jpg"), new JPanel(), new MapPanel("images\\Trainer_Left")}, 
				{new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()}};
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		for (int i=0; i<5; i++)
			p1.add(map[0][i]);
		p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
		for (int i=0; i<5; i++)
			p2.add(map[1][i]);
		p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
		for (int i=0; i<5; i++)
			p3.add(map[2][i]);
		p4.setLayout(new BoxLayout(p4, BoxLayout.Y_AXIS));
		for (int i=0; i<5; i++)
			p4.add(map[3][i]);
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));
		for (int i=0; i<5; i++)
			p5.add(map[4][i]);
		/*JPanel[] xPanels = new JPanel[]{new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
		JPanel[] yPanels = new JPanel[]{new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for (int x=0; x<5; x++)
		{
			xPanels[x].setLayout(new BoxLayout(xPanels[x], BoxLayout.Y_AXIS));
			for (int y=0; y<5; y++)
			{
				if ((y>=1 && y<=3) && (x==1 || x==2))
				{
					MapPanel m = new MapPanel("images\\Grass.jpg");
					m.setOpaque(false);
					xPanels[x].add(m);
					System.out.println ("a");
				}
				else if (x==3 && y==1)
				{
					MapPanel m = new MapPanel("images\\Healer_Left.jpg");
					m.setOpaque(false);
					xPanels[x].add(m);
					System.out.println ("b");
				}
				else if (x==3 && y==3)
				{
					MapPanel m = new MapPanel("images\\Trainer_Left.jpg");
					m.setOpaque(false);
					xPanels[x].add(m);
					System.out.println ("c");
				}
				else if (x==0 && y==0)
				{
					xPanels[x].add(new MapPanel("images\\Player_Down.jpg"));
					System.out.println ("d");
				}
				else
					xPanels[x].add(yPanels[y]);
			}
		}
		revalidate();
		repaint();*/
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JPanel p = new JPanel();
		add (p);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add (new MapPanel("images\\Grass.jpg"));
		p.add (new MapPanel("images\\Grass.jpg"));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}
