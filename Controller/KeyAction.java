package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class KeyAction extends AbstractAction
{
    private String key;
    private JPanel panel;

    public KeyAction(String cue, JPanel affectedPanel) 
    {
        key = cue;
        panel = affectedPanel;
    }
    
    public void actionPerformed(ActionEvent e) 
    {
        if (key.equalsIgnoreCase("aMap"))
        {
        	
        } 
        else if (key.equalsIgnoreCase("aDialogue"))
        {
        	
        } 
        else if (key.equalsIgnoreCase("aAction"))
        {
        	
        }
        else if (key.equalsIgnoreCase("bAction"))
        {
        	
        }
    }
}
