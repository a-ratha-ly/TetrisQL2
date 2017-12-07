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
	
	// Nettoyer la grille
	void Nettoyer() {
		JeuZone.remplirGrille(0, 0, JeuZone.getX(), JeuZone.getY(), 0);
	}
	
	public void Dessin(Graphics g, int x, int y) {
		g.drawRect(x, y, 2+JeuZone.getX()*12, 2 + JeuZone.getY()*12);
		for (int j=0; j<JeuZone.getY(); j++) {
			for (int i=0; i<JeuZone.getX(); i++) {
				if ((JeuZone.grille[i][j]!=0) || 
                        ((i>=px) && (i<px+Piece.getX()) && 
				   (j >= py) && (j < py + Piece.getY()) && 
				   (Piece.grille[i-px][j-py] != 0))) {
					g.fillRect(3+x+i*12, 3+y+j*12, 10, 10);
				}
			}		
		}	
	}
	
	public boolean Etape(boolean Toucher) {
	  if (JeuZone.vide(Piece, px, py+1)) {
		// Descendre la piece
		JeuZone.descendre(Piece,px,py);

		// Effacer les lignes remplies
		int LignePoints = 50;
		for (int j = JeuZone.getY()-1; j>=0; j--) {
			while (CheckLigneRemplie(j) == true) {
				LigneEffacer(j);
				FScore+= LignePoints;
				LignePoints *= 2;
			}
		}

		// Nouvelle piece
		Piece = Factory.creerRandomTetrimino();
		py = 0;
		px = (JeuZone.getX() - Piece.getX()) / 2;
		if (JeuZone.vide(Piece, px, py))
		return true;
	  }
	  py++;
	  if (Toucher)
		FScore += 1;
	  return false;
	}
	
	// Si la ligne est remplie, retourne true
	private boolean CheckLigneRemplie(int y) {
		for(int i=0;i<JeuZone.getX();i++) 
			if(JeuZone.grille[i][y]==0)
				return false;
		return true;
	}

	// Efface la ligne
	private void LigneEffacer(int y) {
		for(int j=y; j>0; j--) {
			for(int i=0; i < JeuZone.getX(); i++) {
				JeuZone.grille[i][j] = JeuZone.grille[i][j-1];
			}
		}
		for(int i=0; i < JeuZone.getX(); i++) {
			JeuZone.grille[i][0]=0;
		}
	}
	
	// retourne le score
	public int Score()
	{
		return FScore;
	}

	// deplacement gauche
	public void AllerGauche(int i)
	{
		if (JeuZone.vide(Piece, px-i, py))
			return;
		px-=i;
	}

	public void AllerGauche()
	{
		AllerGauche(1);
	}

	// deplacement droite
	public void AllerDroite(int i)
	{
		if (JeuZone.vide(Piece, px+i, py))
			return;
		px+= i;
	}

	public void AllerDroite()
	{
		AllerDroite(1);
	}
	
	// rotation droite
	public void TournerDroite()
	{
		Grille test = Factory.nouvellePiece(Piece);
		test.deplacerDroite();
		if(!JeuZone.vide(test,px,py))
			Piece=test;
	}

	// rotation gauche
	public void TournerGauche()
	{
		Grille test = Factory.nouvellePiece(Piece);
		test.deplacerGauche();
		if(!JeuZone.vide(test, px, py))
			Piece = test;
	}

	public void Descendre()
	{
		while(!JeuZone.vide(Piece, px, py+1)){
			py++;
			FScore += 2;
		}
	}

}
