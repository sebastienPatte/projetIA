package jeux.awale;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;

public class PlateauAwale implements PlateauJeu{
	public final static int TAILLE = 6;
	
	
	private static Joueur J1;
	private int grainesJ1;
	
	private static Joueur J2;
	private int grainesJ2;
	
	private int[][] plateau;
	
	public PlateauAwale() {
		this.grainesJ1 = 0;
		this.grainesJ2 = 0;
		initPlateau();
		affichePlateau();
		System.out.println("J1 joue 0");
		joue(J1, new CoupAwale(0));
		affichePlateau();
		System.out.println("J2 joue 0");
		joue(J2, new CoupAwale(0));
		affichePlateau();
		System.out.println("J1 joue 1");
		joue(J1, new CoupAwale(1));
		affichePlateau();
		System.out.println("J2 joue 1");
		joue(J2, new CoupAwale(1));
		affichePlateau();
		System.out.println("J1 joue 2");
		joue(J1, new CoupAwale(2));
		affichePlateau();
		System.out.println("J2 joue 2");
		joue(J2, new CoupAwale(2));
		affichePlateau();
		System.out.println("J1 joue 3");
		joue(J1, new CoupAwale(3));
		affichePlateau();
	}
	
	private void initPlateau() {
		this.plateau = new int[2][TAILLE];
		for(int i=0; i<TAILLE; i++) {
			plateau[0][i] = 4;
			plateau[1][i] = 4;
		}
	}
	
	private void affichePlateau() {
		for(int  i=0; i<TAILLE; i++) {
			System.out.print(" "+plateau[0][i]);
		}
		System.out.println();
		
		for(int  i=0; i<TAILLE; i++) {
			System.out.print(" "+plateau[1][i]);
		}
		System.out.println();
		System.out.println();
	}
	
	@Override
	public ArrayList<CoupJeu> coupsPossibles(Joueur j) {
		ArrayList<CoupJeu> res = new ArrayList<CoupJeu>();
		if(j.equals(J1)) {
			//on ajoute une case aux coupsPossibles si il ya des graines dedans
			for(int i=0; i<TAILLE; i++) {
				if(plateau[0][i]>0) {
					res.add(new CoupAwale(i));
				}
			}
		}else {
			if(j.equals(J2)) {
				//on ajoute une case aux coupsPossibles si il ya des graines dedans
				for(int i=0; i<TAILLE; i++) {
					if(plateau[1][i]>0) {
						res.add(new CoupAwale(i));
					}
				}
			}
		}
		return res;
	}

	@Override
	public void joue(Joueur j, CoupJeu c) {
		CoupAwale newC = (CoupAwale) c;
		int numCase = newC.getNumCase();
		// camp 0 pour J1 ou camp 1 pour J1
		int numCamp = 0;
		if(j.equals(J2)) {
			numCamp = 1;
		}
		int numJoueur = numCamp;
		
		//nombre de graines à déposer
		int nbGraines = plateau[numCamp][numCase];
		//on vide la case jouée
		plateau[numCamp][numCase]=0;
		//tant qu'il reste des graines à déposer
		while(nbGraines > 0){
			//case suivante
			if(numCamp==0) {
				//camp 0 -> on décrémente
				numCase--;
				if(numCase < 0) {
					//si on dépasse la taille du tableau on change de camp
					numCamp = 1 - numCamp;
					numCase=0;
				}
				
			}else {
				//camp 1 -> on incrémente
				numCase++;
				if(numCase >= TAILLE) {
					//si on dépasse la taille du tableau on change de camp
					numCamp = 1 - numCamp;
					numCase=TAILLE-1;
				}
			}
			
			//on ajoute une graine à la case
			plateau[numCamp][numCase]++;
			nbGraines--;
		}
		//quand on a plus de graines 
		while(	numCamp != numJoueur &&
			(plateau[numCamp][numCase] == 2 || plateau[numCamp][numCase]==3 )
		) {
			//capturer graines
			if(numJoueur == 0) {
				//J1
				grainesJ1 += plateau[numCamp][numCase];
				plateau[numCamp][numCase] = 0;
				//case suivante
				numCase++;
			}else {
				//J2
				grainesJ1 += plateau[numCamp][numCase];
				plateau[numCamp][numCase] = 0;
				//case suivante
				numCase++;
			}
			
			if(numCase >= TAILLE || numCase <0) {
				//si on dépasse la taille du tableau on change de camp (donc le while s'arrette)
				numCamp = 1 - numCamp;
			}
		}
		
	}

	@Override
	public boolean finDePartie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlateauJeu copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean coupValide(Joueur j, CoupJeu c) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/************* Gestion des paramètres de classe** ****************/ 

	public static void setJoueurs(Joueur j1, Joueur j2) {
		J1 = j1;
		J2 = j2;
	}

}
