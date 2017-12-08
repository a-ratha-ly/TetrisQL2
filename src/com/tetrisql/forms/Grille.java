package com.tetrisql.forms;

public class Grille {

	public int grille[][];
	public int val;
	protected int x,y;
	
	//Constructeur 1
	public Grille(int sx,int sy,int valeur)
	{
		this.x = sx;
		this.y = sy;
		this.val = valeur;
		grille = new int[x][y];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				grille[i][j]=val;
			}		
		}
			
	}
	
	//Constructeur 2
	//Créer formes (pour Factory)
	public Grille(int sx,int sy)
	{
		this(sx,sy,0);
	}
	
	public Grille(Grille g)
	{
		x = g.x;
		y = g.y;
		val = g.val;
		grille = new int[x][y];
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				grille[i][j]=g.grille[i][j];
			}		
		}			
	}
	
	//Methodes
	public void Remplir(int sx,int sy,int w,int h,int valeur)
	{
		for(int i=0; i<w ;i++) {
			for(int j=0; j<h ;j++) {
				if((sx+i<x) && (sy+j<y) && (sx+i>=0) && (sy+j>=0)){
					grille[sx+i][sy+j]=valeur;
				}
			}
		}

	}

	public void TourneraDroite() 
	{
		int nouvelleGrille[][] = new int[y][x];
		int s=y-1;
		
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++){
				nouvelleGrille[i][j] = grille[j][s-i];
			}
			grille=nouvelleGrille;
		}
			
		int tmp=x;
		x=y;
		y=tmp;
	}

	public void TourneraGauche()
	{
		int nouvelleGrille[][] = new int[y][x];
		int s=x-1;
		
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++){
				nouvelleGrille[i][j]=grille[s-j][i];
			}	
			grille=nouvelleGrille;
		}
		
		int tmp=x;
		x=y;
		y=tmp; 
	}

	public int TailleX() {
		return x;
	}
	public int TailleY() {
		return y;
	}

	public void Descendre(Grille modele,int sx,int sy)
	{
		int mx,my;

		mx = modele.x;
		my = modele.y;
		if(mx+sx>x) mx = x-sx;
		if(my+sy>y) my = y-sy;

		for(int i=0;i<mx;i++) {
			for(int j=0;j<my;j++) {
				if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0))
				{
					grille[sx+i][sy+j]= grille[sx+i][sy+j] | modele.grille[i][j];
				}
			}	
		}
	}

	//Check si grille est vide pour pouvoir la déplacer (voir AllerGauche, AllerDroite)
	public boolean SiVide(Grille modele,int sx,int sy)
	{
		int mx,my;

		mx = modele.x;
		my = modele.y;

		for(int i=0; i<mx ;i++) {
			for(int j=0; j<my ;j++)
			{
				if((sx+i<x) && (sy+j<y) &&  (sx+i>=0) && (sy+j>=0)){
					if(grille[sx+i][sy+j]!=0 && modele.grille[i][j]!=0)
						return true;
				}
				else{ 
					if(val!=0 && modele.grille[i][j]!=0)
						return true;
				}
			} 
		}
		
		return false;
	}
}
