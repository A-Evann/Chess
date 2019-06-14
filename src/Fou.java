import java.util.ArrayList;

public class Fou extends Piece {
    public Fou() {
        super();
    }
    public Fou(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    public boolean mouvementValide(int colonne, int ligne) {
    	int dist_co = this.getColonne() - colonne; // distance en colonne
    	int dist_li = this.getLigne() - ligne;	  // distance en ligne
    	dist_co = Math.abs(dist_co);  //valeur absolue
    	dist_li = Math.abs(dist_li);
    	boolean diagonal = (dist_co == dist_li);//est-ce une diagonale ?
    	/*
		on bouge en diagonal
    	 */
    	if (diagonal) {
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
    		return "\u265D";
    	}
    	else return "\u2657";
    }
    public ArrayList<Integer> portee(){
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
    	return les_cases;
    }
    public ArrayList<Integer> porteeMvt() {
    	return this.portee();
    }

}
