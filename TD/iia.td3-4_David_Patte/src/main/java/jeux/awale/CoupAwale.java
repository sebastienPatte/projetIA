package jeux.awale;

import iia.jeux.modele.CoupJeu;

public class CoupAwale implements CoupJeu{
	/****** Attributs *******/ 


	private int numCase;
	
	public CoupAwale(int c) {
		this.numCase = c;
	}

	/****** Accesseurs *******/ 

	public int getNumCase() {
		return numCase;
	}
	
	public String toString() {
		return ""+numCase;
	}
}
