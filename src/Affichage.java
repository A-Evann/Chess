import java.util.Scanner;
import javax.swing.*;

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
    	System.out.println(ligne_etoile);
        for(int i=7; i>=0; i--) {
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
        System.out.println(ligne_haut);
    }
    
    public String saisirCase() {
    	Scanner sc = new Scanner(System.in);
    	return sc.nextLine();//l'indice de la case
    }
    
    public int saisirNb() {
    	Scanner sc = new Scanner(System.in);
    	int i = sc.nextInt();
    	sc.close();
    	return i;
    }
    public String choisirFichier() {
    	String nom;
    	JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
		nom = chooser.getSelectedFile().getAbsolutePath();
    	return nom;
    }
    public String saisirPseudo() {
    	Scanner sc = new Scanner(System.in);
    	String s = sc.nextLine();
    	return s;
    }
    
}