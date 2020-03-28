package iia.simpleGame.squadro;

import iia.simpleGame.base.IGame;

public class SquadroGameH extends ASquadroGame {

    public SquadroGameH(){
        super();
    }

    @Override
    public int getValue(String role) {
    	if (role == ASquadroGame.ROLE_FIRST) {
            return getNbPieces(ASquadroGame.ROLE_FIRST);
        }
        else {
           	return getNbPieces(ASquadroGame.ROLE_SECOND);
        }
    }
    
    @Override
    public IGame play(String move, String role) {
    	return super.play(move, role);
    }
}
