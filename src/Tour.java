import java.util.ArrayList;

public class Tour extends Piece {
	

    public Tour() {
        super();
    }

    public Tour(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    public boolean mouvementValide(int colonne, int ligne) {
    	/*
    	 on bouge pas de colonne
    	 OU on bouge pas de ligne
    	 
    	 */
    	if (colonne == this.getColonne() || ligne == this.getLigne()) {
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
    		return "\u265C";
    	}
    	else return "\u2656";
    }
    public ArrayList<Integer> portee(){
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	//d'abord on a juste les c++
    	int colonne = this.getColonne() + 1;
    	int ligne = this.getLigne();
    	while((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
    		les_cases.add((8 * ligne) + colonne);
    		colonne++;
    	}
    	//c--
    	colonne = this.getColonne() -1;
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
