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
			p_virtuel.setPlateau(indice, p);//on met la piece a l'indice
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
		System.out.println("pb coupValide retourne false");//msg debug
		return false;
	}
	
	public void jouerCoup(Joueur j) {
		if (this.plateau.echec() == 0) {
			System.out.println("Echec au roi Blanc ! Déplacer votre roi !");
		}
		else if(this.plateau.echec() == 1) {
			System.out.println("Echec au roi Noir ! Déplacer votre roi !");
		}
		
		Piece p = saisiPieceValide(j);
		int dest = j.SaisiCaseDestination();
		
		if (this.plateau.echec() == 0 || this.plateau.echec() == 1) {
			while (!coupValide(dest, p, j) || !(p instanceof Roi)) {
				System.out.println("Coup non valide !");
				p = saisiPieceValide(j);
				dest = j.SaisiCaseDestination();
			}
		}
		
		else {
			while (!coupValide(dest, p, j)) {
				System.out.println("Coup non valide !");
				p = saisiPieceValide(j);
				dest = j.SaisiCaseDestination();
			}
		}
		this.plateau.bougerPiece(dest, p);//le coup est finalement valide, on bouge la piece
		this.plateau.promotion(p);
	}

}
