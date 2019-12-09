package scribee.ok_play;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Board extends JPanel {
	public static BufferedImage emptyTileImage, startTileImage, playableTileImage, moveableTileImage, greenTileImage, orangeTileImage, blueTileImage, pinkTileImage;
	private static int remainingTiles = Settings.STARTING_TILES;
	
	public static Team currentPlayer = Team.GREEN; // stores which team's turn it is

	private BoardTile[] tiles; // stores all the tiles on the board
	
	public static BoardTile movedTile;
	
	public Board() {
		
	}
	
	@Override
    public void addNotify() {
		super.addNotify();
		
        initUI();
    }

	private void initUI() {
		setLayout(new GridLayout(Settings.Y_TILES, Settings.X_TILES, 3, 3)); // create a grid of the specified dimensions to arrange the tiles
		setBorder(BorderFactory.createEmptyBorder()); // remove default grid borders

		// load images
		emptyTileImage = OKPlay.loadImage("src/resources/empty_tile.png");
		startTileImage = OKPlay.loadImage("src/resources/start_tile.png");
		playableTileImage = OKPlay.loadImage("src/resources/playable_tile.png");
		moveableTileImage = OKPlay.loadImage("src/resources/moveable_tile.png");
		greenTileImage = OKPlay.loadImage("src/resources/green_tile.png");
		orangeTileImage = OKPlay.loadImage("src/resources/orange_tile.png");
		blueTileImage = OKPlay.loadImage("src/resources/blue_tile.png");
		pinkTileImage = OKPlay.loadImage("src/resources/pink_tile.png");


		generateEmptyBoard();
	}
	
	public void resetBoard() {
		remainingTiles = Settings.STARTING_TILES;
		currentPlayer = Team.GREEN;
		
		removeAll();
		generateEmptyBoard();
		validate();
	}
	
	private void generateEmptyBoard() {
		tiles = new BoardTile[Settings.NUM_TILES];

		int startTile = Settings.NUM_TILES / 2;

		// create board of empty tiles
		for (int i = 0; i < Settings.NUM_TILES; i++) {
			BoardTile tile;
			if (i == startTile) {
				tile = new BoardTile(startTileImage, playableTileImage, moveableTileImage);
				tile.setLegalTile(true);
			}
			else {
				tile = new BoardTile(emptyTileImage, playableTileImage, moveableTileImage);
			}

			tile.setID(i);
			tile.addActionListener(new TileAction());

			tiles[i] = tile;
			add(tile);
		}
	}

	/**
	 * Updates which players turn it is currently.
	 * Turns go Green->Orange->Blue->Pink->Green, etc.
	 */
    private void updatePlayer() {
    	if (currentPlayer == Team.GREEN) {
    		currentPlayer = Team.ORANGE;
    	}
    	else if (currentPlayer == Team.ORANGE) {
    		currentPlayer = Team.GREEN;
    	}
    }
    
    /**
     * Places a new tile on the specified empty tile.
     * Tile gets a new icon based on which player's turn it is, and
     * all adjacent tiles are updated to be legal to be played on.
     * 
     * @param tile - empty BoardTile to be played on
     */
    private void placeNewTile(BoardTile tile) {
    	if (currentPlayer == Team.GREEN) {
    		tile.setIcon(new ImageIcon(greenTileImage));
    	}
    	else if (currentPlayer == Team.ORANGE) {
    		tile.setIcon(new ImageIcon(orangeTileImage));
    		remainingTiles--;
    	}
    	else if (currentPlayer == Team.BLUE) {
    		tile.setIcon(new ImageIcon(blueTileImage));
    	}
    	else if (currentPlayer == Team.PINK) {
    		tile.setIcon(new ImageIcon(pinkTileImage));
    	}
    	
    	if (movedTile != null) {
    		movedTile.setIcon(new ImageIcon(emptyTileImage));
    		movedTile.setColor(Team.NONE);
    		movedTile = null;
    	}
    	
    	tile.setColor(currentPlayer);

    	checkWinCondition(tile);
    	updateLegalTiles(tile);
    	updatePlayer();
    }

    /**
     * Checks if the most recently played tile caused that player to win the game.
     * 
     * @param tile - newly played tile
     */
    private void checkWinCondition(BoardTile newtile) {
    	Direction[] directionsToCheck = {
    			Direction.ABOVE,
    			Direction.RIGHT_ABOVE,
    			Direction.RIGHT,
    			Direction.RIGHT_BELOW
    		};
    	
    	int tilesInLine;
    	
    	for (Direction direction : directionsToCheck) {
    		tilesInLine = countTilesInDirection(newtile, direction, currentPlayer) + 1 + countTilesInDirection(newtile, direction.getOpposite(), currentPlayer);
    		
    		if (tilesInLine >= Settings.TILES_TO_WIN) {
    			System.out.println(currentPlayer.toString() + " won the game");
    			break;
    		}
    	}
    	
    }
    
    /**
     * 
     * @param tile
     * @param searchDirections
     * @return
     */
    private int countTilesInDirection(BoardTile tile, Direction direction, Team color) {
    	BoardTile nextTile = getTileInDirection(tile, direction);
    	
    	if (nextTile == null) {
    		return 0;
    	}
    	else if (nextTile.getColor() == color) {
    		return 1 + countTilesInDirection(nextTile, direction, color);
    	}
    	
    	return 0;
    }
    
    public BoardTile getTileInDirection(BoardTile startTile, Direction direction) {  	
    	BoardTile newTile = null;
    	
    	if (startTile == null) {
    		return null;
    	}
    	
    	int id = startTile.getID();
    	
    	if (direction == Direction.LEFT) {
    		if (id > 0 && id % Settings.X_TILES != 0) {
    			newTile = tiles[id - 1];
    		}
    	}
    	else if (direction == Direction.RIGHT) {
    		if (id < Settings.NUM_TILES - 1 && (id + 1) % Settings.X_TILES != 0) {
    			newTile = tiles[id + 1];
    		}
    	}
    	else if (direction == Direction.ABOVE) {
    		if (id >= Settings.X_TILES) {
    			newTile = tiles[id - Settings.X_TILES];
    		}
    	}
    	else if (direction == Direction.BELOW) {
    		if (id < Settings.NUM_TILES - Settings.X_TILES) {
    			newTile = tiles[id + Settings.X_TILES];
    		}
    	}
    	else if (direction == Direction.LEFT_ABOVE) {
    		newTile = getTileInDirection(getTileInDirection(startTile, Direction.LEFT), Direction.ABOVE);
    	}
    	else if (direction == Direction.RIGHT_ABOVE) {
    		newTile = getTileInDirection(getTileInDirection(startTile, Direction.RIGHT), Direction.ABOVE);
    	}
    	else if (direction == Direction.LEFT_BELOW) {
    		newTile = getTileInDirection(getTileInDirection(startTile, Direction.LEFT), Direction.BELOW);
    	}
    	else if (direction == Direction.RIGHT_BELOW) {
    		newTile = getTileInDirection(getTileInDirection(startTile, Direction.RIGHT), Direction.BELOW);
    	}
    	
    	return newTile;
    }

	/**
	 * Set all adjacent tiles to be legal to be played on.
	 * 
	 * @param tile - newly placed tile
	 */
	private void updateLegalTiles(BoardTile tile) {
		// if there is a tile to the left, make it legal
		BoardTile left = getTileInDirection(tile, Direction.LEFT);
		if (left != null) {
			left.setLegalTile(true);
		}

		// if there is a tile to the right, make it legal
		BoardTile right = getTileInDirection(tile, Direction.RIGHT);
		if (right != null) {
			right.setLegalTile(true);
		}

		// if there is a tile above this one, make it legal
		BoardTile above = getTileInDirection(tile, Direction.ABOVE);
		if (above != null) {
			above.setLegalTile(true);
		}

		// if there is a tile below this one, make it legal
		BoardTile below = getTileInDirection(tile, Direction.BELOW);
		if (below != null) {
			below.setLegalTile(true);
		}
	}
	
	/**
	 * Set all surrounding tiles which should not be played on after removal to be illegal.
	 * 
	 * @param tile - newly removed tile
	 */
	private void updateIllegalTiles(BoardTile tile) {
//		// if there is a tile to the left, make it legal
//		BoardTile left = getTileInDirection(tile, Direction.LEFT);
//		if (left != null) {
//			left.setLegalTile(true);
//		}
//
//		// if there is a tile to the right, make it legal
//		BoardTile right = getTileInDirection(tile, Direction.RIGHT);
//		if (right != null) {
//			right.setLegalTile(true);
//		}
//
//		// if there is a tile above this one, make it legal
//		BoardTile above = getTileInDirection(tile, Direction.ABOVE);
//		if (above != null) {
//			above.setLegalTile(true);
//		}
//
//		// if there is a tile below this one, make it legal
//		BoardTile below = getTileInDirection(tile, Direction.BELOW);
//		if (below != null) {
//			below.setLegalTile(true);
//		}
	}

	public static int getRemainingTiles() {
		return remainingTiles;
	}
	
	/**
	 * Class defining behavior of clicked tiles.
	 */
	private class TileAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			BoardTile tile = (BoardTile) e.getSource();

			if (tile.isPlayableTile() && (remainingTiles > 0 || movedTile != null)) {
				placeNewTile(tile);
			}
			else if (!tile.isEmptyTile() && remainingTiles <= 0 && movedTile == null && tile.getColor() == currentPlayer) {
				movedTile = tile;
			}
		}
	}
}
