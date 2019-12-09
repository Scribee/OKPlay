package scribee.ok_play;

public enum Direction {
	LEFT,
	RIGHT,
	ABOVE,
	BELOW,
	LEFT_ABOVE,
	RIGHT_ABOVE,
	LEFT_BELOW,
	RIGHT_BELOW;
	
	public Direction getOpposite() {
		if (this == LEFT) {
			return RIGHT;
		}
		else if (this == RIGHT) {
			return LEFT;
		}
		else if (this == ABOVE) {
			return BELOW;
		}
		else if (this == BELOW) {
			return ABOVE;
		}
		else if (this == LEFT_ABOVE) {
			return RIGHT_BELOW;
		}
		else if (this == RIGHT_ABOVE) {
			return LEFT_BELOW;
		}
		else if (this == LEFT_BELOW) {
			return RIGHT_ABOVE;
		}
		else if (this == RIGHT_BELOW) {
			return LEFT_ABOVE;
		}
		else {
			return null;
		}
	}
}
