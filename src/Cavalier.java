import java.util.ArrayList;
public class Cavalier extends Piece {
   public Cavalier() {
        super();
    }

    public Cavalier(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    public boolean mouvementValide(int colonne, int ligne) {
    	int dist_co = this.getColonne() - colonne; // distance en colonne
    	int dist_li = this.getLigne() - ligne;	  // distance en ligne
    	dist_co = Math.abs(dist_co);  //valeur absolue
    	dist_li = Math.abs(dist_li);
    	
    	/*
    	 colonne 2 et ligne 1 
    	 OU
    	 ligne 2 et colonne 1
    	 */
    	if((dist_co == 2 && dist_li == 1) || (dist_li == 2 && dist_co == 1) ) {
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
    		return "\u265E";
    	}
    	else return "\u2658";
    }
    public ArrayList<Integer> portee() {
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	int colonne = this.getColonne() + 2;
    	int ligne = this.getLigne() + 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	ligne = this.getLigne() - 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	
    	colonne = this.getColonne() - 2;
    	ligne = this.getLigne() + 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	ligne = this.getLigne() - 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	
    	colonne = this.getColonne() + 1;
    	ligne = this.getLigne() + 2;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	colonne = this.getColonne() - 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	
    	colonne = this.getColonne() + 1;
    	ligne = this.getLigne() - 2;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	colonne = this.getColonne() - 1;
    	if ((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)) {
    		les_cases.add((8*ligne) + colonne);
    	}
    	return les_cases;
    }
    public ArrayList<Integer> porteeMvt() {
    	return this.portee();
    }

}
