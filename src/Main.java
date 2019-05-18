//import java.util.ArrayList;
import java.io.*; 
public class Main {
	public static void main(String[] args) {
		
		//Affichage aff = new Affichage();
		Partie p = new Partie("Remi", "Evann");
		p.jouerParti();

		/*
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
		
		/*String path=new File("").getAbsolutePath(); 
		new File(path+"\\sauvegarde.txt"); 
		
		try{
		      PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter("sauvegarde.txt")));
		      sortie.print("a2\na3\n");

		      sortie.close();
		    }

		    catch(IOException e) {
		      System.out.println(e);
		    }

		
		p.charger();*/
		
		/*for(int i=0;i<2;i++) {
			if (j == 0) {
				j = 1;
			}
			else if (j == 1) {
				j = 0;
			}
			p.jouerCoup(p.getJoueur(j));
			aff.afficher(p.getPlateau());
		}
		
		String s = p.getPlateau().getBackup();
		System.out.println(s);
		p.sauvegarder(s);
		p.charger();*/
		
	}
}
