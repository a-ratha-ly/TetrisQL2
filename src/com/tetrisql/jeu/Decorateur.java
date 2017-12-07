package com.tetrisql.jeu;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Decorateur implements Forme{

	String messageFixe;
	String messageVariable;

	Forme contenu;

	public Decorateur(Forme contenu, String message){
		this.contenu = contenu;
		this.messageFixe = message;
	}

	public void Dessin(Graphics g, int sx, int sy)
	{
		this.contenu.Dessin(g, sx, sy);

		String msg;
		msg = this.messageFixe + this.messageVariable;

		FontMetrics fm;
		fm = g.getFontMetrics();

		int y;
		y = fm.getHeight();

		int x = fm.stringWidth(msg);
		g.drawString(msg, sx+5, sy+5+y/2);
	}

	public void changerMessage(String nouveauMessage){
		this.messageVariable = nouveauMessage;
	}
}

