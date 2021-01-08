package Entity;

public enum DirectionEnum {
	LEFT(-1, 0), RIGHT(1, 0);

	private int x;
	private int y;
	
	DirectionEnum(int x, int y) {
		this.x = x;
		this.y = y;
	}	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
// Gestion des direction pas besoin de haut et bas avec mon impl�mentation
	
}
