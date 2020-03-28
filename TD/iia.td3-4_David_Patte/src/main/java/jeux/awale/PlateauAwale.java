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
	
	public PlateauAwale(int[][] plateau) {
		this.plateau = new int[2][TAILLE];
		for(int i=0; i<TAILLE; i++) {
			this.plateau[0][i] = plateau[0][i];
			this.plateau[1][i] = plateau[1][i];
		}
		
	}
	
	public PlateauAwale() {
		this.grainesJ1 = 0;
		this.grainesJ2 = 0;
		initPlateau();
		
	}
	
	private void initPlateau() {
		this.plateau = new int[2][TAILLE];
		for(int i=0; i<TAILLE; i++) {
			plateau[0][i] = 4;
			plateau[1][i] = 4;
		}
	}
	
	public void affichePlateau() {
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
			//on ajoute une case aux coupsPossibles si il ya des graines dedans
			for(int i=0; i<TAILLE; i++) {
				if(coupValide(j,new CoupAwale(i))) {
					res.add(new CoupAwale(i));
				}
			}
		
		return res;
	}

	@Override
	public void joue(Joueur j, CoupJeu c) {
		CoupAwale newC = (CoupAwale) c;
		int numCase = newC.getNumCase();
		int caseInit = numCase;
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
			
			//si on est pas sur la case initiale
			if(numCamp != numJoueur || caseInit != numCase) {
				//on ajoute une graine à la case
				plateau[numCamp][numCase]++;
				nbGraines--;
			}
		}
		
		//quand on a plus de graines 
		
		//simuler collecte
		PlateauAwale plateauTemp = (PlateauAwale)(copy());
		int [][] pTemp = plateauTemp.plateau;
		
		while(	numCamp != numJoueur &&
				(pTemp[numCamp][numCase] == 2 || pTemp[numCamp][numCase]==3 )
			) {
				//capturer graines
				if(numJoueur == 0) {
					//J1
					pTemp[numCamp][numCase] = 0;
					//case suivante
					numCase++;
				}else {
					//J2
					pTemp[numCamp][numCase] = 0;
					//case suivante
					numCase--;
				}
				
				if(numCase >= TAILLE || numCase <0) {
					//si on dépasse la taille du tableau on change de camp (donc le while s'arrette)
					numCamp = 1 - numCamp;
				}
			}
		//test ennemi affamé
		boolean ennemiAffame = true;
			for(int i=0; i<TAILLE; i++) {
				if(pTemp[1-numJoueur][i]!=0) {
					ennemiAffame = false;
				}
			}
		
		
		
		while(ennemiAffame	&& numCamp != numJoueur &&
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
				grainesJ2 += plateau[numCamp][numCase];
				plateau[numCamp][numCase] = 0;
				//case suivante
				numCase--;
			}
			
			if(numCase >= TAILLE || numCase <0) {
				//si on dépasse la taille du tableau on change de camp (donc le while s'arrette)
				numCamp = 1 - numCamp;
			}
		}
		
	}

	private int grainesEnJeu() {
		int res=0;
		for(int i=0; i<TAILLE; i++) {
			res+= plateau[0][i];
			res+= plateau[1][i];
		}
		return res;
	}
	
	@Override
	public boolean finDePartie() {
		if(grainesJ1 >= 25 || grainesJ2 >= 25 || grainesEnJeu() <= 6){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PlateauJeu copy() {
		return new PlateauAwale(this.plateau);
	}

	@Override
	public boolean coupValide(Joueur j, CoupJeu c) {
		CoupAwale newC = (CoupAwale) c;
		if(j.equals(J1)) {
			//J1
			//si case non vide
			if(plateau[0][newC.getNumCase()] > 0) {
				PlateauAwale pTemp = (PlateauAwale) copy();
				pTemp.joue(j, c);
				for(int i=0; i<TAILLE; i++) {
					//si une case ennemie n'est pas vide
					if(plateau[1][i] > i) {
						return true;
					}
				}
				return false;
			
			}
			//case vide
			return false;
			
			
			
		}else {
			//J2
			//si case non vide
			if(plateau[1][newC.getNumCase()] > 0) {
				PlateauAwale pTemp = (PlateauAwale) copy();
				pTemp.joue(j, c);
				for(int i=0; i<TAILLE; i++) {
					//si une case ennemie n'est pas vide
					if(plateau[0][i] > TAILLE-i) {
						return true;
					}
				}
				return false;
			}
			//case vide
			return false;
		}
	}
	
	public boolean isJ1(Joueur j) {
		return j.equals(J1);
	}
	
	public boolean isJ2(Joueur j) {
		return j.equals(J2);
	}
	
	public int getGrainesJ1() {
		return grainesJ1;
	}
	
	public int getGrainesJ2() {
		return grainesJ2;
	}
	
	/************* Gestion des paramètres de classe** ****************/ 

	public static void setJoueurs(Joueur j1, Joueur j2) {
		J1 = j1;
		J2 = j2;
	}

}
