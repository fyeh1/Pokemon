package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class DialoguePanel extends JPanel
{
	private JLabel text;
	private int state;
	
	public DialoguePanel()
	{
		super();
	}
	
	public void msg(int m)
	{
		String message;
		if (m==0)
			message = "What will you do?";
		else if (m==1)
			message = "You ran out of usable Pokemon!";
		else if (m==2)
			message = "You whited out!";
		else if (m==3)
			message = "You ran away safely!";
		else
			message = "You could not run!";
		text = new JLabel(message);
		add(text);
		revalidate();
		repaint();
	}
	public void msg(int m, String pokemon, String move, String stat)
	{
		String message;
		if (m<5)
		{
			msg(m);
			return;
		}
		else if (m==5)
			message = "Go! " + pokemon;
		else if (m==6)
			message = pokemon + " used " + move + "!";
		else if (m==7)
			message = pokemon + " could not move!";
		else if (m==8)
			message = pokemon + " increased its " + stat;
		else if (m==9)
			message = pokemon + " fainted!";
		else if (m==10)
			message = pokemon + " dodged the attack!";
		else
			message = pokemon + " healed!";
		
		text = new JLabel(message);
		add(text);
		revalidate();
		repaint();
	}
	
	public int getState()
	{
		return state;
	}
}