import java.util.ArrayList;
public abstract class Piece {
    private int ligne;
    private int colonne;
    private int couleur; // 0=blanc, 1=noir
    private boolean deja_bouge;  

    public abstract boolean mouvementValide(int colonne, int ligne);
    
    public abstract boolean priseValide(int colonne, int ligne);
    
    public abstract ArrayList<Integer> portee();//methode retournant les cases ou la piece peut manger
    
    public boolean mouvementValide(int indice) {
    	return mouvementValide(indice%8, indice/8);
    }
    
    public boolean priseValide(int indice) {
    	return priseValide(indice%8, indice/8);
    }

  //constructeur vide
    public Piece() {
    }


    public Piece(int colonne, int ligne, int couleur) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.couleur = couleur;
        this.setDeja_bouge(false);
    }

    public int getLigne() {
        // Automatically generated method. Please do not modify this code.
        return this.ligne;
    }

    public void setLigne(int value) {
        // Automatically generated method. Please do not modify this code.
        this.ligne = value;
    }

    public int getColonne() {
        // Automatically generated method. Please do not modify this code.
        return this.colonne;
    }

    public void setColonne(int value) {
        // Automatically generated method. Please do not modify this code.
        this.colonne = value;
    }

    public int getCouleur() {
        // Automatically generated method. Please do not modify this code.
        return this.couleur;
    }

    public void setCouleur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.couleur = value;
    }

    public boolean caseValide(int colonne, int ligne) {
        return (colonne>=0 && colonne<=7) && (ligne>=0 && ligne<=7);
    }
    public String toString() {
    	if(this.getCouleur()==0) {
    		return "P";
    	}
    	else return "p";
    }

	public boolean isDeja_bouge() {
		return deja_bouge;
	}

	public void setDeja_bouge(boolean deja_bouge) {
		this.deja_bouge = deja_bouge;
	}
	public int getCouleurAdv() {
		if (this.getCouleur() == 0) return 1;
		else return 0;
	}

}
