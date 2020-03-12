package iia.games.squadro;

import iia.games.algo.Minimax;
import iia.games.nim.BoardNim;
import iia.games.squadro.BoardSquadro;
import iia.games.squadro.HeuristicSquadro;
import iia.games.squadro.MoveSquadro;
import iia.games.squadro.PlayerSquadro;
import iia.games.squadro.RoleSquadro;

public class DuelSquadro {

    public static void main(String[] args) {
        System.out.println("Hello world !");
        PlayerSquadro p1 = new PlayerSquadro("P1_minimax",
                new Minimax<MoveSquadro, RoleSquadro, BoardSquadro>(RoleSquadro.FIRST, RoleSquadro.SECOND, 5),
                RoleSquadro.FIRST, HeuristicSquadro.hTDFIRST);

        PlayerSquadro p2 = new PlayerSquadro("P2_minimax",
                new Minimax<MoveSquadro, RoleSquadro, BoardSquadro>(RoleSquadro.SECOND, RoleSquadro.FIRST, 5),
                RoleSquadro.SECOND, HeuristicSquadro.hTDSECOND);
        BoardSquadro board = new BoardSquadro();
        
        System.out.println(board);
        System.out.println(" ======= GAME OVER =======");

    }
}
