package iia.simpleGame.squadro;

import iia.simpleGame.algo.Minimax;
import iia.simpleGame.base.IGame;
import iia.simpleGame.base.Player;
import iia.simpleGame.squadro.SquadroGameH;
import iia.simpleGame.squadro.SquadroGameV;

public class PlaySquadro {
    public static void main(String[] args) {
        System.out.println("Hello world !");
        
        
        Player p1 = new Player("J1", ASquadroGame.ROLE_FIRST,
        		new Minimax(ASquadroGame.ROLE_FIRST, ASquadroGame.ROLE_SECOND, 5));
        
        Player p2 = new Player("J2", ASquadroGame.ROLE_SECOND,
        		new Minimax(ASquadroGame.ROLE_SECOND, ASquadroGame.ROLE_FIRST, 5));
        
        IGame game_1 = new SquadroGameH(); // horizontal = ROLE_FIRST
        IGame game_2 = new SquadroGameV(); // vertical = ROLE_SECOND
        
        System.out.println(game_1);
        
        System.out.println("mouvements possibles J1 : "+game_1.possibleMoves(ASquadroGame.ROLE_FIRST));
        System.out.println(" ======= GAME OVER =======");
    }
}
