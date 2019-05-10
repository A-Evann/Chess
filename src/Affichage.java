import java.util.Scanner;

public class Affichage {
	
	
	public Affichage() {}
	
	public void afficher(String message) {
		System.out.println(message);
	}
	
    public void afficher(Plateau plateau) {
    	String ligne_haut = "   *";
    	for(int i=0; i<8; i++) {
    		ligne_haut = ligne_haut + "  " + (char)(i+97) + "  *";
    	}
    	String ligne_etoile = "****";
    	for(int i = 0; i<8; i++) {
    		ligne_etoile = ligne_etoile + "******";
    	}
    	String ligne_rien = "   *";
    	for(int i=0; i<8; i++) {
    		ligne_rien = ligne_rien + "     *";
    	}
    	System.out.println(ligne_haut);
    	System.out.println(ligne_etoile);
        for(int i=0; i<8; i++) {
            String ligne_contenu = " " + (i+1) + " *" ;
            for(int y=0; y<8; y++) {
            	String piece;
            	if(plateau.getPlateau(8 * i + y) == null) {
            		piece = "-";
            	}
            	else {
            		piece = plateau.getPlateau(8 * i + y).toString();
            	}
            	ligne_contenu = ligne_contenu + "  " + piece + "  *";
            }
            System.out.println(ligne_rien);
        	System.out.println(ligne_contenu);
        	System.out.println(ligne_rien);
        	System.out.println(ligne_etoile);
        }
    }
    
    public int saisirCase() {
    	Scanner sc = new Scanner(System.in);
    	String i = sc.nextLine();//l'indice de la case
    	if (i.length() > 2) {
    		return -1;
    	}
    	int colonne = i.charAt(0)-97;
    	int ligne = i.charAt(1)-49;
    	return (8*ligne) + colonne;//la valeur de retour est l'indice de la case saisi
    }
    
    public int saisirNb() {
    	Scanner sc = new Scanner(System.in);
    	int i = sc.nextInt();
    	sc.close();
    	return i;
    }
    
    public String saisirPseudo() {
    	Scanner sc = new Scanner(System.in);
    	String s = sc.nextLine();
    	sc.close();
    	return s;
    }
    
}