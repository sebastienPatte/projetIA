package jeux.awale;

import java.util.ArrayList;

import iia.jeux.alg.AlgoJeu;
import iia.jeux.alg.AlphaBeta;
import iia.jeux.alg.Minimax;
import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.joueur.Joueur;

public class PartieAwale {
	public static void main(String[] args) {
		Joueur J1 = new Joueur("J1");
        Joueur J2 = new Joueur("J2");
        Joueur[] lesJoueurs = new Joueur[2];
        lesJoueurs[0] = J1;
        lesJoueurs[1] = J2;
        
        AlgoJeu AlgoJoueur[] = new AlgoJeu[2];
        AlgoJoueur[0] = new AlphaBeta(HeuristiquesAwale.hj1, J1, J2);
        AlgoJoueur[1] = new Minimax(HeuristiquesAwale.hj2, J2, J1);

        System.out.println("TD IIA n.4 - Algorithmes pour les Jeux");
        System.out.println("Etat Initial du plateau de jeu:");
        
        boolean jeufini = false;
        CoupJeu meilleurCoup = null;
        int jnum = 0;
        PlateauAwale.setJoueurs(J1,J2);
        PlateauAwale p = new PlateauAwale();
		


		 int nbCoups = 0;
	        while (!jeufini) {
	            nbCoups++;
	        	p.affichePlateau();
	            System.out.println("C'est au joueur " + lesJoueurs[jnum] + " de jouer.");
	            // Vérifie qu'il y a bien des coups possibles
	            // Ce n'est pas tres efficace, mais c'est plus rapide... a écrire...
	            ArrayList<CoupJeu> lesCoupsPossibles = p.coupsPossibles(lesJoueurs[jnum]);
	            System.out.println("Coups possibles pour" + lesJoueurs[jnum] + " : " + lesCoupsPossibles);
	            if (lesCoupsPossibles.size() > 0) {
	                // On écrit le plateau

	                // Lancement de l'algo de recherche du meilleur coup
	                System.out.println("Recherche du meilleur coup avec l'algo " + AlgoJoueur[jnum]);
	                meilleurCoup = AlgoJoueur[jnum].meilleurCoup(p);
	                System.out.println("Coup joué : " + meilleurCoup + " par le joueur " + lesJoueurs[jnum]);

	                p.joue(lesJoueurs[jnum], meilleurCoup);
	                // Le coup est effectivement joué
	                jnum = 1 - jnum;
	                p.affichePlateau();
	            } else {
	                System.out.println("Le joueur " + lesJoueurs[jnum] + " ne peut plus jouer et abandonne !");
	                System.out.println("Le joueur " + lesJoueurs[1 - jnum] + " a gagné cette partie !");
	                jeufini = true;

	            }
	        }
	        System.out.println("nbCoups = "+nbCoups);

	}
}
