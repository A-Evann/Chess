import java.util.ArrayList;

public class Partie {
	private int tour;
    private Plateau plateau;
    private Joueur[] joueurs;
    public Partie() {
    }

    public Partie(String pseudo1, String pseudo2) {
        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur(pseudo1, 0);
        this.joueurs[1] = new Joueur(pseudo2, 1);
        this.plateau = new Plateau();
        this.tour = 0;//le premier tour c'est le 0 pour correspondre avec %2 (le blanc = joueurs[0])
    }
    public Partie(String pseudo1, String pseudo2, String e) {
        this.joueurs = new Joueur[2];
        this.joueurs[0] = new Joueur(pseudo1, 0);
        this.joueurs[1] = new Joueur(pseudo2, 1);
        this.plateau = new Plateau(e);
        this.tour = 0;//le premier tour c'est le 0 pour correspondre avec %2 (le blanc = joueurs[0])
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
		Piece p = this.plateau.getPlateau(j.SaisiCasePiece());
		while (p == null || p.getCouleur() != j.getCouleur()) {
			if(p == null) System.out.println("case vide saisir une autre");
			else System.out.println("Cette pièce ne vous appartient pas !\n Saisir une autre pièce !");
			p = this.plateau.getPlateau(j.SaisiCasePiece());
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
			if (p instanceof Roi && (indice == ind_piece + 2 || indice == ind_piece - 3)) {
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
		if (this.echec(j)) {//s'il est en echec on lui dit
			System.out.println("Echec a votre roi !");
		}	
		if(this.pat(j)) {
			System.out.println("pat");
		}
		if(this.echec_mat(j)) {
			System.out.println("echec et mat");
		}
		Piece p = saisiPieceValide(j);
		int dest = j.SaisiCaseDestination();		
		while (!sansEchec(dest, p, j)) { //tant qu'il fait pas un mouvement qui enleve l'échec
			System.out.println("Coup non valide ! verifier que le roi n'est plus en echec si c'est le cas");
			p = saisiPieceValide(j);
			dest = j.SaisiCaseDestination();
		}
		this.plateau.bougerPiece(dest, p);//le coup est finalement valide, on bouge la piece
		this.plateau.promotion(p);
	}
	public void tour_jeu() {
		
	}

}
