package scribee.ok_play;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class OKPlay {
	
	public static Board board;
	public static Menu menu;
	
	public static void main(String[] args) {
		
		board = new Board();
		menu = new Menu();
		
		JPanel[] components = {board, menu};
		
		EventQueue.invokeLater(() -> {
			GameWindow game = new GameWindow(components);
			game.setVisible(true);
		});
	}
	
	/**
	 * Returns a BufferedImage created from a specified image file.
	 * 
	 * @param path - path to the image file to be loaded
	 * @return BufferedImage created from a specified image file
	 * @throws IOException if there is an error reading the file 
	 */
	public static BufferedImage loadImage(String path) {
		try {
			BufferedImage image = ImageIO.read(new File(path));

			return image;
		}
		catch (IOException ioex) {
			ioex.printStackTrace();
		}
		
		return null;
	}
}
