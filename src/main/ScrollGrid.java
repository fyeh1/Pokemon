package main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ScrollGrid extends JPanel implements ActionListener {

	private static final int N = 16;
	private ArrayList<JButton> butts = new ArrayList<JButton> ();
	private int index;
	private boolean finished;

	JFrame f = new JFrame("Skillz");

	public ScrollGrid() {
		this.setLayout(new GridLayout(0, 1, 3, 3));
		finished = false;
	}

	public void create(String name) {
		JButton last = new JButton(name);
		last.addActionListener(this);
		butts.add(last);
		this.add(last);
	}

	private void display() {
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new JScrollPane(this));

		f.pack();
		f.setSize(200, 160);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.requestFocus();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new ScrollGrid().display();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for(int x = 0; x<butts.size();x++)
			if(butts.get(x) == e.getSource())
				index = x;
		finished = true;
		f.setVisible(false);	
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public boolean getState()
	{
		return finished;
	}
}