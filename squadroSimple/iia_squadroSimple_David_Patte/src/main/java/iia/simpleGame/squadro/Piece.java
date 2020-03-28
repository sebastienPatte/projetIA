package iia.simpleGame.squadro;

public class Piece {
	private int deplacement;
	private String role;
	public Piece(int d,String r) {
		this.deplacement = d;
		this.role = r;
	}
	
	public int getDeplacement() {
		return this.deplacement;
	}
	
	public String getRole() {
		return this.role;
	}
}
