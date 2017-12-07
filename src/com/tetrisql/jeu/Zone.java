package com.tetrisql.jeu;

import java.awt.Graphics;

public class Zone implements Forme {

	int largeur;
	int hauteur;

	public Zone(int largeur, int hauteur){
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void changerDimension(int largeur,int hauteur){
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void Dessin(Graphics g, int sx, int sy)
	{
		g.draw3DRect(sx-1, sy, this.largeur, this.hauteur, true);
	}

}