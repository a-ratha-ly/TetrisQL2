package com.tetrisql.jeu;

import java.awt.*;
import java.awt.event.*;

import com.tetrisql.forms.Zone;

public class Tetris extends java.applet.Applet implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Etat du jeu
	Jeu Jeu;

	//Enregistrer jeu
	Memento Enregistrement;

	//Tache faisant fonctionner le jeu 
	private volatile Thread JeuFonctionner;
	
	//Dimension de la zone de jeu
	int xs,ys;
	int x,y;

	//Pour le score
	Decorator Score;

	//Etat actuel du jeu 
	Decorator Etat;

	//Statut de jeu
	boolean JeuFini;
	boolean Pause;

	public void init()
	{
	
		try {
			x=Integer.parseInt(getParameter("X"));
		} catch (NullPointerException e) { x=10; }
		  catch (NumberFormatException e) { x=10; }

		try {
			y=Integer.parseInt(getParameter("Y"));
		} catch (NullPointerException e) { y=20; }
		  catch (NumberFormatException e) { y=20; }
		 
		Jeu = new Jeu(x,y);
		Score = new Decorator(new Zone(80,16),"Score: ");
		Etat = new Decorator(new Zone(95,16),"Etat: ");
		Enregistrement = null;

		xs = x*12+16;  // Zone de jeu
		ys = y*12+4   // Zone de jeu
		       +60;	// Score et Etat
		resize(xs,ys);
		requestFocus();

		// Affectation des taches pour les touches a utiliser
		addKeyListener(new KeyListener(){
		public void keyPressed(KeyEvent e){
			int Touche = e.getKeyCode();

			switch (Touche)
			{
				// Commence par le début 
				case KeyEvent.VK_R:
					JeuFini = false;
					Jeu = new Jeu(x,y);
					if(Enregistrement != null)
						Enregistrement.cibleChanger(Jeu);
					if(!Pause && !JeuFini) stop();
					start(); repaint(); break;
				// Enregistrement du jeu dans l'etat actuel 
				case KeyEvent.VK_K:
					Enregistrement = new Memento(Jeu);
					break;
				// Chargement du jeu dans son dernier etat 
				case KeyEvent.VK_Y:
					if(Enregistrement != null) Enregistrement.Chargement();
					repaint(); break;
				// Mettre en pause le jeu 
				case KeyEvent.VK_P:
					if(Pause && !JeuFini){ start(); Pause = false; }
					else{ stop();  Pause = true;  }
					repaint(); break;
			}

			if(!JeuFini && !Pause)
			{
				switch (Touche)
				{
					case KeyEvent.VK_LEFT : Jeu.allerGauche(); repaint(); break;
					case KeyEvent.VK_RIGHT: Jeu.allerDroite(); repaint(); break;
					case KeyEvent.VK_UP   : Jeu.TournerDroite(); repaint(); break;
					case KeyEvent.VK_DOWN : JeuFini = Jeu.etape(true); repaint(); break;
					//REVOIR CES TOUCHES, CA NE FONCTIONNE PAS
					case KeyEvent.VK_Q    : Jeu.tournerGauche(); repaint(); break;
					case KeyEvent.VK_D    : Jeu.TournerDroite(); repaint(); break;
					case KeyEvent.VK_SPACE: Jeu.descendre();     repaint(); break;
				}
		  	}
		}
		public void keyReleased(KeyEvent e){}
		public void keyTyped(KeyEvent e){}
		});
	}

	// Debuter du jeu
	public void start()
	{
		if(JeuFonctionner == null)
		{
			JeuFonctionner = new Thread(this);
			JeuFonctionner.start();
		}
	}

	// Mettre en pause le jeu
	public void stop()
	{
		if(JeuFonctionner != null && JeuFonctionner.isAlive())
			JeuFonctionner.interrupt();
		JeuFonctionner = null;
	}

	public void destroy()
	{
	}
   
	public void run()
	{
		JeuFini = false;
		Pause = false;
		while(!JeuFini && !Pause)
		{
			JeuFini = Jeu.etape(false);
			repaint();
			try {Thread.sleep(500);} catch (InterruptedException e){ return; }
		}
	}

	public void paint(Graphics g)
	{
		Jeu.dessin(g,10,10);
		Score.EcritureDeterminer((new Integer(Jeu.Score())).toString());
		Score.dessin(g,5,ys-40);
		if(JeuFini)
			Etat.EcritureDeterminer("Game Over");
		else if(Pause)
			Etat.EcritureDeterminer("Pause");
		else
			Etat.EcritureDeterminer("En cours");
		Etat.dessin(g,90,ys-40);
		g.drawString("TETRIS",xs,20);
		g.drawString("QL2",xs,35);
	}
}
