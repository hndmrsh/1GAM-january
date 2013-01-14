package com.samuelhindmarsh.ogam.january.game.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.samuelhindmarsh.ogam.january.input.Action;
import com.samuelhindmarsh.ogam.january.input.InputManager;

public class MainGameState extends BasicGameState{

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		debug(g);
		
	}
	
	private void debug(Graphics g){
		// TODO bind commands to inputs as in http://www.slick2d.org/wiki/index.php/Input
		Action[] values = Action.values();
		for(int i = 0; i < values.length; i++){
			g.setColor(InputManager.getInstance().isControlDown(values[i]) ? Color.green : Color.red);
			g.drawString(values[i].toString(), 20, i*20+80);
		}
		
		g.setColor(InputManager.getInstance().isLastInputFromController()? Color.green : Color.red);
		g.drawString("Last input was from controller", 20, 400);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	} 

}
