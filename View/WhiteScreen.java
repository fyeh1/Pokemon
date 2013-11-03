package View;
import javax.swing.JPanel;


public class WhiteScreen extends JPanel
{
	private DialoguePanel dialoguePanel;
	
	public WhiteScreen()
	{
		super();
		dialoguePanel = new DialoguePanel();
		dialoguePanel.msg(1);
		add(dialoguePanel);
		
	}
}
