package com.tetrisql.forms;

import com.tetrisql.jeu.Grille;

//La factory
public class Factory {
	
	//Méthode random pour retourner une forme random
	
	//Switch utilisé par la méthode random (choisir entre différentes formes)
	
	//
}

//Les différentes formes du jeu
class Forme1 extends Grille {
	Forme1(){ //carré
		super(2,2); //Grille(int x, int y)
		grille[0][0] = 1;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 1;
	}
}

class Forme2 extends Grille {
	Forme2(){ //T à l'envers
		super(3,2);
		grille[0][0] = 0;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 0;
		grille[2][1] = 1;	
	}
}





