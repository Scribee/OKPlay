package scribee.ok_play;

public class Player {
	private Player nextPlayer;
	private Team team;
	private int tiles;
	
	public Player() {
		
	}
	
	public Player(Team team, int tiles) {
		this.tiles = tiles;
		this.team = team;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team newTeam) {
		team = newTeam;
	}
	
	public int getRemainingTiles() {
		return tiles;
	}
	
	public void removeTile() {
		tiles--;
	}
	
	public void resetTilesRemaining(int startingTiles) {
		this.tiles = startingTiles;
	}
	
	public Player getNextPlayer() {
		return nextPlayer;
	}
	
	public void setNextPlayer(Player player) {
		nextPlayer = player;
	}
}
