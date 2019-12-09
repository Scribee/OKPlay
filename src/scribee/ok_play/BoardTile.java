package scribee.ok_play;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class for BoardTile objects.
 * 
 * @author Carson Sloan
 * 
 * Created 1/25/19
 * 
 */
@SuppressWarnings("serial")
public class BoardTile extends JButton {

	private boolean isLegal = false; // whether this tile is adjacent to a played tile
	
	private Team color = Team.NONE;
	private int id;
	
	private ImageIcon emptyIcon;
	private ImageIcon playableIcon;
	private ImageIcon moveableIcon;
	private ImageIcon tileIcon;
	
	
	/**
	 * Default constructor for new tile.
	 */
	public BoardTile() {
		super();

		initUI();
	}

	/**
	 * Constructor that sets the image for a new tile.
	 * 
	 * @param image - image to use as this tile's icon
	 */
	public BoardTile(Image emptyImage, Image playableImage, Image moveableImage) {
		super(new ImageIcon(emptyImage));

		playableIcon = new ImageIcon(playableImage);
		emptyIcon = new ImageIcon(emptyImage);
		moveableIcon = new ImageIcon(moveableImage);
		
		initUI();
	}

	/**
	 * Sets up mouse events for tile.
	 */
	private void initUI() {
		setBorder(BorderFactory.createEmptyBorder()); // remove default button borders
		setContentAreaFilled(false); // remove default tile background
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isPlayableTile() && (Board.getRemainingTiles() > 0 || Board.movedTile != null)) {
					setIcon(playableIcon);
				}
				else if (Board.getRemainingTiles() <= 0 && !isEmptyTile() && Board.movedTile == null && color == Board.currentPlayer) {
					setIcon(moveableIcon);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (isPlayableTile() && (Board.getRemainingTiles() > 0 || Board.movedTile != null)) {
					setIcon(emptyIcon);
				}
				else if (Board.getRemainingTiles() <= 0 && !isEmptyTile() && Board.movedTile == null && color == Board.currentPlayer) {
					setIcon(tileIcon);
				}
			}
		});
	}

	/**
	 * Set whether the tile is legal to play on, which is when
	 * it's adjacent to a played tile.
	 * 
	 * @param isLegal - true if tile is directly next to another tile
	 */
	public void setLegalTile(boolean isLegal) {
		this.isLegal = isLegal;
	}

	/**
	 * Returns whether the tile can be played on, which is when it
	 * hasn't been played on yet and is adjacent to a played tile.
	 * 
	 * @return true if the tile is empty and legal to play on
	 */
	public boolean isPlayableTile() {
		return isLegal && isEmptyTile();
	}
	
	/**
	 * Returns whether the tile is empty.
	 * 
	 * @return true if tile has not yet been played on
	 */
	public boolean isEmptyTile() {
		return color == Team.NONE;
	}
	
	public void setColor(Team color) {
		this.color = color;
		tileIcon = (ImageIcon) getIcon();
	}
	
	public Team getColor() {
		return color;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}