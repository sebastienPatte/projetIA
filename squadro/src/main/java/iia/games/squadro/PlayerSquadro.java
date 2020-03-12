package iia.games.squadro;

import iia.games.base.APlayer;
import iia.games.base.IAlgo;
import iia.games.base.IHeuristic;
import iia.games.base.IPlayer;
import iia.games.squadro.BoardSquadro;
import iia.games.squadro.MoveSquadro;
import iia.games.squadro.RoleSquadro;

public class PlayerSquadro extends APlayer<MoveSquadro, RoleSquadro, BoardSquadro> {
	
	private IAlgo<MoveSquadro, RoleSquadro, BoardSquadro> algo;
    private RoleSquadro role;
    private IHeuristic<RoleSquadro, BoardSquadro> heuristic;
    private String name;
    
    public PlayerSquadro(String name, IAlgo<MoveSquadro, RoleSquadro, BoardSquadro> algo, RoleSquadro role, IHeuristic<RoleSquadro, BoardSquadro> h) {
    	super(name, algo, role, h);
    }

    @Override
    public MoveSquadro chooseMove(BoardSquadro board) {
    	// TODO Auto-generated method stub
    	return algo.bestMove(board, role, heuristic);
    }
}
