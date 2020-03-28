package iia.simpleGame.squadro;

import iia.simpleGame.squadro.Piece;
import iia.simpleGame.base.AGame;
import iia.simpleGame.base.IGame;

import java.util.ArrayList;

public abstract class ASquadroGame extends AGame {
	
	public static int TAILLE = 7;
	public static int[] deplacementJ = {1,3,2,3,1};
	public static int[] deplacementR = {3,1,2,1,3};
	
	public static final String ROLE_FIRST = "FIRST";
    public static final String ROLE_SECOND = "SECOND";
    
	private Piece[][] grille;
	
    public ASquadroGame(){
    	this.grille = new Piece[TAILLE][TAILLE];
    	initPieces();
    }

    @Override
    public IGame play(String move, String role) {
        // TODO !
        return null;
    }

    @Override
    public ArrayList<String> possibleMoves(String role) {
    	ArrayList<String> moves = new ArrayList<String>();
    	for(int i=1; i<=5; i++) {
        	if(!pieceFini(i, role)) {
        		moves.add(Integer.toString(i));
        	}
        }
    	return moves;
    }

    @Override
    public boolean isValidMove(String move, String role) {
    	return Integer.parseInt(move) >= 1 && Integer.parseInt(move) <= 5;
    }

    @Override
    public boolean isGameOver() {
    	return !restePiece("FIRST") || !restePiece("FIRST");
    }
    
    @Override
    public String toString() {
    	String res = "";
    	for(int i=0; i<TAILLE; i++) {
    		for(int j=0; j<TAILLE; j++) {
    			if(grille[i][j] == null) {
    				res+="+";
    			}else {
    				if(grille[i][j].getRole() == "FIRST") {
    					if(grille[i][j].getDeplacement()>0){
    						res+=">";
    					}else {
    						res+="<";
						}
    				}else {
    					if(grille[i][j].getRole() == "SECOND") {
    						if(grille[i][j].getDeplacement()>0){
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
    
    /**
     * Initialise le plateau de jeu
     */
    private void initPieces() {
		for(int i=1; i<TAILLE-1; i++) {
			this.grille[i][0] = new Piece(deplacementJ[i-1],"FIRST");
			this.grille[TAILLE-1][i] = new Piece(deplacementR[i-1],"SECOND");
		}
	}
    
    /**
     * @param i
     * @param role
     * @return true si la pièce a gagné, false sinon
     */
    private boolean pieceFini(int i, String role) {
    	if(role == "FIRST"){
    		return (grille[i][0] != null && grille[i][0].getDeplacement() < 0);
    	}else {
    		return (grille[TAILLE-1][i] != null && grille[i][0].getDeplacement() < 0);
    	}
    }
    
    /**
     * @param role
     * @return true si le joueur a encore des pièces à jouer
     */
    private boolean restePiece(String role) {
    	for(int i=1;i<=5;i++) {
    		if( !pieceFini(i, role)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * @param role
     * @return le nombre de pièces restantes à jouer pour ce joueur
     */
    public int getNbPieces(String role) {
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
}
