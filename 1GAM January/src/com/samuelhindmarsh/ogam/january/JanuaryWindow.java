package com.samuelhindmarsh.ogam.january;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

import com.samuelhindmarsh.ogam.january.game.JanuaryGame;
import com.samuelhindmarsh.ogam.january.managers.InputManager;
import com.samuelhindmarsh.ogam.january.managers.LevelManager;

public class JanuaryWindow extends AppGameContainer {
	private static final String TITLE = "January";
	public static final int WIDTH = 240, HEIGHT = 135;
	
	public static int WINDOW_WIDTH = 1440, WINDOW_HEIGHT = 810;
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
		InputManager.INSTANCE.init(getInput());
		LevelManager.INSTANCE.init();
	};
	
	public static void main(String[] args) throws SlickException {
		JanuaryGame game = new JanuaryGame(TITLE);
		
		JanuaryWindow window = new JanuaryWindow(game, WINDOW_WIDTH, WINDOW_HEIGHT, FULLSCREEN);
		window.setVSync(true);
		window.start();
		
		game.enterState(JanuaryGame.STATE_MAIN_GAME);
	}

}
