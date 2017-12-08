package com.tetrisql.forms;

import java.awt.Graphics;

public class Zone implements Forme{
	
	int Largeur;
	int Hauteur;

	public Zone(int Large, int Haut){
		Largeur = Large;
		Hauteur = Haut;
	}

	public void Largeur(int Large,int Haut){
		Largeur = Large;
		Hauteur = Haut;
	}

	public void dessin(java.awt.Graphics g, int sx, int sy)
	{
		g.draw3DRect(sx-1,sy,Largeur,Hauteur,true);
	}

}