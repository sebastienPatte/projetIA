package jeux.awale;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;


public class HeuristiquesAwale {
	public static  Heuristique hj1 = new Heuristique(){
		
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale newP = (PlateauAwale) p;
			return newP.getGrainesJ1();
		}
	};

	public static  Heuristique hj2 = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauAwale newP = (PlateauAwale) p;
			return newP.getGrainesJ2();
		}
	};

}
