package iia.simpleGame.squadro;

import iia.simpleGame.base.IGame;

public class SquadroGameV extends ASquadroGame {

    public SquadroGameV(){
        super();
    }

    @Override
    public int getValue(String role) {
        if (role == ASquadroGame.ROLE_SECOND) {
            return getNbPieces(ASquadroGame.ROLE_SECOND);
        }
        else {
           	return getNbPieces(ASquadroGame.ROLE_FIRST);
        }
    }
    
    @Override
    public IGame play(String move, String role) {
    	return super.play(move, role);
    }
}
