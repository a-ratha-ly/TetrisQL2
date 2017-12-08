package com.tetrisql.jeu;

import java.awt.FontMetrics;
import java.awt.Graphics;

import com.tetrisql.forms.Forme;

public class Decorator implements Forme{
	
	//Parties fixes et variables de la fonction à écrire
	String FixeEcriture;
	String VariableEcriture;

	//Figure à écrire à l'intérieur
	Forme Contenu;

	public Decorator(Forme contenu, String ecriture){
		Contenu = contenu;
		FixeEcriture = ecriture;
	}

	//Pour afficher le score et l'état du jeu (pause/en cours)
	public void Dessin(Graphics g, int sx, int sy)
	{
		//Permet de dessiner à l'intérieur
		Contenu.Dessin(g,sx,sy); //grille et dimension

		String msj = FixeEcriture+VariableEcriture;
		int x,y;
		FontMetrics fm;
		fm = g.getFontMetrics();
		y = fm.getHeight();
		x = fm.stringWidth(msj);
		g.drawString(msj,sx+5,sy+5+y/2);
	}

	public void EcritureDeterminer(String NouvelleEcriture){
		VariableEcriture = NouvelleEcriture;
	}

}

