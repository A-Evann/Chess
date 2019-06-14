import java.util.ArrayList;

public class Roi extends Piece {

	
    public Roi() {
        super(); 
    }
    public Roi(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    
    
    public boolean mouvementValide(int colonne, int ligne) {
    	int dist_co = this.getColonne() - colonne; // distance en colonne
    	int dist_li = this.getLigne() - ligne;	  // distance en ligne
    	dist_co = Math.abs(dist_co);  //valeur absolue
    	dist_li = Math.abs(dist_li);
    	boolean diagonal = (dist_co == dist_li);//est-ce une diagonale ?
    	/*
    	 on bouge pas de colonne ET on bouge de 1 en ligne
    	 OU on bouge pas en ligne et on bouge de 1 en colonne
    	 OU on bouge de 1 en diagonal
    	 */
    	if ((colonne == this.getColonne() && dist_li == 1) || (ligne == this.getLigne() && dist_co == 1) || (diagonal && dist_co==1)) {
    		//System.out.println("Mouvement Valide"); //msg debug
    		return true;
    	}
    	//System.out.println("Mouvement non Valide"); //msg debug
    	return false;
    }
    public boolean priseValide(int colonne, int ligne) {
    	return this.mouvementValide(colonne, ligne);
    }
    public String toString() {
    	if(this.getCouleur()==0) {
    		return "\u265A";
    	}
    	else return "\u2654";
    }
    public ArrayList<Integer> portee(){
    	//ici on reprend la methode de la reine en changeant les while par des if
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	
    	int[] colonne = {0,1,1,1,0,-1,-1,-1};
    	int[] ligne = {-1,-1,0,1,1,1,0,-1};
    	
    	for (int i = 0; i < colonne.length; i++) {
    		if((colonne[i] < 8 && ligne[i] < 8) && (colonne[i] >= 0 && ligne[i] >= 0)){
        		les_cases.add((8 * ligne[i]) + colonne[i]);
    		}
    	}
    	
    	return les_cases;
    }
    public ArrayList<Integer> porteeMvt() {
    	return this.portee();
    }

}
