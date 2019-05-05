import java.util.ArrayList;

public class Dame extends Piece {
	
    public Dame() {
        super();
    }
    
    public Dame(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    
    public boolean mouvementValide(int colonne, int ligne) {
    	
    	int dist_co = this.getColonne() - colonne; // distance en colonne
    	int dist_li = this.getLigne() - ligne;	  // distance en ligne
    	dist_co = Math.abs(dist_co);  //valeur absolue
    	dist_li = Math.abs(dist_li);
    	// comparaison: si distance ligne == distance colonne: mouvement en diagonal: autorisé
    	
    	/*
    	 on bouge pas de colonne
    	 OU on bouge pas de ligne
    	 OU on fait une diagonale
    	 */
    	
    	if (colonne == this.getColonne() || ligne == this.getLigne() || dist_co == dist_li) {
    		System.out.println("Mouvement Valide"); //msg debug
    		return true;
    	}
    	System.out.println("Mouvement non Valide"); //msg debug
    	return false;
    }
    public boolean priseValide(int colonne, int ligne) {
    	return this.mouvementValide(colonne, ligne);
    }
    public String toString() {
    	if(this.getCouleur()==0) {
    		return "D";
    	}
    	else return "d";
    }
    public ArrayList<Integer> portee(){
    	//pour la dame on reprend la methode du fou et celle de la tour
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	int colonne = this.getColonne() + 1;
    	int ligne = this.getLigne() + 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne++;
    		ligne++;
    	}
    	colonne = this.getColonne() + 1;
    	ligne = this.getLigne() - 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne++;
    		ligne--;
    	}
    	colonne = this.getColonne() - 1;
    	ligne = this.getLigne() + 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne--;
    		ligne++;
    	}
    	colonne = this.getColonne() - 1;
    	ligne = this.getLigne() - 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne--;
    		ligne--;
    	}
    	colonne = this.getColonne() + 1;
    	ligne = this.getLigne();
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne++;
    	}
    	//c--
    	colonne = this.getColonne() - 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne--;
    	}
    	//mtn c'est l++
    	colonne = this.getColonne();
    	ligne = this.getLigne() + 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		ligne++;
    	}
    	//l--
    	ligne = this.getLigne() - 1;
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		ligne--;
    	}
    	return les_cases;
    }
    public ArrayList<Integer> porteeMvt() {
    	return this.portee();
    }

}
