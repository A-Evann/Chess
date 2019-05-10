import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {
		Affichage aff = new Affichage();
		Partie p = new Partie("Remi", "Evann");
		aff.afficher(p.getPlateau());
		//Piece p = new Cavalier(0,0,1);
		//System.out.println(p instanceof Fou);
		int j = 1;
		for(int i=0;i<100;i++) {
			if (j == 0) {
				j = 1;
			}
			else if (j == 1) {
				j = 0;
			}
			p.jouerCoup(p.getJoueur(j));
			aff.afficher(p.getPlateau());
		}/*
		for(int i=0;i<0;i++) {
			p.jouerCoup(p.getJoueur(1));
			p.getPlateau().afficherPlateau();
		}
		
		Partie p1 = new Partie("1", "2", "pat");
		p1.getPlateau().afficherPlateau();
		ArrayList<Integer> liste = p1.getPlateau().porteeValide(0);
		for(int i=0; i<liste.size(); i++) {
			System.out.println(liste.get(i));
		}
		p1.jouerCoup(p1.getJoueur(1));
		p1.getPlateau().afficherPlateau();
		p1.jouerCoup(p1.getJoueur(0));
		p1.getPlateau().afficherPlateau();*/
		
		
		new Affichage().afficher(new Affichage().saisirPseudo());
		
	}
}
