import java.util.ArrayList;

public class Plateau {
	private int indice_roiBlanc;
	private int indice_roiNoir;
    private Piece[] plateau;
    public boolean caseLibre(int colonne, int ligne) {
        int indice_case = (8 * ligne) + colonne;
        return this.plateau[indice_case] == null;
    }
    public Plateau(Plateau p) {
    	this.indice_roiBlanc = p.getIndice_roi(0);
    	this.indice_roiNoir = p.getIndice_roi(1);
    	this.plateau = new Piece[64];
    	for(int i = 0; i<64; i++) {
    		if(p.getPlateau(i) instanceof Roi) {
    			this.plateau[i] = new Roi(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		else if(p.getPlateau(i) instanceof Dame) {
    			this.plateau[i] = new Dame(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		else if(p.getPlateau(i) instanceof Pion) {
    			this.plateau[i] = new Pion(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		else if(p.getPlateau(i) instanceof Cavalier) {
    			this.plateau[i] = new Cavalier(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		else if(p.getPlateau(i) instanceof Fou) {
    			this.plateau[i] = new Fou(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		else if(p.getPlateau(i) instanceof Tour){
    			this.plateau[i] = new Tour(p.getPlateau(i).getColonne(), p.getPlateau(i).getLigne(), p.getPlateau(i).getCouleur());
    		}
    		
    	}
    }
    public Plateau(String situation) {
		this.plateau = new Piece[64];
    	if (situation == "echec") {
        	this.plateau[63] = new Roi(7,7,1);
        	this.plateau[0]= new Dame(0,0,0);
        	this.indice_roiNoir = 63;
        	this.plateau[2] = new Roi(0,3,0);
        	this.indice_roiBlanc = 2;
    	}
    	else if(situation == "pat") {
    		this.plateau[56] = new Roi(0,7,1);
    		this.plateau[40] = new Fou(0,5,0);
    		this.plateau[42] = new Cavalier(2,5,0);
    		this.plateau[1]=new Dame(1,0,0);
    		this.indice_roiNoir = 56;
    		this.plateau[2] = new Roi(0,3,0);
        	this.indice_roiBlanc = 2;
    	}
    	else if(situation == "echecetmat") {
    		this.plateau[56] = new Roi(0,7,1);
    		this.plateau[40] = new Fou(0,5,0);
    		this.plateau[42] = new Cavalier(2,5,0);
    		this.plateau[1]=new Dame(1,0,0);
    		this.plateau[63] = new Tour(7,7,0);
    		this.indice_roiNoir = 56;
    		this.plateau[2] = new Roi(0,3,0);
        	this.indice_roiBlanc = 2;
    		
    	}
    	else {
    		this.plateau = new Piece[64];//nouveau tableau de 64 cases
            //initialisation du plateau :
            //les pions blancs
            for(int i = 8; i < 16; i++) {
                int ligne = i/8;
                int colonne = i%8;
                this.plateau[i] = new Pion(colonne, ligne, 0);
            }
            //les pions noirs
            for(int i = 48; i < 56; i++) {
                int ligne = i/8;
                int colonne = i%8;
                this.plateau[i] = new Pion(colonne, ligne, 1);
            }

            //tours
            this.plateau[0] = new Tour(0,0,0);
            this.plateau[7] = new Tour(7,0,0);
            this.plateau[56] = new Tour(0,7,1);
            this.plateau[63] = new Tour(7,7,1);

            //cavaliers
            this.plateau[1] = new Cavalier(1,0,0);
            this.plateau[6] = new Cavalier(6,0,0);
            this.plateau[57] = new Cavalier(1,7,1);
            this.plateau[62] = new Cavalier(6,7,1);

            //fous
            this.plateau[2] = new Fou(2,0,0);
            this.plateau[5] = new Fou(5,0,0);
            this.plateau[58] = new Fou(2,7,1);
            this.plateau[61] = new Fou(5,7,1);

            //dames
            this.plateau[3] = new Dame(3,0,0);
            this.plateau[59] = new Dame(3,7,1);

            //rois
            this.plateau[4] = new Roi(4,0,0);
            this.indice_roiBlanc = 4;
            this.plateau[60] = new Roi(4,7,1);
            this.indice_roiNoir = 60;
    	}
    	
    }
    public Plateau() {
        this.plateau = new Piece[64];//nouveau tableau de 64 cases
        //initialisation du plateau :
        //les pions blancs
        for(int i = 8; i < 16; i++) {
            int ligne = i/8;
            int colonne = i%8;
            this.plateau[i] = new Pion(colonne, ligne, 0);
        }
        //les pions noirs
        for(int i = 48; i < 56; i++) {
            int ligne = i/8;
            int colonne = i%8;
            this.plateau[i] = new Pion(colonne, ligne, 1);
        }

        //tours
        this.plateau[0] = new Tour(0,0,0);
        this.plateau[7] = new Tour(7,0,0);
        this.plateau[56] = new Tour(0,7,1);
        this.plateau[63] = new Tour(7,7,1);

        //cavaliers
        this.plateau[1] = new Cavalier(1,0,0);
        this.plateau[6] = new Cavalier(6,0,0);
        this.plateau[57] = new Cavalier(1,7,1);
        this.plateau[62] = new Cavalier(6,7,1);

        //fous
        this.plateau[2] = new Fou(2,0,0);
        this.plateau[5] = new Fou(5,0,0);
        this.plateau[58] = new Fou(2,7,1);
        this.plateau[61] = new Fou(5,7,1);

        //dames
        this.plateau[3] = new Dame(3,0,0);
        this.plateau[59] = new Dame(3,7,1);

        //rois
        this.plateau[4] = new Roi(4,0,0);
        this.indice_roiBlanc = 4;
        this.plateau[60] = new Roi(4,7,1);
        this.indice_roiNoir = 60;
    }
    public void bougerPiece(int indice, Piece p) {
    	this.bougerPiece(indice%8, indice/8, p);
    }

    public void bougerPiece(int colonne, int ligne, Piece p) {
    	p.setDeja_bouge(true);
    	
    	int indice_case_dep = (8 * p.getLigne()) + p.getColonne();
    	int indice_case_arriv = (8 * ligne) + colonne;
    	this.plateau[indice_case_dep] = null;//la case ou etait la piece devient null
    	this.plateau[indice_case_arriv] = p;//la case ou elle bouge devient la piece
    	//on met a jour les coordonnéees de la piece
    	p.setColonne(colonne);
    	p.setLigne(ligne);
    	if(p instanceof Roi ) {
    		this.setIndice_roi(p.getCouleur(), indice_case_arriv);
    	}
    }

    public int contenuCase(int i) {
    	if(this.plateau[i] == null) {
    		return 2;//case vide
    	}
    	return this.plateau[i].getCouleur();//couleur de la piece de la case
    }

    public int contenuCase(int colonne, int ligne) {
    	int i = (ligne * 8) + colonne;
    	return contenuCase(i);
    }
    public boolean caseValide(int indice) {
    	return caseValide(indice%8, indice/8);
    }

    public boolean caseValide(int colonne, int ligne) {
    	return (colonne < 8 && ligne < 8) && (colonne >= 0 && ligne >= 0);
    }
    public boolean cheminValide(int indice_arr, Piece p) {
    	return cheminValide(p.getColonne(), p.getLigne(), indice_arr%8, indice_arr/8);
    }
 
    public boolean cheminValide(int dep_co, int dep_li, int arr_co, int arr_li) {

      /*
      il faut distinguer 2 cas pour les les deplacements en ligne/colonne : l/c++ ; l/c--
      il faut distinguer 4 cas pour les colonnes : c++,l++ ; c++,l-- ; c--,l++ ; c--,l--
      }
      */
		//distance
		int dist_co = arr_co - dep_co;
		int dist_li = arr_li - dep_li;
		int cpt_co;
		int cpt_li;
    	//cas ou chemin en ligne
    	if (dist_li == 0) {
    		if(dist_co > 0){//on avance les colonne
    			cpt_co = dep_co + 1;
    			while (this.caseLibre(cpt_co, dep_li) && cpt_co < arr_co) {
    				cpt_co++;
    			}
    		}
    		else{//on recule les colonnes
    			cpt_co = dep_co - 1;
    			while (this.caseLibre(cpt_co, dep_li) && cpt_co > arr_co) {
    				cpt_co--;
    			}
    		}
    		if (cpt_co == arr_co || Math.abs(dist_co) == 1) {//si vérification de toutes les cases ou chemin a une distance de 1 case
    			return true;
    		}
   		}

   		//cas ou chemin en colonne
    	else if(dist_co == 0){
    		if(dist_li > 0){//on avance les lignes
    			cpt_li = dep_li + 1;
     				while (this.caseLibre(dep_co, cpt_li) && cpt_li < arr_li) {
     					cpt_li++;
     				}
    		}
    		else{
    			cpt_li = dep_li - 1;
    			while (this.caseLibre(dep_co, cpt_li) && cpt_li > arr_li) {
    				cpt_li--;
    			}
    		}
   			if (cpt_li == arr_li || Math.abs(dist_li) == 1) { //si vérification de toutes les cases ou chemin a une distance de 1 case
   				return true;
   			}
   		}

   		//cas ou chemin en diagonale
   		else if(Math.abs(dist_co) == Math.abs(dist_li)) {
   				//ici distinguer les 4 cas possibles
   			if(dist_co>0 && dist_li>0){//c++;l++
   				cpt_li = dep_li + 1;
   				cpt_co = dep_co + 1;
   				while (this.caseLibre(cpt_co, cpt_li) && cpt_co < arr_co && cpt_li < arr_li) {
     				cpt_co++;
     				cpt_li++;
     			}
   			}
   			else if(dist_co>0 && dist_li<0){//c++;l--
   				cpt_li = dep_li - 1;
   				cpt_co = dep_co + 1;
   				while (this.caseLibre(cpt_co, cpt_li) && cpt_co < arr_co && cpt_li > arr_li) {
   					cpt_co++;
   					cpt_li--;
   				}
   			}
   			else if(dist_co<0 && dist_li>0){//c--;l++
   				cpt_li = dep_li + 1;
   				cpt_co = dep_co - 1;
   				while (this.caseLibre(cpt_co, cpt_li) && cpt_co > arr_co && cpt_li < arr_li) {
   					cpt_co--;
   					cpt_li++;
   				}
   			}
   			else{
   				cpt_li = dep_li - 1;
   				cpt_co = dep_co - 1;
   				while (this.caseLibre(cpt_co, cpt_li) && cpt_co > arr_co && cpt_li > arr_li) {
   					cpt_co--;
   					cpt_li--;
   				}
   			}


   			if ((cpt_co == arr_co && cpt_li == arr_li) || (Math.abs(dist_co) == 1 && Math.abs(dist_li) == 1)) {//si vérification de toutes les cases ou chemin a une distance de 1 case
   				return true;
   				}
   		}

   		/*else {
   			System.out.println("PROBLEME: methode cheminValide() dans Plateau");//msg debug
   		}*/
    	return false;
    }

    public void setPlateau(int i, Piece p) {
    	this.plateau[i] = p;
    }

    public Piece getPlateau(int i) {
    	return this.plateau[i];
    }

    public void promotion(Piece p) {
    	Affichage aff = new Affichage();
    	
    	if (p.getCouleur() == 0 && p.getLigne() == 7) {
    	
    		while(this.plateau[(p.getLigne() * 8) + p.getColonne()] instanceof Pion) {
    			aff.afficher("Promotion: En quelle pièce voulez-vous promuvoir votre pion ?\n1- En Dame\n2- En Cavalier\n3- En Tour\n4- En Fou");
    			int i = aff.saisirNb();
    			
    			switch (i) {
    				case 1 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Dame(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 2 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Cavalier(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 3 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Tour(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 4 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Fou(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				default : aff.afficher("Veuillez saisir un numero valide !"); 
    			}
    		}
    	}
    	
    	else if (p.getCouleur() == 1 && p.getLigne() == 0) {
    		
    		while(this.plateau[(p.getLigne() * 8) + p.getColonne()] instanceof Pion) {
    			aff.afficher("Promotion: En quelle pièce voulez-vous promuvoir votre pion ?\n1- En Dame\n2- En Cavalier\n3- En Tour\n4- En Fou");
    			int i = aff.saisirNb();
    			
    			switch (i) {
    				case 1 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Dame(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 2 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Cavalier(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 3 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Tour(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				case 4 : this.plateau[(p.getLigne() * 8) + p.getColonne()] = new Fou(p.getColonne(), p.getLigne(), p.getCouleur());
    					break;
    				default : aff.afficher("Veuillez saisir un numero valide !"); 
    			}
    		}
    	}
    }


    public ArrayList<Integer> porteeValide(int couleur){
    	ArrayList<Integer> les_cases = new ArrayList<Integer>();
    	for(int i = 0; i<64; i++) {//on parcours tous le plateau
    		if (this.getPlateau(i) != null && this.getPlateau(i).getCouleur() == couleur) {//si la piece est de la couleur en parametre
    			ArrayList<Integer> case_piece = this.getPlateau(i).portee();//on prends toutes les cases de la portée de la piece
    			int ind = 0;//indice pour parcourir les cases de la piece qu'on traite
    			while(ind < case_piece.size()) {
    				//on commence par verfier que la case est pas deja dans le tableau
    				int j = 0;
    				boolean deja_la = false;
    				while (j < les_cases.size() && deja_la == false) {
    					if (case_piece.get(ind) == les_cases.get(j)) {
    						deja_la = true;
    					}
    					j++;
    				}
    				if(!deja_la && ((this.getPlateau(i) instanceof Cavalier || this.cheminValide(case_piece.get(ind), this.getPlateau(i))) && this.contenuCase(case_piece.get(ind)) != couleur)) {
    					//le chemin est valide et la case est pas de la couleur paramétré
    						les_cases.add(case_piece.get(ind));//on ajoute la cases dans le tableau si elle y est pas deja
    				}
    				ind++;
    			}
    		}
    	}
    	return les_cases;
    }
    public boolean isAPortee(int ind_case, int couleur_adv) {
    	boolean test = false;
    	int i = 0;
    	ArrayList<Integer> portee = this.porteeValide(couleur_adv);
    	while(i < portee.size() && !test) {
    		if(ind_case == portee.get(i)) {
    			test = true;
    		}
    		i++;
    	}
    	return test;
    }
    public boolean roquePossible(Piece tour) {
    	boolean test = false;
    	int indiceT = (tour.getLigne()*8) + tour.getColonne();
    	if (!this.plateau[this.getIndice_roi(tour.getCouleur())].isDeja_bouge() && (tour instanceof Tour && !tour.isDeja_bouge())) {
    		if(this.getIndice_roi(tour.getCouleur()) > indiceT) {
    			int i = 1;
    			while((i < Math.abs((this.getIndice_roi(tour.getCouleur()) - indiceT))) && !this.isAPortee(this.getIndice_roi(tour.getCouleur()) - i, tour.getCouleurAdv()) && this.contenuCase(this.getIndice_roi(tour.getCouleur()) - i) == 2) {//on fait une boucle qui verif que les cases entre roi et tour sont vides et pas a portee
    				i++;
    			}
    			test = i==Math.abs((this.getIndice_roi(tour.getCouleur()) - indiceT));//test = on a fait toute la boucle
    		}
    		else {
    			int i = 1;
    			while((i <= Math.abs((this.getIndice_roi(tour.getCouleur()) - indiceT))) && !this.isAPortee(this.getIndice_roi(tour.getCouleur()) + i, tour.getCouleurAdv()) && this.contenuCase(this.getIndice_roi(tour.getCouleur()) + i) == 2) {//on fait une boucle qui verif que les cases entre roi et tour sont vides et pas a portee
    				i++;
    			}
    			test = i==Math.abs((this.getIndice_roi(tour.getCouleur()) - indiceT));//test = on a fait toute la boucle    					
    		}
    	}  	
    	return test;
    }

    public int getIndice_roi(int couleur) {
		if (couleur == 0) {
			return indice_roiBlanc;
		}
		else return indice_roiNoir;
	}
    public void setIndice_roi(int couleur, int indice) {
    	if (couleur == 0) {
			this.indice_roiBlanc = indice;
		}
		else this.indice_roiNoir = indice;
    }
    

	
}
