public class Joueur {
    private String Pseudo;
    private int Couleur;

    public Joueur() {
    }
    public Joueur(String pseudo, int couleur) {
        this.Pseudo = pseudo;
        this.Couleur = couleur;
    }
    public String getPseudo() {
        // Automatically generated method. Please do not modify this code.
        return this.Pseudo;
    }
    public void setPseudo(String value) {
        // Automatically generated method. Please do not modify this code.
        this.Pseudo = value;
    }
    public int getCouleur() {
        // Automatically generated method. Please do not modify this code.
        return this.Couleur;
    }
    public int getAversaire() {
    	if (this.getCouleur() == 0) return 1;
		else return 0;
    }
    
    public void setCouleur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.Couleur = value;
    }
    
    public int SaisiCasePiece() {
    	Affichage aff = new Affichage();
    	aff.afficher(this.Pseudo + " : saisir Case depart (m : ouvrir menu)");
    	String s = aff.saisirCase();
    	if(s.equals("m")) {
    		return -1;//c'est pas une case donc on peut s'en servir pour indiquer que faut aller au menu
    	}
    	while (s.length() != 2) {
    		aff.afficher("mauvaise saisie");
    		s = aff.saisirCase();
    	}
    	
    	int colonne = s.charAt(0)-97;
    	int ligne = s.charAt(1)-49;
    	int i = (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    	
    	while (i/8 < 0 || i/8 > 7 || i%8 < 0 || i%8 > 7) {//mauvaise saisi on recommence
    		aff.afficher("mauvaise saisie");
    		s = aff.saisirCase();
    		
    		colonne = s.charAt(0)-97;
        	ligne = s.charAt(1)-49;
        	i = (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    	}
    	return i;
    }
    
    public int SaisiCaseDestination() {
    	Affichage aff = new Affichage();
    	aff.afficher(this.Pseudo + " : saisir Case destination (m : ouvir menu)");
    	String s = aff.saisirCase();
    	if(s.equals("m")) {
    		return -1;//c'est pas une case donc on peut s'en servir pour indiquer que faut aller au menu
    	}
    	while (s.length() != 2) {
    		aff.afficher("mauvaise saisie");
    		s = aff.saisirCase();
    	}  	
    	int colonne = s.charAt(0)-97;
    	int ligne = s.charAt(1)-49;
    	int i = (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    	
    	while (i/8 < 0 || i/8 > 7 || i%8 < 0 || i%8 > 7) {//mauvaise saisi on recommence
    		aff.afficher("mauvaise saisie");
    		s = aff.saisirCase();
    		
    		colonne = s.charAt(0)-97;
        	ligne = s.charAt(1)-49;
        	i = (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    	}
    	return i;
    }
}
