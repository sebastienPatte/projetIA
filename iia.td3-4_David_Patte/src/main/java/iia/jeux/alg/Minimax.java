/**
 * 
 */

package iia.jeux.alg;

import java.util.ArrayList;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;
import jeux.dominos.CoupDominos;
import jeux.dominos.HeuristiquesDominos;
import jeux.dominos.PlateauDominos;

public class Minimax implements AlgoJeu {

    /** La profondeur de recherche par défaut
     */
    private final static int PROFMAXDEFAUT = 4;

   
    // -------------------------------------------
    // Attributs
    // -------------------------------------------
 
    /**  La profondeur de recherche utilisée pour l'algorithme
     */
    private int profMax = PROFMAXDEFAUT;

     /**  L'heuristique utilisée par l'algorithme
      */
   private Heuristique h;

    /** Le joueur Min
     *  (l'adversaire) */
    private Joueur joueurMin;

    /** Le joueur Max
     * (celui dont l'algorithme de recherche adopte le point de vue) */
    private Joueur joueurMax;

    /**  Le nombre de noeuds développé par l'algorithme
     * (intéressant pour se faire une idée du nombre de noeuds développés) */
       private int nbnoeuds;

    /** Le nombre de feuilles évaluées par l'algorithme
     */
    private int nbfeuilles;


  // -------------------------------------------
  // Constructeurs
  // -------------------------------------------
    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public Minimax(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        this.profMax = profMaxi;
//		System.out.println("Initialisation d'un MiniMax de profondeur " + profMax);
    }

   // -------------------------------------------
  // Méthodes de l'interface AlgoJeu
  // -------------------------------------------
   public CoupJeu meilleurCoup(PlateauJeu p) {
        /* A vous de compléter le corps de ce fichier */
        
        CoupJeu coupMax = p.coupsPossibles(this.joueurMax).get(0);
        PlateauDominos pTempCoup1 = (PlateauDominos) p.copy();
    	pTempCoup1.joue(this.joueurMax, p.coupsPossibles(this.joueurMax).get(0));
    	int hMax =  maxMin(pTempCoup1);
        
    	
    	System.out.println("nbCoupsPossibles = "+p.coupsPossibles(this.joueurMax).size());
        for(int i =1; i < p.coupsPossibles(this.joueurMax).size();i++) {
        	//System.out.println("i="+i);
        	PlateauDominos pTemp = (PlateauDominos) p.copy();
        	CoupDominos coup = (CoupDominos) p.coupsPossibles(this.joueurMax).get(i);
        	pTemp.joue(this.joueurMax, coup);
        	
        	int hTemp = maxMin(pTemp);
        	
        	if(hTemp > hMax) {
        		hMax = hTemp;
        		coupMax = coup;
        	}
        	profMax = PROFMAXDEFAUT;
        	
        }
        System.out.println("nbFeuilles = "+nbfeuilles+" nbNoeuds = "+nbnoeuds);
        nbfeuilles=0;
        nbnoeuds=0;
        return coupMax;
    }
  // -------------------------------------------
  // Méthodes publiques
  // -------------------------------------------
    public String toString() {
        return "MiniMax(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // Méthodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax
    
    private int maxMin(PlateauDominos p){
    	
    	//System.out.println("maxMin profMax = "+profMax);
    	if(this.profMax <= 1 || p.finDePartie()) {
    		nbfeuilles++;
    		return h.eval(p, joueurMax);
    	}else {
    		//eval favorable
    		nbnoeuds++;
    		int maxi = -999; //a modifier pour d'autres jeux    		
    		for(CoupJeu coup : p.coupsPossibles(this.joueurMax)) {
    			//on simule coup
    			PlateauDominos pTemp = (PlateauDominos) p.copy();
            	pTemp.joue(this.joueurMax, coup);
    	
            	//appel rec
            	profMax--;
            	maxi = Math.max(maxi, minMax(pTemp));
            	profMax++;
    			
    		}
    		return maxi;
    	}
    }
    private int minMax(PlateauDominos p){
    	
    	//System.out.println("minMax profMax = "+profMax);
    	if(this.profMax <= 1|| p.finDePartie()) {
    		nbfeuilles++;
    		return h.eval(p, joueurMax);
    	}else {
    		//eval favorable
    		nbnoeuds++;
    		int mini = +999; //a modifier pour d'autres jeux
    		for(CoupJeu coup : p.coupsPossibles(this.joueurMax)) {
    			//on simule coup
    			PlateauDominos pTemp = (PlateauDominos) p.copy();
            	pTemp.joue(this.joueurMax, coup);
            	//appel rec
            	profMax--;
            	mini = Math.min(mini, maxMin(pTemp));
            	profMax++;
    		}
    		return mini;
    	}
    }
}
