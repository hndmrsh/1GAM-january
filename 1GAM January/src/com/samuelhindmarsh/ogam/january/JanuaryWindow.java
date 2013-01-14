package com.samuelhindmarsh.ogam.january;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.game.JanuaryGame;
import com.samuelhindmarsh.ogam.january.input.InputManager;

public class JanuaryWindow extends AppGameContainer {
	private static String TITLE = "January";
	public static int WIDTH = 800, HEIGHT = 600;
	private static boolean FULLSCREEN = false;
	
	private static boolean DEBUG_MODE = true;
	
	public JanuaryWindow(Game game, int width, int height, boolean fullscreen) throws SlickException {
		super(game, width, height, fullscreen);
	}
	
	@Override
	protected void initSystem() throws SlickException {
		super.initSystem();
		
		// setup window properties
		setVSync(true);
		setShowFPS(DEBUG_MODE);
		
		// initialize managers
		InputManager.getInstance().init(getInput());
	};
	
	public static void main(String[] args) throws SlickException {
		JanuaryGame game = new JanuaryGame(TITLE);
		
		JanuaryWindow window = new JanuaryWindow(game, WIDTH, HEIGHT, FULLSCREEN);
		window.setVSync(true);
		window.start();
		
		game.enterState(JanuaryGame.STATE_MAIN_GAME);
	}

}
