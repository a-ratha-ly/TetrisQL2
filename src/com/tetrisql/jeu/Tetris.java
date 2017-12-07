package com.tetrisql.jeu;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;

public class Tetris extends java.applet.Applet implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Jeu jeu;
	private volatile Thread thread; 
	public boolean fin;
	public boolean stop;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	//démarrer le jeu
	public void start() {
		if(thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	
	//arrêter le jeu
	public void stop() {
		if(thread != null && thread.isAlive()) {
			thread.interrupt();
		}
		thread = null;
	}
	
	



}
