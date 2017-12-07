package com.tetrisql.forms;

import com.tetrisql.jeu.Grille;

//La factory
public class Factory {
	
	//Méthode random pour retourner une forme random
	
	//Switch utilisé par la méthode random (choisir entre différentes formes)
	
	//
}

//Les différentes formes du jeu
class TetriminoI extends Grille {
	TetriminoI(){ //barre
		super(4,1); //Grille(int x, int y)
		grille[0][0] = 1;
		grille[1][0] = 1;
		grille[2][0] = 1;
		grille[3][0] = 1;
	}
}

class TetriminoO extends Grille {
	TetriminoO(){ //carré
		super(2,2); 
		grille[0][0] = 1;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 1;
	}
}

class TetriminoT extends Grille {
	TetriminoT(){ // T
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 1;
		grille[2][1] = 0;	
	}
}

class TetriminoL extends Grille {
	TetriminoL(){ // L 
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 0;
		grille[2][0] = 1;
		grille[2][1] = 0;	
	}
}

class TetriminoJ extends Grille {
	TetriminoJ(){ // L inversé
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 0;
		grille[2][0] = 1;
		grille[2][1] = 1;	
	}
}

class TetriminoZ extends Grille {
	TetriminoZ(){ // biais 
		super(3,2);
		grille[0][0] = 1;
		grille[0][1] = 0;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 0;
		grille[2][1] = 1;	
	}
}

class TetriminoS extends Grille {
	TetriminoS(){ // biais inversé
		super(3,2);
		grille[0][0] = 0;
		grille[0][1] = 1;
		grille[1][0] = 1;
		grille[1][1] = 1;
		grille[2][0] = 1;
		grille[2][1] = 0;	
	}
}





