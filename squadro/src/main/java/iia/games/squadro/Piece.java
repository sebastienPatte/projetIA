package iia.games.squadro;

public class Piece {
	private int d;
	private RoleSquadro role;
	
	public Piece(int d, RoleSquadro r) {
		this.d = d;
		this.role = r;
	}
	
	public RoleSquadro getRole() {
		return role;
	}
	
	public int getD() {
		return this.d;
	}
}
