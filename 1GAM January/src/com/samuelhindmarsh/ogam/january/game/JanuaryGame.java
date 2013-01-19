package com.samuelhindmarsh.ogam.january.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.samuelhindmarsh.ogam.january.game.states.MainGameState;

public class JanuaryGame extends StateBasedGame {

	public static final boolean DEBUG = true;
	public static int STATE_MAIN_GAME = 0;
	
	public JanuaryGame(String title) {
		super(title);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MainGameState());
	}
	
}
