package com.tetrisql.jeu;

public class Grille {
	//Attributs
	public int x, y, valeur; //val = pour détecter quelle forme-pièce
	public int grille[][];
	
	//Constructeur 1 (pour Factory)
	public Grille(int tx, int ty) { //tailles
		//grille vide
		this(tx, ty, 0); 
	}
	
	//Constructeur 2
	public Grille(int tx, int ty, int val) {	
		x = tx;
		y = ty;
		valeur = val;	
		
		//création grille
		grille = new int[x][y];
		
		//remplissage grille
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				grille[i][j] = val;
			}
		}
	}
	
	//Constructeur 3
	public Grille(Grille g) { //récupérer la grille en argument
		x = g.x;
		y = g.y;
		valeur = g.valeur;
		
		grille = new int[x][y];
		
		//remplissage grille
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				grille[i][j] = g.grille[i][j];
			}
		}	
	}
	
	//weigh, height
	public void remplirGrille(int tx, int ty, int w, int h, int val) {
		for (int i=0; i<w; i++) {
			for (int j=0; j<h; j++) {
				if((tx+i<x) && (ty+j<y) && (tx+i>=0) && (ty+j>=0)) {
					grille[x+i][y+j] = val;
				}
			}
		}
	}
	
	//getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	//mouvements
	public void descendre(Grille g, int tx, int ty) {
		int px, py; //positions
	
		px = g.x;
		py = g.y;
		
		if(px+tx>x) {
			px = x-tx;
		}
		
		if(py+ty>y) {
			py = y-ty;
		}
		
		for(int i=0; i<px; i++) {
			for(int j=0; j<py; j++) {
				if((tx+i<x) && (tx+j<y) && (tx+i>=0) && (ty+j>=0)) {
					grille[tx+i][ty+j] = grille[tx+i][ty+j] | g.grille[i][j];
				}
			}
		}
	}
}
