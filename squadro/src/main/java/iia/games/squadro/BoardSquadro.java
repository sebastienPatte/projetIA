package iia.games.squadro;

import iia.games.base.ABoard;

import java.util.ArrayList;

public class BoardSquadro extends ABoard<MoveSquadro, RoleSquadro, BoardSquadro> {

    /*
        INTERFACE
     */
	public static int TAILLE = 7;
	public static int[] deplacementJ = {1,3,2,3,1};
	public static int[] deplacementR = {3,1,2,1,3};
	private Piece[][] grille;
	
	public BoardSquadro() {
		this.grille = new Piece[TAILLE][TAILLE];
		initPieces();
	}

	
    @Override
    public BoardSquadro play(MoveSquadro move, RoleSquadro role) {
        return null;
    }

    @Override
    public ArrayList<MoveSquadro> possibleMoves(RoleSquadro role) {
    	ArrayList<MoveSquadro> moves = new ArrayList<MoveSquadro>();
    	for(int i=1; i<=5; i++) {
        	if(!pieceFini(i, role)) {
        		moves.add(new MoveSquadro(i));
        	}
        }
    	return moves;
    }


    @Override
    public boolean isGameOver() {
    	return !restePiece(RoleSquadro.FIRST) || !restePiece(RoleSquadro.FIRST);
    }

    @Override
    public boolean isValidMove(MoveSquadro move, RoleSquadro role) {
            return move.getNumPiece() >= 1 && move.getNumPiece() <= 5;
    }

    
    
    @Override
    public String toString() {
    	String res = "";
    	for(int i=0; i<TAILLE; i++) {
    		for(int j=0; j<TAILLE; j++) {
    			if(grille[i][j] == null) {
    				res+="+";
    			}else {
    				if(grille[i][j].getRole() == RoleSquadro.FIRST) {
    					if(grille[i][j].getD()>0){
    						res+=">";
    					}else {
    						res+="<";
						}
    				}else {
    					if(grille[i][j].getRole() == RoleSquadro.SECOND) {
    						if(grille[i][j].getD()>0){
        						res+="^";
        					}else {
        						res+="v";
    						}
        				}
    				}
    			}
    		}
    		res+="\n";
    	}
    	return res;
    }
    
    //méthodes supplémentaires
    
    private void initPieces() {
		for(int i=1; i<TAILLE-1; i++) {
			this.grille[i][0] = new Piece(deplacementJ[i-1],RoleSquadro.FIRST);
			this.grille[TAILLE-1][i] = new Piece(deplacementR[i-1],RoleSquadro.SECOND);
		}
	}
    
    public int getNbPieces(RoleSquadro role) {
    	int res=0;
    	for(int i=0; i<TAILLE; i++) {
    		for(int j=0; j<TAILLE; j++) {
    			if(grille[i][j] != null && grille[i][j].getRole() == role) {
    				res++;
    			}
    		}
    	}
    	return res;
    }
    
    private boolean pieceFini(int i, RoleSquadro role) {
    	if(role == RoleSquadro.FIRST){
    		return (grille[i][0] != null && grille[i][0].getD() < 0);
    	}else {
    		return (grille[TAILLE-1][i] != null && grille[i][0].getD() < 0);
    	}
    	
    	
    }
    
    
    private boolean restePiece(RoleSquadro role) {
    	for(int i=1;i<=5;i++) {
    		if( !pieceFini(i, role)) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
