import java.util.ArrayList;
import java.io.*;

public class Partie {
	private int tour;
    private Plateau plateau;
    private Joueur[] joueurs;
    private String backup;

    public Partie() {
        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur("", 0);
        this.joueurs[1] = new Joueur("", 1);
        this.plateau = new Plateau();
        this.tour = 0;//le premier tour c'est le 0 pour correspondre avec %2 (le blanc = joueurs[0])
        this.backup = "" + "\n" + "" + "\n" + tour + "\n";//on ajoute les pseudos et tour a la backup pour sauvegarder unepartie et non pas un plateau
        this.menuDeb();
    }

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	public void setJoueur(int i, Joueur j) {
		this.joueurs[i]=j;
	}
	
	public Joueur getJoueur(int i) {
		return this.joueurs[i];
	}
	
	public Piece saisiPieceValide(Joueur j) {
		Affichage aff = new Affichage();
		int saisi = j.SaisiCasePiece();
		if(saisi == -1) {
			return null;
		}
		Piece p = this.plateau.getPlateau(saisi);
		while (p == null || p.getCouleur() != j.getCouleur()) {
			if(p == null) aff.afficher("case vide: saisir une autre");
			else aff.afficher("Cette piece ne vous appartient pas!\nSaisir une autre piece");
			saisi = j.SaisiCasePiece();
			if(saisi == -1) {
				return null;
			}
			p = this.plateau.getPlateau(saisi);			
		}
		return p;
	}

	public boolean sansEchec(int indice, Piece p, Joueur j) {
		boolean test = coupValide(indice, p, j);
		if(test) {//si le coup est valide on regarde que ca crée pas d'echec
			Plateau p_virtuel = new Plateau(this.getPlateau());
			Piece p_double = new Pion(p.getColonne(), p.getLigne(), p.getCouleur());
			if(p instanceof Roi) {
				p_virtuel.setIndice_roi(p.getCouleur(), indice);
			}
			p_virtuel.setPlateau(indice, p_double);//on crée une nouvelle piece a l'indice (pour eviter les pb avec les indices des vieilles pieces pas a jour)
			p_virtuel.setPlateau((p.getLigne()*8 + p.getColonne()), null);
			test = !p_virtuel.isAPortee(p_virtuel.getIndice_roi(p.getCouleur()), p.getCouleurAdv());//on regarde si le roi de la couleur de la piece est pas a portée pour les adversaires
		}
		return test;
	}
	
	public boolean coupValide(int indice, Piece p, Joueur j) {

		if (this.plateau.caseValide(indice) && this.plateau.contenuCase(indice) != j.getCouleur()) { //il faut que la case soit libre ou adverse
			
			int ind_piece = p.getLigne() * 8 + p.getColonne(); // indice de la piece
			if (p instanceof Roi && (indice == ind_piece + 2 || indice == ind_piece - 2)) {
				 //si la piece est un roi et qu'ele est placée a cote de l'une des tours
				
				if (!((Roi)p).isDeja_bouge() && indice < ind_piece && this.plateau.getPlateau(p.getLigne() * 8) instanceof Tour) {
					 //si le roi est placé a côté de la tour de gauche
					
					Tour tour = (Tour)this.plateau.getPlateau(p.getLigne() * 8);
					if (!tour.isDeja_bouge() && this.plateau.roquePossible(tour)) { //vérification si le roque est possible
						this.plateau.bougerPiece(indice + 1, tour); //on bouge la tour puisque le roi bougera en retournant true
						return true;
					}
				}
				
				else if (this.plateau.getPlateau(p.getLigne() * 8 + 7) instanceof Tour) {
					//sinon le roi est placé a côté de la tour de droite
					
					Tour tour = (Tour)this.plateau.getPlateau(p.getLigne() * 8 + 7);
					if (this.plateau.roquePossible(tour)) { //vérification si le roque est possible
						this.plateau.bougerPiece(indice - 1, tour); //on bouge la tour puisque le roi bougera en retournant true
						return true;
					}
				}
			}

			else if (p instanceof Cavalier || this.plateau.cheminValide(indice, p)) {
				if(this.plateau.contenuCase(indice) == 2) {
					return p.mouvementValide(indice);
				}
				return p.priseValide(indice);
			}
		}
		//System.out.println("pb coupValide retourne false");//msg debug
		return false;
	}
	public boolean echec(Joueur j) {
		return this.getPlateau().isAPortee(this.getPlateau().getIndice_roi(j.getCouleur()), j.getAversaire());
	}
	
    public boolean pat(Joueur j) {//si pat est vrai c'est qu'on est en situation de pat
    	if(this.echec(j))return false;//s'il est en echec il est pas en pat
    	ArrayList<Integer> cases_mvt_roi = this.getPlateau().getPlateau(this.getPlateau().getIndice_roi(j.getCouleur())).porteeMvt();//ici on fait une liste des cases de mvt du roi
    	boolean test;
    	int i = 0;
    	while (i < cases_mvt_roi.size() && !this.sansEchec(cases_mvt_roi.get(i), this.getPlateau().getPlateau(this.getPlateau().getIndice_roi(j.getCouleur())), j) ) {
    		/*
    		 * on fait des tour de boucle tant que on a pas depassé la taille de la liste et que un coup ne donne pas d'indice où on bouge sans echec
    		 */
    		i++;
    	}
    	//si on a fait toute la liste ca veut dire qu'aucune case pour le roi n'est possible 
    	
    	test = i==cases_mvt_roi.size();
    	if(test) {//s'il y a une case possible pour le mouvement du roi ca ne sert a rien de faire ce qui suit
    		/*
    		 * ici on va regarder mtn qu'aucun des mouvement possible pour le joueur
    		 * pour ca on parcourt tout le plateau
    		 * et on verifie tous les deplacement possible de chaque piece du joueur
    		 */
    		ArrayList<Integer> cases_piece = new ArrayList<Integer>();
    		for(int k = 0; k<64 && test; k++) {//si on trouve une piece qui a un mvt possible ca sert a rien de rester dans la boucle
    			if(this.getPlateau().getPlateau(k) != null && this.getPlateau().getPlateau(k).getCouleur() == j.getCouleur()) {//on a une case pas null et de la couleur du joueur
    				cases_piece = this.getPlateau().getPlateau(k).porteeMvt();
    				i = 0;
    				while (i < cases_piece.size() && !this.sansEchec(cases_piece.get(i), this.getPlateau().getPlateau(k), j) ) {
    		    		/*
    		    		 * on fait des tour de boucle tant que on a pas depassé la taille de la liste et que un coup ne donne pas d'indice où on bouge sans echec
    		    		 */
    		    		i++;
    		    	}
    		    	//si on a fait toute la liste ca veut dire qu'aucune case n'est possible 
    		    	test = i==cases_piece.size();
    			}
    		}
    	}
    	return test ;
    }
	
    public boolean echec_mat(Joueur j) {
    	if(!this.echec(j))return false;
    	ArrayList<Integer> cases_mvt_roi = this.getPlateau().getPlateau(this.getPlateau().getIndice_roi(j.getCouleur())).porteeMvt();//ici on fait une liste des cases de mvt du roi
    	boolean test;
    	int i = 0;
    	while (i < cases_mvt_roi.size() && !this.sansEchec(cases_mvt_roi.get(i), this.getPlateau().getPlateau(this.getPlateau().getIndice_roi(j.getCouleur())), j) ) {
    		/*
    		 * on fait des tour de boucle tant que on a pas depassé la taille de la liste et que un coup ne donne pas d'indice où on bouge sans echec
    		 */
    		i++;
    	}
    	//si on a fait toute la liste ca veut dire qu'aucune case pour le roi n'est possible 
    	
    	test = i==cases_mvt_roi.size();
    	if(test) {//s'il y a une case possible pour le mouvement du roi ca ne sert a rien de faire ce qui suit
    		/*
    		 * ici on va regarder mtn qu'aucun des mouvement possible pour le joueur
    		 * pour ca on parcourt tout le plateau
    		 * et on verifie tous les deplacement possible de chaque piece du joueur
    		 */
    		ArrayList<Integer> cases_piece = new ArrayList<Integer>();
    		for(int k = 0; k<64 && test; k++) {//si on trouve une piece qui a un mvt possible ca sert a rien de rester dans la boucle
    			if(this.getPlateau().getPlateau(k) != null && this.getPlateau().getPlateau(k).getCouleur() == j.getCouleur()) {//on a une case pas null et de la couleur du joueur
    				cases_piece = this.getPlateau().getPlateau(k).porteeMvt();
    				i = 0;
    				while (i < cases_piece.size() && !this.sansEchec(cases_piece.get(i), this.getPlateau().getPlateau(k), j) ) {
    		    		/*
    		    		 * on fait des tour de boucle tant que on a pas depassé la taille de la liste et que un coup ne donne pas d'indice où on bouge sans echec
    		    		 */
    		    		i++;
    		    	}
    		    	//si on a fait toute la liste ca veut dire qu'aucune case n'est possible 
    		    	test = i==cases_piece.size();
    			}
    		}
    	}
    	return test;

    }
	public void jouerCoup(Joueur j) {
		Affichage aff = new Affichage();
		if (this.echec(j)) {//s'il est en echec on lui dit
			aff.afficher("Echec a votre roi !");
		}	
		if(this.pat(j)) {
			aff.afficher("Pat");
		}
		if(this.echec_mat(j)) {
			aff.afficher("Echec et Mat");
		}
		Piece p = saisiPieceValide(j);
		if(p == null) {//si la piece est null il a saisi m
			this.menuMil();
		}
		else {
			int dest = j.SaisiCaseDestination();	
			if(dest == -1) {//si la destination est -1 il a saisi m
				this.menuMil();
			}
			else {
				while (!sansEchec(dest, p, j)) { //tant qu'il fait pas un mouvement qui enleve l'échec
					aff.afficher("Coup non valide ! vérifier votre roi");
					p = saisiPieceValide(j);
					if(p == null) {//si la piece est null il a saisi m
						this.menuMil();
					}
					dest = j.SaisiCaseDestination();
					if(dest == -1) {//si la destination est -1 il a saisi m
						this.menuMil();
					}
				}
				this.plateau.bougerPiece(dest, p);//le coup est finalement valide, on bouge la piece
				this.plateau.promotion(p);
			}
			
		}
		
	}
	public void tour_jeu() {	
		this.jouerCoup(this.getJoueur(this.getTour()%2));
		new Affichage().afficher(this.getPlateau());
	}
	public void jouerPartie() {
		this.menuDeb();
	}
	public void jouer() {
		new Affichage().afficher(this.getPlateau());
		while(!echec_mat(this.getJoueur(this.getTour()%2)) && !pat(this.getJoueur(this.getTour()%2))) {
			tour_jeu();
			this.setTour(this.getTour() + 1);//tour ++;
		}
		if(echec_mat(this.getJoueur(this.getTour()%2))) {
			new Affichage().afficher("Echec et mat, " + this.getJoueur((this.getTour() + 1)%2).getPseudo() + " a gagné !");
			new Partie();
		}
		else {
			new Affichage().afficher("Egalité ! (pat)");
		}
	}
	
	public void charger() {
		
		/* Dans le cas ou on charge en cours de partie
		 * on regenere un plateau pour faire les deplacment sauvgardes correctement
		 */
		this.setPlateau(new Plateau());
		Affichage aff = new Affichage();
		aff.afficher("Avec Eclipse, la fenetre de chargement de fichier est ouverte derriere les autres");
		try {
			String nom = aff.choisirFichier();
			//adaptation du code de charger en ihm
			BufferedReader br = new BufferedReader(new FileReader(nom));
		    String line;
		    line = br.readLine();
		    String pseudo1 = line;
		    line = br.readLine();
		    String pseudo2 = line;
		    line = br.readLine();
		    int tour = Integer.parseInt(line);  
		    this.setTour(tour);
		    this.setJoueur(0, new Joueur(pseudo1, 0));
		    this.setJoueur(1, new Joueur(pseudo2, 1));
		    line = br.readLine();//on arrive mtn aux coups
		    
		    int i = 0;
		    //Simulation d'une partie avec les mouvements sauvegardés
		    while (line != null) {	
				/* 
				 * copier-coller de jouerCoup() sans les vérifications de mouvements valide
				 * car ils ont été vérifiés dans la partie sauvegardée 
				 */
		    	i = (8 * (line.charAt(1) - 49)) + (line.charAt(0) - 97);
				Piece p = this.plateau.getPlateau(i);
				
				line = br.readLine();
		    	i = (8 * (line.charAt(1) - 49)) + (line.charAt(0) - 97);
				int dest = i;
				this.plateau.bougerPiece(dest, p);
				this.plateau.promotion(p);
				line = br.readLine();
			}
		    aff.afficher(this.plateau);
		    br.close();
		}
		
		catch(IOException e) {
			System.out.println(e);
		}
		this.jouer();
	}
	
	public void sauvegarder() {
		Affichage aff = new Affichage();
		String path=new File("").getAbsolutePath(); 
		aff.afficher("Saisir nom du fichier a sauvegarder !");
		String nom = aff.saisirPseudo();
		new File(path+"\\" + nom +".txt");
		
		try{
		      PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nom + ".txt")));
		      sortie.print(getBackup());//on ecrit les pseudos le tour et tous les coups
		      sortie.close();
	    }

	    catch(IOException e) {
		      System.out.println(e);
		}
	}

	public String getBackup() {
		setBackup(getJoueur(0).getPseudo() + "\n" + getJoueur(1).getPseudo() + "\n" + getTour() + "\n" + getPlateau().getBackup());
		return backup;
	}
	public void menuDeb() {//pas besoin de menu de fin c'est le meme que celui de début
		Affichage aff = new Affichage();
		String s = "Que voulez-vous faire ?\nj : jouer une nouvelle partie\nc : charger une partie\nq : quitter le jeu";
		aff.afficher(s);
		String rep = aff.saisirPseudo();
		System.out.println("rep : " + rep);
		while(!rep.equals("j") && !rep.equals("c") && !rep.equals("q")) {
			aff.afficher("mauvaise saisie\n" + s);
			rep = aff.saisirPseudo();
		}
		if (rep.equals("j")) {
			aff.afficher("saisir pseudo joueur 1 !");
			String pseudo1 = aff.saisirPseudo();
			aff.afficher("saisir pseudo joueur 2 !");
			String pseudo2 = aff.saisirPseudo();
			this.getJoueur(0).setPseudo(pseudo1);
			this.getJoueur(1).setPseudo(pseudo2);
			this.jouer();
		}
		else if(rep.equals("c")) {
			this.charger();
		}
		else{
			aff.afficher("merci d'avoir jouer, à la prochaine !");
			System.exit(0);
		}
	}
	public void menuMil() {
		Affichage aff = new Affichage();
		String s = "Que voulez-vous faire ?\nj : reprendre la partie en cours\nr : recommencer une partie\nc : charger une partie\ns : sauvegarder la partie en cours\nq : quitter le jeu";
		aff.afficher(s);
		String rep = aff.saisirPseudo();
		while(!rep.equals("j") && !rep.equals("r") && !rep.equals("c") && !rep.equals("s") && !rep.equals("q")) {
			aff.afficher("mauvaise saisie\n" + s);
			rep = aff.saisirPseudo();
		}
		if (rep.equals("r")) {
			aff.afficher("saisir pseudo joueur 1 !");
			String pseudo1 = aff.saisirPseudo();
			aff.afficher("saisir pseudo joueur 2 !");
			String pseudo2 = aff.saisirPseudo();
			this.getJoueur(0).setPseudo(pseudo1);
			this.getJoueur(1).setPseudo(pseudo2);
			this.setPlateau(new Plateau());
			this.setTour(0);
			this.jouer();
		}
		else if(rep.equals("j")) {
			this.jouer();
		}
		else if(rep.equals("c")) {
			this.charger();
		}
		else if(rep.equals("s")) {
			this.sauvegarder();
			aff.afficher("Sauvegarde réussie, retour a la partie en cours");
			this.jouer();
		}
		else{
			aff.afficher("merci d'avoir jouer, à la prochaine !");
			System.exit(0);
		}
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}

}
