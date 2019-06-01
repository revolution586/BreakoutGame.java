package BreakoutGame;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Breakout extends JFrame {
	
	public Breakout() {
		
		initUI();
	}
	
	private void initUI() {
		
		add(new Board());
		setTitle("Breakout");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(Commons.WIDTH, Commons.HEIGHT);
		setLocationRelativeTo(null);
		setRezisable(false);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			Breakout game = new Breakout();
			game.setVisible(true);
		});
	}
}

//And finally, this is the Breakout class which has the main entry method where window for where the game is taken place will show.//
