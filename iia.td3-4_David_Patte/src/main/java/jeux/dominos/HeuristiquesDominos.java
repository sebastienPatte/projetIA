package jeux.dominos;

import iia.jeux.alg.Heuristique;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;


public class HeuristiquesDominos{

	public static  Heuristique hblanc = new Heuristique(){
				
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos newP = (PlateauDominos) p;
			
			if(newP.isJoueurBlanc(j)) {
				//heuristique pour joueur blanc
				return newP.nbCoupsBlanc() - newP.nbCoupsNoir();
			}else {
				//heuristique pour joueur noir
				return newP.nbCoupsBlanc() - newP.nbCoupsNoir();
			}
		}
	};

	public static  Heuristique hnoir = new Heuristique(){
	
		public int eval(PlateauJeu p, Joueur j){
			PlateauDominos newP = (PlateauDominos) p;
			
			if(newP.isJoueurBlanc(j)) {
				//heuristique pour joueur blanc
				return newP.nbCoupsNoir() - newP.nbCoupsBlanc();
			}else {
				//heuristique pour joueur noir
				return newP.nbCoupsNoir() - newP.nbCoupsBlanc();
			}
			
			
			
		}
	};

}
