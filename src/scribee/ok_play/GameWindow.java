 package scribee.ok_play;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Carson Sloan
 *
 * Created 1/25/19
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	/**
	 * Default constructor for a new game of OK Play.
	 */
	public GameWindow() {
		initUI(null);
	}
	
	public GameWindow(JPanel[] components) {
		initUI(components);
	}
	
	/**
	 * Sets up the game UI.
	 */
	private void initUI(JPanel[] components) {
//		add(board, BorderLayout.CENTER); // make the board the middle component on the screen
//		add(menu, BorderLayout.NORTH);
		
		add(components[0], BorderLayout.CENTER);
		add(components[1], BorderLayout.NORTH);
		
		setTitle("OK Play");
		setResizable(false);
		pack(); // sets dimensions
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // centers window
	}
}
