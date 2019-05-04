import java.util.ArrayList;

public class Pion extends Piece {	
    public Pion() {
        super();
    }
    public Pion(int colonne, int ligne, int couleur) {
        super(colonne, ligne, couleur);
    }
    
    public boolean mouvementValide(int indice) {
		return mouvementValide(indice%8, indice/8);
	}

	public boolean priseValide(int indice) {
		return priseValide(indice%8, indice/8);
	}
    
    public boolean mouvementValide(int colonne, int ligne) {
    	if (this.getCouleur() == 0) { //pour les pions blancs
    		/*la piece a jamais bougé ET le mouvement en ligne est de 2
    		 OU le mouvement en ligne est de 1
    		  ET PAS DE MOUVEMET  EN COLONNE !!!!
    		 */
    		if (((!this.isDeja_bouge() && ligne - this.getLigne() == 2) || (ligne - this.getLigne() == 1)) && (colonne - this.getColonne() == 0)) {
    			return true;
    		}
    	}
    	/*la piece a jamais bougé ET le mouvement en ligne est de -2
		 OU le mouvement en ligne est de -1
		  ET PAS DE MOUVEMET  EN COLONNE !!!!
		 */
    	else if (this.getCouleur() == 1) {//pions noirs
    		if (((!this.isDeja_bouge() && ligne - this.getLigne() == -2) || (ligne - this.getLigne() == -1)) && (colonne - this.getColonne() == 0)) {
    			return true;
    		}
    	}
    	System.out.println("pb mouvementValide()");//msg debug
    	return false;
    }
    
    public boolean priseValide(int colonne, int ligne) {
    	
    	int dep_col = colonne - this.getColonne();
    	int dep_lig = ligne - this.getLigne();
    	
    	if (this.getCouleur() == 0) { // pions blancs
    		if (dep_lig == 1 && Math.abs(dep_col) == 1) {
    			return true;
    		}
    	}
    	
    	else if (this.getCouleur() == 1) { // pions noirs
    		if (dep_lig == -1 && Math.abs(dep_col) == 1) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    public String toString() {
    	if(this.getCouleur()==0) {
    		return "P";
    	}
    	else return "p";
    }
    public ArrayList<Integer> portee(){
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	if(this.getCouleur() == 0) {//c'est un blanc donc on mange en l++,c++ et l++,c--
    		int colonne = this.getColonne() + 1;
        	int ligne = this.getLigne() + 1;
        	if((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
        		les_cases.add((8 * ligne) + colonne);
        	}
        	colonne = this.getColonne() - 1;
        	ligne = this.getLigne() + 1;
        	if((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
        		les_cases.add((8 * ligne) + colonne);
        	}
    	}
    	else{//c'est un noir donc on mange en l--,c++ et l--,c--
    		int colonne = this.getColonne() + 1;
        	int ligne = this.getLigne() - 1;
        	if((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
        		les_cases.add((8 * ligne) + colonne);
        	}
        	colonne = this.getColonne() - 1;
        	ligne = this.getLigne() - 1;
        	if((colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0)){
        		les_cases.add((8 * ligne) + colonne);
        	}
    	}
    	
    	return les_cases;
    }

}
