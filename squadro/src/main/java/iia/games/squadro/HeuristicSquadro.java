package iia.games.squadro;

import iia.games.base.IHeuristic;
import iia.games.squadro.BoardSquadro;
import iia.games.squadro.RoleSquadro;


public class HeuristicSquadro  {
    
	//public static final IHeuristic<RoleSquadro, BoardSquadro> hZero = (boardSquadro, r) -> 0;
	
    public static final IHeuristic<RoleSquadro, BoardSquadro> hTDFIRST = (boardSquadro, r) -> {
	        
	        if (r == RoleSquadro.SECOND) {
	            return boardSquadro.getNbPieces(RoleSquadro.SECOND);
	        }
	        else {
	           	return boardSquadro.getNbPieces(RoleSquadro.FIRST);
	        }
	    };

	    public static final IHeuristic<RoleSquadro, BoardSquadro> hTDSECOND = (boardSquadro, r) -> {
	        
	        if (r == RoleSquadro.FIRST) {
	        	return boardSquadro.getNbPieces(RoleSquadro.FIRST);
	        }
	        else {
	        	return boardSquadro.getNbPieces(RoleSquadro.SECOND);
	        }
	    };
}
