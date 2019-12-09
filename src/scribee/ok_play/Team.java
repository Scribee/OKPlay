package scribee.ok_play;

/**
 * Enum defining the teams for the game OK Play.
 * 
 * @author Carson Sloan
 * 
 * Created 1/26/19
 * 
 */
public enum Team {
	GREEN("Green"),
	ORANGE("Orange"),
	BLUE("Blue"),
	PINK("Pink"),
	NONE("Empty");
	
	private final String name;
	
	private Team(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
