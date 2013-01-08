package com.samuelhindmarsh.ogam.january;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.game.JanuaryGame;

public class JanuaryWindow extends AppGameContainer {
	private static String TITLE = "January";
	private static int WIDTH = 800, HEIGHT = 600;
	private static boolean FULLSCREEN = false;
	
	public JanuaryWindow(Game game, int width, int height, boolean fullscreen) throws SlickException {
		super(game, width, height, fullscreen);
	}
	
	public static void main(String[] args) throws SlickException {
		JanuaryGame game = new JanuaryGame(TITLE);
		
		JanuaryWindow window = new JanuaryWindow(game, WIDTH, HEIGHT, FULLSCREEN);
		window.setVSync(true);
		window.start();
		
		game.enterState(JanuaryGame.STATE_MAIN_GAME);
	}

}
