package com.tetrisql.jeu;

import java.util.*;

public class Grille {
	//Attributs
	public int x, y, valeur; //val = pour détecter quelle forme-pièce
	public int grille[][];
	
	//Constructeur 1
	public Grille(int li, int col) {
		//grille vide
		this(li, col, 0); 
	}
	
	//Constructeur 2
	public Grille(int li, int col, int val) {	
		x = li;
		y = col;
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

	
}
