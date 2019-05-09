import java.util.Scanner;
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
    	Scanner sc = new Scanner(System.in);
    	System.out.println(this.getPseudo() + " saisir la case de la pièce a selectionner (colonne puis ligne ex : a4, h6)");
    	String i = sc.nextLine();//l'indice de la case
    	while(i.length() != 2 || (i.charAt(0)-97<0 || i.charAt(0)-97>7) || (i.charAt(1)-49<0 || i.charAt(1)-49>7)) {//mauvaise saisi on recommence
    		System.out.println(this.getPseudo() + " saisir la case de la pièce \nSuivre le format indiqué (colonne puis ligne ex : a4, h6)");
    		i = sc.nextLine();
    	}
    	int colonne = i.charAt(0)-97;
    	int ligne = i.charAt(1)-49;
    	return (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    }
    public int SaisiCaseDestination() {
    	Scanner sc = new Scanner(System.in);
    	System.out.println(this.getPseudo() + " saisir la case de destination (colonne puis ligne ex : a4, h6)");
    	String i = sc.nextLine();
    	while(i.length() != 2 || (i.charAt(0)-97<0 || i.charAt(0)-97>7) || (i.charAt(1)-49<0 || i.charAt(1)-49>7)) {//mauvaise saisi on recommence
    		System.out.println(this.getPseudo() + " saisir la case de destination ! \nSuivre le format indiqué (colonne puis ligne ex : a4, h6)");
    		i = sc.nextLine();
    	}
    	int colonne = i.charAt(0)-97;
    	int ligne = i.charAt(1)-49;
    	return (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    }
}
