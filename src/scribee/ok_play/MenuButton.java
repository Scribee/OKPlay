package scribee.ok_play;

import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 * Class for BoardTile objects.
 * 
 * @author Carson Sloan
 * 
 * Created 1/25/19
 * 
 */
@SuppressWarnings("serial")
public class MenuButton extends JButton {
	
	private int id;
	
	private String text;
	
	private ImageIcon defaultIcon;
	
	/**
	 * Default constructor for new button.
	 */
	public MenuButton() {
		super();

		initUI();
	}

	/**
	 * Constructor that sets the image for a new tile.
	 * 
	 * @param image - image to use as this tile's icon
	 */
	public MenuButton(String text, Image defaultImage) {
		super(text, new ImageIcon(defaultImage));
		
		this.text = text;

		defaultIcon = new ImageIcon(defaultImage);
		
		initUI();
	}

	/**
	 * Sets up mouse events for tile.
	 */
	private void initUI() {
		setBorder(BorderFactory.createEmptyBorder()); // remove default button borders
		setContentAreaFilled(false); // remove default button background
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
	}
	
	@Override
	public String toString() {
		return text;
	}
}