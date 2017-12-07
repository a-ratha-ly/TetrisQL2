package com.tetrisql.jeu;

import java.util.*;

public class Grille {
	//Attributs
	public int x, y, valeur; //val = pour détecter quelle forme-pièce
	public int grille[][];
	
	//Constructeur
	public Grille(int li, int col, int val) {	
		x = li;
		y = col;
		valeur = val;	
		
		//création grille
		grille = new int[x][y];
		
		//dimensionnement grille
		for (int i=0; i<x; i++) {
			for (int j=0; j<y; j++) {
				grille[i][j] = val;
			}
		}
	}
	
}
