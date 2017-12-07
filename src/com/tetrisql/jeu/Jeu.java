package com.tetrisql.jeu;
import java.awt.Graphics;
import com.tetrisql.forms.Factory;

public class Jeu implements Forme {
	
	Grille JeuZone;
	Grille Piece;
	int FScore;
	int px, py;
	
	public Jeu(int xs, int ys) {
		JeuZone = new Grille(xs, ys, 1);
		Nettoyer();
		Piece = Factory.creerRandomTetrimino();
		py = 0;
		px = (xs - Piece.getX()) / 2;
		FScore = 0;
	}
	
	void Nettoyer() {
		JeuZone.remplirGrille(0, 0, JeuZone.getX(), JeuZone.getY(), 0);
	}
	
	public void Dessin(Graphics g, int x, int y) {
		g.drawRect(x, y, 2+JeuZone.getX()*12, 2 + JeuZone.getY()*12);
		for (int j=0; j<JeuZone.getY(); j++)
			for (int i=0; i<JeuZone.getX(); i++)
				if ((JeuZone.grille[i][j]!=0) || 
                           ((i>=px) && (i<px+Piece.getX()) && 
				   (j >= py) && (j < py + Piece.getY()) && 
				   (Piece.grille[i-px][j-py] != 0)))
				g.fillRect(3+x+i*12,3+y+j*12,10,10);
		
	}
	
}
