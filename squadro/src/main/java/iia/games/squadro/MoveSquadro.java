package iia.games.squadro;

import iia.games.base.IMove;

public class MoveSquadro implements IMove {
	public static int MIN = 0;
	public static int MAX = 4;
	/**indice de la pièce (de 1 à 5)*/
	private int numPiece;
	
	public MoveSquadro(int n) {
		this.numPiece = n;
	}
	
	public int getNumPiece() {
		return this.numPiece;
	}
	
	@Override
	public String toString() {
		return ""+numPiece;
	}
}
