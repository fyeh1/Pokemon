package View;
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
	
	public void msg(int m, String pokemon, String move, String stat)
	{
		String message;
		if (m==0)
			message = "Go! " + pokemon;
		else if (m==1)
			message = pokemon + " used " + move + "!";
		else if (m==2)
			message = pokemon + " could not move!";
		else if (m==3)
			message = pokemon + " increased its " + stat;
		else if (m==4)
			message = pokemon + " fainted!";
		else if (m==5)
			message = "You ran out of usable Pokemon!";
		else if (m==6)
			message = "You whited out!";
		else
			message = "What will you do?";
		
		text = new JLabel(message);
		add(text);
		revalidate();
		repaint();
	}
}