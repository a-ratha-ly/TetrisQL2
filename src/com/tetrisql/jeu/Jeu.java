package com.tetrisql.jeu;

import java.awt.Graphics;
import com.tetrisql.forms.Factory;
import com.tetrisql.forms.Forme;
import com.tetrisql.forms.Grille;

public class Jeu implements Forme{
	
	Grille JeuZone;
	Grille Piece;
	int FScore;
	int px,py;

	public Jeu(int xs,int ys)
	{
		JeuZone = new Grille(xs,ys,1); //zone jeu et dimensions
		Nettoyer();
		Piece = Factory.nouvellePiece(); //Pièce random
		py = 0;
		px = (xs - Piece.TailleX()) / 2;
		FScore=0;
	}
	
	//Nouvelle grille de jeu vierge
	void Nettoyer()
	{
		//x,y,sx,sy,valeur
		JeuZone.Remplir(0,0,JeuZone.TailleX(),JeuZone.TailleY(),0);
	}

	public void Dessin(Graphics g, int x, int y)
	{
		g.drawRect(x,y,2+JeuZone.TailleX()*12, 2+JeuZone.TailleY()*12);
		for(int j=0;j<JeuZone.TailleY();j++) {
			for(int i=0;i<JeuZone.TailleX();i++) {
				if((JeuZone.grille[i][j]!=0) || 
                        ((i>=px) && (i<px+Piece.TailleX()) && 
				   (j>=py) && (j<py+Piece.TailleY()) && 
				   (Piece.grille[i-px][j-py]!=0)))
				g.fillRect(3+x+i*12,3+y+j*12,10,10);
			}			
		}
	}

	//Gère les différents enchaînements du jeu
	public boolean Etape(boolean Toucher)
	{
	  if(JeuZone.SiVide(Piece,px,py+1))
	  {
		//Descendre la pièce
		JeuZone.Descendre(Piece,px,py);

		// Effacer les lignes remplies
		int LignePoints = 50;
		for(int j=JeuZone.TailleY()-1;j>=0;j--) {
			while(LigneEstElleRempli(j)==true)
			{
				LigneEffacer(j);
				//Notation simple
				FScore+=LignePoints;
				LignePoints *= 2;
			}
		}
	
		//Nouvelle piece
		Piece = Factory.nouvellePiece();
		py=0;
		px=(JeuZone.TailleX()-Piece.TailleX())/2;
		if(JeuZone.SiVide(Piece,px,py))
		return true;
	  }
	  py++;
	  
	  if(Toucher)
		FScore += 1;
	  return false;
	}

	//Gère quand une ligne est complètement remplie (scan la grille)
	private boolean LigneEstElleRempli(int y)
	{
		for(int i=0;i<JeuZone.TailleX();i++)
			if(JeuZone.grille[i][y]==0)
				return false;
		return true;
	}

	//Gère effacement d'une ligne remplie/complétée
	private void LigneEffacer(int y)
	{
		for(int j=y;j>0;j--) {
			for(int i=0;i<JeuZone.TailleX();i++)
				JeuZone.grille[i][j]=JeuZone.grille[i][j-1];
		}
		
		for(int i=0;i<JeuZone.TailleX();i++)
			JeuZone.grille[i][0]=0;
	}

	//Retourne le score
	public int Score()
	{
		return FScore;
	}

	//Déplacement vers la gauche (appelée par AllerGauche())
	public void AllerGauche(int i)
	{
		if(JeuZone.SiVide(Piece,px-i,py))
			return;
		px-=i;
	}

	//Déplace vers la gauche de 1
	public void AllerGauche()
	{
		AllerGauche(1);
	}

	//Décale vers la droite de 1
	public void AllerDroite(int i)
	{
		if(JeuZone.SiVide(Piece,px+i,py))
			return;
		px+=i;
	}

	public void AllerDroite()
	{
		AllerDroite(1);
	}

	public void TournerDroite()
	{
		Grille test = Factory.nouvellePiece(Piece);
		test.TourneraDroite();
		if(!JeuZone.SiVide(test,px,py))
			Piece=test;
	}

	public void TournerGauche()
	{
		Grille test = Factory.nouvellePiece(Piece);
		test.TourneraGauche();
		if(!JeuZone.SiVide(test,px,py))
			Piece=test;
	}

	public void Descendre()
	{
		while(!JeuZone.SiVide(Piece,px,py+1)){
			py++;
			FScore += 2;
		}
	}

}

//Pour enregistrer le jeu
class Memento{
	Jeu Cible;
	Grille JeuZone;
	Grille Piece;
	int FScore;
	int px,py;

	public Memento(Jeu cible){
		Cible = cible;
		JeuZone = new Grille(Cible.JeuZone);
		Piece = new Grille(Cible.Piece);
		FScore = Cible.FScore;
		px = Cible.px;
		py = Cible.py;
	}
	
	public void CibleChanger(Jeu cible){
		Cible = cible;
	}

	public void Chargement(){
		try{
			Cible.JeuZone = JeuZone;
			Cible.Piece = Piece;
			Cible.FScore = FScore;
			Cible.px = px;
			Cible.py = py;
		}catch(NullPointerException e){
			System.out.println("Non restauré");
		}
	}
}
