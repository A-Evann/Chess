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
    	aff.afficher(this.Pseudo + " : saisir Case depart");
    	int i = aff.saisirCase();
    	while (i/8 < 0 || i/8 > 7 || i%8 < 0 || i%8 > 7) {//mauvaise saisi on recommence
    		aff.afficher("mauvaise saisie");
    		aff.saisirCase();
    	}
    	return i;
    }
    public int SaisiCaseDestination() {
    	Affichage aff = new Affichage();
    	aff.afficher("saisir Case destination");
    	int i = aff.saisirCase();
    	while (i/8 < 0 || i/8 > 7 || i%8 < 0 || i%8 > 7) {//mauvaise saisi on recommence
    		aff.afficher("mauvaise saisie");
    		aff.saisirCase();
    	}
    	return i;
    }
}
