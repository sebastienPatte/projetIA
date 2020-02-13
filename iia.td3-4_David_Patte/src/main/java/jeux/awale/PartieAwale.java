package jeux.awale;

import iia.jeux.modele.joueur.Joueur;

public class PartieAwale {
	public static void main(String[] args) {
		PlateauAwale.setJoueurs(new Joueur("J1"), new Joueur("J2"));
		PlateauAwale p = new PlateauAwale();
	}
}
