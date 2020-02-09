package iia.jeux.alg;

import iia.jeux.modele.CoupJeu;
import iia.jeux.modele.PlateauJeu;
import iia.jeux.modele.joueur.Joueur;
import jeux.dominos.CoupDominos;
import jeux.dominos.PlateauDominos;

public class AlphaBeta implements AlgoJeu{
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
    public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin) {
        this(h,joueurMax,joueurMin,PROFMAXDEFAUT);
    }

    public AlphaBeta(Heuristique h, Joueur joueurMax, Joueur joueurMin, int profMaxi) {
        this.h = h;
        this.joueurMin = joueurMin;
        this.joueurMax = joueurMax;
        this.profMax = profMaxi;
    }

   // -------------------------------------------
  // Méthodes de l'interface AlgoJeu
  // -------------------------------------------
   public CoupJeu meilleurCoup(PlateauJeu p) {
        /* A vous de compléter le corps de ce fichier */
        
        CoupJeu coupMax = p.coupsPossibles(this.joueurMax).get(0);
        PlateauDominos pTempCoup1 = (PlateauDominos) p.copy();
    	pTempCoup1.joue(this.joueurMax, p.coupsPossibles(this.joueurMax).get(0));
    	int hMax =  minMax(pTempCoup1,-999,999);
        
    	
    	System.out.println("nbCoupsPossibles = "+p.coupsPossibles(this.joueurMax).size());
        for(int i =1; i < p.coupsPossibles(this.joueurMax).size();i++) {
        	//System.out.println("i="+i);
        	PlateauDominos pTemp = (PlateauDominos) p.copy();
        	CoupDominos coup = (CoupDominos) p.coupsPossibles(this.joueurMax).get(i);
        	pTemp.joue(this.joueurMax, coup);
        	
        	int hTemp = minMax(pTemp,-999,999);
        	
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
        return "AlphaBeta(ProfMax="+profMax+")";
    }



  // -------------------------------------------
  // Méthodes internes
  // -------------------------------------------

    //A vous de jouer pour implanter Minimax
    
    private int maxMin(PlateauDominos p, int alpha, int beta){
    	
    	//System.out.println("maxMin profMax = "+profMax);
    	if(this.profMax <= 1 || p.finDePartie()) {
    		nbfeuilles++;
    		return h.eval(p, joueurMax);
    	}else {
    		//eval favorable
    		nbnoeuds++;
    		for(CoupJeu coup : p.coupsPossibles(this.joueurMax)) {
    			//on simule coup
    			PlateauDominos pTemp = (PlateauDominos) p.copy();
            	pTemp.joue(this.joueurMax, coup);
    	
            	//appel rec
            	profMax--;
            	alpha = Math.max(alpha, minMax(pTemp,alpha,beta));
            	profMax++;
    			if(alpha >= beta) {
    				return beta;
    			}
    		}
    		return alpha;
    	}
    }
    private int minMax(PlateauDominos p, int alpha, int beta){
    	
    	//System.out.println("minMax profMax = "+profMax);
    	if(this.profMax <= 1|| p.finDePartie()) {
    		nbfeuilles++;
    		return h.eval(p, joueurMax);
    	}else {
    		//eval non favorable
    		nbnoeuds++;
    		for(CoupJeu coup : p.coupsPossibles(this.joueurMax)) {
    			//on simule coup
    			PlateauDominos pTemp = (PlateauDominos) p.copy();
            	pTemp.joue(this.joueurMax, coup);
            	//appel rec
            	profMax--;
            	beta = Math.min(beta, maxMin(pTemp,alpha,beta));
            	profMax++;
            	if(alpha >= beta) {
    				return alpha;
    			}
    		}
    		return beta;
    	}
    }
}
