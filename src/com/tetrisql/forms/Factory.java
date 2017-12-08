package com.tetrisql.forms;

public class Factory {
	
	//Détermine le type de piece (random)
	public static Grille nouvellePiece(){
		int Genre = (int)(Math.random()*7.0);
		return nouvellePiece(Genre);
	}
	
	//Retourne une pièce
	public static Grille nouvellePiece(int Genre){
		switch(Genre){
			case 0: return new Piece1();
			case 1: return new Piece2();
			case 2: return new Piece3();
			case 3: return new Piece4();
			case 4: return new Piece5();
			case 5: return new Piece6();
			case 6: return new Piece7();
		}
		return nouvellePiece();
	}

	public static Grille nouvellePiece(Grille ancien){
		return new Grille(ancien);
	}
}

////////// TETRIMINOS ////////// 

class Piece1 extends Grille{
	Piece1(){ //I
		super(4,1);
 		grille[0][0] = 1;
 		grille[0][1] = 1;
 		grille[0][2] = 1;
 		grille[0][3] = 1;
	}
}

class Piece2 extends Grille{
	Piece2(){ //carré
		super(2,2);
		grille[0][0]=1;
		grille[1][0]=1;
		grille[0][1]=1;
		grille[1][1]=1;
	}
}

class Piece3 extends Grille{
	Piece3(){ //T inversé
		super(3,2);
		grille[0][0] = 0;
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 1;
		grille[2][1] = 0;
	}
}

class Piece4 extends Grille{
	Piece4(){ //L
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 0;
		grille[2][0] = 1;
		grille[2][1] = 0;
	}
}

class Piece5 extends Grille{
	Piece5(){ //L inversé
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 0;
		grille[2][0] = 1;
		grille[2][1] = 1;
	}
}

class Piece6 extends Grille{
	Piece6(){ //biais
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 0;
		grille[2][1] = 1;	
	}
}

class Piece7 extends Grille{
	Piece7(){ //I
		super(3,2);
		grille[0][0] = 0;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 1;
		grille[2][1] = 0;
	}
}